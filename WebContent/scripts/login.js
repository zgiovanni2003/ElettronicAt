/**
 * 
 */
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
	    document.getElementById('typeEmailX-2').addEventListener('input', validateEmail);
	    document.getElementById('typePasswordX-2').addEventListener('input', validatePassword);
	    document.getElementById('login-form').addEventListener('submit', function(event) {
		    // Previene il comportamento predefinito del form di inviare la richiesta
		    event.preventDefault();
		    
		    // Effettua la validazione dei campi
		    let isEmailValid = validateEmail();
		    let isPasswordValid = validatePassword();
		
		    // Se entrambi i campi sono validi, esegui l'invio del form
		    if (isEmailValid && isPasswordValid) {
		      this.submit();
		    }
	  	});
	});

  

