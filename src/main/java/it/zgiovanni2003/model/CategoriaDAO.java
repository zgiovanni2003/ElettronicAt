package it.zgiovanni2003.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Database_Manager dbManager;

    public CategoriaDAO() {
        this.dbManager = new Database_Manager();
    }

    public int aggiungiCategoria(Categoria categoria) throws SQLException {
        String query = "INSERT INTO `categorie` (`categoria_id`, `nome_categoria`) VALUES (NULL, ?)";
        String[] params = {categoria.getNomeCategoria()};

        return dbManager.toPost(query, params);
    }

    public int eliminaCategoria(int id) throws SQLException {
        String query = "DELETE FROM categorie WHERE `categorie`.`categoria_id` = ?";
        String[] params = {String.valueOf(id)};

        return dbManager.toPost(query, params);
    }

    public List<Categoria> selezionaCategorie() throws SQLException {
        String query = "SELECT categoria_id, nome_categoria FROM categorie";
        String[] params = {};
        ResultSet resultSet = dbManager.toGet(query, params);
        List<Categoria> categorie = new ArrayList<>();

        while (resultSet.next()) {
            Categoria categoria = new Categoria();
            categoria.setId(resultSet.getInt("categoria_id"));
            categoria.setNomeCategoria(resultSet.getString("nome_categoria"));
            categorie.add(categoria);
        }

        return categorie;
    }
}
