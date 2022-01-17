package com.bag.hotelmanagementsystem.service;

import com.bag.hotelmanagementsystem.model.ReservationModel;
import com.bag.hotelmanagementsystem.model.RoomModel;

import java.util.List;

public interface ReservationService {
    List<ReservationModel> getAllReservation();
    ReservationModel saveReservation(ReservationModel reservationModel);
    ReservationModel getReservationById(Long id);
    ReservationModel updateReservation(ReservationModel reservationModel);
    void deleteReservationById(Long id);
}
