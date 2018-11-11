package com.skilldistillery.petowner.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pet_owner")
public class PetOwner {
		
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="date_created")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date dateCreated;
	
	@Column(name="date_of_birth")
	private Date dob;
	
	@Column(name="apartment_number")
	private Integer apartmentNumber;
	
	@Column(name="active")
	private boolean isActive;
	
//	@JsonIgnore
	@OneToMany(mappedBy="owner")
	private List<Pet> pets;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getApartmentNumber() {
		return apartmentNumber;
	}

	public void setApartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}


	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
		PetOwner other = (PetOwner) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

	public PetOwner(int id, String firstName, String lastName, Date dateCreated, Date dob, int apartmentNumber,
			boolean active, List<Pet> pets) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateCreated = dateCreated;
		this.dob = dob;
		this.apartmentNumber = apartmentNumber;
		this.isActive = active;
		this.pets = pets;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	@Override
	public String toString() {
		return "PetOwner [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateCreated="
				+ dateCreated + ", dob=" + dob + ", apartmentNumber=" + apartmentNumber + ", active=" + isActive + "]";
	}

	public PetOwner() {
		super();
	}

	public PetOwner(int id, String firstName, String lastName, Date dateCreated, Date dob, int apartmentNumber,
			boolean active) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateCreated = dateCreated;
		this.dob = dob;
		this.apartmentNumber = apartmentNumber;
		this.isActive = active;
	}
	
	
	
}
