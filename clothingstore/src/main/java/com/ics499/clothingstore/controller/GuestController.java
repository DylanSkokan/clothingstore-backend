package com.ics499.clothingstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ics499.clothingstore.model.Guest;
import com.ics499.clothingstore.repository.GuestRepository;

/**
 * Guest Controller, used to send information to Angular Front end.
 * 
 * @author Dylan Skokan - Isaiah Cuellar - Tom Waterman - Justin Pham - Kyle
 *         McClernon
 *
 */

@RestController
@RequestMapping("guest")
@CrossOrigin(origins = "http://localhost:4200")
public class GuestController {

	@Autowired
	GuestRepository guestRepository;

	@GetMapping("/test")
	public String test() {
		return "Guest Controller";
	}

	@PostMapping("/save")
	public Guest saveGuest(@RequestBody Guest guest) {
		return guestRepository.save(guest);
	}

	@PostMapping("/saveMany")
	public List<Guest> saveManyGuests(@RequestBody List<Guest> guests) {
		return guestRepository.saveAll(guests);
	}

	@GetMapping("/all")
	public List<Guest> list() {
		return guestRepository.findAll();
	}

	@GetMapping("{id}")
	public Guest get(@PathVariable Long id) {
		return guestRepository.getReferenceById(id);
	}

	@PostMapping("/add")
	public Guest create(@RequestBody final Guest guest) {
		return guestRepository.saveAndFlush(guest);
	}
}
