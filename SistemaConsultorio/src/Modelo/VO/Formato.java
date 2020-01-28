package Modelo.VO;
import java.text.SimpleDateFormat;
public class Formato {
	static SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

	public static SimpleDateFormat getFormato() {
		return formato;
	}

	public static void setFormato(SimpleDateFormat formato) {
		Formato.formato = formato;
	}
}
