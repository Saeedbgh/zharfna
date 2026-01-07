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
    private int artistSharePercent;

    @Column(name = "owner_share_percent", nullable = false)
    private int ownerSharePercent;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(nullable = false)
    private boolean active = true;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String terms;

    @PrePersist
    @PreUpdate
    private void validateShares() {
        if (artistSharePercent + ownerSharePercent != 100) {
            throw new IllegalStateException(
                    "Sum of all shares must equal 100"
            );
        }
    }

    public boolean isActiveNow() {
        LocalDate today = LocalDate.now();
        return active &&
                !startDate.isAfter(today) &&
                (endDate == null || !endDate.isBefore(today));
    }


}
