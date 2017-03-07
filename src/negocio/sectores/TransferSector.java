package negocio.sectores;

import java.util.Vector;

/** Transfer de Sector. Posee 5 atributos:
 * <ul>
 * - activo: indica si el sector esta dado de alta o de baja <br>
 * - id: unico para cada sector <br>
 * - nombre: describe el sector <br>
 * - numCeldas: n�mero de celdas que posee el sector <br>
 * - celdas: array de id's de celdas
 * </ul>
 * Todos los atributos son accesibles con getters y setters
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class TransferSector {

	/** Crea un sector con id negativo y sin datos
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public TransferSector() {
		this.activo = true;
		this.id = -1;
		this.nombre = null;
		this.numCeldas = 0;
		this.celdas = null;
	}

	/** Crea un sector inicailizado con los datos recibidos
	 * @param id unico para cada sector <br>
	 * @param nombre describe el sector <br>
	 * @param numCeldas n�mero de celdas que posee el sector <br>
	 * @param celdas array de id's de celdas
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public TransferSector(boolean activo, int id, String nombre, int numCeldas, Vector<Integer> celdas) {
		this.activo = activo;
		this.id = id;
		this.nombre = nombre;
		this.numCeldas = numCeldas;
		this.celdas = celdas;
	}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getNombre() {return this.nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}
	
	public int getNumCeldas() {return this.numCeldas;}
	public void setNumCeldas(int numCeldas) {this.numCeldas = numCeldas;}
	
	public Vector<Integer> getCeldas() {return this.celdas;}
	public void setCeldas(Vector<Integer> celdas) {this.celdas = celdas;}
	
	public boolean getActivo() {return activo;}
	public void setActivo(boolean activo) {this.activo = activo;}

	private boolean activo;
	private int id;
	private String nombre;
	private int numCeldas;
	private Vector<Integer> celdas = null;
}