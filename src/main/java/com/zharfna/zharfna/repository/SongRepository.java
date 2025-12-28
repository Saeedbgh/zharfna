package com.zharfna.zharfna.repository;

import com.zharfna.zharfna.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findBySongCode(Long songCode);

    List<Song> findAllBySingers_Id(Long singerId);

    boolean existsBySongCode(Long songCode);
}