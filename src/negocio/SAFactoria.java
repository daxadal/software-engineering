package negocio;

import negocio.actividades.SAActividades;
import negocio.celdas.SACeldas;
import negocio.presos.SAPresos;
import negocio.sectores.SASector;
import negocio.visitantes.SAVisitantes;
import negocio.visitas.SAVisitas;

public abstract class SAFactoria {
	
	public static SAFactoria getInstance() {
		if (instancia == null)
			instancia = new SAFactoriaImpl();
		return instancia;
	}

	public abstract SASector generarSASector();

	public abstract SACeldas generarSACeldas();

	public abstract SAPresos generarSAPresos();

	public abstract SAVisitantes generarSAVisitantes();

	public abstract SAVisitas generarSAVisitas();

	public abstract SAActividades generarSAActividades();

	private static SAFactoria instancia = null;
}