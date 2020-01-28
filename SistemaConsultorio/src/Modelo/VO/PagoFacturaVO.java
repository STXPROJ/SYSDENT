package Modelo.VO;


public class PagoFacturaVO{
private int idPago;
private String Fecha;
private FacturaVO factura;
private double total;
// METODOS GETTER Y SETTER DE LA CLASE PAGO FACTURA 


public int getIdPago() {
	return idPago;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
}

public FacturaVO getFactura() {
	return factura;
}

public void setFactura(FacturaVO factura) {
	this.factura = factura;
}

public void setIdPago(int idPago) {
	this.idPago = idPago;
}

public String getFecha() {
	return Fecha;
}
public void setFecha(String fecha) {
	Fecha = fecha;
}



}

