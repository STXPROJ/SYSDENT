package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Conexion.Conexion;
import Modelo.VO.SolicitudProtesisVO;
import Vista.ComponentCustomization;
import Modelo.VO.SolicitudProtesisVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.PacienteVO;;

public class SolicitudProtesisDAO {
	 private CallableStatement cs;
	 private ComponentCustomization custom = new ComponentCustomization();
		private CRUD_DAO consulta;
		private java.sql.PreparedStatement ps;
	public void RealizarSolicitudProtesis(SolicitudProtesisVO solicitud) {
	try {
		// Preparar el procedimiento antes de ejecutar
		    cs = Conexion.getConnection().prepareCall("Call SolicitarProtesis(?,?)");
		    // Ingresar Parametros
		    cs.setInt(1,solicitud.getPaciente().getIdPaciente() );
		    cs.setInt(2, solicitud.getLaboratorio().getIdLaboratorio());
	    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("Solicitud Registrada con exito", 4);     }
		catch (SQLException e) {
	        e.printStackTrace();
	    }}
	public ArrayList<SolicitudProtesisVO> listasolicitud() throws SQLException{
		ArrayList<SolicitudProtesisVO> listasolicitud = new ArrayList<SolicitudProtesisVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectAllsolicitud()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	// Datos Paciente
	    	PacienteVO paciente = new PacienteVO();
	    	paciente.setIdPaciente(rs.getInt(3));
	    	paciente.setNombre(rs.getString(4));
	    	// Datos Laboratorio
	    	LaboratorioVO laboratorio = new LaboratorioVO();
	    	laboratorio.setIdLaboratorio(rs.getInt(5));
	    	laboratorio.setNombre(rs.getString(6));
	    	// Datos Solicitud
	    	SolicitudProtesisVO solicitud = new SolicitudProtesisVO();
	    	solicitud.setIdSolicitud(rs.getInt(1));
	    	solicitud.setFecha(rs.getString(2));
	    	solicitud.setPaciente(paciente);
	    	solicitud.setLaboratorio(laboratorio);
	    	solicitud.setEstado(rs.getBoolean(7));
			listasolicitud.add(solicitud);		
		}
	    rs.close();
		cs.close();
	    return listasolicitud;
	}
	public ArrayList<SolicitudProtesisVO> filtroSolicitudProtesis(String table,String field,String value) throws SQLException{
		ArrayList<SolicitudProtesisVO> listasolicitud = new ArrayList<SolicitudProtesisVO>();
		consulta = new CRUD_DAO();
		String query = consulta.filterTable(table);
		query = query + " " + field + " = "+ value;
		// Preparar el procedimiento antes de ejecutar
	    ps = Conexion.getConnection().prepareStatement(query);
    // Ejecutar Procedimiento
	    ResultSet rs = 	 ps.executeQuery();
	    while(rs.next()) {
	    	// Datos Paciente
	    	PacienteVO paciente = new PacienteVO();
	    	paciente.setIdPaciente(rs.getInt(3));
	    	paciente.setNombre(rs.getString(4));
	    	// Datos Laboratorio
	    	LaboratorioVO laboratorio = new LaboratorioVO();
	    	laboratorio.setIdLaboratorio(rs.getInt(5));
	    	laboratorio.setNombre(rs.getString(6));
	    	// Datos Solicitud
	    	SolicitudProtesisVO solicitud = new SolicitudProtesisVO();
	    	solicitud.setIdSolicitud(rs.getInt(1));
	    	solicitud.setFecha(rs.getString(2));
	    	solicitud.setPaciente(paciente);
	    	solicitud.setLaboratorio(laboratorio);
	    	solicitud.setEstado(rs.getBoolean(7));
			listasolicitud.add(solicitud);		
		}
	    rs.close();
		ps.close();
	    return listasolicitud;
	}
}
