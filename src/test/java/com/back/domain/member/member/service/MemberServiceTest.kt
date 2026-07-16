package com.back.domain.member.member.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService
) {

    @Test
    @DisplayName("회원 개수 조회")
    fun t1() {
        val count = memberService.count()

        assertThat(count).isEqualTo(5)
    }

    @Test
    @DisplayName("회원 가입")
    fun t2() {
        val member = memberService.join("user4", "1234", "유저4")

        assertThat(member.id).isGreaterThan(0)
        assertThat(member.username).isEqualTo("user4")
        assertThat(member.password).isEqualTo("1234")
        assertThat(member.nickname).isEqualTo("유저4")
    }

    @Test
    @DisplayName("회원 가입 후 개수 증가")
    fun t3() {
        val beforeCount = memberService.count()

        memberService.join("user5", "1234", "유저5")

        val afterCount = memberService.count()

        assertThat(afterCount).isEqualTo(beforeCount + 1)
    }
}
