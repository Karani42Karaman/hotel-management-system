package com.bag.hotelmanagementsystem.repository;

import com.bag.hotelmanagementsystem.model.ReservationModel;
import com.bag.hotelmanagementsystem.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {
    @Query(value = "SELECT * FROM reservation u where u.email = ? and u.tcNumber = ?;", nativeQuery = true)
    public List<ReservationModel> getFindResesevation(String email, Long tcNumber);
}
