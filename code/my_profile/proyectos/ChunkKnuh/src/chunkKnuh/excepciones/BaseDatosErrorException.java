package chunkknuh.excepciones;

public class BaseDatosErrorException extends Exception {
	private static final long serialVersionUID = 1L;
	// BaseDatos
	public static final String[] ERROR_CONTROLADOR = { "No se logró encontrar la base de datos, verificar controlador.",
			"Error de controlador" };
	public static final String[] ERROR_CONEXIÓN = { "No se logró conectar a la base de datos, verificar url.",
			"Error en la conexión" };
	public static final String[] ERROR_DESCONEXIÓN = {
			"No se logró desconectar de la base de datos, verificar conexión.", "Error en la desconexión" };
	public static final String[] ERROR_CONSULTA = {
			"No se logró hacer la consulta en la base de datos por errores de sintaxis o tablas inexistentes.",
			"Error en la consulta" };
	public static final String[] ERROR_ACCIÓN = {
			"No se logró hacer la acción( insertar, actualizar o eliminar) en la base de datos por errores de sintaxis o tablas inexistentes.",
			"Error en la acción" };
	public static final String[] ERROR_BD = { "No se logró usar la base de datos.", "Error en la base de datos" };

	// BaseDatosLegos
	public static final String[] ERROR_CREAR_BD = { "No se logró crear la base de datos por errores de sintaxis.",
			"Error en crear base de datos" };
	public static final String[] ERROR_INCORRECTA_BD = {
			"No se logró conectar a la base de datos con los datos correctos.", "Base de datos incorrecta" };
	public static final String[] ERROR_CREAR_REPORTE_BD = {
			"No se logró crear un reporte de los datos de la base de datos.", "Error en crear reporte" };

	public static final String[] ERROR_SALIDA_DE_TEMAS = {
			"No se logró la salida de datos de la base de datos de la tabla tema.", "Error en la salida de temas" };
	public static final String[] ERROR_SALIDA_DE_COLORES = {
			"No se logró la salida de datos de la base de datos de la tabla color.", "Error en la salida de colores" };
	public static final String[] ERROR_SALIDA_DE_LEGOS = {
			"No se logró la salida de datos de la base de datos de la tabla lego.", "Error en la salida de legos" };
	public static final String[] ERROR_GUARDAR_LEGO = { "No se logró insertar el nuevo lego en la base de datos.",
			"Error en la entrada de legos" };

	public static final String[] ERROR_OBTENER_ID_COLORES = {
			"No se logró obtener el idColor en la tabla color de la base de datos.", "Error en obtener los idColores" };
	public static final String[] ERROR_ENTRADA_LEGO_COLOR = {
			"No se logró insertar el dato de la tabla lego_color  en la base de datos.",
			"Error en la entrada de lego_color" };
	public static final String[] ERROR_ELIMINAR_LEGO_COLOR = {
			"No se logró eliminar el dato de la tabla lego_color en la base de datos.", "Error al borrar Lego_color" };

	public static final String[] ERROR_OBTENER_COLORES = {
			"No se logró la obtener colores de datos de la base de datos de la tabla color.",
			"Error en obtener los colores" };

	public static final String[] ERROR_OBTENER_IDTEMA = {
			"No se logró obtener el idTema en la tabla tema en la base de datos.", "Error en obtener el idTema" };

	public static final String[] ERROR_OBTENER_TEMA = {
			"No se logró obtener el tema en la tabla tema en la base de datos.", "Error en obtener el tema" };

	public static final String[] ERROR_OBTENER_EXTENSION = {
			"No se logró obtener la extensión en la tabla imagen en la base de datos.",
			"Error en obtener la extensión" };

	public static final String[] ERROR_ELIMINAR_IMAGEN = {
			"No se logró eliminar el dato de la tabla imagen en la base de datos.", "Error al borrar imagen" };

	public static final String[] ERROR_FORMATO_FECHA = { "No se logró dar un formato a la fecha de publicación.",
			"Error en formato de fecha de publicación" };
	public static final String[] ERROR_OBTENER_ULTIMO_ID = {
			"No se logró obtener el último id ingresado en la base de datos.", "Error en el último id " };

	public static final String[] ERROR_ENTRADA_COLOR = { "No se logró insertar el nuevo color en la base de datos.",
			"Error en la entrada de colores" };

	public static final String[] ERROR_INSERTAR_IMAGEN = {
			"No se logró insertar el archivo en la tabla de imagen en la base de datos.", "Error entrada de imagen" };

	public static final String[] ERROR_ARCHIVO = { "No se logró cerrar el flujo,debido a un error.",
			"Error al cerrar el FileOutputStream" };
	public static final String[] ERROR_OBTENER_IMAGEN = {
			"No se logró obtener el archivo de la tabla de imagen de la base de datos.", "Error al obtener la imagen" };
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