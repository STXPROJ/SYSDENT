package Modelo.VO;

public class LaboratorioVO {
private int idLaboratorio;
private String Nombre;
private String Descripcion;
private String Telefono;
private String Rnc;
private String Contacto;
private String Correo;
private String Direccion;


// METODOS GETTER Y SETTER DE LA CLASE LABORATORIO


public int getIdLaboratorio() {
	return idLaboratorio;
}

public String getDescripcion() {
	return Descripcion;
}

public void setDescripcion(String descripcion) {
	Descripcion = descripcion;
}

public String getContacto() {
	return Contacto;
}

public void setContacto(String contacto) {
	Contacto = contacto;
}

public String getCorreo() {
	return Correo;
}

public void setCorreo(String correo) {
	Correo = correo;
}

public String getRnc() {
	return Rnc;
}

public void setRnc(String rnc) {
	Rnc = rnc;
}

public String getNombre() {
	return Nombre;
}
public void setNombre(String nombre) {
	Nombre = nombre;
}
public void setIdLaboratorio(int idLaboratorio) {
	this.idLaboratorio = idLaboratorio;
}

public String getTelefono() {
	return Telefono;
}
public void setTelefono(String telefono) {
	Telefono = telefono;
}
public String getDireccion() {
	return Direccion;
}
public void setDireccion(String direccion) {
	Direccion = direccion;
}


}
