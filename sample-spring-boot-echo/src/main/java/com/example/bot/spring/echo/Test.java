package com.example.bot.spring.echo;

import com.google.common.base.Utf8;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Test {
    public static String Crawler(String url){
        HttpClientBuilder builder = HttpClients.custom();
        CloseableHttpClient client = builder.build();
        HttpGet request = new HttpGet(url);
        request.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
        request.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
        String content=null;
        try {
            CloseableHttpResponse response =client.execute(request);

            HttpEntity entity=response.getEntity();
            content= EntityUtils.toString(entity,"UTF-8");
             //content = new String(content.getBytes("ISO-8859-1"),"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
