package com.bag.hotelmanagementsystem.controller;

import com.bag.hotelmanagementsystem.model.RoomModel;
import com.bag.hotelmanagementsystem.service.RoomService;
import com.bag.hotelmanagementsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

        List<RoomModel> roomModelList = roomService.getAllRoom();
        model.addAttribute("roomModelList", roomModelList);
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
        return "roomEditPage";
    }

    @PostMapping(value = "/postCreateRoom")
    public String postCreateRoom(@ModelAttribute RoomModel roomModel) {
        roomService.saveRoom(roomModel);
        return "redirect:/admin/getAdmin";
    }

    @GetMapping("/deleteRoom/{roomId}")
    public String deleteRoom(@PathVariable(value = "roomId") Long roomId, HttpServletRequest request) {
        roomService.deleteRoomById(roomId);
        return "redirect:/admin/getAdmin";
    }

    @GetMapping("/editRoom/{roomId}")
    public String editRoom(@PathVariable(value = "roomId") Long roomId, Model model) {
        RoomModel roomModel = roomService.getRoomById(roomId);
        model.addAttribute("roomModel", roomModel);
        return "roomEditPage";
    }

    @PostMapping("/updateRoom")
    public String updateRoom(@ModelAttribute RoomModel roomModel) {
        roomService.saveRoom(roomModel);
        return "redirect:/admin/getAdmin";
    }

}
