package io.mhan.springjpatest.repository;

import io.mhan.springjpatest.base.init.TestService;
import io.mhan.springjpatest.posts.repository.PostRepository;
import io.mhan.springjpatest.posts.repository.QueryDslPostRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class RepositoryTestBase {

    @Autowired
    PostRepository postRepository;

    @Autowired
    QueryDslPostRepository queryDslPostRepository;

    @Autowired
    TestService testService;

    @BeforeAll
    void beforeAll() {
        testService.testData();
    }

    @AfterAll
    void afterAll() {
        // 초기 데이터 생성 모두 삭제
        postRepository.deleteAll();
    }
}
