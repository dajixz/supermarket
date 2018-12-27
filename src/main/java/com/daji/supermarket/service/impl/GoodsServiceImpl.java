package com.daji.supermarket.service.impl;

import com.daji.supermarket.entity.Goods;
import com.daji.supermarket.repository.GoodsRepository;
import com.daji.supermarket.service.GoodsService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 大稽
 * @date2018/12/1114:46
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Goods saveGoods(Goods goods) {
        Goods save = goodsRepository.save(goods);
        return save;
    }

    @Override
    public Page<Goods> getGoodsList(Integer page) {
        Pageable pageable =  PageRequest.of(page-1, 5);
        return goodsRepository.findAllByFlag(pageable,true);
    }

    @Override
    public Goods getGoodsById(Integer id) {
        return goodsRepository.getOne(id);
    }

    @Override
    @Transactional
    public Integer deleteGoodsById(Integer id) {
        return goodsRepository.deleteGoodsById(id);
    }

    @Override
    public Page<Goods> getGoodsByGoodsName(Integer page,String goodsName) {
        Pageable pageable =  PageRequest.of(page-1, 5);
        return goodsRepository.findAllByGoodsNameLikeAndFlag(pageable,"%"+goodsName+"%",true);
    }

    @Override
    public Page<Goods> getGoodsByCategory(Integer page, Integer category) {
        Pageable pageable =  PageRequest.of(page-1, 5);
        return goodsRepository.findAllByCategoryAndFlag(pageable,category,true);
    }
}
