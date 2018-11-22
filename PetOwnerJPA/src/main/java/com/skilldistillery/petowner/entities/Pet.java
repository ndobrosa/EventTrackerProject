package com.skilldistillery.petowner.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * Entity mapped according to the table in the relational database
 */
@Entity
@Table(name = "pet_owned")
public class Pet {

	//fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
//	@JsonIgnore commented out. Pets will have an owner mapped, 
//	but the owner will not have pets to avoid recursion.
	@ManyToOne
	@JoinColumn(name="owner_id")
	private PetOwner owner;
	
	private String name;
	
	@Column(name="animal_type")
	private String animalType;
	
	private String breed;
	
	private Integer age;
	
	@Column(name="is_on_property")
	private boolean isOnProperty;
	
	@Column(name="movein_date")
	private Date moveInDate;
	
	@Column(name="moveout_date")
	private Date moveOutDate;
	
	private Integer rent;

	
	
	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PetOwner getOwner() {
		return owner;
	}

	public void setOwner(PetOwner owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isOnProperty() {
		return isOnProperty;
	}

	public void setOnProperty(boolean isOnProperty) {
		this.isOnProperty = isOnProperty;
	}
	public Integer getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public Date getMoveInDate() {
		return moveInDate;
	}

	public void setMoveInDate(Date moveInDate) {
		this.moveInDate = moveInDate;
	}

	public Date getMoveOutDate() {
		return moveOutDate;
	}

	public void setMoveOutDate(Date moveOutDate) {
		this.moveOutDate = moveOutDate;
	}
	

	
	// hashcode and equal;s using id which is a uniqe value
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	
	// to string, no-arg constructor, fully loaded constructor

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", animalType=" + animalType + ", breed=" + breed + ", age=" + age
				+ ", isOnProperty=" + isOnProperty + ", moveInDate=" + moveInDate + ", moveOutDate=" + moveOutDate
				+ ", Rent=" + rent + "]";
	}

	public Pet() {
		super();
	}

	public Pet(int id, PetOwner owner, String name, String animalType, String breed, int age, boolean isOnProperty) {
		super();
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.animalType = animalType;
		this.breed = breed;
		this.age = age;
		this.isOnProperty = isOnProperty;
	}
	
	
}
