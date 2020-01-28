package Modelo.Coordinador;

import Modelo.Logica.LogicaCRUD;
import Modelo.Logica.LogicaHistorial;
import Modelo.VO.HistorialClinicoVO;

public class CoordinadorHistorial {
	private LogicaHistorial logicaHistorial;
	private LogicaHistorial getLogica() {
		return logicaHistorial;
		}
	public void setLogica(LogicaHistorial logicaHistorial) {
		this.logicaHistorial = logicaHistorial;
	}
	public boolean addHistorial(HistorialClinicoVO historial) throws ClassNotFoundException {
	return logicaHistorial.validaraddHistorial(historial);	
	}
	public HistorialClinicoVO getHistorial(int id) {
		return logicaHistorial.validarBuscarHistorial(id);
	}
	}
