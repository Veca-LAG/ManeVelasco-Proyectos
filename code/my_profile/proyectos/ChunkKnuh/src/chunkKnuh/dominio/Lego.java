package chunkknuh.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import chunkknuh.excepciones.LegoAdvertenciasException;

//paradigmas de programación II
//Alumno=Mane Isabela Velasco Naranjo
//Reto 2

public class Lego implements Serializable {
	// Preparar la entidad para permitir la serialización. Serializable
	private static final long serialVersionUID = 1L;
	// Variables miembro.
	// FIXME En la clase de la entidad agregar una variable entera para el
	// identificador del registro. Setter
	// y getter por defecto. Inicialización por defecto en el constructor.
	private int idLego;
	// 1. Número libre.
	private int piezas;
	// 2. Número con rango.
	private float precio;

	// 3. Texto en formato libre.
	private String nombre;

	// 4. Texto con formato predefinido.
	private String código;

	// 5. Fecha, que será un objeto de tipo Date (java.util).
	private Date fechaPublicación;

	// 6. Dato obtenido de opciones mutuamente excluyentes fijas.
	// Opciones fijas: 1.5+ ,4+ ,6+ ,9+ ,13+ ,18+ .
	private String edadMínima;

	// 7. Dato obtenido de opciones mutuamente excluyentes dinámicas.
	private String tema;

	// 8. Dato multivalorado de opciones no excluyentes fijas.
	// Opciones multivaloradas fijas: pegatina, plástico, impresión.
	private ArrayList<String> impresiones;

	// 9. Dato multivalorado de opciones no excluyentes dinámicas.
	private ArrayList<String> colores;

	// 10. Imagen, que será un String para la ruta de la imagen.
	private String imagen;

	// Métodos get
	public int getIdLego() {
		return idLego;
	}

	public int getPiezas() {
		return piezas;
	}

	public float getPrecio() {
		return precio;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCódigo() {
		return código;
	}

	public Date getFechaPublicación() {
		return fechaPublicación;
	}

	public String getEdadMínima() {
		return edadMínima;
	}

	public String getTema() {
		return tema;
	}

	public ArrayList<String> getImpresiones() {
		return impresiones;
	}

	public ArrayList<String> getColores() {
		return colores;
	}

	public String getImagen() {
		return imagen;
	}

	// Métodos set.
	public void setIdLego(int idLego) {
		this.idLego = idLego;
	}

	public void setPiezas(String piezas) {
		setPiezas(Integer.parseInt(piezas.trim()));
	}

	public void setPiezas(int piezas) {
		this.piezas = piezas;
	}

	public void setPrecio(String precio) {
		setPrecio(Float.parseFloat(precio.trim()));
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setNombre(String nombre) throws LegoAdvertenciasException {
		nombre = nombre.trim();
		if (nombre.isEmpty()) {
			throw new LegoAdvertenciasException(LegoAdvertenciasException.NOMBRE_OBLIGATORIO);
		} else {
			this.nombre = nombre;
		}
	}

	public void setCódigo(String código) throws LegoAdvertenciasException {
		código = código.trim();
		if (código.matches("[0-9]{5}")) {
			this.código = código;
		} else if (código.isEmpty()) {
			throw new LegoAdvertenciasException(LegoAdvertenciasException.CÓDIGO_OBLIGATORIO);
		} else {
			throw new LegoAdvertenciasException(LegoAdvertenciasException.CÓDIGO_FORMATO);
		}
	}

	public void setFechaPublicación(Date fechaPublicación) {
		this.fechaPublicación = fechaPublicación;
	}

	public void setEdadMínima(String edadMínima) {
		this.edadMínima = edadMínima;
	}

	public void setTema(String tema) throws LegoAdvertenciasException {
		this.tema = tema;
	}

	public void setImpresiones(ArrayList<String> impresiones) {
		this.impresiones = impresiones;
	}

	public void setColores(ArrayList<String> colores) throws LegoAdvertenciasException {
		if (colores.isEmpty()) {
			throw new LegoAdvertenciasException(LegoAdvertenciasException.COLORES_OBLIGATORIOS);
		}
		this.colores = colores;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	// Constructor sin parámetros que establezca todos los valores por defecto.
	public Lego() {
		// 0 int
		this.idLego = 0;
		// 1.int.
		this.piezas = 0;

		// 2.int.
		this.precio = 0;

		// 3.String.
		this.nombre = "";

		// 4.String.
		this.código = "";

		// 5.Date.
		this.fechaPublicación = null;

		// 6.String.
		this.edadMínima = "";

		// 7.String.
		this.tema = "";

		// 8.list String.
		this.impresiones = null;

		// 9.list String.
		this.colores = null;

		// 10.String.
		this.imagen = "";
	}

	// La representación en cadena de la entidad.
	@Override
	public String toString() {
		// return piezas + "- " + precio + "- " +nombre+"-"+ código+"-"+edadMínima;
		return tema + "- " + nombre + "- " + código;
	}

}
