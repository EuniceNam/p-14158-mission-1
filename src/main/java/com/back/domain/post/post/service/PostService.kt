package com.back.domain.post.post.service

import com.back.domain.member.member.entity.Member
import com.back.domain.post.post.entity.Post
import com.back.domain.post.post.repository.PostRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class PostService(private val postRepository: PostRepository) {

    fun count(): Long = postRepository.count()

    fun findById(id: Int): Post? = postRepository.findById(id).getOrNull()

    fun modify(post: Post, title: String, content: String) {
        post.title = title
        post.content = content
    }

    fun write(author: Member, title: String, content: String): Post =
        Post(author, title, content).also { postRepository.save(it) }
}