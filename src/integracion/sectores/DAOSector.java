package integracion.sectores;

import java.util.Vector;

import negocio.sectores.TransferSector;

public interface DAOSector {
	
	public TransferSector leerSector(int id);
	
	public Vector<TransferSector> leerTodosSector();
	
	public int crearSector(TransferSector sector);

	public int actualizarSector(TransferSector sector);
	
	public TransferSector buscarPorNombre(String n);
}