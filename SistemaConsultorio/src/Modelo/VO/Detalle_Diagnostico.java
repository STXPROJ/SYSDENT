package Modelo.VO;

public class Detalle_Diagnostico extends DiagnosticoVO{
private ServicioVO servicio;
private int cantidad;

//METODO EXTRAIDOS DE LA CLASE DIAGNOSTICO
@Override
public int getIdDiagnostico() {
	// TODO Auto-generated method stub
	return super.getIdDiagnostico();
}
@Override
public void setIdDiagnostico(int idDiagnostico) {
	// TODO Auto-generated method stub
	super.setIdDiagnostico(idDiagnostico);
}

//METODOS GETTER Y SETTER DE ATRIBUTOS EXTRAS


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
