package chunkknuh.excepciones;

public class BaseDatosErrorException extends Exception {
	private static final long serialVersionUID = 1L;
	// BaseDatos
	public static final String[] ERROR_CONTROLADOR = { "No se logr� encontrar la base de datos, verificar controlador.",
			"Error de controlador" };
	public static final String[] ERROR_CONEXI�N = { "No se logr� conectar a la base de datos, verificar url.",
			"Error en la conexi�n" };
	public static final String[] ERROR_DESCONEXI�N = {
			"No se logr� desconectar de la base de datos, verificar conexi�n.", "Error en la desconexi�n" };
	public static final String[] ERROR_CONSULTA = {
			"No se logr� hacer la consulta en la base de datos por errores de sintaxis o tablas inexistentes.",
			"Error en la consulta" };
	public static final String[] ERROR_ACCI�N = {
			"No se logr� hacer la acci�n( insertar, actualizar o eliminar) en la base de datos por errores de sintaxis o tablas inexistentes.",
			"Error en la acci�n" };
	public static final String[] ERROR_BD = { "No se logr� usar la base de datos.", "Error en la base de datos" };

	// BaseDatosLegos
	public static final String[] ERROR_CREAR_BD = { "No se logr� crear la base de datos por errores de sintaxis.",
			"Error en crear base de datos" };
	public static final String[] ERROR_INCORRECTA_BD = {
			"No se logr� conectar a la base de datos con los datos correctos.", "Base de datos incorrecta" };
	public static final String[] ERROR_CREAR_REPORTE_BD = {
			"No se logr� crear un reporte de los datos de la base de datos.", "Error en crear reporte" };

	public static final String[] ERROR_SALIDA_DE_TEMAS = {
			"No se logr� la salida de datos de la base de datos de la tabla tema.", "Error en la salida de temas" };
	public static final String[] ERROR_SALIDA_DE_COLORES = {
			"No se logr� la salida de datos de la base de datos de la tabla color.", "Error en la salida de colores" };
	public static final String[] ERROR_SALIDA_DE_LEGOS = {
			"No se logr� la salida de datos de la base de datos de la tabla lego.", "Error en la salida de legos" };
	public static final String[] ERROR_GUARDAR_LEGO = { "No se logr� insertar el nuevo lego en la base de datos.",
			"Error en la entrada de legos" };

	public static final String[] ERROR_OBTENER_ID_COLORES = {
			"No se logr� obtener el idColor en la tabla color de la base de datos.", "Error en obtener los idColores" };
	public static final String[] ERROR_ENTRADA_LEGO_COLOR = {
			"No se logr� insertar el dato de la tabla lego_color  en la base de datos.",
			"Error en la entrada de lego_color" };
	public static final String[] ERROR_ELIMINAR_LEGO_COLOR = {
			"No se logr� eliminar el dato de la tabla lego_color en la base de datos.", "Error al borrar Lego_color" };

	public static final String[] ERROR_OBTENER_COLORES = {
			"No se logr� la obtener colores de datos de la base de datos de la tabla color.",
			"Error en obtener los colores" };

	public static final String[] ERROR_OBTENER_IDTEMA = {
			"No se logr� obtener el idTema en la tabla tema en la base de datos.", "Error en obtener el idTema" };

	public static final String[] ERROR_OBTENER_TEMA = {
			"No se logr� obtener el tema en la tabla tema en la base de datos.", "Error en obtener el tema" };

	public static final String[] ERROR_OBTENER_EXTENSION = {
			"No se logr� obtener la extensi�n en la tabla imagen en la base de datos.",
			"Error en obtener la extensi�n" };

	public static final String[] ERROR_ELIMINAR_IMAGEN = {
			"No se logr� eliminar el dato de la tabla imagen en la base de datos.", "Error al borrar imagen" };

	public static final String[] ERROR_FORMATO_FECHA = { "No se logr� dar un formato a la fecha de publicaci�n.",
			"Error en formato de fecha de publicaci�n" };
	public static final String[] ERROR_OBTENER_ULTIMO_ID = {
			"No se logr� obtener el �ltimo id ingresado en la base de datos.", "Error en el �ltimo id " };

	public static final String[] ERROR_ENTRADA_COLOR = { "No se logr� insertar el nuevo color en la base de datos.",
			"Error en la entrada de colores" };

	public static final String[] ERROR_INSERTAR_IMAGEN = {
			"No se logr� insertar el archivo en la tabla de imagen en la base de datos.", "Error entrada de imagen" };

	public static final String[] ERROR_ARCHIVO = { "No se logr� cerrar el flujo,debido a un error.",
			"Error al cerrar el FileOutputStream" };
	public static final String[] ERROR_OBTENER_IMAGEN = {
			"No se logr� obtener el archivo de la tabla de imagen de la base de datos.", "Error al obtener la imagen" };
	public String titulo;

	public BaseDatosErrorException(String[] msg) {
		super(msg[0]);
		setTitulo(msg[1]);
	}

	public String getTitulo() {
		return titulo;
	}

	private void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}