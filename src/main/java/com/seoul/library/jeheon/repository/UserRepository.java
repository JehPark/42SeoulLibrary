package com.seoul.library.jeheon.repository;

import com.seoul.library.jeheon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
