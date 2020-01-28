package Modelo.Coordinador;

import java.sql.SQLException;
import java.util.ArrayList;
import Modelo.Logica.LogicaPaciente;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.SexoVO;

public class CoordinadorPaciente {
private LogicaPaciente logicaPaciente;

public void setLogica(LogicaPaciente logicaPaciente) {
	this.logicaPaciente = logicaPaciente;
}

private LogicaPaciente getLogica() {
	return logicaPaciente;
}
public boolean addPaciente(PacienteVO Paciente) throws ClassNotFoundException {
	return logicaPaciente.validarAddPaciente(Paciente);
}
public ArrayList<SexoVO> listaSexo() throws SQLException{
	return logicaPaciente.validarListaSexo();
}
public ArrayList<EstadoCivilVO> listaEstado() throws SQLException{
	return logicaPaciente.validarListaestadoCivil();
}
public ArrayList<PacienteVO> listaPaciente() throws SQLException{
	return logicaPaciente.validarListaPaciente();
}
public boolean addEstado(EstadoCivilVO estado) {
	return logicaPaciente.validarAddEstado(estado);
}
}
