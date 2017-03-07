package negocio.actividades;

import java.util.Calendar;
import java.util.Vector;

public class TransferActividades {
	
	public TransferActividades() {
		this.activo = true;
		this.id = -1;
		this.nombre = null;
		this.descripcion = null;
		this.aforo = 0;
		this.fecha = null;
		this.presos = null;
		this.numPresos = 0;
	}
	
	public TransferActividades(boolean activo, int id, String nombre, String descripcion,
			int aforo, Calendar fecha, int numPresos, Vector<Integer> presos) {
		this.activo = activo;
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.aforo = aforo;
		this.fecha = fecha;
		this.presos = presos;
		this.numPresos = numPresos;
	}
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String descripcion;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private Calendar fecha;

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	private int aforo;

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	private int numPresos;

	public int getNumPresos() {
		return numPresos;
	}

	public void setNumPresos(int numPresos) {
		this.numPresos = numPresos;
	}

	private Vector<Integer> presos = null;

	public Vector<Integer> getPresos() {
		return presos;
	}

	public void setPresos(Vector<Integer> presos) {
		this.presos = presos;
	}

	private boolean activo;
	
	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}