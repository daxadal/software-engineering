package negocio.sectores;

import java.util.Vector;

public interface SASector {
	
	public int crearSector(TransferSector sector);

	public int eliminarSector(int id);

	public int modificarSector(TransferSector sector);

	public TransferSector mostrarUnoSector(int id);

	public Vector<TransferSector> mostrarTodosSector();
}