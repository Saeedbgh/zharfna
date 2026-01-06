package com.zharfna.zharfna.repository;

import com.zharfna.zharfna.entity.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmployeeId(String employeeId);
}