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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;
    private RoomService roomService;
    private ReservationService reservationService;

    public AdminController(UserService userService, ReservationService reservationService, RoomService roomService) {
        this.userService = userService;
        this.roomService = roomService;
        this.reservationService = reservationService;
    }


    @GetMapping(value = "/getAdmin")
    public String getAdminPage(Model model, HttpServletResponse response, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            //add cookie to response
            Cookie cookie1 = new Cookie("UserInfo", sessionAdmin.getName());
            cookie1.setMaxAge(1 * 24 * 60 * 60); // expires in 1 days
            cookie1.setSecure(false);
            cookie1.setHttpOnly(false);
            response.addCookie(cookie1);

            //add cookie to response
            Cookie cookie2 = new Cookie("Role", "Admin");
            cookie2.setMaxAge(1 * 24 * 60 * 60); // expires in 1 days
            cookie2.setSecure(false);
            cookie2.setHttpOnly(false);
            response.addCookie(cookie2);
            List<RoomModel> roomModelList = roomService.getAllRoom();

            model.addAttribute("roomModelList", roomModelList);
            return "roomIndexPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }


    @GetMapping(value = "/getRoom")
    public String getRoomPage(Model model, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            return "redirect:/admin/getAdmin";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping(value = "/getCreateRoom")
    public String getCreateRoom(Model model, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            return "roomEditPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @PostMapping(value = "/postCreateRoom")
    public String postCreateRoom(@ModelAttribute RoomModel roomModel, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            if (roomModel != null) {
                roomService.saveRoom(roomModel);
            }
            return "redirect:/admin/getAdmin";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/deleteRoom/{roomId}")
    public String deleteRoom(@PathVariable(value = "roomId") Long roomId, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            roomService.deleteRoomById(roomId);
            return "redirect:/admin/getAdmin";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/editRoom/{roomId}")
    public String editRoom(@PathVariable(value = "roomId") Long roomId, Model model, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            RoomModel roomModel = roomService.getRoomById(roomId);
            model.addAttribute("roomModel", roomModel);
            return "roomEditPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @PostMapping("/updateRoom")
    public String updateRoom(@ModelAttribute(value = "roomModel") RoomModel roomModel, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            roomService.saveRoom(roomModel);
            return "redirect:/admin/getAdmin";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/getUser")
    public String getUserPage(Model model, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            List<UserModel> userModelList = userService.getUserByRole(false);
            model.addAttribute("userModelList", userModelList);
            return "userIndexPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @PostMapping("/postCreateUser")
    public String postCreateUser(@ModelAttribute(value = "userModel") UserModel userModel, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            userService.saveUser(userModel);
            return "redirect:/admin/getUser";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable(value = "userId") Long userId, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            userService.deleteUserById(userId);
            return "redirect:/admin/getUser";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/editUser/{userId}")
    public String editUser(@PathVariable(value = "userId") Long userId, Model model, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            UserModel userModel = userService.getUserById(userId);
            model.addAttribute("userModel", userModel);
            return "userEditPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute(value = "userModel") UserModel userModel, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            userService.saveUser(userModel);
            return "redirect:/admin/getUser";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/getReservation")
    public String getReservation(Model model, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            List<ReservationModel> reservationModelList = reservationService.getAllReservation();
            List<RoomModel> roomModelList = roomService.getRoomByReserve(false);
            model.addAttribute("roomModelList", roomModelList);
            model.addAttribute("reservationModelList", reservationModelList);
            return "reservationIndexPage";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @GetMapping("/deleteReservation/{reservationId}")
    public String deleteReservation(@PathVariable(value = "reservationId") Long reservationId, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
            ReservationModel reservationModel = reservationService.getReservationById(reservationId);
            RoomModel roomModel = roomService.getRoomById(reservationModel.getRoomNo());
            roomModel.setReserved(false);
            roomService.saveRoom(roomModel);
            reservationService.deleteReservationById(reservationId);
            return "redirect:/admin/getReservation";
        } else {
            return "redirect:/login/Authorization";
        }
    }

    @PostMapping("/postCreateReservation")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String postCreateReservation(@ModelAttribute(value = "reservationModel") ReservationModel reservationModel, HttpServletRequest request) {
        UserModel sessionAdmin = (UserModel) request.getSession().getAttribute("admin");
        if (sessionAdmin != null) {
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

            reservationService.saveReservation(reservationModel);
            return "redirect:/admin/getReservation";
        } else {
            return "redirect:/login/Authorization";
        }
    }
}
