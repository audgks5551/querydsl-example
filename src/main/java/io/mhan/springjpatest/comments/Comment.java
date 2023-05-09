package io.mhan.springjpatest.comments;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder
@Table(name = "comments")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static Comment create(String content) {
        Comment comment = Comment.builder()
                .content(content)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return comment;
    }
}
