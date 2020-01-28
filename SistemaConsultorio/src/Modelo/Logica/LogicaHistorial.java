package Modelo.Logica;

import javax.swing.JOptionPane;

import Modelo.Coordinador.CoordinadorCRUD;
import Modelo.Coordinador.CoordinadorHistorial;
import Modelo.DAO.CitasDAO;
import Modelo.DAO.HistorialClinicoDAO;
import Modelo.DAO.PacienteDAO;
import Modelo.VO.HistorialClinicoVO;
import Vista.ComponentCustomization;

public class LogicaHistorial {
	private CoordinadorHistorial coordHistorial;
	private HistorialClinicoDAO consultaHistorial;
	private ComponentCustomization custom = new ComponentCustomization();
	public void setCoordinador(CoordinadorHistorial coordHistorial) {
		this.coordHistorial = coordHistorial;
	}
	public boolean validaraddHistorial(HistorialClinicoVO historial) throws ClassNotFoundException {
		boolean bool = false;
		consultaHistorial = new  HistorialClinicoDAO();
		if(historial.getPaciente().getNombre() == null)
		{custom.msg("",1);}
		else {
			if(custom.msgConfirm("Guardar Historial Clinico?", 1) == JOptionPane.YES_OPTION){
				consultaHistorial.registrarHistorialClinico(historial);
				bool = true;
			}}return bool;
}
	public HistorialClinicoVO validarBuscarHistorial(int id) {
		consultaHistorial = new HistorialClinicoDAO();
		if(consultaHistorial.buscarHistorial(id) == null ) {
			custom.msg("Historial", 2);
			System.out.println("nada");
			return null;
		
		}else {	
		return consultaHistorial.buscarHistorial(id);
	}	
	}

	}