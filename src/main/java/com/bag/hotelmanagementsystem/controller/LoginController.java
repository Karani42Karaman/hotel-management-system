package com.bag.hotelmanagementsystem.controller;

import com.bag.hotelmanagementsystem.model.UserModel;
import com.bag.hotelmanagementsystem.service.UserService;
import com.bag.hotelmanagementsystem.serviceImpl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getLogin")
    public String login(Model model) {
        return "login";
    }

    @PostMapping(value = "/postLogin")
    public String postLogin(@ModelAttribute UserModel user, HttpSession session ,Model model) {
        if (user != null) {
            UserModel userModel = userService.getUser(user.getEmail(), user.getTcNumber());
            if (userModel != null && user.getEmail().equals(userModel.getEmail()) && user.getTcNumber().equals(userModel.getTcNumber())) {
                if (userModel.isAdmin()) {
                    session.setAttribute("admin", userModel);
                    return "redirect:/admin/getAdmin";
                }else{
                    session.setAttribute("employe", userModel);
                    return "redirect:/employe/getEmploye";
                }
            } else {
                model.addAttribute("error", false);
            }
        }
        return "login";
    }



    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login/getLogin";
    }

    @GetMapping(value = "/Authorization")
    public String getAuthErrorPage() {
        return "authErrorPage";
    }

}
