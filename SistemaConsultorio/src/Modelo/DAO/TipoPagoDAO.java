package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.VO.RolVO;
import Modelo.VO.TipoPagoVO;
import Vista.ComponentCustomization;

public class TipoPagoDAO {
		private CallableStatement cs;
		private ComponentCustomization custom = new ComponentCustomization();
		public void registrarTipoPago(TipoPagoVO tipo) throws ClassNotFoundException {
			try {
			// Preparar el procedimiento antes de ejecutar
			     cs = Conexion.getConnection().prepareCall("Call insertTipoPago(?)");
			    // Ingresar Parametros
			    cs.setString(1, tipo.getTipo());
			    // Ejecutar Procedimiento
			    cs.execute();
			    cs.close();
			    custom.msg("Forma de Pago registrada con exito", 4);  }
			catch (SQLException e) {
		        e.printStackTrace();
		    }}
		public ArrayList<TipoPagoVO> listaTipoPago() throws SQLException{
			ArrayList<TipoPagoVO> listaTipo = new ArrayList<TipoPagoVO>();
			// Preparar el procedimiento antes de ejecutar
		    cs = Conexion.getConnection().prepareCall("call selectTipoPago()");
	    // Ejecutar Procedimiento
		    ResultSet rs = 	 cs.executeQuery();
		    while(rs.next()) {
		    	TipoPagoVO tipo = new TipoPagoVO();
		    	tipo.setIdTipo(rs.getInt(1));
		    	tipo.setTipo(rs.getString(2));
				listaTipo.add(tipo);	
			}
		    rs.close();
			cs.close();
		    return listaTipo;
		}
	}

