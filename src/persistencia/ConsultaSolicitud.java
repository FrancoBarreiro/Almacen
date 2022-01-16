/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Franco
 */
public class ConsultaSolicitud extends Conexion {

    public ConsultaSolicitud() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
    }

    public DefaultTableModel mostrarSolicitudesSinAbastecer() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");

        String[] solicitud = new String[4];
        PreparedStatement consulta = consultaPrepare("Select * From Solicitudes Where Estado = 'Sin abastecer' order by Id_Solicitud");
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            solicitud[0] = resultado.getString("Id_Solicitud");
            solicitud[1] = resultado.getString("Id_Producto");
            solicitud[2] = resultado.getString("Fecha_Solicitud");
            solicitud[3] = resultado.getString("Estado");
            modelo.addRow(solicitud);
        }
        return modelo;
    }

    public boolean cambiarEstadoSolicitud(String idSolicitud, String estado) throws SQLException {
        boolean estadoProcedimiento = false;

        if (estado.equals("Abastecida")) {
            PreparedStatement consulta = actualizar("Update Solicitudes Set Estado = ? Where Id_Solicitud = ?");
            consulta.setString(1, estado);
            consulta.setString(2, idSolicitud);
            int respuesta = consulta.executeUpdate();
            if (respuesta > 0) {
                estadoProcedimiento = true;
            }
        } else if (estado.equals("Rechazada")) {
            PreparedStatement consulta = actualizar("Update Solicitudes Set Estado = ? Where Id_Solicitud = ?");
            consulta.setString(1, estado);
            consulta.setString(2, idSolicitud);
            int respuesta = consulta.executeUpdate();
            if (respuesta > 0) {
                estadoProcedimiento = true;
            }
        }
        return estadoProcedimiento;
    }

    public DefaultTableModel mostrarTodasLasSolicitudes() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");

        String[] solicitud = new String[4];
        PreparedStatement consulta = consultaPrepare("Select * From Solicitudes order by Id_Solicitud");
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            solicitud[0] = resultado.getString("Id_Solicitud");
            solicitud[1] = resultado.getString("Id_Producto");
            solicitud[2] = resultado.getString("Fecha_Solicitud");
            solicitud[3] = resultado.getString("Estado");
            modelo.addRow(solicitud);
        }
        return modelo;
    }

    public DefaultTableModel mostrarSolicitudesAbastecidas() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");

        String[] solicitud = new String[4];
        PreparedStatement consulta = consultaPrepare("Select * From Solicitudes Where Estado = 'Abastecida' order by Id_Solicitud");
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            solicitud[0] = resultado.getString("Id_Solicitud");
            solicitud[1] = resultado.getString("Id_Producto");
            solicitud[2] = resultado.getString("Fecha_Solicitud");
            solicitud[3] = resultado.getString("Estado");
            modelo.addRow(solicitud);
        }
        return modelo;
    }

    public DefaultTableModel mostrarSolicitudesRechazadas() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");

        String[] solicitud = new String[4];
        PreparedStatement consulta = consultaPrepare("Select * From Solicitudes Where Estado = 'Rechazada' order by Id_Solicitud");
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            solicitud[0] = resultado.getString("Id_Solicitud");
            solicitud[1] = resultado.getString("Id_Producto");
            solicitud[2] = resultado.getString("Fecha_Solicitud");
            solicitud[3] = resultado.getString("Estado");
            modelo.addRow(solicitud);
        }
        return modelo;
    }
}
