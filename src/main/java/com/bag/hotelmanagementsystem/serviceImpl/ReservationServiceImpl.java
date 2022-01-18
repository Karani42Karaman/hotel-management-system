package com.bag.hotelmanagementsystem.serviceImpl;

import com.bag.hotelmanagementsystem.model.ReservationModel;
import com.bag.hotelmanagementsystem.model.RoomModel;
import com.bag.hotelmanagementsystem.repository.ReservationRepository;
import com.bag.hotelmanagementsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Override
    public List<ReservationModel> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public ReservationModel saveReservation(ReservationModel reservationModel) {
        return reservationRepository.save(reservationModel);
    }

    @Override
    public ReservationModel getReservationById(Long id) {
        return reservationRepository.getById(id);
    }

    @Override
    public ReservationModel updateReservation(ReservationModel reservationModel) {
        return reservationRepository.save(reservationModel);
    }

    @Override
    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<ReservationModel> getAllByReservation(String email, Long tcNumber) {
        return reservationRepository.getFindResesevation(email, tcNumber);
    }

    @Override
    public ReservationModel getReservationid(Long id) {
        return reservationRepository.getByid(id);
    }
}
