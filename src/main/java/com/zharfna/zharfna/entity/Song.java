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
@Table(name = "songs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song extends BaseEntity<Long> {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "song_singers",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "singer_id")
    )
    private Set<Singer> singers = new HashSet<>();

    @Column(name = "song_name", nullable = false)
    private String songName;

    @Column(name = "song_code", nullable = false, unique = true)
    private Long songCode;


}
