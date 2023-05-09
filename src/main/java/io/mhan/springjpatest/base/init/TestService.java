package io.mhan.springjpatest.base.init;

import io.mhan.springjpatest.posts.entity.Post;
import io.mhan.springjpatest.posts.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class TestService {
    private final PostRepository postRepository;

    public void testData() {
        Random random = new Random();
        for (int i=1; i<=50; i++) {
            Post post = Post.builder()
                    .title("title" + i)
                    .content("content" + (i + 1))
                    .views(random.nextLong(101))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            postRepository.save(post);
        }
    }
}
