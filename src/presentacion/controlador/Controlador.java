
package presentacion.controlador;

public abstract class Controlador {

	public static Controlador getInstance() {
		if (instancia == null)
			instancia = new ControladorImp();
		return instancia;
	}

	public abstract void actualiza(int opcion, Object objeto);

	
	private static Controlador instancia = null;
	
	//Constantes generales
	public static final int OPERACION_CORRECTA = 0;
	public static final int DATOS_INCORRECTOS = -3;
	public static final int ESCRITURA_INCORRECTA = -1;
	public static final int COINCIDENCIA_HORARIO = -6;
	
	//sectores
	public static final int SECTOR_EXISTENTE = -2;
	public static final int SECTOR_NO_EXISTENTE = -2;
	public static final int SECTOR_CON_CELDAS = -3;
	
	public static final int CREAR_SECTOR = 10;
	public static final int ELIMINAR_SECTOR = 11;
	public static final int MOSTRAR_PARA_MODIFICAR_SECTOR = 12;
	public static final int MODIFICAR_SECTOR = 13;
	public static final int MOSTRAR_UN_SECTOR = 14;
	public static final int MOSTRAR_TODOS_SECTOR = 15;
	
	//celdas
	public static final int CELDA_EXISTENTE = -4;
	public static final int CELDA_NO_EXISTENTE = -4;
	public static final int CELDA_CON_PRESOS = -3;
	public static final int CELDA_LLENA = -2;
	
	public static final int CREAR_CELDA = 20;
	public static final int ELIMINAR_CELDA = 21;
	public static final int MOSTRAR_PARA_MODIFICAR_CELDA = 22;
	public static final int MODIFICAR_CELDA = 23;
	public static final int MOSTRAR_UNA_CELDA = 24;
	public static final int MOSTRAR_TODAS_CELDAS = 25;
	public static final int MOVER_CELDA_SECTOR = 26;
	
	//presos
	public static final int PRESO_EXISTENTE = -5;
	public static final int PRESO_REINCIDENTE = -4;
	public static final int PRESO_NO_EXISTENTE = -5;
	
	public static final int ALTA_PRESO = 30;
	public static final int ALTA_REINCIDENTE = 31;
	public static final int BAJA_PRESO = 32;
	public static final int MOSTRAR_PARA_ALTA_REINCIDENTE = 33;
	public static final int MOSTRAR_PARA_MODIFICAR_PRESO = 34;
	public static final int MODIFICAR_PRESO = 35;
	public static final int MOSTRAR_UNO_PRESO = 36;
	public static final int MOSTRAR_TODOS_PRESO = 37;
	public static final int MOVER_PRESO_CELDA = 38;
	
	//Actividades
	public static final int ACTIVIDAD_EXISTENTE = -2;
	public static final int ACTIVIDAD_NO_EXISTENTE = -2;
	public static final int ACTIVIDAD_PASADA = -3;
	public static final int ACTIVIDAD_CON_PRESOS = -4;
	public static final int ACTIVIDAD_COMPLETA = -4;
	public static final int PRESO_NO_PERTENECIENTE = -6;
	public static final int PRESO_YA_ANYADIDO = -7;
	
	public static final int CREAR_ACTIVIDAD = 40;
	public static final int ELIMINAR_ACTIVIDAD = 41;
	public static final int MOSTRAR_TODAS_ACTIVIDADES = 42;
	public static final int MOSTRAR_UNA_ACTIVIDAD = 43;
	public static final int ANYADIR_PRESO_A_ACTIVIDAD = 44;
	public static final int ELIMINAR_PRESO_DE_ACTIVIDAD = 45;
	public static final int MOSTRAR_ACTIVIDADES_DE_PRESO = 46;
	
	//Visitas
	public static final int VISITA_EXISTENTE = -2;
	public static final int VISITA_NO_EXISTENTE = -2;
	public static final int VISITA_PASADA = -3;
	public static final int VISITAS_PENDIENTES = -3;
	public static final int VISITA_UN_VISITANTE = -8;

	public static final int CREAR_VISITA = 50;
	public static final int ELIMINAR_VISITA = 51;
	public static final int MOSTRAR_TODAS_VISITAS = 52;
	public static final int MOSTRAR_UNA_VISITA = 53;
	public static final int ANYADIR_VISITANTE_A_VISITA = 54;
	public static final int ELIMINAR_VISITANTE_DE_VISITA = 55;
	public static final int MOSTRAR_VISITAS_DE_PRESO = 56;
	public static final int MOSTRAR_VISITAS_DE_VISITANTE = 57;
	
	//Visitantes
	public static final int VISITANTE_NO_EXISTENTE = -5;
	public static final int VISITANTE_EXISTENTE = -5;
	public static final int VISITANTE_NO_PERTENECIENTE_VISITA = -4;
	public static final int VISITANTE_YA_ANYADIDO = -7;
	
	public static final int ALTA_VISITANTE = 60;
	public static final int BAJA_VISITANTE = 61;
	public static final int MOSTRAR_PARA_MODIFICAR_VISITANTE = 62;
	public static final int MODIFICAR_VISITANTE = 63;
	public static final int MOSTRAR_UNO_VISITANTE = 64;
	public static final int MOSTRAR_TODOS_VISITANTE = 65;

	
}