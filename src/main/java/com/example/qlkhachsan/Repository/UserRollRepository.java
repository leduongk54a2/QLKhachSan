package com.example.qlkhachsan.Repository;

import com.example.qlkhachsan.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRollRepository extends JpaRepository<UserRole,Long> {
}
