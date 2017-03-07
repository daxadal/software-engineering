package negocio.celdas;

import java.util.Vector;

public class TransferCeldas {
	public TransferCeldas(){
		this.numCelda = -1;
		this.activo = true;
		this.idCelda = -1;
		this.idSector = -1;
		this.capacidad = 0;
		this.presos = null;
	}
	
	public TransferCeldas(boolean activo, int idCelda, int numCelda, int idSector, int cap, Vector<Integer> presos){
		this.activo = activo;
		this.numCelda = numCelda;
		this.idCelda = idCelda;
		this.idSector = idSector;
		this.capacidad = cap;
		this.presos = presos;
	}
	
	public int getIdCelda() {
		return idCelda;
	}
	public void setIdCelda(int idCelda) {
		this.idCelda = idCelda;
	}
	
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public int getIdSector() {
		return idSector;
	}
	public void setIdSector(int idSector) {
		this.idSector = idSector;
	}
	
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public Vector<Integer> getPresos() {
		return presos;
	}
	public void setPresos(Vector<Integer> presos) {
		this.presos = presos;
	}
	
	public int getNumCelda() {
		return numCelda;
	}

	public void setNumCelda(int numCelda) {
		this.numCelda = numCelda;
	}

	private boolean activo;
	private int numCelda;
	private int idCelda;
	private int idSector;
	private int capacidad;
	private Vector<Integer> presos;
}
