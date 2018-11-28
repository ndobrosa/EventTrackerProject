import { PetOwnerService } from './../pet-owner.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PetOwner } from '../models/pet-owner';

@Component({
  selector: 'app-pet-owner-list',
  templateUrl: './pet-owner-list.component.html',
  styleUrls: ['./pet-owner-list.component.css']
})
export class PetOwnerListComponent implements OnInit {
  petOwners: PetOwner[] = [];
  selected: PetOwner = null;
  newOwner: PetOwner = new PetOwner();
  editOwner: PetOwner = null;
  constructor(private petOwnerService: PetOwnerService, private currentRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    this.loadPetowners();
  }
  loadPetowners() {
    this.petOwnerService.index().subscribe(
      data => {
        this.petOwners = data;
      },
      err => {
        console.error('Observer got an error: ' + err);
      }
    );
  }

  displayOwner(petowner: PetOwner) {
    this.selected = petowner;
  }

  addOwner() {
    this.petOwnerService.create(this.newOwner).subscribe(
      data => {
        this.newOwner = new PetOwner();
        this.loadPetowners();
      },
      err => {
        console.error('pet-owner-list.component.ts got an error: ' + err);
      }
    );
  }

  cancelEdit() {
    this.editOwner = null;
    this.loadPetowners();
  }

  updateOwner(petOwner: PetOwner) {
    this.petOwnerService.update(petOwner).subscribe(
      data => {
        this.loadPetowners();
        this.editOwner = null;
      },
      err => {
          console.error('Observer got an error: ' + err) ;
      }
    );
    this.selected = Object.assign({}, petOwner);
  }

  displayTable() {
    this.selected = null;
  }

  deleteOwner(id: number) {
    console.log(id);
    this.petOwnerService.destroy(id).subscribe(
      data => {
        this.loadPetowners();
      },
      err => {
        console.error('Observer got an error' + err);
      }
    );
  }

  setEditOwner() {
    this.editOwner = Object.assign({}, this.selected);
  }
}
