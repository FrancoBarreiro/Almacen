/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import logica.Usuario;

/**
 *
 * @author Franco
 */
public class ConsultaUsuario extends Conexion {

    public ConsultaUsuario() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
    
    }
    
    public Usuario loguearse (Usuario usuario) throws SQLException {
        PreparedStatement consulta = consultaPrepare("Select Tipo_Usuario From Usuarios Where Nombre_Usuario=? AND Contrasenia=?");
        consulta.setString(1, usuario.getNombreUsuario());
        consulta.setString(2, usuario.getContrasenia());
        ResultSet resultado = consulta.executeQuery();
        
        if(resultado.next()){
            String tipoUsuario = resultado.getString("Tipo_Usuario");
            usuario.setTipoUsuario(tipoUsuario);
            
        }
        return usuario;
    }

    public boolean crearUsuario(String nombreUsuario, String contrasenia, String idEmpleado, String tipoUsuario) throws SQLException {
        boolean estado = false;
        String nomUsuario = null;
        String id = null;
        
        String idNuevo = null;

        PreparedStatement consulta = consultaPrepare("{call dbo.CrearIdUsuario}");
        ResultSet resultado = consulta.executeQuery();
        if(resultado.next()){
            idNuevo = resultado.getString("Id");
        }
        
        consulta = consultaPrepare("Select Id_Empleado From Empleados Where Id_Empleado = ?");
        consulta.setString(1, idEmpleado);
        resultado = consulta.executeQuery();
        while (resultado.next()) {
            String idEnBaseDatos = resultado.getString("Id_Empleado");
            estado = true;
        } 
        
        consulta = consultaPrepare("Select Nombre_Usuario As NombreUsuario, Id_Empleado As id From Usuarios");
        resultado = consulta.executeQuery();
        while (resultado.next()) {
            nomUsuario = resultado.getString("NombreUsuario");
            id = resultado.getString("id");
            if (nomUsuario.equals(nombreUsuario) || id.equals(idEmpleado)) {
                estado = false;
                break;
            }
        }
        
        if (estado == true) {
            consulta = actualizar("Insert into Usuarios (Id_Usuario, Id_Empleado, Tipo_Usuario, Nombre_Usuario, Contrasenia) values (?,?,?,?,?)");
            consulta.setString(1, idNuevo);
            consulta.setString(2, idEmpleado);
            consulta.setString(3, tipoUsuario);
            consulta.setString(4, nombreUsuario);
            consulta.setString(5, contrasenia);
            int respuesta = consulta.executeUpdate();

            if (respuesta < 1){
                estado = false;
            }
        }
        return estado;
    }

    public DefaultTableModel mostrarListaUsuarios() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID Usuario");
        modelo.addColumn("ID Empleado");
        modelo.addColumn("Nombre Usuario");
        modelo.addColumn("Tipo");

        String[] usuario = new String[4];
        PreparedStatement consulta = consultaPrepare("SELECT * FROM Usuarios");
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            usuario[0] = resultado.getString("Id_Usuario");
            usuario[1] = resultado.getString("Id_Empleado");
            usuario[2] = resultado.getString("Nombre_Usuario");
            if (resultado.getString("Tipo_Usuario").equalsIgnoreCase("Adm")) {
                usuario[3] = "Administrador";
            } else if (resultado.getString("Tipo_Usuario").equalsIgnoreCase("Ven")) {
                usuario[3] = "Vendedor";
            }
            modelo.addRow(usuario);
        }
        return modelo;
    }

    public boolean actualizarUsuario(String nombreUsuario, String contrasenia, String idEmpleado, String tipoUsuario) throws SQLException {
        boolean estado = false;
        PreparedStatement consulta;

        if (contrasenia.trim().equalsIgnoreCase("")) {
            consulta = actualizar("Update Usuarios Set Tipo_Usuario = ?, Nombre_Usuario = ? Where Id_Empleado = ?");
            consulta.setString(1, tipoUsuario);
            consulta.setString(2, nombreUsuario);
            consulta.setString(3, idEmpleado);
            int respuesta = consulta.executeUpdate();

            if (respuesta > 0) {
                estado = true;
            }
        } else {
            consulta = actualizar("Update Usuarios Set Tipo_Usuario = ?, Nombre_Usuario = ?, Contrasenia = ? Where Id_Empleado = ?");
            consulta.setString(1, tipoUsuario);
            consulta.setString(2, nombreUsuario);
            consulta.setString(3, contrasenia);
            consulta.setString(4, idEmpleado);
            int respuesta = consulta.executeUpdate();

            if (respuesta > 0) {
                estado = true;
            }
        }
        return estado;
    }

    public boolean eliminarUsuario(String idUsuario) throws SQLException {
        boolean estado = false;
        PreparedStatement consulta = actualizar("Delete From Usuarios Where Id_Usuario = ?");
        consulta.setString(1, idUsuario);
        int respuesta = consulta.executeUpdate();

        if (respuesta > 0) {
            estado = true;
        }
        return estado;
    }
}
