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
public class ConsultaAlmacen extends Conexion {

    public ConsultaAlmacen() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

    }

    public String mostrarSiguienteIdAlmacen() throws SQLException {
        String id = null;
        PreparedStatement consulta = consultaPrepare("{Call dbo.CrearIdAlmacen}");
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {
            id = resultado.getString("Id");
        }
        return id;
    }

    public DefaultTableModel stockFrutas() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock Kg");

        String[] producto = new String[4];
        PreparedStatement consulta = consultaPrepare("Select Productos.Id_Producto, Nombre_Producto, ((Precio_Kg_Costo * Porcentaje_Venta/100) + Precio_Kg_Costo) As Precio, Stock_Kg From Almacen Inner Join Productos on Almacen.Id_Producto = Productos.Id_Producto Where Productos.Id_Identificador = 1");
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            producto[0] = resultado.getString("Id_Producto");
            producto[1] = resultado.getString("Nombre_Producto");
            producto[2] = resultado.getString("Precio");
            producto[3] = resultado.getString("Stock_Kg");
            modelo.addRow(producto);
        }
        return modelo;
    }

    public DefaultTableModel buscarFruta(String buscar) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock Kg");

        String[] producto = new String[4];
        PreparedStatement consulta = consultaPrepare("Select Productos.Id_Producto, Nombre_Producto, ((Precio_Kg_Costo * Porcentaje_Venta/100) + Precio_Kg_Costo) As Precio, Stock_Kg From Almacen Inner Join Productos on Almacen.Id_Producto = Productos.Id_Producto Where Nombre_Producto Like ? and Productos.Id_Identificador = 1 Order by Nombre_Producto");
        consulta.setString(1, buscar);
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            producto[0] = resultado.getString("Id_Producto");
            producto[1] = resultado.getString("Nombre_Producto");
            producto[2] = resultado.getString("Precio");
            producto[3] = resultado.getString("Stock_Kg");
            modelo.addRow(producto);
        }
        return modelo;
    }

    public DefaultTableModel stockVerduras() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock Kg");

        String[] producto = new String[4];
        PreparedStatement consulta = consultaPrepare("Select Productos.Id_Producto, Nombre_Producto, ((Precio_Kg_Costo * Porcentaje_Venta/100) + Precio_Kg_Costo) As Precio, Stock_Kg From Almacen Inner Join Productos on Almacen.Id_Producto = Productos.Id_Producto Where Productos.Id_Identificador = 2");
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            producto[0] = resultado.getString("Id_Producto");
            producto[1] = resultado.getString("Nombre_Producto");
            producto[2] = resultado.getString("Precio");
            producto[3] = resultado.getString("Stock_Kg");
            modelo.addRow(producto);
        }
        return modelo;
    }

    public DefaultTableModel buscarVerdura(String buscar) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock Kg");

        String[] producto = new String[4];
        PreparedStatement consulta = consultaPrepare("Select Productos.Id_Producto, Nombre_Producto, ((Precio_Kg_Costo * Porcentaje_Venta/100) + Precio_Kg_Costo) As Precio, Stock_Kg From Almacen Inner Join Productos on Almacen.Id_Producto = Productos.Id_Producto Where Nombre_Producto Like ? and Productos.Id_Identificador = 2 Order by Nombre_Producto");
        consulta.setString(1, buscar);
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            producto[0] = resultado.getString("Id_Producto");
            producto[1] = resultado.getString("Nombre_Producto");
            producto[2] = resultado.getString("Precio");
            producto[3] = resultado.getString("Stock_Kg");
            modelo.addRow(producto);
        }
        return modelo;
    }

    public DefaultTableModel verVentasPorPeriodo(String fechaDesde, String fechaHasta) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("Código");
        modelo.addColumn("Fecha");
        modelo.addColumn("Ganancia");

        String[] venta = new String[3];
        PreparedStatement consulta = consultaPrepare("Select * From Venta Where Fecha_Venta Between ? AND ? Order by Fecha_Venta");
        consulta.setString(1, fechaDesde);
        consulta.setString(2, fechaHasta);
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            venta[0] = resultado.getString("Id_Venta");
            venta[1] = resultado.getString("Fecha_Venta");
            venta[2] = resultado.getString("Ganancia_Total");
            modelo.addRow(venta);
        }
        return modelo;
    }

    public DefaultTableModel limpiarTablaVentas() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Fecha");
        modelo.addColumn("Ganancia");
        return modelo;
    }

    public DefaultTableModel verGananciasPorProductos(String fechaDesde, String fechaHasta) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Ganancia");

        String[] producto = new String[3];
        PreparedStatement consulta = consultaPrepare("Select auxiliar.Id_Producto, Nombre_Producto, Ganancia_Total From (Select Id_Producto, sum(Ganancia) as Ganancia_Total From Venta inner Join Detalle_Venta On Venta.Id_Venta = Detalle_Venta.Id_Venta Where Fecha_Venta Between ? AND ? Group By Detalle_Venta.Id_Producto) As auxiliar inner join Productos on Productos.Id_Producto = auxiliar.Id_Producto Order By Nombre_Producto");
        consulta.setString(1, fechaDesde);
        consulta.setString(2, fechaHasta);
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            producto[0] = resultado.getString("Id_Producto");
            producto[1] = resultado.getString("Nombre_Producto");
            producto[2] = resultado.getString("Ganancia_Total");
            modelo.addRow(producto);
        }
        return modelo;
    }

    public DefaultTableModel limpiarTablaGananciaProductos() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Ganancia");
        return modelo;
    }

    public String productoQueGeneroMasGanancia(String fechaDesde, String fechaHasta) throws SQLException {
        String nombre = null;
        String ganancia = null;
        String producto = null;
        PreparedStatement consulta = consultaPrepare("Select Top 1 auxiliar.Id_Producto, Nombre_Producto, Ganancia_Total From (Select Id_Producto, sum(Ganancia) as Ganancia_Total From Venta inner Join Detalle_Venta On Venta.Id_Venta = Detalle_Venta.Id_Venta Where Fecha_Venta Between ? AND ? Group By Detalle_Venta.Id_Producto) As auxiliar inner join Productos on Productos.Id_Producto = auxiliar.Id_Producto order by Ganancia_Total desc");
        consulta.setString(1, fechaDesde);
        consulta.setString(2, fechaHasta);        
        ResultSet resultado = consulta.executeQuery();
        if(resultado.next()) {
            nombre = resultado.getString("Nombre_Producto");
            ganancia = resultado.getString("Ganancia_Total");
        }       
        producto = ""+nombre+" - $"+ganancia+"";
        return producto;
    }
}
