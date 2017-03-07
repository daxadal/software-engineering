package integracion.visitas;

import java.util.Calendar;
import java.util.Vector;

import negocio.visitas.TransferVisitas;

public interface DAOVisitas {
	
	public TransferVisitas leerVisita(int id);

	public Vector<TransferVisitas> leerTodosVisita();
	
	public int crearVisita(TransferVisitas visita);

	public int actualizarVisita(TransferVisitas visita);
	
	public TransferVisitas buscarPorFechaYPreso(Calendar fecha, int idPreso);
}