// paradigmas de programación II
// Práctica 6
// Alumno=Mane Isabela Velasco Naranjo
// grupo 512

public class  EmpleadoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public static final String PRIMER_NOMBRE_OBLIGATORIO = "Se requiere el primer nombre ya que es obligatorio.";
    public static final String  APELLIDO_PATERNO_OBLIGATORIO = "Se requiere el apellido paterno ya que es obligatorio.";
    public static final String  NÚMERO_EMPLEADO_ENTERO = "Se requiere números enteros para el número de empleado.";
    public static final String  NÚMERO_EMPLEADO_NÚMEROS_POSITIVOS = "Se requiere solo números mayores a cero número de empleado.";
   public static final String CURP_FORMATO_VALIDO = "Se requiere un formato valido de 18 caracteres: \n 4 letras, 6 números , 1 letra (M ó H), 5 letras,1 alfanumérico y 1 número."; 
    public static final String   TIPO_ORDENACIÓN_DE_CONSTANTES_VALIDO= "Escoge una opción de los tres tipos de ordenación.";
    public static final String   HORAS_TRABAJADAS_AL_MES_FORMATO_DECIMAL= "Escribe solo números decimales válidos para las horas trabajadas";
    public static final String   HORAS_TRABAJADAS_AL_MES_RANGO= "El rango de horas trabajadas debe ser de 6 horas a 240 horas";
    public static final String   SUELDO_HORAS_FORMATO_DECIMAL= "Escribe solo números decimales válidos para el sueldo por horas";
    public static final String   SUELDO_HORAS_RANGO= "El rango de sueldo por horas debe ser de 31.12 a 309.60";
    public static final String   PORCENTAJE_DE_TASA_DE_COMISIÓN_FORMATO_DECIMAL= "Escribe solo números decimales válidos para el porcentaje de tasa de comisión";
    public static final String   PORCENTAJE_DE_TASA_DE_COMISIÓN_RANGO= "El rango del porcentaje de tasa de comisión debe ser de 1% a 93%";
    public static final String   VENTA_MENSUAL_FORMATO_DECIMAL= "Escribe solo números decimales válidos para la venta mensual";
    public static final String   VENTA_MENSUAL_RANGO= "El rango de la venta mensual debe ser de 0 a 74296.55";
    public static final String   SUELDO_DIARIO_FORMATO_DECIMAL= "Escribe solo números decimales válidos para el sueldo diario";
    public static final String   SUELDO_DIARIO_RANGO= "El rango del sueldo diario debe ser de 248.93 a 2476.52";
    public static final String   DÍAS_TRABAJADOS_AL_MES_FORMATO_DECIMAL="Escribe solo números decimales válidos para los días trabajados al mes";
    public static final String   DÍAS_TRABAJADOS_AL_MES_RANGO= "El número de días trabajados debe ser de 1 a 30";
    
	public EmpleadoException(String msg) {
		super(msg);
	}

}