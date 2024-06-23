/**
 * 
 */
$(document).ready(function() {	
	
	function addCart(id) {
	    var conferma = confirm("Sei sicuro di voler aggiungere questo prodotto?");
	    if (conferma) {
	        $.ajax({
	            url: 'cart?action=add&id=' + id + '&quantity=1',
	            type: 'GET',
	            success: function(data) {
	                // Aggiorna la tabella dopo l'aggiunta
	                aggiornaTabellaProdotto();
	            },
	            error: function(xhr, status, error) {
	                console.error('Errore durante l\'aggiunta al carrello:', status, error);
	            }
	        });
	    }
	}

    // Funzione per aggiornare la tabella delle categorie
	function aggiornaTabellaProdotto() {
	    $.ajax({
	        url: 'admin/prodotto?action=select',
	        type: 'GET',
	        dataType: 'json',
	        success: function(data) {
	            // Svuota il contenitore delle card
	            $('#card_container').empty();
	            // Aggiungi nuove card con i dati aggiornati
	            $.each(data, function(index, item) {
	            	var divCols= $('<div>').addClass('col');
	                var card = $('<div>').addClass('card').css('width', '18rem');
	                var imgSrc = 'images/load/' + item.img;
	                var img = $('<img>').addClass('card-img-top').attr('src', imgSrc).attr('alt', 'Card image cap');
	                var cardBody = $('<div>').addClass('card-body');
	                var cardTitle = $('<h5>').addClass('card-title').text(item.nome_prodotto);
	                var cardText = $('<p>').addClass('card-text').text(item.descrizione);
	                var cardPrice = $('<p>').addClass('card-text').text('Prezzo: \u20ac' + item.prezzo);
	                var addToCartButton = $('<a>').attr('href', 'javascript:void(0)').addClass('btn btn-primary').click(function() {
	                    addCart(item.prodotto_id);
	                }).text('Aggiungi al Carrello');
	                
	                cardBody.append(cardTitle, cardText, cardPrice, addToCartButton);
	                card.append(img, cardBody);
	                divCols.append(card);
	                $('#card_container').append(divCols); // Aggiungi la card al contenitore
	            });
	        },
	        error: function(xhr, status, error) {
	            console.error('Errore durante il recupero dei prodotti:', status, error);
	        }
	    });
	}

	
    // Carica la tabella delle categorie al caricamento della pagina
    aggiornaTabellaProdotto();
});
