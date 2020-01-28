package Modelo.VO;

public class CuentaPorPagarVO extends Cuenta {
private SolicitudProtesisVO Solicitud;

// METODS GETTER Y SETTER DE ATRIBUTOS DE CLASE CUENTA POR PAGAR

public SolicitudProtesisVO getSolicitud() {
	return Solicitud;
}

public void setSolicitud(SolicitudProtesisVO solicitud) {
	Solicitud = solicitud;
}

// METODOS EXTENDIDO DE CLASE CUENTA
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
