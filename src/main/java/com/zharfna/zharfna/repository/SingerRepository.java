package com.zharfna.zharfna.repository;

import com.zharfna.zharfna.entity.user.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SingerRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findByStageName(String stageName);

    List<Artist> findAllByEnabledTrue();
}