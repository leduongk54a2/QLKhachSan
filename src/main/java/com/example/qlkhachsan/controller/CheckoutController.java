package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.Repository.GuestRepository;
import com.example.qlkhachsan.Repository.RentalRepository;
import com.example.qlkhachsan.Repository.RoomRepository;
import com.example.qlkhachsan.Repository.UserRepository;
import com.example.qlkhachsan.model.AppUser;
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
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private UserRepository useRepo;


    @GetMapping
    public String searchRoom(Model model, Principal principal) {
        String message = principal.getName();
        model.addAttribute("message1", message);
        AppUser au = useRepo.findUserName(message);
        Guest guest = au.getGuest();
        List<Long> lr = renRepo.findRoomByGuestID(guest.getGuestId());
        List<Room> Rooms = roomRepo.findAll();


        Set<Room> result  = new HashSet<>();
        for (Long r : lr) {
            Optional<Room> optRoom = roomRepo.findById(r);
            if(optRoom.isPresent() && optRoom.get().getIsEmpty().equalsIgnoreCase("Đã SD") ){
                result.add(optRoom.get());
            }
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
        Rental rental = new Rental();
        List<Rental> lrent = renRepo.findByGuestIDandRoomID(ud,id);
        rental = lrent.get(lrent.size()-1);
        rental.setCheckOutDate(new Date());
//        room.setIsEmpty("Trống");
//        roomRepo.save(room);
//        renRepo.save(rental);
        renRepo.save(rental);
        room.setIsEmpty("Trống");
        roomRepo.save(room);
        Long getDiff = rental.getCheckOutDate().getTime()-rental.getCheckInDate().getTime();
        Long getDaysDiff = TimeUnit.MILLISECONDS.toSeconds(getDiff);
        Double payment = (Math.ceil(Double.parseDouble(getDaysDiff.toString())/86400))*(room.getPriceDay());
        model.addAttribute("Rental",rental);
        model.addAttribute("payment",payment);

        return "paymentkh";
    }

    @GetMapping("/add/{id}/{ud}/payment")
    public  String showResult(@PathVariable(name = "id") Long id,@PathVariable(name = "ud") Long ud , Model model, Principal principal) {
        Room room = new Room();
        Optional<Room> optRoom = roomRepo.findById(id);
        if(optRoom.isPresent()){
            room=optRoom.get();
        }

        Rental rental = new Rental();
        List<Rental> lrent = renRepo.findByGuestIDandRoomID(ud,id);
        rental = lrent.get(lrent.size()-1);


        model.addAttribute("Rental",rental);
        return "trathanhcong";
    }

}
