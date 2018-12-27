package com.daji.supermarket.controller;

import com.daji.supermarket.entity.Goods;
import com.daji.supermarket.service.GoodsService;
import com.daji.supermarket.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 大稽
 * @date2018/12/1114:34
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/getGoods")
    public Page<Goods> getGoods(Integer page ,String goodsName){
        return goodsService.getGoodsByGoodsName(page,goodsName);
    }

    @GetMapping("/getGoodsList")
    public Page<Goods> getGoodsList(Integer page){
        return goodsService.getGoodsList(page);
    }

    @GetMapping("/getGoodsByCategory")
    public Page<Goods> getGoodsByCategory(Integer page,Integer category){
        return goodsService.getGoodsByCategory(page,category);
    }

    @PostMapping("/updateGoods")
    @ResponseBody
    public ResponseVo saveGoods(Goods goods) {
        Goods save = goodsService.saveGoods(goods);
        if(save!=null) {
            return new ResponseVo(200, save, "添加成功");
        }else{
            return new ResponseVo(403, null, "添加失败");
        }
    }
}
