package service.Impl;

import Dao.CurrencyRepository;
import entity.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CurrencyService {
    @Autowired
    CurrencyRepository currencydao;

    public Page queryByTime(String Time){
        return currencydao.findByTime(Time);
    }
}
