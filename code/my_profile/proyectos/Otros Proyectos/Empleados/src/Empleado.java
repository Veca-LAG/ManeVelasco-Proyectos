import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

// paradigmas de programación II
// Práctica 6
// Alumno=Mane Isabela Velasco Naranjo
// grupo 512

public abstract class Empleado implements Comparable<Empleado>, Serializable{

	private static final long serialVersionUID = 1L;
	
	private int numeroEmpleado;
	private String curp;
	private String primerNombre;
	private String segundoNombre;
	private String apellidoPaterno;
	private String apellidoMaterno;

	public static final int POR_NOMBRES = 1;
	public static final int POR_APELLIDOS = 2;
	public static final int POR_NUMERO_EMPLEADO = 3;

	private static int tipoOrdenacion = POR_NOMBRES;

	protected class Formateador {
		public String monedaPrecio(double moneda) {
			NumberFormat formato = NumberFormat.getCurrencyInstance();
			String cadenaMoneda = formato.format(moneda);
			return cadenaMoneda;
		}

		public String decimalPrecio(float decimal) {
			DecimalFormat formato = new DecimalFormat("0.0");
			String cadenaDecimal = formato.format(decimal);
			return cadenaDecimal;
		}
	}

	protected Empleado(String numeroEmpleado, String curp, String primerNombre, String segundoNombre,
			String apellidoPaterno, String apellidoMaterno) throws EmpleadoException {
		setNumeroEmpleado(numeroEmpleado);
		setCurp(curp);
		setPrimerNombre(primerNombre);
		setSegundoNombre(segundoNombre);
		setApellidoPaterno(apellidoPaterno);
		setApellidoMaterno(apellidoMaterno);
	}

	// SETTER
	public void setNumeroEmpleado(String numeroEmpleado) throws EmpleadoException {
		try {
			int numeroEmpleo = Integer.parseInt(numeroEmpleado.trim());
			setNumeroEmpleado(numeroEmpleo);
		}catch (NumberFormatException e) {
			throw new EmpleadoException(EmpleadoException.NÚMERO_EMPLEADO_ENTERO);
		}
	}

	public void setNumeroEmpleado(int numeroEmpleado) throws EmpleadoException {
		if(numeroEmpleado<1) {
			throw new EmpleadoException(EmpleadoException.NÚMERO_EMPLEADO_NÚMEROS_POSITIVOS);
		}
		this.numeroEmpleado = numeroEmpleado;
	}

	public void setCurp(String curp) throws EmpleadoException {
		if(!curp.matches("[A-Z]{4}[0-9]{6}[HM]{1}[A-Z]{5}[A-Z0-9]{1}[0-9]{1}")) {
			throw new EmpleadoException(EmpleadoException.CURP_FORMATO_VALIDO);
		}
		this.curp = curp;
	}

	public void setPrimerNombre(String primerNombre) throws EmpleadoException {
		if (primerNombre.trim().isEmpty()) {
			throw new EmpleadoException(EmpleadoException.PRIMER_NOMBRE_OBLIGATORIO);
		}
		assert(primerNombre != null):"nooo";
		this.primerNombre = primerNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public void setApellidoPaterno(String apellidoPaterno) throws EmpleadoException {
		if (apellidoPaterno.trim().isEmpty()) {
			throw new EmpleadoException(EmpleadoException.APELLIDO_PATERNO_OBLIGATORIO);
		}
		this.apellidoPaterno = apellidoPaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public static void setTipoOrdenacion(int tipoOrdenacion) throws EmpleadoException {
		if(!(tipoOrdenacion==POR_NUMERO_EMPLEADO||tipoOrdenacion==POR_APELLIDOS||tipoOrdenacion==POR_NOMBRES)) {
			throw new EmpleadoException(EmpleadoException.TIPO_ORDENACIÓN_DE_CONSTANTES_VALIDO);
		}
		Empleado.tipoOrdenacion = tipoOrdenacion;
	}

	// GETTER
	public int getNumeroEmpleado() {
		return numeroEmpleado;
	}

	public String getCurp() {
		return curp;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public static int getTipoOrdenacion() {
		return tipoOrdenacion;
	}

	// información de empleado
	public String obtenerDetalles() {
		int numeroEmpleado = getNumeroEmpleado();
		String curp = getCurp();
		String primerNombre = getPrimerNombre();
		String segundoNombre = getSegundoNombre();
		String apellidoPaterno = getApellidoPaterno();
		String apellidoMaterno = getApellidoMaterno();
		String detallesDeEmpleado = "Número de empleado: " + numeroEmpleado + "\nCurp: " + curp + "\nPrimer Nombre: "
				+ primerNombre + "\nSegundo Nombre: " + segundoNombre + "\nApellido Paterno: " + apellidoPaterno
				+ "\nApellido Materno: " + apellidoMaterno;
		return detallesDeEmpleado;
	}

	public abstract double calcularSueldoMes();

	public String getSueldoMes() {
		Formateador formateador = new Formateador();
		return formateador.monedaPrecio(calcularSueldoMes());
	}

	@Override
	public int compareTo(Empleado orden) {
		int comparacion = 0;

		// nombres
		if (tipoOrdenacion == POR_NOMBRES) {
			comparacion = primerNombre.compareToIgnoreCase(orden.primerNombre);
			if (comparacion != 0) {
				return comparacion;
			}
			comparacion = segundoNombre.compareToIgnoreCase(orden.segundoNombre);
			if (comparacion != 0) {
				return comparacion;
			}
			comparacion = apellidoPaterno.compareToIgnoreCase(orden.apellidoPaterno);
			if (comparacion != 0) {
				return comparacion;
			}
			return apellidoMaterno.compareToIgnoreCase(orden.apellidoMaterno);

			// apellidos
		} else if (tipoOrdenacion == POR_APELLIDOS) {
			comparacion = apellidoPaterno.compareToIgnoreCase(orden.apellidoPaterno);
			if (comparacion != 0) {
				return comparacion;
			}
			comparacion = apellidoMaterno.compareToIgnoreCase(orden.apellidoMaterno);
			if (comparacion != 0) {
				return comparacion;
			}
			comparacion = primerNombre.compareToIgnoreCase(orden.primerNombre);
			if (comparacion != 0) {
				return comparacion;
			}
			return segundoNombre.compareToIgnoreCase(orden.segundoNombre);

			// número de empleado
		} else {
			return Integer.compare(numeroEmpleado, orden.numeroEmpleado);
		}
	}

}