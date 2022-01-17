package com.bag.hotelmanagementsystem.repository;

import com.bag.hotelmanagementsystem.model.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {
}
