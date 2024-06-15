/**
 * 
 */
$(document).ready(function() {
    // Funzione per eliminare una categoria tramite AJAX
    function eliminaCategoria(id) {
        var conferma = confirm("Sei sicuro di voler eliminare questa categoria?");
        if (conferma) {
            $.ajax({
                url: 'deleteCategoria?id=' + id ,
                type: 'GET',
                success: function(data) {
                    // Aggiorna la tabella dopo l'eliminazione
                    aggiornaTabellaCategorie();
                },
                error: function(xhr, status, error) {
                    console.error('Errore durante l\'eliminazione della categoria:', status, error);
                }
            });
        }
    }

    // Funzione per aggiornare la tabella delle categorie
    function aggiornaTabellaCategorie() {
        $.ajax({
            url: 'selectCategoria',
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                // Svuota la tabella
                $('#categoria_table tbody').empty();
                // Aggiungi nuove righe alla tabella con i dati aggiornati
                $.each(data, function(index, item) {
                    var row = $('<tr>'); // Creo una nuova riga per ogni elemento
                    row.append($('<td>').text(item.nome_categoria)); // Aggiungo il nome categoria alla prima colonna
                    var deleteLink = $('<a>').attr('href', 'javascript:void(0)').click(function() {
                        eliminaCategoria(item.categoria_id);
                    }).text('Elimina'); // Creo un link "Elimina" per l'id categoria
                    row.append($('<td>').append(deleteLink)); // Aggiungo il link alla seconda colonna
                    $('#categoria_table').append(row); // Aggiungo la riga alla tabella
                });
            },
            error: function(xhr, status, error) {
                console.error('Errore durante il recupero delle categorie:', status, error);
            }
        });
    }

    // Carica la tabella delle categorie al caricamento della pagina
    aggiornaTabellaCategorie();
});