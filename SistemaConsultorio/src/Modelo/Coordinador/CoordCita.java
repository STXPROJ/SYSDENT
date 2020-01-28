package Modelo.Coordinador;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import Modelo.DAO.PacienteDAO;
import Modelo.Logica.LogicaCita;
import Modelo.VO.CitasVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCitaVO;
import Modelo.VO.PacienteVO;

public class CoordCita {
private LogicaCita logicaCita;

public LogicaCita getLogicaCita() {
	return logicaCita;
}
public void setLogicaCita(LogicaCita logicaCita) {
	this.logicaCita = logicaCita;
}

public boolean addCita(CitasVO cita) throws ParseException {
	return logicaCita.validarAddCita(cita);
}

public boolean addEstado(EstadoCitaVO estado) throws ParseException {
	return logicaCita.validarAddEstado(estado);
}
public ArrayList<CitasVO> listaCita() throws SQLException{
	return logicaCita.validarListaCitas();
	
}
public ArrayList<EmpleadoVO> empleadoComboBox() throws SQLException{
	return logicaCita.validarEmpleadoComboBox();
}
public ArrayList<EstadoCitaVO> estadoComboBox() throws SQLException{
	return logicaCita.validarEstadoComboBox();
}
public PacienteVO buscarPaciente(int id) {
	return logicaCita.validarBuscarPaciente(id);
}
}
