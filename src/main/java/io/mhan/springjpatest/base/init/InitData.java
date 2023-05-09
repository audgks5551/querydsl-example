package io.mhan.springjpatest.base.init;

import io.mhan.springjpatest.posts.entity.Post;
import io.mhan.springjpatest.posts.repository.PostRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@Configuration
public class InitData {

    @Bean
    @Profile("default") // default 모드에서만 실행
    public ApplicationRunner defaultInit(TestService testService) {
        return args -> testService.testData();
    }
}
