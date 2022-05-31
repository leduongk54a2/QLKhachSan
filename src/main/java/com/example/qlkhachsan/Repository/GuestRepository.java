package com.example.qlkhachsan.Repository;



import com.example.qlkhachsan.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface GuestRepository extends JpaRepository<Guest,Long> {

    @Modifying
    @Query(value = "select * from guest  where guest_name = :guest_name",nativeQuery = true)
    Guest findByGuestName(@Param("guest_name") String guest_name);
}
