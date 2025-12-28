package com.zharfna.zharfna.repository;

import com.zharfna.zharfna.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
    Optional<Operator> findByOperatorName(String name);

    Optional<Operator> findByOperatorCode(Long code);
}
