package com.zharfna.zharfna.repository;

import com.zharfna.zharfna.entity.RevenueRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RevenueRecordRepository extends JpaRepository<RevenueRecord, Long> {
    List<RevenueRecord> findAllBySong_IdAndReportDateBetween(Long songId, LocalDate start, LocalDate end);

    @Query("SELECT r FROM RevenueRecord r JOIN r.song s JOIN s.singers sn " +
            "WHERE sn.id = :singerId AND r.reportDate BETWEEN :start AND :end")
    List<RevenueRecord> findAllBySingerIdAndDateRange(@Param("singerId") Long singerId,
                                                      @Param("start") LocalDate start,
                                                      @Param("end") LocalDate end);
}
