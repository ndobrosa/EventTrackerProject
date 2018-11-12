## Event Tracker Project

### Overview
This project is the back-end for a RESTful web app that allows an apartment complex to keep track of pets and their owners.

The database contains two tables - one holding information about pets on the property and the other holding info about the owners. The backend is designed to have two of each - JPA entities, repositories, service interfaces and implementation classes, and controllers.
JPA Repository methods allow the user to create, update, edit (put and patch), and delete pets and pet owners. Also, the user can do multiple other searches, such as search pet by rent range, move in date, etc.

Controller paths currently work in Postman. Over the next couple of weeks, I intend to develop the front-end using JavaScript, Angular, HTML and CSS.

####
Deployed at: http://18.188.120.152:8080/PetOwnerRest/
Use routes to (see under Pet and Pet Owner Functionality) to access data. Front end not yet created.

### Pet Owner Functionality
In the database, pet_owner table has the following fields: id (number, primary key), first_name (text), last_name (text), date_of_birth (date), apartment_number(number), and active (boolean).

A user can create, update, replace, view one, view all, or delete pet owners.
A user can also set and get owners who are active (currently live on the property with pets).

#### Routes
* GET: api/petowners
  - view all pet-owners.
  - http://18.188.120.152:8080/PetOwnerRest/api/petowners

* GET: api/petowners/{id}  
  - view a single owner, use id values 1-4.
  - e.g., http://18.188.120.152:8080/PetOwnerRest/api/petowners/3

* POST: api/petowners
  - create a new owner.

* DELETE: api/petowners/{id}
  - deletes a pet.

* PATCH: petowners/{id}
  - replaces only fields that do not equal null.

* PUT: api/petowners/{id}
  - Replaces all fields in the pet object. Make sure to provide an Owner input with id.
  - Fields moveout_date, rent, and age are not required (can be null).

* GET: petowners/active
  - get owners who currently live on the property (are active).
  - http://18.188.120.152:8080/PetOwnerRest/api/petowners/active

* PUT: petowners/{id}/active/{active}
  - set whether an owner is active.


#### Unimplemented methods
Repository methods also exist that would allow the user to search for pet owners by keyword contained in the first name or last name, find users by date of birth, find users by date created, and find users by apartment number. However, none of these methods have been implemented in the service and controller, so the user has no access to them. The next method I intend to implement is to search by apartment number, as it makes business sense (e.g., in the case of neighbor complaints).



### Pet Functionality
In the database, pet_owned table has the following fields: id (number, primary key), owner_id (number, foreign key to pet_owner), name (text), animal_type (text - e.g. 'DOG'), breed(text), age(number), is_on_property(boolean), movein_date(date), moveout_date(date), rent(number).

A user can create, update, replace, view one, view all, or delete pets.
Also, a user can also look up all pets owned by a particular owner using owner's id. They can also search by animal type (e.g., all dogs on property). A user can look up all pets that moved in, as well as all pets that moved out in a user-specified date range. Finally, they can also retrieve a list of all pets in a certain pet-rent range where a user provides minimum and maximum.

#### Routes
* GET: api/petowners
  - view all pets.
  - http://18.188.120.152:8080/PetOwnerRest/api/petowners

* GET: api/petowners/{id}  
  - view a single pet, use id values 1-6.
  - e.g., http://18.188.120.152:8080/PetOwnerRest/api/petowners/3

* POST: api/pets
  - create a new pet.

* DELETE: api/pets/{id}
  - deletes a pet.

* PATCH: pets/{id}
  - replaces only fields that do not equal null.

* PUT: api/pets/{id}
  - Replaces all fields in the pet object. Make sure to provide an Owner input with id.
  - Fields moveout_date, rent, and age are not required (can be null).

* GET: api/pets/petowner/{id}
  - finds all pets owned by a specific owner
  - e.g., http://18.188.120.152:8080/PetOwnerRest/api/pets/petowner/1

* GET: api/pets/type/{type}
  - find pets that are a certain kind of animal (e.g., see all pets that are cats)
  - e.g., http://18.188.120.152:8080/PetOwnerRest/api/pets/type/cat

* GET: api/pets/active/{isResiding}
  - gets all pets currently living on the property (e.g., for rent collection purposes)
  - e.g., http://18.188.120.152:8080/PetOwnerRest/api/pets/active/true

* GET: api/pets/movein/{from}/{until}
  - retrieves all pets that moved in during a specified period
  - e.g.,  http://18.188.120.152:8080/PetOwnerRest/api/pets/movein/2016-01-01/2018-11-11

* GET: api/pets/moveout/{from}/{until}
  - retrieves all pets that moved out during a specified period
  - currently an empty list
  - e.g., http://18.188.120.152:8080/PetOwnerRest/api/pets/moveout/2016-01-01/2018-11-11

* GET: api/pets/rent/{minimum}/{maximum}
  - retrieves a list of pets within a certain pet-rent range
  - e.g., http://18.188.120.152:8080/PetOwnerRest/api/pets/rent/20/40



#### Unimplemented methods
Similarly to the owners, there are methods inside of the pet repository that have not yet been implemented. These include the ability to find pets by a keyword contained in the name, by breed and by age.

### Technologies used
* RESTful API
* JSON API
* Java
* SQL
* UNIX
* MySQL Workbench
* Spring Boot
* Spring MVC
* Hibernate
* Spring Data JPA
* Gradle
* JUnit Jupiter
* AWS

### Stretch goals
This project is work-in-progress, and I am likely to continue working on it over the next couple of weekends. The following are the essential next steps.
* Write tests Service and Controller classes (currently only tested in Postman).
* Add unimplemented methods.
* Develop dynamic front-end using JavaScript.
