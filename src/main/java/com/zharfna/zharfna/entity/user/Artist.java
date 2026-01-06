package com.zharfna.zharfna.entity.user;

import com.zharfna.zharfna.entity.Owner;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(
        name = "artists",
        indexes = {
                @Index(name = "idx_stage_name", columnList = "stage_name"),
                @Index(name = "idx_owner_id", columnList = "owner_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @Column(name = "stage_name", nullable = false, unique = true, length = 100)
    private String stageName;

    @Lob
    private String biography;

    @Column(name = "profile_picture_url", length = 500)
    private String profilePictureUrl;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }
}
