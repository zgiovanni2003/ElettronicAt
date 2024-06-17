/**
 * 
 */
$(document).ready(function() {
	
	function loadCategoria(){
		$.ajax({
	        url: 'categoria?action=select',
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
	
	
	function editProdotto(id) {
    var conferma = confirm("Sei sicuro di voler modificare questo prodotto?");
    if (conferma) {
        $.ajax({
            url: 'prodotto?action=selectById&id=' + id,
            type: 'GET',
            success: function(data) {
                // Svuota il contenitore del form
                $('#formContainer').empty();
                
                // Crea un form element
                var form = $('<form method="post" action="prodotto?action=update"></form>');
                var hiddenId = $('<input>').attr({
                    type: 'hidden',
                    name: 'prodotto_id',
                    value: data.prodotto_id
                });
                form.append(hiddenId);

                // Crea input per i campi del prodotto
                var nomeProdotto = $('<input>').attr({
                    type: 'text',
                    name: 'nome_prodotto',
                    value: data.nome_prodotto,
                    placeholder: 'Nome Prodotto'
                });
                var descrizione = $('<textarea></textarea>').attr({
                    name: 'descrizione',
                    placeholder: 'Descrizione'
                }).text(data.descrizione);
                var prezzo = $('<input>').attr({
                    type: 'number',
                    name: 'prezzo',
                    value: data.prezzo,
                    step: '0.01',
                    placeholder: 'Prezzo'
                });

                // Aggiungi input al form
                form.append($('<label>').text('Nome Prodotto: ')).append(nomeProdotto).append('<br>');
                form.append($('<label>').text('Descrizione: ')).append(descrizione).append('<br>');
                form.append($('<label>').text('Prezzo: ')).append(prezzo).append('<br>');

                // Aggiungi il form al contenitore
                $('#formContainer').append(form);

                // Aggiungi un pulsante di submit al form
                var submitButton = $('<button></button>').attr({
                    type: 'submit'
                }).text('Salva Modifiche');
                form.append(submitButton);
            },
            error: function(xhr, status, error) {
                console.error('Errore durante la modifica del prodotto:', status, error);
            }
        });
    }
}


	
	function eliminaProdotto(id) {
        var conferma = confirm("Sei sicuro di voler eliminare questo prodotto?");
        if (conferma) {
            $.ajax({
                url: 'prodotto?action=delete&id=' + id ,
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
            url: 'prodotto?action=select',
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
                    var editLink = $('<a>').attr('href', 'javascript:void(0)').click(function() {
                        editProdotto(item.prodotto_id);
                    }).text('Modifica'); 
                    row.append($('<td>').append(editLink)); 
                    
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
