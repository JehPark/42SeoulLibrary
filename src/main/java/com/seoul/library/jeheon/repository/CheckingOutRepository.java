package com.seoul.library.jeheon.repository;

import com.seoul.library.jeheon.domain.CheckingOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckingOutRepository extends JpaRepository<CheckingOut, Long> {
}
