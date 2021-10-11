package com.seoul.library.jeheon.repository;

import com.seoul.library.jeheon.domain.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    public void mockDataInjection(){
        bookRepository.save(Book.builder()
                .author("김영한")
                .title("ORM JPA")
                .publisher("에이콘")
                .quantity(5)
                .build());

        bookRepository.save(Book.builder()
                .author("제임스 J. 예")
                .title("모던 웹 애플리케이션 개발")
                .publisher("위키북스")
                .quantity(3)
                .build());

        bookRepository.save(Book.builder()
                .author("알렉스 쉬")
                .title("가상 면접 사례로 배우는 대규모 시스템 설계 기초")
                .publisher("인사이트")
                .quantity(2)
                .build());

        bookRepository.save(Book.builder()
                .author("아드난 아지즈")
                .title("262가지 문제로 정복하는 코딩 인터뷰 in Java")
                .publisher("인사이트")
                .quantity(3)
                .build());
    }

    @Test
    public void saveBookTest() throws Exception{
        //when
        final Optional<Book> book = Optional.ofNullable(bookRepository.findBookByTitle("ORM JPA"));
        final Book book1 = book.get();
        //then
        assertThat(book1.getAuthor()).isEqualTo("김영한");
        assertThat(book1.getPublisher()).isEqualTo("에이콘");
    }

    @Test
    public void findBookByTitleContainingTest() throws Exception{
        //given
        //when
        final List<Book> books = bookRepository.findBooksByTitleContaining("정복");
        //then
        assertThat(books.isEmpty()).isFalse();
        books.forEach(book -> assertThat(book.getTitle()).contains("정복"));
    }

    @AfterEach
    public void clearData(){
        bookRepository.deleteAll();
    }

}