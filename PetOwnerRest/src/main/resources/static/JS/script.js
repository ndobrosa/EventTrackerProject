window.addEventListener('load', function(e) {
	console.log('document loaded');
	init();
});

function init() {

	document.getOwnersForm.show.addEventListener('click', function(e) {
		e.preventDefault(); // keeps refresh from happenning
		getOwners();
	});

	document.getOwnerIdForm.show.addEventListener('click', function(e) {
		e.preventDefault(); // keeps refresh from happenning
		var formId = e.target.parentElement.ownerId.value;

		getPetOwner(formId);
	});

	document.createOwnerForm.create.addEventListener('click', function(e) {
		e.preventDefault(); // keeps refresh from happenning
		var form = e.target.parentElement;
		var owner = {
			firstName : form.fname.value,
			lastName : form.lname.value,
			dob : form.dob.value + "T07:00:00.000+0000",
			apartmentNumber : form.apartment.value,
			active : form.active.value
		}
		createOwner(owner);
	});

}

function createOwner(owner) {

	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/petowners');

	xhr.setRequestHeader("Content-type", "application/json"); // Declare that
	// content type
	// is JSOn

	xhr.onreadystatechange = function() { // called by send method
		console.log(this.readyState);
		if (xhr.readyState === 4) {
			console.log(xhr.status);
			if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
				var data = JSON.parse(xhr.responseText);
				getPetOwner(data.id);
			} else {
				console.log("POST request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};

	var ownerJson = JSON.stringify(owner); // Convert JS object to JSON string

	xhr.send(ownerJson); // In the background it is running
	// onreadystatechange.

	console.log('json: ' + ownerJson); // onreadystatechange is in the
	// backgroun(asynchronous), so this
	// happens instead.
	// getFilm(filmJson.id);

}

function getOwners() {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/petowners/');

	xhr.onreadystatechange = function() {

		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let response = xhr.responseText;
				console.log(response);

				let owners = JSON.parse(response);
				displayAllOwners(owners);
				// getFilmActors(film.id);
			}
		} else {
			console.log("Status: " + xhr.status + ", " + xhr.statusText);
		}
	}
	xhr.send();
}

function displayAllOwners(owners) {
	// console.log('in Display')
	var dataDiv = document.getElementById('petOwner');
	dataDiv.textContent = '';

	var title = document.createElement('h2');
	title.textContent = 'Pet-Owners';
	dataDiv.appendChild(title);

	var ul = document.createElement('ul');

	for (var i = 0; i < owners.length; i++) {
		var li = document.createElement('li');
		li.textContent = "Name: " + owners[i].firstName + " "
				+ owners[i].lastName;
		ul.appendChild(li);

		li = document.createElement('li');
		li.textContent = "ID: " + owners[i].id;
		ul.appendChild(li);

		li = document.createElement('li');
		li.textContent = "Apt no.: " + owners[i].apartmentNumber;
		ul.appendChild(li);

		li = document.createElement('li');
		li.textContent = "Date Of Birth: " + owners[i].dob.substring(0, 10);
		;
		ul.appendChild(li);

		li = document.createElement('br');
		ul.appendChild(li);
	}

	dataDiv.appendChild(ul);

	// Fill in info about actors

}

function getPetOwner(id) {
	console.log('Id: ' + id)
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/petowners/' + id); // Did not work with
	// `api/films/${id}/actors`

	console.log("id: " + `api/petowners/{id}`);
	xhr.onreadystatechange = function() {
		console.log(xhr.status);

		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let response = xhr.responseText;
				console.log(response);
				let owner = JSON.parse(response);
				displayOwner(owner);
				// getPets(owner.Id);
			} else {
				var dataDiv = document.getElementById('petOwner');
				dataDiv.textContent = 'Owner not found';
			}
		} else {
			console.log("Status: " + xhr.status + ", " + xhr.statusText);
		}
	}
	xhr.send();
}

function displayOwner(owner) {
	var dataDiv = document.getElementById('petOwner');
	dataDiv.textContent = '';

	var ul = document.createElement('ul');

	var li = document.createElement('h2');
	li.textContent = owner.firstName + " " + owner.lastName;
	dataDiv.appendChild(li);

	li = document.createElement('li');
	li.textContent = "ID: " + owner.id;
	ul.appendChild(li);

	li = document.createElement('li');
	li.textContent = "Apt no.: " + owner.apartmentNumber;
	ul.appendChild(li);

	li = document.createElement('li');
	li.textContent = "Date Of Birth: " + owner.dob.substring(0, 10);
	;
	ul.appendChild(li);

	li = document.createElement('br');
	ul.appendChild(li);

	dataDiv.appendChild(ul);

	var form = document.createElement('form');
	form.setAttribute('name', 'beginOwnerUpdate');
	form.setAttribute('id', 'beginOwnerUpdate');

	var button = document.createElement('button');
	button.setAttribute('name', 'update');
	button.setAttribute('id', 'updateButton')
	button.innerHTML = 'update';

	form.appendChild(button);
	dataDiv.appendChild(form);

	addPetUpdateListener(owner);
}

function addPetUpdateListener(owner) {

	var button = document.getElementById('updateButton');
	button.addEventListener('click', function(e) {
		e.preventDefault(); // keeps refresh from happenning
		updatePet(owner);
	});

}

function updatePet(owner) {

	document.getElementById('beginOwnerUpdate').style.visibility = "hidden";

	var dataDiv = document.getElementById('petOwner');

	var form = document.createElement('form');
	form.setAttribute('name', 'updateOwnerForm');

	var input = document.createElement('h3');
	input.innerText = "Update owner info:"
	dataDiv.appendChild(input);

	var input = document.createElement('input');
	input.setAttribute('type', 'text');
	input.setAttribute('name', 'fname');
	input.setAttribute('placeholder', 'first name');
	// input.setAttribute('required');
	form.appendChild(input);

	form.appendChild(document.createElement('br'));
	input = document.createElement('input');
	input.setAttribute('type', 'text');
	input.setAttribute('name', 'lname');
	input.setAttribute('placeholder', 'last name');
	// input.setAttribute('required');
	form.appendChild(input);

	form.appendChild(document.createElement('br'));
	// <input type="number" name="apartment" placeholder="Apartment number"
	// required>
	input = document.createElement('input');
	input.setAttribute('type', 'number');
	input.setAttribute('name', 'apartment');
	input.setAttribute('placeholder', 'Apartment number');
	// input.setAttribute('required');
	form.appendChild(input);

	form.appendChild(document.createElement('br'));
	form.appendChild(document.createTextNode("Date of Birth: "));

	input = document.createElement('input');
	input.setAttribute('type', 'date');
	input.setAttribute('name', 'dob');
	input.required = true;
	form.appendChild(input);

	form.appendChild(document.createElement('br'));
	form.appendChild(document.createTextNode("Active: "));

	input = document.createElement('select');
	input.setAttribute('name', 'active');

	var option = document.createElement('option');
	option.setAttribute('value', true);
	option.innerHTML = "Yes";
	input.appendChild(option);

	option = document.createElement('option');
	option.setAttribute('value', false);
	option.innerHTML = "No";
	input.appendChild(option);

	input.appendChild(option);
	form.appendChild(input);

	form.appendChild(document.createElement('br'));
	form.appendChild(document.createElement('br'));

	var button = document.createElement('button');
	button.setAttribute('name', 'executeUpdate');
	button.setAttribute('id', 'executeUpdate')
	button.innerHTML = 'confirm update';
	form.appendChild(button);
	form.appendChild(document.createElement('br'));

	dataDiv.appendChild(form);
	
	addPetUpdateExecuteListener(owner);

}

function addPetUpdateExecuteListener(owner) {
	var button = document.getElementById('executeUpdate');

	var xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/petowners/' + owner.id);

	xhr.setRequestHeader("Content-type", "application/json");

	button.addEventListener('click', function(e) {
		e.preventDefault(); // keeps refresh from happenning
		console.log("inside");
		var form = e.target.parentElement;
		var owner = {
		firstName : form.fname.value,
		lastName : form.lname.value,
		dob : form.dob.value + "T07:00:00.000+0000",
		apartmentNumber : form.apartment.value,
		active : form.active.value
		}
		createOwner(owner);
	});
}
