package com.kakao.tech.bookmanager.domain

import jakarta.persistence.*

@Entity
@Table(name = "book_category_book")
class BookCategoryBook(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "book_id")
    var book: Book,

    @ManyToOne
    @JoinColumn(name = "category_id")
    var bookCategory: BookCategory
)
