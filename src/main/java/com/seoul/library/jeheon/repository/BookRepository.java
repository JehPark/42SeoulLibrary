package com.seoul.library.jeheon.repository;

import com.seoul.library.jeheon.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByTitle(String title);
    List<Book> findBooksByTitleContaining(String title);
    Page<Book> findAll(Pageable pageable);
    Page<Book> findBookByTitleContaining(String title, Pageable pageable);
    Page<Book> findBookByAuthorContaining(String author, Pageable pageable);
}
