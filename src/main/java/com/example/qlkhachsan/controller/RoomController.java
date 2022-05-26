package com.example.qlkhachsan.controller;


import com.example.qlkhachsan.Repository.RoomRepository;
import com.example.qlkhachsan.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("/quanlyphong")
@AllArgsConstructor

public class RoomController {
    @Autowired
    private RoomRepository repo;

    @GetMapping
    public String showListRoom(Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        List<Room> lr = (List<Room>) repo.findAll();
        model.addAttribute("Rooms",lr);
        return "quanlyphong";
    }
    @GetMapping("/search")
    public String searchRoom(@Param("keyword") String keyword,Model model,Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        keyword = keyword.trim();
        List<Room> lr = repo.findAll();
        List<Room> result  = new ArrayList<Room>();
        for (Room r : lr) {
            if(r.getRoom_id().toString().contains(keyword) ||
                    r.getType().toLowerCase().contains(keyword.toLowerCase()) ||
                    r.getIsEmpty().toLowerCase().contains(keyword.toLowerCase())||
                    r.getPriceDay().toString().contains(keyword)) {
                result.add(r);
            }
        }
        model.addAttribute("Rooms",result);
        return "quanlyphong";
    }
    @GetMapping("/add")
    public  String showAddRoom(Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        model.addAttribute("Room", new Room() );
        return "themphong";
    }

    @PostMapping("/add")
    public  String addRoom(Room room) {
        repo.save(room);
        return "redirect:/quanlyphong";
    }

    @GetMapping("/edit/{id}")
    public  String showEditRoom(@PathVariable(name = "id") Long id, Model model, Principal principal) {
        String message = principal.getName() ;
        model.addAttribute("message1", message);
        Room room = new Room();
        Optional<Room> optRoom = repo.findById(id);
        if(optRoom.isPresent()){
            room = optRoom.get();
        }
        model.addAttribute("Room", room );
        return "suaphong";
    }
    @PostMapping("/edit/{id}")
    public  String editRoom(@PathVariable(name = "id") Long id,Room room) {
        Optional<Room> optRoom = repo.findById(id);
        if(optRoom.isPresent()){
            Room roomExist = optRoom.get();
            roomExist.setType(room.getType());
            roomExist.setPriceDay(room.getPriceDay());
            roomExist.setIsEmpty(room.getIsEmpty());
            repo.save(roomExist);
        }
        return "redirect:/quanlyphong";
    }
    @GetMapping("/delete/{id}")
    public  String deleteRoom(@PathVariable(name = "id") Long id) {
        repo.deleteById(id);
        return "redirect:/quanlyphong";
    }


}
