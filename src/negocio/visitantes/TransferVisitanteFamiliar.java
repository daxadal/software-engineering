package negocio.visitantes;

import java.util.Vector;

public class TransferVisitanteFamiliar extends TransferVisitantes {

	public TransferVisitanteFamiliar() {
		this.activo = false;
		this.id = -1;
		this.nombre = null;
		this.apellido = null;
		this.dni = null;
		this.historialVisitas = null;
		this.parentesco = null;
	}
	
	public TransferVisitanteFamiliar(boolean activo, int id, String nombre, String apellido,
			String dni , Vector<Integer> historialVisitas, String parentesco) {
		this.activo = activo;
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.historialVisitas = historialVisitas;
		this.parentesco = parentesco;
	}
	
	protected String parentesco;

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	
	public String getNombreCampo() {
		return "Parentesco";
	}
	
	public String getDatoCampo() {
		return parentesco;
	}
	
	public void setDatoCampo(String str) {
		this.parentesco = str;
	}
	
	public String toString() {
		return "Familiar";
	}
	
}
