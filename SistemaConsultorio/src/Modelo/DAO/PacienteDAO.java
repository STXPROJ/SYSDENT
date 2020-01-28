package Modelo.DAO;
import java.awt.JobAttributes;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import Modelo.VO.CitasVO;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.SexoVO;
import Vista.ComponentCustomization;
import Modelo.VO.PacienteVO;
import Conexion.Conexion;
public class PacienteDAO {
	private  CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	private CRUD_DAO consulta;
	private java.sql.PreparedStatement ps;
	public void RegistrarPaciente(PacienteVO paciente) throws ClassNotFoundException {
		try {
		// Preparar el procedimiento antes de ejecutar
		   cs = Conexion.getConnection().prepareCall("Call insertPaciente(?,?,?,?,?,?,?)");
		    // Ingresar Parametros
		   	/* Datos Sexo */
		 
		    cs.setString(1, paciente.getNombre());
		    cs.setInt(2, paciente.getSexo().getIdSexo());
		    cs.setString(3, paciente.getFechaIngreso());
		    cs.setString(4, paciente.getFechaNac());
		    cs.setInt(5, paciente.getEstadoCivil().getIdEstado());
		    cs.setString(6, paciente.getOcupacion());
		    cs.setString(7, paciente.getTelefono());
		    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("Se Agrego el Paciente", 4);   }
		catch (SQLException e) {
	        e.printStackTrace();
	    }	}
	public ArrayList<PacienteVO> listapaciente() throws SQLException{
		ArrayList<PacienteVO> listapaciente = new ArrayList<PacienteVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectAllpaciente()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	/* Datos Sexo */
	    	SexoVO sexo = new SexoVO();
	    	sexo.setIdSexo(rs.getInt(3));
	    	sexo.setGenero(rs.getString(4));
	    	// Datos Estado
	    	EstadoCivilVO estado = new EstadoCivilVO();
	    	estado.setIdEstado(rs.getInt(7));
	    	estado.setNombre(rs.getString(8));
	    	PacienteVO paciente = new PacienteVO();
	    	paciente.setIdPaciente(rs.getInt(1));
	    	paciente.setNombre(rs.getString(2));
	    	paciente.setSexo(sexo);
	    	paciente.setFechaIngreso(rs.getString(5));
	    	paciente.setFechaNac(rs.getString(6));
	    	paciente.setEstadoCivil(estado);
	    	paciente.setOcupacion(rs.getString(9));
	    	paciente.setTelefono(rs.getString(10));
	    	paciente.setBalance(rs.getDouble(11));
			listapaciente.add(paciente);	
		}
	    rs.close();
		cs.close();
	    return listapaciente;
	}
	public ArrayList<PacienteVO> filtroPaciente(String table,String field,String value) throws SQLException{
		ArrayList<PacienteVO> listapaciente = new ArrayList<PacienteVO>();
		consulta = new CRUD_DAO();
		String query = consulta.filterTable(table);
		query = query + " " + field + " = "+ value;
		// Preparar el procedimiento antes de ejecutar
	    ps = Conexion.getConnection().prepareStatement(query);
    // Ejecutar Procedimiento
	    ResultSet rs = 	 ps.executeQuery();
	    while(rs.next()) {
	    	/* Datos Sexo */
	    	SexoVO sexo = new SexoVO();
	    	sexo.setIdSexo(rs.getInt(3));
	    	sexo.setGenero(rs.getString(4));
	    	// Datos Estado
	    	EstadoCivilVO estado = new EstadoCivilVO();
	    	estado.setIdEstado(rs.getInt(7));
	    	estado.setNombre(rs.getString(8));
	    	PacienteVO paciente = new PacienteVO();
	    	paciente.setIdPaciente(rs.getInt(1));
	    	paciente.setNombre(rs.getString(2));
	    	paciente.setSexo(sexo);
	    	paciente.setFechaIngreso(rs.getString(5));
	    	paciente.setFechaNac(rs.getString(6));
	    	paciente.setEstadoCivil(estado);
	    	paciente.setOcupacion(rs.getString(9));
	    	paciente.setTelefono(rs.getString(10));
	    	paciente.setBalance(rs.getDouble(11));
			listapaciente.add(paciente);	
		}
	    rs.close();
		ps.close();
	    return listapaciente;
	}
	public PacienteVO buscarPaciente(int id) {
	    PacienteVO paciente = new PacienteVO();
	    try {
			// Preparar el procedimiento antes de ejecutar
			   cs = Conexion.getConnection().prepareCall("Call selectidPaciente(?)");
			    // Ingresar Parametros
			    cs.setInt(1, id);
			    // Ejecutar Procedimiento
			    ResultSet rs = 	 cs.executeQuery();
			    while(rs.next()) {
			    	// Datos Sexo
			    	SexoVO sexo = new SexoVO();
			    	sexo.setIdSexo(rs.getInt(3));
			    	sexo.setGenero(rs.getString(4));
			    	// Datos Estado
			    	EstadoCivilVO estado = new EstadoCivilVO();
			    	estado.setIdEstado(rs.getInt(7));
			    	estado.setNombre(rs.getString(8));
			    	// Datos Paciente
			    	paciente.setIdPaciente(rs.getInt(1));
			    	paciente.setNombre(rs.getString(2));
			    	paciente.setSexo(sexo);
			    	paciente.setEstadoCivil(estado);
			    	paciente.setFechaIngreso(rs.getString(5));
			    	paciente.setFechaNac(rs.getString(6));
			    	paciente.setOcupacion(rs.getString(9));
			    	paciente.setTelefono(rs.getString(10));
			    	paciente.setBalance(rs.getDouble(11));
			    }
			    rs.close();
			    cs.close();
		} 
			catch (SQLException e) {
		        custom.msg("Paciente", 2);}
		return paciente;
		}
	
}

