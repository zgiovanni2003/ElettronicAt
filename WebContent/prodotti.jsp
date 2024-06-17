<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function() {	
	
	function addCart(id) {
	    var conferma = confirm("Sei sicuro di voler aggiungere questo prodotto?");
	    if (conferma) {
	        $.ajax({
	            url: 'addToCart?id=' + id + '&quantity=1',
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
	                var imgSrc = '<%= request.getContextPath() %>/images/load/' + item.img;
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

</script>
<title>E-Commerce Tec</title>

</head>
<body>
	<%@ include file="components/header.jsp" %>
	
	<div class="album py-4 ">
	    <div class="container">
		    <div id="card_container" class="row justify-content-center top-buffer g-4">
		        <!-- Le card verranno aggiunte qui dinamicamente -->
		    </div>
		</div>
	</div>

	<%@ include file="components/footer.jsp" %>
</body>
</html>