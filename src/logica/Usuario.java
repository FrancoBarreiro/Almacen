
package logica;

public class Usuario extends Empleado {
    
    String tipoUsuario;
    String nombreUsuario;
    String contrasenia;

    public Usuario(String idEmpleado, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String cedula, String direccion, String celular, String tipoUsuario, String nombreUsuario, String contrasenia) {
        super(idEmpleado, primerNombre, segundoNombre, primerApellido, segundoApellido, cedula, direccion, celular);
        this.tipoUsuario = tipoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
    }

    public Usuario() {
        
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    public String toString() {
        return "Usuario: Tipo de Usuario = " + tipoUsuario + ", Nombre Usuario = " + nombreUsuario;
    }
    
}
