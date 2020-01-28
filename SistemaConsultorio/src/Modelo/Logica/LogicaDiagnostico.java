package Modelo.Logica;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import Modelo.Coordinador.CoordinadorDiagnostico;
import Modelo.DAO.CRUD_DAO;
import Modelo.DAO.CitasDAO;
import Modelo.DAO.DentagramaDAO;
import Modelo.DAO.Detalle_DiagnosticoDAO;
import Modelo.DAO.DiagnosticoDAO;
import Modelo.DAO.PacienteDAO;
import Modelo.DAO.ServicioDAO;
import Modelo.VO.DentagramaVO;
import Modelo.VO.Detalle_DiagnosticoVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;
import Vista.ComponentCustomization;

public class LogicaDiagnostico {
private CoordinadorDiagnostico coordDiagnostico;
private ComponentCustomization custom = new ComponentCustomization();
private PacienteDAO consultaPaciente;
private ServicioDAO consultaServicio;
private CRUD_DAO selectDiagnostico;
private DiagnosticoDAO consultaDiagnostico;
private Detalle_DiagnosticoDAO consultaDetalle;
private DentagramaDAO consultaDentagrama;
public void setCoordDiagnostico(CoordinadorDiagnostico coordDiagnostico) {
	this.coordDiagnostico = coordDiagnostico;
}



public boolean validarAddDiagnostico(DiagnosticoVO diagnostico,ArrayList<Detalle_DiagnosticoVO> listaDetalle) {
	boolean bool = false;
	consultaDiagnostico = new DiagnosticoDAO();
	consultaDetalle = new Detalle_DiagnosticoDAO(); 
	selectDiagnostico = new CRUD_DAO();
	consultaPaciente = new PacienteDAO();
	try {

	if (listaDetalle.isEmpty()){
		custom.msg("El Diagnostico debe de tener al menos 1 detalle", 7);
	}else if (!(diagnostico.getPaciente().getNombre().isEmpty())) {
	if (validarDetalle(listaDetalle) == true) {	
		if(custom.msgConfirm("Guardar Diagnostico?", 1) == JOptionPane.YES_OPTION){
		consultaDiagnostico.RealizarDiagnostico(diagnostico);
		if (diagnostico.getDentagrama() != null){
			validarAgregarDentagrama(diagnostico.getDentagrama());
		}
		for (int i=0;i<listaDetalle.size();i++) {
			consultaDetalle.RegistrarDetalleDiagnostico(listaDetalle.get(i));}
			bool = true;
		}
	}}else {
		bool = false;
		custom.msg("", 1);
	}
	}catch (Exception e) {
		e.printStackTrace();
	}
	return bool;}
		
	
public PacienteVO validarBuscarPaciente(int id) {
		consultaPaciente = new PacienteDAO();
		if(consultaPaciente.buscarPaciente(id).getNombre() == null ) {
			custom.msg("Paciente", 2);
			return null;
		}else {	
		return consultaPaciente.buscarPaciente(id);
	}	
	}
public ArrayList<ServicioVO> validarListaServicios() throws SQLException{
	consultaServicio = new ServicioDAO();
		return consultaServicio.listaServicio();
}
public ArrayList<DiagnosticoVO> validarListaDiagnostico() throws SQLException{
	consultaDiagnostico = new DiagnosticoDAO();
	if(!consultaDiagnostico.listaDiagnostico().isEmpty()) {
		return consultaDiagnostico.listaDiagnostico();}
		else {
			custom.msg("No existen Diagnosticos", 7);
			return null;
}}
public ArrayList<Detalle_DiagnosticoVO> validarListaDetalle(int id) throws SQLException{
	consultaDetalle = new Detalle_DiagnosticoDAO();
	if(!consultaDetalle.listaDetalle(id).isEmpty()) {
		return consultaDetalle.listaDetalle(id);}
		else {
			return null;
}}
public DentagramaVO validarBuscarDentagrama(int idPaciente,int idDiagnostico) {
	consultaDentagrama = new DentagramaDAO();
	DentagramaVO dentagrama = consultaDentagrama.buscarDentagrama(idPaciente, idDiagnostico);
	if(dentagrama != null){
		return dentagrama;
	}else {
	return null;}
}
private void validarAgregarDentagrama(DentagramaVO dentagrama) throws ClassNotFoundException {
	consultaDentagrama = new DentagramaDAO();
	consultaDentagrama.registrarDentagrama(dentagrama);		
}
public boolean validarDetalle(ArrayList<Detalle_DiagnosticoVO> listaDetalle) {
	boolean bool = false;
	
	for (int i = 0;i<listaDetalle.size();i++) {
	if ( !(listaDetalle.size()>=0)||(!(listaDetalle.get(i).getCantidad() >= 0) ||  
			(!(listaDetalle.get(i).getServicio().getIdServicio() >= 0)))) {
		custom.msg("", 1);
		bool = false;}
	else {
	
	bool = true;;
			}}

	return bool;}
}


