package com.seoul.library.jeheon.service;

import com.seoul.library.jeheon.domain.Book;
import com.seoul.library.jeheon.domain.User;
import com.seoul.library.jeheon.repository.BookRepository;
import com.seoul.library.jeheon.repository.CheckingOutInfoRepository;
import com.seoul.library.jeheon.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckOutServiceTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CheckOutService checkOutService;

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

        userRepository.save(User.builder()
                .name("Jeheon")
                .intraId("jehpark")
                .build());

        userRepository.save(User.builder()
                .name("제헌")
                .intraId("jehpark")
                .build());
    }

    @Test
    public void 대출테스트() throws Exception{
        //given
        final List<User> all = userRepository.findAll();
        final User user = all.get(0);
        final List<Book> books = bookRepository.findAllById(List.of(1L, 2L, 3L));

        for (Book book : books) {
            System.out.println("book.getQuantity() = " + book.getQuantity());
        }
        //when
        checkOutService.checkout(user.getId(), List.of(1L, 2L, 3L), List.of(1, 1, 1));
        //then
        for (Book book : books) {
            System.out.println("book.getQuantity() = " + book.getQuantity());
        }
    }
}