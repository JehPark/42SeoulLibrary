package com.seoul.library.jeheon.repository;

import com.seoul.library.jeheon.domain.CheckingOutInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckingOutInfoRepository extends JpaRepository<CheckingOutInfo, Long> {
}
