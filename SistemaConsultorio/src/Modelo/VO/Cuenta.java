package Modelo.VO;

public class Cuenta {
private int idCuenta;
private double Monto;
private String Estado;
private double Pagado;

public int getIdCuenta() {
	return idCuenta;
}

public void setIdCuenta(int idCuenta) {
	this.idCuenta = idCuenta;
}

public double getMonto() {
	return Monto;
}
public void setMonto(double monto) {
	Monto = monto;
}
public String getEstado() {
	return Estado;
}
public void setEstado(String estado) {
	Estado = estado;
}
public double getPagado() {
	return Pagado;
}
public void setPagado(double pagado) {
	Pagado = pagado;
}


}
