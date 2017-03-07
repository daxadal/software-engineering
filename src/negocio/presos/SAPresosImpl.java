package negocio.presos;

import integracion.DAOFactoria;
import integracion.actividades.DAOActividades;
import integracion.celdas.DAOCeldas;
import integracion.presos.DAOPresos;
import integracion.visitantes.DAOVisitantes;
import integracion.visitas.DAOVisitas;

import java.util.Vector;

import negocio.actividades.TransferActividades;
import negocio.celdas.TransferCeldas;
import negocio.visitantes.TransferVisitantes;
import negocio.visitas.TransferVisitas;

public class SAPresosImpl implements SAPresos {
	public int altaPresoNuevo(TransferPresos preso) {
		int datos_ok = comprobarDatos(preso);
		if (datos_ok < 0) return datos_ok;
		else {
			DAOPresos daoPreso = DAOFactoria.getInstance().generarDAOPresos();
			TransferPresos tPreso = daoPreso.buscarPorDNI(preso.getDni());
			DAOCeldas daoCelda = DAOFactoria.getInstance().generarDAOCeldas();
			TransferCeldas celda = daoCelda.leerCelda(preso.getCelda());
			if(tPreso == null){
				if(celda.getPresos().size() < celda.getCapacidad())
				{
					preso.setActivo(true);
					preso.setActividades(new Vector<Integer>());
					preso.setVisitas(new Vector<Integer>());
					int crear_ok = daoPreso.crearPreso(preso);
					if(crear_ok >= 0){
						Vector<Integer> presos = celda.getPresos();
						presos.addElement(preso.getId());
						celda.setPresos(presos);
						
						if(daoCelda.actualizarCelda(celda) >= 0){
							return crear_ok;
						}
					}
					return -1;
				}
				else return -2;
			}
			else if (!tPreso.getActivo())
				return -4;
			else
				return -5;

		}
	}

	public int altaPresoReincidente(TransferPresos preso) {
		DAOPresos daoPresos = DAOFactoria.getInstance().generarDAOPresos();
		TransferPresos tPreso = daoPresos.leerPreso(preso.getId());
		if(tPreso != null && !tPreso.getActivo()){
			TransferCeldas celda = DAOFactoria.getInstance().generarDAOCeldas().leerCelda(preso.getCelda());
			if(celda != null && celda.getCapacidad() > celda.getPresos().size()){
				tPreso.setActivo(true);
				tPreso.setCondena(tPreso.getCondena() + " / " + preso.getCondena());
				tPreso.setCelda(preso.getCelda());
				tPreso.setActividades(new Vector<Integer>());
				tPreso.setVisitas(new Vector<Integer>());
				return daoPresos.actualizarPreso(tPreso);
			}
			else return -3;
		}
		else
			return -5;
	}

	public int bajaPreso(int id) {
		DAOPresos daoPresos = DAOFactoria.getInstance().generarDAOPresos();
		TransferPresos preso = daoPresos.leerPreso(id);
		if(preso != null && preso.getActivo()){
			Vector<Integer> actividades = preso.getActividades();
			boolean ok = true;
			if(actividades.size() != 0){
				DAOActividades daoAct = DAOFactoria.getInstance().generarDAOActividades();
				for(int i = 0; i < actividades.size(); i++){
					TransferActividades act = daoAct.leerActividad(actividades.elementAt(i));
					act.setNumPresos(act.getNumPresos()-1);
					Vector<Integer> presos = act.getPresos();
					presos.remove((Integer)id);
					act.setPresos(presos);
					if(daoAct.actualizarActividad(act) < 0)
						ok = false;
				}
				preso.setActividades(new Vector<Integer>());
			}
			Vector<Integer> visitas = preso.getVisitas();
			if(visitas.size() != 0){
				DAOVisitas daoVisitas = DAOFactoria.getInstance().generarDAOVisitas();
				DAOVisitantes daoVis = DAOFactoria.getInstance().generarDAOVisitantes();
				for(int i = 0; i < visitas.size(); i++){
					TransferVisitas visita = daoVisitas.leerVisita(visitas.elementAt(i));
					Vector<Integer> visitantes = visita.getVisitantes();
					for(int j = 0; j < visitantes.size(); j++){
						TransferVisitantes vis = daoVis.leerVisitante(visitantes.elementAt(j));
						Vector<Integer> histVisitas = vis.getHistorialVisitas();
						histVisitas.remove((Integer)visita.getId());
						vis.setHistorialVisitas(histVisitas);
						if(daoVis.actualizarVisitante(vis) < 0)
							ok = false;
					}
					visita.setActivo(false);
					if(daoVisitas.actualizarVisita(visita) < 0)
						ok = false;
				}
				preso.setVisitas(new Vector<Integer>());
			}
			DAOCeldas daoCeldas = DAOFactoria.getInstance().generarDAOCeldas();
			TransferCeldas celda = daoCeldas.leerCelda(preso.getCelda());
			Vector <Integer> presos = celda.getPresos();
			presos.remove((Integer)id);
			celda.setPresos(presos);
			preso.setActivo(false);
			if(ok && daoCeldas.actualizarCelda(celda) >= 0 && daoPresos.actualizarPreso(preso) >= 0){
				return 0;
			}
			return -1;
		}
		else
			return -5;
	}

	public TransferPresos mostrarDatosPreso(int id, boolean necActivo) {
		TransferPresos preso = DAOFactoria.getInstance().generarDAOPresos().leerPreso(id);
		
		if(!necActivo && preso != null && !preso.getActivo())
				return preso;
		else if(necActivo && preso != null && preso.getActivo())
			return preso;
		else
			return null;
	}

	public Vector<TransferPresos> mostrarDatosPresos() {
		Vector<TransferPresos> presos = DAOFactoria.getInstance().generarDAOPresos().leerTodosPreso();
	
		if(presos != null && presos.size() > 0) return presos;
		return null;
	}

	public int moverPresoAcelda(int idPreso, int idCelda) {
		DAOPresos daoPresos = DAOFactoria.getInstance().generarDAOPresos();
		TransferPresos preso = daoPresos.leerPreso(idPreso);
		
		if(preso != null && preso.getActivo()){
			DAOCeldas daoCelda = DAOFactoria.getInstance().generarDAOCeldas();
			TransferCeldas celda1 = daoCelda.leerCelda(idCelda);
			if(celda1 != null && celda1.getActivo()){
				if(idCelda != preso.getCelda()){
					Vector<Integer> presos1 = celda1.getPresos();
					if(presos1.size() < celda1.getCapacidad()){
						presos1.addElement(idPreso);
						celda1.setPresos(presos1);
						TransferCeldas celda2 = daoCelda.leerCelda(preso.getCelda());
						Vector <Integer> presos2 = celda2.getPresos();
						presos2.remove((Integer)idPreso);
						celda2.setPresos(presos2);
						preso.setCelda(idCelda);
						if(daoPresos.actualizarPreso(preso) >= 0 && daoCelda.actualizarCelda(celda1) >= 0 && daoCelda.actualizarCelda(celda2) >= 0)
							return 0;
						else
							return -1;
					}
					else return -2;
				}
				else return 0;
			}
			else return -4;
		}
		else return -5;
	}

	public int modificarPreso(TransferPresos preso) {
		int datos_ok = comprobarDatos(preso);
		DAOPresos dao = DAOFactoria.getInstance().generarDAOPresos();
		TransferPresos p = dao.buscarPorDNI(preso.getDni());
		if (datos_ok == -1 || (p != null && p.getId() != preso.getId()))
			return -3;
		else {
			return dao.actualizarPreso(preso);
		}
	}
	
	private int comprobarDatos(TransferPresos preso){
		DAOCeldas daoCelda = DAOFactoria.getInstance().generarDAOCeldas();
		TransferCeldas celda = daoCelda.leerCelda(preso.getCelda());
		if(preso.getNombre() != null && preso.getNombre() != "" && preso.getApellido() != null && preso.getApellido() != ""
				&& preso.getCondena() != null && preso.getCondena() != "" && preso.getDni() != null && preso.getDni().length() == 9 
				&& celda != null)
			return 0;
		else if (celda != null && celda.getCapacidad() > celda.getPresos().size()) return -2; 
		else return -3;
	}
}