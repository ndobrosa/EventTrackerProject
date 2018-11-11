package com.skilldistillery.petowner.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.petowner.entities.Pet;
import com.skilldistillery.petowner.services.PetService;

@RestController
@RequestMapping("api")
public class PetController {
	
	@Autowired
	PetService petService;
	
	@RequestMapping(path="pets", method=RequestMethod.GET)
	public List<Pet> index() {
		return petService.showAllPets();
	}
	
	@RequestMapping(path="pets/{id}", method=RequestMethod.GET)
	public Pet show(@PathVariable("id") int id, HttpServletResponse resp) {
		return petService.findById(id);
	}

	
	
}
