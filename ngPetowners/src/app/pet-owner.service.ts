import {HttpHeaders, HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/internal/operators';
import { PetOwner } from './models/pet-owner';

@Injectable({
  providedIn: 'root'
})
export class PetOwnerService {
  petOwners: PetOwner[] = [];
  private baseUrl = 'http://localhost:8080/';
  private url = this.baseUrl + 'api/petowners';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      // 'Authorization': 'my-auth-token'
    })
  };


  constructor(private http: HttpClient) { }


  index() {
    return this.http.get<PetOwner[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error retrieving Todo list');
      })
   );
  }

  show(id) {
    return this.http.get<PetOwner>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error retrieving Todo list');
      })
    );
  }




  update(petOwner: PetOwner) {
    // if (petOwner.completed) {
    //   petOwner.completeDate = this.datePipe.transform(Date.now(), 'shortDate');
    // } else {
    //   petOwner.completeDate = '';
    // }

    return this.http.patch<PetOwner>(this.url + '/' + petOwner.id, petOwner, this.httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('todoService.update(): Error updating Todo');
      })
    );
  }

  create(petOwner: PetOwner) {
    // petOwner.completed = false;
    // petOwner.description = '';
    // petOwner.completeDate = '';

    return this.http.post<PetOwner>(this.url, petOwner, this.httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('todoService.create(): Error adding new Todo');
      })
   );
  }

  destroy(id: number) {
  return this.http.delete(this.url + '/' + id).pipe(
    catchError((err: any) => {
      console.log(err);
      return throwError('todoService.destroy(): Error deleting Todo with id: ' + id);
    })
  );
   }
}
