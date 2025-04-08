package chunkknuh.base_datos;

import java.sql.Statement;
import java.util.ArrayList;

import chunkknuh.excepciones.BaseDatosErrorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDatos {
	private final String URL = "jdbc:sqlite:legos.db";
	private final String CONTROLADOR = "org.sqlite.JDBC";
	protected Connection conexión;

	public BaseDatos() {
	}

	protected void realizarConexión() throws BaseDatosErrorException {
		try {
			Class.forName(CONTROLADOR);
			conexión = DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_CONTROLADOR);
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_CONEXIÓN);
		}
	}

	protected void desconectar() throws BaseDatosErrorException {
		try {
			conexión.close();
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_DESCONEXIÓN);
		}
	}

	protected int realizarAcción(String acción) throws BaseDatosErrorException {
		try {
			Statement instrucción = conexión.createStatement();
			int filas = instrucción.executeUpdate(acción);
			return filas;
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_ACCIÓN);
		}
	}

	protected ResultSet realizarConsulta(String consulta) throws BaseDatosErrorException {
		try {
			Statement consultaStatement = conexión.createStatement();
			ResultSet resultado = consultaStatement.executeQuery(consulta);
			return resultado;
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_CONSULTA);
		}
	}

	// obtener mediante metadatos una lista
	// con las tablas de la base de datos.
	protected ArrayList<String> consultarTablas() throws BaseDatosErrorException {
		try {
			String[] tipoTabla = { "TABLE" };
			ResultSet nomTablas;
			nomTablas = conexión.getMetaData().getTables(null, null, null, tipoTabla);
			ArrayList<String> tablas = new ArrayList<>();
			while (nomTablas.next()) {
				tablas.add(nomTablas.getString(3));
			}
			return tablas;
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_BD);
		}
	}

	// getters
	public String getCONTROLADOR() {
		return CONTROLADOR;
	}

	public String getURL() {
		return URL;
	}
}