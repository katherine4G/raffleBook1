

package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionDB {
    
    Connection connect = null;
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "RIFAS"; //SYSTEM //RIFA
    private static final String PASSWORD = "300504"; //1234
   
    public ConnectionDB() {
        openConnection();
    }

    private void openConnection() {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            connect = DriverManager.getConnection(URL, USER, PASSWORD);
            if(connect != null){
                System.out.println("Conexión exitosa estilo HR");
            }
            
        } catch(SQLException | ClassNotFoundException ex){
                System.out.println("Error de conexión a la base de datos");
            }
    }
    
    public Connection getConnection(){
        return connect;
    }
    public void closeConnection(){
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
