package Modelo.VO;

public class Detalle_ProtesisVO extends SolicitudProtesisVO{
private ServicioVO servicio;
private int cantidad;
private String descripcion;
// METODOS EXTENDIDOS DE LA CLASE SOLICITUD PROTESIS
@Override
public int getIdSolicitud() {
	// TODO Auto-generated method stub
	return super.getIdSolicitud();
}

@Override
public void setIdSolicitud(int idSolicitud) {
	// TODO Auto-generated method stub
	super.setIdSolicitud(idSolicitud);
}

// METODOS GETTER Y SETTER DE ATRIBUTOS EXTRAS DE CLASE DETALLE PROTESIS
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



}
