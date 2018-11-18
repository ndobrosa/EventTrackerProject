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
	// TODO:
	// * Create and append elements to the data div to display:
	// * Film title (h1) and description (blockquote).
	// * Rating, release year, and length as an unordered list.

	// Fill in info about film

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

}






