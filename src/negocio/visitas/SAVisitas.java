/**
 * 
 */
package negocio.visitas;

import java.util.Vector;

public interface SAVisitas {

	public int crearVisita(TransferVisitas visita);

	public int eliminarVisita(int id);

	public TransferVisitas mostrarUnoVisita(int id);

	public Vector<TransferVisitas> mostrarTodosVisita();

	public int anyadirVisitanteVisita(int idVisita, int idVisitante);

	public int eliminarVisitanteVisita(int idVisita, int idVisitante);

	public Vector<TransferVisitas> mostrarVisitasPreso(int id);

	public Vector<TransferVisitas> mostrarVisitasVisitante(int id);
}