package com.skilldistillery.petowner.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pet_owned")
public class Pet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="owner_id")
	private PetOwner owner;
	
	private String name;
	
	@Column(name="animal_type")
	private String animalType;
	
	private String breed;
	
	private int age;
	
	@Column(name="is_on_property")
	private boolean isOnProperty;

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

	public int getAge() {
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

	@Override
	public String toString() {
		return "Pet [id=" + id + ", owner=" + owner.getFirstName() + " " + owner.getLastName() + ", name=" + name + ", animalType=" + animalType + ", breed="
				+ breed + ", age=" + age + ", isOnProperty=" + isOnProperty + "]";
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
