
// paradigmas de programaci�n I
// paradigmas de programaci�n II
// Pr�ctica 6
// Alumno=Mane Isabela Velasco Naranjo
// grupo 512
public class EmpleadoPorDiasMasComision extends EmpleadoPorComision {

	private static final long serialVersionUID = 1L;
	private double sueldoDiario;
	private float diasTrabajadosMes;

	public EmpleadoPorDiasMasComision(String numeroEmpleado, String curp, String primerNombre, String segundoNombre,
			String apellidoPaterno, String apellidoMaterno, String porcentajeTasaComision, String ventaMensual,
			String sueldoDiario, String diasTrabajadosMes) throws EmpleadoException {
		super(numeroEmpleado, curp, primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno,
				porcentajeTasaComision, ventaMensual);

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
		// Aserci�n de sueldo diario.
		assert (sueldoDiario >= 0) : "El sueldo diario debe ser un n�mero positivo.";
		this.sueldoDiario = sueldoDiario;
	}

	public void setDiasTrabajadosMes(String diasTrabajadosMes) throws EmpleadoException {
		try {
			setDiasTrabajadosMes(Float.parseFloat(diasTrabajadosMes));
		} catch (NumberFormatException e) {
			throw new EmpleadoException(EmpleadoException.D�AS_TRABAJADOS_AL_MES_FORMATO_DECIMAL);
		}
	}

	public void setDiasTrabajadosMes(float diasTrabajadosMes) throws EmpleadoException {
		if (!(diasTrabajadosMes >= 1 && diasTrabajadosMes <= 30)) {
			throw new EmpleadoException(EmpleadoException.D�AS_TRABAJADOS_AL_MES_RANGO);
		}
		// Aserci�n de d�as trabajados.
		assert (diasTrabajadosMes >= 0) : "Los d�as trabajados en el mes deben ser un n�mero positivo.";
		this.diasTrabajadosMes = diasTrabajadosMes;
	}

	// GETTERS
	public double getSueldoDiario() {
		return sueldoDiario;
	}

	public float getDiasTrabajadosMes() {
		return diasTrabajadosMes;
	}

	public String obtenerDetalles() {

		Formateador formato = new Formateador();

		String detallesDeEmpleado = super.obtenerDetalles() + "\n Sueldo diario: "
				+ formato.monedaPrecio(getSueldoDiario()) + "\nD�as trabajados por Mes: "
				+ formato.decimalPrecio(getDiasTrabajadosMes());
		return detallesDeEmpleado;
	}

	public double calcularSueldoMes() {
		double sueldoMes = (diasTrabajadosMes * sueldoDiario) + super.calcularSueldoMes();
		return sueldoMes;
	}

}