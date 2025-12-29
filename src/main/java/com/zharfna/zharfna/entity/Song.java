package com.zharfna.zharfna.entity;

import com.zharfna.zharfna.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "songs", indexes = {
        @Index(name = "idx_song_code", columnList = "song_code")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song extends BaseEntity<Long> {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "song_artists",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private Set<Artist> artists = new HashSet<>();

    @Column(name = "song_name", nullable = false, length = 200)
    private String songName;

    @Column(name = "song_code", nullable = false, unique = true)
    private Long songCode;
}
