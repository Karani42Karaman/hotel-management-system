package com.bag.hotelmanagementsystem.repository;

import com.bag.hotelmanagementsystem.model.RoomModel;
import com.bag.hotelmanagementsystem.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomModel, Long> {
    @Query(value = "SELECT * FROM hotel.room r where r.is_reserved = ?;", nativeQuery = true)
    public List<RoomModel> getRoomByReserve(boolean is_reserved);


    @Query(value = "SELECT * FROM hotel.room r where r.roomnumber = ?;", nativeQuery = true)
    public RoomModel getRoomByRoomNumber(Long roomnumber);

    @Query(value = "SELECT * FROM hotel.room r where r.id = ?;", nativeQuery = true)
    public RoomModel getRoomByid(Long id);

}
