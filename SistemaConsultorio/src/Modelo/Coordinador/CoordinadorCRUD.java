package Modelo.Coordinador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.DAO.CitasDAO;
import Modelo.DAO.DiagnosticoDAO;
import Modelo.DAO.EmpleadoDAO;
import Modelo.DAO.FacturaDAO;
import Modelo.DAO.LaboratorioDAO;
import Modelo.DAO.PacienteDAO;
import Modelo.DAO.ServicioDAO;
import Modelo.DAO.SolicitudProtesisDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.Logica.LogicaCRUD;
import Modelo.VO.CitasVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.FacturaVO;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.SolicitudProtesisVO;
import Modelo.VO.UsuarioVO;

public class CoordinadorCRUD {
private LogicaCRUD logicaCrud;

private LogicaCRUD getLogica() {
	return logicaCrud;
	}
public void setLogica(LogicaCRUD logicaCrud) {
	this.logicaCrud = logicaCrud;
}
public boolean delete(String[] table,int id) {
	return logicaCrud.validarDelete(table, id);
}
public int selectTopID(String table) {
	return logicaCrud.selectTopID(table);
}
public ArrayList<CitasVO> filtroCita(String table,String field,String value) throws SQLException{
	return logicaCrud.validarfiltroCita(table, field, value);}
public ArrayList<DiagnosticoVO> filtroDiagnostico(String table,String field,String value) throws SQLException{
	return logicaCrud.validarfiltroDiagnostico(table, field, value);}
public ArrayList<EmpleadoVO> filtroEmpleado(String table,String field,String value) throws SQLException{
	return logicaCrud.validarfiltroEmpleado(table, field, value);}
public ArrayList<FacturaVO> filtroFactura(String table,String field,String value) throws SQLException{
	return 	logicaCrud.validarfiltroFactura(table, field, value);}
public ArrayList<LaboratorioVO> filtroLaboratorio(String table,String field,String value) throws SQLException{
	return logicaCrud.validarfiltroLaboratorio(table, field, value);}
public ArrayList<PacienteVO> filtroPaciente(String table,String field,String value) throws SQLException{
	return logicaCrud.validarfiltroPaciente(table, field, value);}
public ArrayList<ServicioVO> filtroServicio(String table,String field,String value) throws SQLException{
	return 	logicaCrud.validarfiltroServicio(table, field, value);}
public ArrayList<SolicitudProtesisVO> filtroSolicitud(String table,String field,String value) throws SQLException{
	return logicaCrud.validarfiltroSolicitud(table, field, value);}
public ArrayList<UsuarioVO> filtroUsuario(String table,String field,String value) throws SQLException{
	return 	logicaCrud.validarfiltroUsuario(table, field, value);}
}
