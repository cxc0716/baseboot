package com.cxc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxc.common.constant.CommonConstant;
import com.cxc.common.exception.WeixinServiceException;
import com.cxc.domain.Content;
import com.cxc.model.QrcodeInfo;
import com.cxc.service.ContentService;
import com.cxc.service.WeixinService;
import com.cxc.vo.AjaxResult;
import com.cxc.vo.UserSimpleInfo;

@RestController
@RequestMapping("/wx")
public class WeixinController extends BaseController {

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private ContentService contentService;

    @Value("${msg.send.channel}")
    private Integer channel;

    @RequestMapping("/qrcode")
    public AjaxResult getQrcode(Integer id, HttpServletRequest request) {
        try {
            QrcodeInfo qrcodeInfo = weixinService.getQrcodeInfo();
            qrcodeInfo.setContentId(id);
            qrcodeInfo.setTimeSec(CommonConstant.VALID_TIME_SEC);
            qrcodeInfo.setCurrentTime(System.currentTimeMillis());
            return initSuccessResult(qrcodeInfo);
        } catch (Exception e) {
            logger.error("[qrcode]", e);
            return initFailureResult(e.getMessage());
        }
    }

    @RequestMapping("request")
    public AjaxResult waitAndSendMsg(String uuid, Integer id, Long time,
        HttpServletRequest request) {
        try {
            if (id == null || StringUtils.isBlank(uuid) || time == null) {
                return initFailureResult("param is null");
            }
            logger.debug("[op:waitAndSendMsg] uuid:{},contentId:{}", uuid, id);
            Content content = contentService.getById(id);
            if (content == null) {
                return initFailureResult("id not exsist");
            }
            UserSimpleInfo loginUser = getLoginUser(request);
            content.setUserNote(loginUser.getNote());
            logger.info("config channel is {}", channel);
            if (channel == 0) {
                Boolean ret = weixinService.sendMsg(uuid, content);
            } else {
                weixinService.sendMsg2(uuid, content);
            }
            return initSuccessResult("发送成功");
        } catch (WeixinServiceException e) {
            logger.error("[op:waitAndSendMsg1] msg:{}", e.getMessage());
            return initFailureResult(e.getMessage());
        } catch (Exception e) {
            logger.error("[op:waitAndSendMsg2]", e);
            return initFailureResult("发送信息失败");
        }
    }
}
