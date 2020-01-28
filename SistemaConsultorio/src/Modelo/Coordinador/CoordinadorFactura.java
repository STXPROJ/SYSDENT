package Modelo.Coordinador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Logica.LogicaFactura;
import Modelo.VO.Detalle_DiagnosticoVO;
import Modelo.VO.Detalle_FacturaVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.FacturaVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;

public class CoordinadorFactura {
private LogicaFactura logicaFactura;

private LogicaFactura getLogica()
{return logicaFactura;
	}

public void setLogica(LogicaFactura logicaFactura) {
	this.logicaFactura = logicaFactura;
}
public boolean addFactura(FacturaVO factura,ArrayList<Detalle_FacturaVO> listaDetalle) {
	return logicaFactura.validarAddFactura(factura, listaDetalle);
}
public ArrayList<FacturaVO> listaFactura() throws SQLException{
	return logicaFactura.validarListaFactura();
}
public ArrayList<FacturaVO> facturasPendientes() throws SQLException{
	return logicaFactura.validarFacturasPendientes();
}
public ArrayList<Detalle_FacturaVO> listaDetalle(int id) throws SQLException{
	return logicaFactura.validarListaDetalle(id);
}
public ArrayList<ServicioVO> listaServicio() throws SQLException{
	return logicaFactura.validarListaServicio();
}

public PacienteVO buscarPaciente(int id) {
	return logicaFactura.validarBuscarPaciente(id);
}
public DiagnosticoVO buscarDiagnostico(int id) {
	return logicaFactura.validarBuscarDiagnostico(id);
}

public ArrayList<Detalle_DiagnosticoVO> detalleDiagnostico(int id) throws SQLException{
	return logicaFactura.validarDetalleDiagnostico(id);
}
}
