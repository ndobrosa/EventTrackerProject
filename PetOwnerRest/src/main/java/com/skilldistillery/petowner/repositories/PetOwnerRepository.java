package com.skilldistillery.petowner.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.petowner.entities.Pet;
import com.skilldistillery.petowner.entities.PetOwner;

public interface PetOwnerRepository extends JpaRepository<PetOwner, Integer> {
	List<PetOwner> findByFirstNameContainingOrLastNameContaining(String fName, String lName);
	List<PetOwner> findByDateCreated(Date date);
	List<PetOwner> findByDob(Date date);
	List<PetOwner> findByApartmentNumber(int number);
	List<PetOwner> findByIsActive(boolean active);
	PetOwner findByPets_Id(int id);
	
	
	
	
}
