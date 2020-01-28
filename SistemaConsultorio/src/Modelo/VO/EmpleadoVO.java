package Modelo.VO;


public class EmpleadoVO extends Persona {
private int idEmpleado;
private String Cedula;
private CargoVO Cargo;
private String Direccion;
private String Correo;
private Double Sueldo;
private String Apellido;
// METODOS EXTENDIDOS DE LA CLASE PERSONA

@Override
public String getTelefono() {
	// TODO Auto-generated method stub
	return super.getTelefono();
}
@Override
public void setTelefono(String telefono) {
	// TODO Auto-generated method stub
	super.setTelefono(telefono);
}
@Override
public String getNombre() {
	// TODO Auto-generated method stub
	return super.getNombre();
}
@Override
public void setNombre(String nombre) {
	// TODO Auto-generated method stub
	super.setNombre(nombre);
}
@Override
public SexoVO getSexo() {
	// TODO Auto-generated method stub
	return super.getSexo();
}
@Override
public void setSexo(SexoVO sexo) {
	// TODO Auto-generated method stub
	super.setSexo(sexo);
}
@Override
public String getFechaIngreso() {
	// TODO Auto-generated method stub
	return super.getFechaIngreso();
}
@Override
public void setFechaIngreso(String fechaIngreso) {
	// TODO Auto-generated method stub
	super.setFechaIngreso(fechaIngreso);
}


// METODOS GETTER Y SETTER DE ATRIBUTOS EXTRAS DE EMPLEADO VO




public String getCedula() {
	return Cedula;
}
public Double getSueldo() {
	return Sueldo;
}
public void setSueldo(Double sueldo) {
	Sueldo = sueldo;
}
public int getIdEmpleado() {
	return idEmpleado;
}

public void setCedula(String cedula) {
	Cedula = cedula;
}
public String getApellido() {
	return Apellido;
}
public void setApellido(String apellido) {
	Apellido = apellido;
}
public CargoVO getCargo() {
	return Cargo;
}
public void setCargo(CargoVO cargo) {
	Cargo = cargo;
}
public String getDireccion() {
	return Direccion;
}
public void setDireccion(String direccion) {
	Direccion = direccion;
}
public String getCorreo() {
	return Correo;
}
public void setCorreo(String correo) {
	Correo = correo;
}
public void setIdEmpleado(int idEmpleado) {
	this.idEmpleado = idEmpleado;
}





}
