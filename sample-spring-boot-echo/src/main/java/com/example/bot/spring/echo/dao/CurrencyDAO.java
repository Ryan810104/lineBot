package com.example.bot.spring.echo.dao;

import com.example.bot.spring.echo.entity.Page1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyDAO extends JpaRepository <Page1,Integer>{
    public List<Page1> findByTime(String time);
}
