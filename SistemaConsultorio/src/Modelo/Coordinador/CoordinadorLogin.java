package Modelo.Coordinador;

import java.sql.SQLException;

import Modelo.Logica.LogicaLogin;
import Modelo.VO.UsuarioVO;

public class CoordinadorLogin {
private LogicaLogin logicaLogin;

private LogicaLogin getLogica() {
	return logicaLogin;
}

public void setLogica(LogicaLogin logicaLogin) {
	this.logicaLogin = logicaLogin;
}
public boolean usuarioID(UsuarioVO user) throws SQLException {
	return logicaLogin.validarUsuarioID(user);
}
public UsuarioVO usuarioActivo(String user) {
	return logicaLogin.validarUserActivo(user);
}
}
