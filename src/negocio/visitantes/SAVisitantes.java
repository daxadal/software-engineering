package negocio.visitantes;

import java.util.Vector;

public interface SAVisitantes {

	public int crearVisitante(TransferVisitantes visitante);
	public int eliminarVisitante(int id);
	public int modificarVisitante(TransferVisitantes visitante);
	public TransferVisitantes mostrarUnoVisitante(int id);
	public Vector<TransferVisitantes> mostrarTodosVisitantes();
	
}
