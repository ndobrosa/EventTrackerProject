package com.skilldistillery.petowner.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.petowner.entities.Pet;
import com.skilldistillery.petowner.entities.PetOwner;

public interface PetRepository extends JpaRepository<Pet, Integer> {
	List<Pet> findByOwner(PetOwner owner);
	List<Pet> findByOwner_Id(int id);
	List<Pet> findByNameContaining(String name);
	List<Pet> findByAnimalType(String animal);
	List<Pet> findByBreed(String breed);
	List<Pet> findByAge(int age);
	List<Pet> findByIsOnProperty(boolean isResiding);
	
}
