package entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Page {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer number;
    private String content;
    private String Time;
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
