package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "home"})
    public String homePage(Model model,Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);

        return "home";
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        if(principal != null) {
            User loginUser = (User) ((Authentication)principal).getPrincipal();
            String userInfor = WebUtils.toString(loginUser);
            model.addAttribute("userInfor", userInfor);
            String message = "Xin chào " + principal.getName() + ". Bạn không có quyền truy cập vào trang web này";
            model.addAttribute("message", message);
            message = principal.getName() ;
            model.addAttribute("message1", message);
        }
        return "403Page";
    }
}
