package com.zharfna.zharfna.entity;

import com.zharfna.zharfna.entity.base.BaseEntity;
import com.zharfna.zharfna.enums.OwnerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners", indexes = {
        @Index(name = "idx_national_code", columnList = "national_code")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends BaseEntity<Long> {

    @Column(nullable = false, length = 200)
    private String name;

    @Column(name = "national_code", unique = true, length = 20)
    private String nationalCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "owner_type", nullable = false, length = 20)
    private OwnerType ownerType;

    @Column(name = "company_registration_number", length = 50)
    private String companyRegistrationNumber;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(nullable = false)
    private boolean active = true;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<Artist> artists = new HashSet<>();

    // Helper methods
    public void addArtist(Artist artist) {
        artists.add(artist);
        artist.setOwner(this);
    }

    public void removeArtist(Artist artist) {
        artists.remove(artist);
        artist.setOwner(null);
    }
}
