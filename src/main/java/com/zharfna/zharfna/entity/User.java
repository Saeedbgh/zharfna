package com.zharfna.zharfna.entity;

import com.zharfna.zharfna.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User extends BaseEntity<Long> {

    @Column(unique = true, nullable = false)
    private String cellPhoneNumber;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean enabled = true;
}