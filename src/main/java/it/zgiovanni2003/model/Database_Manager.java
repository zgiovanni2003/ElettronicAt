package it.zgiovanni2003.model;

import java.sql.*;

public class Database_Manager {

    public int toPost(String query, String[] params) {
        int righe = -1;
        Connection connect = null;
        try {
            connect = DriverManagerConnectionPool.getConnection();
            PreparedStatement statement = connect.prepareStatement(query);
            
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            
            righe = statement.executeUpdate();
            System.out.println("Query eseguita con successo. Righe modificate: " + righe);
        } catch (SQLException ex) {
            System.out.println("Errore : " + ex);
            System.exit(1);
        } finally {
            try {
                if (connect != null) {
                    DriverManagerConnectionPool.releaseConnection(connect);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return righe;
    }

    public ResultSet toGet(String query, String[] params){
        ResultSet result = null;
        Connection connect = null;
        try {
            connect = DriverManagerConnectionPool.getConnection();
            PreparedStatement statement = connect.prepareStatement(query);
            
            for (int i = 0; i < params.length; i++) {
                statement.setString(i + 1, params[i]);
            }
            
            result = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connect != null) {
                    DriverManagerConnectionPool.releaseConnection(connect);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
