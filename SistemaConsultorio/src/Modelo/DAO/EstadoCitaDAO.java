package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.VO.EstadoCitaVO;
import Modelo.VO.SexoVO;
import Vista.ComponentCustomization;

public class EstadoCitaDAO {
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	public void addEstado(EstadoCitaVO estado) {
		try {
			// Preparar el procedimiento antes de ejecutar
			     cs = Conexion.getConnection().prepareCall("Call insertEstadoCita(?)");
			    // Ingresar Parametros
			    cs.setString(1, estado.getNombre());
			   
		    // Ejecutar Procedimiento
			    cs.execute();
			    cs.close();
			    custom.msg("Estado de cita agregado con exito", 4);
		}
			catch (SQLException e) {
		        e.printStackTrace();
			}}
	public ArrayList<EstadoCitaVO> listaEstado() throws SQLException{
		ArrayList<EstadoCitaVO> listadoEstado = new ArrayList<EstadoCitaVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectEstadoCita()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	EstadoCitaVO estado = new EstadoCitaVO();
	    	estado.setIdEstado(rs.getInt(1));
	    	estado.setNombre(rs.getString(2));
			listadoEstado.add(estado);	
		}
	    rs.close();
		cs.close();
	    return listadoEstado;
	}
}
