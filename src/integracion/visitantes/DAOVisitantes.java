package integracion.visitantes;

import java.util.Vector;

import negocio.visitantes.TransferVisitantes;

public interface DAOVisitantes {
	public TransferVisitantes leerVisitante(int id);
	
	public Vector<TransferVisitantes> leerTodosVisitante();
	
	public int crearVisitante(TransferVisitantes visitante);
	
	public int actualizarVisitante(TransferVisitantes visitante);
	
	public TransferVisitantes buscarPorDNI(String dni);
}
