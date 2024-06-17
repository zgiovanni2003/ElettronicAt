/**
 * 
 */

/**
 * 
 */

  function validateName() {
    let name = document.getElementById('typeName').value.trim();
    let nameError = document.getElementById('name-error');
    let namePattern = /^[A-Za-z]+$/;

    if (name === '') {
      nameError.innerText = 'Inserisci un nome.';
      nameError.classList.remove('d-none');
      return false;
    } else if (!namePattern.test(name)) {
      nameError.innerText = 'Inserisci un nome valido.';
      nameError.classList.remove('d-none');
      return false;
    } else {
      nameError.classList.add('d-none');
      return true;
    }
  }

  function validateSurname() {
    let surname = document.getElementById('typeSurname').value.trim();
    let surnameError = document.getElementById('surname-error');
    let surnamePattern = /^[A-Za-z]+$/;

    if (surname === '') {
      surnameError.innerText = 'Inserisci un cognome.';
      surnameError.classList.remove('d-none');
      return false;
    } else if (!surnamePattern.test(surname)) {
      surnameError.innerText = 'Inserisci un cognome valido.';
      surnameError.classList.remove('d-none');
      return false;
    } else {
      surnameError.classList.add('d-none');
      return true;
    }
  }
  
function validateDate() {
  let inputDate = document.getElementById('typeDate').value.trim();
  let dateError = document.getElementById('date-error');
  let datePattern = /^(\d{2})\/(\d{2})\/(\d{4})$/;

  // Converti la data nel formato "dd/mm/yyyy" se proviene da un input type="date"
  let dateParts = inputDate.split('-'); // Dividi la data per "-"
  if (dateParts.length === 3) {
    inputDate = `${dateParts[2]}/${dateParts[1]}/${dateParts[0]}`; // Formato "dd/mm/yyyy"
  }

  if (inputDate === '') {
    dateError.innerText = 'Inserisci una data.';
    dateError.classList.remove('d-none');
    return false;
  } else if (!datePattern.test(inputDate)) {
    dateError.innerText = 'Inserisci una data nel formato dd/mm/yyyy.';
    dateError.classList.remove('d-none');
    return false;
  } else {
    dateError.classList.add('d-none');
    return true;
  }
}


  // Funzione per validare l'email con pattern
  function validateEmail() {
    let email = document.getElementById('typeEmailX-2').value.trim();
    let emailError = document.getElementById('email-error');
    let emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (email === '') {
      emailError.innerText = 'Inserisci un indirizzo email.';
      emailError.classList.remove('d-none');
      return false;
    } else if (!emailPattern.test(email)) {
      emailError.innerText = 'Inserisci un indirizzo email valido.';
      emailError.classList.remove('d-none');
      return false;
    } else {
      emailError.classList.add('d-none');
      return true;
    }
  }

  // Funzione per validare la password con pattern
  function validatePassword() {
    let password = document.getElementById('typePasswordX-2').value.trim();
    let passwordError = document.getElementById('password-error');
    let passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/; // Almeno 6 caratteri

    if (password === '') {
      passwordError.innerText = 'Inserisci una password.';
      passwordError.classList.remove('d-none');
      return false;
    } else if (!passwordPattern.test(password)) {
      passwordError.innerText = 'La password deve contenere almeno un numero, una lettera minuscola, una lettera maiuscola e essere lunga almeno 8 caratteri.';
      passwordError.classList.remove('d-none');
      return false;
    } else {
      passwordError.classList.add('d-none');
      return true;
    }
  }

  // Aggiungi un ascoltatore di eventi per l'input per validare in tempo reale

	document.addEventListener('DOMContentLoaded', function() {
		document.getElementById('typeName').addEventListener('input', validateName);
	    document.getElementById('typeSurname').addEventListener('input', validateSurname);
	    document.getElementById('typeDate').addEventListener('input', validateDate);
	    document.getElementById('typeEmailX-2').addEventListener('input', validateEmail);
	    document.getElementById('typePasswordX-2').addEventListener('input', validatePassword);
	    document.getElementById('register-form').addEventListener('submit', function(event) {
		    // Previene il comportamento predefinito del form di inviare la richiesta
		    event.preventDefault();
		    
		    // Effettua la validazione dei campi
		    let isNameValid = validateName();
		    let isSurnameValid = validateSurname();
		    let isDateValid = validateDate();

		    let isEmailValid = validateEmail();
		    let isPasswordValid = validatePassword();
		
		    // Se entrambi i campi sono validi, esegui l'invio del form
		    if (isNameValid && isSurnameValid && isDateValid && isEmailValid && isPasswordValid) {
		      this.submit();
		    }
	  	});
	});

  

