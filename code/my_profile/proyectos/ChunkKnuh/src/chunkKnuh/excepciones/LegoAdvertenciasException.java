package chunkknuh.excepciones;

//paradigmas de programaci�n II
//Alumno=Mane Isabela Velasco Naranjo
//Reto 2
public class LegoAdvertenciasException extends Exception {
	private static final long serialVersionUID = 1L;
	// Piezas y precio est�n en un modelo
	public static final String[] NOMBRE_OBLIGATORIO = { "Es necesario escribir un nombre para los legos.",
			"Nombre obligatorio" };

	public static final String[] C�DIGO_OBLIGATORIO = { "Es necesario escribir un c�digo para los legos .",
			"C�digo obligatorio" };
	public static final String[] C�DIGO_FORMATO = { "El c�digo escrito debe ser una combinacion de 5 n�meros enteros",
			"C�digo formato invalido" };

	//Las impresiones no son obligatorias

	public static final String[] COLORES_OBLIGATORIOS = { "Es necesario agregar colores para los legos en la lista.",
			"Colores obligatorios" };
	
	public String titulo;

	public LegoAdvertenciasException(String[] msg) {
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