package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Conexion.Conexion;
import Modelo.VO.CitasVO;
import Modelo.VO.Detalle_DiagnosticoVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.SexoVO;
import Modelo.VO.UsuarioVO;
import Vista.ComponentCustomization;
import Modelo.VO.DiagnosticoVO;;

public class DiagnosticoDAO {
	private  CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	private CRUD_DAO consulta;
	private java.sql.PreparedStatement ps;
	/* Crear Diagnostico */
	public void RealizarDiagnostico(DiagnosticoVO diagnostico) {
	try {
		// Preparar el procedimiento antes de ejecutar
		     cs = Conexion.getConnection().prepareCall("Call RealizarDiagnostico(?,?)");
		    // Ingresar Parametros
		    cs.setInt(1, diagnostico.getPaciente().getIdPaciente());
		    cs.setInt(2, diagnostico.getUsuario().getIdUsuario());
	    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("Diagnostico Registrado", 4);  
		    }
		catch (SQLException e) {
	        e.printStackTrace();
	    }}
	
		
	public ArrayList<DiagnosticoVO> listaDiagnostico() throws SQLException{
		ArrayList<DiagnosticoVO> listaDiagnostico = new ArrayList<DiagnosticoVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectAllDiagnostico()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	// Datos Paciente
	    	PacienteVO paciente = new PacienteVO();
	    	paciente.setIdPaciente(rs.getInt(2));
	    	paciente.setNombre(rs.getString(3));
	    	// Datos EMpleado
	    	EmpleadoVO empleado = new EmpleadoVO();
	    	empleado.setIdEmpleado(rs.getInt(6));
	    	empleado.setNombre(rs.getString(7));
	    	// Datos Usuario
	    	UsuarioVO usuario = new UsuarioVO();
	    	usuario.setIdUsuario(rs.getInt(4));
	    	usuario.setNombre(rs.getString(5));
	    	usuario.setEmpleado(empleado);
	    	// Datos Diagnostico
	    	DiagnosticoVO diagnostico = new DiagnosticoVO();
	    	diagnostico.setIdDiagnostico(rs.getInt(1));
	    	diagnostico.setFechaDiagnostico(rs.getString(8));
	    	diagnostico.setPaciente(paciente);
	    	diagnostico.setUsuario(usuario);
			listaDiagnostico.add(diagnostico);		
		}
	    rs.close();
		cs.close();
	    return listaDiagnostico;
	}public DiagnosticoVO buscarDiagnostico(int id) {
		DiagnosticoVO diagnostico = new DiagnosticoVO();
	    try {
			// Preparar el procedimiento antes de ejecutar
			   cs = Conexion.getConnection().prepareCall("Call selectidDiagnostico(?)");
			    // Ingresar Parametros
			    cs.setInt(1, id);
			    // Ejecutar Procedimiento
			    ResultSet rs = 	 cs.executeQuery();
			    while(rs.next()) {
			    	PacienteVO paciente = new PacienteVO();
			    	paciente.setIdPaciente(rs.getInt(2));
			    	paciente.setNombre(rs.getString(3));
			    	// Datos EMpleado
			    	EmpleadoVO empleado = new EmpleadoVO();
			    	empleado.setIdEmpleado(rs.getInt(6));
			    	empleado.setNombre(rs.getString(7));
			    	// Datos Usuario
			    	UsuarioVO usuario = new UsuarioVO();
			    	usuario.setIdUsuario(rs.getInt(4));
			    	usuario.setNombre(rs.getString(5));
			    	usuario.setEmpleado(empleado);
			    	// Datos Diagnostico
			    	diagnostico = new DiagnosticoVO();
			    	diagnostico.setIdDiagnostico(rs.getInt(1));
			    	diagnostico.setFechaDiagnostico(rs.getString(8));
			    	diagnostico.setPaciente(paciente);	
			    	diagnostico.setUsuario(usuario);
			    }
			    rs.close();
			    cs.close();
		} 
			catch (SQLException e) {
		        custom.msg("Diagnostico", 2);}
		return diagnostico;
		}
	
	public ArrayList<DiagnosticoVO> filtroDiagnostico(String table,String field,String value) throws SQLException{
		ArrayList<DiagnosticoVO> listaDiagnostico = new ArrayList<DiagnosticoVO>();
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
	    	paciente.setIdPaciente(rs.getInt(2));
	    	paciente.setNombre(rs.getString(3));
	    	
	    	DiagnosticoVO diagnostico = new DiagnosticoVO();
	    	diagnostico.setIdDiagnostico(rs.getInt(1));
	    	diagnostico.setFechaDiagnostico(rs.getString(4));
	    	diagnostico.setPaciente(paciente);
			listaDiagnostico.add(diagnostico);		
		}
	    rs.close();
		ps.close();
	    return listaDiagnostico;
	}
}
