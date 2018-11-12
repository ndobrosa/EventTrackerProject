## Event Tracker Project

### Overview
This project is back-end for a RESTful web app that allows an apartment complex to keep track of pets and their owners.

The database contains two tables - one holding information about pets on the property and the other holding info about the owners. The backend is designed to have two of each - JPA entities, repositories, service interfaces and implementation classes, and controllers.
JPA Repository methods allow the user to create, update, edit (put and patch), and delete pets and pet owners. In addition, the user can do multiple other searches, such as search pet by rent range, move in date, etc.

Controller paths currently work in Postman. Over the next couple of weeks, I intend to develop the front-end using JavaScript, Angular, HTML and CSS.

### Pet Owner Functionality
In the database, pet_owner table has the following fields: id (number, primary key), first_name (text), last_name (text), date_of_birth (date), apartment_number(number), and active (boolean).

A user can create, update, replace, view one, view all, or delete pet owners.
A user can also set and get owners who are active (currently live on the property with pets).

#### Unimplemented methods
Repository methods also exist that would allow the user to  search for pet owners by keyword contained in first name or last name, find users by date of birth, find users by date created, and find users by apartment number. However, none of these methods have been implemented in the service and controller, so user has no access to them. The next method I intend to implement is to search by apartment number, as it makes business sense (e.g. in case of neighbor complaints).

### Pet Functionality
In the database, pet_owned table has the following fields: id (number, primary key), owner_id (number, foreign key to pet_owner), name (text), animal_type (text - e.g. 'DOG'), breed(text), age(number), is_on_property(boolean), movein_date(date), moveout_date(date), rent(number).

A user can create, update, replace, view one, view all, or delete pets.
In addition, a user can also look up all pets owned by a certain owner using owner's id. They can  also search by animal type (e.g. all dogs on property). A user can look up all pets that moved in, as well as all pets that moved out in a user specified date range. Finally, they can also retrieve a list of all pets in a certain pet-rent range where a user provides minimum and maximum.

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

### Stretch goals
This project is work-in-progress and I am likely to continue working on it over the next couple of weekends. The following are the most essential next steps.
* Write tests Service and Controller classes (currently only tested in Postman).
* Add unimplemented methods.
* Develop dynamic front-end using JavaScript.
