package com.back.domain.post.post.repository

import com.back.domain.member.member.repository.MemberRepository
import com.back.domain.post.post.entity.Post
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class PostRepositoryTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val postRepository: PostRepository
) {

    @Test
    @DisplayName("2번 글 조회")
    fun t1() {
        val post2 = postRepository.findByIdOrNull(2)!!

        assertThat(post2.title).isEqualTo("제목 2")
        assertThat(post2.content).isEqualTo("내용 2")
    }

    @Test
    @DisplayName("글 생성")
    fun t2() {
        val memberUser1 = memberRepository.findByIdOrNull(3)!!
        val post = Post(memberUser1, "제목 new", "내용 new")
        assertThat(post.id).isEqualTo(0)

        postRepository.save(post)

        assertThat(post.id).isGreaterThan(0)
        assertThat(post.title).isEqualTo("제목 new")
        assertThat(post.content).isEqualTo("내용 new")
    }

    @Test
    @DisplayName("글 개수 조회")
    fun t3() {
        val count = postRepository.count()

        assertThat(count).isEqualTo(2)
    }
}