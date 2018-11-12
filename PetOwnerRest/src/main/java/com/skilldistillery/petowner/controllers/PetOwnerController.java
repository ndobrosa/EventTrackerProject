package com.skilldistillery.petowner.controllers;

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

import com.skilldistillery.petowner.entities.PetOwner;
import com.skilldistillery.petowner.services.PetOwnerService;

@RestController
@RequestMapping("api")
public class PetOwnerController {

	@Autowired
	PetOwnerService petOwnerService;

	//Show all petowners
	@RequestMapping(path = "petowners", method = RequestMethod.GET)
	public List<PetOwner> index() {
		return petOwnerService.showAllOwners();
	}

	// find a single pet owner by id
	@RequestMapping(path = "petowners/{id}", method = RequestMethod.GET)
	public PetOwner show(@PathVariable("id") int id, HttpServletRequest req, HttpServletResponse resp) {
		PetOwner petOwner = petOwnerService.findById(id);

		if (petOwner == null) {
			resp.setStatus(404);
		} else {
			String newResourceUrl = req.getRequestURL().toString();
			resp.setHeader("Location", newResourceUrl);
		}
		return petOwner;
	}

	
	// Insert a new owner
	@PostMapping("petowners")
	public String create(@RequestBody PetOwner newPetOwner, HttpServletRequest req, HttpServletResponse resp) {

		newPetOwner = petOwnerService.create(newPetOwner);
		String responseBody = null;

		if (newPetOwner.getId() != 0) {
			
			// sets a status to 'created' and returns a JSON response with result and a link to get the newly created owner
			resp.setStatus(201);
			String newResourceUrl = req.getRequestURL().toString() + newPetOwner.getId();
			resp.setHeader("Location", newResourceUrl);
			responseBody = "{ \"result\": \"created\", \"id\":" + "/" + newPetOwner.getId() + ",";
			responseBody += "\"url\":\"" + newResourceUrl + "\"}";
		} else {
			responseBody = "\"result\": \"failed\"";
			resp.setStatus(406);
		}

		return responseBody;
	}

	// delete an owner from the DB
	@DeleteMapping("petowners/{id}")
	public String delete(@PathVariable("id") int id, HttpServletRequest req, HttpServletResponse resp) {

		String responseBody = null;
		if (petOwnerService.delete(id)) {
			responseBody = "{ \"result\": \"deleted\"}";
		} else {
			responseBody = "{ \"result\": \"Deletion failed\"}";
		}

		return responseBody;
	}

	// Patch an owner
	@PatchMapping("petowners/{id}")
	public String update(@RequestBody PetOwner updatedPetOwner, @PathVariable("id") int id, HttpServletRequest req,
			HttpServletResponse resp) {
		PetOwner originalPetOwner = petOwnerService.findById(id);
		String responseBody = null;

			if (originalPetOwner != null && petOwnerService.update(updatedPetOwner, id) != null) {

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
	
	// replace an owner (put)
	@PutMapping("petowners/{id}")
	public String replace(@RequestBody PetOwner updatedPetOwner, @PathVariable("id") int id, HttpServletRequest req,
			HttpServletResponse resp) {
		
		PetOwner originalPetOwner = petOwnerService.findById(id);
		String responseBody = null;
		
		if (originalPetOwner != null && petOwnerService.replace(updatedPetOwner, id) != null) {
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
	
	// get owners who live on property (are active)
	@GetMapping("petowners/active")
	public List<PetOwner> getActive() {
		return petOwnerService.findActive();
	}
	
	// set whether an owner is active
	@PutMapping("petowners/{id}/active/{active}")
	public String setActive( @PathVariable("id") int id, @PathVariable("active") boolean active, HttpServletRequest req,
			HttpServletResponse resp) {
		String responseBody = null;
		if(petOwnerService.setActive(id, active) != null) {
			String newResourceUrl = "http://localhost:8083/api/petowners/" + id;
			resp.setHeader("Location", newResourceUrl);
			
			responseBody = "{ \"result\": \"changed\",";
			responseBody += "\"url\":\"" + newResourceUrl + "\"}";
		}
		else {
			responseBody = "\"result\": \"failed\"";
			resp.setStatus(406);
		}
		
		return responseBody;
	}
	
	
	
	
	
	
	
}
