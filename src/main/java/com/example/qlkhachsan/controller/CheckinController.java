package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.Repository.GuestRepository;
import com.example.qlkhachsan.Repository.RentalRepository;
import com.example.qlkhachsan.Repository.RoomRepository;
import com.example.qlkhachsan.Repository.UserRepository;
import com.example.qlkhachsan.model.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Controller
@RequestMapping("/checkin")
public class CheckinController {
    @Autowired
    private RoomRepository roomRepo;
    @Autowired
    private RentalRepository renRepo;
    @Autowired
    private GuestRepository gueRepo;
    @Autowired
    private UserRepository useRepo;

    @GetMapping
    public String showListRoom(Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);

        List<Room> lr = (List<Room>) roomRepo.findAll();
        model.addAttribute("Rooms",lr);
        model.addAttribute("Check1","All");
        return "checkin";
    }

    @GetMapping("/search")
    public String searchRoom(@Param("keyword") String keyword, Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        List<Room> lr = roomRepo.findAll();
        keyword = keyword.trim();
        if(keyword.equalsIgnoreCase("All")) {
            model.addAttribute("check1",keyword);
            model.addAttribute("Rooms",lr);
            return "checkin";
        }

        List<Room> result  = new ArrayList<Room>();
        for (Room r : lr) {
            if(r.getType().equalsIgnoreCase(keyword)) {
                result.add(r);
            }
        }
        model.addAttribute("check1",keyword);
        model.addAttribute("Rooms",result);
        return "checkin";
    }

    @GetMapping("/add/{id}")
    public  String showListGuest(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        AppUser au = useRepo.findUserName(message);
        Guest guest = au.getGuest();
        Room room = new Room();
        Optional<Room> optRoom = roomRepo.findById(id);
        if(optRoom.isPresent()){
            room = optRoom.get();
        }
//        Rental rental = new Rental();
//        rental.setRoom(room);
//        rental.setCheckInDate(new Date());
//        model.addAttribute("Rental",rental);
        if(room.getIsEmpty().equalsIgnoreCase("Đã SD")) {
            return "datloi";
        }
        model.addAttribute("Room", room );
        model.addAttribute("Guest",guest);
        return "formdat";
    }

    @GetMapping("/add/{id}/submit")
    public String checkinForm(@PathVariable(name = "id") Long id,Model model,Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        AppUser au = useRepo.findUserName(message);
        Guest guest = au.getGuest();
        Room room = new Room();
        Optional<Room> optRoom = roomRepo.findById(id);
        if(optRoom.isPresent()){
            room = optRoom.get();
            room.setIsEmpty("Đã SD");
            roomRepo.save(room);
        }

        Rental rental = new Rental();
        rental.setRoom(room);
        rental.setGuest(guest);
        rental.setCheckInDate(new Date());
        renRepo.save(rental);
        model.addAttribute("Rental",rental);

        return "datthanhcong";
    }
}
