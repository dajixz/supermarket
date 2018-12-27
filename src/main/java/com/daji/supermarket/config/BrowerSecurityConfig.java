package com.daji.supermarket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.xml.ws.Action;

/**
 * @author 大稽
 * @date2018/12/1223:06
 */
@Configuration
@EnableWebSecurity
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private AccessDeniedHandler myAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.headers().frameOptions().disable();

        http.formLogin()
                .loginPage("/view/login")
                .loginProcessingUrl("/authentication/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/view/login")
                .and()
            .authorizeRequests()
                .antMatchers("/view/login",
                        "/logout",
                        "/static/**",
                        "/js/*",
                        "/css/*",
                        "/images/*",
                        "/lib/**",
                        "/fonts/**",
                        "/favicon.ico").permitAll()
                .antMatchers("/view/**","/")
                .authenticated()
                .anyRequest()
                .access("@rbacService.hasPermission(request,authentication)")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler)
                .and()
                .csrf().disable();
    }
}
