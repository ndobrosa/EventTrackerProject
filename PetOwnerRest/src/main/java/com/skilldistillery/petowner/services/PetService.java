package com.skilldistillery.petowner.services;

import java.util.Date;
import java.util.List;

import com.skilldistillery.petowner.entities.Pet;

public interface PetService {

	List<Pet> showAllPets();

	Pet findById(int id);

	List<Pet> findByRent(int min, int max);

	List<Pet> findByMoveOutDate(Date begin, Date end);

	List<Pet> findByMoveInDate(Date begin, Date end);

	List<Pet> getPetsOnProperty(boolean isResiding);

	List<Pet> findByType(String type);

	List<Pet> findByOwner(int id);

	Pet replace(Pet updatedPet, int id);

	Pet update(Pet updatedPet, int id);

	boolean delete(int id);

	Pet create(Pet pet);

}
