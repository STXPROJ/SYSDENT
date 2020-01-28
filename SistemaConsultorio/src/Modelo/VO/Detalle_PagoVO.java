package Modelo.VO;

public class Detalle_PagoVO extends PagoFacturaVO{
	private TipoPagoVO tipoPago;
	private Double monto;
	private String descripcion;
	
	
public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

public TipoPagoVO getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(TipoPagoVO tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	// Metodos Implementados de la clase extendida
	@Override
	public int getIdPago() {
		// TODO Auto-generated method stub
		return super.getIdPago();
	}

	@Override
	public FacturaVO getFactura() {
		// TODO Auto-generated method stub
		return super.getFactura();
	}

	@Override
	public String getFecha() {
		// TODO Auto-generated method stub
		return super.getFecha();
	}

}
