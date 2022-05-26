package com.example.qlkhachsan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "guest")
public class Guest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "guest_id")
	private long guestId;
	
	@Column(name = "guest_name")
	private String guestName;


	@Column(name = "birth")
	private String birth;
	
	@Column(name = "id_card")
	private String idCard;

	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "email")
	private String email;

	@OneToMany(mappedBy = "guest",fetch = FetchType.LAZY)
	private Set<Rental> rentals;





}
