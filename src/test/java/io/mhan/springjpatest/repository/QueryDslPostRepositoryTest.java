package io.mhan.springjpatest.repository;

import io.mhan.springjpatest.posts.entity.Post;
import io.mhan.springjpatest.posts.vo.Keyword;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static io.mhan.springjpatest.posts.vo.KeywordType.TITLE;
import static org.assertj.core.api.Assertions.assertThat;


public class QueryDslPostRepositoryTest extends RepositoryTestBase {

    @Test
    @DisplayName("모든 post 조회")
    void t1() {
        Sort sort = Sort.unsorted(); // 아무 정렬 조건을 주지 않는다

        List<Post> posts = queryDslPostRepository.findAll(null, sort);

        assertThat(posts.size()).isEqualTo(15);
    }

    @Test
    @DisplayName("오래된 순 조회")
    void t2() {
        Sort.Order order = Sort.Order.asc("id"); // id를 기준으로 asc 정렬

        Sort sort = Sort.by(order);

        List<Post> posts = queryDslPostRepository.findAll(null, sort);

        assertThat(posts).isSortedAccordingTo(Comparator.comparing(Post::getId));
    }

    @Test
    @DisplayName("최신순 조회")
    void t3() {
        Sort.Order order = Sort.Order.desc("createdAt"); // createdAt을 기준으로 desc 정렬

        Sort sort = Sort.by(order);

        List<Post> posts = queryDslPostRepository.findAll(null, sort);
        Collections.reverse(posts);

        assertThat(posts).isSortedAccordingTo(Comparator.comparing(Post::getCreatedAt));
    }

    @Test
    @DisplayName("아무 정렬 조건을 주지 않았을 때")
    void t4() {
        List<Post> posts = queryDslPostRepository.findAll(null, Sort.unsorted());

        assertThat(posts).isSortedAccordingTo(Comparator.comparing(Post::getId));
    }

    @Test
    @DisplayName("가장 조회수가 많은순으로 정렬")
    void t5() {
        Sort.Order order = Sort.Order.desc("views"); // views를 기준으로 desc 정렬

        Sort sort = Sort.by(order);
        List<Post> posts = queryDslPostRepository.findAll(null, sort); // post1, post2, post3

        Collections.reverse(posts); // post3, post2, post1

        assertThat(posts).isSortedAccordingTo(Comparator.comparing(Post::getViews));
    }

    @Test
    @DisplayName("keyword title값이 10이 포함된 post만 조회")
    void t6() {
        Keyword keyword = Keyword.builder()
                .type(TITLE)
                .value("10")
                .build();
        List<Post> posts = queryDslPostRepository.findAll(keyword, Sort.unsorted());

        assertThat(posts.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("keyword title과 content값이 10이 포함된 post만 조회")
    void t7() {
        Keyword keyword = Keyword.builder()
                .type(TITLE)
                .value("10")
                .build();
        List<Post> posts = queryDslPostRepository.findAll(keyword, Sort.unsorted());

        assertThat(posts.size()).isEqualTo(2);
    }

}
