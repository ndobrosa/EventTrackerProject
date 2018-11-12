package com.skilldistillery.petowner.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.petowner.entities.Pet;
import com.skilldistillery.petowner.entities.PetOwner;
import com.skilldistillery.petowner.repositories.PetOwnerRepository;
import com.skilldistillery.petowner.repositories.PetRepository;

@Service
public class PetOwnerServiceImpl implements PetOwnerService {

	@Autowired
	PetOwnerRepository petOwnerRepo;
	@Autowired
	PetRepository petRepo;

	//Retrieves a list of all owners
	@Override
	public List<PetOwner> showAllOwners() {

		return petOwnerRepo.findAll();
	}

	// read a single pet owner by id
	@Override
	public PetOwner findById(int id) {
		Optional<PetOwner> petOwnerOpt = petOwnerRepo.findById(id);
		PetOwner petOwner = null;

		if (petOwnerOpt.isPresent()) {
			petOwner = petOwnerOpt.get();
		}

		return petOwner;
	}

	// Insert a new owner
	@Override
	public PetOwner create(PetOwner petOwner) {
		return petOwnerRepo.saveAndFlush(petOwner);
	}

	// delete an owner from the DB
	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		Optional<PetOwner> opt = petOwnerRepo.findById(id);
		if (opt.isPresent()) {
			PetOwner petOwner = opt.get();
			List<Pet> petsOwned = petOwner.getPets();

			// Consider changing with a method insice PetService
			for (Pet pet : petsOwned) {
				petRepo.delete(pet);
			}
			petOwnerRepo.delete(petOwner);

			opt = petOwnerRepo.findById(petOwner.getId());
			if (!opt.isPresent()) {
				deleted = true;
			}
		}

		return deleted;
	}

	// Patch an owner
	@Override
	public PetOwner update(PetOwner updatedPetOwner, int id) {
		PetOwner originalPetOwner = null;
		Optional<PetOwner> opt = petOwnerRepo.findById(id);

		if (opt.isPresent()) {
			originalPetOwner = opt.get();

			if (updatedPetOwner.getApartmentNumber() != null) {
				originalPetOwner.setApartmentNumber(updatedPetOwner.getApartmentNumber());
			}
			if (updatedPetOwner.getDob() != null) {
				originalPetOwner.setDob(updatedPetOwner.getDob());
			}
			if (updatedPetOwner.getFirstName() != null) {
				originalPetOwner.setFirstName(updatedPetOwner.getFirstName());
			}
			if (updatedPetOwner.getLastName() != null) {
				originalPetOwner.setLastName(updatedPetOwner.getLastName());
			}
			petOwnerRepo.saveAndFlush(originalPetOwner);
		}
		
		return originalPetOwner;
	}

	// replace an owner (put)
	@Override
	public PetOwner replace(PetOwner updatedPetOwner, int id) {

		PetOwner originalPetOwner = null;
		Optional<PetOwner> opt = petOwnerRepo.findById(id);

		if (opt.isPresent()) {
			originalPetOwner = opt.get();
			
			originalPetOwner.setApartmentNumber(updatedPetOwner.getApartmentNumber());
			originalPetOwner.setDob(updatedPetOwner.getDob());
			originalPetOwner.setFirstName(updatedPetOwner.getFirstName());
			originalPetOwner.setLastName(updatedPetOwner.getLastName());
			petOwnerRepo.saveAndFlush(originalPetOwner);
		}
		
		return originalPetOwner;
	}
	
	// get owners who live on property (are active)
	@Override
	public List<PetOwner> findActive() {
		return petOwnerRepo.findByIsActive(true);
	}
	
	// set whether an owner is active
	@Override
	public PetOwner setActive(int id, boolean active) {
		Optional<PetOwner> opt = petOwnerRepo.findById(id);
		PetOwner petOwner = null;
		
		if (opt.isPresent()) {
			petOwner = opt.get();
			petOwner.setActive(active);
			
			petOwnerRepo.saveAndFlush(petOwner);
			}
		
		return petOwner;
		}
		
		
	
	
}
