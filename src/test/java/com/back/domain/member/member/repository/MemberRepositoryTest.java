package com.back.domain.member.member.repository;

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
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("3번 회원 조회")
    void t1() {
        Member member3 = memberRepository.findById(3).get();

        assertThat(member3.getUsername()).isEqualTo("user1");
        assertThat(member3.getNickname()).isEqualTo("유저1");
    }

    @Test
    @DisplayName("회원 생성")
    void t2() {
        Member member = new Member("user4", "1234", "유저4");
        assertThat(member.getId()).isEqualTo(0);

        memberRepository.save(member);

        assertThat(member.getId()).isGreaterThan(0);
        assertThat(member.getUsername()).isEqualTo("user4");
        assertThat(member.getPassword()).isEqualTo("1234");
        assertThat(member.getNickname()).isEqualTo("유저4");
    }

    @Test
    @DisplayName("회원 개수 조회")
    void t3() {
        long count = memberRepository.count();

        assertThat(count).isEqualTo(5);
    }
}
