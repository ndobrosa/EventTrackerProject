package com.skilldistillery.petowner.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.petowner.entities.PetOwner;


class PetOwnerTests {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private PetOwner petOwner;

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
		petOwner = em.find(PetOwner.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		petOwner = null;
	}
	
	@Test
	void test_retrieved_PetOwner_all_fields_mapping() {
		assertEquals(1, petOwner.getId());
		assertEquals("Jason", petOwner.getFirstName());
		assertEquals("Melody", petOwner.getLastName());
		assertEquals("2018-11-09 19:46:04", petOwner.getDateCreated().toString().substring(0, 19));
		assertEquals("1960-11-11", petOwner.getDob().toString().substring(0, 10));
		assertEquals(23, petOwner.getApartmentNumber());
		assertEquals(true, petOwner.isActive());
		assertEquals(3, petOwner.getPets().size());
	}
	
}
