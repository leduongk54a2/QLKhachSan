package com.example.qlkhachsan.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private  Long room_id;


    @Column(name = "type")
    private String type;

    @Column(name = "priceDay")
    private Double priceDay;

    @Column(name = "isEmpty")
    private String isEmpty;


}
