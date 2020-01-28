package Modelo.VO;


public class Persona {

private String Nombre;
private SexoVO Sexo;
private String FechaIngreso;
private String Telefono;


//METODOS GETTER Y SETER DE LA CLASE PERSONA QUE EXTENDERA A LA CLASE PACIENTE Y EMPLEADO
public String getFechaIngreso() {
	return FechaIngreso;
}
public void setFechaIngreso(String fechaIngreso) {
	FechaIngreso = fechaIngreso;
}


public String getTelefono() {
	return Telefono;
}
public void setTelefono(String telefono) {
	Telefono = telefono;
}
public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
public SexoVO getSexo() {
	return Sexo;
}
public void setSexo(SexoVO sexo) {
	Sexo = sexo;
}


}
