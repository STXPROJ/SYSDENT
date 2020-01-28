package Modelo.Logica;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import Modelo.Coordinador.CoordCita;
import Modelo.DAO.CitasDAO;
import Modelo.DAO.EmpleadoDAO;
import Modelo.DAO.EstadoCitaDAO;
import Modelo.DAO.PacienteDAO;
import Modelo.VO.CitasVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCitaVO;
import Modelo.VO.PacienteVO;
import Vista.ComponentCustomization;

public class LogicaCita {
private PacienteDAO consultaPaciente;
private EmpleadoDAO consultaEmpleado;
private EstadoCitaDAO consultaEstado;
private CitasDAO consultaCita;
private CoordCita coordinadorCita;
private ComponentCustomization custom = new ComponentCustomization();
private ValidationClass validation = new ValidationClass();


public void setCoordCita(CoordCita coordinadorCita) {
	this.coordinadorCita = coordinadorCita;
}

public boolean validarAddCita(CitasVO cita) {
	boolean bool = false;
	CitasDAO citas = new CitasDAO();
	try {
	if(cita.getEstado().getNombre().isEmpty() || cita.getEstado().getNombre().isEmpty() 
			|| cita.getEmpleado().getNombre().isEmpty())
	{custom.msg("",1);}
	else if(validation.validDate(cita.getFechaProgramada()) == false) {
		custom.msg("Formato de Fecha no valido", 6);}
		else if(validation.validTime(cita.getHoraCita()) == false) {
			custom.msg("Formato de Hora no valido", 6);
	}else {
		if(custom.msgConfirm("Desea Agendar esta cita?", 1) == JOptionPane.YES_OPTION){
			citas.AgendarCita(cita);
			bool = true;
		}}} catch (Exception e) {
			e.printStackTrace();
		}return bool;
	}
public PacienteVO validarBuscarPaciente(int id) {
	consultaPaciente = new PacienteDAO();
	if(consultaPaciente.buscarPaciente(id).getNombre() == null ) {
		custom.msg("paciente", 2);
		return null;
	}else {	
	return consultaPaciente.buscarPaciente(id);
}	
}
public ArrayList<EmpleadoVO> validarEmpleadoComboBox() throws SQLException{
	consultaEmpleado = new EmpleadoDAO();
	return consultaEmpleado.empleadoComboBox();
	
}
public ArrayList<EstadoCitaVO> validarEstadoComboBox() throws SQLException{
	consultaEstado = new EstadoCitaDAO();
	if(!consultaEstado.listaEstado().isEmpty()) {
	return consultaEstado.listaEstado();}
	else {
		return consultaEstado.listaEstado();
	}
}

public ArrayList<CitasVO> validarListaCitas() throws SQLException{
	consultaCita = new CitasDAO();
	if (!consultaCita.listaCitas().isEmpty()) {
	return consultaCita.listaCitas();}
	else {		custom.msg("No existen Citas", 7);
	return null;
}}
public boolean validarAddEstado(EstadoCitaVO estado) {
	boolean bool = false;
	consultaEstado = new EstadoCitaDAO();
	try {
	if(estado.getNombre().isEmpty())
	{custom.msg("Debe llenar el campo de Nombre",7);}
	else
	{consultaEstado.addEstado(estado);
			bool = true;
		}} catch (Exception e) {
			e.printStackTrace();
		}return bool;
}
}
