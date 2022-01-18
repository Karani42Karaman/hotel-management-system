package com.bag.hotelmanagementsystem.service;

import com.bag.hotelmanagementsystem.model.RoomModel;

import java.util.List;

public interface RoomService {
    List<RoomModel> getAllRoom();
    RoomModel saveRoom(RoomModel roomModel);
    RoomModel getRoomById(Long id);
    RoomModel updateRoom(RoomModel roomModel);
    void deleteRoomById(Long id);
    List<RoomModel> getRoomByReserve(boolean reserve);
    RoomModel getRoomByRoomNumber(Long roomNumber);
    RoomModel getRoomByid(Long id);

}
