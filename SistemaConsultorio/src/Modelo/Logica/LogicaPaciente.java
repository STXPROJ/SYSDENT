package Modelo.Logica;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Coordinador.CoordinadorPaciente;
import Modelo.DAO.EstadoCitaDAO;
import Modelo.DAO.EstadoCivilDAO;
import Modelo.DAO.PacienteDAO;
import Modelo.DAO.SexoDAO;
import Modelo.VO.EstadoCitaVO;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.SexoVO;
import Vista.ComponentCustomization;

public class LogicaPaciente {
private CoordinadorPaciente coordPaciente;
private ComponentCustomization custom = new ComponentCustomization();
private SexoDAO sexo;
private EstadoCivilDAO consultaEstadoCivil;
private ValidationClass validation = new ValidationClass();
private PacienteDAO consultaPaciente;
public void setCoordinador(CoordinadorPaciente coordPaciente) {
	this.coordPaciente = coordPaciente;
}

public boolean validarAddPaciente(PacienteVO Paciente) throws ClassNotFoundException {
	consultaPaciente = new PacienteDAO();
	boolean bool = false;
	if(Paciente.getNombre().isEmpty() || validation.validDigit(Paciente.getTelefono()) == false)
			{custom.msg("", 1);}
	else if(validation.validDate(Paciente.getFechaIngreso()) == false || 
			validation.validDate(Paciente.getFechaNac()) == false) {
		custom.msg("Formato de Fecha no valido", 6);}
	else {
		if(custom.msgConfirm("Registrar Paciente?", 1) == JOptionPane.YES_OPTION){
	consultaPaciente.RegistrarPaciente(Paciente);
	bool = true;}
	
	}return bool;
}


public ArrayList<SexoVO> validarListaSexo() throws SQLException{
	sexo = new SexoDAO();
	return sexo.listaSexo();
}

public ArrayList<EstadoCivilVO> validarListaestadoCivil() throws SQLException{
	consultaEstadoCivil = new EstadoCivilDAO();
	return consultaEstadoCivil.listaestado();
}
public ArrayList<PacienteVO> validarListaPaciente() throws SQLException{
	consultaPaciente = new PacienteDAO();
	if(!consultaPaciente.listapaciente().isEmpty()) {
	return consultaPaciente.listapaciente();}
	else {
		custom.msg("No hay Pacientes disponibles", 7);
		return null;}
}
public boolean validarAddEstado(EstadoCivilVO estado) {
	boolean bool = false;
	consultaEstadoCivil = new EstadoCivilDAO();
	try {
	if(estado.getNombre().isEmpty())
	{custom.msg("Debe llenar el campo de Nombre",7);}
	else
	{consultaEstadoCivil.addEstado(estado);
			bool = true;
		}} catch (Exception e) {
			e.printStackTrace();
		}return bool;
}
}
