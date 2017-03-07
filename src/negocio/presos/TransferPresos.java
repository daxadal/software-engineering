package negocio.presos;

import java.util.Vector;

public class TransferPresos {
	public TransferPresos(){
		this.activo = true;
		this.id = -1;
		this.nombre = null;
		this.apellido = null;
		this.dni = null;
		this.celda = -1;
		this.condena = null;
		this.actividades = new Vector<Integer>();
		this.visitas = new Vector<Integer>();
	}
	
	public TransferPresos(boolean activo, int id, String nombre, String apellido,
			String dni, int celda, String condena, Vector<Integer> actividades,
			Vector<Integer> visitas) {
		this.activo = activo;
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.celda = celda;
		this.condena = condena;
		this.actividades = actividades;
		this.visitas = visitas;
	}

	private boolean activo;
	private int id;
	private String nombre;
	private String apellido;
	private String dni;
	private int celda;
	private String condena;
	private Vector<Integer> actividades;
	private Vector<Integer> visitas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Vector<Integer> getVisitas() {
		return visitas;
	}

	public void setVisitas(Vector<Integer> visitas) {
		this.visitas = visitas;
	}

	public Vector<Integer> getActividades() {
		return actividades;
	}

	public void setActividades(Vector<Integer> actividades) {
		this.actividades = actividades;
	}

	public int getCelda() {
		return celda;
	}

	public void setCelda(int celda) {
		this.celda = celda;
	}

	public String getCondena() {
		return condena;
	}

	public void setCondena(String condena) {
		this.condena = condena;
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}