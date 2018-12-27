package com.daji.supermarket.service;

import com.daji.supermarket.entity.Goods;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 大稽
 * @date2018/12/1114:46
 */
public interface GoodsService {
    Goods saveGoods(Goods goods);
    Page<Goods> getGoodsList(Integer page);
    Goods getGoodsById(Integer id);
    Integer deleteGoodsById(Integer id);
    Page<Goods> getGoodsByGoodsName(Integer page,String goodsName);

    Page<Goods> getGoodsByCategory(Integer page, Integer category);
}
