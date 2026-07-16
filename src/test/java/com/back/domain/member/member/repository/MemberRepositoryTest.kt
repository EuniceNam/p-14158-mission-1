package com.back.domain.member.member.repository

import com.back.domain.member.member.entity.Member
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
class MemberRepositoryTest @Autowired constructor(
    private val memberRepository: MemberRepository
) {

    @Test
    @DisplayName("3번 회원 조회")
    fun t1() {
        val member3 = memberRepository.findByIdOrNull(3)!!

        assertThat(member3.username).isEqualTo("user1")
        assertThat(member3.nickname).isEqualTo("유저1")
    }

    @Test
    @DisplayName("회원 생성")
    fun t2() {
        val member = Member("user4", "1234", "유저4")
        assertThat(member.id).isEqualTo(0)

        memberRepository.save(member)

        assertThat(member.id).isGreaterThan(0)
        assertThat(member.username).isEqualTo("user4")
        assertThat(member.password).isEqualTo("1234")
        assertThat(member.nickname).isEqualTo("유저4")
    }

    @Test
    @DisplayName("회원 개수 조회")
    fun t3() {
        val count = memberRepository.count()

        assertThat(count).isEqualTo(5)
    }
}
