package Modelo.VO;

import java.sql.Date;

public class SolicitudProtesisVO {
private int idSolicitud;
private String Fecha;
private PacienteVO paciente;
private LaboratorioVO laboratorio;
private boolean Estado;

// METODOS GETTER Y SETTER DE LA CLASE SOLICITUD PROTESIS


public int getIdSolicitud() {
	return idSolicitud;
}

public void setIdSolicitud(int idSolicitud) {
	this.idSolicitud = idSolicitud;
}

public String getFecha() {
	return Fecha;
}
public void setFecha(String fecha) {
	Fecha = fecha;
}
public PacienteVO getPaciente() {
	return paciente;
}
public void setPaciente(PacienteVO paciente) {
	this.paciente = paciente;
}
public LaboratorioVO getLaboratorio() {
	return laboratorio;
}
public void setLaboratorio(LaboratorioVO laboratorio) {
	this.laboratorio = laboratorio;
}

public boolean isEstado() {
	return Estado;
}

public void setEstado(boolean estado) {
	Estado = estado;
}



}
