package com.skilldistillery.petowner.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.petowner.entities.Pet;
import com.skilldistillery.petowner.entities.PetOwner;
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
		Optional<Pet> petOpt = petRepo.findById(id);
		Pet pet = null;

		if (petOpt.isPresent()) {
			pet = petOpt.get();
		}
		return pet;
	}

	@Override
	public Pet create(Pet pet) {
		return petRepo.saveAndFlush(pet);
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		Optional<Pet> opt = petRepo.findById(id);

		if (opt.isPresent()) {
			petRepo.deleteById(id);

			opt = petRepo.findById(id);

			if (!opt.isPresent()) {
				deleted = true;
			}
		}
		return deleted;
	}
	
	
	@Override
	// put
	public Pet update(Pet updatedPet, int id) {
		Pet originalPet = null;
		Optional<Pet> opt = petRepo.findById(id);

		if (opt.isPresent()) {
			originalPet = opt.get();

//			if (updatedPet.getApartmentNumber() != null) {
//				originalPet.setApartmentNumber(updatedPet.getApartmentNumber());
//			}
			
			if(updatedPet.getAge() != null) {
				originalPet.setAge(updatedPet.getAge());
			}
			if(updatedPet.getAnimalType() != null) {
				originalPet.setAnimalType(updatedPet.getAnimalType());
			}
			if(updatedPet.getBreed() != null) {
				originalPet.setBreed(updatedPet.getBreed());
			}
			if(updatedPet.getMoveInDate() != null) {
				originalPet.setMoveInDate(updatedPet.getMoveInDate());
			}
			if(updatedPet.getMoveOutDate() != null) {
				originalPet.setMoveOutDate(updatedPet.getMoveOutDate());
			}
			if(updatedPet.getName() != null) {
				originalPet.setName(updatedPet.getName());
			}
			if(updatedPet.getOwner() != null) {
				originalPet.setOwner(updatedPet.getOwner());
			}
			if(updatedPet.getRent() != null) {
				originalPet.setRent(updatedPet.getRent());
			}
			
			petRepo.saveAndFlush(originalPet);
		}
		
		return originalPet;
	}
	
	
	@Override
	public Pet replace(Pet updatedPet, int id) {
		Pet originalPet = null;
		Optional<Pet> opt = petRepo.findById(id);
		
		if (opt.isPresent()) {
			originalPet = opt.get();
			
//			if (updatedPet.getApartmentNumber() != null) {
//				originalPet.setApartmentNumber(updatedPet.getApartmentNumber());
//			}
			
				originalPet.setAge(updatedPet.getAge());
				originalPet.setAnimalType(updatedPet.getAnimalType());
				originalPet.setBreed(updatedPet.getBreed());
				originalPet.setMoveInDate(updatedPet.getMoveInDate());
				originalPet.setMoveOutDate(updatedPet.getMoveOutDate());
				originalPet.setName(updatedPet.getName());
				originalPet.setOwner(updatedPet.getOwner());
				originalPet.setRent(updatedPet.getRent());
			
			petRepo.saveAndFlush(originalPet);
		}
		
		return originalPet;
	}

	@Override
	public List<Pet> findByOwner(int id) {
		List<Pet> petsByOwner = petRepo.findByOwner_Id(id);

		return petsByOwner;
	}

	@Override
	public List<Pet> findByType(String type) {
		List<Pet> petsByType = petRepo.findByAnimalType(type);
		return petsByType;
	}

	@Override
	// findByIsOnProperty
	public List<Pet> getPetsOnProperty(boolean isResiding) {
		List<Pet> petsOnProperty = petRepo.findByIsOnProperty(isResiding);

		return petsOnProperty;
	}

	@Override
//	List<Pet> findByMoveInDateBetween(Date begin, Date end);
	public List<Pet> findByMoveInDate(Date begin, Date end) {
		List<Pet> petsByMoveInDateRange = petRepo.findByMoveInDateBetween(begin, end);
		return petsByMoveInDateRange;
	}

	@Override
//	List<Pet> findByMoveOutDateBetween(Date begin, Date end);
	public List<Pet> findByMoveOutDate(Date begin, Date end) {
		List<Pet> petsByMoveOutDateRange = petRepo.findByMoveOutDateBetween(begin, end);
		return petsByMoveOutDateRange;
	}

	@Override
//	List<Pet> findByRentBetween(int min, int max);
	public List<Pet> findByRent(int min, int max) {
		List<Pet> petsByRent = petRepo.findByRentBetween(min, max);
		return petsByRent;
	}

}
