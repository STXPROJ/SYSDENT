package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexion.Conexion;
import Modelo.VO.DentagramaVO;
import Modelo.VO.HistorialClinicoVO;
import Modelo.VO.PacienteVO;
import Vista.ComponentCustomization;

public class DentagramaDAO {
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	private DentagramaVO dentagrama;
	public void registrarDentagrama(DentagramaVO dentagrama) throws ClassNotFoundException {
		try {
		// Preparar el procedimiento antes de ejecutar
		     cs = Conexion.getConnection().prepareCall("Call insertDentagrama(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
		     		+ ",?,?,?,?,?,?,?,?,?,?)");
		    // Ingresar Parametros
		    cs.setInt(1,dentagrama.getPaciente().getIdPaciente());
		    cs.setInt(2, dentagrama.getDiagnostico().getIdDiagnostico());
		    cs.setBoolean(3, dentagrama.isIu8());
		    cs.setBoolean(4, dentagrama.isIu7());
		    cs.setBoolean(5, dentagrama.isIu6());
		    cs.setBoolean(6, dentagrama.isIu5());
		    cs.setBoolean(7, dentagrama.isIu4());
		    cs.setBoolean(8, dentagrama.isIu3());
		    cs.setBoolean(9, dentagrama.isIu2());
		    cs.setBoolean(10, dentagrama.isIu1());
		    cs.setBoolean(11, dentagrama.isId8());
		    cs.setBoolean(12, dentagrama.isId7());
		    cs.setBoolean(13, dentagrama.isId6());
		    cs.setBoolean(14, dentagrama.isId5());
		    cs.setBoolean(15, dentagrama.isId4());
		    cs.setBoolean(16, dentagrama.isId3());
		    cs.setBoolean(17, dentagrama.isId2());
		    cs.setBoolean(18, dentagrama.isId1());
		    cs.setBoolean(19, dentagrama.isDu8());
		    cs.setBoolean(20, dentagrama.isDu7());
		    cs.setBoolean(21, dentagrama.isDu6());
		    cs.setBoolean(22, dentagrama.isDu5());
		    cs.setBoolean(23, dentagrama.isDu4());
		    cs.setBoolean(24, dentagrama.isDu3());
		    cs.setBoolean(25, dentagrama.isDu2());
		    cs.setBoolean(26, dentagrama.isDu1());
		    cs.setBoolean(27, dentagrama.isDu8());
		    cs.setBoolean(28, dentagrama.isDu7());
		    cs.setBoolean(29, dentagrama.isDu6());
		    cs.setBoolean(30, dentagrama.isDu5());
		    cs.setBoolean(31, dentagrama.isDu4());
		    cs.setBoolean(32, dentagrama.isDu3());
		    cs.setBoolean(33, dentagrama.isDu2());
		    cs.setBoolean(34, dentagrama.isDu1());

		    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		  }
		catch (SQLException e) {
	        e.printStackTrace();
	    }}
	public DentagramaVO buscarDentagrama(int idpaciente,int iddiagnostico) {
		dentagrama = new DentagramaVO();
	    try {
			// Preparar el procedimiento antes de ejecutar
			   cs = Conexion.getConnection().prepareCall("Call selectDentagrama(?,?)");
			    // Ingresar Parametros
			    cs.setInt(1, idpaciente);
			    cs.setInt(2, iddiagnostico);
			    System.out.println(iddiagnostico);
			    System.out.println(idpaciente);
			    // Ejecutar Procedimiento
			    ResultSet rs = 	 cs.executeQuery();
			    while(rs.next()) {
			    	// Datos Dentagrama
			    	dentagrama.setIu8(rs.getBoolean(3));
			    	dentagrama.setIu7(rs.getBoolean(4));
			    	dentagrama.setIu6(rs.getBoolean(5));
			    	dentagrama.setIu5(rs.getBoolean(6));
			    	dentagrama.setIu4(rs.getBoolean(7));
			    	dentagrama.setIu3(rs.getBoolean(8));
			    	dentagrama.setIu2(rs.getBoolean(9));
			    	dentagrama.setIu1(rs.getBoolean(10));
			    	dentagrama.setId8(rs.getBoolean(11));
			    	dentagrama.setId7(rs.getBoolean(12));
			    	dentagrama.setId6(rs.getBoolean(13));
			    	dentagrama.setId5(rs.getBoolean(14));
			    	dentagrama.setId4(rs.getBoolean(15));
			    	dentagrama.setId3(rs.getBoolean(16));
			    	dentagrama.setId2(rs.getBoolean(17));
			    	dentagrama.setId1(rs.getBoolean(18));
			    	dentagrama.setDu8(rs.getBoolean(19));
			    	dentagrama.setDu7(rs.getBoolean(20));
			    	dentagrama.setDu6(rs.getBoolean(21));
			    	dentagrama.setDu5(rs.getBoolean(22));
			    	dentagrama.setDu4(rs.getBoolean(23));
			    	dentagrama.setDu3(rs.getBoolean(24));
			    	dentagrama.setDu2(rs.getBoolean(25));
			    	dentagrama.setDu1(rs.getBoolean(26));
			    	dentagrama.setDd8(rs.getBoolean(27));
			    	dentagrama.setDd7(rs.getBoolean(28));
			    	dentagrama.setDd6(rs.getBoolean(29));
			    	dentagrama.setDd5(rs.getBoolean(30));
			    	dentagrama.setDd4(rs.getBoolean(31));
			    	dentagrama.setDd3(rs.getBoolean(32));
			    	dentagrama.setDd2(rs.getBoolean(33));
			    	dentagrama.setDd1(rs.getBoolean(34));
			    }
			    rs.close();
			    cs.close();
		} 
			catch (SQLException e) {
		        custom.msg("Dentagrama", 2);}
		return dentagrama;
		}
	
}
