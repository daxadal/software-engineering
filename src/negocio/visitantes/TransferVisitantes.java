package negocio.visitantes;

import java.util.Vector;

public abstract class TransferVisitantes {
	
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
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
	public Vector<Integer> getHistorialVisitas() {
		return historialVisitas;
	}
	public void setHistorialVisitas(Vector<Integer> historialVisitas) {
		this.historialVisitas = historialVisitas;
	}
	
	public abstract String getNombreCampo();
	
	public abstract String getDatoCampo();
	
	public abstract void setDatoCampo(String str);
	
	public abstract String toString();
	
	protected boolean activo;
	protected int id;
	protected String nombre;
	protected String apellido;
	protected String dni;
	protected Vector<Integer> historialVisitas;
}
