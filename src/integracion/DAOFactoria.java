package integracion;

import integracion.actividades.DAOActividades;
import integracion.celdas.DAOCeldas;
import integracion.presos.DAOPresos;
import integracion.sectores.DAOSector;
import integracion.visitantes.DAOVisitantes;
import integracion.visitas.DAOVisitas;

public abstract class DAOFactoria {
	
	public static DAOFactoria getInstance() {
		if (instancia == null)
			instancia = new DAOFactoriaImpl();
		return instancia;
	}

	public abstract DAOSector generarDAOSector();
	
	public abstract DAOPresos generarDAOPresos();

	public abstract DAOVisitas generarDAOVisitas();

	public abstract DAOVisitantes generarDAOVisitantes();

	public abstract DAOCeldas generarDAOCeldas();

	public abstract DAOActividades generarDAOActividades();

	
	private static DAOFactoria instancia = null;
}