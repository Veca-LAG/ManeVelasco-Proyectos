import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//paradigmas de programación II
//Práctica 6
//Alumno=Mane Isabela Velasco Naranjo
//grupo 512

public class Sueldo extends JFrame {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		// colección heterogénea de objetos de tipo Empleado.
		ArrayList<Empleado> empleados = new ArrayList<>();
//FIXME lectura
		try(ObjectInputStream lector=new ObjectInputStream(new FileInputStream("empleados.dat"))){
			empleados=(ArrayList<Empleado>)lector.readObject();
		}catch(IOException | ClassNotFoundException e) {
			
		} 
		do {
			// tipo de empleado escogido
			String[] tiposDeEmpleados = { "Empleado por días", "Empleado por horas", "Empleado por comisión",
					"Empleado por días más comisión" };
			String tipoEscogido = (String) JOptionPane.showInputDialog(null, "Selecciona el tipo de empleado:",
					"Tipo de empleado", JOptionPane.QUESTION_MESSAGE, null, tiposDeEmpleados, tiposDeEmpleados[0]);

			try {

				if (tipoEscogido == null) {
					continue;
				}
				// Datos del empleado
				String numeroEmpleado = (String) JOptionPane.showInputDialog(null, "Escriba el número del empleado",
						"Datos generales", JOptionPane.QUESTION_MESSAGE);
				if (numeroEmpleado == null) {
					continue;
				}

				String curp = (String) JOptionPane.showInputDialog(null, "Escriba su CURP", "Datos generales",
						JOptionPane.QUESTION_MESSAGE);
				if (curp == null) {
					continue;
				}

				String primerNombre = (String) JOptionPane.showInputDialog(null, "Escriba su primer nombre",
						"Datos generales", JOptionPane.QUESTION_MESSAGE);
				if (primerNombre == null) {
					continue;
				}

				String segundoNombre = (String) JOptionPane.showInputDialog(null, "Escriba su segundo nombre",
						"Datos generales", JOptionPane.QUESTION_MESSAGE);
				if (segundoNombre == null) {
					continue;
				}

				String apellidoPaterno = (String) JOptionPane.showInputDialog(null, "Escriba su apellido paterno",
						"Datos generales", JOptionPane.QUESTION_MESSAGE);
				if (apellidoPaterno == null) {
					continue;
				}

				String apellidoMaterno = (String) JOptionPane.showInputDialog(null, "Escriba su apellido materno",
						"Datos generales", JOptionPane.QUESTION_MESSAGE);
				if (apellidoMaterno == null) {
					continue;
				}

				// particulares del empleado
				String porcentajeTasaComision = null;
				String ventaMensual = null;
				String sueldoDiario = null;
				String diasTrabajadosMes = null;

				// diario
				if (tipoEscogido.compareTo(tiposDeEmpleados[0]) == 0
						|| tipoEscogido.compareTo(tiposDeEmpleados[3]) == 0) {

					sueldoDiario = (String) JOptionPane.showInputDialog(null, "Sueldo diario", tipoEscogido,
							JOptionPane.QUESTION_MESSAGE);
					if (sueldoDiario == null) {
						continue;
					}
					diasTrabajadosMes = (String) JOptionPane.showInputDialog(null, "Días trabajados en el mes",
							tipoEscogido, JOptionPane.QUESTION_MESSAGE);
					if (diasTrabajadosMes == null) {
						continue;
					}
					if (tipoEscogido.compareTo(tiposDeEmpleados[0]) == 0) {
						empleados.add(new EmpleadoPorDias(numeroEmpleado, curp, primerNombre, segundoNombre,
								apellidoPaterno, apellidoMaterno, sueldoDiario, diasTrabajadosMes));
					}
				}

				// horas
				if (tipoEscogido.compareTo(tiposDeEmpleados[1]) == 0) {
					String horasTrabajadasMes = (String) JOptionPane.showInputDialog(null, "Horas trabajadas en el mes",
							tipoEscogido, JOptionPane.QUESTION_MESSAGE);
					if (horasTrabajadasMes == null) {
						continue;
					}
					String sueldoPorHora = (String) JOptionPane.showInputDialog(null, "Sueldo por hora", tipoEscogido,
							JOptionPane.QUESTION_MESSAGE);
					if (sueldoPorHora == null) {
						continue;
					}
					empleados.add(new EmpleadoPorHoras(numeroEmpleado, curp, primerNombre, segundoNombre,
							apellidoPaterno, apellidoMaterno, horasTrabajadasMes, sueldoPorHora));
				}

				// comisión
				if (tipoEscogido.compareTo(tiposDeEmpleados[2]) == 0
						|| tipoEscogido.compareTo(tiposDeEmpleados[3]) == 0) {

					porcentajeTasaComision = (String) JOptionPane.showInputDialog(null,
							"Porcentaje de tasa de comisión", tipoEscogido, JOptionPane.QUESTION_MESSAGE);
					if (porcentajeTasaComision == null) {
						continue;
					}
					ventaMensual = (String) JOptionPane.showInputDialog(null, "Venta mensual", tipoEscogido,
							JOptionPane.QUESTION_MESSAGE);
					if (ventaMensual == null) {
						continue;
					}
					if (tipoEscogido.compareTo(tiposDeEmpleados[2]) == 0) {
						empleados.add(new EmpleadoPorComision(numeroEmpleado, curp, primerNombre, segundoNombre,
								apellidoPaterno, apellidoMaterno, porcentajeTasaComision, ventaMensual));
					} else {

						// días mas comisión
						empleados.add(new EmpleadoPorDiasMasComision(numeroEmpleado, curp, primerNombre, segundoNombre,
								apellidoPaterno, apellidoMaterno, porcentajeTasaComision, ventaMensual, sueldoDiario,
								diasTrabajadosMes));
					}
				}

			} catch (EmpleadoException e) {
				// mensaje para validaciones con excepciones
				JOptionPane.showMessageDialog(null, e.getMessage(), "Valores no válidos", JOptionPane.WARNING_MESSAGE);
			}
			// mensaje se pedirá nuevo empleado
		} while (JOptionPane.showConfirmDialog(null, "¿Desea registrar a un nuevo empleado?", "Registro de empleados",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION);

		
		// seleccionar el tipo de ordenación a emplear.


		String[] opcionesDeOrdenacion = { "Por nombres", "Por apellidos", "Por número de empleado" };
		String ordenacion = (String) JOptionPane.showInputDialog(null, "Selecciona el tipo de ordenación:",
				"Tipo de ordenación", JOptionPane.QUESTION_MESSAGE, null, opcionesDeOrdenacion,
				opcionesDeOrdenacion[0]);

		// Tipo de ordenación, solo las tres constantes válidas.
		try {
			if (ordenacion != null) {
				if (ordenacion.compareTo(opcionesDeOrdenacion[0]) == 0) {
					Empleado.setTipoOrdenacion(Empleado.POR_NOMBRES);
				} else if (ordenacion.compareTo(opcionesDeOrdenacion[1]) == 0) {
					Empleado.setTipoOrdenacion(Empleado.POR_APELLIDOS);
				} else {
					Empleado.setTipoOrdenacion(Empleado.POR_NUMERO_EMPLEADO);
				}
			}
			
		} catch (EmpleadoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Opción no válida", JOptionPane.WARNING_MESSAGE);
		}
		
		// FIXME Ordenar la colección heterogénea de empleados
		Collections.sort(empleados);
		
		try(ObjectOutputStream escritor=new ObjectOutputStream(new FileOutputStream("empleados.dat"))){
			escritor.writeObject(empleados);
		}catch(IOException e) {
			
		}

		// información de cada empleado
		for (Empleado empleado : empleados) {
			double sueldo = empleado.calcularSueldoMes();
			if (empleado instanceof EmpleadoPorDias) {
				double sueldoDias = sueldo + (sueldo * 0.08);
				JOptionPane.showMessageDialog(null, empleado.obtenerDetalles() + "\n\nSueldo al mes: " + sueldoDias,
						"Empleado " + empleado.getNumeroEmpleado(), JOptionPane.INFORMATION_MESSAGE);

			} else if (empleado instanceof EmpleadoPorComision && !(empleado instanceof EmpleadoPorDiasMasComision)) {
				double sueldoComision = sueldo + (sueldo * 0.05);
				JOptionPane.showMessageDialog(null, empleado.obtenerDetalles() + "\n\nSueldo al mes: " + sueldoComision,
						"Empleado " + empleado.getNumeroEmpleado(), JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null,
						empleado.obtenerDetalles() + "\n\nSueldo al mes: " + empleado.getSueldoMes(),
						"Empleado " + empleado.getNumeroEmpleado(), JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}