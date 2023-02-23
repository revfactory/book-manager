package com.kakao.tech.bookmanager.domain

import jakarta.persistence.*

@Entity
@Table(name = "book_category")
class BookCategory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var categoryName: String,

    @ManyToMany
    @JoinTable(name = "book_category_book",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "book_id")])
    var books: MutableSet<Book> = mutableSetOf()
)
