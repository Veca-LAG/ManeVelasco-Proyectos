// paradigmas de programación II
// Práctica 6
// Alumno=Mane Isabela Velasco Naranjo
// grupo 512
public class EmpleadoPorDias extends Empleado {
	private static final long serialVersionUID = 1L;

	private double sueldoDiario;
	private float diasTrabajadosMes;

	public EmpleadoPorDias(String numeroEmpleado, String curp, String primerNombre, String segundoNombre,
			String apellidoPaterno, String apellidoMaterno, String sueldoDiario, String diasTrabajadosMes)
			throws EmpleadoException {
		super(numeroEmpleado, curp, primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno);

		setSueldoDiario(sueldoDiario);
		setDiasTrabajadosMes(diasTrabajadosMes);
	}

	// SETTERS
	public void setSueldoDiario(String sueldoDiario) throws EmpleadoException {
		try {
			setSueldoDiario(Double.parseDouble(sueldoDiario));
		} catch (NumberFormatException e) {
			throw new EmpleadoException(EmpleadoException.SUELDO_DIARIO_FORMATO_DECIMAL);
		}
	}

	public void setSueldoDiario(double sueldoDiario) throws EmpleadoException {
		if (!(sueldoDiario >= 248.93 && sueldoDiario <= 2476.52)) {
			throw new EmpleadoException(EmpleadoException.SUELDO_DIARIO_RANGO);
		}
		// Aserción de sueldo diario
		assert (this.sueldoDiario >= 0) : "El sueldo diario debe ser un número positivo";
		this.sueldoDiario = sueldoDiario;
	}

	public void setDiasTrabajadosMes(String diasTrabajadosMes) throws EmpleadoException {
		try {
			setDiasTrabajadosMes(Float.parseFloat(diasTrabajadosMes));
		} catch (NumberFormatException e) {
			throw new EmpleadoException(EmpleadoException.DÍAS_TRABAJADOS_AL_MES_FORMATO_DECIMAL);
		}
	}

	public void setDiasTrabajadosMes(float diasTrabajadosMes) throws EmpleadoException {
		if (!(diasTrabajadosMes >= 1 && diasTrabajadosMes <= 30)) {
			throw new EmpleadoException(EmpleadoException.DÍAS_TRABAJADOS_AL_MES_RANGO);
		}
		// Aserción de días trabajados.
		assert (this.diasTrabajadosMes >= 0) : "Los días trabajados en el mes deben ser un número positivo";
		this.diasTrabajadosMes = diasTrabajadosMes;
	}

	// GETTERS
	public float getDiasTrabajadosMes() {
		return diasTrabajadosMes;
	}

	public double getSueldoDiario() {
		return sueldoDiario;
	}

	public String obtenerDetalles() {
		Formateador formato = new Formateador();
		String detallesDeEmpleado = super.obtenerDetalles() + "\n Sueldo diario: "
				+ formato.monedaPrecio(getSueldoDiario()) + "\nDías trabajados por Mes: "
				+ formato.decimalPrecio(getDiasTrabajadosMes());
		return detallesDeEmpleado;
	}

	@Override
	public double calcularSueldoMes() {
		double sueldoMes = diasTrabajadosMes * sueldoDiario;
		return sueldoMes;
	}

}