package negocio.visitantes;

import java.util.Vector;

public class TransferVisitanteProfesional extends TransferVisitantes {

	public TransferVisitanteProfesional() {
		this.activo = false;
		this.id = -1;
		this.nombre = null;
		this.apellido = null;
		this.dni = null;
		this.historialVisitas = null;
		this.email = null;
	}
	
	public TransferVisitanteProfesional(boolean activo, int id, String nombre, String apellido,
			String dni , Vector<Integer> historialVisitas, String email) {
		this.activo = activo;
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.historialVisitas = historialVisitas;
		this.email = email;
	}
	
	protected String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNombreCampo() {
		return "Email";
	}
	
	public String getDatoCampo() {
		return email;
	}
	
	public void setDatoCampo(String str) {
		this.email = str;
	}
	
	public String toString() {
		return "Profesional";
	}
	
	
	
}
