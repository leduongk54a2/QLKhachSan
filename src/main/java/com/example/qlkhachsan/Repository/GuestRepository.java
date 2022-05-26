package com.example.qlkhachsan.Repository;


import com.example.qlkhachsan.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface GuestRepository extends JpaRepository<Guest,Long> {
}
