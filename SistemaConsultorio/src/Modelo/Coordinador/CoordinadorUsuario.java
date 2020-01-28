package Modelo.Coordinador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Logica.LogicaUsuario;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.RolVO;
import Modelo.VO.UsuarioVO;

public class CoordinadorUsuario {
private LogicaUsuario logicaUsuario;

private LogicaUsuario getLogica() {
	return logicaUsuario;
}
public void setLogica(LogicaUsuario logicaUsuario) {
	this.logicaUsuario = logicaUsuario;
}
public boolean addUsuario(UsuarioVO usuario) throws ClassNotFoundException, SQLException {
	return logicaUsuario.validarAddUsuario(usuario);
}
public EmpleadoVO buscarEmplead(int id) throws SQLException {
	return logicaUsuario.validarBuscarEmpleado(id);
}
public ArrayList<UsuarioVO> listaUsuario() throws SQLException{
	return logicaUsuario.validarListaUsuario();
}
public ArrayList<RolVO> listaRol() throws SQLException{
	return logicaUsuario.validarListaRol();
}
public boolean addRol(RolVO rol) throws SQLException{
	return logicaUsuario.validarAddRol(rol);
}
}
