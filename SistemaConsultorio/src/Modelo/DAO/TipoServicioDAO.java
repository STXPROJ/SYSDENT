package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.VO.TipoServicioVO;
import Vista.ComponentCustomization;

public class TipoServicioDAO {
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	public void registrartipoServicio(TipoServicioVO tipoServicio) throws ClassNotFoundException {
		try {
		// Preparar el procedimiento antes de ejecutar
		     cs = Conexion.getConnection().prepareCall("Call insertTipoServicio(?,?)");
		    // Ingresar Parametros
		    cs.setString(1, tipoServicio.getNombre());
		    cs.setString(2, tipoServicio.getDescripcion());
		    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("Tipo de Servicio registrado con exito", 4);  }
		catch (SQLException e) {
	        e.printStackTrace();
	    }}
	public ArrayList<TipoServicioVO> listatipoServicio() throws SQLException{
		ArrayList<TipoServicioVO> listatipoServicio = new ArrayList<TipoServicioVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selecttipoServicio()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	TipoServicioVO tipoServicio = new TipoServicioVO();
	    	tipoServicio.setIdTipo(rs.getInt(1));
	    	tipoServicio.setNombre(rs.getString(2));
	    	tipoServicio.setDescripcion(rs.getString(3));
			listatipoServicio.add(tipoServicio);	
		}
	    rs.close();
		cs.close();
	    return listatipoServicio;
	}
}
