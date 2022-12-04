package com.example.Sbb.Sbb;

import com.example.Sbb.Sbb.Service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserSecurityService userSecurityService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //모든 인증되지 않은 요청을 허락 => 로그인 하지 않아도 모든 페이지에 접근할 수 있음
        //X-Frame-Options 헤더값을 sameorigin으로 설정하여
        //frame에 포함된 페이지가 페이지를 제공하는 사이트와 동일한 경우에는 계속 사용할 수 있도록 함
        http.authorizeHttpRequests().antMatchers("/**").permitAll()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
                ))
                //스프링 시큐리티의 로그인 설정을 담당하는 부분
                //로그인 페이지의 url은 /user/login
                //로그인 성공하면 이동하는 디폴트 페이지는 루트 url(/)임
                .and()
                .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/");
        return http.build();
    }


    //passwordEncoder를 Spring Bean으로 등록하여 사용
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * 스프링 시큐리티의 인증 담당
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
