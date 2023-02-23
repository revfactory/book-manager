package com.kakao.tech.bookmanager.repository

import com.kakao.tech.bookmanager.domain.BookCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookCategoryRepository: JpaRepository<BookCategory, Long> {
}

