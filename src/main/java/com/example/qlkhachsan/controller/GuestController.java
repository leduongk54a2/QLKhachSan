package com.example.qlkhachsan.controller;

import com.example.qlkhachsan.Repository.GuestRepository;
import com.example.qlkhachsan.model.Employee;
import com.example.qlkhachsan.model.Guest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/dangky")
public class GuestController {

    @Autowired
    private GuestRepository repo;


    @GetMapping
    public  String showAddGuest(Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        model.addAttribute("Guest", new Guest() );
        return "dangky";
    }

    @PostMapping()
    public  String addGuest(Guest guest ,Model model){
        repo.save(guest);
        model.addAttribute("Guest",guest);
        return "dangkythanhcong";
    }


}
