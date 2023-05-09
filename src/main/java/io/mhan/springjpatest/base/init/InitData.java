package io.mhan.springjpatest.base.init;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class InitData {

    @Bean
    @Profile("default") // default 모드에서만 실행
    public ApplicationRunner defaultInit(TestService testService) {
        return args -> testService.testData();
    }
}
