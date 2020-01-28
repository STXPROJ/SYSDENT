package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.VO.EstadoCitaVO;
import Modelo.VO.EstadoCivilVO;
import Vista.ComponentCustomization;

public class EstadoCivilDAO {
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	public void addEstado(EstadoCivilVO estado) {
		try {
			// Preparar el procedimiento antes de ejecutar
			     cs = Conexion.getConnection().prepareCall("Call insertEstado(?)");
			    // Ingresar Parametros
			    cs.setString(1, estado.getNombre());
			   
		    // Ejecutar Procedimiento
			    cs.execute();
			    cs.close();
			    custom.msg("Estado Civil agregado con exito", 4);
		}
			catch (SQLException e) {
		        e.printStackTrace();
			}}
	public ArrayList<EstadoCivilVO> listaestado() throws SQLException{
		ArrayList<EstadoCivilVO> listaestado = new ArrayList<EstadoCivilVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectestado()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	EstadoCivilVO estado = new EstadoCivilVO();
	    	estado.setIdEstado(rs.getInt(1));
	    	estado.setNombre(rs.getString(2));
			listaestado.add(estado);	
		}
	    rs.close();
		cs.close();
	    return listaestado;
	}
}
