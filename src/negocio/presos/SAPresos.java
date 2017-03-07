package negocio.presos;

import java.util.Vector;

public interface SAPresos {
	public int altaPresoNuevo(TransferPresos preso);
	
	public int altaPresoReincidente(TransferPresos preso);
	
	public int bajaPreso(int id);
	
	public TransferPresos mostrarDatosPreso(int id, boolean necActivo);
	
	public Vector<TransferPresos> mostrarDatosPresos();
	
	public int moverPresoAcelda(int idPreso, int idCelda);
	
	public int modificarPreso(TransferPresos preso);
}