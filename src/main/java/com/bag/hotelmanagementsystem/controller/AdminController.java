package com.bag.hotelmanagementsystem.controller;

import com.bag.hotelmanagementsystem.dto.RoomDto;
import com.bag.hotelmanagementsystem.model.RoomModel;
import com.bag.hotelmanagementsystem.service.RoomService;
import com.bag.hotelmanagementsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;
    private RoomService roomService;

    public AdminController(UserService userService, RoomService roomService) {
        this.userService = userService;
        this.roomService = roomService;
    }


    @GetMapping(value = "/getAdmin")
    public String getAdminPage(Model model) {

        return "roomIndexPage";
    }

    @GetMapping(value = "/getReservation")
    public String getReservationPage(Model model) {
        return "reservation";
    }

    @GetMapping(value = "/getRoom")
    public String getRoomPage(Model model) {
        return "index";
    }

    @GetMapping(value = "/getCreateRoom")
    public String getCreateRoom(Model model) {
        return "roomCreatePage";
    }

    @PostMapping(value = "/postCreateRoom")
    public String postCreateRoom(@ModelAttribute RoomDto roomDto) {



        return "roomCreatePage";
    }




}
