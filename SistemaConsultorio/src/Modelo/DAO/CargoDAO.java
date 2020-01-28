package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Conexion.Conexion;
import Modelo.VO.CargoVO;
import Vista.ComponentCustomization;
import Modelo.VO.CargoVO;

public class CargoDAO {
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	public void registrarCargo(CargoVO cargo) throws ClassNotFoundException {
		try {
		// Preparar el procedimiento antes de ejecutar
		     cs = Conexion.getConnection().prepareCall("Call insertcargo(?,?)");
		    // Ingresar Parametros
		    cs.setString(1, cargo.getNombre());
		    cs.setString(2, cargo.getDescripcion());
		    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("Cargo registrado con exito", 4);  }
		catch (SQLException e) {
	        e.printStackTrace();
	    }}
	public ArrayList<CargoVO> listacargo() throws SQLException{
		ArrayList<CargoVO> listacargo = new ArrayList<CargoVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectcargo()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	CargoVO cargo = new CargoVO();
	    	cargo.setIdCargo(rs.getInt(1));
	    	cargo.setNombre(rs.getString(2));
	    	cargo.setDescripcion(rs.getString(3));
			listacargo.add(cargo);	
		}
	    rs.close();
		cs.close();
	    return listacargo;
	}
}
