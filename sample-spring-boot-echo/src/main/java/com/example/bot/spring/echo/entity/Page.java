package com.example.bot.spring.echo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="CURRENCY")
public class Page {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer number;
    private String content;
    private String time;
    private String jpy;
    private String usd;
    private String eur;
    private String vnd;
    private String cny;
    private String hkd;
    private String sgd;
    private String gbp;
    private String thb;
    private String url;


}
