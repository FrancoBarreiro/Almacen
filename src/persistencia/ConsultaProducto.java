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
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import logica.Producto;

/**
 *
 * @author Franco
 */
class ConsultaProducto extends Conexion {

    public ConsultaProducto() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

    }

    public String mostrarSiguienteIdProducto() throws SQLException {
        String id = null;
        PreparedStatement consulta = consultaPrepare("{Call dbo.CrearIdProducto}");
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {
            id = resultado.getString("Id");
        }
        return id;
    }

    public boolean registrarProducto(Producto producto) throws SQLException {
        boolean estado = true;
        String nomProducto = null;
        int id_Identificador = 0;
        if (producto.getIdentificador().equals("FRU")) {
            id_Identificador = 1;
        } else {
            id_Identificador = 2;
        }
        PreparedStatement consulta = consultaPrepare("Select Nombre_Producto From Productos");
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            nomProducto = resultado.getString("Nombre_Producto");
            if (nomProducto.equalsIgnoreCase(producto.getNombreProducto())) {
                estado = false;
                break;
            }
        }
        if (estado == true) {
            consulta = actualizar("Insert into Productos (Id_Producto, Id_Identificador, Nombre_Producto, Cantidad_Minima, Porcentaje_Venta) values (?,?,?,?,?)");
            consulta.setString(1, producto.getIdProducto());
            consulta.setInt(2, id_Identificador);
            consulta.setString(3, producto.getNombreProducto());
            consulta.setInt(4, producto.getCantidadMinima());
            consulta.setInt(5, producto.getPorcentajeVenta());
            int respuesta = consulta.executeUpdate();
            if (respuesta > 0) {
                estado = true;
            }
        }
        return estado;
    }

    public DefaultTableModel mostrarListadoProductos() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Identificador");
        modelo.addColumn("Cantidad Mínima");
        modelo.addColumn("% Venta");

        String[] producto = new String[5];
        PreparedStatement consulta = consultaPrepare("Select Id_Producto, Identificador, Nombre_Producto, Cantidad_Minima, Porcentaje_Venta From Productos INNER JOIN Nomenclador_Productos ON Nomenclador_Productos.Id = Productos.Id_Identificador Order by Nombre_Producto");
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            producto[0] = resultado.getString("Id_Producto");
            producto[1] = resultado.getString("Nombre_Producto");
            producto[2] = resultado.getString("Identificador");
            producto[3] = resultado.getString("Cantidad_Minima");
            producto[4] = resultado.getString("Porcentaje_Venta");
            modelo.addRow(producto);
        }
        return modelo;
    }

    public DefaultTableModel buscarProductos(String buscar) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Identificador");
        modelo.addColumn("Cantidad Mínima");
        modelo.addColumn("% Venta");

        String[] producto = new String[5];
        PreparedStatement consulta = consultaPrepare("Select Id_Producto, Identificador, Nombre_Producto, Cantidad_Minima, Porcentaje_Venta From Productos INNER JOIN Nomenclador_Productos ON Nomenclador_Productos.Id = Productos.Id_Identificador Where Nombre_Producto Like ? Order by Nombre_Producto");
        consulta.setString(1, buscar);
        ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            producto[0] = resultado.getString("Id_Producto");
            producto[1] = resultado.getString("Nombre_Producto");
            producto[2] = resultado.getString("Identificador");
            producto[3] = resultado.getString("Cantidad_Minima");
            producto[4] = resultado.getString("Porcentaje_Venta");
            modelo.addRow(producto);
        }
        return modelo;
    }

    public boolean actualizarProducto(Producto producto) throws SQLException {
        boolean estado = false;
        int id_Identificador = 0;
        if (producto.getIdentificador().equals("FRU")) {
            id_Identificador = 1;
        } else {
            id_Identificador = 2;
        }
        PreparedStatement consulta = actualizar("Update Productos Set Nombre_producto = ?, Id_Identificador = ? Where Id_Producto = ?");
        consulta.setString(1, producto.getNombreProducto());
        consulta.setInt(2, id_Identificador);
        consulta.setString(3, producto.getIdProducto());
        int respuesta = consulta.executeUpdate();

        if (respuesta > 0) {
            estado = true;
        }
        return estado;
    }

    public boolean eliminarProducto(String id) throws SQLException {
        boolean estado = false;
        PreparedStatement consulta = actualizar("Delete From Productos Where Id_Producto = ?");
        consulta.setString(1, id);
        int respuesta = consulta.executeUpdate();

        if (respuesta > 0) {
            estado = true;
        }
        return estado;
    }

    public boolean registrarProductoAlmacen(Producto producto, String suministrador) throws SQLException {
        boolean estado = true;
        String nomProducto = null;
        int id_Identificador = 0;
        if (producto.getIdentificador().equals("FRU")) {
            id_Identificador = 1;
        } else {
            id_Identificador = 2;
        }
        PreparedStatement consulta = consultaPrepare("Select Nombre_Producto From Productos");
        ResultSet resultado = consulta.executeQuery();
        while (resultado.next()) {
            nomProducto = resultado.getString("Nombre_Producto");
            if (nomProducto.equalsIgnoreCase(producto.getNombreProducto())) {
                estado = false;
                break;
            }
        }
        if (estado == true) {
            consulta = actualizar("Insert into Productos (Id_Producto, Id_Identificador, Nombre_Producto) values (?,?,?)");
            consulta.setString(1, producto.getIdProducto());
            consulta.setInt(2, id_Identificador);
            consulta.setString(3, producto.getNombreProducto());
            int respuesta = consulta.executeUpdate();
            if (respuesta > 0) {
                consulta = actualizar("Insert into Almacen (Id_Suministrador, Id_Producto, Precio_Kg_Almacen, Stock_Kg, Porciento_Venta, Cantidad_Minima_Kg) values (?,?,?,?,?,?)");
                consulta.setString(1, suministrador);
                consulta.setString(2, producto.getIdProducto());
                consulta.setDouble(3, producto.getPrecioKg());
                consulta.setDouble(4, producto.getStockKg());
                consulta.setInt(5, producto.getPorcentajeVenta());
                consulta.setInt(6, producto.getCantidadMinima());
                int respuesta2 = consulta.executeUpdate();
                if (respuesta2 < 1) {
                    estado = false;
                }
            }
        }
        return estado;
    }

    public boolean ingresarCompra(Producto[] productos, String fecha, String idSuministrador, String numeroFactura) throws SQLException {
        boolean estado = true;
        PreparedStatement consulta;
        ResultSet resultado;

        String idCompra = null;
        consulta = consultaPrepare("{Call dbo.CrearIdCompra}");
        resultado = consulta.executeQuery();
        if (resultado.next()) {
            idCompra = resultado.getString("Id");
        }
        consulta = actualizar("Insert into Compras (Id_Compra, Numero_Factura, Id_Suministrador, Fecha) values (?,?,?,?)");
        consulta.setString(1, idCompra);
        consulta.setString(2, numeroFactura);
        consulta.setString(3, idSuministrador);
        consulta.setString(4, fecha);
        int respuesta = consulta.executeUpdate();
        if (respuesta < 1) {
            estado = false;
        }

        if (estado == true) {
            for (int i = 0; i < productos.length; i++) {
                consulta = actualizar("Insert into Detalle_Compra(Numero_Factura, Id_Producto, Cantidad, Importe) values (?,?,?,?)");
                consulta.setString(1, numeroFactura);
                consulta.setString(2, productos[i].getIdProducto());
                consulta.setString(3, String.valueOf(productos[i].getStockKg()));
                consulta.setString(4, String.valueOf(productos[i].getPrecioKg() * productos[i].getStockKg()));
                respuesta = consulta.executeUpdate();
                if (respuesta < 1) {
                    estado = false;
                    break;
                }
            }
        }
        if (estado == true) {
            String idAlmacen = null;
            for (int i = 0; i < productos.length; i++) {
                consulta = consultaPrepare("{Call dbo.CrearIdAlmacen}");
                resultado = consulta.executeQuery();
                if (resultado.next()) {
                    idAlmacen = resultado.getString("Id");
                }
                consulta = actualizar("Insert into Almacen(Id_Almacen, Id_Suministrador, Id_Producto, Precio_Kg_Costo, Stock_Kg) values (?,?,?,?,?)");
                consulta.setString(1, idAlmacen);
                consulta.setString(2, idSuministrador);
                consulta.setString(3, productos[i].getIdProducto());
                consulta.setDouble(4, productos[i].getPrecioKg());
                consulta.setDouble(5, productos[i].getStockKg());
                respuesta = consulta.executeUpdate();
                if (respuesta < 1) {
                    estado = false;
                    break;
                }
            }
        }
        return estado;
    }

    public DefaultTableModel mostrarListadoProductosVenta() throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");

        String[] producto = new String[4];
        PreparedStatement consulta = consultaPrepare("Select Productos.Id_Producto, Nombre_Producto, Precio_Kg_Costo, Porcentaje_Venta, Precio_Kg_Costo+(Porcentaje_Venta*Precio_Kg_Costo/100) as Precio, Stock_Kg From Productos inner join Almacen on Productos.Id_Producto = Almacen.Id_Producto Order by Nombre_Producto");
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

    public DefaultTableModel buscarProductosVenta(String buscar) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");

        String[] producto = new String[4];
        PreparedStatement consulta = consultaPrepare("Select Productos.Id_Producto, Nombre_Producto, Precio_Kg_Costo, Porcentaje_Venta, Precio_Kg_Costo+(Porcentaje_Venta*Precio_Kg_Costo/100) as Precio, Stock_Kg From Productos inner join Almacen on Productos.Id_Producto = Almacen.Id_Producto Where Nombre_Producto Like ? Order by Nombre_Producto");
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

    public String mostrarSiguienteIdVenta() throws SQLException {
        String id = null;
        PreparedStatement consulta = consultaPrepare("{Call dbo.CrearIdVenta}");
        ResultSet resultado = consulta.executeQuery();
        if (resultado.next()) {
            id = resultado.getString("Id");
        }
        return id;
    }

    public boolean registrarVenta(Producto[] productos, String fecha, String idVenta) throws SQLException {
        boolean estado = true;
        PreparedStatement consulta;
        ResultSet resultado;

        double ganancia = 0;
        double gananciaTotal = 0;
        double precioVenta = 0;
        int respuesta = 0;
        double dividir = 0;
        double precioCosto = 0;

        for (int i = 0; i < productos.length; i++) {
            consulta = consultaPrepare("Select Porcentaje_Venta From Productos Where Id_Producto = ?");
            consulta.setString(1, productos[i].getIdProducto());
            resultado = consulta.executeQuery();

            while (resultado.next()) {
                productos[i].setPorcentajeVenta(resultado.getInt("Porcentaje_Venta"));
            }
        }

        if (estado == true) {
            for (int i = 0; i < productos.length; i++) {
                dividir = ((double) productos[i].getPorcentajeVenta() / 100) + 1;
                gananciaTotal = ((productos[i].getPrecioKg() - (productos[i].getPrecioKg() / dividir)) * productos[i].getStockKg()) + gananciaTotal;
            }
            consulta = actualizar("Insert into Venta(Id_Venta, Fecha_Venta, Ganancia_Total) values (?,?,?)");
            consulta.setString(1, idVenta);
            consulta.setString(2, fecha);
            consulta.setDouble(3, gananciaTotal);
            respuesta = consulta.executeUpdate();
            if (respuesta < 1) {
                estado = false;
            }
        }

        if (estado == true) {
            for (int i = 0; i < productos.length; i++) {
                dividir = ((double) productos[i].getPorcentajeVenta() / 100) + 1;
                ganancia = ((productos[i].getPrecioKg() - (productos[i].getPrecioKg() / dividir)) * productos[i].getStockKg());
                consulta = actualizar("Insert into Detalle_Venta(Id_Venta, Id_Producto, Precio_Kg_Venta, Cantidad, Ganancia) values (?,?,?,?,?)");
                consulta.setString(1, idVenta);
                consulta.setString(2, productos[i].getIdProducto());
                consulta.setDouble(3, productos[i].getPrecioKg());
                consulta.setDouble(4, productos[i].getStockKg());
                consulta.setDouble(5, ganancia);
                respuesta = consulta.executeUpdate();
                if (respuesta < 1) {
                    estado = false;
                    break;
                }
            }
        }

        if (estado == true) {
            double stockDisponible = 0;
            double stockTotal = 0;
            String idSolicitud = null;

            for (int i = 0; i < productos.length; i++) {
                dividir = ((double) productos[i].getPorcentajeVenta() / 100) + 1;
                precioCosto = Math.round((productos[i].getPrecioKg() / dividir) * 100);
                consulta = consultaPrepare("Select Stock_Kg From Almacen Where Id_Producto = ? and Precio_Kg_Costo = ?");
                consulta.setString(1, productos[i].getIdProducto());
                consulta.setDouble(2, precioCosto / 100);
                resultado = consulta.executeQuery();

                while (resultado.next()) {
                    stockDisponible = resultado.getDouble("Stock_Kg");
                    if (productos[i].getStockKg() == stockDisponible) {
                        consulta = actualizar("Delete From Almacen Where Id_Producto = ? and Precio_Kg_Costo = ?");
                        consulta.setString(1, productos[i].getIdProducto());
                        consulta.setDouble(2, precioCosto / 100);
                        consulta.executeUpdate();

                        consulta = consultaPrepare("Select sum(Stock_Kg) as Total_Stock From Almacen where Id_Producto = ?");
                        consulta.setString(1, productos[i].getIdProducto());
                        consulta.executeQuery();
                        resultado = consulta.executeQuery();
                        if (resultado.next()) {
                            stockTotal = resultado.getDouble("Total_Stock");
                        }

                        consulta = consultaPrepare("Select Cantidad_Minima From Productos Where Id_Producto = ?");
                        consulta.setString(1, productos[i].getIdProducto());
                        resultado = consulta.executeQuery();
                        if (resultado.next()) {
                            productos[i].setCantidadMinima(resultado.getInt("Cantidad_Minima"));
                        }

                        if (stockTotal <= productos[i].getCantidadMinima()) {
                            consulta = consultaPrepare("{Call dbo.CrearIdSolicitud}");
                            resultado = consulta.executeQuery();
                            if (resultado.next()) {
                                idSolicitud = resultado.getString("Id");
                            }

                            consulta = actualizar("Insert into Solicitudes(Id_Solicitud, Id_Producto, Fecha_Solicitud, Estado) values (?,?,?,?)");
                            consulta.setString(1, idSolicitud);
                            consulta.setString(2, productos[i].getIdProducto());
                            consulta.setString(3, fecha);
                            consulta.setString(4, "Sin abastecer");
                            consulta.executeUpdate();
                        }

                    } else if (productos[i].getStockKg() < stockDisponible) {

                        double nuevoStock = (double) stockDisponible - productos[i].getStockKg();
                        consulta = actualizar("Update Almacen Set Stock_Kg = ? Where Id_Producto = ? and Precio_Kg_Costo = ?");
                        consulta.setDouble(1, nuevoStock);
                        consulta.setString(2, productos[i].getIdProducto());
                        consulta.setDouble(3, precioCosto / 100);
                        consulta.executeUpdate();

                        consulta = consultaPrepare("Select Cantidad_Minima From Productos Where Id_Producto = ?");
                        consulta.setString(1, productos[i].getIdProducto());
                        resultado = consulta.executeQuery();
                        if (resultado.next()) {
                            productos[i].setCantidadMinima(resultado.getInt("Cantidad_Minima"));
                        }
                        if (nuevoStock <= productos[i].getCantidadMinima()) {
                            consulta = consultaPrepare("Select sum(Stock_Kg) as Total_Stock From Almacen where Id_Producto = ?");
                            consulta.setString(1, productos[i].getIdProducto());
                            consulta.executeQuery();
                            resultado = consulta.executeQuery();
                            if (resultado.next()) {
                                stockTotal = resultado.getDouble("Total_Stock");
                            }
                            if (stockTotal <= productos[i].getCantidadMinima()) {
                                consulta = consultaPrepare("{Call dbo.CrearIdSolicitud}");
                                resultado = consulta.executeQuery();
                                if (resultado.next()) {
                                    idSolicitud = resultado.getString("Id");
                                }
                                consulta = actualizar("Insert into Solicitudes(Id_Solicitud, Id_Producto, Fecha_Solicitud, Estado) values (?,?,?,?)");
                                consulta.setString(1, idSolicitud);
                                consulta.setString(2, productos[i].getIdProducto());
                                consulta.setString(3, fecha);
                                consulta.setString(4, "Sin abastecer");
                                consulta.executeUpdate();
                            }
                        }
                    }
                }
            }
        }
        return estado;
    }
}
