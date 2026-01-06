package com.zharfna.zharfna.entity.user;

import com.zharfna.zharfna.entity.base.BaseEntity;
import com.zharfna.zharfna.enums.UserType;
import com.zharfna.zharfna.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
public class User extends BaseEntity<Long> {

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 15)
    private String mobileNumber;

    @Column(unique = true, length = 320)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Set<UserRole> userRoles = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Set<UserType> userTypes = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> grandAuthorities() {
        // ROLE_ADMIN
        return userRoles.stream()
                .map(userRole -> new SimpleGrantedAuthority("ROLE_" + userRole.name()))
                .collect(Collectors.toSet());

    }


    @Column(nullable = false)
    private boolean enabled = true;
}
