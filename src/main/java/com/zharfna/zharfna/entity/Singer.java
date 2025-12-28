package com.zharfna.zharfna.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "singers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Singer extends User {

    private String stageName; // نام هنری

    private String biography;

    private String profilePictureUrl;
}