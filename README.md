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
* GET: api/pets - view all pet owners.
* GET: api/pets/{id}  - view a single pet owner, use id values 1-4
* POST: api/pets - create a new pet
*
*
*
*
*
*
*

#### Unimplemented methods
Repository methods also exist that would allow the user to search for pet owners by keyword contained in the first name or last name, find users by date of birth, find users by date created, and find users by apartment number. However, none of these methods have been implemented in the service and controller, so the user has no access to them. The next method I intend to implement is to search by apartment number, as it makes business sense (e.g., in case of neighbor complaints).



### Pet Functionality
In the database, pet_owned table has the following fields: id (number, primary key), owner_id (number, foreign key to pet_owner), name (text), animal_type (text - e.g. 'DOG'), breed(text), age(number), is_on_property(boolean), movein_date(date), moveout_date(date), rent(number).

A user can create, update, replace, view one, view all, or delete pets.
Also, a user can also look up all pets owned by a particular owner using owner's id. They can also search by animal type (e.g., all dogs on property). A user can look up all pets that moved in, as well as all pets that moved out in a user-specified date range. Finally, they can also retrieve a list of all pets in a certain pet-rent range where a user provides minimum and maximum.

#### Routes
* GET: api/pets
  - view all pets.
* GET: api/pets/{id}  
  - view a single pet, use id values 1-6
* POST: api/pets
  - create a new pet
* DELETE: pets/{id}
  - deletes a pet
* PATCH: pets/{id}
  - replaces only fields that do not equal null
* PATCH: pets/{id}
  - replaces all fields in the pet object. Make sure to provide an Owner input with id.
  - moveout_date, rent, and age are not required (can be null).
*
*
*
*
*
*

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
