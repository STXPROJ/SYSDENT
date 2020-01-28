package Modelo.VO;

public class UsuarioVO {
private int idUsuario;
private String nombre;
private String password;
private RolVO rol;
private EmpleadoVO empleado;
public int getIdUsuario() {
	return idUsuario;
}
public void setIdUsuario(int idUsuario) {
	this.idUsuario = idUsuario;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public RolVO getRol() {
	return rol;
}
public void setRol(RolVO rol) {
	this.rol = rol;
}
public EmpleadoVO getEmpleado() {
	return empleado;
}
public void setEmpleado(EmpleadoVO empleado) {
	this.empleado = empleado;
}


}
