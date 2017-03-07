package negocio.actividades;

import java.util.Vector;

public interface SAActividades {
	
	public int crearActividad(TransferActividades actividad);

	public int eliminarActividad(int id);
	
	public int anyadirPresoActividad(int idActividad, int idPreso);

	public int eliminarPresoActividad(int idAct, int idPreso);

	public TransferActividades mostrarActividad(int id);

	public Vector<TransferActividades> mostrarActividadesPreso(int id);
	
	public Vector<TransferActividades> mostrarTodasActividades();
}