package PH_DAO;


import java.io.IOException;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {
    Properties props=new Properties();
    private static String user;
    private static String password;
    private static String url;
    //Objet Connection
    private static Connection connect;
    //Constructeur privé
    private Connexion(){
        try {
            props.load(new FileInputStream("conf.properties"));
            url=props.getProperty("jdbc.url");
            user=props.getProperty("jdbc.user");
            password=props.getProperty("jdbc.password");
            System.out.println("user: "+user);
           
            connect = DriverManager.getConnection(url, user, password);
            System.out.println("Connection établie avec succés !");
        }
        catch ( SQLException | IOException e) {
            System.out.println("Erreur: "+ e.getMessage());
        }
    }
    //Méthode qui retourne l’instance et la créer si elle n'existe pas
    public static Connection getConnexion(){
        if(connect == null){
            new Connexion();
        }
        return connect;
    }
    public static void closeConnection() throws SQLException {
        if (connect != null) {
            connect.close();
        }
    }
}