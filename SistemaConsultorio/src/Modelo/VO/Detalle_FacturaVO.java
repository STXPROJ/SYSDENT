package Modelo.VO;

public class Detalle_FacturaVO extends FacturaVO {
private ServicioVO servicio;
private int cantidad;
private double total;
private String descripcion;

// METODOS EXTENDIDOS DE LA CLASE FACTURA
@Override
public int getIdFactura() {
	// TODO Auto-generated method stub
	return super.getIdFactura();
}
@Override
public void setIdFactura(int idFactura) {
	// TODO Auto-generated method stub
	super.setIdFactura(idFactura);
}
// METODOS GETTER Y SETTER DE LA CLASE DETALLE FACTUR
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public ServicioVO getServicio() {
	return servicio;
}


public void setServicio(ServicioVO servicio) {
	this.servicio = servicio;
}
public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}


}
