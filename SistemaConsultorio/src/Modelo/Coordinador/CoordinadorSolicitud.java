package Modelo.Coordinador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.DAO.Detalle_ProtesisDAO;
import Modelo.Logica.LogicaSolicitud;
import Modelo.VO.Detalle_DiagnosticoVO;
import Modelo.VO.Detalle_ProtesisVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.SolicitudProtesisVO;

public class CoordinadorSolicitud {
private LogicaSolicitud logicaSolicitud;

private LogicaSolicitud getLogica() {
	return logicaSolicitud;
}

public void setLogica(LogicaSolicitud logicaSolicitud) {
	this.logicaSolicitud = logicaSolicitud;
}

public boolean addSolicitud(SolicitudProtesisVO solicitud,ArrayList<Detalle_ProtesisVO> listaDetalle) {
	return logicaSolicitud.validarAddSolicitu(solicitud, listaDetalle);
}

public ArrayList<ServicioVO> listaServicio() throws SQLException{
	return logicaSolicitud.validarListaServicio();
}
public ArrayList<SolicitudProtesisVO> listaSolicitud() throws SQLException{
	return logicaSolicitud.validarListaSolicitud();
}
public ArrayList<Detalle_ProtesisVO> listaDetalle(int id) throws SQLException{
	return logicaSolicitud.validarListaDetalle(id);
}
public PacienteVO buscarPaciente(int id) {
	return logicaSolicitud.validarBuscarPaciente(id);
}
public LaboratorioVO buscarLaboratorio(int id) throws SQLException {
	return logicaSolicitud.validarBuscarLaboratorio(id);
}
}
