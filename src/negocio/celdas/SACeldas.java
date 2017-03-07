package negocio.celdas;

import java.util.Vector;

import negocio.celdas.TransferCeldas;

public interface SACeldas {
	
	public int crearCelda(TransferCeldas celda);

	public int eliminarCelda(int id);

	public int modificarCelda(TransferCeldas celda);

	public TransferCeldas mostrarUnoCelda(int id);

	public Vector<TransferCeldas> mostrarTodosCelda();
	
	public int moverCeldaASector(int idCelda, int idSector);
}