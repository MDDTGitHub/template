package com.madd.template.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientutil {

    public static String doGet(String url, Map<String,String>param){
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            //创建url
            URIBuilder builder = new URIBuilder(url);
            if(param != null){
                for(String key:param.keySet()){
                    builder.addParameter(key,param.get(key));
                }
            }
            URI uri = builder.build();
            //创建HttpGet
            HttpGet get = new HttpGet(uri);
            //执行请求
            response = httpClient.execute(get);
            //判断返回状态是否为200
            if(response.getStatusLine().getStatusCode() == 200){
                resultString = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(response != null){
                    response.close();
                }
                httpClient.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url){
        return doGet(url,null);
    }

    public static String doPostWithMap(String url, Map<String,String>param){
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            //创建HttpGet
            HttpPost post = new HttpPost(url);
            //创建参数列表
            if(param != null){
                List<NameValuePair> paramList = new ArrayList<>();
                for(String key:param.keySet()){
                    paramList.add(new BasicNameValuePair(key,param.get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                post.setEntity(entity);
            }
            //执行请求
            response = httpClient.execute(post);
            resultString = EntityUtils.toString(response.getEntity(),"UTF-8");
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doPost(String url){
        return doPostWithMap(url,null);
    }

    public static String doPostJson(String url,String json){
        //创建HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            //创建HttpGet
            HttpPost post = new HttpPost(url);
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            post.setEntity(entity);
            //执行请求
            response = httpClient.execute(post);
            resultString = EntityUtils.toString(response.getEntity(),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                response.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doPut(String url, Map<String,String>param){
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            //创建url
            URIBuilder builder = new URIBuilder(url);
            if(param != null){
                for(String key:param.keySet()){
                    builder.addParameter(key,param.get(key));
                }
            }
            URI uri = builder.build();
            //创建HttpPut
            HttpPut put = new HttpPut(uri);
            //执行请求
            response = httpClient.execute(put);
            //判断返回状态是否为200
            if(response.getStatusLine().getStatusCode() == 200){
                resultString = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(response != null){
                    response.close();
                }
                httpClient.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doDelete(String url){
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpDelete delete = new HttpDelete(url);
            //执行请求
            response = httpClient.execute(delete);
            resultString = EntityUtils.toString(response.getEntity(),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                response.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return resultString;
    }
}
