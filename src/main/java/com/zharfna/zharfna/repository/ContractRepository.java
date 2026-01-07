package com.zharfna.zharfna.repository;


import com.zharfna.zharfna.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findAllByArtistName_Id(Long artistId);

    @Query("SELECT c FROM Contract c WHERE c.artistName.id = :artistId " +
            "AND c.active = true " +
            "AND :date BETWEEN c.startDate AND COALESCE(c.endDate, :futureDate)")
    Optional<Contract> findValidContract(@Param("artistId") Long artistId,
                                         @Param("date") LocalDate date,
                                         @Param("futureDate") LocalDate futureDate);
}
