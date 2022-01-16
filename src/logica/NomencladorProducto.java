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
public class NomencladorProducto {
    String nombreNomenclador;
    String identificador;

    public NomencladorProducto(String nombreNomenclador, String identificador) {
        this.nombreNomenclador = nombreNomenclador;
        this.identificador = identificador;
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

    
    public String toString() {
        return "NomencladorProducto: " + "Nombre Nomenclador = " + nombreNomenclador + ", Identificador = " + identificador;
    }
}
