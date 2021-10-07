package com.seoul.library.jeheon.service;

import com.seoul.library.jeheon.domain.Book;
import com.seoul.library.jeheon.domain.CheckOutStatus;
import com.seoul.library.jeheon.domain.CheckingOutInfo;
import com.seoul.library.jeheon.domain.User;
import com.seoul.library.jeheon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    final private UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Long userId, String name, String intraId){
        final Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()){
            return;
        }
        final User user = userOptional.get();
        user.update(name, intraId);
    }
}
