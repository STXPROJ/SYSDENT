package SistemaConsultorio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Modelo.Coordinador.CoordinadorLogin;
import Modelo.DAO.*;
import Modelo.Logica.LogicaLogin;
import Modelo.VO.*;
public class Test {
	private static CoordinadorLogin coordLogin = new CoordinadorLogin();
	private static LogicaLogin logicaLogin = new LogicaLogin();
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		HistorialClinicoDAO historial = new  HistorialClinicoDAO();
		HistorialClinicoVO hist = new HistorialClinicoVO();
		hist = historial.buscarHistorial(1);
		if(hist.isAlergaAnestesia() == true) {
			System.out.println(hist.getPaciente().getNombre());
		}
	}
	}
	