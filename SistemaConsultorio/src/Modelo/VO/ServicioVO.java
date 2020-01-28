package Modelo.VO;

public class ServicioVO {
private int idServicio;
private String Nombre;
private String Descripcion;
private double Precio;
private TipoServicioVO tipoServicio;

// METODOS GETTER Y SETTER DE LA CLASE SERVICIO




public String getNombre() {
	return Nombre;
}
public void setIdServicio(int idServicio) {
	this.idServicio = idServicio;
}
public TipoServicioVO getTipoServicio() {
	return tipoServicio;
}
public void setTipoServicio(TipoServicioVO tipoServicio) {
	this.tipoServicio = tipoServicio;
}
public int getIdServicio() {
	return idServicio;
}

public void setNombre(String nombre) {
	Nombre = nombre;
}
public String getDescripcion() {
	return Descripcion;
}
public void setDescripcion(String descripcion) {
	Descripcion = descripcion;
}
public double getPrecio() {
	return Precio;
}
public void setPrecio(double precio) {
	Precio = precio;
}


}
