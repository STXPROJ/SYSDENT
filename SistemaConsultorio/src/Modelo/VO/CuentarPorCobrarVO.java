package Modelo.VO;

public class CuentarPorCobrarVO extends Cuenta {


private FacturaVO factura;

// METODOS GETTER Y SETTER DE LA CLASE CUENTA POR COBRAR

public FacturaVO getFactura() {
	return factura;
}
public void setFactura(FacturaVO factura) {
	this.factura = factura;
}


//METODOS EXTENDIDOS DE LA CLASE CUENTA

@Override
public int getIdCuenta() {
	// TODO Auto-generated method stub
	return super.getIdCuenta();
}
@Override
public double getMonto() {
	// TODO Auto-generated method stub
	return super.getMonto();
}
@Override
public void setIdCuenta(int idCuenta) {
	// TODO Auto-generated method stub
	super.setIdCuenta(idCuenta);
}
@Override
public void setMonto(double monto) {
	// TODO Auto-generated method stub
	super.setMonto(monto);
}
@Override
public String getEstado() {
	// TODO Auto-generated method stub
	return super.getEstado();
}
@Override
public void setEstado(String estado) {
	// TODO Auto-generated method stub
	super.setEstado(estado);
}
@Override
public double getPagado() {
	// TODO Auto-generated method stub
	return super.getPagado();
}
@Override
public void setPagado(double pagado) {
	// TODO Auto-generated method stub
	super.setPagado(pagado);
}




}
