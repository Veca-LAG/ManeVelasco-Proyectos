package chunkknuh.base_datos;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import chunkknuh.dominio.Lego;
import chunkknuh.excepciones.*;
import chunkknuh.gui.CatalogoDeLego;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class BaseDatosLegos extends BaseDatos {

	public BaseDatosLegos() throws BaseDatosErrorException {
		super();
		if (!validarBD()) {
			crearBD();
			datosBD();
		}
	}

	private boolean validarBD() throws BaseDatosErrorException {
		realizarConexión();
		ArrayList<String> tablas = new ArrayList<>();
		tablas = consultarTablas();
		desconectar();
		if (tablas.size() > 0) {
			for (String auxTablas : tablas) {
				if (auxTablas.equalsIgnoreCase("lego")) {
					return true;
				}
			}
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_INCORRECTA_BD);
		} else {
			return false;
		}
	}

	private void crearBD() throws BaseDatosErrorException {
		try {
			realizarConexión();

			realizarAcción(
					"CREATE TABLE IF NOT EXISTS tema(idTema INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombreTema TEXT COLLATE NOCASE)");

			realizarAcción(
					"CREATE TABLE IF NOT EXISTS color(idColor INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombreColor TEXT COLLATE NOCASE)");

			realizarAcción("CREATE TABLE IF NOT EXISTS lego(idLego INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
					+ "piezas INTEGER, precio REAL, nombre TEXT COLLATE NOCASE, codigo TEXT COLLATE NOCASE,"
					+ " fechaPublicacion TEXT COLLATE NOCASE, edadMinima TEXT COLLATE NOCASE, idTema INTEGER, impresiones TEXT COLLATE NOCASE, "
					+ "FOREIGN KEY(idTema) REFERENCES tema(idTema) ON DELETE CASCADE ON UPDATE CASCADE)");

			realizarAcción(
					"CREATE TABLE IF NOT EXISTS imagen(idLego INTEGER NOT NULL,archivo BLOB, extension TEXT COLLATE NOCASE, "
							+ "FOREIGN KEY(idLego) REFERENCES lego(idLego) ON DELETE CASCADE ON UPDATE CASCADE)");

			realizarAcción("CREATE TABLE IF NOT EXISTS lego_color(idLego INTEGER, idColor INTEGER,"
					+ "FOREIGN KEY(idLego) REFERENCES lego(idLego) ON DELETE CASCADE ON UPDATE CASCADE,"
					+ "FOREIGN KEY(idColor) REFERENCES color(idColor) ON DELETE CASCADE ON UPDATE CASCADE)");

			desconectar();
		} catch (BaseDatosErrorException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_CREAR_BD);
		}

	}

	private void datosBD() throws BaseDatosErrorException {
		realizarConexión();
		realizarAcción("INSERT INTO color (idColor, nombreColor)"
				+ "VALUES(null, 'Rojo'), (null, 'Naranjo'), (null, 'Amarillo'), (null, 'Verde'), (null, 'Azul'), (null, 'Morado')");

		realizarAcción("INSERT INTO tema (idTema, nombreTema)"
				+ "VALUES(null, 'Arquitectura'), (null, 'City'), (null, 'Dreams'), (null, 'Disney'), (null, 'Harry Potter'), (null, 'Ninjago')");
		desconectar();
	}

	// Metodos para ingresar datos
	public ArrayList<String> consultarTemas() throws BaseDatosErrorException {
		realizarConexión();
		ArrayList<String> temas = new ArrayList<>();
		try (ResultSet resultados = realizarConsulta("SELECT nombreTema FROM tema ORDER BY nombreTema ASC")) {
			while (resultados.next()) {
				temas.add(resultados.getString(1));
			}
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_SALIDA_DE_TEMAS);
		}
		desconectar();
		return temas;
	}

	public ArrayList<String> consultarColores() throws BaseDatosErrorException {
		realizarConexión();
		ArrayList<String> colores = new ArrayList<>();
		String consulta = "SELECT nombreColor FROM color ORDER BY nombreColor ASC";

		try (ResultSet resultados = realizarConsulta(consulta)) {
			while (resultados.next()) {
				colores.add(resultados.getString("nombreColor"));
			}
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_SALIDA_DE_COLORES);
		}
		desconectar();
		return colores;
	}

	// Consulta de legos
	public ArrayList<Lego> consultarLegos() throws LegoAdvertenciasException, BaseDatosErrorException {
		ArrayList<Lego> legos = new ArrayList<>();
		realizarConexión();
		try (ResultSet res = realizarConsulta(
				"SELECT idLego, piezas, precio, nombre, codigo, fechaPublicacion,edadMinima, "
						+ "idTema,impresiones  FROM lego " + "ORDER BY nombre, codigo DESC")) {// ciclo
			while (res.next()) {
				Lego lego = new Lego();
				int idLego = res.getInt("idLego");
				lego.setIdLego(idLego);
				lego.setPiezas(res.getInt("piezas"));
				lego.setPrecio(res.getFloat("precio"));
				lego.setNombre(res.getString("nombre"));
				lego.setCódigo(res.getString("codigo"));
				// fecha
				SimpleDateFormat formatoFechaCadena = new SimpleDateFormat("yyyy-MM-dd");
				String fechaPublicacionCadena = res.getString("fechaPublicacion");
				Date fechaPublicacion;
				try {
					fechaPublicacion = formatoFechaCadena.parse(fechaPublicacionCadena);
				} catch (ParseException e) {
					throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_FORMATO_FECHA);
				}
				lego.setFechaPublicación(fechaPublicacion);
				// edadMinima
				lego.setEdadMínima(res.getString("edadMinima"));
				// tema
				int idTema = res.getInt("idTema");
				String tema = obtenerTema(idTema);
				lego.setTema(tema);

				// multivalorado
				String impresiones = res.getString("impresiones");
				ArrayList<String> impresionesLista = new ArrayList<>(Arrays.asList(impresiones.split(",")));
				lego.setImpresiones(impresionesLista);
				lego.setColores(obtenerColores(idLego));

				// imagen
				String extension = obtenerExtension(idLego);
				if (extension != null) {
					String imagen = "imagen" + File.separator + idLego + extension;
					lego.setImagen(imagen);
				} else {
					lego.setImagen(CatalogoDeLego.IMAGEN_DEFECTO);
				}

				// lego
				legos.add(lego);
			}
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_SALIDA_DE_LEGOS);
		}
		desconectar();
		return legos;
	}

	// guardar nuevo, guardar modificado y eliminar registros
	public void guardarLegoBD(Lego lego, String modo) throws BaseDatosErrorException {
		realizarConexión();
		String consulta = null;
		int idLego = lego.getIdLego();
		int piezas = lego.getPiezas();
		float precio = lego.getPrecio();
		String nombre = lego.getNombre();
		String código = lego.getCódigo();
		// Fecha
		Date fechaPublicacion = lego.getFechaPublicación();
		SimpleDateFormat formatoFechaCadena = new SimpleDateFormat("yyyy-MM-dd");
		String fechaPublicacionCadena = formatoFechaCadena.format(fechaPublicacion);
		// edadMinima
		String edadMínima = lego.getEdadMínima();
		// tema
		String tema = lego.getTema();
		int idTema = obtenerIdTema(lego, tema);
		if (idTema == -1) {
			realizarAcción("INSERT INTO tema(idTema, nombreTema) VALUES(NULL, '" + tema + "')");
			idTema = obtenerUltimoId();
		}
		// impresiones
		ArrayList<String> impresionesLista = lego.getImpresiones();
		String impresiones = String.join(",", impresionesLista);

		// guardar metodos
		if (modo.equals("nuevo")) {
			consulta = "INSERT INTO lego(idLego, piezas, precio, nombre, codigo, fechaPublicacion, edadMinima, idTema, impresiones) "
					+ "VALUES (NULL," + piezas + ", " + precio + ", '" + nombre + "', '" + código + "', '"
					+ fechaPublicacionCadena + "', '" + edadMínima + "', " + idTema + ",'" + impresiones + "' )";

		} else if (modo.equals("modificar")) {
			consulta = "UPDATE lego SET " + "piezas=" + piezas + " , " + "precio=" + precio + " , " + "nombre='"
					+ nombre + "' , " + "codigo='" + código + "' , " + "fechaPublicacion='" + fechaPublicacionCadena
					+ "' , " + "edadMinima='" + edadMínima + "' , " + "idTema=" + idTema + " , " + "impresiones='"
					+ impresiones + "'  WHERE idLego= " + idLego;
		} else {
			consulta = "DELETE FROM lego WHERE idLego=" + idLego;
		}
		try {
			// tabla lego
			realizarAcción(consulta);

			int identificadorLego = 0;
			if (modo.equals("eliminar") || modo.equals("modificar")) {
				// tabla lego_color
				identificadorLego = idLego;
				eliminarLegoColor(identificadorLego);
				// tabla imagen
				eliminarImagen(identificadorLego);
			}
			if (modo.equals("nuevo") || modo.equals("modificar")) {
				if (identificadorLego == 0) {
					identificadorLego = obtenerUltimoId();
				}
				// tabla lego_color
				insertarLegoColor(identificadorLego, obtenerIdsColores(lego.getColores()));

				// tabla imagen
				String rutaArchivo = lego.getImagen();
				if (!rutaArchivo.equals(CatalogoDeLego.IMAGEN_DEFECTO)) {
					// insertar imagen
					String extension = rutaArchivo.substring(rutaArchivo.lastIndexOf("."));
					insertarImagen(identificadorLego, rutaArchivo, extension);
					String imagen = "imagen" + File.separator + identificadorLego + extension;
					obtenerImagen(identificadorLego, imagen);
				}
			}
		} catch (Exception e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_GUARDAR_LEGO);
		}
		desconectar();
	}

	private ArrayList<Integer> obtenerIdsColores(ArrayList<String> colores)
			throws SQLException, BaseDatosErrorException {
		ArrayList<Integer> idColor = new ArrayList<>();
		String consulta;
		for (String color : colores) {
			consulta = "SELECT idColor FROM color WHERE nombreColor ='" + color + "'";

			// Ejecutar la consulta
			try (ResultSet res = realizarConsulta(consulta)) {
				// Si encuentra el color, agrega el ID a la lista
				if (res.next()) {
					idColor.add(res.getInt("idColor"));
				}
			} catch (Exception e) {
				throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_OBTENER_ID_COLORES);
			}
		}
		return idColor;
	}

	private ArrayList<String> obtenerColores(int identificadorLego) throws BaseDatosErrorException {
		ArrayList<String> colores = new ArrayList<>();
		String consulta = "SELECT nombreColor FROM color C, lego_color LC "
				+ "WHERE C.idColor = LC.idColor AND LC.idLego = " + identificadorLego + " ORDER BY C.nombreColor ASC";

		try (ResultSet resultados = realizarConsulta(consulta)) {
			while (resultados.next()) {
				colores.add(resultados.getString("nombreColor"));
			}
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_OBTENER_COLORES);
		}
		return colores;
	}

	private void insertarLegoColor(int idLego, ArrayList<Integer> idColores)
			throws SQLException, BaseDatosErrorException {
		for (int idColor : idColores) {
			try {
				realizarAcción("INSERT INTO lego_color (idLego, idColor) VALUES (" + idLego + ", " + idColor + ")");
			} catch (BaseDatosErrorException e) {
				throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_ENTRADA_LEGO_COLOR);
			}
		}
	}

	private void eliminarLegoColor(int idLego) throws BaseDatosErrorException {
		try {
			realizarAcción("DELETE FROM lego_color WHERE idLego=" + idLego);
		} catch (BaseDatosErrorException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_ELIMINAR_LEGO_COLOR);
		}
	}

	private int obtenerIdTema(Lego lego, String tema) throws BaseDatosErrorException {
		// Valor por defecto
		int idTema = -1;
		String consulta = "SELECT idTema FROM tema WHERE nombreTema='" + tema + "'";

		try (ResultSet res = realizarConsulta(consulta)) {
			if (res.next()) {
				idTema = res.getInt("idTema");
			}
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_OBTENER_IDTEMA);
		}
		return idTema;
	}

	private String obtenerTema(int idTema) throws BaseDatosErrorException {
		// Valor por defecto
		String tema = null;
		String consulta = "SELECT nombreTema FROM tema WHERE idTema=" + idTema;
		try (ResultSet res = realizarConsulta(consulta)) {
			if (res.next()) {
				tema = res.getString("nombreTema");
			}
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_OBTENER_TEMA);
		}
		return tema;
	}

	private void eliminarImagen(int idLego) throws BaseDatosErrorException {
		try {
			realizarAcción("DELETE FROM imagen WHERE idLego=" + idLego);
		} catch (BaseDatosErrorException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_ELIMINAR_IMAGEN);
		}
	}

	// insertar imagen
	private void insertarImagen(int idLego, String rutaArchivo, String extension) throws BaseDatosErrorException {
		FileInputStream input = null;
		ByteArrayOutputStream output = null;
		String sql = "INSERT INTO imagen(idLego, archivo,extension) VALUES(?, ?,?)";
		try (PreparedStatement stmt = conexión.prepareStatement(sql)) {
			input = new FileInputStream(new File(rutaArchivo));
			byte[] buffer = new byte[1024];
			output = new ByteArrayOutputStream();
			int len = input.read(buffer);
			while (len != -1) {
				output.write(buffer, 0, len);
				len = input.read(buffer);
			}
			byte[] bytes = output.toByteArray();
			stmt.setInt(1, idLego);
			stmt.setBytes(2, bytes);
			stmt.setString(3, extension);
			stmt.executeUpdate();
		} catch (IOException | SQLException ex) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_INSERTAR_IMAGEN);
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_ARCHIVO);
			}
		}
	}

	private String obtenerExtension(int idLego) throws BaseDatosErrorException {
		// Valor por defecto
		String extension = null;
		String consulta = "SELECT extension FROM imagen WHERE idLego=" + idLego;
		try (ResultSet res = realizarConsulta(consulta)) {
			if (res.next()) {
				extension = res.getString("extension");
			}
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_OBTENER_EXTENSION);
		}
		return extension;
	}

	private void obtenerImagen(int idLego, String rutaArchivo) throws BaseDatosErrorException {
		InputStream input = null;
		FileOutputStream output = null;
		realizarConexión();
		try {
			String sql = "SELECT archivo FROM imagen WHERE idLego = " + idLego;
			ResultSet rs = realizarConsulta(sql);
			File file = new File(rutaArchivo);
			output = new FileOutputStream(file);

			if (rs.next()) {
				input = rs.getBinaryStream("archivo");
				byte[] buffer = new byte[1024];
				int len = input.read(buffer);
				while (len != -1) {
					output.write(buffer, 0, len);
					len = input.read(buffer);
				}
			}
		} catch (SQLException | IOException ex) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_OBTENER_IMAGEN);
		} finally {
			try {
				if (input != null) {
					input.close();
				}
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_ARCHIVO);
			}
		}
		desconectar();
	}

	private int obtenerUltimoId() throws BaseDatosErrorException {
		try {
			ResultSet rs = realizarConsulta("SELECT last_insert_rowid()");// obtener el último ID insertado en sqlite
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_OBTENER_ULTIMO_ID);
		}
		return 0;
	}

	public void agregarColorBD(String color) throws BaseDatosErrorException {
		realizarConexión();
		String consulta = "INSERT INTO color(nombreColor) VALUES('" + color + "')";
		try {
			realizarAcción(consulta);
		} catch (BaseDatosErrorException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_ENTRADA_COLOR);
		}
		desconectar();
	}

	public void crearReporte(String destino) throws BaseDatosErrorException {
		realizarConexión();
		try {
			String jasper = "lego.jasper";
			JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(jasper);
			Map<String, Object> parametros = new HashMap<String, Object>();
			DateFormat formatoFecha = DateFormat.getDateInstance(DateFormat.LONG);// formato largo de fecha
			parametros.put("fecha", formatoFecha.format(new Date()));// parametros
			parametros.put("entidad", "lego");// parametros
			JasperPrint reporteLleno = JasperFillManager.fillReport(reporte, parametros, conexión);
			JasperExportManager.exportReportToPdfFile(reporteLleno, destino);
			Desktop.getDesktop().open(new File(destino));
		} catch (JRException | IOException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_CREAR_REPORTE_BD);
		}
		desconectar();
	}

}