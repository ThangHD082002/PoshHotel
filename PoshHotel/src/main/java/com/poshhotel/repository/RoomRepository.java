package com.poshhotel.repository;

import com.poshhotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
    Room findRoomById(Long id);
}
