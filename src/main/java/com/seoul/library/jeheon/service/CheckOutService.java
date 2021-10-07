package com.seoul.library.jeheon.service;

import com.seoul.library.jeheon.domain.*;
import com.seoul.library.jeheon.repository.BookRepository;
import com.seoul.library.jeheon.repository.CheckingOutInfoRepository;
import com.seoul.library.jeheon.repository.CheckingOutRepository;
import com.seoul.library.jeheon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CheckOutService {
    final private CheckingOutInfoRepository checkingOutInfoRepository;
    final private UserRepository userRepository;
    final private BookRepository bookRepository;

    @Transactional
    public void checkout(Long userId, List<Long> bookIds, List<Integer> quantities){
        List<CheckingOut> checkingOuts = new ArrayList<>();

        // user와 책 찾기
        final Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) throw new RuntimeException("존재하지 않는 유저입니다.");
        final User user = userOptional.get();
        final List<Book> books = bookRepository.findAllById(bookIds);

        // 대출생성
        for (int i = 0; i < books.size(); i++){
            checkingOuts.add(CheckingOut.createCheckingOut(books.get(i), quantities.get(i)));
        }

        //대출정보 생성

        final CheckingOutInfo checkingOutInfo = CheckingOutInfo.createCheckingOutInfo(checkingOuts, user);
        checkingOutInfoRepository.save(checkingOutInfo);
    }
}
