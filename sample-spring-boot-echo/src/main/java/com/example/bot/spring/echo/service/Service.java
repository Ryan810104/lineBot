package com.example.bot.spring.echo.service;



import com.example.bot.spring.echo.dao.CurrencyDAO;
import com.example.bot.spring.echo.entity.Page1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class Service {

    @Autowired
    CurrencyDAO currencydao;

    public Page1 queryByTime(String Time){
        return currencydao.findByTime(Time);
    }

}
