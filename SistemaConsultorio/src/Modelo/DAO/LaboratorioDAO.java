package Modelo.DAO;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.VO.CitasVO;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.SexoVO;
import Vista.ComponentCustomization;
import Modelo.VO.LaboratorioVO;
import Conexion.Conexion;
public class LaboratorioDAO {
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	private CRUD_DAO consulta;
	private java.sql.PreparedStatement ps;
	public void RegistrarLaboratorio(LaboratorioVO laboratorio) throws ClassNotFoundException {
		try {
		// Preparar el procedimiento antes de ejecutar
		     cs = Conexion.getConnection().prepareCall("Call insertLaboratorio(?,?,?,?,?,?,?)");
		    // Ingresar Parametros
		    cs.setString(1, laboratorio.getNombre());
		    cs.setString(2,laboratorio.getTelefono());
		    cs.setString(3, laboratorio.getRnc());
		    cs.setString(4, laboratorio.getContacto());
		    cs.setString(5, laboratorio.getCorreo());
		    cs.setString(6, laboratorio.getDireccion());
		    cs.setString(7, laboratorio.getDescripcion());
		    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("Laboratorio Registrado con exito", 4);    }
		catch (SQLException e) {
	        e.printStackTrace();
	    }	}
	public ArrayList<LaboratorioVO> listalaboratorio() throws SQLException{
		ArrayList<LaboratorioVO> listalaboratorio = new ArrayList<LaboratorioVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectAllLaboratorio()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	LaboratorioVO laboratorio = new LaboratorioVO();
	    	laboratorio.setIdLaboratorio(rs.getInt(1));
	    	laboratorio.setNombre(rs.getString(2));
	    	laboratorio.setTelefono(rs.getString(3));
	    	laboratorio.setRnc(rs.getString(4));
	    	laboratorio.setContacto(rs.getString(5));
	    	laboratorio.setCorreo(rs.getString(6));
	    	laboratorio.setDireccion(rs.getString(7));
	    	laboratorio.setDescripcion(rs.getString(8));
	    	listalaboratorio.add(laboratorio);
		}
	    rs.close();
		cs.close();
	    return listalaboratorio;
	}
	public ArrayList<LaboratorioVO> filtroLaboratorio(String table,String field,String value) throws SQLException{
		ArrayList<LaboratorioVO> listalaboratorio = new ArrayList<LaboratorioVO>();
		consulta = new CRUD_DAO();
		String query = consulta.filterTable(table);
		query = query + " " + field + " = "+ value;
		// Preparar el procedimiento antes de ejecutar
	    ps = Conexion.getConnection().prepareStatement(query);
    // Ejecutar Procedimiento
	    ResultSet rs = 	 ps.executeQuery();
	    while(rs.next()) {
	    	LaboratorioVO laboratorio = new LaboratorioVO();
	    	laboratorio.setIdLaboratorio(rs.getInt(1));
	    	laboratorio.setNombre(rs.getString(2));
	    	laboratorio.setTelefono(rs.getString(3));
	    	laboratorio.setRnc(rs.getString(4));
	    	laboratorio.setContacto(rs.getString(5));
	    	laboratorio.setCorreo(rs.getString(6));
	    	laboratorio.setDireccion(rs.getString(7));
	    	laboratorio.setDescripcion(rs.getString(8));
	    	listalaboratorio.add(laboratorio);
		}
	    rs.close();
		ps.close();
	    return listalaboratorio;
	}
	public LaboratorioVO buscarLaboratorio(int id) throws SQLException {
	    LaboratorioVO laboratorio = new LaboratorioVO();
			// Preparar el procedimiento antes de ejecutar
			   cs = Conexion.getConnection().prepareCall("Call selectidLaboratorio(?)");
			    // Ingresar Parametros
			    cs.setInt(1, id);
			    // Ejecutar Procedimiento
			    ResultSet rs = 	 cs.executeQuery();
			    while(rs.next()) {
			    	// Datos laboratorio
			    	laboratorio.setIdLaboratorio(rs.getInt(1));
			    	laboratorio.setNombre(rs.getString(2));
			    	laboratorio.setTelefono(rs.getString(3));
			    	laboratorio.setRnc(rs.getString(4));
			    	laboratorio.setContacto(rs.getString(5));
			    	laboratorio.setCorreo(rs.getString(6));
			    	laboratorio.setDireccion(rs.getString(7));
			    	laboratorio.setDescripcion(rs.getString(8));
			    }
			    rs.close();
			    cs.close();
			    return laboratorio;
			}
}