package com.skilldistillery.petowner.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.petowner.entities.Pet;
import com.skilldistillery.petowner.repositories.PetRepository;

@Service
public class PetServiceImpl implements PetService {
	
	@Autowired
	PetRepository petRepo;
	
	
	@Override
	public List<Pet> showAllPets() {
		
		return petRepo.findAll();
	}

	@Override
	public Pet findById(int id) {
		Optional <Pet> petOpt = petRepo.findById(id);
		Pet pet = null;
		
		if(petOpt.isPresent()) {
			pet = petOpt.get();
		}
		return pet;
	}


}
