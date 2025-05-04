// paradigmas de programación II
// Práctica 6
// Alumno=Mane Isabela Velasco Naranjo
// grupo 512
public class EmpleadoPorHoras extends Empleado {

	private static final long serialVersionUID = 1L;
	
	private float horasTrabajadasMes;
	private double sueldoPorHora;

	public EmpleadoPorHoras(String numeroEmpleado, String curp, String primerNombre, String segundoNombre,
			String apellidoPaterno, String apellidoMaterno, String horasTrabajadasMes, String sueldoPorHora)
			throws EmpleadoException {
		super(numeroEmpleado, curp, primerNombre, segundoNombre, apellidoPaterno, apellidoMaterno);

		setHorasTrabajadasMes(horasTrabajadasMes);
		setSueldoPorHora(sueldoPorHora);
	}

	// SETTERS
	public void setHorasTrabajadasMes(String horasTrabajadasMes) throws EmpleadoException {
		try {
			setHorasTrabajadasMes(Float.parseFloat(horasTrabajadasMes));
		} catch (NumberFormatException e) {
			throw new EmpleadoException(EmpleadoException.HORAS_TRABAJADAS_AL_MES_FORMATO_DECIMAL);
		}
	}

	public void setHorasTrabajadasMes(float horasTrabajadasMes) throws EmpleadoException {
		if (!(horasTrabajadasMes >= 6 && horasTrabajadasMes <= 240)) {
			throw new EmpleadoException(EmpleadoException.HORAS_TRABAJADAS_AL_MES_RANGO);
		}
		// Aserción de días trabajados.
		assert (horasTrabajadasMes >= 0) : "Las horas trabajadas en el mes deben ser un número positivo..";

		this.horasTrabajadasMes = horasTrabajadasMes;

	}

	public void setSueldoPorHora(String sueldoPorHora) throws EmpleadoException {
		try {
			setSueldoPorHora(Double.parseDouble(sueldoPorHora));
		} catch (NumberFormatException e) {
			throw new EmpleadoException(EmpleadoException.SUELDO_HORAS_FORMATO_DECIMAL);
		}
	}

	public void setSueldoPorHora(double sueldoPorHora) throws EmpleadoException {
		if (!(sueldoPorHora >= 31.12 && sueldoPorHora <= 309.60)) {
			throw new EmpleadoException(EmpleadoException.SUELDO_HORAS_RANGO);
		}
		// Aserción de sueldo por hora.
		assert (sueldoPorHora >= 0) : "El sueldo por hora debe ser un número positivo.";

		this.sueldoPorHora = sueldoPorHora;
	}

	// GETTERS
	public float getHorasTrabajadasMes() {
		return horasTrabajadasMes;
	}

	public double getSueldoPorHora() {
		return sueldoPorHora;
	}

	public String obtenerDetalles() {
		Formateador formato = new Formateador();
		String detallesDeEmpleado = super.obtenerDetalles() + "\n Horas trabajadas en este mes: "
				+ formato.decimalPrecio(getHorasTrabajadasMes()) + "\n Sueldo por hora: "
				+ formato.monedaPrecio(getSueldoPorHora());
		return detallesDeEmpleado;
	}

	@Override
	public double calcularSueldoMes() {
		double sueldoMes = sueldoPorHora * horasTrabajadasMes;
		return sueldoMes;
	}
}