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
    final private CheckingOutRepository checkingOutRepository;

    @Transactional
    public void checkout(Long userId, CheckingOutInfo checkingOutInfo ,Long bookId){
        final List<CheckingOut> checkingOuts = checkingOutInfo.getCheckingOuts();

        // user와 책 찾기
        final Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) throw new RuntimeException("존재하지 않는 유저입니다.");
        final User user = userOptional.get();
        final Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) throw new RuntimeException("존재하지 않는 책입니다.");
        final Book book = bookOptional.get();

        // 대출생성
        checkingOuts.add(CheckingOut.createCheckingOut(book));
        //대출정보 생성
        checkingOutInfoRepository.save(checkingOutInfo);
    }

    @Transactional
    public void bookReturn(Long checkingOutId, Long checkingOutInfoId) {
        final Optional<CheckingOutInfo> checkingOutInfoOptional = checkingOutInfoRepository.findById(checkingOutInfoId);
        if (checkingOutInfoOptional.isEmpty()) throw new RuntimeException("존재하지 않는 대출정보 입니다.");
        final CheckingOutInfo checkingOutInfo = checkingOutInfoOptional.get();
        final List<CheckingOut> checkingOuts = checkingOutInfo.getCheckingOuts();
        for (CheckingOut checkingOut : checkingOuts) {
            if (checkingOut.getId().equals(checkingOutId)){
                checkingOut.bookReturn();
            }
        }
    }
}
