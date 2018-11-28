export class PetOwner {
  id: number;
  firstName: string;
  lastName: string;
  dateCreated: Date;
  dob: Date;
  apartmentNumber: number;
  isActive: boolean;

  constructor(
    id?: number,
    firstName?: string,
    lastName?: string,
    dateCreated?: Date,
    dateOfBirth?: Date,
    apartmentNumber?: number,
    isActive?
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateCreated = dateCreated;
    this.dob = dateOfBirth;
    this.apartmentNumber = apartmentNumber;
    this.isActive = isActive;
  }
}
