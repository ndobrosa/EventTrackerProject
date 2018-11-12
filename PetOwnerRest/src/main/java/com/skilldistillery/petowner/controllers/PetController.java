package com.skilldistillery.petowner.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.petowner.entities.Pet;
import com.skilldistillery.petowner.services.PetOwnerService;
import com.skilldistillery.petowner.services.PetService;

@RestController
@RequestMapping("api")
public class PetController {
	
	@Autowired
	PetService petService;
	
	@Autowired
	PetOwnerService petOwnerService;
	
	// Retrieve all pets from the database.
	@RequestMapping(path="pets", method=RequestMethod.GET)
	public List<Pet> index() {
		return petService.showAllPets();
	}
	
	// Retrieve a single pet from the database
	@RequestMapping(path="pets/{id}", method=RequestMethod.GET)
	public Pet show(@PathVariable("id") int id, HttpServletResponse resp) {
		Pet pet = petService.findById(id);
		if(pet != null) {
			resp.setStatus(200);
		}
		else {
			resp.setStatus(404);
		}
			
		return pet;
	}
	
	// Creates a pet in the database
	@PostMapping("pets")
	public String create(@RequestBody Pet newPet, HttpServletRequest req, HttpServletResponse resp) {

		newPet = petService.create(newPet);
		String responseBody = null;

		if (newPet.getId() != 0) {
			resp.setStatus(201);
			String newResourceUrl = req.getRequestURL().toString() + "/" + newPet.getId();
			resp.setHeader("Location", newResourceUrl);
			responseBody = "{ \"result\": \"created\", \"id\":" + newPet.getId() + ",";
			responseBody += "\"url\":\"" + newResourceUrl + "\"}";
		} else {
			responseBody = "\"result\": \"failed\"";
			resp.setStatus(406);
		}

		return responseBody;
	}

	// removes a pet from the DB
	@DeleteMapping("pets/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest req, HttpServletResponse resp) {

		String responseBody = null;
		
		if (petService.delete(id)) {
			responseBody = "{ \"result\": \"deleted\"}";
		} else {
			responseBody = "{ \"result\": \"Deletion failed\"}";
		}

		return responseBody;
	}
	
	// patches a pet
	@PatchMapping("pets/{id}")
	public String update(@RequestBody Pet updatedPet, @PathVariable("id") int id, HttpServletRequest req,
			HttpServletResponse resp) {
		Pet originalPetOwner = petService.findById(id);
		String responseBody = null;

			if (originalPetOwner != null && petService.update(updatedPet, id) != null) {

				resp.setStatus(200);
				String newResourceUrl = req.getRequestURL().toString();
				resp.setHeader("Location", newResourceUrl);
				responseBody = "{ \"result\": \"patched\",";
				responseBody += "\"url\":\"" + newResourceUrl + "\"}";
			} else {
				responseBody = "\"result\": \"failed\"";
				resp.setStatus(406);
			}
		return responseBody;
	}
	
	// puts a new pet instead of the old one, keeps the same id. Make sure to also provide owner object with an existing id in Postman.
	@PutMapping("pets/{id}")
	public String replace(@RequestBody Pet updatedPet, @PathVariable("id") int id, HttpServletRequest req,
			HttpServletResponse resp) {
		
		Pet originalPet = petService.findById(id);
		String responseBody = null;
		
		if (originalPet != null && petService.replace(updatedPet, id) != null) {
			resp.setStatus(200);
			String newResourceUrl = req.getRequestURL().toString();
			resp.setHeader("Location", newResourceUrl);
			responseBody = "{ \"result\": \"replaced\",";
			responseBody += "\"url\":\"" + newResourceUrl + "\"}";
		} else {
			responseBody = "\"result\": \"failed\"";
			resp.setStatus(406);
		}
		
		return responseBody;
	}
	
	// finds all pets owned by a certain owner
	@GetMapping("pets/petowner/{id}")
	public List<Pet> findByOwner(@PathVariable("id") int id, HttpServletRequest req,
			HttpServletResponse resp) {
		if(petOwnerService.findById(id) != null) {
			resp.setStatus(200);
		}
		else {
			resp.setStatus(404);
		}
		
		return petService.findByOwner(id);
	}
	
	// e.g. find all pets that are cats
	@GetMapping("pets/type/{type}")
	public List<Pet> findByAnimalType(@PathVariable("type") String type, HttpServletRequest req,
			HttpServletResponse resp) {
		return petService.findByType(type);
	}
	
	//getPetsOnProperty
	@GetMapping("pets/active/{isResiding}")
	public List<Pet> findByAnimalsOnProperty(@PathVariable("isResiding") boolean isResiding, HttpServletRequest req,
			HttpServletResponse resp) {
		return petService.getPetsOnProperty(isResiding);
	}
	
	// retrieves all pets that moved in during a certain period
	@GetMapping("pets/movein/{from}/{until}")
	public List<Pet> findByMoveInDate(@PathVariable("from") String fromString,@PathVariable("until") String untilString, HttpServletRequest req,
			HttpServletResponse resp) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date from = null;
		Date until = null;
		
		try {
			from = sdf.parse(fromString);
			until = sdf.parse(untilString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return petService.findByMoveInDate(from, until);
	}
	
	// retrieves all pets that moved out during a certain period
	@GetMapping("pets/moveout/{from}/{until}")
	public List<Pet> findByMoveOutDate(@PathVariable("from") String fromString,@PathVariable("until") String untilString, HttpServletRequest req,
			HttpServletResponse resp) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date from = null;
		Date until = null;
		
		try {
			from = sdf.parse(fromString);
			until = sdf.parse(untilString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return petService.findByMoveOutDate(from, until);
	}
	//"pets/moveout/{from}/{until}"
	@GetMapping("pets/rent/{minimum}/{maximum}")
	public List<Pet> findByPetRent (@PathVariable("minimum") Integer min,@PathVariable("maximum") Integer max, HttpServletRequest req,
			HttpServletResponse resp) {
		return petService.findByRent(min, max);
	}
}
