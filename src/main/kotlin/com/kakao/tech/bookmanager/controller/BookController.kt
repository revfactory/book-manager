package com.kakao.tech.bookmanager.controller

import com.kakao.tech.bookmanager.domain.Book
import com.kakao.tech.bookmanager.service.BookService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(private val bookService: BookService) {

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    fun addBook(@RequestBody book: Book): Book {
        return bookService.addBook(book)
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    fun getAllBooks(): List<Book> {
        return bookService.getAllBooks()
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ROLE_USER')")
    fun searchBooks(@RequestParam searchKeyword: String): List<Book> {
        return bookService.searchBooks(searchKeyword)
    }

    @GetMapping("/{bookId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    fun getBookById(@PathVariable bookId: Long): Book {
        return bookService.getBookById(bookId)
    }

    @PutMapping("/{bookId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    fun updateBook(@PathVariable bookId: Long, @RequestBody book: Book): Book {
        return bookService.updateBook(bookId, book)
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    fun deleteBook(@PathVariable bookId: Long) {
        bookService.deleteBook(bookId)
    }
}

