/**
 * 
 */
$(document).ready(function() {
	
	function loadCategoria(){
		$.ajax({
	        url: 'selectCategoria',
	        type: 'GET',
	        dataType: 'json', // Specifica il tipo di dati che ci si aspetta di ricevere
	        success: function(data) {
	            $.each(data, function(index, item) {
	                $('#select_categoria').append($('<option>', {
	                    value: item.categoria_id,
	                    text: item.nome_categoria
	                }));
	            });
	        },
	        error: function(xhr, status, error) {
	            console.error('Errore durante il recupero delle categorie:', status, error);
	        }
	    });
	}
	
	
	
	
	function eliminaProdotto(id) {
        var conferma = confirm("Sei sicuro di voler eliminare questo prodotto?");
        if (conferma) {
            $.ajax({
                url: 'deleteProdotto?id=' + id ,
                type: 'GET',
                success: function(data) {
                    // Aggiorna la tabella dopo l'eliminazione
                    aggiornaTabellaProdotto();
                },
                error: function(xhr, status, error) {
                    console.error('Errore durante l\'eliminazione dell prodotto:', status, error);
                }
            });
        }
    }

    // Funzione per aggiornare la tabella delle categorie
    function aggiornaTabellaProdotto() {
        $.ajax({
            url: 'selectProdotto',
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
                    row.append($('<td>').text(item.prezzo))
                    row.append($('<td>').text(item.nome_categoria));
                    ;
                    var deleteLink = $('<a>').attr('href', 'javascript:void(0)').click(function() {
                        eliminaProdotto(item.prodotto_id);
                    }).text('Elimina'); // Creo un link "Elimina" per l'id categoria
                    row.append($('<td>').append(deleteLink)); // Aggiungo il link alla seconda colonna
                    $('#prodotto_table').append(row); // Aggiungo la riga alla tabella
                });
            },
            error: function(xhr, status, error) {
                console.error('Errore durante il recupero delle categorie:', status, error);
            }
        });
    }
	
    loadCategoria();
    // Carica la tabella delle categorie al caricamento della pagina
    aggiornaTabellaProdotto();
});
