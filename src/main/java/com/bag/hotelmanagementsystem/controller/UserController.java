package com.bag.hotelmanagementsystem.controller;

import com.bag.hotelmanagementsystem.service.ReservationService;
import com.bag.hotelmanagementsystem.service.RoomService;
import com.bag.hotelmanagementsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private RoomService roomService;
    private ReservationService reservationService;

    public UserController(UserService userService,ReservationService reservationService, RoomService roomService) {
        this.userService = userService;
        this.roomService = roomService;
        this.reservationService = reservationService;
    }









}
