package com.spring.restful.config;

import com.spring.restful.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

   @Bean
   public JwtTokenProvider tokenProvider() {
       return new JwtTokenProvider();
   }

}
