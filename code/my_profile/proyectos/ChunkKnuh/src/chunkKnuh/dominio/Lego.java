package chunkKnuh.dominio;

import java.util.ArrayList;
import java.util.Date;

// paradigmas de programación I
// Reto 3- Catálogo de lego
// Alumno=Mane Isabela Velasco Naranjo
// grupo 412
// 20/06/2024

public class Lego {
	// Variables miembro.
	// 1. Número libre.
	private int piezas;
	// 2. Número con rango.
	private float precio;

	// 3. Texto en formato libre.
	private String nombre;

	// 4. Texto con formato predefinido.
	private String código;

	// 5. Fecha, que será un objeto de tipo Date (java.util).
	private Date añoPublicación;

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

	public Date getAñoPublicación() {
		return añoPublicación;
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

	public void setPiezas(String piezas) {
		setPiezas(Integer.parseInt(piezas.trim()));
	}

	public void setPiezas(int piezas) {
		if (piezas > 0) {
			this.piezas = piezas;
		} else {
			this.piezas = 0;
		}
	}

	public void setPrecio(String precio) {
		setPrecio(Float.parseFloat(precio.trim()));
	}

	public void setPrecio(float precio) {
		if (precio > 240.99 && precio < 14213.96) {
			this.precio = precio;
		} else {
			this.precio = 0;
		}
	}

	public void setNombre(String nombre) {
		nombre = nombre.trim();
		if (nombre.isEmpty()) {
			throw new IllegalArgumentException();
		} else {
			this.nombre = nombre;
		}
	}

	public void setCódigo(String código) {
		código = código.trim();
		if (código.matches("[0-9]{5}")) {
			this.código = código;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void setAñoPublicación(Date añoPublicación) {
		this.añoPublicación = añoPublicación;
	}

	public void setEdadMínima(String edadMínima) {
		this.edadMínima = edadMínima;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public void setImpresiones(ArrayList<String> impresiones) {
		this.impresiones = impresiones;
	}

	public void setColores(ArrayList<String> colores) {
		this.colores = colores;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	// Constructor sin parámetros que establezca todos los valores por defecto.
	public Lego() {
		// 1.int.
		this.piezas = 0;

		// 2.int.
		this.precio = 0;

		// 3.String.
		this.nombre = "";

		// 4.String.
		this.código = "";

		// 5.Date.
		this.añoPublicación = null;

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
		return piezas + "- " + precio + "- " +nombre+"-"+ código+"-"+edadMínima;
	}

}
