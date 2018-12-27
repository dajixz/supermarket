package com.daji.supermarket.repository;

import com.daji.supermarket.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


/**
 * @author 大稽
 * @date2018/12/1114:47
 */
public interface GoodsRepository extends JpaRepository<Goods,Integer>,JpaSpecificationExecutor<Goods> {

    Page<Goods> findAllByFlag(Pageable pageable,boolean flag);

    Page<Goods> findAllByGoodsNameLikeAndFlag(Pageable pageable,String goodsName, boolean flag);
    @Modifying
    @Query("update Goods set flag = false where id=:id")
    Integer deleteGoodsById(@Param("id")int id);

    Page<Goods> findAllByCategoryAndFlag(Pageable pageable,Integer category,boolean flag);
}
