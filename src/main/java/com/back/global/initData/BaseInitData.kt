package com.back.global.initData

import com.back.domain.member.member.service.MemberService
import com.back.domain.post.post.service.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.transaction.annotation.Transactional

@Configuration
class BaseInitData(private val memberService: MemberService, private val postService: PostService) {
    @Autowired
    @Lazy
    private lateinit var self: BaseInitData

    @Bean
    fun baseInitDataApplicationRunner(): ApplicationRunner = ApplicationRunner { self.work1() }

    @Transactional
    fun work1() {
        if (memberService.count() > 0) return
        val memberSystem = memberService.join("system", "1234", "시스템")
        val memberAdmin = memberService.join("admin", "1234", "관리자")
        val memberUser1 = memberService.join("user1", "1234", "유저1")
        val memberUser2 = memberService.join("user2", "1234", "유저2")
        val memberUser3 = memberService.join("user3", "1234", "유저3")

        if (postService.count() > 0) return

        val post1 = postService.write(memberUser1, "제목 1", "내용 1")
        val post2 = postService.write(memberUser2, "제목 2", "내용 2")

        println("기본 데이터가 초기화되었습니다.")
    }
}