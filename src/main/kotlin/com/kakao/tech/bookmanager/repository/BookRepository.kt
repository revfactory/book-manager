package com.kakao.tech.bookmanager.repository

import com.kakao.tech.bookmanager.domain.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book, Long> {
    fun findByTitleContainingIgnoreCase(title: String): List<Book>
}
