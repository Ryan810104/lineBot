/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.example.bot.spring.echo;


import entity.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import service.IDownLoadService;
import service.IProcessService;
import service.Impl.HttpClientDownLoadService;
import service.Impl.TWBank;
import start.StartUSD;


@SpringBootApplication
@LineMessageHandler
public class EchoApplication {

    private final Logger log = LoggerFactory.getLogger(EchoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: " + event);
//        HttpClientDownLoadService down = new HttpClientDownLoadService();
//        Page page=down.download("https://rate.bot.com.tw/xrt?Lang=zh-TW");
        String receive = event.getMessage().getText();
        String originalMessageText="";
        StartUSD usd = new StartUSD();
        usd.setDownLoadService(new HttpClientDownLoadService());
        usd.setProcessService(new TWBank());
        String url = "https://rate.bot.com.tw/xrt?Lang=zh-TW";
        Page page=usd.downLoadPage(url);
        usd.processPage(page);
        if(receive!= null && receive.trim().length() > 0){
            String str1 = "美金";
            String strsign = "$";
            String str2 = "英鎊";

            String str3 = "歐元";
            String stre = "eu";
            String replyMessage = "";
            String command = receive.toLowerCase().trim();
            //if ("USD".equalsIgnoreCase(command)) {
            if (command.contains(str1)||command.contains(strsign)) {
                replyMessage = page.getTime().toString()+"報價為"+page.getUSD().toString();
            }else if(command.contains(str2)){
                replyMessage = page.getTime().toString()+"報價為"+page.getGBP().toString();
            }else if(command.contains(str3)||command.contains(stre)){
                replyMessage = page.getTime().toString()+"報價為"+page.getEUR().toString();
            }
            else{
                replyMessage = "is this good to drink ?";
            }
            return new TextMessage(replyMessage);
        }
        return null;



//    System.out.println(page.getUSD()+"");
//        if (event.getMessage().getText().toString() == "USD") {
  //        originalMessageText=page.getUSD().toString();
//        }else {
//            originalMessageText = event.getMessage().getText() + "哈哈哈";
//        }
//        System.out.print(originalMessageText);
//        return new TextMessage(originalMessageText);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
