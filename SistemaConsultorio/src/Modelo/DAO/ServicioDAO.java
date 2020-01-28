package Modelo.DAO;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.VO.CitasVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.TipoServicioVO;
import Vista.ComponentCustomization;
import Modelo.VO.ServicioVO;
import Conexion.Conexion;
public class ServicioDAO {
	private CallableStatement cs ;
	private TipoServicioVO tipoServicio;
	private ComponentCustomization custom = new ComponentCustomization();
	private CRUD_DAO consulta;
	private java.sql.PreparedStatement ps;
	public void RegistrarServicio(ServicioVO servicio) throws ClassNotFoundException {
		try {
		// Preparar el procedimiento antes de ejecutar
		   cs = Conexion.getConnection().prepareCall("Call insertServicio(?,?,?,?)");
		    // Ingresar Parametros
		    cs.setInt(1, servicio.getTipoServicio().getIdTipo());
		    cs.setString(2, servicio.getNombre());;
		    cs.setString(3, servicio.getDescripcion());
		    cs.setDouble(4, servicio.getPrecio());
		    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("Servicio Registrado con exito", 4);    }
		catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public ArrayList<ServicioVO> listaServicio() throws SQLException{
		ArrayList<ServicioVO> servicios = new ArrayList<ServicioVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("Call selectServicio()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	// Datos Tipo Servicio
	    	tipoServicio = new TipoServicioVO();
	  		tipoServicio.setIdTipo(rs.getInt(4));
	  		tipoServicio.setNombre(rs.getString(6));
	  		// Datos Servicio
	    	ServicioVO servicio = new ServicioVO();
			servicio.setIdServicio(rs.getInt(1));
			servicio.setTipoServicio(tipoServicio);
			servicio.setNombre(rs.getString(2));
			servicio.setDescripcion(rs.getString(3));
			servicio.setPrecio(rs.getDouble(4));
			servicios.add(servicio);		
		}
	    rs.close();
		cs.close();
	    return servicios;
	}
		
public ArrayList<ServicioVO> filtroServicio(String table,String field,String value) throws SQLException{
	ArrayList<ServicioVO> servicios = new ArrayList<ServicioVO>();
	consulta = new CRUD_DAO();
	String query = consulta.filterTable(table);
	query = query + " " + field + " = "+ value;
	// Preparar el procedimiento antes de ejecutar
    ps = Conexion.getConnection().prepareStatement(query);
// Ejecutar Procedimiento
    ResultSet rs = 	 ps.executeQuery();
    while(rs.next()) {
    	// Datos Tipo Servicio
    	tipoServicio = new TipoServicioVO();
  		tipoServicio.setIdTipo(rs.getInt(4));
  		tipoServicio.setNombre(rs.getString(6));
  		// Datos Servicio
    	ServicioVO servicio = new ServicioVO();
		servicio.setIdServicio(rs.getInt(1));
		servicio.setTipoServicio(tipoServicio);
		servicio.setNombre(rs.getString(2));
		servicio.setDescripcion(rs.getString(3));
		servicio.setPrecio(rs.getDouble(4));
		servicios.add(servicio);		
	}
    rs.close();
	ps.close();
    return servicios;
}
}