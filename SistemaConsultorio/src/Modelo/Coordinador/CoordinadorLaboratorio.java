package Modelo.Coordinador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Logica.LogicaLaboratorio;
import Modelo.VO.LaboratorioVO;

public class CoordinadorLaboratorio {
private LogicaLaboratorio logicaLaboratorio;

public LogicaLaboratorio getLogica() {
	return logicaLaboratorio;
}
public void setLogica(LogicaLaboratorio logicaLaboratorio) {
	this.logicaLaboratorio = logicaLaboratorio;
}

public boolean addLaboratorio(LaboratorioVO laboratorio) {
	return logicaLaboratorio.validarAddLaboratorio(laboratorio);
}
public ArrayList<LaboratorioVO> listaLaboratorio() throws SQLException{
	return logicaLaboratorio.validarListaLaboratorio();
}
}
