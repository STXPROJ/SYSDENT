
package Modelo.VO;

public class PacienteVO extends Persona {
private int idPaciente;
private String FechaNac;
private EstadoCivilVO estadoCivil;
private String Ocupacion;
private double balance;


// METODOS EXTENDIDOS DE LA CLASE PERSOA
@Override
public String getTelefono() {

	return super.getTelefono();
}
@Override
public void setTelefono(String telefono) {

	super.setTelefono(telefono);
}
@Override
public String getNombre() {

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

// METODOS GETTER Y SETTER DE ATRIBUTO EXTRAS DE LA CLASE PACIENTE



public String getFechaNac() {
	return FechaNac;
}
public int getIdPaciente() {
	return idPaciente;
}

public void setIdPaciente(int idPaciente) {
	this.idPaciente = idPaciente;
}
public void setFechaNac(String fechaNac) {
	FechaNac = fechaNac;
}
public EstadoCivilVO getEstadoCivil() {
	return estadoCivil;
}
public void setEstadoCivil(EstadoCivilVO estadoCivil) {
	this.estadoCivil = estadoCivil;
}
public String getOcupacion() {
	return Ocupacion;
}
public void setOcupacion(String ocupacion) {
	Ocupacion = ocupacion;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}



}

