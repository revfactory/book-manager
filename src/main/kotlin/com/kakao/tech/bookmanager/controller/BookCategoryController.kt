package com.kakao.tech.bookmanager.controller

import com.kakao.tech.bookmanager.domain.Book
import com.kakao.tech.bookmanager.domain.BookCategory
import com.kakao.tech.bookmanager.domain.BookCategoryBook
import com.kakao.tech.bookmanager.service.BookCategoryService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class BookCategoryController(
    private val bookCategoryService: BookCategoryService
) {

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    fun addBookCategory(@RequestBody bookCategory: BookCategory): BookCategory {
        return bookCategoryService.addBookCategory(bookCategory)
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    fun getAllBookCategories(): List<BookCategory> {
        return bookCategoryService.getAllBookCategories()
    }

    @GetMapping("/{categoryId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    fun getBookCategoryById(@PathVariable categoryId: Long): BookCategory {
        return bookCategoryService.getBookCategoryById(categoryId)
    }

    @PutMapping("/{categoryId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    fun updateBookCategory(@PathVariable categoryId: Long, @RequestBody bookCategory: BookCategory): BookCategory {
        return bookCategoryService.updateBookCategory(categoryId, bookCategory)
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    fun deleteBookCategory(@PathVariable categoryId: Long) {
        bookCategoryService.deleteBookCategory(categoryId)
    }

    @PostMapping("/{categoryId}/books")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    fun addBookToCategory(@PathVariable categoryId: Long, @RequestBody bookId: Long): BookCategoryBook {
        return bookCategoryService.addBookToCategory(categoryId, bookId)
    }

    @GetMapping("/{categoryId}/books")
    @PreAuthorize("hasRole('ROLE_USER')")
    fun getBooksByCategoryId(@PathVariable categoryId: Long): List<Book> {
        return bookCategoryService.getBooksByCategoryId(categoryId)
    }

    @DeleteMapping("/{categoryId}/books/{bookId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    fun removeBookFromCategory(@PathVariable categoryId: Long, @PathVariable bookId: Long) {
        bookCategoryService.removeBookFromCategory(categoryId, bookId)
    }
}

