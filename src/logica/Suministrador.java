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
public class Suministrador {
    String nombreSuministrador;
    String direccion;
    String telefono;
    String email;

    public Suministrador(String nombreSuministrador, String direccion, String telefono, String email) {
        this.nombreSuministrador = nombreSuministrador;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }
    
    public String getNombreSuministrador() {
        return nombreSuministrador;
    }

    public void setNombreSuministrador(String nombreSuministrador) {
        this.nombreSuministrador = nombreSuministrador;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Nombre Suministrador = " + nombreSuministrador + ", Direccion = " + direccion + ", Teléfono = " + telefono + ", email = " + email;
    }

}
