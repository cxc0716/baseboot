package com.cxc.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cxc.common.util.RandomCodeGenerator;
import com.cxc.configuration.InitConfig;
import com.cxc.model.CurrentIssueInfo;
import com.cxc.model.RecentIssueInfo;
import com.cxc.model.RecentIssueItem;
import com.cxc.model.SubmitParamBean;
import com.cxc.service.MockService;
import com.cxc.service.RequestProxyService;
import com.cxc.service.RequestService;
import com.google.common.collect.Lists;

@Service
public class CoreServiceImpl {

    /**
     * //todo-cxc
     * <ul>
     * <li>code可配</li>
     * <li>alert</li>
     * <li>连中指定次数reset</li>
     * <li>以money来判断是否停止</li>
     * <li>跨天问题</li>
     * <li>宕机后重启恢复问题</li>
     * <li>统计</li>
     * </ul>
     */

    private final static Logger logger = LoggerFactory
        .getLogger(CoreServiceImpl.class);

    @Autowired
    private RequestProxyService requestProxyService;

    @Autowired
    private RequestService requestCb868MasterService;

    @Autowired
    private RequestService requestCb868SlaveService;

    @Value("${platform.issue.maxHitCount}")
    private int maxHitCount;

    @Value("${platform.1.bonus}")
    private double bonus;

    @Value("${platform.1.rate}")
    private double rate;

    @Value("${platform.issue.mode}")
    private int mode;

    public static int INIT_TIMES = 1;

    //连续命中次数
    private int hitCount = 0;

    //当前投入本金
    private BigDecimal currentCapital;

    //    Queue<Boolean> hitRecordQueue = new ArrayBlockingQueue<Boolean>(
    //        maxHitCount);

    public void submit() {
        //login 
        boolean loginOk = requestCb868MasterService.login();
        if (loginOk) {
            logger.info("[op:submit] server1 login successfully");
            boolean login = requestCb868SlaveService.login();
            if (login) {
                logger.info("op:submit] server2 login successfully");
            }
        }
        List<String> codeList = RandomCodeGenerator.getCode(50);
        List<String> subtractCodeList = RandomCodeGenerator
            .getSubtractCode(codeList);
        logger.info("[op:submit] server1 code. list={}",
            StringUtils.join(codeList, "|"));
        logger.info("[op:submit] server2 code. list={}",
            StringUtils.join(subtractCodeList, "|"));
        SubmitParamBean master = new SubmitParamBean();
        master.setMode(mode);
        master.setMain(true);
        master.setTime(INIT_TIMES);
        master.setNos(codeList);
        master.setPrice(getPrice(INIT_TIMES));

        SubmitParamBean slave = new SubmitParamBean();
        slave.setMode(mode);
        slave.setMain(false);
        slave.setTime(INIT_TIMES);
        slave.setNos(subtractCodeList);
        master.setPrice(getPrice(INIT_TIMES));

        currentCapital = getPrice(INIT_TIMES);

        while (true) {
            // pass rest money check
            boolean pass = requestProxyService.initCheck();
            if (!pass) {
                logger.warn("[op:submit] the capital is not enough.");
                break;
            }
            CurrentIssueInfo currentIssueInfo = requestCb868MasterService
                .getCurrentIssueInfo();
            if (currentIssueInfo.getDailyCount() <= currentIssueInfo
                .getOpenedCount()) {
                logger.info("[op:submit] today's game is over.");
            }
            String currentIssue = currentIssueInfo.getIssue();
            master.setIssue(currentIssue);
            slave.setIssue(currentIssue);
            //todo time check protect
            boolean submit = requestCb868MasterService.submit(master);
            if (submit) {
                logger.info("[op:submit] server1 submit ok.");
                submit = requestCb868SlaveService.submit(slave);
                if (submit) {
                    logger.info("[op:submit] server2 submit ok.");
                }
            }
            BigDecimal total = BigDecimal.ZERO;
            if (submit) {
                //开始轮询结果
                while (true) {
                    RecentIssueInfo recentIssueInfo = requestCb868MasterService
                        .getRecentIssueInfo(1, 30);
                    if (recentIssueInfo != null) {
                        List<RecentIssueItem> items = recentIssueInfo
                            .getItems();
                        if (org.apache.commons.collections.CollectionUtils
                            .isNotEmpty(items)) {
                            RecentIssueItem recentIssueItem = items.get(0);
                            if (recentIssueItem.getIssue()
                                .equals(currentIssue)) {
                                BigDecimal price1 = BigDecimal.ZERO;
                                BigDecimal price2 = BigDecimal.ZERO;
                                logger.info(
                                    "[op:submit] get the result.lastIssue={}",
                                    JSON.toJSONString(recentIssueItem));
                                List<String> retCodeList = recentIssueItem
                                    .getCodeList();
                                String suffix = retCodeList
                                    .get(retCodeList.size() - 2)
                                    + retCodeList.get(retCodeList.size() - 1);
                                if (codeList.contains(suffix)) {
                                    //master hit
                                    currentCapital = master.getPrice()
                                        .multiply(new BigDecimal(bonus));
                                    price1 = currentCapital
                                        .subtract(master.getPrice());
                                    price2 = BigDecimal.ZERO
                                        .subtract(slave.getPrice());
                                    int times = getTimes(currentCapital);
                                    master.setPrice(getPrice(times));
                                    master.setTime(times);
                                    if (master.isMain()) {
                                        hitCount++;
                                    } else {
                                        hitCount = 1;
                                        slave.setTime(INIT_TIMES);
                                        slave.setPrice(getPrice(INIT_TIMES));
                                        master.setMain(true);
                                        slave.setMain(false);
                                    }
                                } else {
                                    //slave hit
                                    currentCapital = slave.getPrice()
                                        .multiply(new BigDecimal(bonus));
                                    price2 = currentCapital
                                        .subtract(slave.getPrice());
                                    price1 = BigDecimal.ZERO
                                        .subtract(master.getPrice());
                                    int times = getTimes(currentCapital);
                                    slave.setTime(times);
                                    slave.setPrice(getPrice(times));
                                    if (slave.isMain()) {
                                        hitCount++;
                                    } else {
                                        hitCount = 1;
                                        master.setTime(INIT_TIMES);
                                        master.setPrice(getPrice(INIT_TIMES));
                                        master.setMain(false);
                                        slave.setMain(true);
                                    }
                                }
                                total = total.add(price1.add(price2));
                                logger.info(
                                    "[op:submit] sub hit {} times continuously,server1:{},server2:{},current:{},total:{}",
                                    hitCount,
                                    price1.setScale(3, RoundingMode.HALF_UP)
                                        .toPlainString(),
                                    price2.setScale(3, RoundingMode.HALF_UP)
                                        .toPlainString(),
                                    currentCapital
                                        .setScale(3, RoundingMode.HALF_UP)
                                        .toPlainString(),
                                    total.setScale(3, RoundingMode.HALF_UP)
                                        .toPlainString());
                                break;
                            } else {
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {}
                            }
                        }
                    }
                }
            }
            if (hitCount >= maxHitCount) {
                logger.info("[op:submit] hit {} times continuously", hitCount);
                break;
            }
        }
        logger.info("[op:submit] ");

    }

    private BigDecimal getPrice(int times) {
        int base = 100;
        BigDecimal result = BigDecimal.ZERO;
        if (mode == SubmitParamBean.MODE_YUAN) {
            result = new BigDecimal(base);
        } else if (mode == SubmitParamBean.MODE_JIAO) {
            result = new BigDecimal(base).divide(new BigDecimal(10));
        } else if (mode == SubmitParamBean.MODE_FEN) {
            result = new BigDecimal(base).divide(new BigDecimal(100));

        } else if (mode == SubmitParamBean.MODE_LI) {
            result = new BigDecimal(base).divide(new BigDecimal(1000));
        }
        return result.multiply(new BigDecimal(times)).setScale(3,
            RoundingMode.HALF_UP);
    }

    private BigDecimal getBaseBonus(double bonus) {
        //1.95是以分来基准
        BigDecimal result = BigDecimal.ZERO;
        if (mode == SubmitParamBean.MODE_YUAN) {
            result = new BigDecimal(bonus).multiply(new BigDecimal(100));
        } else if (mode == SubmitParamBean.MODE_JIAO) {
            result = new BigDecimal(bonus).multiply(new BigDecimal(10));
        } else if (mode == SubmitParamBean.MODE_FEN) {
            result = new BigDecimal(bonus).divide(new BigDecimal(1));
        } else if (mode == SubmitParamBean.MODE_LI) {
            result = new BigDecimal(bonus).divide(new BigDecimal(10));
        }
        return result;
    }

    private int getTimes(BigDecimal currentCapital) {
        //按元处理
        BigDecimal result = BigDecimal.ZERO;
        if (mode == SubmitParamBean.MODE_YUAN) {
            result = new BigDecimal(100);
        } else if (mode == SubmitParamBean.MODE_JIAO) {
            result = new BigDecimal(10);
        } else if (mode == SubmitParamBean.MODE_FEN) {
            result = new BigDecimal(1);
        } else if (mode == SubmitParamBean.MODE_LI) {
            result = new BigDecimal(0.1);
        }
        BigDecimal bigDecimal = currentCapital.setScale(0,
            RoundingMode.HALF_UP);
        return bigDecimal.divide(result).intValue();
    }

    public void simulate(String date) throws IOException {
        this.mode = 3;
        this.maxHitCount = 4;
        this.bonus = 1.95;
        INIT_TIMES = 1;

        BigDecimal price1 = BigDecimal.ZERO;
        BigDecimal price2 = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;

        List<String> codeList = RandomCodeGenerator.getCode(50);
        List<String> subtractCodeList = RandomCodeGenerator
            .getSubtractCode(codeList);
        logger.info("[op:submit] server1 code. list={}",
            StringUtils.join(codeList, "|"));
        logger.info("[op:submit] server2 code. list={}",
            StringUtils.join(subtractCodeList, "|"));
        SubmitParamBean master = new SubmitParamBean();
        master.setMode(mode);
        master.setMain(true);
        master.setTime(INIT_TIMES);
        master.setNos(codeList);
        master.setPrice(getPrice(INIT_TIMES));

        SubmitParamBean slave = new SubmitParamBean();
        slave.setMode(mode);
        slave.setMain(false);
        slave.setTime(INIT_TIMES);
        slave.setNos(subtractCodeList);
        slave.setPrice(getPrice(INIT_TIMES));

        currentCapital = getPrice(INIT_TIMES);

        List<MockService.Item> historyList = MockService.getHistoryList(date);
        int idx = 0;
        while (true) {
            MockService.Item item = historyList.get(idx);
            idx++;
            if (idx >= historyList.size()) {
                mList.add(total);
                break;
            }
            CurrentIssueInfo currentIssueInfo = new CurrentIssueInfo();
            currentIssueInfo.setIssue(item.getNo());
            String currentIssue = currentIssueInfo.getIssue();
            master.setIssue(currentIssue);
            slave.setIssue(currentIssue);
            //todo time check protect
            //            boolean submit = requestCb868MasterService.submit(master);
            boolean submit = true;
            if (submit) {
                logger.info(
                    "[op:submit] server1 submit ok.issue={},main={},price={},times={}",
                    master.getIssue(), master.isMain(), master.getPrice(),
                    master.getTime());
                //                submit = requestCb868SlaveService.submit(slave);
                submit = true;
                if (submit) {
                    logger.info(
                        "[op:submit] server2 submit ok.issue={},main={},price={},times={}",
                        slave.getIssue(), slave.isMain(), slave.getPrice(),
                        slave.getTime());
                }
            }
            if (submit) {
                //开始轮询结果
                while (true) {
                    RecentIssueInfo recentIssueInfo = new RecentIssueInfo();
                    recentIssueInfo.setSuccess(1);
                    RecentIssueItem recentIssueItem1 = new RecentIssueItem();
                    recentIssueItem1.setIssue(item.getNo());
                    recentIssueItem1.setCode(item.getCode());
                    recentIssueItem1.setLotteryid(1);
                    recentIssueInfo
                        .setItems(Lists.newArrayList(recentIssueItem1));
                    if (recentIssueInfo != null) {
                        List<RecentIssueItem> items = recentIssueInfo
                            .getItems();
                        if (org.apache.commons.collections.CollectionUtils
                            .isNotEmpty(items)) {
                            RecentIssueItem recentIssueItem = items.get(0);
                            if (recentIssueItem.getIssue()
                                .equals(currentIssue)) {
                                List<String> retCodeList = recentIssueItem
                                    .getCodeList();
                                String suffix = retCodeList
                                    .get(retCodeList.size() - 2)
                                    + retCodeList.get(retCodeList.size() - 1);
                                if (codeList.contains(suffix)) {
                                    //master hit
                                    currentCapital = master.getPrice()
                                        .multiply(new BigDecimal(bonus));
                                    price1 = currentCapital
                                        .subtract(master.getPrice());
                                    price2 = BigDecimal.ZERO
                                        .subtract(slave.getPrice());
                                    int times = getTimes(currentCapital);
                                    master.setPrice(getPrice(times));
                                    master.setTime(times);
                                    if (master.isMain()) {
                                        hitCount++;
                                    } else {
                                        hitCount = 1;
                                        slave.setTime(INIT_TIMES);
                                        slave.setPrice(getPrice(INIT_TIMES));
                                        master.setMain(true);
                                        slave.setMain(false);
                                    }
                                } else {
                                    //slave hit
                                    currentCapital = slave.getPrice()
                                        .multiply(new BigDecimal(bonus));
                                    price2 = currentCapital
                                        .subtract(slave.getPrice());
                                    price1 = BigDecimal.ZERO
                                        .subtract(master.getPrice());
                                    int times = getTimes(currentCapital);
                                    slave.setTime(times);
                                    slave.setPrice(getPrice(times));
                                    if (slave.isMain()) {
                                        hitCount++;
                                    } else {
                                        hitCount = 1;
                                        master.setTime(INIT_TIMES);
                                        master.setPrice(getPrice(INIT_TIMES));
                                        master.setMain(false);
                                        slave.setMain(true);
                                    }
                                }
                                total = total.add(price1.add(price2));
                                logger.info(
                                    "[op:submit] sub hit {} times continuously,server1:{},server2:{},current:{},total:{}",
                                    hitCount,
                                    price1.setScale(3, RoundingMode.HALF_UP)
                                        .toPlainString(),
                                    price2.setScale(3, RoundingMode.HALF_UP)
                                        .toPlainString(),
                                    currentCapital
                                        .setScale(3, RoundingMode.HALF_UP)
                                        .toPlainString(),
                                    total.setScale(3, RoundingMode.HALF_UP)
                                        .toPlainString());
                                break;
                            } else {
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {}
                            }
                        }
                    }
                }
            }
            /*
             * if (hitCount >= maxHitCount) {
             * logger.info("[op:submit] hit {} times continuously", hitCount);
             * break; }
             */
            if (total.compareTo(new BigDecimal(100)) > 0) {
                mList.add(total);
                break;
            }
        }
    }

   static List<BigDecimal> mList = Lists.newArrayList();

    public static void main(String[] args) throws IOException {
        List<String> dateList = Lists.newArrayList();
        CoreServiceImpl coreService = new CoreServiceImpl();
        for (int i = 1; i <= 31; i++) {
            String suffix = i < 10 ? "0" + i : i + "";
            coreService.simulate("201712" + suffix);
            System.out.println("#############");
        }

        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal bigDecimal : mList) {
            System.out.println("record-->"+bigDecimal.setScale(3,RoundingMode.HALF_UP).toPlainString());
            result = result.add(bigDecimal);
        }
        System.out.println("result-->"+result.setScale(3,RoundingMode.HALF_UP));

    }
}
