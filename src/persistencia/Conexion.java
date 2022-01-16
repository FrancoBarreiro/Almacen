package persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Conexion {

    private Connection conexion; 
    
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String servidor="jdbc:sqlserver://Localhost:1433;databaseName=Almacen";
    String usuario="sa";
    String pass="123";
    
    public Conexion() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        Class.forName(driver);
        conexion=DriverManager.getConnection(servidor, usuario, pass);
    }
  
    public ResultSet consulta(String sql) throws SQLException {
        Statement s = conexion.createStatement();
        return s.executeQuery(sql);
    }

    public PreparedStatement actualizar(String sql) throws SQLException {
        PreparedStatement s = conexion.prepareStatement(sql);
        return s;
    }

    public PreparedStatement consultaPrepare(String sql) throws SQLException {
        PreparedStatement s = conexion.prepareStatement(sql);
        return s;
    }  

}

