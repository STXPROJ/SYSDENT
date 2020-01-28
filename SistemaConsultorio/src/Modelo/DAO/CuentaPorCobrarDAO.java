package Modelo.DAO;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.VO.CitasVO;
import Modelo.VO.CuentarPorCobrarVO;

import Modelo.VO.FacturaVO;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.PacienteVO;

import javax.swing.JOptionPane;

import Conexion.Conexion;

public class CuentaPorCobrarDAO {
	private FacturaVO factura = new FacturaVO();
	private CallableStatement cs;
	private CRUD_DAO consulta;
	private java.sql.PreparedStatement ps;
	public void RegistrarCXC(CuentarPorCobrarVO cxc) {
	try {
		// Preparar el procedimiento antes de ejecutar
		    cs = Conexion.getConnection().prepareCall("Call insertcxc(?,?,?,?)");
		    // Ingresar Parametros
		    cs.setInt(1, 1);
		    cs.setDouble(2, cxc.getMonto());
		    cs.setString(3, cxc.getEstado());
		    cs.setDouble(4, cxc.getPagado());
	    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    JOptionPane.showMessageDialog(null, "se Agrego cxc ");   }
		catch (SQLException e) {
	        e.printStackTrace();
	    }}
	public ArrayList<CuentarPorCobrarVO> listacxc() throws SQLException{
		ArrayList<CuentarPorCobrarVO> listacxc = new ArrayList<CuentarPorCobrarVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectAllCXC()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	// Datos Paciente
	    	PacienteVO paciente = new PacienteVO();
	    	paciente.setIdPaciente(rs.getInt(3));
	    	paciente.setNombre(rs.getString(4));
	    	// Datos Factura
	    	FacturaVO factura = new FacturaVO();
	    	factura.setIdFactura(rs.getInt(2));
	    	factura.setPaciente(paciente);
	    	// Datos CXC
	    	CuentarPorCobrarVO cxc = new CuentarPorCobrarVO();
	    	cxc.setIdCuenta(rs.getInt(1));
	    	cxc.setFactura(factura);
	    	cxc.setMonto(rs.getDouble(5));
	    	cxc.setEstado(rs.getString(6));
	    	cxc.setPagado(rs.getDouble(7));
	    	listacxc.add(cxc);	
		}
	    rs.close();
		cs.close();
	    return listacxc;
	
}
	public ArrayList<CuentarPorCobrarVO> filtrocxc(String table,String field,String value) throws SQLException{
		ArrayList<CuentarPorCobrarVO> listacxc = new ArrayList<CuentarPorCobrarVO>();
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
	    	paciente.setIdPaciente(rs.getInt(3));
	    	paciente.setNombre(rs.getString(4));
	    	// Datos Factura
	    	FacturaVO factura = new FacturaVO();
	    	factura.setIdFactura(rs.getInt(2));
	    	factura.setPaciente(paciente);
	    	// Datos CXC
	    	CuentarPorCobrarVO cxc = new CuentarPorCobrarVO();
	    	cxc.setIdCuenta(rs.getInt(1));
	    	cxc.setFactura(factura);
	    	cxc.setMonto(rs.getDouble(5));
	    	cxc.setEstado(rs.getString(6));
	    	cxc.setPagado(rs.getDouble(7));
	    	listacxc.add(cxc);	
		}
	    rs.close();
		ps.close();
	    return listacxc;
}}
