package com.example.bot.spring.echo.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Page1 {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer number;
    private String content;
    private String time;
    private String JPY;
    private String USD;
    private String EUR;
    private String VND;
    private String CNY;
    private String HKD;
    private String SGD;
    private String GBP;
    private String THB;
    private String url;

}
