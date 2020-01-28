package Modelo.Logica;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Coordinador.CoordinadorUsuario;
import Modelo.DAO.EmpleadoDAO;
import Modelo.DAO.EstadoCivilDAO;
import Modelo.DAO.RolDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.RolVO;
import Modelo.VO.UsuarioVO;
import Vista.ComponentCustomization;

public class LogicaUsuario {
private CoordinadorUsuario coordUsuario;
private ComponentCustomization custom = new ComponentCustomization();
private EmpleadoDAO consultaEmpleado;
private UsuarioDAO consultaUsuario;
private RolDAO consultaRol;
public void setCoordinador(CoordinadorUsuario coordUsuario) {
	this.coordUsuario = coordUsuario;
}

public boolean validarAddUsuario(UsuarioVO usuario) throws ClassNotFoundException, SQLException {
	boolean bool = false;
	consultaUsuario = new UsuarioDAO();
	if(usuario.getNombre().isEmpty() || usuario.getPassword().isEmpty()) {
		custom.msg("", 1);}
	else if(!(usuario.getPassword().length() >= 6)){
		custom.msg("La contraseña debe tener al menos 6 digitos", 7);}
	else {
		if (validarExistenciaUser(usuario) == false) {
				if(custom.msgConfirm("Desea Agregar Este Usuario", 1) == JOptionPane.YES_OPTION) {
					consultaUsuario = new UsuarioDAO();
					consultaUsuario.Registrarusuario(usuario);
					bool = true;
					}}
		}return bool;
	}
public boolean validarExistenciaUser(UsuarioVO usuario) throws SQLException {
	boolean bool = false;
	for (int i = 0;i<consultaUsuario.listausuario().size();i++) {
		if (usuario.getNombre().equals(consultaUsuario.listausuario().get(i).getNombre())) {
			custom.msg("El usuario ya existe", 6);
			bool = true;
		}}
		return bool;
}


public EmpleadoVO validarBuscarEmpleado(int id) throws SQLException {
	 consultaEmpleado= new EmpleadoDAO();
	if(consultaEmpleado.buscarempleado(id).getNombre() == null ) {
		custom.msg("Empleado", 2);
		return null;
	}else {	
	return consultaEmpleado.buscarempleado(id);
}}
public ArrayList<UsuarioVO> validarListaUsuario() throws SQLException{
	consultaUsuario = new UsuarioDAO();
	if(!consultaUsuario.listausuario().isEmpty()) {
		return consultaUsuario.listausuario();
	}else {
		custom.msg("No existen usuarios", 7);
		return null;
	}}
public ArrayList<RolVO> validarListaRol() throws SQLException{
	consultaRol = new RolDAO();
		return consultaRol.listarol();
	}
public boolean validarAddRol(RolVO rol) {
	boolean bool = false;
	 consultaRol= new RolDAO();
	try {
	if(rol.getNombre().isEmpty())
	{custom.msg("Debe llenar el campo de Nombre",7);}
	else
	{consultaRol.registrarrol(rol);
			bool = true;
		}} catch (Exception e) {
			e.printStackTrace();
		}return bool;
}
}
