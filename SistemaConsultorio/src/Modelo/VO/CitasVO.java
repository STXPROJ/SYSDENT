package Modelo.VO;

import java.sql.Date;

public class CitasVO {
private int idCita;
private String FechaCita;
private String FechaProgramada;
private String HoraCita;
private PacienteVO paciente;
private EstadoCitaVO Estado;
private EmpleadoVO empleado;

// METODOS GETTER Y SETTER DE LA CLASE CITAS


public String getFechaCita() {
	return FechaCita;
}
public int getIdCita() {
	return idCita;
}
public void setIdCita(int idCita) {
	this.idCita = idCita;
}
public void setFechaCita(String fechaCita) {
	FechaCita = fechaCita;
}
public String getFechaProgramada() {
	return FechaProgramada;
}
public void setFechaProgramada(String fechaProgramada) {
	FechaProgramada = fechaProgramada;
}
public String getHoraCita() {
	return HoraCita;
}
public void setHoraCita(String horaCita) {
	HoraCita = horaCita;
}
public PacienteVO getPaciente() {
	return paciente;
}
public void setPaciente(PacienteVO paciente) {
	this.paciente = paciente;
}
public EstadoCitaVO getEstado() {
	return Estado;
}
public void setEstado(EstadoCitaVO estado) {
	Estado = estado;
}
public EmpleadoVO getEmpleado() {
	return empleado;
}
public void setEmpleado(EmpleadoVO empleado) {
	this.empleado = empleado;
}



}
