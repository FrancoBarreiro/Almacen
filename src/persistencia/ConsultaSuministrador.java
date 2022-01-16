/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.IOException;
import java.sql.SQLException;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import logica.Suministrador;

/**
 *
 * @author Franco
 */
public class ConsultaSuministrador extends Conexion {

    public ConsultaSuministrador() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

    }

    public boolean registrarSuministrador(Suministrador suministrador) throws SQLException {
        boolean estado = false;
        String idSuministrador = null;
        PreparedStatement consulta = consultaPrepare("CrearIdSuministrador");
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {
            idSuministrador = resultado.getString("id");
        }

        if (estado == false) {
            consulta = consultaPrepare("INSERT INTO Suministrador (Id_Suministrador, Nombre, Direccion, Telefono, Email) Values (?,?,?,?,?)");
            consulta.setString(1, idSuministrador);
            consulta.setString(2, suministrador.getNombreSuministrador());
            consulta.setString(3, suministrador.getDireccion());
            consulta.setString(4, suministrador.getTelefono());
            consulta.setString(5, suministrador.getEmail());
            int respuesta = consulta.executeUpdate();

            if (respuesta > 0) {
                estado = true;
            }
        }
        return estado;
    }

    public DefaultTableModel mostrarListadoSuministradores() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Dirección");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Email");

        String[] suministrador = new String[5];
        PreparedStatement consulta = consultaPrepare("SELECT * FROM Suministrador");
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            suministrador[0] = resultado.getString("Id_Suministrador");
            suministrador[1] = resultado.getString("Nombre");
            suministrador[2] = resultado.getString("Direccion");
            suministrador[3] = resultado.getString("Telefono");
            suministrador[4] = resultado.getString("Email");
            modelo.addRow(suministrador);
        }
        return modelo;
    }

    public boolean eliminarSuministrador(String id) throws SQLException {
        boolean estado = false;
        PreparedStatement consulta = actualizar("Delete From Suministrador Where Id_Suministrador = ?");
        consulta.setString(1, id);
        int respuesta = consulta.executeUpdate();

        if (respuesta > 0) {
            estado = true;
        }
        return estado;
    }

    public boolean actualizarSuministrador(Suministrador suministrador, String id) throws SQLException {
        boolean estado = false;
        PreparedStatement consulta = actualizar("Update Suministrador Set Nombre = ?, Direccion = ?, Telefono = ?, Email = ? Where Id_Suministrador = ?");
        consulta.setString(1, suministrador.getNombreSuministrador());
        consulta.setString(2, suministrador.getDireccion());
        consulta.setString(3, suministrador.getTelefono());
        consulta.setString(4, suministrador.getEmail());
        consulta.setString(5, id);
        int respuesta = consulta.executeUpdate();

        if (respuesta > 0) {
            estado = true;
        }
        return estado;
    }

    public void llenarComboBoxSuministrador(JComboBox<String> cboSuministrador) throws SQLException {
        PreparedStatement consulta = consultaPrepare("Select Id_Suministrador, Nombre From Suministrador Order By Nombre");
        ResultSet resultado = consulta.executeQuery();

        cboSuministrador.addItem("--Seleccione una opción--");
        while (resultado.next()) {
            cboSuministrador.addItem(resultado.getString("Id_Suministrador") + " - " + resultado.getString("Nombre"));
        }
    }
}

