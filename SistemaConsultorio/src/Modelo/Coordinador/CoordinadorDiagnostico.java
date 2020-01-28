package Modelo.Coordinador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Logica.LogicaDiagnostico;
import Modelo.VO.DentagramaVO;
import Modelo.VO.Detalle_DiagnosticoVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.ServicioVO;

public class CoordinadorDiagnostico {
private LogicaDiagnostico logicaDiagnostico;
public LogicaDiagnostico getLogica() {
	return logicaDiagnostico;
}
public void setLogica(LogicaDiagnostico logicaDiagnostico) {
	this.logicaDiagnostico = logicaDiagnostico;
}
public boolean addDiagnostico(DiagnosticoVO diagnostico,ArrayList<Detalle_DiagnosticoVO> listaDetalle) {
	return logicaDiagnostico.validarAddDiagnostico(diagnostico,listaDetalle);
	
}

public PacienteVO buscarPaciente(int id) {
	return logicaDiagnostico.validarBuscarPaciente(id);
}
public DentagramaVO buscarDentagrama(int idPaciente,int idDiagnostico) {
	return logicaDiagnostico.validarBuscarDentagrama(idPaciente, idDiagnostico);
}
public ArrayList<ServicioVO> listaServicio() throws SQLException{
	return logicaDiagnostico.validarListaServicios();
}
public ArrayList<DiagnosticoVO> listaDiagnostico() throws SQLException{
	return logicaDiagnostico.validarListaDiagnostico();
}
public ArrayList<Detalle_DiagnosticoVO> listaDetalle(int id) throws SQLException{
	return logicaDiagnostico.validarListaDetalle(id);
}
}
