package Modelo.Coordinador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Logica.LogicaEmpleado;
import Modelo.VO.CargoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.SexoVO;

public class CoordinadorEmpleado {
private LogicaEmpleado logicaEmpleado;

public void setLogica(LogicaEmpleado logicaEmpleado) {
	this.logicaEmpleado = logicaEmpleado;
}

private LogicaEmpleado getLogica() {
	return logicaEmpleado;
}
public boolean addEmpleado(EmpleadoVO empleado) throws ClassNotFoundException {
return	logicaEmpleado.validarAddEmpleado(empleado);
}
public ArrayList<SexoVO> listaSexo() throws SQLException{
	return logicaEmpleado.validarListaSexo();
}
public ArrayList<CargoVO> listaCargo() throws SQLException{
	return logicaEmpleado.validarListaCargo();
}
public ArrayList<EmpleadoVO> listaEmpleado() throws SQLException{
	return logicaEmpleado.validarListaEmpleado();
}
public boolean addCargo(CargoVO cargo) {
	return logicaEmpleado.validarAddCargo(cargo);
	}
}
