package com.zharfna.zharfna.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "admins",
        indexes = {
                @Index(name = "idx_admin_employee_id", columnList = "employee_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends User {
    @Column(name = "employee_id", nullable = false, unique = true, length = 50)
    private String employeeId;
}
