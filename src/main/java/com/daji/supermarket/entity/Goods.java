package com.daji.supermarket.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 大稽
 * @date2018/12/1010:32
 */
//货品
@Entity
@Data
public class Goods {

    @Id
    @GeneratedValue
    private int Id;
    private String goodsId;
    private String goodsName;
    private double price;
    private int num;


    //生产日期
    @Temporal(value = TemporalType.DATE)
    private Date produceTime;
    //
    @Temporal(value = TemporalType.DATE)
    private Date dueTime;
    private String producer;
    private int category;

    private boolean flag=true;

}
