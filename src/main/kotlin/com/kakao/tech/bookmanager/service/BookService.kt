package com.kakao.tech.bookmanager.service

import com.kakao.tech.bookmanager.domain.Book
import com.kakao.tech.bookmanager.domain.BookCategoryBook
import com.kakao.tech.bookmanager.repository.BookCategoryRepository
import com.kakao.tech.bookmanager.repository.BookRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val bookCategoryRepository: BookCategoryRepository
) {

    fun addBook(book: Book): Book {
        return bookRepository.save(book)
    }

    fun getAllBooks(): List<Book> {
        return bookRepository.findAll()
    }

    fun searchBooks(searchKeyword: String): List<Book> {
        return bookRepository.findByTitleContainingIgnoreCase(searchKeyword)
    }

    fun getBookById(bookId: Long): Book {
        return bookRepository.findById(bookId)
            .orElseThrow { EntityNotFoundException("Book with id $bookId not found") }
    }

    fun updateBook(bookId: Long, book: Book): Book {
        val existingBook = bookRepository.findById(bookId)
            .orElseThrow { EntityNotFoundException("Book with id $bookId not found") }

        existingBook.isbn = book.isbn
        existingBook.title = book.title
        existingBook.author = book.author
        existingBook.introduce = book.introduce
        existingBook.tags = book.tags

        return bookRepository.save(existingBook)
    }

    fun deleteBook(bookId: Long) {
        bookRepository.deleteById(bookId)
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
}

