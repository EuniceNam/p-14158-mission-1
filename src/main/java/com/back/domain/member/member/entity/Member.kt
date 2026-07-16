package com.back.domain.member.member.entity

import com.back.global.jpa.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Member(username: String, password: String, nickname: String) : BaseEntity() {
    @Column(unique = true)
    var username: String = username
        protected set
    var password: String = password
        protected set
    var nickname: String = nickname
        protected set
}