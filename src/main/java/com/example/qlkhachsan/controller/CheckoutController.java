package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.Repository.GuestRepository;
import com.example.qlkhachsan.Repository.RentalRepository;
import com.example.qlkhachsan.Repository.RoomRepository;
import com.example.qlkhachsan.model.Guest;
import com.example.qlkhachsan.model.Rental;
import com.example.qlkhachsan.model.Room;
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
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private RoomRepository roomRepo;
    @Autowired
    private RentalRepository renRepo;
    @Autowired
    private GuestRepository gueRepo;

    @GetMapping
    public String showSearchRoom(Model model) {
        model.addAttribute("Rooms",new ArrayList<Room>());
        return "checkout";
    }
    @GetMapping("/search")
    public String searchRoom(@Param("keyword") String keyword, Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        List<Long> lr = renRepo.findRoomByGuestID(Long.parseLong(keyword.trim()));
        List<Room> Rooms = roomRepo.findAll();


        Set<Room> result  = new HashSet<>();
        for (Long r : lr) {
            Optional<Room> optRoom = roomRepo.findById(r);
            if(optRoom.isPresent() && optRoom.get().getIsEmpty().equalsIgnoreCase("Đã SD") ){
                result.add(optRoom.get());
            }
        }
        Guest guest = new Guest();
        Optional<Guest> optGuest = gueRepo.findById(Long.parseLong(keyword.trim()));
        if(optGuest.isPresent()){
            guest=optGuest.get();
        }
        model.addAttribute("Guest",guest);
        model.addAttribute("Rooms",result);

        return "checkout";
    }
    @GetMapping("/add/{id}/{ud}")
    public String checkinForm(@PathVariable(name = "id") Long id,@PathVariable(name = "ud") Long ud ,Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);

        Room room = new Room();
        Optional<Room> optRoom = roomRepo.findById(id);
        if(optRoom.isPresent()){
            room=optRoom.get();
        }
        Rental rental =  renRepo.findByGuestIDandRoomID(ud,id).get(0);

        rental.setCheckOutDate(new Date());
        room.setIsEmpty("Trống");
        roomRepo.save(room);
        renRepo.save(rental);
        model.addAttribute("Rental",rental);

        return "trathanhcong";
    }
}
