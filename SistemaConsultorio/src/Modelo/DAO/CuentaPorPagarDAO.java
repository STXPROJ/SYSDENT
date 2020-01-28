package Modelo.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.VO.CuentaPorPagarVO;
import Modelo.VO.CuentarPorCobrarVO;
import Modelo.VO.FacturaVO;
import Modelo.VO.LaboratorioVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.SolicitudProtesisVO;
import javax.swing.JOptionPane;

import Conexion.Conexion;

public class CuentaPorPagarDAO {
	private CallableStatement cs;
	private SolicitudProtesisVO solicitud = new SolicitudProtesisVO();
	private CRUD_DAO consulta;
	private java.sql.PreparedStatement ps;
	public void RegistrarCXP(CuentaPorPagarVO cxp) {
	try {
		// Preparar el procedimiento antes de ejecutar
		    CallableStatement cs = Conexion.getConnection().prepareCall("Call insertCXP(?,?,?,?)");
		    // Ingresar Parametros
		    cs.setInt(1, 1);
		    cs.setDouble(2, cxp.getMonto());
		    cs.setString(3, cxp.getEstado());
		    cs.setDouble(4, cxp.getPagado());
	    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    JOptionPane.showMessageDialog(null, "se Agrego CXP ");   }
		catch (SQLException e) {
	        e.printStackTrace();
	    }
}
	public ArrayList<CuentaPorPagarVO> listacxp() throws SQLException{
		ArrayList<CuentaPorPagarVO> listacxp = new ArrayList<CuentaPorPagarVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call selectAllcxp()");
    // Ejecutar Procedimiento
	    ResultSet rs = 	 cs.executeQuery();
	    while(rs.next()) {
	    	// Datos Paciente
	    	PacienteVO paciente = new PacienteVO();
	    	paciente.setIdPaciente(rs.getInt(5));
	    	paciente.setNombre(rs.getString(6));
	    	// Datos Laboratorio
	    	LaboratorioVO laboratorio = new LaboratorioVO();
	    	laboratorio.setIdLaboratorio(rs.getInt(3));
	    	laboratorio.setNombre(rs.getString(4));
	    	// Datos Solicitud
	    	SolicitudProtesisVO solicitud = new SolicitudProtesisVO();
	    	solicitud.setIdSolicitud(rs.getInt(2));
	    	solicitud.setPaciente(paciente);
	    	solicitud.setLaboratorio(laboratorio);
	    	
	    	// Datos cxp
	    	CuentaPorPagarVO cxp = new CuentaPorPagarVO();
	    	cxp.setIdCuenta(rs.getInt(1));
	    	cxp.setSolicitud(solicitud);
	    	cxp.setMonto(rs.getDouble(7));
	    	cxp.setEstado(rs.getString(8));
	    	cxp.setPagado(rs.getDouble(9));
	    	listacxp.add(cxp);	
		}
	    rs.close();
		cs.close();
	    return listacxp;	

}
	public ArrayList<CuentaPorPagarVO> filtroCXP(String table,String field,String value) throws SQLException{
		ArrayList<CuentaPorPagarVO> listacxp = new ArrayList<CuentaPorPagarVO>();
		consulta = new CRUD_DAO();
		String query = consulta.filterTable(table);
		query = query + " " + field + " = "+ value;
		// Preparar el procedimiento antes de ejecutar
	    ps = Conexion.getConnection().prepareStatement(query);
	    ResultSet rs = 	 ps.executeQuery();
	    while(rs.next()) {
	    	// Datos Paciente
	    	PacienteVO paciente = new PacienteVO();
	    	paciente.setIdPaciente(rs.getInt(5));
	    	paciente.setNombre(rs.getString(6));
	    	// Datos Laboratorio
	    	LaboratorioVO laboratorio = new LaboratorioVO();
	    	laboratorio.setIdLaboratorio(rs.getInt(3));
	    	laboratorio.setNombre(rs.getString(4));
	    	// Datos Solicitud
	    	SolicitudProtesisVO solicitud = new SolicitudProtesisVO();
	    	solicitud.setIdSolicitud(rs.getInt(2));
	    	solicitud.setPaciente(paciente);
	    	solicitud.setLaboratorio(laboratorio);
	    	
	    	// Datos cxp
	    	CuentaPorPagarVO cxp = new CuentaPorPagarVO();
	    	cxp.setIdCuenta(rs.getInt(1));
	    	cxp.setSolicitud(solicitud);
	    	cxp.setMonto(rs.getDouble(7));
	    	cxp.setEstado(rs.getString(8));
	    	cxp.setPagado(rs.getDouble(9));
	    	listacxp.add(cxp);	
		}
	    rs.close();
		ps.close();
	    return listacxp;	

}
	
}

