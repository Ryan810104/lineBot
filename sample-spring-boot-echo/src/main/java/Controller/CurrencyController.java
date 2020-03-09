package Controller;

import Dao.CurrencyRepository;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.Impl.CurrencyService;


@RestController
@Transactional
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @Autowired
    CurrencyRepository dao;


    @RequestMapping(value = "/find")
    public Page findById(String Time){
        Page page=currencyService.queryByTime("2020/3/4");
        System.out.println(page);
        return page;
    }


    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String sayHello(){
        return "hello";
    }
}
