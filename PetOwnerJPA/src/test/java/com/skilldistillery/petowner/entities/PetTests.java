package com.skilldistillery.petowner.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.petowner.entities.Pet;

class PetTests {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Pet pet;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("petowner");
	}
	

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		pet = em.find(Pet.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pet = null;
	}
	@Test
	void test_retrieved_PetOwner_is_mapped_correctly_for_all_fields()  {
		assertEquals(1, pet.getId());
		assertEquals(1, pet.getOwner().getId());
		assertEquals("Leo", pet.getName());
		assertEquals("DOG", pet.getAnimalType());
		assertEquals("Shepherd Mix", pet.getBreed());
		assertEquals(1, pet.getAge());
		assertEquals(true, pet.isOnProperty());
		assertEquals(50, pet.getRent());
		assertEquals("2016-11-01", pet.getMoveInDate().toString().substring(0, 10));
		assertNull(pet.getMoveOutDate());
	}

}
