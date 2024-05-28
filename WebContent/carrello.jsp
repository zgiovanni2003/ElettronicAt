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
	
	function deleteCart(id) {
	    var conferma = confirm("Sei sicuro di voler elimir questo prodotto dal carrello?");
	    if (conferma) {
	        $.ajax({
	            url: 'deleteToCart?id=' + id ,
	            type: 'GET',
	            success: function(data) {
	                // Aggiorna la tabella dopo l'aggiunta
	                aggiornaTabellaProdotto();
	            },
	            error: function(xhr, status, error) {
	                console.error('Errore durante l\'eliminazione dal carrello:', status, error);
	            }
	        });
	    }
	}

    // Funzione per aggiornare la tabella delle categorie
    function aggiornaTabellaProdotto() {
        $.ajax({
            url: 'viewCart',
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                // Svuota la tabella
                $('#prodotto_table tbody').empty();
                // Aggiungi nuove righe alla tabella con i dati aggiornati
                $.each(data, function(index, item) {
                    var row = $('<tr>'); // Creo una nuova riga per ogni elemento
                    row.append($('<td>').text(item.nome_prodotto)); // Aggiungo il nome categoria alla prima colonna
                    row.append($('<td>').text(item.descrizione));
                    row.append($('<td>').text(item.prezzo));
                    row.append($('<td>').text(item.nome_categoria));
                    row.append($('<td>').text(item.quantity));
                    var deleteLink = $('<a>').attr('href', 'javascript:void(0)').click(function() {
                        deleteCart(item.prodotto_id);
                    }).text('Elimina dal Carrello'); // Creo un link "Elimina" per l'id categoria
                    row.append($('<td>').append(deleteLink)); // Aggiungo il link alla seconda colonna
                    $('#prodotto_table').append(row); // Aggiungo la riga alla tabella
                });
            },
            error: function(xhr, status, error) {
                console.error('Errore durante il recupero delle categorie:', status, error);
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
	<table id="prodotto_table">
        <thead>
            <tr>
                <th>Nome Prodotto</th>
                <th>Descrizione</th>
                <th>Prezzo</th>
                <th>Nome Categoria</th>
                <th>Quantità</th>
                <th>Elimina</th>
            </tr>
        </thead>
        <tbody>
            
        </tbody>
    </table>
	<%@ include file="components/footer.jsp" %>
</body>
</html>