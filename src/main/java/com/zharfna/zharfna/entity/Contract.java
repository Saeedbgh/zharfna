package com.zharfna.zharfna.entity;

import com.zharfna.zharfna.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "contracts", indexes = {
        @Index(name = "idx_owner_active", columnList = "owner_id, active"),
        @Index(name = "idx_start_end_date", columnList = "start_date, end_date")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contract extends BaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @Column(name = "artist_share_percent", nullable = false)
    private Integer artistSharePercent;

    @Column(name = "owner_share_percent", nullable = false)
    private Integer ownerSharePercent;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(nullable = false)
    private boolean active = true;

    @Column(columnDefinition = "TEXT")
    private String terms;

    @PrePersist
    @PreUpdate
    private void validateShares() {
        int total = artistSharePercent + ownerSharePercent;
        if (total != 100) {
            throw new IllegalStateException(
                    "Sum of all shares must equal 100, current sum: " + total
            );
        }
    }
}
