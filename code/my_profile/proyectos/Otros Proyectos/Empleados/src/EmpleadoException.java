// paradigmas de programaci�n II
// Pr�ctica 6
// Alumno=Mane Isabela Velasco Naranjo
// grupo 512

public class  EmpleadoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public static final String PRIMER_NOMBRE_OBLIGATORIO = "Se requiere el primer nombre ya que es obligatorio.";
    public static final String  APELLIDO_PATERNO_OBLIGATORIO = "Se requiere el apellido paterno ya que es obligatorio.";
    public static final String  N�MERO_EMPLEADO_ENTERO = "Se requiere n�meros enteros para el n�mero de empleado.";
    public static final String  N�MERO_EMPLEADO_N�MEROS_POSITIVOS = "Se requiere solo n�meros mayores a cero n�mero de empleado.";
   public static final String CURP_FORMATO_VALIDO = "Se requiere un formato valido de 18 caracteres: \n 4 letras, 6 n�meros , 1 letra (M � H), 5 letras,1 alfanum�rico y 1 n�mero."; 
    public static final String   TIPO_ORDENACI�N_DE_CONSTANTES_VALIDO= "Escoge una opci�n de los tres tipos de ordenaci�n.";
    public static final String   HORAS_TRABAJADAS_AL_MES_FORMATO_DECIMAL= "Escribe solo n�meros decimales v�lidos para las horas trabajadas";
    public static final String   HORAS_TRABAJADAS_AL_MES_RANGO= "El rango de horas trabajadas debe ser de 6 horas a 240 horas";
    public static final String   SUELDO_HORAS_FORMATO_DECIMAL= "Escribe solo n�meros decimales v�lidos para el sueldo por horas";
    public static final String   SUELDO_HORAS_RANGO= "El rango de sueldo por horas debe ser de 31.12 a 309.60";
    public static final String   PORCENTAJE_DE_TASA_DE_COMISI�N_FORMATO_DECIMAL= "Escribe solo n�meros decimales v�lidos para el porcentaje de tasa de comisi�n";
    public static final String   PORCENTAJE_DE_TASA_DE_COMISI�N_RANGO= "El rango del porcentaje de tasa de comisi�n debe ser de 1% a 93%";
    public static final String   VENTA_MENSUAL_FORMATO_DECIMAL= "Escribe solo n�meros decimales v�lidos para la venta mensual";
    public static final String   VENTA_MENSUAL_RANGO= "El rango de la venta mensual debe ser de 0 a 74296.55";
    public static final String   SUELDO_DIARIO_FORMATO_DECIMAL= "Escribe solo n�meros decimales v�lidos para el sueldo diario";
    public static final String   SUELDO_DIARIO_RANGO= "El rango del sueldo diario debe ser de 248.93 a 2476.52";
    public static final String   D�AS_TRABAJADOS_AL_MES_FORMATO_DECIMAL="Escribe solo n�meros decimales v�lidos para los d�as trabajados al mes";
    public static final String   D�AS_TRABAJADOS_AL_MES_RANGO= "El n�mero de d�as trabajados debe ser de 1 a 30";
    
	public EmpleadoException(String msg) {
		super(msg);
	}

}