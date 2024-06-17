package it.zgiovanni2003.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdottiDAO {

    private Database_Manager db;

    public ProdottiDAO() {
        db = new Database_Manager();
    }

    public int addProdotto(Prodotto prodotto) {
        String query = "INSERT INTO `prodotti` (`prodotto_id`, `nome_prodotto`, `descrizione`, `prezzo`, `img`, `categoria_id`) "
                + "VALUES (NULL, ?, ?, ?, ?, ?)";
        String[] params = { 
            prodotto.getNomeProdotto(), 
            prodotto.getDescrizione(), 
            String.valueOf(prodotto.getPrezzo()), 
            prodotto.getImg(), 
            prodotto.getNomeCategoria() // Assuming categoria_id is stored in nome_categoria temporarily
        };
        return db.toPost(query, params);
    }

    public List<Prodotto> getProdotti() {
        String query = "SELECT prodotti.prodotto_id, prodotti.nome_prodotto, prodotti.descrizione, prodotti.prezzo, prodotti.img, categorie.nome_categoria "
                + "FROM prodotti LEFT JOIN categorie ON prodotti.categoria_id = categorie.categoria_id";
        String[] params = {};
        ResultSet resultSet = db.toGet(query, params);
        List<Prodotto> prodotti = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Prodotto prodotto = new Prodotto(
                        resultSet.getInt("prodotto_id"),
                        resultSet.getString("nome_prodotto"),
                        resultSet.getString("descrizione"),
                        resultSet.getDouble("prezzo"),
                        resultSet.getString("img"),
                        resultSet.getString("nome_categoria")
                );
                prodotti.add(prodotto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prodotti;
    }

    public String getProdottoImg(String id) {
        String query = "SELECT img FROM prodotti WHERE prodotti.prodotto_id = ?";
        String[] params = { id };
        ResultSet result = db.toGet(query, params);
        try {
            if (result.next()) {
                return result.getString("img");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public Prodotto getProdottoById(String id) {
        String query = "SELECT prodotti.prodotto_id, prodotti.nome_prodotto, prodotti.descrizione, prodotti.prezzo, prodotti.img, categorie.nome_categoria "
                + "FROM prodotti LEFT JOIN categorie ON prodotti.categoria_id = categorie.categoria_id WHERE prodotti.prodotto_id = ?";
        String[] params = { id };
        ResultSet resultSet = db.toGet(query, params);
        Prodotto prodotto = null;

        try {
            if (resultSet.next()) {
                prodotto = new Prodotto(
                        resultSet.getInt("prodotto_id"),
                        resultSet.getString("nome_prodotto"),
                        resultSet.getString("descrizione"),
                        resultSet.getDouble("prezzo"),
                        resultSet.getString("img"),
                        resultSet.getString("nome_categoria")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prodotto;
    }
    
    
    public int updateProdotto(Prodotto prodotto) {
        String query = "UPDATE prodotti SET nome_prodotto = ?, descrizione = ?, prezzo = ? WHERE prodotto_id = ?";
        String[] params = { 
            prodotto.getNomeProdotto(), 
            prodotto.getDescrizione(), 
            String.valueOf(prodotto.getPrezzo()), 
            String.valueOf(prodotto.getProdottoId())
        };
        return db.toPost(query, params);
    }

    
    
    public int deleteProdotto(String id) {
        String query = "DELETE FROM prodotti WHERE prodotti.prodotto_id = ?";
        String[] params = { id };
        return db.toPost(query, params);
    }
}
