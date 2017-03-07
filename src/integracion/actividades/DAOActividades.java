package integracion.actividades;

import java.util.Calendar;
import java.util.Vector;

import negocio.actividades.TransferActividades;

public interface DAOActividades {
	
	public TransferActividades leerActividad(int id);

	public Vector<TransferActividades> leerTodosActividad();
	
	public int crearActividad(TransferActividades actividad);

	public int actualizarActividad(TransferActividades actividad);
	
	public TransferActividades buscarPorNombreYFecha(String nombre, Calendar fecha);
}