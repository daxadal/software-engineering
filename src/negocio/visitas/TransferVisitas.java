
package negocio.visitas;

import java.util.Calendar;
import java.util.Vector;

public class TransferVisitas {

	public TransferVisitas(boolean activo, int id, Calendar fecha, int preso, 
						int numVisitantes, Vector<Integer> visitantes) {
		this.activo = activo;
		this.id = id;
		this.fecha = fecha;
		this.preso = preso;
		this.numVisitantes = numVisitantes;
		this.visitantes = visitantes;
	}
	
	public TransferVisitas() {
		this.activo = false;
		this.id = -1;
		this.fecha = null;
		this.preso = -1;
		this.numVisitantes = 0;
		this.visitantes = null;
	}

	private boolean activo;
	private int id;
	private Calendar fecha;
	private int preso;
	private int numVisitantes;
	private Vector<Integer> visitantes = null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	
	public int getPreso() {
		return preso;
	}

	public void setPreso(int preso) {
		this.preso = preso;
	}
	
	public int getNumVisitantes() {
		return numVisitantes;
	}

	public void setNumVisitantes(int numVisitantes) {
		this.numVisitantes = numVisitantes;
	}

	public Vector<Integer> getVisitantes() {
		return visitantes;
	}

	public void setVisitantes(Vector<Integer> visitantes) {
		this.visitantes = visitantes;
	}
}