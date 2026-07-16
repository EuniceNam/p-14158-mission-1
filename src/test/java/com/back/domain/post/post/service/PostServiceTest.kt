package com.back.domain.post.post.service

import com.back.domain.member.member.repository.MemberRepository
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
class PostServiceTest @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val postService: PostService
) {

    @Test
    @DisplayName("글 개수 조회")
    fun t1() {
        val count = postService.count()

        assertThat(count).isEqualTo(2)
    }

    @Test
    @DisplayName("id로 글 조회")
    fun t2() {
        val post = postService.findById(2)!!

        assertThat(post.title).isEqualTo("제목 2")
        assertThat(post.content).isEqualTo("내용 2")
    }

    @Test
    @DisplayName("존재하지 않는 id로 글 조회시 빈 값")
    fun t3() {
        val post = postService.findById(9999)

        assertThat(post).isNull()
    }

    @Test
    @DisplayName("글 작성")
    fun t4() {
        val author = memberRepository.findByIdOrNull(3)!!

        val post = postService.write(author, "제목 new", "내용 new")

        assertThat(post.id).isGreaterThan(0)
        assertThat(post.author.id).isEqualTo(author.id)
        assertThat(post.title).isEqualTo("제목 new")
        assertThat(post.content).isEqualTo("내용 new")
        assertThat(postService.count()).isEqualTo(3)
    }

    @Test
    @DisplayName("글 수정")
    fun t5() {
        val post = postService.findById(1)!!

        postService.modify(post, "제목 수정", "내용 수정")

        assertThat(post.title).isEqualTo("제목 수정")
        assertThat(post.content).isEqualTo("내용 수정")
    }
}
