// paradigmas de programación II
// Práctica 6
// Alumno=Mane Isabela Velasco Naranjo
// grupo 512

public class EmpleadoPorComision extends Empleado {

	private static final long serialVersionUID = 1L;
	
	private float porcentajeTasaComision;
	private double ventaMensual;

	public EmpleadoPorComision(String numeroEmpleado, String curp, String primerNombre, String segundoNombre,
			String apellidoPaterno, String apellidoMaterno, String porcentajeTasaComision, String ventaMensual)
			throws EmpleadoException {
		super(numeroEmpleado, curp, primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno);

		setPorcentajeTasaComision(porcentajeTasaComision);
		setVentaMensual(ventaMensual);
	}

	// SETTERS
	public void setPorcentajeTasaComision(String porcentajeTasaComision) throws EmpleadoException {
		try {
			setPorcentajeTasaComision(Float.parseFloat(porcentajeTasaComision));
		} catch (NumberFormatException e) {
			throw new EmpleadoException(EmpleadoException.PORCENTAJE_DE_TASA_DE_COMISIÓN_FORMATO_DECIMAL);
		}
	}

	public void setPorcentajeTasaComision(float porcentajeTasaComision) throws EmpleadoException {
		if (!(porcentajeTasaComision >= 1 && porcentajeTasaComision <= 93)) {
			throw new EmpleadoException(EmpleadoException.PORCENTAJE_DE_TASA_DE_COMISIÓN_RANGO);
		}
		// Aserción de porcentaje de tasa de comisión
		assert (porcentajeTasaComision >= 0) : "El porcentaje de tasa de comisión debe ser un número positivo.";
		this.porcentajeTasaComision = porcentajeTasaComision;
	}

	public void setVentaMensual(String ventaMensual) throws EmpleadoException {
		try {
			setVentaMensual(Double.parseDouble(ventaMensual));
		} catch (NumberFormatException e) {
			throw new EmpleadoException(EmpleadoException.VENTA_MENSUAL_FORMATO_DECIMAL);
		}
	}

	public void setVentaMensual(double ventaMensual) throws EmpleadoException {
		if (!(ventaMensual >= 0 && ventaMensual <= 74296.55)) {
			throw new EmpleadoException(EmpleadoException.VENTA_MENSUAL_RANGO);
		}
		// Aserción de porcentaje de venta mensual.
		assert (ventaMensual >= 0) : "La venta mensual  debe ser un número positivo.";
		this.ventaMensual = ventaMensual;
	}

	// GETTERS
	public float getPorcentajeTasaComision() {
		return porcentajeTasaComision;
	}

	public double getVentaMensual() {
		return ventaMensual;
	}

	public String obtenerDetalles() {

		Formateador formato = new Formateador();

		String detallesDeEmpleado = super.obtenerDetalles() + "\nPorcentaje de tasa de comisión: "
				+ formato.decimalPrecio(getPorcentajeTasaComision()) + " % \n Venta mensual: "
				+ formato.monedaPrecio(getVentaMensual());
		return detallesDeEmpleado;
	}

	@Override
	public double calcularSueldoMes() {
		double sueldoMes = ventaMensual * (porcentajeTasaComision / 100);
		return sueldoMes;
	}

}