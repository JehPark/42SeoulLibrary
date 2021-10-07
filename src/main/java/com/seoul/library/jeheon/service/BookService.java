package com.seoul.library.jeheon.service;

import com.seoul.library.jeheon.domain.Book;
import com.seoul.library.jeheon.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    final private BookRepository bookRepository;

    @Transactional
    public void saveBook(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(Long bookId, String title, String author, String publisher, int quantity){
        final Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()){
            return;
        }
        final Book book = bookOptional.get();
        book.update(title, author, publisher, quantity);
    }

    public Page<Book> findAll(Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public Page<Book> findByTitle(String search, Pageable pageable){
        return bookRepository.findBookByTitleContaining(search, pageable);
    }

    public Page<Book> findByAuthor(String search, Pageable pageable){
        return bookRepository.findBookByAuthorContaining(search, pageable);
    }

}
