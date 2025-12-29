package com.zharfna.zharfna.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "artists", indexes = {
        @Index(name = "idx_stage_name", columnList = "stage_name"),
        @Index(name = "idx_owner", columnList = "owner_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @Column(name = "stage_name", unique = true, length = 100)
    private String stageName;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @Column(name = "profile_picture_url", length = 500)
    private String profilePictureUrl;
}
