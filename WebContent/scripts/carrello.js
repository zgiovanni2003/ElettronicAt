/**
 * 
 */
$(document).ready(function() {	
	
	function deleteCart(id) {
	    var conferma = confirm("Sei sicuro di voler elimir questo prodotto dal carrello?");
	    if (conferma) {
	        $.ajax({
	            url: 'cart?action=delete&id=' + id ,
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
            url: 'cart?action=view',
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
                console.error('Errore durante il recupero del carrello:', status, error);
            }
        });
    }
	
    // Carica la tabella delle categorie al caricamento della pagina
    aggiornaTabellaProdotto();
});
