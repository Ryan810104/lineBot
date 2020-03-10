package service.Impl;

import com.example.bot.spring.echo.entity.Page;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import service.IProcessService;
//實作頁面的解析，抓到頁面中USD的現金賣出價格
public class TWBank implements IProcessService {
    @Override
    public void process(Page page) {

    String content = page.getContent();
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode rootNode =htmlCleaner.clean(content);
        try {
            //獲取到網頁時間
            Object[] evaluateXPath= rootNode.evaluateXPath("//*[@id=\"h1_small_id\"]");
            if(evaluateXPath.length>0){
                TagNode node = (TagNode) evaluateXPath[0];
                System.out.println("Time "+node.getText().toString());
                page.setTime(node.getText().toString());
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }

        try {
            //獲取到USD的現金賣出價
           Object[] evaluateXPath= rootNode.evaluateXPath("/body/div[1]/main/div[4]/table/tbody/tr[1]/td[3]");
           if(evaluateXPath.length>0){
               TagNode node = (TagNode) evaluateXPath[0];
               System.out.println("USD"+node.getText().toString());
            page.setUsd(node.getText().toString());
           }
        } catch (XPatherException e) {
            e.printStackTrace();
        }

        try {
            //獲取到JPY的現金賣出價
            Object[] evaluateXPath= rootNode.evaluateXPath("/body/div[1]/main/div[4]/table/tbody/tr[8]/td[3]");
            if(evaluateXPath.length>0){
                TagNode node = (TagNode) evaluateXPath[0];
                System.out.println("JPY"+node.getText().toString());
                page.setJpy(node.getText().toString());
                //System.out.println(node.getText().toString());
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }

        try {
            //獲取到HKD的現金賣出價
            Object[] evaluateXPath= rootNode.evaluateXPath("/body/div[1]/main/div[4]/table/tbody/tr[2]/td[3]");
            if(evaluateXPath.length>0){
                TagNode node = (TagNode) evaluateXPath[0];
                System.out.println("HKD"+node.getText().toString());
                page.setHkd(node.getText().toString());
                //System.out.println(node.getText().toString());
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }


        try {
            //獲取到EU的現金賣出價
            Object[] evaluateXPath= rootNode.evaluateXPath("/body/div[1]/main/div[4]/table/tbody/tr[15]/td[3]");
            if(evaluateXPath.length>0){
                TagNode node = (TagNode) evaluateXPath[0];
                System.out.println("EUR"+node.getText().toString());
                page.setEur(node.getText().toString());
                //System.out.println(node.getText().toString());
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }

        try {
            //獲取到VND的現金賣出價
            Object[] evaluateXPath= rootNode.evaluateXPath("/body/div[1]/main/div[4]/table/tbody/tr[17]/td[3]");
            if(evaluateXPath.length>0){
                TagNode node = (TagNode) evaluateXPath[0];
                System.out.println("VND"+node.getText().toString());
                page.setVnd(node.getText().toString());
                //System.out.println(node.getText().toString());
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }

        try {
            //獲取到RMB的現金賣出價
            Object[] evaluateXPath= rootNode.evaluateXPath("/body/div[1]/main/div[4]/table/tbody/tr[19]/td[3]");
            if(evaluateXPath.length>0){
                TagNode node = (TagNode) evaluateXPath[0];
                System.out.println("RMB"+node.getText().toString());
                page.setCny(node.getText().toString());
                //System.out.println(node.getText().toString());
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }

        try {
            //獲取到SGD的現金賣出價
            Object[] evaluateXPath= rootNode.evaluateXPath("/body/div[1]/main/div[4]/table/tbody/tr[6]/td[3]");
            if(evaluateXPath.length>0){
                TagNode node = (TagNode) evaluateXPath[0];
                System.out.println("SGD"+node.getText().toString());
                page.setSgd(node.getText().toString());
                //System.out.println(node.getText().toString());
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }

        try {
            //獲取到GBP的現金賣出價
            Object[] evaluateXPath= rootNode.evaluateXPath("/body/div[1]/main/div[4]/table/tbody/tr[3]/td[3]");
            if(evaluateXPath.length>0){
                TagNode node = (TagNode) evaluateXPath[0];
                System.out.println("GBP"+node.getText().toString());
                page.setGbp(node.getText().toString());
                //System.out.println(node.getText().toString());
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }

        try {
            //獲取到THB的現金賣出價
            Object[] evaluateXPath= rootNode.evaluateXPath("/body/div[1]/main/div[4]/table/tbody/tr[12]/td[3]");
            if(evaluateXPath.length>0){
                TagNode node = (TagNode) evaluateXPath[0];
                System.out.println("THB "+node.getText().toString());
                page.setThb(node.getText().toString());
                //System.out.println(node.getText().toString());
            }
        } catch (XPatherException e) {
            e.printStackTrace();
        }
    }
}
