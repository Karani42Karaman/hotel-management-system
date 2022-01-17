package com.bag.hotelmanagementsystem.serviceImpl;

import com.bag.hotelmanagementsystem.model.RoomModel;
import com.bag.hotelmanagementsystem.repository.RoomRepository;
import com.bag.hotelmanagementsystem.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomModel> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public RoomModel saveRoom(RoomModel roomModel) {
        return roomRepository.save(roomModel);
    }

    @Override
    public RoomModel getRoomById(Long id) {
        return roomRepository.getById(id);
    }

    @Override
    public RoomModel updateRoom(RoomModel roomModel) {
        return roomRepository.save(roomModel);
    }

    @Override
    public void deleteRoomById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public List<RoomModel> getRoomByReserve(boolean reserve) {
        return roomRepository.getRoomByReserve(reserve);
    }

    @Override
    public RoomModel getRoomByRoomNumber(Long roomNumber) {
        return roomRepository.getRoomByRoomNumber(roomNumber);
    }
}
