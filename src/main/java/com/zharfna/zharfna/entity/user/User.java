package com.zharfna.zharfna.entity.user;

import com.zharfna.zharfna.entity.base.BaseEntity;
import com.zharfna.zharfna.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_users_mobile", columnList = "mobile"),
                @Index(name = "idx_users_email", columnList = "email")
        }
)
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User extends BaseEntity<Long> implements UserDetails {

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 15)
    private String mobile;

    @Column(unique = true, length = 320)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Set<Role> roles = new HashSet<>();

/*    public Collection<? extends GrandAuthority> grandAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_") + role.name()))
        .collct(Collectors.toSet());
}*/


    @Column(nullable = false)
    private boolean enabled = true;
}
