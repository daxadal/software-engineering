package negocio;

import negocio.actividades.SAActividades;
import negocio.actividades.SAActividadesImpl;
import negocio.celdas.SACeldas;
import negocio.celdas.SACeldasImpl;
import negocio.presos.SAPresos;
import negocio.presos.SAPresosImpl;
import negocio.sectores.SASector;
import negocio.sectores.SASectorImpl;
import negocio.visitantes.SAVisitantes;
import negocio.visitantes.SAVisitantesImpl;
import negocio.visitas.SAVisitas;
import negocio.visitas.SAVisitasImpl;

public class SAFactoriaImpl extends SAFactoria {

	public SASector generarSASector() {
		return new SASectorImpl();
	}

	public SACeldas generarSACeldas() {
		return new SACeldasImpl();
	}

	public SAPresos generarSAPresos() {
		return new SAPresosImpl();
	}

	public SAVisitantes generarSAVisitantes() {
		return new SAVisitantesImpl();
	}

	public SAVisitas generarSAVisitas() {
		return new SAVisitasImpl();
	}

	public SAActividades generarSAActividades() {
		return new SAActividadesImpl();
	}
}