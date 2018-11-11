package com.skilldistillery.petowner.services;

import java.util.List;

import com.skilldistillery.petowner.entities.Pet;

public interface PetService {

	List<Pet> showAllPets();

	Pet findById(int id);

}
