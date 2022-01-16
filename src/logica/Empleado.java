/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.Date;

/**
 *
 * @author Franco
 */
public class Empleado {
    String idEmpleado;
    String primerNombre;
    String segundoNombre;
    String primerApellido;
    String segundoApellido;
    String fechaNacimiento;
    String cedula;
    String direccion;
    String celular;
    
    public Empleado (String idEmpleado, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String fechaNacimiento, String cedula, String direccion, String celular){
        this.idEmpleado = idEmpleado;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.cedula = cedula;
        this.direccion = direccion;
        this.celular = celular;
    }

        public Empleado (String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String fechaNacimiento, String cedula, String direccion, String celular){
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.cedula = cedula;
        this.direccion = direccion;
        this.celular = celular;
    }
        
    public Empleado () {
        
    }

    public Empleado (String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public String getPrimerNombre() {
        return primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getCedula() {
        return cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    public String toString() {
        return "Nombre = " + primerNombre + " , Apellido = " + primerApellido + " , Cedula = " + cedula;
    }
    
}