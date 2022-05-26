package com.example.qlkhachsan.Repository;

import com.example.qlkhachsan.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RentalRepository extends JpaRepository<Rental,Long> {

    @Modifying
    @Query(value="SELECT room_room_id FROM rental WHERE guest_guest_id = :guest_guest_id  ", nativeQuery = true)
    List<Long> findRoomByGuestID(@Param("guest_guest_id") Long guest_guest_id);
    @Modifying
    @Query(value="SELECT * FROM rental WHERE guest_guest_id = :guest_guest_id and room_room_id = :room_room_id ", nativeQuery = true)
    List<Rental> findByGuestIDandRoomID(@Param("guest_guest_id") Long guest_guest_id,@Param("room_room_id") Long room_room_id );
}
