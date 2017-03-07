package integracion;

import integracion.actividades.DAOActividades;
import integracion.actividades.DAOActividadesImpl;
import integracion.celdas.DAOCeldas;
import integracion.celdas.DAOCeldasImpl;
import integracion.presos.DAOPresos;
import integracion.presos.DAOPresosImpl;
import integracion.sectores.DAOSector;
import integracion.sectores.DAOSectorImpl;
import integracion.visitantes.DAOVisitantes;
import integracion.visitantes.DAOVisitantesImpl;
import integracion.visitas.DAOVisitas;
import integracion.visitas.DAOVisitasImpl;

public class DAOFactoriaImpl extends DAOFactoria {

	public DAOSector generarDAOSector() {
		return new DAOSectorImpl();
	}

	public DAOPresos generarDAOPresos() {
		return new DAOPresosImpl();
	}

	public DAOVisitas generarDAOVisitas() {
		return new DAOVisitasImpl();
	}

	public DAOVisitantes generarDAOVisitantes() {
		return new DAOVisitantesImpl();
	}

	public DAOCeldas generarDAOCeldas() {
		return new DAOCeldasImpl();
	}

	public DAOActividades generarDAOActividades() {
		return new DAOActividadesImpl();
	}
}