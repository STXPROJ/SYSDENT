package Modelo.Coordinador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Logica.LogicaServicio;
import Modelo.VO.ServicioVO;
import Modelo.VO.TipoServicioVO;

public class CoordinadorServicio {
private LogicaServicio logicaServicio;

public LogicaServicio getLogica(){
	return logicaServicio;
}

public void setLogica(LogicaServicio logicaServicio) {
	this.logicaServicio = logicaServicio;
}
public boolean addServicio(ServicioVO servicio) throws ClassNotFoundException {
	return logicaServicio.validarAddServicio(servicio);
}
public ArrayList<ServicioVO> listaServicio() throws SQLException{
	return logicaServicio.validarListaServicio();
}
public ArrayList<TipoServicioVO> listaTipo() throws SQLException{
	return logicaServicio.validarListaTipo();
}
public boolean addTipo(TipoServicioVO tipo) throws SQLException{
	return logicaServicio.validarAddTipo(tipo);
}
}
