package com.kakao.tech.bookmanager.service

import com.kakao.tech.bookmanager.domain.Book
import com.kakao.tech.bookmanager.domain.BookCategory
import com.kakao.tech.bookmanager.domain.BookCategoryBook
import com.kakao.tech.bookmanager.repository.BookCategoryRepository
import com.kakao.tech.bookmanager.repository.BookRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class BookCategoryService(
    private val bookCategoryRepository: BookCategoryRepository,
    private val bookRepository: BookRepository
) {

    fun addBookCategory(bookCategory: BookCategory): BookCategory {
        return bookCategoryRepository.save(bookCategory)
    }

    fun getAllBookCategories(): List<BookCategory> {
        return bookCategoryRepository.findAll()
    }

    fun getBookCategoryById(categoryId: Long): BookCategory {
        return bookCategoryRepository.findById(categoryId)
            .orElseThrow { EntityNotFoundException("Book category with id $categoryId not found") }
    }

    fun updateBookCategory(categoryId: Long, bookCategory: BookCategory): BookCategory {
        val existingBookCategory = bookCategoryRepository.findById(categoryId)
            .orElseThrow { EntityNotFoundException("Book category with id $categoryId not found") }

        existingBookCategory.categoryName = bookCategory.categoryName

        return bookCategoryRepository.save(existingBookCategory)
    }

    fun deleteBookCategory(categoryId: Long) {
        bookCategoryRepository.deleteById(categoryId)
    }

    fun getBooksByCategoryId(categoryId: Long): List<Book> {
        val bookCategory = bookCategoryRepository.findById(categoryId)
            .orElseThrow { EntityNotFoundException("Book category with id $categoryId not found") }

        return bookCategory.books.toList()
    }

    fun removeBookFromCategory(categoryId: Long, bookId: Long) {
        val bookCategory = bookCategoryRepository.findById(categoryId)
            .orElseThrow { EntityNotFoundException("Book category with id $categoryId not found") }

        val book = bookRepository.findById(bookId)
            .orElseThrow { EntityNotFoundException("Book with id $bookId not found") }

        bookCategory.books.remove(book)
        book.categories.remove(bookCategory)

        bookCategoryRepository.save(bookCategory)
    }

    fun addBookToCategory(categoryId: Long, bookId: Long): BookCategoryBook {
        val bookCategory = bookCategoryRepository.findById(categoryId)
            .orElseThrow { EntityNotFoundException("Book category with id $categoryId not found") }

        val book = bookRepository.findById(bookId)
            .orElseThrow { EntityNotFoundException("Book with id $bookId not found") }

        bookCategory.books.add(book)
        book.categories.add(bookCategory)

        bookCategoryRepository.save(bookCategory)

        return BookCategoryBook(book = book, bookCategory = bookCategory)
    }
}

