package com.bag.hotelmanagementsystem.controller;

import com.bag.hotelmanagementsystem.model.ReservationModel;
import com.bag.hotelmanagementsystem.model.RoomModel;
import com.bag.hotelmanagementsystem.model.UserModel;
import com.bag.hotelmanagementsystem.service.ReservationService;
import com.bag.hotelmanagementsystem.service.RoomService;
import com.bag.hotelmanagementsystem.service.UserService;
import org.apache.catalina.User;
import org.springframework.format.annotation.DateTimeFormat;
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
    private ReservationService reservationService;

    public AdminController(UserService userService,ReservationService reservationService, RoomService roomService) {
        this.userService = userService;
        this.roomService = roomService;
        this.reservationService = reservationService;
    }


    @GetMapping(value = "/getAdmin")
    public String getAdminPage(Model model) {

        List<RoomModel> roomModelList = roomService.getAllRoom();
        model.addAttribute("roomModelList", roomModelList);
        return "roomIndexPage";
    }

    /*@GetMapping(value = "/getReservation")
    public String getReservationPage(Model model) {
        return "reservation";
    }*/

    @GetMapping(value = "/getRoom")
    public String getRoomPage(Model model) {
        return "redirect:/admin/getAdmin";
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
    public String updateRoom(@ModelAttribute(value = "roomModel") RoomModel roomModel) {
        roomService.saveRoom(roomModel);
        return "redirect:/admin/getAdmin";
    }

    @GetMapping("/getUser")
    public String getUserPage(Model model) {

        List<UserModel> userModelList = userService.getUserByRole(false);
        model.addAttribute("userModelList", userModelList);
        return "userIndexPage";
    }

    @PostMapping("/postCreateUser")
    public String postCreateUser(@ModelAttribute(value = "userModel") UserModel userModel) {
        userService.saveUser(userModel);
        return "redirect:/admin/getUser";
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
        userService.deleteUserById(userId);
        return "redirect:/admin/getUser";
    }

    @GetMapping("/editUser/{userId}")
    public String editUser(@PathVariable(value = "userId") Long userId, Model model) {
        UserModel userModel = userService.getUserById(userId);
        model.addAttribute("userModel", userModel);
        return "userEditPage";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute(value = "userModel") UserModel userModel) {
        userService.saveUser(userModel);
        return "redirect:/admin/getUser";
    }

    @GetMapping("/getReservation")
    public String getReservation(Model model) {
        List<ReservationModel> reservationModelList = reservationService.getAllReservation();
        List<RoomModel> roomModelList = roomService.getRoomByReserve(false);
        model.addAttribute("roomModelList", roomModelList);
        model.addAttribute("reservationModelList", reservationModelList);
        return "reservationIndexPage";
    }

    @GetMapping("/deleteReservation/{reservationId}")
    public String deleteReservation(@PathVariable(value = "reservationId") Long reservationId, HttpServletRequest request) {
        ReservationModel reservationModel = reservationService.getReservationById(reservationId);
        RoomModel roomModel = roomService.getRoomById(reservationModel.getRoomNo());
        roomModel.setReserved(false);
        roomService.saveRoom(roomModel);
        reservationService.deleteReservationById(reservationId);
        return "redirect:/admin/getReservation";
    }

    @PostMapping("/postCreateReservation")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String postCreateReservation(@ModelAttribute(value = "reservationModel") ReservationModel reservationModel) {
        RoomModel roomModel = roomService.getRoomByRoomNumber(reservationModel.getRoomNo());
        roomModel.setReserved(true);
        roomService.saveRoom(roomModel);

        UserModel userModel1 = userService.getUser(reservationModel.getEmail(),reservationModel.getTcNumber());

        if(userModel1 == null){
            UserModel userModel = new UserModel();
            userModel.setAdmin(false);
            userModel.setEmail(reservationModel.getEmail());
            userModel.setName(reservationModel.getName());
            userModel.setSurName(reservationModel.getSurName());
            userModel.setTcNumber(reservationModel.getTcNumber());
            userModel.setTelephoneNumber(reservationModel.getTelephoneNumber());
            userService.saveUser(userModel);
        }

        reservationService.saveReservation(reservationModel);
        return "redirect:/admin/getReservation";
    }


}
