package Modelo.VO;


public class DiagnosticoVO {
private int idDiagnostico;
private PacienteVO paciente;
private UsuarioVO usuario;
private String FechaDiagnostico;
private DentagramaVO dentagrama;
//METODOS GETTER Y SETTER DE LA CLASE DIAGNOSTICO

public int getIdDiagnostico() {
	return idDiagnostico;
}

public void setIdDiagnostico(int idDiagnostico) {
	this.idDiagnostico = idDiagnostico;
}

public PacienteVO getPaciente() {
	return paciente;
}
public void setPaciente(PacienteVO paciente) {
	this.paciente = paciente;
}
public String getFechaDiagnostico() {
	return FechaDiagnostico;
}
public void setFechaDiagnostico(String fechaDiagnostico) {
	FechaDiagnostico = fechaDiagnostico;
}

public UsuarioVO getUsuario() {
	return usuario;
}

public void setUsuario(UsuarioVO usuario) {
	this.usuario = usuario;
}

public DentagramaVO getDentagrama() {
	return dentagrama;
}

public void setDentagrama(DentagramaVO dentagrama) {
	this.dentagrama = dentagrama;
}




}
