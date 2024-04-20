package main;

import connection.ConnectionDB;
import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Raffle_Book extends Application {
   
    public static Connection connection = null;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXMLprincipal.fxml"));       
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();
        
        
    }
    
    public static void main(String[] args) {
        
         launch(args);
        try{
            ConnectionDB conn;
            conn = new ConnectionDB();
            
            connection = conn.getConnection();
            
            
        } catch (Exception ex){
                System.out.println("Error en la conexión");
            }
  
    }
  
}
