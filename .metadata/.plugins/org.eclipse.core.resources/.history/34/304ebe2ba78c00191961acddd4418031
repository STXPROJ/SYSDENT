
	package Modelo.DAO;

	import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

	import Conexion.Conexion;
	import Modelo.VO.FacturaVO;
	import Modelo.VO.PacienteVO;
import Modelo.VO.SexoVO;
import Modelo.VO.UsuarioVO;
import Vista.ComponentCustomization;
import Modelo.VO.FacturaVO;
import Modelo.VO.DiagnosticoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.EstadoCivilVO;
	public class FacturaDAO {
		private CallableStatement cs;
		private ComponentCustomization custom = new ComponentCustomization();
		private CRUD_DAO consulta;
		private java.sql.PreparedStatement ps;
		public void RealizarFactura(FacturaVO Factura) {
		try {
			// Preparar el procedimiento antes de ejecutar
			     cs = Conexion.getConnection().prepareCall("Call RealizarFactura(?,?,?)");
			    // Ingresar Parametros
			    cs.setInt(1, Factura.getPaciente().getIdPaciente());
			    cs.setInt(2, Factura.getUsuario().getIdUsuario());
			    cs.setInt(3, Factura.getDescuento());
		    // Ejecutar Procedimiento
			    cs.execute();
			    cs.close();
			    custom.msg("Factura Registrada con exito", 4);    }
			catch (SQLException e) {
		        e.printStackTrace();
		    }}
		public ArrayList<FacturaVO> listafactura() throws SQLException{
			ArrayList<FacturaVO> listafactura = new ArrayList<FacturaVO>();
			// Preparar el procedimiento antes de ejecutar
		    cs = Conexion.getConnection().prepareCall("call selectAllFactura()");
	    // Ejecutar Procedimiento
		    ResultSet rs = 	 cs.executeQuery();
		    while(rs.next()) {
		    	// Datos Paciente
		    	PacienteVO paciente = new PacienteVO();
		    	paciente.setIdPaciente(rs.getInt(2));
		    	paciente.setNombre(rs.getString(3));
		    	// Datos Empleado
		    	UsuarioVO usuario = new UsuarioVO();
		    	usuario.setIdUsuario(rs.getInt(5));
		    	usuario.setNombre(rs.getString(6));
		    	
		    	FacturaVO factura = new FacturaVO();
		    	factura.setIdFactura(rs.getInt(1));
		    	factura.setFecha(rs.getString(4));
		    	factura.setEstado(rs.getBoolean(7));	
		    	factura.setDescuento(rs.getInt(8));
		    	factura.setTotal(rs.getDouble(9));
		    	factura.setPaciente(paciente);
		    	factura.setUsuario(usuario);
				listafactura.add(factura);		
			}
		    rs.close();
			cs.close();
		    return listafactura;
		}
		public ArrayList<FacturaVO> filtroFactura(String table,String field,String value) throws SQLException{
			ArrayList<FacturaVO> listafactura = new ArrayList<FacturaVO>();
			consulta = new CRUD_DAO();
			String query = consulta.filterTable(table);
			query = query + " " + field + " = "+ value;
			// Preparar el procedimiento antes de ejecutar
		    ps = Conexion.getConnection().prepareStatement(query);
	    // Ejecutar Procedimiento
		    ResultSet rs = 	 ps.executeQuery();
		    while(rs.next()) {
		    	// Datos Paciente
		    	PacienteVO paciente = new PacienteVO();
		    	paciente.setIdPaciente(rs.getInt(2));
		    	paciente.setNombre(rs.getString(3));
		    	// Datos Empleado
		    	UsuarioVO usuario = new UsuarioVO();
		    	usuario.setIdUsuario(rs.getInt(5));
		    	usuario.setNombre(rs.getString(6));
		    	
		    	FacturaVO factura = new FacturaVO();
		    	factura.setIdFactura(rs.getInt(1));
		    	factura.setFecha(rs.getString(4));
		    	factura.setEstado(rs.getBoolean(7));
		    	factura.setPaciente(paciente);
		    	factura.setUsuario(usuario);
				listafactura.add(factura);		
			}
		    rs.close();
			ps.close();
		    return listafactura;
		}
		public void actualizarFactura(FacturaVO factura) throws SQLException {
			// Preparar el procedimiento antes de ejecutar
		     cs = Conexion.getConnection().prepareCall("Call updateEstadoFactura(?)");
		    // Ingresar Parametros
		    cs.setInt(1, factura.getIdFactura());
	    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    }
		
		public FacturaVO buscarFactura(int id) {
		    FacturaVO factura = new FacturaVO();
		    try {
				// Preparar el procedimiento antes de ejecutar
				   cs = Conexion.getConnection().prepareCall("Call selectidFactura(?)");
				    // Ingresar Parametros
				    cs.setInt(1, id);
				    // Ejecutar Procedimiento
				    ResultSet rs = 	 cs.executeQuery();
				    while(rs.next()) {
				      	// Datos Paciente
				    	PacienteVO paciente = new PacienteVO();
				    	paciente.setIdPaciente(rs.getInt(2));
				    	paciente.setNombre(rs.getString(3));
				    	// Datos Empelado
				    	UsuarioVO usuario = new UsuarioVO();
				    	usuario.setIdUsuario(rs.getInt(5));
				    	usuario.setNombre(rs.getString(6));
				  
				    	// Datos Factura
				    	factura.setIdFactura(rs.getInt(1));
				    	factura.setFecha(rs.getString(4));
				    	factura.setEstado(rs.getBoolean(7));	
				    	factura.setDescuento(rs.getInt(8));
				    	factura.setTotal(rs.getDouble(9));
				    	factura.setPagado(rs.getDouble(10));
				    	factura.setPaciente(paciente);
				    	factura.setUsuario(usuario);
				    	if (rs.getDouble(11) <= 0){
				    		factura.setPendiente(0);
				    	}else {
				    		factura.setPendiente(rs.getDouble(11));
				    	}
				    }
				    rs.close();
				    cs.close();
			} 
				catch (SQLException e) {
			        custom.msg("Factura", 2);}
			return factura;
			}
		public ArrayList<FacturaVO> facturasPendientes() throws SQLException{
			ArrayList<FacturaVO> listafactura = new ArrayList<FacturaVO>();
			// Preparar el procedimiento antes de ejecutar
		    cs = Conexion.getConnection().prepareCall("call selectFacturaPendiente()");
	    // Ejecutar Procedimiento
		    ResultSet rs = 	 cs.executeQuery();
		    while(rs.next()) {
		    	// Datos Paciente
		    	PacienteVO paciente = new PacienteVO();
		    	paciente.setIdPaciente(rs.getInt(2));
		    	paciente.setNombre(rs.getString(3));
		    	// Datos Empleado
		    	UsuarioVO usuario = new UsuarioVO();
		    	usuario.setIdUsuario(rs.getInt(5));
		    	usuario.setNombre(rs.getString(6));
		    	
		    	FacturaVO factura = new FacturaVO();
		    	factura.setIdFactura(rs.getInt(1));
		    	factura.setFecha(rs.getString(4));
		    	factura.setEstado(rs.getBoolean(7));	
		    	factura.setDescuento(rs.getInt(8));
		    	factura.setTotal(rs.getDouble(9));
		    	factura.setPaciente(paciente);
		    	factura.setUsuario(usuario);
				listafactura.add(factura);		
			}
		    rs.close();
			cs.close();
		    return listafactura;
		}
	}
