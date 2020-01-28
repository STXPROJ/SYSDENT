package Modelo.Logica;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Coordinador.CoordinadorEmpleado;
import Modelo.DAO.CargoDAO;
import Modelo.DAO.EmpleadoDAO;
import Modelo.DAO.EstadoCivilDAO;
import Modelo.DAO.SexoDAO;
import Modelo.VO.CargoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.SexoVO;
import Vista.ComponentCustomization;

public class LogicaEmpleado {
private CoordinadorEmpleado coordempleado;
private ComponentCustomization custom = new ComponentCustomization();
private SexoDAO sexo;
private CargoDAO cargo;
private ValidationClass validation = new ValidationClass();
private EmpleadoDAO consultaEmpleado;
public void setCoordinador(CoordinadorEmpleado coordempleado) {
	this.coordempleado = coordempleado;
}

public boolean validarAddEmpleado(EmpleadoVO empleado) throws ClassNotFoundException {
	boolean bool = false;
	consultaEmpleado = new EmpleadoDAO();
	if(empleado.getNombre().isEmpty() || empleado.getApellido().isEmpty() || 
			validation.validDigit(empleado.getTelefono()) == false||
			validation.validDigit(empleado.getCedula())== false)
			{custom.msg("", 1);}
	else if(validation.validDate(empleado.getFechaIngreso()) == false) {
		custom.msg("Formato de Fecha no valido", 6);}
	else if(!empleado.getCorreo().isEmpty() && validation.validEmail(empleado.getCorreo()) == false) {
		custom.msg("El correo no es valido",6);
	}
	else {
		if(custom.msgConfirm("Registrar Empleado?", 1) == JOptionPane.YES_OPTION){
	consultaEmpleado.RegistrarEmpleado(empleado);
	bool = true;}
	}
	return bool;
}


public ArrayList<SexoVO> validarListaSexo() throws SQLException{
	sexo = new SexoDAO();
	return sexo.listaSexo();}


public ArrayList<CargoVO> validarListaCargo() throws SQLException{
	cargo = new CargoDAO();
	return cargo.listacargo();
}
public ArrayList<EmpleadoVO> validarListaEmpleado() throws SQLException{
	consultaEmpleado = new EmpleadoDAO();
	if(!consultaEmpleado.listaEmpleado().isEmpty()) {
	return consultaEmpleado.listaEmpleado();}
	else {
		custom.msg("No hay empleados disponibles", 7);
		return null;}
}
public boolean validarAddCargo(CargoVO elcargo) {
	boolean bool = false;
	cargo = new CargoDAO();
	try {
	if(elcargo.getNombre().isEmpty())
	{custom.msg("Debe llenar el campo de Nombre",7);}
	else
	{cargo.registrarCargo(elcargo);
			bool = true;
		}} catch (Exception e) {
			e.printStackTrace();
		}return bool;
}
}
