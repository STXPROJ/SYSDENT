package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.VO.RolVO;
import Vista.ComponentCustomization;
import Modelo.VO.RolVO;

public class RolDAO {
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	public void registrarrol(RolVO rol) throws ClassNotFoundException {
		try {
		// Preparar el procedimiento antes de ejecutar
		     cs = Conexion.getConnection().prepareCall("Call insertrol(?,?)");
		    // Ingresar Parametros
		    cs.setString(1, rol.getNombre());
		    cs.setString(2, rol.getDescripcion());
		    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("Rol registrado con exito", 4);  }
		catch (SQLException e) {
	        e.printStackTrace();
	    }}
	public ArrayList<RolVO> listarol() throws SQLException{
		ArrayList<RolVO> listarol = new ArrayList<RolVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectRol()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	RolVO rol = new RolVO();
	    	rol.setIdRol(rs.getInt(1));
	    	rol.setNombre(rs.getString(2));
	    	rol.setDescripcion(rs.getString(3));
			listarol.add(rol);	
		}
	    rs.close();
		cs.close();
	    return listarol;
	}
}
