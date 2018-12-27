package com.daji.supermarket.service.impl;

import com.daji.supermarket.entity.Role;
import com.daji.supermarket.entity.User;
import com.daji.supermarket.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 大稽
 * @date2018/12/1223:23
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户：{}",username);
        User user = userRepository.findUserByUserId(username);
        String roleStr="";
        if(user!=null){
            for(Role role:user.getRoleList()){
                roleStr+=role.getRoleName()+",";
            }
            List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(roleStr);
            user.setAuthorities(grantedAuthorityList);
        }else{
            throw new UsernameNotFoundException("用户名或密码错误！");
        }
        return user;
    }
}
