/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Franco
 */
public class Producto extends NomencladorProducto {

    String idProducto;
    String nombreProducto;
    double precioKg;
    double stockKg;
    int cantidadMinima;
    int porcentajeVenta;

        public Producto(String nombreNomenclador, String identificador, String idProducto, String nombreProducto) {
        super(nombreNomenclador, identificador);
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
    }
        
    public Producto(String nombreNomenclador, String identificador, String idProducto, double stockKg, double precioKg) {
        super(nombreNomenclador, identificador);
        this.idProducto = idProducto;
        this.stockKg = stockKg;
        this.precioKg = precioKg;
    }
    
    
    public Producto(String nombreNomenclador, String identificador, String idProducto, String nombreProducto, int cantidadMinima, int porcentajeVenta) {
        super(nombreNomenclador, identificador);
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadMinima = cantidadMinima;
        this.porcentajeVenta = porcentajeVenta;
    }

    public Producto(String nombreNomenclador, String identificador, String idProducto, String nombreProducto, double precioKg, double stockKg, int cantidadMinima, int porcentajeVenta) {
        super(nombreNomenclador, identificador);
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioKg = precioKg;
        this.stockKg = stockKg;
        this.cantidadMinima = cantidadMinima;
        this.porcentajeVenta = porcentajeVenta;
    }

    public Producto(String nombreNomenclador, String identificador) {
        super(nombreNomenclador, identificador);
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreNomenclador() {
        return nombreNomenclador;
    }

    public void setNombreNomenclador(String nombreNomenclador) {
        this.nombreNomenclador = nombreNomenclador;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public double getPrecioKg() {
        return precioKg;
    }

    public void setPrecioKg(double precioKg) {
        this.precioKg = precioKg;
    }

    public double getStockKg() {
        return stockKg;
    }

    public void setStockKg(double stockKg) {
        this.stockKg = stockKg;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public int getPorcentajeVenta() {
        return porcentajeVenta;
    }

    public void setPorcentajeVenta(int porcentajeVenta) {
        this.porcentajeVenta = porcentajeVenta;
    }

    public String toString() {
        return "Nombre Producto = " + nombreProducto;
    }

    public String obtenerIdSuministrador(String suministrador) {
        String suministrador1[] = new String[2];
        suministrador1 = suministrador.split(" - ");
        String idSuministrador = suministrador1[0];
        return idSuministrador;
    }
}
