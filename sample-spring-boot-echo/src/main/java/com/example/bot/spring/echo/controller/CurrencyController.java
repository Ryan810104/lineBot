package com.example.bot.spring.echo.controller;



import com.example.bot.spring.echo.dao.CurrencyDAO;
import com.example.bot.spring.echo.entity.Page1;
import com.example.bot.spring.echo.service.Service;
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
    public Page1 findByTime(String Time){
        Page1 page1=currencyService.queryByTime(Time);
      return page1;
    }


    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String sayHello(){
        return "hello";
    }
}
