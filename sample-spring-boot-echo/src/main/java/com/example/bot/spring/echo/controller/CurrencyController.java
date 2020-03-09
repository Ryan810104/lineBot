package com.example.bot.spring.echo.controller;



import com.example.bot.spring.echo.dao.CurrencyDAO;
import com.example.bot.spring.echo.entity.Page1;
import com.example.bot.spring.echo.service.Service;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
public class CurrencyController {

    @Autowired
    Service currencyService;

    @Autowired
    CurrencyDAO dao;


    @RequestMapping(value = "/find")
    public List<Page1> findById(String Time){
        List<Page1> page=currencyService.queryByTime("2020/3/4");
        System.out.println(page);
        return page;
    }


    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String sayHello(){
        return "hello";
    }

}
