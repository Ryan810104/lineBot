package Dao;

import entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Page,Integer> {
    public Page findByTime(String Time);
}
