package com.seoul.library.jeheon.service;

import com.seoul.library.jeheon.domain.User;
import com.seoul.library.jeheon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    final private UserRepository userRepository;

    public void saveUser(User user){
        final User userByName = userRepository.findUserByNickName(user.getNickName());
        if (userByName != null) throw new IllegalStateException("이미 같은 이름을 가진 유저가 존재합니다.");
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long userId, String name){
        final Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) throw new RuntimeException("존재하지 않는 유저입니다.");
        final User user = userOptional.get();
        final User userByName = userRepository.findUserByNickName(name);
        if (userByName != null) throw new IllegalStateException("같은 이름을 가진 유저가 존재합니다.");
        user.update(name);
    }
}
