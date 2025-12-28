package com.zharfna.zharfna.repository;

import com.zharfna.zharfna.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SingerRepository extends JpaRepository<Singer, Long> {

    Optional<Singer> findByStageName(String stageName);

    List<Singer> findAllByEnabledTrue();
}