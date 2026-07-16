package com.back.domain.post.post.service;

import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.repository.MemberRepository;
import com.back.domain.post.post.entity.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class PostServiceTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostService postService;

    @Test
    @DisplayName("글 개수 조회")
    void t1() {
        long count = postService.count();

        assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("id로 글 조회")
    void t2() {
        Optional<Post> opPost = postService.findById(2);

        assertThat(opPost).isPresent();
        assertThat(opPost.get().getTitle()).isEqualTo("제목 2");
        assertThat(opPost.get().getContent()).isEqualTo("내용 2");
    }

    @Test
    @DisplayName("존재하지 않는 id로 글 조회시 빈 값")
    void t3() {
        Optional<Post> opPost = postService.findById(9999);

        assertThat(opPost).isEmpty();
    }

    @Test
    @DisplayName("글 작성")
    void t4() {
        Member author = memberRepository.findById(3).get();

        Post post = postService.write(author, "제목 new", "내용 new");

        assertThat(post.getId()).isGreaterThan(0);
        assertThat(post.getAuthor().getId()).isEqualTo(author.getId());
        assertThat(post.getTitle()).isEqualTo("제목 new");
        assertThat(post.getContent()).isEqualTo("내용 new");
        assertThat(postService.count()).isEqualTo(3);
    }

    @Test
    @DisplayName("글 수정")
    void t5() {
        Post post = postService.findById(1).get();

        postService.modify(post, "제목 수정", "내용 수정");

        assertThat(post.getTitle()).isEqualTo("제목 수정");
        assertThat(post.getContent()).isEqualTo("내용 수정");
    }
}
