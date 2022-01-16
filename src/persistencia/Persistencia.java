/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import logica.Empleado;
import logica.Producto;
import logica.Suministrador;
import logica.Usuario;

/**
 *
 * @author Franco
 */
public class Persistencia {

    private static Persistencia objeto = null;

    private Persistencia() {

    }

    public static Persistencia getInstance() {
        if (objeto == null) {
            objeto = new Persistencia();
        }
        return objeto;
    }

    public Usuario loguearse(Usuario usuario) throws ClassNotFoundException, SQLException, IOException {
        ConsultaUsuario consulta = new ConsultaUsuario();
        consulta.loguearse(usuario);
        return usuario;
    }

    public String buscarEmpleadoPorCedula(String cedula) throws SQLException, ClassNotFoundException, IOException {
        ConsultaEmpleado consulta = new ConsultaEmpleado();
        return consulta.buscarEmpleadoPorCedula(cedula);
    }

    public boolean crearUsuario(String nombreUsuario, String contrasenia, String idEmpleado, String tipoUsuario) throws ClassNotFoundException, SQLException, IOException {
        ConsultaUsuario consulta = new ConsultaUsuario();
        return consulta.crearUsuario(nombreUsuario, contrasenia, idEmpleado, tipoUsuario);
    }

    public DefaultTableModel mostrarListadoUsuarios() throws SQLException, ClassNotFoundException, IOException {
        ConsultaUsuario consulta = new ConsultaUsuario();
        return consulta.mostrarListaUsuarios();
    }

    public boolean actualizarUsuario(String nombreUsuario, String contrasenia, String idEmpleado, String tipoUsuario) throws ClassNotFoundException, SQLException, IOException {
        ConsultaUsuario consulta = new ConsultaUsuario();
        return consulta.actualizarUsuario(nombreUsuario, contrasenia, idEmpleado, tipoUsuario);
    }

    public boolean eliminarUsuario(String idUsuario) throws ClassNotFoundException, SQLException, IOException {
        ConsultaUsuario consulta = new ConsultaUsuario();
        return consulta.eliminarUsuario(idUsuario);
    }

    public boolean registrarSuministrador(Suministrador suministrador) throws ClassNotFoundException, SQLException, IOException {
        ConsultaSuministrador consulta = new ConsultaSuministrador();
        return consulta.registrarSuministrador(suministrador);
    }

    public DefaultTableModel mostrarListadoSuministradores() throws ClassNotFoundException, SQLException, IOException {
        ConsultaSuministrador consulta = new ConsultaSuministrador();
        return consulta.mostrarListadoSuministradores();
    }

    public boolean eliminarSuministrador(String id) throws ClassNotFoundException, SQLException, IOException {
        ConsultaSuministrador consulta = new ConsultaSuministrador();
        return consulta.eliminarSuministrador(id);
    }

    public boolean actualizarSuministrador(Suministrador suministrador, String id) throws ClassNotFoundException, SQLException, IOException {
        ConsultaSuministrador consulta = new ConsultaSuministrador();
        return consulta.actualizarSuministrador(suministrador, id);
    }

    public boolean registrarEmpleado(Empleado empleado) throws ClassNotFoundException, SQLException, IOException {
        ConsultaEmpleado consulta = new ConsultaEmpleado();
        return consulta.registrarEmpleado(empleado);
    }

    public TableModel mostrarListadoEmpleados() throws ClassNotFoundException, SQLException, IOException {
        ConsultaEmpleado consulta = new ConsultaEmpleado();
        return consulta.mostrarListadoEmpleados();
    }

    public Empleado buscarEmpleado(Empleado empleado) throws ClassNotFoundException, SQLException, IOException {
        ConsultaEmpleado consulta = new ConsultaEmpleado();
        return consulta.buscarEmpleado(empleado);
    }

    public boolean actualizarEmpleado(Empleado empleado) throws ClassNotFoundException, SQLException, IOException {
        ConsultaEmpleado consulta = new ConsultaEmpleado();
        return consulta.actualizarEmpleado(empleado);
    }

    public boolean eliminarEmpleado(String id) throws ClassNotFoundException, SQLException, IOException {
        ConsultaEmpleado consulta = new ConsultaEmpleado();
        return consulta.eliminarEmpleado(id);
    }

    public String mostrarSiguienteIdProducto() throws ClassNotFoundException, SQLException, IOException {
        ConsultaProducto consulta = new ConsultaProducto();
        return consulta.mostrarSiguienteIdProducto();
    }

    public boolean registrarProducto(Producto producto) throws ClassNotFoundException, SQLException, IOException {
        ConsultaProducto consulta = new ConsultaProducto();
        return consulta.registrarProducto(producto);
    }

    public DefaultTableModel mostrarListadoProductos() throws ClassNotFoundException, SQLException, IOException {
        ConsultaProducto consulta = new ConsultaProducto();
        return consulta.mostrarListadoProductos();
    }

    public DefaultTableModel buscarProductos(String buscar) throws ClassNotFoundException, SQLException, IOException {
        ConsultaProducto consulta = new ConsultaProducto();
        return consulta.buscarProductos(buscar);
    }

    public boolean actualizarProducto(Producto producto) throws ClassNotFoundException, SQLException, IOException {
        ConsultaProducto consulta = new ConsultaProducto();
        return consulta.actualizarProducto(producto);
    }

    public boolean eliminarProducto(String id) throws ClassNotFoundException, SQLException, IOException {
        ConsultaProducto consulta = new ConsultaProducto();
        return consulta.eliminarProducto(id);
    }

    public boolean registrarProductoAlmacen(Producto producto, String suministrador) throws ClassNotFoundException, SQLException, IOException {
        ConsultaProducto consulta = new ConsultaProducto();
        return consulta.registrarProductoAlmacen(producto, suministrador);
    }

    public void llenarComboBoxSuministrador(JComboBox<String> cboSuministrador) throws ClassNotFoundException, SQLException, IOException {
        ConsultaSuministrador consulta = new ConsultaSuministrador();
        consulta.llenarComboBoxSuministrador(cboSuministrador);
    }

    public String mostrarSiguienteIdAlmacen() throws ClassNotFoundException, SQLException, IOException {
        ConsultaAlmacen consulta = new ConsultaAlmacen();
        return consulta.mostrarSiguienteIdAlmacen();
    }

    public boolean ingresarCompra(Producto[] productos, String fecha, String idSuministrador, String numeroFactura) throws ClassNotFoundException, SQLException, IOException {
        ConsultaProducto consulta = new ConsultaProducto();
        return consulta.ingresarCompra(productos, fecha, idSuministrador, numeroFactura);

    }

    public TableModel mostrarListadoProductosVenta() throws ClassNotFoundException, SQLException, IOException {
        ConsultaProducto consulta = new ConsultaProducto();
        return consulta.mostrarListadoProductosVenta();
    }

    public String mostrarSiguienteIdVenta() throws ClassNotFoundException, SQLException, IOException {
        ConsultaProducto consulta = new ConsultaProducto();
        return consulta.mostrarSiguienteIdVenta();
    }

    public DefaultTableModel buscarProductosVenta(String buscar) throws ClassNotFoundException, SQLException, IOException {
        ConsultaProducto consulta = new ConsultaProducto();
        return consulta.buscarProductosVenta(buscar);
    }

    public boolean registrarVenta(Producto[] productos, String fecha, String idVenta) throws ClassNotFoundException, SQLException, IOException {
        ConsultaProducto consulta = new ConsultaProducto();
        return consulta.registrarVenta(productos, fecha, idVenta);
    }

    public DefaultTableModel mostrarSolicitudesSinAbastecer() throws ClassNotFoundException, SQLException, IOException {
        ConsultaSolicitud consulta = new ConsultaSolicitud();
        return consulta.mostrarSolicitudesSinAbastecer();
    }

    public boolean cambiarEstadoSolicitud(String idSolicitud, String estado) throws ClassNotFoundException, SQLException, IOException {
        ConsultaSolicitud consulta = new ConsultaSolicitud();
        return consulta.cambiarEstadoSolicitud(idSolicitud, estado);
    }

    public DefaultTableModel mostrarTodasLasSolicitudes() throws ClassNotFoundException, SQLException, IOException {
        ConsultaSolicitud consulta = new ConsultaSolicitud();
        return consulta.mostrarTodasLasSolicitudes();
    }

    public DefaultTableModel mostrarSolicitudesAbastecidas() throws ClassNotFoundException, SQLException, IOException {
        ConsultaSolicitud consulta = new ConsultaSolicitud();
        return consulta.mostrarSolicitudesAbastecidas();
    }

    public DefaultTableModel mostrarSolicitudesRechazadas() throws ClassNotFoundException, SQLException, IOException {
        ConsultaSolicitud consulta = new ConsultaSolicitud();
        return consulta.mostrarSolicitudesRechazadas();
    }

    public DefaultTableModel stockFrutas() throws ClassNotFoundException, SQLException, IOException {
        ConsultaAlmacen consulta = new ConsultaAlmacen();
        return consulta.stockFrutas();
    }

    public DefaultTableModel buscarFruta(String buscar) throws ClassNotFoundException, SQLException, IOException {
        ConsultaAlmacen consulta = new ConsultaAlmacen();
        return consulta.buscarFruta(buscar);
    }

    public DefaultTableModel stockVerduras() throws ClassNotFoundException, SQLException, IOException {
        ConsultaAlmacen consulta = new ConsultaAlmacen();
        return consulta.stockVerduras();
    }

    public DefaultTableModel buscarVerdura(String buscar) throws ClassNotFoundException, SQLException, IOException {
        ConsultaAlmacen consulta = new ConsultaAlmacen();
        return consulta.buscarVerdura(buscar);
    }

    public DefaultTableModel verVentasPorPeriodo(String fechaDesde, String fechaHasta) throws ClassNotFoundException, SQLException, IOException {
        ConsultaAlmacen consulta = new ConsultaAlmacen();
        return consulta.verVentasPorPeriodo(fechaDesde, fechaHasta);
    }

    public DefaultTableModel limpiarTablaVentas() throws ClassNotFoundException, SQLException, IOException {
        ConsultaAlmacen consulta = new ConsultaAlmacen();
        return consulta.limpiarTablaVentas();
    }

    public DefaultTableModel verGananciasPorProducto(String fechaDesde, String fechaHasta) throws ClassNotFoundException, SQLException, IOException {
        ConsultaAlmacen consulta = new ConsultaAlmacen();
        return consulta.verGananciasPorProductos(fechaDesde, fechaHasta);
    }

    public DefaultTableModel limpiarTablaGananciaProductos() throws ClassNotFoundException, SQLException, IOException {
        ConsultaAlmacen consulta = new ConsultaAlmacen();
        return consulta.limpiarTablaGananciaProductos();
    }

    public String productoQueGeneroMasGanancia(String fechaDesde, String fechaHasta) throws ClassNotFoundException, SQLException, IOException {
        ConsultaAlmacen consulta = new ConsultaAlmacen();
        return consulta.productoQueGeneroMasGanancia(fechaDesde, fechaHasta);
    }
}
