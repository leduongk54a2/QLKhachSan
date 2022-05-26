package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.Repository.GuestRepository;
import com.example.qlkhachsan.model.Employee;
import com.example.qlkhachsan.model.Guest;
import com.example.qlkhachsan.model.Room;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Controller
@RequestMapping("/khachhang")
public class ManageGuestController {

    @Autowired
    private GuestRepository repo;


    @RequestMapping()
    public String showListEmployee(Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        List<Guest> lg = (List<Guest>) repo.findAll();
        model.addAttribute("Guests",lg);
        return "quanlykhachhang";
    }


    @GetMapping("/search")
    public String searchGuest(@Param("keyword") String keyword, Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        keyword = keyword.trim();
        List<Guest> le = repo.findAll();
        List<Guest> result  = new ArrayList<Guest>();
        for (Guest e : le) {
            Long x = e.getGuestId();
            if(x.toString().toLowerCase().contains((keyword).toLowerCase())||
                    e.getGuestName().toLowerCase().contains(keyword.toLowerCase()) ||
                    e.getBirth().toLowerCase().contains(keyword.toLowerCase())||
                    e.getAddress().toLowerCase().contains(keyword.toLowerCase())||
                    e.getEmail().toLowerCase().contains(keyword.toLowerCase())||
                    e.getIdCard().toLowerCase().contains(keyword.toLowerCase())||
                    e.getPhoneNumber().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(e);
            }
        }
        model.addAttribute("Guests",result);
        return "quanlykhachhang";
    }
    @GetMapping("/edit/{id}")
    public  String showEditGuest(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        Guest guest = new Guest();
        Optional<Guest> optGuest = repo.findById(id);
        if(optGuest.isPresent()){
            guest = optGuest.get();
        }
        model.addAttribute("Guest", guest );
        return "suakhachhang";
    }
    @PostMapping("/edit/{id}")
    public  String editGuest(@PathVariable(name = "id") Long id,Guest guest) {
        Optional<Guest> optGuest = repo.findById(id);
        if( optGuest.isPresent()){
            Guest guestExist =  optGuest.get();
            guestExist.setIdCard(guest.getIdCard());
            guestExist.setGuestName(guest.getGuestName());
            guestExist.setAddress((guest.getAddress()));
            guestExist.setBirth((guest.getBirth()));
            guestExist.setEmail(guest.getEmail());
            guestExist.setPhoneNumber(guest.getPhoneNumber());
            repo.save(guestExist);
        }
        return "redirect:/khachhang";
    }
    @GetMapping("/delete/{id}")
    public  String deleteGuest(@PathVariable(name = "id") Long id) {
        repo.deleteById(id);
        return "redirect:/khachhang";
    }
}
