package com.daji.supermarket.repository;

import com.daji.supermarket.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author 大稽
 * @date2018/12/1214:01
 */
public interface UserRepository extends JpaRepository<User,String>,JpaSpecificationExecutor<User> {
    Page<User> findAllByFlag(Pageable pageable, boolean flag);

    @Modifying
    @Query("update User set flag = false where userId=:userId")
    Integer deleteUserByUserId(@Param("userId")String userId);

    User getUserByUserIdAndFlag(String userId,boolean flag);

    User findUserByUserId(String userId);
}
