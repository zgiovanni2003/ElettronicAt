package it.zgiovanni2003.common;

import java.sql.*;

public class Database_Manager {
    private String URL_mioDB;
    private String driver;
    private Connection connect;
    Database_Manager(String URL_mioDB, String driver){
        this.URL_mioDB=URL_mioDB;
        this.driver=driver;
    }

    public void connectDriver(){       
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException el) {
            System.out.println("Driver non trovato...");
            System.exit(1);
        }
    }

    public void connect_DB(String user,String password){
        //stabilisco la connessione
        System.out.println("Connessione con: "+URL_mioDB);
        connect = null;
        try {
            connect = DriverManager.getConnection(URL_mioDB,user,password);
        } catch (Exception e) {
            System.out.println("Errore nella connessione: "+ e);
            System.exit(1);
        }
    }


    public void toPost(String query, String[] params) {
        try {
            PreparedStatement statement = connect.prepareStatement(query);
            
            for (int i = 0; i < params.length; i++)
                statement.setString(i + 1, params[i]);
            
            int righe = statement.executeUpdate();
            System.out.println("Query eseguita con successo. Righe modificate: " + righe);
        } catch (SQLException ex) {
            System.out.println("Errore : " + ex);
            System.exit(1);
        }
    }


    public ResultSet toGet(String query, String[] params){
        ResultSet result=null;
        try {
        	PreparedStatement statement = connect.prepareStatement(query);
            
            for (int i = 0; i < params.length; i++)
                statement.setString(i + 1, params[i]);
            
            result = statement.executeQuery();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
