package Modelo.Logica;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Coordinador.CoordinadorLaboratorio;
import Modelo.DAO.LaboratorioDAO;
import Modelo.VO.LaboratorioVO;
import Vista.ComponentCustomization;

public class LogicaLaboratorio {
private CoordinadorLaboratorio coordLaboratorio;
private ValidationClass validation = new ValidationClass();
private ComponentCustomization custom = new ComponentCustomization();
private LaboratorioDAO consultaLaboratorio;
public void setCoordinador(CoordinadorLaboratorio coordLaboratorio) {
	this.coordLaboratorio = coordLaboratorio;
}

public boolean validarAddLaboratorio(LaboratorioVO laboratorio) {
	boolean bool = false;
	try {

	if(laboratorio.getNombre().isEmpty()) {
		custom.msg("", 1);}
	else {
		if (custom.msgConfirm("Desea Registrar este laboratorio?", 1) == JOptionPane.YES_OPTION) {
			consultaLaboratorio = new LaboratorioDAO();
			consultaLaboratorio.RegistrarLaboratorio(laboratorio);
			bool = true;
		}}}catch (Exception e) {
	custom.msg("", 1);
}return bool;
	}

public ArrayList<LaboratorioVO> validarListaLaboratorio() throws SQLException{
	consultaLaboratorio = new LaboratorioDAO();
	if(!consultaLaboratorio.listalaboratorio().isEmpty()) {
	return consultaLaboratorio.listalaboratorio();
	}
	else {
			custom.msg("No hay laboratorios disponibles", 7);
			return null;
	}
}
}