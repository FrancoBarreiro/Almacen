/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import logica.Empleado;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Franco
 */
public class ConsultaEmpleado extends Conexion {

    public ConsultaEmpleado() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
    
    }

    public String buscarEmpleadoPorCedula(String cedula) throws SQLException {
        String idEmpleado = null;
        PreparedStatement consulta = consultaPrepare("select Id_Empleado from Empleados Where Cedula = ?");
        consulta.setString(1, cedula);
        ResultSet resultado = consulta.executeQuery();
        
        if(resultado.next()) {
            idEmpleado = resultado.getString("Id_Empleado");
        }
        return idEmpleado;
    }

    public boolean registrarEmpleado(Empleado empleado) throws SQLException {
        boolean estado = false;
        String idNuevo = null;
        String cedulaEnBaseDatos = null;
        
        PreparedStatement consulta = consultaPrepare("{call dbo.CrearIdEmpleado}");
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {
            idNuevo = resultado.getString("Id");
        }
        
        consulta = consultaPrepare("Select Cedula As cedula From Empleados Where Cedula=?");
        consulta.setString(1, empleado.getCedula());
        resultado = consulta.executeQuery();
        if(resultado.next()){
            cedulaEnBaseDatos = resultado.getString("cedula");
        }
        
        if(!idNuevo.isEmpty() || !empleado.getCedula().equals(cedulaEnBaseDatos)) {
        consulta = actualizar("Insert into Empleados (Id_Empleado, Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido, Fecha_Nacimiento, Cedula, Direccion, Celular) values (?,?,?,?,?,?,?,?,?)");
        consulta.setString(1, idNuevo);
        consulta.setString(2, empleado.getPrimerNombre());
        consulta.setString(3, empleado.getSegundoNombre());
        consulta.setString(4, empleado.getPrimerApellido());
        consulta.setString(5, empleado.getSegundoApellido());
        consulta.setString(6, empleado.getFechaNacimiento());
        consulta.setString(7, empleado.getCedula());
        consulta.setString(8, empleado.getDireccion());
        consulta.setString(9, empleado.getCelular());
        consulta.executeUpdate();
        estado = true;
        }
        return estado;
    }
    
        public DefaultTableModel mostrarListadoEmpleados() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }   
        };
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Fecha Nac.");
        modelo.addColumn("Cédula");
        modelo.addColumn("Celular");

        String[] empleado = new String[6];
        PreparedStatement consulta = consultaPrepare("SELECT * FROM Empleados");
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            empleado[0] = resultado.getString("Id_Empleado");
            empleado[1] = resultado.getString("Primer_Nombre");
            empleado[2] = resultado.getString("Primer_Apellido");
            empleado[3] = resultado.getString("Fecha_Nacimiento");
            empleado[4] = resultado.getString("Cedula");
            empleado[5] = resultado.getString("Celular");
            modelo.addRow(empleado);
        }
        return modelo;
    }

    public Empleado buscarEmpleado(Empleado empleado) throws SQLException {
        PreparedStatement consulta = consultaPrepare("Select * From Empleados Where Id_Empleado = ?");
        consulta.setString(1, empleado.getIdEmpleado());
        ResultSet resultado = consulta.executeQuery();
        if(resultado.next()){
            empleado.setPrimerNombre(resultado.getString("Primer_Nombre"));
            empleado.setSegundoNombre(resultado.getString("Segundo_Nombre"));
            empleado.setPrimerApellido(resultado.getString("Primer_Apellido"));
            empleado.setSegundoApellido(resultado.getString("Segundo_Apellido"));
            empleado.setFechaNacimiento(resultado.getString("Fecha_Nacimiento"));
            empleado.setCedula(resultado.getString("Cedula"));
            empleado.setDireccion(resultado.getString("Direccion"));
            empleado.setCelular(resultado.getString("Celular"));
        }
        return empleado;
    }

    public boolean actualizarEmpleado(Empleado empleado) throws SQLException {
        boolean estado = false;
        PreparedStatement consulta = actualizar("Update Empleados Set Primer_Nombre = ?, Segundo_Nombre = ?, Primer_Apellido = ?, Segundo_Apellido = ?, Cedula = ?, Direccion = ?, Celular = ? Where Id_Empleado = ?");
            consulta.setString(1, empleado.getPrimerNombre());
            consulta.setString(2, empleado.getSegundoNombre());
            consulta.setString(3, empleado.getPrimerApellido());
            consulta.setString(4, empleado.getSegundoApellido());
            consulta.setString(5, empleado.getCedula());
            consulta.setString(6, empleado.getDireccion());
            consulta.setString(7, empleado.getCelular());
            consulta.setString(8, empleado.getIdEmpleado());
            consulta.executeUpdate();
            estado = true;
        return estado;
    }

    public boolean eliminarUsuario(String idUsuario) throws SQLException {
        boolean estado = false; 
        PreparedStatement consulta = actualizar("Delete From Usuarios Where Id_Usuario = ?");
        consulta.setString(1, idUsuario);
        consulta.executeUpdate();
        estado = true;    
        return estado;   
    }

    public boolean eliminarEmpleado(String id) throws SQLException {
        boolean estado = false; 
        PreparedStatement consulta = consultaPrepare("Select Id_Usuario From Usuarios Where Id_Empleado = ?");
        consulta.setString(1, id);
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {
            consulta = actualizar("Delete From Usuarios Where Id_Empleado = ?");
            consulta.setString(1, id);
            consulta.executeUpdate();
            
            consulta = actualizar("Delete From Empleados Where Id_Empleado = ?");
            consulta.setString(1, id);
            consulta.executeUpdate();
            estado = true;
        } else {
            consulta = actualizar("Delete From Empleados Where Id_Empleado = ?");
            consulta.setString(1, id);
            consulta.executeUpdate();
            estado = true;
        }   
        return estado; 
    }
    
}
