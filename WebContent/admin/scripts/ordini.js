/**
 * 
 */

$(document).ready(function() {	
	


    // Funzione per aggiornare la tabella delle categorie
    function aggiornaTabellaProdotto() {
        $.ajax({
            url: 'viewOrdiniAdmin',
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                // Svuota la tabella
                $('#prodotto_table tbody').empty();
                // Aggiungi nuove righe alla tabella con i dati aggiornati
                $.each(data, function(index, item) {
                    var row = $('<tr>'); // Creo una nuova riga per ogni elemento
                    row.append($('<td>').text(item.ordine_id)); // Aggiungo il nome categoria alla prima colonna
                    row.append($('<td>').text(item.data_ordine));
                    row.append($('<td>').text(item.nome_prodotto));
                    row.append($('<td>').text(item.prezzo_tot));
                    row.append($('<td>').text(item.quantity));
                    row.append($('<td>').text(item.email));
                    row.append($('<td>').text(item.name));
                    row.append($('<td>').text(item.surname));
                    $('#prodotto_table').append(row); // Aggiungo la riga alla tabella
                });
            },
            error: function(xhr, status, error) {
                console.error('Errore durante il recupero degli ordini:', status, error);
            }
        });
    }
	
    // Carica la tabella delle categorie al caricamento della pagina
    aggiornaTabellaProdotto();
});