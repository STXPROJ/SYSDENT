package Modelo.Coordinador;

import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Logica.LogicaFactura;
import Modelo.Logica.LogicaPago;
import Modelo.VO.Detalle_PagoVO;
import Modelo.VO.FacturaVO;
import Modelo.VO.PagoFacturaVO;
import Modelo.VO.TipoPagoVO;

public class CoordinadorPagos {
	private LogicaPago logicaPago;
	private LogicaPago getLogica(){
		return logicaPago;
		}

	public void setLogica(LogicaPago logicaPago) {
		this.logicaPago = logicaPago;
	}
	public FacturaVO buscarFactura(int id) {
		return logicaPago.validarBuscarFactura(id);
	}
	public ArrayList<PagoFacturaVO> listaPagos() throws SQLException{
		return logicaPago.validarListaPagos();
	}
	public ArrayList<Detalle_PagoVO> listaDetallePagos(int id) throws SQLException{
		return logicaPago.validarListaDetalle(id);
	}
	public ArrayList<TipoPagoVO> listaTipoPago() throws SQLException{
		return logicaPago.validarListaTipoPago();
	}
	public boolean RegistrarPago(PagoFacturaVO pago,ArrayList<Detalle_PagoVO> listaDetalle) {
		return logicaPago.validarRegistrarPago(pago, listaDetalle);
	}
	public boolean registrarTipo(TipoPagoVO tipo) {
		return logicaPago.validarAddTipo(tipo);
	}

}