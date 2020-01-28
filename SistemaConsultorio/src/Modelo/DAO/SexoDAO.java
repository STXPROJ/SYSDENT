package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.VO.SexoVO;

public class SexoDAO {
	private CallableStatement cs;
	public ArrayList<SexoVO> listaSexo() throws SQLException{
		ArrayList<SexoVO> listaSexo = new ArrayList<SexoVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectSexo()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	SexoVO sexo = new SexoVO();
	    	sexo.setIdSexo(rs.getInt(1));
	    	sexo.setLetra(rs.getString(2));
	    	sexo.setGenero(rs.getString(3));
			listaSexo.add(sexo);	
		}
	    rs.close();
		cs.close();
	    return listaSexo;
	}
}
