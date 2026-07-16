package com.back.domain.member.member.service

import com.back.domain.member.member.entity.Member
import com.back.domain.member.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {

    fun count(): Long = memberRepository.count()

    fun join(username: String, password: String, nickname: String): Member =
        memberRepository.save(Member(username, password, nickname))
}