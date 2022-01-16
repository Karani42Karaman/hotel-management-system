package com.bag.hotelmanagementsystem.repository;

import com.bag.hotelmanagementsystem.model.RoomModel;
import com.bag.hotelmanagementsystem.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<RoomModel, Long> {
    @Query(value = "SELECT * FROM hotel.room r where r.is_reseved = ?;", nativeQuery = true)
    public List<RoomModel> getRoomByReserve(boolean is_reserved);

}
