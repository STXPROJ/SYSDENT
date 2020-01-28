package Modelo.DAO;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.VO.DiagnosticoVO;
import Modelo.VO.EmpleadoVO;
import Modelo.VO.FacturaVO;
import Modelo.VO.PacienteVO;
import Modelo.VO.PagoFacturaVO;
import Vista.ComponentCustomization;
import Conexion.Conexion;
public class PagoFacturaDAO {
	private CallableStatement cs;
	private ComponentCustomization custom = new ComponentCustomization();
	public void RealizarPago(PagoFacturaVO pago) throws ClassNotFoundException {
		try {
			
		// Preparar el procedimiento antes de ejecutar
		     cs = Conexion.getConnection().prepareCall("Call RealizarPago(?)");
		    // Ingresar Parametros
		    cs.setInt(1, pago.getFactura().getIdFactura());
		    // Ejecutar Procedimiento
		    cs.execute();
		    cs.close();
		    custom.msg("El pago se ha realizado de manera exitosa ", 4);    }
		catch (SQLException e) {
	        e.printStackTrace();
	    }}
	public ArrayList<PagoFacturaVO> listapagoFactura() throws SQLException{
		ArrayList<PagoFacturaVO> listapagoFactura = new ArrayList<PagoFacturaVO>();
		// Preparar el procedimiento antes de ejecutar
	    cs = Conexion.getConnection().prepareCall("call SelectAllPagos()");
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
	    	factura.setTotal(rs.getDouble(7));
	    	if (rs.getDouble(8) <= 0){
	    		factura.setPendiente(0);
	    	}else {
	    		factura.setPendiente(rs.getDouble(8));
	    	}
	    	// Datos Pago
	    	PagoFacturaVO pagoFactura = new PagoFacturaVO();
	    	pagoFactura.setIdPago(rs.getInt(1));
	    	pagoFactura.setFecha(rs.getString(5));
	    	pagoFactura.setFactura(factura);
	    	pagoFactura.setTotal(rs.getDouble(6));
			listapagoFactura.add(pagoFactura);		
		}
	    rs.close();
		cs.close();
	   return listapagoFactura;
	}

}