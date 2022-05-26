package com.example.qlkhachsan.Repository;

import com.example.qlkhachsan.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<AppUser, Long> {

    @Query("select usr from AppUser usr where usr.userName = :userName")
    public AppUser findUserName(@Param("userName") String userName);
}
