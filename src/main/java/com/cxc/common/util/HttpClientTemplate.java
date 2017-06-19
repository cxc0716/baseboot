/**
 * @(#)HttpClientTemplate.java, 2015年9月8日.
 *
 * Copyright 2015 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.common.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Http工具类
 * <p>
 * 本工具类主要针对常用的GET和POST请求进行了封装并提供了方便的HTTP调用接口
 * <p>
 * 对于常见的GET和POST请求，只需要指定url、参数和编码方式即可方便调用，参见
 * <li>{@code executeGet(url)}
 * <li>{@code executeGet(url, parameters)}
 * <li>{@code executeGet(url, parameters, charset)}
 * <li>{@code executePost(url)}
 * <li>{@code executePost(url, parameters)}
 * <li>{@code executePost(url, parameters, charset)}
 */
@Component
public class HttpClientTemplate {

    private static final Logger logger = LoggerFactory
        .getLogger(HttpClientTemplate.class);

    private PoolingHttpClientConnectionManager connectionManager;

    private HttpClient httpClient;

    private int timeout = 50000;

    private String defaultCharset = "utf-8";

    @PostConstruct
    public void init() {
        httpClient = HttpClients.custom()
            .setConnectionManager(connectionManager).build();
    }

    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    /**
     * 使用默认编码执行执行无参数的POST请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @return
     * @throws IOException
     */
    public String executePost(String url) throws IOException {
        return executePost(url, null);
    }

    /**
     * 使用默认编码执行POST请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @param parameters
     *            参数
     * @return
     * @throws IOException
     */
    public String executePost(String url, List<NameValuePair> parameters)
        throws IOException {
        return executePost(url, parameters, null);
    }

    /**
     * 使用默认编码执行POST请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @param parameters
     *            参数
     * @param timeout
     *            超时时间
     * @return
     * @throws IOException
     */
    public String executePost(String url, List<NameValuePair> parameters,
        int timeout) throws IOException {
        return executePost(url, parameters, null, timeout);
    }

    /**
     * 执行POST请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @param parameters
     *            参数
     * @param charset
     *            编码，若为null则使用默认编码
     * @return
     * @throws IOException
     */
    public String executePost(String url, List<NameValuePair> parameters,
        String charset) throws IOException {
        return executePost(url, parameters, charset, this.timeout);
    }

    /**
     * 执行POST请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @param parameters
     *            参数
     * @param charset
     *            编码，若为null则使用默认编码
     * @param timeout
     *            超时时间
     * @return
     * @throws IOException
     */
    public String executePost(String url, List<NameValuePair> parameters,
        String charset, int timeout) throws IOException {
        charset = charset != null ? charset : defaultCharset;
        HttpPost postRequest = makePostRequest(url, parameters, charset,
            timeout);
        String result = requestAndParse(postRequest, charset);
        if (logger.isDebugEnabled()) {
            logger.debug(
                "[op:HttpClientTemplate] response from url={}, param={}, timeout={}, data={}",
                url, JSON.toJSON(parameters), timeout, result);
        }
        return result;
    }

    /**
     * 执行POST请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @param stringEntity
     *            请求内容体，默认为json格式
     * @param charset
     *            编码，若为null则使用默认编码
     * @return
     * @throws IOException
     */
    public String executePost(String url, String stringEntity, String charset)
        throws IOException {
        return executePost(url, stringEntity, charset, this.timeout);
    }

    /**
     * 执行POST请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @param stringEntity
     *            请求内容体，默认为json格式
     * @param charset
     *            编码，若为null则使用默认编码
     * @param contentType
     *            数据类型
     * @return
     * @throws IOException
     */
    public String executePost(String url, String stringEntity, String charset,
        ContentType contentType) throws IOException {
        charset = charset != null ? charset : defaultCharset;
        HttpPost postRequest = makePostRequest(url, stringEntity, charset,
            contentType);
        String result = requestAndParse(postRequest, charset);
        if (logger.isDebugEnabled()) {
            logger.debug(
                "[op:HttpClientTemplate] response from url={}, entity={}, data={}",
                url, stringEntity, result);
        }
        return result;
    }

    /**
     * 执行POST请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @param stringEntity
     *            请求内容体，默认为json格式
     * @param charset
     *            编码，若为null则使用默认编码
     * @param timeout
     *            超时时间
     * @return
     * @throws IOException
     */
    public String executePost(String url, String stringEntity, String charset,
        int timeout) throws IOException {
        charset = charset != null ? charset : defaultCharset;
        HttpPost postRequest = makePostRequest(url, stringEntity, charset,
            timeout);
        String result = requestAndParse(postRequest, charset);
        if (logger.isDebugEnabled()) {
            logger.debug(
                "[op:HttpClientTemplate] response from url={}, entity={}, data={}, timeout={}",
                url, stringEntity, result, timeout);
        }
        return result;
    }

    /**
     * 使用默认的编码执行无参数的GET请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @return
     * @throws IOException
     */
    public String executeGet(String url) throws IOException {
        return executeGet(url, null);
    }

    /**
     * 使用默认的编码执行GET请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @param parameters
     *            参数
     * @return
     * @throws IOException
     */
    public String executeGet(String url, List<NameValuePair> parameters)
        throws IOException {
        return executeGet(url, parameters, null);
    }

    /**
     * 执行GET请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @param parameters
     *            参数
     * @param charset
     *            编码，若为null则使用默认编码
     * @return
     * @throws IOException
     */
    public HttpResponse executeGetHttpResponse(String url,
        List<NameValuePair> parameters, String charset) throws IOException {
        charset = charset != null ? charset : defaultCharset;
        HttpGet getRequest = makeGetRequest(url, parameters, charset);
        if (logger.isDebugEnabled()) {
            logger.debug("[op:executeGet] getRequest={}",
                JSON.toJSON(getRequest));
        }

        return httpClient.execute(getRequest);
    }

    /**
     * 执行GET请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @param parameters
     *            参数
     * @param charset
     *            编码，若为null则使用默认编码
     * @return
     * @throws IOException
     */
    public String executeGet(String url, List<NameValuePair> parameters,
        String charset) throws IOException {
        charset = charset != null ? charset : defaultCharset;
        HttpGet getRequest = makeGetRequest(url, parameters, charset);
        if (logger.isDebugEnabled()) {
            logger.debug("[op:executeGet] getRequest={}",
                JSON.toJSON(getRequest));
        }
        String result = requestAndParse(getRequest, charset);
        if (logger.isDebugEnabled()) {
            logger.debug(
                "[op:executeGet] response from url={}, param={}, data={}", url,
                JSON.toJSON(parameters), result);
        }
        return result;
    }

    /**
     * 执行GET请求并将响应实体以字符串返回
     * 
     * @param url
     *            url
     * @param parameters
     *            参数
     * @param charset
     *            编码，若为null则使用默认编码
     * @param timeout
     *            超时时间
     * @return
     * @throws IOException
     */
    public String executeGet(String url, List<NameValuePair> parameters,
        String charset, int timeout) throws IOException {
        charset = charset != null ? charset : defaultCharset;
        HttpGet getRequest = makeGetRequest(url, parameters, charset, timeout);
        if (logger.isDebugEnabled()) {
            logger.debug("[op:executeGet] getRequest={}",
                JSON.toJSON(getRequest));
        }
        String result = requestAndParse(getRequest, charset);
        if (logger.isDebugEnabled()) {
            logger.debug(
                "[op:executeGet] response from url={}, param={}, data={}, timeout={}",
                url, JSON.toJSON(parameters), result, timeout);
        }
        return result;
    }

    /**
     * 根据给定的url、参数和编码方式构建一个GET请求
     * 
     * @param url
     *            url
     * @param parameters
     *            参数
     * @param charset
     *            编码，若为null则使用默认编码
     * @return
     */
    private HttpGet makeGetRequest(String url, List<NameValuePair> parameters,
        String charset) {
        return makeGetRequest(url, parameters, charset, this.timeout);
    }

    /**
     * 根据给定的url、参数和编码方式构建一个GET请求
     * 
     * @param url
     *            url
     * @param parameters
     *            参数
     * @param charset
     *            编码，若为null则使用默认编码
     * @param timeout
     *            超时时间
     * @return
     */
    private HttpGet makeGetRequest(String url, List<NameValuePair> parameters,
        String charset, int timeout) {
        String queryString = null;
        if (parameters != null && parameters.size() > 0) {
            charset = charset != null ? charset : defaultCharset;
            queryString = URLEncodedUtils.format(parameters, charset);
        }
        String requestUrl = url;
        if (queryString != null) {
            if (url.indexOf("?") == -1)
                requestUrl += "?" + queryString;
            else
                requestUrl += "&" + queryString;
        }
        if (logger.isDebugEnabled()) {
            logger.debug(
                "[op:makeGetRequest] making get request url={}, timeout={}",
                requestUrl);
        }
        return getHttpGet(requestUrl, timeout);
    }

    /**
     * 根据给定的url、参数和编码方式构建一个POST请求
     * 
     * @param url
     *            url
     * @param parameters
     *            参数
     * @param charset
     *            编码，若为null则使用默认编码
     * @param timeout
     *            超时时间
     * @return
     */
    private HttpPost makePostRequest(String url, List<NameValuePair> parameters,
        String charset, int timeout) throws IOException {
        HttpPost post = getHttpPost(url, timeout);
        if (parameters != null && parameters.size() > 0) {
            charset = charset != null ? charset : defaultCharset;
            UrlEncodedFormEntity urfe = new UrlEncodedFormEntity(parameters,
                charset);
            post.setEntity(urfe);
            if (logger.isDebugEnabled()) {
                logger.debug(
                    "[op:makePostRequest] making post request url={}, param={}, timeout={}",
                    url, JSON.toJSON(parameters), timeout);
            }
        }
        return post;
    }

    /**
     * 根据给定的url、参数和编码方式构建一个POST请求
     * 
     * @param url
     *            url
     * @param stringEntity
     *            请求内容体，默认为json格式
     * @param charset
     *            编码，若为null则使用默认编码
     * @param contentType
     *            数据类型
     * @return
     */
    private HttpPost makePostRequest(String url, String stringEntity,
        String charset, ContentType contentType) throws IOException {
        HttpPost post = getHttpPost(url);
        if (stringEntity != null) {
            charset = charset != null ? charset : defaultCharset;
            StringEntity se = new StringEntity(stringEntity,
                contentType.getMimeType(), charset);
            post.setEntity(se);
            if (logger.isDebugEnabled()) {
                logger.debug(
                    "[op:makePostRequest] making post request url={}, entity={}",
                    url, stringEntity);
            }
        }
        return post;
    }

    /**
     * 根据给定的url、参数和编码方式构建一个POST请求
     * 
     * @param url
     *            url
     * @param stringEntity
     *            请求内容体，默认为json格式
     * @param charset
     *            编码，若为null则使用默认编码
     * @param timeout
     *            超时时间
     * @return
     */
    private HttpPost makePostRequest(String url, String stringEntity,
        String charset, int timeout) throws IOException {
        HttpPost post = getHttpPost(url, timeout);
        if (stringEntity != null) {
            charset = charset != null ? charset : defaultCharset;
            StringEntity se = new StringEntity(stringEntity,
                ContentType.APPLICATION_JSON);
            post.setEntity(se);
            if (logger.isDebugEnabled()) {
                logger.debug(
                    "[op:makePostRequest] making post request url={}, entity={}, timeout={}",
                    url, stringEntity, timeout);
            }
        }
        return post;
    }

    /**
     * 生成POST请求，使用配置的参数
     * 
     * @param url
     * @return
     */
    private HttpPost getHttpPost(String url) {
        HttpPost postMethod = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
            .setSocketTimeout(timeout).setRedirectsEnabled(false).build();
        postMethod.setConfig(requestConfig);
        return postMethod;
    }

    /**
     * 生成POST请求，使用配置的参数
     * 
     * @param url
     * @param timeout
     * @return
     */
    private HttpPost getHttpPost(String url, int timeout) {
        if (timeout <= 0) {
            return getHttpPost(url);
        }

        HttpPost postMethod = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
            .setSocketTimeout(timeout).setRedirectsEnabled(false).build();
        postMethod.setConfig(requestConfig);
        return postMethod;
    }

    /**
     * 生成GET请求，使用配置的参数
     * 
     * @param url
     * @return
     */
    private HttpGet getHttpGet(String url) {
        HttpGet getMethod = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
            .setSocketTimeout(timeout).setRedirectsEnabled(false).build();
        getMethod.setConfig(requestConfig);
        return getMethod;
    }

    /**
     * 生成GET请求，使用配置的参数
     * 
     * @param url
     * @parem timeout
     * @return
     */
    private HttpGet getHttpGet(String url, int timeout) {
        if (timeout <= 0) {
            return getHttpGet(url);
        }
        HttpGet getMethod = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
            .setSocketTimeout(timeout).setRedirectsEnabled(false).build();
        getMethod.setConfig(requestConfig);
        return getMethod;
    }

    /**
     * 执行请求并获取响应
     * 
     * @param httpRequest
     *            HTTP请求
     * @param charset
     *            响应体编码
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    private String requestAndParse(HttpUriRequest httpRequest, String charset)
        throws IOException {
        HttpResponse httpResponse = httpClient.execute(httpRequest);
        return getResponseContentStr(httpResponse, charset);
    }

    /**
     * 使用指定编码将响应实体转为字符串
     * 
     * @param httpResponse
     *            响应
     * @return
     * @throws IOException
     */
    private String getResponseContentStr(HttpResponse httpResponse,
        String charset) throws IOException {
        HttpEntity entity = getResponseContentEntity(httpResponse);
        if (null == entity) {
            return null;
        }
        return EntityUtils.toString(entity, defaultCharset);
    }

    /**
     * 获取响应实体
     * 
     * @param httpResponse
     * @return
     * @throws IOException
     */
    private HttpEntity getResponseContentEntity(HttpResponse httpResponse)
        throws IOException {
        StatusLine statusLine = httpResponse.getStatusLine();
        if (null == statusLine) {
            EntityUtils.consumeQuietly(httpResponse.getEntity());
            throw new IOException("status not specified");
        }
        int statusCode = statusLine.getStatusCode();
        if (statusCode < 200 || statusCode > 299) {
            //            EntityUtils.consumeQuietly(httpResponse.getEntity());
            logger.error(
                "[op: getResponseContentEntity] http request fail, respond={}",
                JSON.toJSON(statusLine));
            throw new IOException("status code: " + statusCode);
        }
        Header[] allHeaders = httpResponse.getAllHeaders();
        System.out.println("print header begin ");
        for (Header allHeader: allHeaders) {
            System.out.println(allHeader.getName() + "###"
                + allHeader.getValue() + "###" + allHeader.getElements());
            System.out.println(">>>>>>");
        }
        System.out.println("print header end");
        return httpResponse.getEntity();
    }

    /**
     * 参数处理:无参数时，return一个空的map
     *
     * @param map
     * @return
     */
    public List<NameValuePair> parseToNameValuePairs(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return new ArrayList<NameValuePair>();
        }

        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry: map.entrySet()) {
            formParams
                .add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return formParams;
    }

    public void setConnectionManager(
        PoolingHttpClientConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    /**
     * 文件传输post请求
     * <p>
     * 执行无参数的POST请求传输文件，并将响应实体以字符串返回
     * 
     * @param url
     * @param filePath
     * @return
     */
    public String executeFilePost(String url, List<NameValuePair> parameters,
        String filePath) throws IOException {

        File file = new File(filePath);
        if (file.exists()) {
            return executeFilePost(url, parameters, file,null);
        } else {
            logger.error(
                "[op:executeFilePost] HttpClientTemplate.executeFilePost  file is not exist! filePath={} ",
                filePath);
        }
        return null;
    }

    /**
     * 文件传输post请求
     * <p>
     * 执行无参数的POST请求传输文件，并将响应实体以字符串返回
     *
     * @param url
     * @param
     * @return
     */
    @SuppressWarnings("Since15")
    public String executeFilePost(String url, List<NameValuePair> parameters,
        File file,String contentType) throws IOException {

        String result = "";

        if (file != null && file.exists()) {

            HttpPost httpPost = getHttpPost(url);
            FileBody bin = new FileBody(file,contentType);
            MultipartEntity reqEntity = new MultipartEntity(
                HttpMultipartMode.BROWSER_COMPATIBLE, null,
                Charset.forName("UTF-8"));
            reqEntity.addPart("filename",bin);

            if(parameters != null){
                for (NameValuePair parameter : parameters) {
                    StringBody stringBody = new StringBody(
                        parameter.getValue(),Charset.forName("UTF-8"));
                    reqEntity.addPart(parameter.getName(),stringBody);
                }
            }
            httpPost.setEntity(reqEntity);

            if (logger.isDebugEnabled()) {
                logger.debug(
                    "[op:executePost] making post request url={}, fileName={}, totalSpace={}",
                    url, file.getName(), file.getTotalSpace());
            }
            //文件传输
            HttpResponse response = httpClient.execute(httpPost);
            result = getResponseContentStr(response, null);

            if (logger.isDebugEnabled()) {
                logger.debug(
                    "[op:HttpClientTemplate] response from url={}, fileName={}, response={}",
                    url, file.getName(), result);
            }
        } else {
            logger.error(
                "[op:executeFilePost] HttpClientTemplate.executeFilePost  file is not exist!");
        }
        return result;
    }

    /**
     * 执行GET请求并将响应实体以HttpResponse返回
     * 
     * @param url
     *            url
     * @param parameters
     *            参数
     * @param charset
     *            编码，若为null则使用默认编码
     * @return
     * @throws IOException
     */
    public HttpResponse executeFileGet(String url,
        List<NameValuePair> parameters, String charset, int timeout)
        throws IOException {
        charset = charset != null ? charset : defaultCharset;
        HttpGet httpGet = makeGetRequest(url, parameters, charset, timeout);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        if (logger.isDebugEnabled()) {
            logger.debug("[op:executeFileGet] response from url={}, param={}",
                url, JSON.toJSON(parameters));
        }
        return httpResponse;
    }

    public Map<String, String> getBodyAndCookieByPost(String url,
        String stringEntity) throws IOException {
        Map<String, String> resultMap = Maps.newHashMap();
        String charset = "utf-8";
        HttpPost postRequest = makePostRequest(url, stringEntity, charset,
            timeout);
        HttpResponse httpResponse = httpClient.execute(postRequest);
        Header[] headers = httpResponse.getHeaders("Set-Cookie");
        if (headers != null) {
            for (Header header: headers) {
                String value = header.getValue();
                if (value.contains("webwx_data_ticket")) {
                    String[] split = value.split(";");
                    String[] tickets = split[0].split("=");
                    resultMap.put("dataTicket", tickets[1]);
                    break;
                }
            }
        }
        String respBody = getResponseContentStr(httpResponse, charset);
        resultMap.put("body", respBody);
        return resultMap;
    }
}
