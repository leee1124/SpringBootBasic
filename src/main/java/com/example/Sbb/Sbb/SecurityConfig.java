package com.example.Sbb.Sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;


/**
 * @Configuration은 스프링의 환경설정 파일임을 나타내는 애너테이션
 * 스프링 시큐리티의 설정을 위해서 사용했음
 * @EnableWebSecurity는 모든 요청 URL이 스프링 시큐리티의 제어를 받도록 만드는 애너테이션
 * @EnableWebSecurity를 사용하면 내부적으로 SpringSecurityFilterChain이 동작하여 URL 필터가 적용됨
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //모든 인증되지 않은 요청을 허락 => 로그인 하지 않아도 모든 페이지에 접근할 수 있음
        http.authorizeHttpRequests().antMatchers("/**").permitAll()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
                ));
        return http.build();
    }
}
