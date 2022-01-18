package com.bag.hotelmanagementsystem.controller;

import com.bag.hotelmanagementsystem.model.ReservationModel;
import com.bag.hotelmanagementsystem.model.RoomModel;
import com.bag.hotelmanagementsystem.model.UserModel;
import com.bag.hotelmanagementsystem.service.ReservationService;
import com.bag.hotelmanagementsystem.service.RoomService;
import com.bag.hotelmanagementsystem.service.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/employe")
public class EmployeController {
    private UserService userService;
    private RoomService roomService;
    private ReservationService reservationService;

    public EmployeController(UserService userService, ReservationService reservationService, RoomService roomService) {
        this.userService = userService;
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @GetMapping(value = "getIndex")
    public String getIndex(Model model) {
        List<RoomModel> roomModelList = roomService.getRoomByReserve(false);
        int sayac = 0;
        model.addAttribute("sayac", sayac);
        model.addAttribute("roomModelList", roomModelList);
        return "Giris";
    }


    @GetMapping(value = "getWelcome")
    public String getWelcome() {

        return "Welcome";
    }

    @GetMapping(value = "/getEmploye")
    public String getEmploye(Model model, HttpServletResponse response, HttpServletRequest request) {

        UserModel sessionEmploye = (UserModel) request.getSession().getAttribute("employe");
        if (sessionEmploye != null) {
            //add cookie to response
            Cookie cookie1 = new Cookie("UserInfo", sessionEmploye.getName());
            cookie1.setMaxAge(1 * 24 * 60 * 60); // expires in 1 days
            cookie1.setSecure(false);
            cookie1.setHttpOnly(false);
            response.addCookie(cookie1);

            //add cookie to response
            Cookie cookie2 = new Cookie("Role", "Employe");
            cookie2.setMaxAge(1 * 24 * 60 * 60); // expires in 1 days
            cookie2.setSecure(false);
            cookie2.setHttpOnly(false);
            response.addCookie(cookie2);

            List<RoomModel> roomModelList = roomService.getAllRoom();
            model.addAttribute("roomModelList", roomModelList);
            List<ReservationModel> reservationModelList = reservationService.getAllByReservation(sessionEmploye.getEmail(), sessionEmploye.getTcNumber());
            model.addAttribute("reservationModelList", reservationModelList);
            return "employeIndexPage";
        } else {
            return "redirect:/login/Authorization";
        }

    }

    @GetMapping("/deleteReservation/{reservationId}")
    public String deleteReservation(@PathVariable(value = "reservationId") Long reservationId, HttpServletRequest request) {
        UserModel sessionEmploye = (UserModel) request.getSession().getAttribute("employe");
        if (sessionEmploye != null) {
            ReservationModel reservationModel = reservationService.getReservationById(reservationId);
            RoomModel roomModel = roomService.getRoomById(reservationModel.getRoomNo());
            roomModel.setReserved(false);
            roomService.saveRoom(roomModel);
            reservationService.deleteReservationById(reservationId);
            return "employeIndexPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }


    @PostMapping("/postCreateReservationOut")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String postCreateReservationOut(@ModelAttribute(value = "reservationModel") ReservationModel reservationModel, Model model, HttpServletRequest request) {
        UserModel sessionEmploye = (UserModel) request.getSession().getAttribute("employe");
        if (sessionEmploye != null) {
            RoomModel roomModel = roomService.getRoomByRoomNumber(reservationModel.getRoomNo());
            roomModel.setReserved(true);
            roomService.saveRoom(roomModel);

            UserModel userModel1 = userService.getUser(reservationModel.getEmail(), reservationModel.getTcNumber());

            if (userModel1 == null) {
                UserModel userModel = new UserModel();
                userModel.setAdmin(false);
                userModel.setEmail(reservationModel.getEmail());
                userModel.setName(reservationModel.getName());
                userModel.setSurName(reservationModel.getSurName());
                userModel.setTcNumber(reservationModel.getTcNumber());
                userModel.setTelephoneNumber(reservationModel.getTelephoneNumber());
                userService.saveUser(userModel);
            }

            model.addAttribute("deneme", true);
            reservationService.saveReservation(reservationModel);
            return "reservation";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping(value = "/getReservationOut")
    public String getReservationOut(Model model, HttpServletRequest request) {
        UserModel sessionEmploye = (UserModel) request.getSession().getAttribute("employe");
        if (sessionEmploye != null) {
            List<RoomModel> roomModelList = roomService.getRoomByReserve(false);
            model.addAttribute("roomModelList", roomModelList);
            return "reservation";
        } else {
            return "redirect:/login/Authorization";
        }
    }

}
