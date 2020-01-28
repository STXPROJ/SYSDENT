package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.VO.CitasVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCivilVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.RolVO;
import Modelo.VO.SexoVO;
import Modelo.VO.UsuarioVO;
import Modelo.VO.RolVO;
import Vista.ComponentCustomization;

public class UsuarioDAO {
	private UsuarioVO usuario;
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	private CRUD_DAO consulta;
	private java.sql.PreparedStatement ps;
	public UsuarioVO getusuario() {
		return usuario;
	}
	
	public void Registrarusuario(UsuarioVO usuario) throws ClassNotFoundException {
		try {
		// Preparar el procedimiento antes de ejecutar
		     cs = Conexion.getConnection().prepareCall("Call insertusuario(?,?,?,?)");
		    // Ingresar Parametros
		    cs.setString(1, usuario.getNombre());
		    cs.setInt(2, usuario.getEmpleado().getIdEmpleado());
		    cs.setInt(3, usuario.getRol().getIdRol());
		    cs.setString(4, usuario.getPassword());
	
		    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("Usuario registrado con exito", 4);     }
		catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public ArrayList<UsuarioVO> listausuario() throws SQLException{
		ArrayList<UsuarioVO> listausuario = new ArrayList<UsuarioVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectUsuario()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	/* Datos Rol */
	    	RolVO rol = new RolVO();
	    	rol.setIdRol(rs.getInt(5));
	    	rol.setNombre(rs.getString(6));
	    	/* Datos Empleado */
	    	EmpleadoVO empleado = new EmpleadoVO();
	    	empleado.setIdEmpleado(rs.getInt(3));
	    	empleado.setNombre(rs.getString(4));
	    	/* Datos Usuario */
	    	UsuarioVO usuario = new UsuarioVO();
	    	usuario.setIdUsuario(rs.getInt(1));
	    	usuario.setNombre(rs.getString(2));
	    	usuario.setPassword(rs.getString(7));
	    	usuario.setEmpleado(empleado);
	    	usuario.setRol(rol);
			listausuario.add(usuario);	
		}
	    rs.close();
		cs.close();
	    return listausuario;
	}
	public UsuarioVO buscarUsuario(String user) {
	    UsuarioVO usuario = new UsuarioVO();
	    try {
			// Preparar el procedimiento antes de ejecutar
			   cs = Conexion.getConnection().prepareCall("Call selectidUsuario(?)");
			    // Ingresar Parametros
			    cs.setString(1, user);
			    // Ejecutar Procedimiento
			    ResultSet rs = 	 cs.executeQuery();
			    while(rs.next()) {
			    	/* Datos Rol */
			    	RolVO rol = new RolVO();
			    	rol.setIdRol(rs.getInt(5));
			    	rol.setNombre(rs.getString(6));
			    	/* Datos Empleado */
			    	EmpleadoVO empleado = new EmpleadoVO();
			    	empleado.setIdEmpleado(rs.getInt(3));
			    	empleado.setNombre(rs.getString(4));
			    	/* Datos Usuario */
			    	usuario.setIdUsuario(rs.getInt(1));
			    	usuario.setNombre(rs.getString(2));
			    	usuario.setPassword(rs.getString(7));
			    	usuario.setEmpleado(empleado);
			    	usuario.setRol(rol);
	
			    }
			    rs.close();
			    cs.close();
		} 
			catch (SQLException e) {
		        custom.msg("Usuario", 2);}
		return usuario;
	}
	
	public ArrayList<UsuarioVO> filtroUsuario(String table,String field,String value) throws SQLException{
		ArrayList<UsuarioVO> listausuario = new ArrayList<UsuarioVO>();
		consulta = new CRUD_DAO();
		String query = consulta.filterTable(table);
		query = query + " " + field + " = "+ value;
		// Preparar el procedimiento antes de ejecutar
	    ps = Conexion.getConnection().prepareStatement(query);
    // Ejecutar Procedimiento
	    ResultSet rs = 	 ps.executeQuery();
	    while(rs.next()) {
	    	/* Datos Rol */
	    	RolVO rol = new RolVO();
	    	rol.setIdRol(rs.getInt(5));
	    	rol.setNombre(rs.getString(6));
	    	/* Datos Empleado */
	    	EmpleadoVO empleado = new EmpleadoVO();
	    	empleado.setIdEmpleado(rs.getInt(3));
	    	empleado.setNombre(rs.getString(4));
	    	/* Datos Usuario */
	    	UsuarioVO usuario = new UsuarioVO();
	    	usuario.setIdUsuario(rs.getInt(1));
	    	usuario.setNombre(rs.getString(2));
	    	usuario.setPassword(rs.getString(7));
	    	usuario.setEmpleado(empleado);
	    	usuario.setRol(rol);
			listausuario.add(usuario);	
		}
	    rs.close();
		ps.close();
	    return listausuario;
	}
}

