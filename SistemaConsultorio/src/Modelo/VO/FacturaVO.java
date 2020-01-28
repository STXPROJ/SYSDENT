package Modelo.VO;



public class FacturaVO {
private int idFactura;
private PacienteVO Paciente;
private String Fecha;
private UsuarioVO Usuario;
private boolean estado;
private int descuento;
private double total;
private double pagado;
private double pendiente;






public int getIdFactura() {
	return idFactura;
}

public void setIdFactura(int idFactura) {
	this.idFactura = idFactura;
}

public PacienteVO getPaciente() {
	return Paciente;
}
public void setPaciente(PacienteVO paciente) {
	Paciente = paciente;
}
public String getFecha() {
	return Fecha;
}
public void setFecha(String fecha) {
	Fecha = fecha;
}
public UsuarioVO getUsuario() {
	return Usuario;
}
public void setUsuario(UsuarioVO usuario) {
	Usuario = usuario;
}

public double getPendiente() {
	return pendiente;
}

public void setPendiente(double pendiente) {
	this.pendiente = pendiente;
}

public double getPagado() {
	return pagado;
}

public void setPagado(double pagado) {
	this.pagado = pagado;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
}

public int getDescuento() {
	return descuento;
}

public void setDescuento(int descuento) {
	this.descuento = descuento;
}

public boolean isEstado() {
	return estado;
}

public void setEstado(boolean estado) {
	this.estado = estado;
}


}
