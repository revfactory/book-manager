package com.kakao.tech.bookmanager.domain

import jakarta.persistence.*

@Entity
@Table(name = "book")
class Book(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var isbn: String,

    var title: String,

    var author: String,

    var introduce: String,

    @ElementCollection
    var tags: MutableList<String> = mutableListOf(),

    @ManyToMany(mappedBy = "books")
    var categories: MutableSet<BookCategory> = mutableSetOf()
)
