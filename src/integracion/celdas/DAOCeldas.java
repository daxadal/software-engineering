package integracion.celdas;

import java.util.Vector;

import negocio.celdas.TransferCeldas;

public interface DAOCeldas {
	
	public TransferCeldas leerCelda(int id);
	
	public Vector<TransferCeldas> leerTodosCelda();
	
	public int crearCelda(TransferCeldas celda);

	public int actualizarCelda(TransferCeldas celda);
	
	public TransferCeldas buscarPorNumCelda(int n);
}
