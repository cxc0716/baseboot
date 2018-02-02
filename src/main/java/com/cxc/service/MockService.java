package com.cxc.service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cxc.common.util.HttpClientTemplate;
import com.google.common.collect.Lists;

public class MockService {
    static String url = "http://kaijiang.500.com/static/public/ssc/xml/qihaoxml/%s.xml";

    static HttpClientTemplate httpClientTemplate = null;

    static {
        httpClientTemplate = new HttpClientTemplate();
        try {
            httpClientTemplate.init();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static List<Item> getHistoryList(String date) throws IOException {
        List<Item> list = Lists.newArrayList();
        // 解析books.xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            String newUrl = String.format(url, date);
            Document document = reader.read(newUrl);
            // 通过document对象获取根节点bookstore
            Element bookStore = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();
            // 遍历迭代器，获取根节点中的信息（书籍）
            while (it.hasNext()) {
                Element book = (Element) it.next();
                // 获取book的属性名以及 属性值
                List<Attribute> bookAttrs = book.attributes();
                Item item = new Item();
                for (Attribute attr: bookAttrs) {
                    if (attr.getName().equals("opencode")) {
                        item.setCode(attr.getValue());
                    }
                    if (attr.getName().equals("expect")) {
                        item.setNo(attr.getValue());
                    }
                }
                list.add(0, item);
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public static class Item {
        private String no;

        private String code;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
