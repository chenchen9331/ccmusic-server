package com.chenchen.ccmusic;

import com.chenchen.ccmusic.common.utils.IdWorker;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *  ccmusic 启动类
 * @author chenchen
 */
@SpringBootApplication
@MapperScan("com.chenchen.ccmusic.dao")
public class CcmusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcmusicApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
