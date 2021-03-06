package Modelo.Logica;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Modelo.Coordinador.CoordinadorFactura;
import Modelo.Coordinador.CoordinadorPagos;
import Modelo.DAO.CRUD_DAO;
import Modelo.DAO.CargoDAO;
import Modelo.DAO.Detalle_FacturaDAO;
import Modelo.DAO.Detalle_PagoDAO;
import Modelo.DAO.FacturaDAO;
import Modelo.DAO.PacienteDAO;
import Modelo.DAO.PagoFacturaDAO;
import Modelo.DAO.ServicioDAO;
import Modelo.DAO.TipoPagoDAO;
import Modelo.VO.CargoVO;
import Modelo.VO.Detalle_FacturaVO;
import Modelo.VO.Detalle_PagoVO;
import Modelo.VO.FacturaVO;
import Modelo.VO.PagoFacturaVO;
import Modelo.VO.ServicioVO;
import Modelo.VO.TipoPagoVO;
import Vista.ComponentCustomization;

public class LogicaPago {
	private FacturaDAO consultaFactura;
	private PagoFacturaDAO consultaPago;
	private CoordinadorPagos coordPago;
	private Detalle_PagoDAO consultaDetalle;
	private CRUD_DAO selectPago;
	private ComponentCustomization custom = new ComponentCustomization();
	private ValidationClass validation = new ValidationClass();
	private TipoPagoDAO consultaTipoPago;
	public void setCoordi(CoordinadorPagos coordPago) {
		this.coordPago = coordPago;
	}
	public boolean validarRegistrarPago(PagoFacturaVO pago,ArrayList<Detalle_PagoVO> listaDetalle) {
		boolean bool = false;
		consultaPago = new PagoFacturaDAO();
		consultaDetalle = new Detalle_PagoDAO(); 
		consultaFactura = new FacturaDAO();
		selectPago = new CRUD_DAO();
		
		try {
			if (listaDetalle.isEmpty()){
				custom.msg("El pago debe de tener al menos 1 forma de pago", 7);
			}else if (!(pago.getFactura().getIdFactura() < 0)) {
			
			if (validarDetalle(listaDetalle) == true) {	
				if(custom.msgConfirm("Guardar Pago?", 1) == JOptionPane.YES_OPTION){
				bool = true;
				consultaPago.RealizarPago(pago);
				consultaFactura.actualizarFactura(pago.getFactura());
				for (int i=0;i<listaDetalle.size();i++) {
					consultaDetalle.RegistrarDetalleFactura(listaDetalle.get(i));}
				}
				consultaFactura.actualizarFactura(pago.getFactura());
			}}else {
				custom.msg("", 1);
				bool = false;
			}
			}catch (Exception e) {
				custom.msg("", 1);
				e.printStackTrace();
			}
		return bool;}
	public boolean validarDetalle(ArrayList<Detalle_PagoVO> listaDetalle) {
		boolean bool = false;

		try {
		for (int i = 0;i<listaDetalle.size();i++) {
		if (!(listaDetalle.get(i).getMonto() >= 0) ||  
				(!(listaDetalle.get(i).getTipoPago().getIdTipo() >= 0))) {
			custom.msg("", 1);
			bool = false;;}
		else {
		bool = true;;
				}}}
		catch (Exception e) {
			e.printStackTrace();
		}
		return bool;}
	public ArrayList<PagoFacturaVO> validarListaPagos() throws SQLException{
		consultaPago = new PagoFacturaDAO();
		if(!consultaPago.listapagoFactura().isEmpty()) {
			return consultaPago.listapagoFactura();}
			else {
				custom.msg("No existen Pagos", 7);
				return null;
	}}
	public ArrayList<Detalle_PagoVO> validarListaDetalle(int id) throws SQLException{
		consultaDetalle = new Detalle_PagoDAO();
		if(!consultaDetalle.listaDetalle(id).isEmpty()) {
			return consultaDetalle.listaDetalle(id);}
			else {
				return null;
	}}
	public FacturaVO validarBuscarFactura(int id) {
		consultaFactura = new FacturaDAO();
		if(consultaFactura.buscarFactura(id).getFecha() == null ) {
			custom.msg("Factura", 2);
			return null;
		}else {	
		return consultaFactura.buscarFactura(id);
	}}
	public ArrayList<TipoPagoVO> validarListaTipoPago() throws SQLException{
		consultaTipoPago = new TipoPagoDAO();
		return consultaTipoPago.listaTipoPago();
	}
	public boolean validarAddTipo(TipoPagoVO tipo) {
		boolean bool = false;
		consultaTipoPago = new TipoPagoDAO();
		try {
		if(tipo.getTipo().isEmpty())
		{custom.msg("Debe llenar el campo de Tipo",7);}
		else
		{consultaTipoPago.registrarTipoPago(tipo);
				bool = true;
			}} catch (Exception e) {
				e.printStackTrace();
			}return bool;
	}
}
