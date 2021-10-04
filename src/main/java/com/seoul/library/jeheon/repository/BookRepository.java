package com.seoul.library.jeheon.repository;

import com.seoul.library.jeheon.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
