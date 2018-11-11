package com.skilldistillery.petowner.services;

import java.util.List;

import com.skilldistillery.petowner.entities.PetOwner;

public interface PetOwnerService {
	
	public List<PetOwner> showAllOwners();
	
	public PetOwner findById(int id);

	boolean delete(int id);

	PetOwner update(PetOwner updatedPetOwner, int id);

	PetOwner replace(PetOwner updatedPetOwner, int id);

	List<PetOwner> findActive();

	PetOwner setActive(int id, boolean active);

	PetOwner create(PetOwner petOwner);
}
