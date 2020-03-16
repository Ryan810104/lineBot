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



import com.example.bot.spring.echo.controller.CurrencyController;
import com.example.bot.spring.echo.entity.Page;
import com.example.bot.spring.echo.entity.Page1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
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
@Autowired
CurrencyController controller;

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: " + event);
//        HttpClientDownLoadService down = new HttpClientDownLoadService();
//        Page page=down.download("https://rate.bot.com.tw/xrt?Lang=zh-TW");
        String receive = event.getMessage().getText();
        String originalMessageText="";
        //啟動爬蟲
        StartUSD usd = new StartUSD();
        usd.setDownLoadService(new HttpClientDownLoadService());
        usd.setProcessService(new TWBank());

        //爬蟲網址
        String url = "https://rate.bot.com.tw/xrt?Lang=zh-TW";

        //儲存頁面資料
        Page page=usd.downLoadPage(url);
        usd.processPage(page);

        //檢查是否有接收的傳進來的訊息
        if(receive!= null && receive.trim().length() > 0){

            String str1 = "美金";
            String strsign = "$";
            String str2 = "英鎊";

            String str3 = "歐元";
            String stre = "eu";
            String time = "2020";
            String replyMessage = "";
            //收到對方的訊息改成小寫以及除掉空格
            String command = receive.toLowerCase().trim();




            //先檢查是否有包含時間
            if(command.contains(time)){
                //檢查是否包含"美金"
                if (command.contains(str1)) {

                    //擷取收到資料前面10個數字預設為日期
                    String ReciveTime=command.substring(0, 10);
                    String pattern = "^[0-9]{4}/[0-1]{1}[0-9]{1}/[0-1]{1}[0-9]{1}$";
                    //檢查是否符合日期格式，若不符合則回覆要求照格式
                    if(!ReciveTime.matches(pattern)){
                        replyMessage="請依照2020/03/04美金 格式詢問";
                    }else{
                        //若有符合格式則就去資料庫撈取歷史報價
                        Page1 page1 = controller.findByTime(ReciveTime);
                        if(page1==null){
                            replyMessage="請輸入正確日期";
                        }else {
                            replyMessage = command.substring(0, 10) + "報價為" + page1.getUsd().toString();
                        }
                    }
                }else if(command.contains(str2)){
                    //擷取收到資料前面10個數字預設為日期
                    String ReciveTime=command.substring(0, 10);
                    String pattern = "^[0-9]{4}/[0-1]{1}[0-9]{1}/[0-1]{1}[0-9]{1}$";
                    //檢查是否符合日期格式，若不符合則回覆要求照格式
                    if(!ReciveTime.matches(pattern)){
                        replyMessage="請依照2020/03/04美金 格式詢問";
                    }else{
                        //若有符合格式則就去資料庫撈取歷史報價
                        Page1 page1 = controller.findByTime(ReciveTime);
                        if(page1==null){
                            replyMessage="請輸入正確日期";
                        }else {
                            replyMessage = command.substring(0, 10) + "報價為" + page1.getGbp().toString();
                        }
                    }

                } else if(command.contains(str3)){
                    //擷取收到資料前面10個數字預設為日期
                    String ReciveTime=command.substring(0, 10);
                    String pattern = "^[0-9]{4}/[0-1]{1}[0-9]{1}/[0-1]{1}[0-9]{1}$";
                    //檢查是否符合日期格式，若不符合則回覆要求照格式
                    if(!ReciveTime.matches(pattern)){
                        replyMessage="請依照2020/03/04美金 格式詢問";
                    }else{
                        //若有符合格式則就去資料庫撈取歷史報價
                        Page1 page1 = controller.findByTime(ReciveTime);
                        if(page1==null){
                            replyMessage="請輸入正確日期";
                        }else {
                            replyMessage = command.substring(0, 10) + "報價為" + page1.getEur().toString();
                        }
                    }
                }

                else {
                    replyMessage = "請檢察輸入幣別";
                }
            }else {
                //沒包含時間則為去爬蟲撈及時報價
                //美金
                if (command.contains(str1) || command.contains(strsign)) {

                    replyMessage = page.getTime().toString() + "報價為" + page.getUsd().toString();

                    //英鎊
                } else if (command.contains(str2)) {
                    replyMessage = page.getTime().toString() + "報價為" + page.getGbp().toString();

                    //歐元
                } else if (command.contains(str3) || command.contains(stre)) {
                    replyMessage = page.getTime().toString() + "報價為" + page.getEur().toString();


                } else {
                    replyMessage = "Is this good to drink ?";
                }


            }
            return new TextMessage(replyMessage);
        }
        return null;
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
