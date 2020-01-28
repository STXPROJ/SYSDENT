package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Conexion.Conexion;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.HistorialClinicoVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.RolVO;
import Modelo.VO.SexoVO;
import Vista.ComponentCustomization;

public class HistorialClinicoDAO {
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	public void registrarHistorialClinico(HistorialClinicoVO historial) throws ClassNotFoundException {
		try {
		// Preparar el procedimiento antes de ejecutar
		     cs = Conexion.getConnection().prepareCall("Call updateHistorial(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		    // Ingresar Parametros
		    cs.setInt(1,historial.getPaciente().getIdPaciente());
		    cs.setBoolean(2, historial.isDiabetes());
		    cs.setBoolean(3,historial.isLesionCongenita());
		    cs.setBoolean(4,historial.isHipertension());
		    cs.setBoolean(5,historial.isCefalea());
		    cs.setBoolean(6,historial.isMareos());
		    cs.setBoolean(7,historial.isHemofilia());
		    cs.setBoolean(8,historial.isEndocarditis());
		    cs.setBoolean(9,historial.isHepatitis());
		    cs.setBoolean(10,historial.isAsma());
		    cs.setBoolean(11,historial.isFiebre());
		    cs.setBoolean(12,historial.isArtritis());
		    cs.setBoolean(13,historial.isCancer());
		    cs.setBoolean(14,historial.isNervios());
		    cs.setBoolean(15,historial.isHemorragia());
		    cs.setBoolean(16,historial.isAlergaAnestesia());
		    cs.setBoolean(17,historial.isAlergiaAlimentos());
		    cs.setBoolean(18,historial.isalergiaAntibiotico());
		    cs.setBoolean(19,historial.isVenerea());
		    cs.setBoolean(20,historial.isVarices());
		    cs.setBoolean(21,historial.isRx());
		    cs.setBoolean(22,historial.isTx());
		    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("Historial Clinico Actualizado con exito", 4);  }
		catch (SQLException e) {
	        e.printStackTrace();
	    }}
	public HistorialClinicoVO buscarHistorial(int id) {
	    HistorialClinicoVO historial = new HistorialClinicoVO();
	    try {
			// Preparar el procedimiento antes de ejecutar
			   cs = Conexion.getConnection().prepareCall("Call selectHistorial(?)");
			    // Ingresar Parametros
			    cs.setInt(1, id);
			    // Ejecutar Procedimiento
			    ResultSet rs = 	 cs.executeQuery();
			    while(rs.next()) {
			    	PacienteVO paciente = new PacienteVO();
			    	// Datos Paciente
			    	paciente.setIdPaciente(rs.getInt(2));
			    	paciente.setNombre(rs.getString(1));
			    	// Datos Historial
			    	historial.setPaciente(paciente);
			    	historial.setDiabetes(rs.getBoolean(3));
			    	historial.setLesionCongenita(rs.getBoolean(4));
			    	historial.setHipertension(rs.getBoolean(5));
			    	historial.setCefalea(rs.getBoolean(6));
			    	historial.setMareos(rs.getBoolean(7));
			    	historial.setHemofilia(rs.getBoolean(8));
			    	historial.setEndocarditis(rs.getBoolean(9));
			    	historial.setHepatitis(rs.getBoolean(10));
			    	historial.setAsma(rs.getBoolean(11));
			    	historial.setFiebre(rs.getBoolean(12));
			    	historial.setArtritis(rs.getBoolean(13));
			    	historial.setCancer(rs.getBoolean(14));
			    	historial.setNervios(rs.getBoolean(15));
			    	historial.setHemorragia(rs.getBoolean(16));
			    	historial.setAlergaAnestesia(rs.getBoolean(17));
			    	historial.setAlergiaAlimentos(rs.getBoolean(18));
			    	historial.setalergiaAntibiotico(rs.getBoolean(19));
			    	historial.setVenerea(rs.getBoolean(20));
			    	historial.setVarices(rs.getBoolean(21));
			    	historial.setRx(rs.getBoolean(22));
			    	historial.setTx(rs.getBoolean(23));
			    	historial.setPaciente(paciente);
			    }
			    rs.close();
			    cs.close();
		} 
			catch (SQLException e) {
		        custom.msg("Historial", 2);}
		return historial;
		}
	
}
