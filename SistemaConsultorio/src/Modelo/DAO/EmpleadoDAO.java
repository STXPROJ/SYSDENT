package Modelo.DAO;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.VO.CargoVO;
import Modelo.VO.CitasVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.SexoVO;
import Vista.ComponentCustomization;
import Conexion.Conexion;
public class EmpleadoDAO {
	private EmpleadoVO Empleado;
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	private CRUD_DAO consulta;
	private java.sql.PreparedStatement ps;
	public EmpleadoVO getEmpleado() {
		return Empleado;
	}
	
	public void RegistrarEmpleado(EmpleadoVO Empleado) throws ClassNotFoundException {
		try {
		// Preparar el procedimiento antes de ejecutar
		     cs = Conexion.getConnection().prepareCall("Call insertEmpleado(?,?,?,?,?,?,?,?,?,?)");
		    // Ingresar Parametros
		    cs.setString(1, Empleado.getNombre());
		    cs.setString(2, Empleado.getApellido());
		    cs.setInt(3, Empleado.getSexo().getIdSexo());
		    cs.setString(4, Empleado.getFechaIngreso());
		    cs.setString(5, Empleado.getCedula());
		    cs.setInt(6, Empleado.getCargo().getIdCargo());
		    cs.setString(7, Empleado.getDireccion());
		    cs.setString(8, Empleado.getTelefono());
		    cs.setString(9, Empleado.getCorreo());
		    cs.setDouble(10, Empleado.getSueldo());
		    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("Empleado registrado con exito", 4);     }
		catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public ArrayList<EmpleadoVO> listaEmpleado() throws SQLException{
		ArrayList<EmpleadoVO> listaEmpleado = new ArrayList<EmpleadoVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectAllEmpleado()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	/* Datos Sexo */
	    	SexoVO sexo = new SexoVO();
	    	sexo.setIdSexo(rs.getInt(4));
	    	sexo.setGenero(rs.getString(5));
	    	/* Datos Cargo */
	    	CargoVO cargo = new CargoVO();
	    	cargo.setIdCargo(rs.getInt(8));
	    	cargo.setNombre(rs.getString(9));
	    	/* Datos empleado */
	    	EmpleadoVO empleado = new EmpleadoVO();
	    	empleado.setSexo(sexo);
	    	empleado.setCargo(cargo);
	    	empleado.setIdEmpleado(rs.getInt(1));
	    	empleado.setNombre(rs.getString(2));
	    	empleado.setApellido(rs.getString(3));
	    	empleado.setFechaIngreso(rs.getString(6));
	    	empleado.setCedula(rs.getString(7));
	    	empleado.setDireccion(rs.getString(10));
	    	empleado.setTelefono(rs.getString(11));
	    	empleado.setCorreo(rs.getString(12));
	    	empleado.setSueldo(rs.getDouble(13));
			listaEmpleado.add(empleado);	
		}
	    rs.close();
		cs.close();
	    return listaEmpleado;
	}
	public ArrayList<EmpleadoVO> filtroEmpleado(String table,String field,String value) throws SQLException{
		ArrayList<EmpleadoVO> listaEmpleado = new ArrayList<EmpleadoVO>();
		consulta = new CRUD_DAO();
		String query = consulta.filterTable(table);
		query = query + " " + field + " = "+ value;
		// Preparar el procedimiento antes de ejecutar
	    ps = Conexion.getConnection().prepareStatement(query);	
	    ResultSet rs = 	 ps.executeQuery();
	    while(rs.next()) {
	    	/* Datos Sexo */
	    	SexoVO sexo = new SexoVO();
	    	sexo.setIdSexo(rs.getInt(4));
	    	sexo.setGenero(rs.getString(5));
	    	/* Datos Cargo */
	    	CargoVO cargo = new CargoVO();
	    	cargo.setIdCargo(rs.getInt(8));
	    	cargo.setNombre(rs.getString(9));
	    	/* Datos empleado */
	    	EmpleadoVO empleado = new EmpleadoVO();
	    	empleado.setSexo(sexo);
	    	empleado.setCargo(cargo);
	    	empleado.setIdEmpleado(rs.getInt(1));
	    	empleado.setNombre(rs.getString(2));
	    	empleado.setApellido(rs.getString(3));
	    	empleado.setFechaIngreso(rs.getString(6));
	    	empleado.setCedula(rs.getString(7));
	    	empleado.setDireccion(rs.getString(10));
	    	empleado.setTelefono(rs.getString(11));
	    	empleado.setCorreo(rs.getString(12));
	    	empleado.setSueldo(rs.getDouble(13));
			listaEmpleado.add(empleado);	
		}
	    rs.close();
		ps.close();
	    return listaEmpleado;
	}
public ArrayList<EmpleadoVO> empleadoComboBox() throws SQLException{
		ArrayList<EmpleadoVO> listaEmpleado = new ArrayList<EmpleadoVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call cbbEmpleado()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	EmpleadoVO empleado = new EmpleadoVO();
	    	empleado.setIdEmpleado(rs.getInt(1));
	    	empleado.setNombre(rs.getString(2));
			listaEmpleado.add(empleado);	
		}
	    rs.close();
		cs.close();
	    return listaEmpleado;
	}
	public EmpleadoVO buscarempleado(int id) throws SQLException {
	    EmpleadoVO empleado = new EmpleadoVO();
			// Preparar el procedimiento antes de ejecutar
			   cs = Conexion.getConnection().prepareCall("Call selectidempleado(?)");
			    // Ingresar Parametros
			    cs.setInt(1, id);
			    // Ejecutar Procedimiento
			    ResultSet rs = 	 cs.executeQuery();

			    while(rs.next()) {
			    	// Datos Sexo
			    	SexoVO sexo = new SexoVO();
			    	sexo.setIdSexo(rs.getInt(4));
			    	sexo.setGenero(rs.getString(5));
			    	// Datos Cargo
			    	CargoVO cargo = new CargoVO();
			    	cargo.setIdCargo(rs.getInt(7));
			    	cargo.setNombre(rs.getString(8));
			    	
			    	empleado.setIdEmpleado(rs.getInt(1));
			    	empleado.setNombre(rs.getString(2));
			    	empleado.setApellido(rs.getString(3));
			    	empleado.setSexo(sexo);
			    	empleado.setFechaIngreso(rs.getString(6));
			    	empleado.setDireccion(rs.getString(9));
			    	empleado.setTelefono(rs.getString(10));
			    	empleado.setCorreo(rs.getString(11));
			    	empleado.setSueldo(rs.getDouble(12));
			    	}
			    rs.close();
			    cs.close();
			    return empleado;
	}	
	public ArrayList<EmpleadoVO> filtroEmpleado() throws SQLException{
		ArrayList<EmpleadoVO> listaEmpleado = new ArrayList<EmpleadoVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call filterTable(?,?,?)");
    // Ejecutar Procedimiento
	    cs.setString(1, "Empleado");
	    cs.setString(2, "idEmpleado");
	    cs.setString(3, "1");
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	/* Datos Sexo */
	    	SexoVO sexo = new SexoVO();
	    	sexo.setIdSexo(rs.getInt(4));
	    	sexo.setGenero(rs.getString(5));
	    	/* Datos Cargo */
	    	CargoVO cargo = new CargoVO();
	    	cargo.setIdCargo(rs.getInt(8));
	    	cargo.setNombre(rs.getString(9));
	    	/* Datos empleado */
	    	EmpleadoVO empleado = new EmpleadoVO();
	    	empleado.setSexo(sexo);
	    	empleado.setCargo(cargo);
	    	empleado.setIdEmpleado(rs.getInt(1));
	    	empleado.setNombre(rs.getString(2));
	    	empleado.setApellido(rs.getString(3));
	    	empleado.setFechaIngreso(rs.getString(6));
	    	empleado.setCedula(rs.getString(7));
	    	empleado.setDireccion(rs.getString(10));
	    	empleado.setTelefono(rs.getString(11));
	    	empleado.setCorreo(rs.getString(12));
	    	empleado.setSueldo(rs.getDouble(13));
			listaEmpleado.add(empleado);	
		}
	    rs.close();
		cs.close();
		 for (int i=0;i<listaEmpleado.size();i++) {
		    	System.out.println(listaEmpleado.get(i).getNombre());
		    }
		 System.out.println("nada");
	    return listaEmpleado;
	   
	}
}
