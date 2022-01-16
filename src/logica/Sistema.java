/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import persistencia.Persistencia;

/**
 *
 * @author Franco
 */
public class Sistema {
    private static Sistema objeto = null;
    
    public static Sistema getInstance() {
        if (objeto == null) {
            objeto = new Sistema();
        }
        return objeto;
    }

    public Usuario loguearse(Usuario usuario) throws ClassNotFoundException, SQLException, IOException {
        Persistencia.getInstance().loguearse(usuario);
        return usuario;
    }

    public String buscarEmpleadoPorCedula(String cedula) throws SQLException, ClassNotFoundException, IOException {
        return Persistencia.getInstance().buscarEmpleadoPorCedula(cedula);   
    }

    public boolean crearUsuario(String nombreUsuario, String contrasenia, String idEmpleado, String tipoUsuario) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().crearUsuario(nombreUsuario, contrasenia, idEmpleado, tipoUsuario);
    }

    public DefaultTableModel mostrarListadoUsuarios() throws SQLException, ClassNotFoundException, IOException {
        return Persistencia.getInstance().mostrarListadoUsuarios();
    }

    public boolean actualizarUsuario(String nombreUsuario, String contrasenia, String idEmpleado, String tipoUsuario) throws ClassNotFoundException, SQLException, SQLException, IOException {
        return Persistencia.getInstance().actualizarUsuario(nombreUsuario, contrasenia, idEmpleado, tipoUsuario);
    }

    public boolean eliminarUsuario(String idUsuario) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().eliminarUsuario(idUsuario);
    }

    public boolean registrarSuministrador(Suministrador suministrador) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().registrarSuministrador(suministrador);
    }
    
    public DefaultTableModel mostrarListadoSuministradores() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().mostrarListadoSuministradores();
    }

    public boolean eliminarSuministrador(String id) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().eliminarSuministrador(id);
    }

    public boolean actualizarSuministrador(Suministrador suministrador, String id) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().actualizarSuministrador(suministrador, id);
    }

    public boolean registrarEmpleado(Empleado empleado) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().registrarEmpleado(empleado);
    }

    public TableModel mostrarListadoEmpleados() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().mostrarListadoEmpleados();
    }

    public Empleado buscarEmpleado(Empleado empleado) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().buscarEmpleado(empleado);
    }

    public boolean actualizarEmpleado(Empleado empleado) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().actualizarEmpleado(empleado);
    }

    public boolean eliminarEmpleado(String id) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().eliminarEmpleado(id);
    }

    public String mostrarSiguienteIdProducto() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().mostrarSiguienteIdProducto();
    }

    public boolean registrarProducto(Producto producto) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().registrarProducto(producto);
    }

    public DefaultTableModel mostrarListadoProductos() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().mostrarListadoProductos();
    }

    public DefaultTableModel buscarProductos(String buscar) throws ClassNotFoundException,SQLException, IOException {
        return Persistencia.getInstance().buscarProductos(buscar);
    }

    public boolean actualizarProducto(Producto producto) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().actualizarProducto(producto);
    }

    public boolean eliminarProducto(String id) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().eliminarProducto(id);
    }

    public boolean registrarProductoAlmacen(Producto producto, String suministrador) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().registrarProductoAlmacen(producto, producto.obtenerIdSuministrador(suministrador));
    }

    public void buscarProductoEnAlmacen(Producto producto, String id) {
        
    }

    public void llenarComboBoxSuministrador(JComboBox<String> cboSuministrador) throws ClassNotFoundException, SQLException, IOException {
        Persistencia.getInstance().llenarComboBoxSuministrador(cboSuministrador);
    }

    public String mostrarSiguienteIdAlmacen() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().mostrarSiguienteIdAlmacen();
    }

    public boolean ingresarCompra(Producto[] productos, String fecha, String idSuministrador, String numeroFactura) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().ingresarCompra(productos, fecha, idSuministrador, numeroFactura);
    }

    public TableModel mostrarListadoProductosVenta() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().mostrarListadoProductosVenta();
    }

    public String mostrarSiguienteIdVenta() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().mostrarSiguienteIdVenta();
    }

    public DefaultTableModel buscarProductosVenta(String buscar) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().buscarProductosVenta(buscar);
    }

    public boolean registrarVenta(Producto[] productos, String fecha, String idVenta) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().registrarVenta(productos, fecha, idVenta);
    }

    public DefaultTableModel mostrarSolicitudesSinAbastecer() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().mostrarSolicitudesSinAbastecer();
    }

    public boolean cambiarEstadoSolicitud(String idSolicitud, String estado) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().cambiarEstadoSolicitud(idSolicitud, estado);
    }

    public DefaultTableModel mostrarTodasLasSolicitudes() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().mostrarTodasLasSolicitudes();
    }

    public DefaultTableModel mostrarSolicitudesAbastecidas() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().mostrarSolicitudesAbastecidas();
    }

    public DefaultTableModel mostrarSolicitudesRechazadas() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().mostrarSolicitudesRechazadas();
    }

    public DefaultTableModel stockFrutas() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().stockFrutas();
    }

    public DefaultTableModel buscarFruta(String buscar) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().buscarFruta(buscar);
    }

    public DefaultTableModel stockVerduras() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().stockVerduras();        
    }

    public DefaultTableModel buscarVerdura(String buscar) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().buscarVerdura(buscar);
    }

    public DefaultTableModel verVentasPorPeriodo(String fechaDesde, String fechaHasta) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().verVentasPorPeriodo(fechaDesde, fechaHasta);
    }

    public DefaultTableModel limpiarTablaVentas() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().limpiarTablaVentas();
    }

    public DefaultTableModel verGananciasPorProducto(String fechaDesde, String fechaHasta) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().verGananciasPorProducto(fechaDesde, fechaHasta);
    }

    public DefaultTableModel limpiarTablaGananciaProductos() throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().limpiarTablaGananciaProductos();
    }

    public String productoQueGeneroMasGanancia(String fechaDesde,String fechaHasta) throws ClassNotFoundException, SQLException, IOException {
        return Persistencia.getInstance().productoQueGeneroMasGanancia(fechaDesde, fechaHasta);
    }
}
