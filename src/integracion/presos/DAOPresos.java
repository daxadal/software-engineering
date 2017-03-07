package integracion.presos;

import java.util.Vector;

import negocio.presos.TransferPresos;

public interface DAOPresos {
	
	public TransferPresos leerPreso(int id);

	public Vector<TransferPresos> leerTodosPreso();
	
	public int crearPreso(TransferPresos preso);

	public int actualizarPreso(TransferPresos preso);
	
	public TransferPresos buscarPorDNI(String dni);
}