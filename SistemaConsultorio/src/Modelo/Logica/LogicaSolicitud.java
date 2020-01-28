package Modelo.Logica;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Coordinador.CoordinadorSolicitud;
import Modelo.DAO.CRUD_DAO;
import Modelo.DAO.Detalle_DiagnosticoDAO;
import Modelo.DAO.Detalle_FacturaDAO;
import Modelo.DAO.Detalle_ProtesisDAO;
import Modelo.DAO.DiagnosticoDAO;
import Modelo.DAO.FacturaDAO;
import Modelo.DAO.LaboratorioDAO;
import Modelo.DAO.PacienteDAO;
import Modelo.DAO.ServicioDAO;
import Modelo.DAO.SolicitudProtesisDAO;
import Modelo.VO.Detalle_DiagnosticoVO;
import Modelo.VO.Detalle_FacturaVO;
import Modelo.VO.Detalle_ProtesisVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.SolicitudProtesisVO;
import Vista.ComponentCustomization;

public class LogicaSolicitud {
private CoordinadorSolicitud coordSolicitud;
private ComponentCustomization custom = new ComponentCustomization();
private ServicioDAO consultaServicio;
private PacienteDAO consultaPaciente;
private SolicitudProtesisDAO consultaSolicitud;
private Detalle_ProtesisDAO consultaDetalle;
private LaboratorioDAO consultaLaboratorio;
private CRUD_DAO selectSolicitud;
private ValidationClass validation = new ValidationClass();
public void setCoordinador(CoordinadorSolicitud coordinadorSolicitud) {
	this.coordSolicitud = coordinadorSolicitud;
}
public boolean validarAddSolicitu(SolicitudProtesisVO solicitud,ArrayList<Detalle_ProtesisVO> listaDetalle) {
boolean bool = false;
consultaSolicitud = new SolicitudProtesisDAO();
consultaDetalle = new Detalle_ProtesisDAO(); 
selectSolicitud = new CRUD_DAO();
consultaPaciente = new PacienteDAO();
try {
	if (listaDetalle.isEmpty()){
		custom.msg("La Solicitud debe de tener al menos 1 detalle", 7);	
	}
	else if(solicitud.getLaboratorio().getNombre().isEmpty() || solicitud.getPaciente().getNombre().isEmpty()) {
		custom.msg("", 1);	
	}
	else{
		if(validarDetalle(listaDetalle) == true) {	
		if(custom.msgConfirm("Guardar Solicitud?", 1) == JOptionPane.YES_OPTION){
		consultaSolicitud.RealizarSolicitudProtesis(solicitud);
		for (int i=0;i<listaDetalle.size();i++) {
			consultaDetalle.RegistrarDetalleProtesis(listaDetalle.get(i));
			bool = true;}
		}}}
	}catch (Exception e) {
		e.printStackTrace();
		custom.msg("", 1);
		bool = false;
	}
return bool;}

public boolean validarDetalle(ArrayList<Detalle_ProtesisVO> listaDetalle) {
	boolean bool = false;

	try {
	for (int i = 0;i<listaDetalle.size();i++) {
	if (!(listaDetalle.get(i).getCantidad() >= 0) ||  
			(!(listaDetalle.get(i).getServicio().getIdServicio() >= 0))) {
		custom.msg("", 1);
		bool = false;;}
	else {
	bool = true;;
			}}}
	catch (Exception e) {
		e.printStackTrace();
	}
	return bool;}

public ArrayList<SolicitudProtesisVO> validarListaSolicitud() throws SQLException{
	consultaSolicitud = new SolicitudProtesisDAO();
	if(!consultaSolicitud.listasolicitud().isEmpty()) {
		return consultaSolicitud.listasolicitud();}
		else {
			custom.msg("No existen Solicitudes", 7);
			return null;
}}
public ArrayList<Detalle_ProtesisVO> validarListaDetalle(int id) throws SQLException{
	consultaDetalle = new Detalle_ProtesisDAO();
	if(!consultaDetalle.listaDetalle(id).isEmpty()) {
		return consultaDetalle.listaDetalle(id);}
		else {
			return null;
}}
public ArrayList<ServicioVO> validarListaServicio() throws SQLException{
	consultaServicio = new ServicioDAO();
	return consultaServicio.listaServicio();
}
public PacienteVO validarBuscarPaciente(int id) {
	consultaPaciente = new PacienteDAO();
	if(consultaPaciente.buscarPaciente(id).getNombre() == null ) {
		custom.msg("Paciente", 2);
		return null;
	}else {	
	return consultaPaciente.buscarPaciente(id);}
}
public LaboratorioVO validarBuscarLaboratorio(int id) throws SQLException {
	consultaLaboratorio = new LaboratorioDAO();
	if(consultaLaboratorio.buscarLaboratorio(id).getNombre() == null ) {
		custom.msg("Laboratorio", 2);
		return null;
	}else {	
	return consultaLaboratorio.buscarLaboratorio(id);}}

}
