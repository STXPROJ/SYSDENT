package Modelo.Logica;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.DAO.CRUD_DAO;
import Modelo.DAO.CitasDAO;
import Modelo.DAO.DiagnosticoDAO;
import Modelo.DAO.EmpleadoDAO;
import Modelo.DAO.FacturaDAO;
import Modelo.DAO.LaboratorioDAO;
import Modelo.DAO.PacienteDAO;
import Modelo.DAO.ServicioDAO;
import Modelo.DAO.SolicitudProtesisDAO;
import Modelo.DAO.UsuarioDAO;
import Modelo.VO.CitasVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.FacturaVO;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.SolicitudProtesisVO;
import Modelo.VO.UsuarioVO;
import Vista.ComponentCustomization;

public class LogicaCRUD {
private CoordinadorCRUD coordCrud;
private CRUD_DAO consultaCRUD;
private ComponentCustomization custom = new ComponentCustomization();
private CitasDAO consultacita;
private DiagnosticoDAO consultadiagnostico;
private EmpleadoDAO consultaEmpleado;
private FacturaDAO consultaFactura;
private LaboratorioDAO consultaLaboratorio;
private PacienteDAO consultaPaciente;
private ServicioDAO consultaServicio;
private SolicitudProtesisDAO consultaSolicitud;
private UsuarioDAO consultaUsuario;
public void setCoordinador(CoordinadorCRUD coordCrud) {
	this.coordCrud = coordCrud;
}
public boolean validarDelete(String [] table,int id){
	boolean bool = false;
	consultaCRUD = new CRUD_DAO();
	if (!(table.length <= 0) || (!(id <= 0)) ) {
			int DialogResult = custom.msgConfirm("Desea Eliminar este Registro?", 1);
			if (DialogResult == JOptionPane.YES_OPTION){
			try {
				consultaCRUD.DeleteFromTable(table, id);
				bool =true;
			}
		catch (Exception ex) {
			custom.msg("Seleccione un registro de la tabla",6);
			ex.printStackTrace();
		}}
	}else {
		custom.msg("Debe seleccionar un registro de la tabla", 7);
	}
	return bool;};
	
public int selectTopID(String table) {
	consultaCRUD = new CRUD_DAO();
	if(!(table.isEmpty())) {
		return consultaCRUD.selectTopID(table);
	}
	return 0;
}

public ArrayList<CitasVO> validarfiltroCita(String table,String field,String value) throws SQLException{
	consultacita = new CitasDAO();
	field = field.replace(" ", "");
	field = field.replace("doctor", "e.nombre");
	field = field.replace("fechacita", "date_format(c.FechaProgramada, '%d/%m/%y') like ");
	System.out.println(field);
	value = "'" + value + "'";
	if (!consultacita.filtroCita(table, field, value).isEmpty()) {
	return consultacita.filtroCita(table, field, value);}
	else {		custom.msg("No existen Registros", 7);
	return null;}}
public ArrayList<DiagnosticoVO> validarfiltroDiagnostico(String table,String field,String value) throws SQLException{
	consultadiagnostico = new DiagnosticoDAO();
	field = field.replace(" ", "");
	value = "'" + value + "'";
	if (!consultadiagnostico.filtroDiagnostico(table, field, value).isEmpty()) {
	return consultadiagnostico.filtroDiagnostico(table, field, value);}
	else {		custom.msg("No existen Registros", 7);
	return null;}}
public ArrayList<EmpleadoVO> validarfiltroEmpleado(String table,String field,String value) throws SQLException{
	consultaEmpleado = new EmpleadoDAO();
	field = field.replace(" ", "");
	value = "'" + value + "'";
	if (!consultaEmpleado.filtroEmpleado(table, field, value).isEmpty()) {
	return consultaEmpleado.filtroEmpleado(table, field, value);}
	else {		custom.msg("No existen Registros", 7);
	return null;}}
public ArrayList<FacturaVO> validarfiltroFactura(String table,String field,String value) throws SQLException{
	consultaFactura = new FacturaDAO();
	field = field.replace(" ", "");
	value = "'" + value + "'";
	if (!	consultaFactura.filtroFactura(table, field, value).isEmpty()) {
	return 	consultaFactura.filtroFactura(table, field, value);}
	else {		custom.msg("No existen Registros", 7);
	return null;}}
public ArrayList<LaboratorioVO> validarfiltroLaboratorio(String table,String field,String value) throws SQLException{
	consultaLaboratorio = new LaboratorioDAO();
	field = field.replace(" ", "");
	value = "'" + value + "'";
	if (!consultaLaboratorio.filtroLaboratorio(table, field, value).isEmpty()) {
	return consultaLaboratorio.filtroLaboratorio(table, field, value);}
	else {		custom.msg("No existen Registros", 7);
	return null;}}
public ArrayList<PacienteVO> validarfiltroPaciente(String table,String field,String value) throws SQLException{
	consultaPaciente = new PacienteDAO();
	field = field.replace(" ", "");
	value = "'" + value + "'";
	if (!consultaPaciente.filtroPaciente(table, field, value).isEmpty()) {
	return consultaPaciente.filtroPaciente(table, field, value);}
	else {		custom.msg("No existen Registros", 7);
	return null;}}
public ArrayList<ServicioVO> validarfiltroServicio(String table,String field,String value) throws SQLException{
	consultaServicio = new ServicioDAO();
	field = field.replace(" ", "");
	value = "'" + value + "'";
	if (!	consultaServicio.filtroServicio(table, field, value).isEmpty()) {
	return 	consultaServicio.filtroServicio(table, field, value);}
	else {		custom.msg("No existen Registros", 7);
	return null;}}
public ArrayList<SolicitudProtesisVO> validarfiltroSolicitud(String table,String field,String value) throws SQLException{
	consultaSolicitud = new SolicitudProtesisDAO();
	field = field.replace(" ", "");
	value = "'" + value + "'";
	if (!consultaSolicitud.filtroSolicitudProtesis(table, field, value).isEmpty()) {
	return consultaSolicitud.filtroSolicitudProtesis(table, field, value);}
	else {		custom.msg("No existen Registros", 7);
	return null;}}
public ArrayList<UsuarioVO> validarfiltroUsuario(String table,String field,String value) throws SQLException{
	consultaUsuario = new UsuarioDAO();
	field = field.replace(" ", "");
	value = "'" + value + "'";
	if (!	consultaUsuario.filtroUsuario(table, field, value).isEmpty()) {
	return 	consultaUsuario.filtroUsuario(table, field, value);}
	else {		custom.msg("No existen Registros", 7);
	return null;}}

}
