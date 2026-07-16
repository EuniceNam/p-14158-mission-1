package com.back.domain.member.member.service;

import com.back.domain.member.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원 개수 조회")
    void t1() {
        long count = memberService.count();

        assertThat(count).isEqualTo(5);
    }

    @Test
    @DisplayName("회원 가입")
    void t2() {
        Member member = memberService.join("user4", "1234", "유저4");

        assertThat(member.getId()).isGreaterThan(0);
        assertThat(member.getUsername()).isEqualTo("user4");
        assertThat(member.getPassword()).isEqualTo("1234");
        assertThat(member.getNickname()).isEqualTo("유저4");
    }

    @Test
    @DisplayName("회원 가입 후 개수 증가")
    void t3() {
        long beforeCount = memberService.count();

        memberService.join("user5", "1234", "유저5");

        long afterCount = memberService.count();

        assertThat(afterCount).isEqualTo(beforeCount + 1);
    }
}
