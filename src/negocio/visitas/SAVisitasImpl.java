
package negocio.visitas;

import integracion.DAOFactoria;
import integracion.presos.DAOPresos;
import integracion.visitantes.DAOVisitantes;
import integracion.visitas.DAOVisitas;

import java.util.Calendar;
import java.util.Vector;

import negocio.presos.TransferPresos;
import negocio.visitantes.TransferVisitantes;

public class SAVisitasImpl implements SAVisitas {

	private int comprobarDatos(TransferVisitas visita) {
		
		boolean errorPreso = false;
		boolean errorVisitante = false;
		int i = 0;
		TransferVisitas tVisitaAux;
		
		TransferPresos preso = DAOFactoria.getInstance().generarDAOPresos().leerPreso(visita.getPreso());
		if (preso != null && preso.getActivo()){
			Vector<Integer> visitasPreso = preso.getVisitas();
			while (i<visitasPreso.size() && !errorPreso){
				tVisitaAux = DAOFactoria.getInstance().generarDAOVisitas().leerVisita(visitasPreso.elementAt(i));
				if (tVisitaAux.getFecha().compareTo(visita.getFecha()) == 0)
					errorPreso = true;
				i++;
			}
		}
		else 
			errorPreso = true;
		
		int j = 0;
		TransferVisitantes visitante = DAOFactoria.getInstance().generarDAOVisitantes().leerVisitante(visita.getVisitantes().elementAt(0));
		if (visitante != null && visitante.getActivo()){
			Vector<Integer> visitasVisitante = visitante.getHistorialVisitas();
			while (j<visitasVisitante.size() && !errorVisitante){
				tVisitaAux = DAOFactoria.getInstance().generarDAOVisitas().leerVisita(visitasVisitante.elementAt(j));
				if (tVisitaAux.getFecha().compareTo(visita.getFecha()) == 0)
					errorVisitante = true;
				j++;
			}
		}
		else
			errorVisitante = true;

		if(visita.getFecha().after(Calendar.getInstance()) && !errorVisitante && !errorPreso){
			return 0;
		}
		else
			return -1;
	}

	public int crearVisita(TransferVisitas visita) {
		int datos_ok = comprobarDatos(visita);
		if(datos_ok == -1){
			return -3;
		}
		else{
			DAOVisitas daoVisita = DAOFactoria.getInstance().generarDAOVisitas();
			TransferVisitas tVisitas = daoVisita.buscarPorFechaYPreso(visita.getFecha(), visita.getPreso());
			if(tVisitas == null){
				visita.setActivo(true);
				visita.setNumVisitantes(1);
				int crear_ok = daoVisita.crearVisita(visita);
				if(crear_ok >= 0){
					DAOPresos daoPreso = DAOFactoria.getInstance().generarDAOPresos();
					TransferPresos preso = daoPreso.leerPreso(visita.getPreso());
					Vector<Integer> visitas = preso.getVisitas();
					visitas.addElement(crear_ok);
					preso.setVisitas(visitas);
					DAOVisitantes daoVis = DAOFactoria.getInstance().generarDAOVisitantes();
					TransferVisitantes vis = daoVis.leerVisitante(visita.getVisitantes().elementAt(0));
					Vector<Integer> visitasV = vis.getHistorialVisitas();
					visitasV.addElement(crear_ok);
					vis.setHistorialVisitas(visitasV);
					if(daoPreso.actualizarPreso(preso) >= 0 && daoVis.actualizarVisitante(vis) >= 0)
						return crear_ok;
				}
				return -1;
			}
			else if(!tVisitas.getActivo()){
				tVisitas.setActivo(true);
				tVisitas.setNumVisitantes(1);
				tVisitas.setVisitantes(visita.getVisitantes());
				DAOPresos daoPreso = DAOFactoria.getInstance().generarDAOPresos();
				TransferPresos preso = daoPreso.leerPreso(visita.getPreso());
				Vector<Integer> visitas = preso.getVisitas();
				visitas.addElement(tVisitas.getId());
				preso.setVisitas(visitas);
				DAOVisitantes daoVis = DAOFactoria.getInstance().generarDAOVisitantes();
				TransferVisitantes vis = daoVis.leerVisitante(visita.getVisitantes().elementAt(0));
				Vector<Integer> visitasV = vis.getHistorialVisitas();
				visitasV.addElement(tVisitas.getId());
				vis.setHistorialVisitas(visitasV);
				return daoVisita.actualizarVisita(tVisitas);
			}
			else{
				return -2;
			}
		}
	}

	public int eliminarVisita(int id) {
		DAOVisitas daoVisitas = DAOFactoria.getInstance().generarDAOVisitas();
		TransferVisitas visita = daoVisitas.leerVisita(id);
		if(visita != null && visita.getActivo()){
			if(visita.getFecha().after(Calendar.getInstance())){
				DAOVisitantes daoVis = DAOFactoria.getInstance().generarDAOVisitantes();
				DAOPresos daoPresos = DAOFactoria.getInstance().generarDAOPresos();
				boolean ok = true;
				
				TransferPresos preso = daoPresos.leerPreso(visita.getPreso());
				Vector<Integer> visitasP = preso.getVisitas();
				visitasP.remove((Integer)id);
				preso.setVisitas(visitasP);
				
				Vector<Integer> visitantes = visita.getVisitantes();
				for(int i = 0; i < visita.getNumVisitantes(); i++){
					TransferVisitantes vis = daoVis.leerVisitante(visitantes.elementAt(i));
					Vector<Integer> visitasV = vis.getHistorialVisitas();
					visitasV.remove((Integer)id);
					vis.setHistorialVisitas(visitasV);
					if(daoVis.actualizarVisitante(vis) < 0)
						ok = false;
				}
				
				visita.setActivo(false);
				if(ok && daoPresos.actualizarPreso(preso) >= 0)
					return daoVisitas.actualizarVisita(visita);
				return -1;
			}
			else
				return -3;
		}
		else
			return -2;
	}

	public TransferVisitas mostrarUnoVisita(int id) {
		TransferVisitas visita = DAOFactoria.getInstance().generarDAOVisitas().leerVisita(id);
		if(visita != null && visita.getActivo())
			return visita;
		else
			return null;
	}

	public Vector<TransferVisitas> mostrarTodosVisita() {
		Vector<TransferVisitas> visitas = DAOFactoria.getInstance().generarDAOVisitas().leerTodosVisita();
		
		if(visitas != null && visitas.size() > 0) return visitas;
		return null;

	}

	public int anyadirVisitanteVisita(int idVisita, int idVisitante) {
		DAOVisitas daoVisita = DAOFactoria.getInstance().generarDAOVisitas();
		TransferVisitas visita = daoVisita.leerVisita(idVisita);
		if(visita != null && visita.getActivo()){
			if(visita.getFecha().after(Calendar.getInstance())){
					DAOVisitantes daoVisitante = DAOFactoria.getInstance().generarDAOVisitantes();
					TransferVisitantes visitante = daoVisitante.leerVisitante(idVisitante);
					if(visitante != null && visitante.getActivo()){
						Vector<Integer> visitas = visitante.getHistorialVisitas();
						if(visitas.contains(idVisita)){
							return -7;
						}
						int i = 0;
						boolean ok = true;
						while(ok && i < visitas.size()){
							TransferVisitas visitaV = daoVisita.leerVisita(visitas.elementAt(i));
							if(visitaV.getFecha().compareTo(visita.getFecha()) == 0)
								ok = false;
						}
						
						if(ok){
							visitas.addElement(idVisita);
							visitante.setHistorialVisitas(visitas);
							Vector<Integer> visitantes = visita.getVisitantes();
							visitantes.addElement(idVisitante);
							visita.setVisitantes(visitantes);
							visita.setNumVisitantes(visita.getNumVisitantes()+1);
							if(daoVisita.actualizarVisita(visita) >= 0 && daoVisitante.actualizarVisitante(visitante) >= 0)
								return 0;
							else
								return -1;
						}
						else
							return -6;
					}
					else
						return -5;
			}
			else
				return -3;
		}
		else
			return -2;
	}

	public Vector<TransferVisitas> mostrarVisitasPreso(int id) {
		TransferPresos preso = DAOFactoria.getInstance().generarDAOPresos().leerPreso(id);
		if(preso != null && preso.getActivo()){
			Vector <Integer> visitas = preso.getVisitas();
			int numVisitas = visitas.size();
			Vector<TransferVisitas> visita = new Vector<TransferVisitas>();
			DAOVisitas daoVisita = DAOFactoria.getInstance().generarDAOVisitas();
			
			for(int i = 0; i < numVisitas; i++){
				visita.addElement(daoVisita.leerVisita(visitas.elementAt(i)));
			}
			
			return visita;
		}
		else
			return null;
	}

	public Vector<TransferVisitas> mostrarVisitasVisitante(int id) {
		TransferVisitantes visitante = DAOFactoria.getInstance().generarDAOVisitantes().leerVisitante(id);
		if( visitante != null && visitante.getActivo()){
			Vector <Integer> visitas = visitante.getHistorialVisitas();
			int numVisitas = visitas.size();
			Vector<TransferVisitas> visita = new Vector<TransferVisitas>();
			DAOVisitas daoVisita = DAOFactoria.getInstance().generarDAOVisitas();
			
			for(int i = 0; i < numVisitas; i++){
				visita.addElement(daoVisita.leerVisita(visitas.elementAt(i)));
			}
			
			return visita;
		}
		else
			return null;
	}

	public int eliminarVisitanteVisita(int idVisita, int idVisitante) {
		DAOVisitas daoVisita = DAOFactoria.getInstance().generarDAOVisitas();
		TransferVisitas visita = daoVisita.leerVisita(idVisita);
		if(visita != null && visita.getActivo()){
			if(visita.getFecha().after(Calendar.getInstance())){
					DAOVisitantes daoVisitante = DAOFactoria.getInstance().generarDAOVisitantes();
					TransferVisitantes visitante = daoVisitante.leerVisitante(idVisitante);
					if(visitante != null && visitante.getActivo()){
						if(visita.getNumVisitantes() == 1)
							return -8;
						Vector<Integer> visitas = visitante.getHistorialVisitas();
						if(visitas.contains((Integer) idVisita)){
							visitas.remove((Integer) idVisita);
							visitante.setHistorialVisitas(visitas);
							Vector<Integer> visitantes = visita.getVisitantes();
							visitantes.remove((Integer) idVisitante);
							visita.setVisitantes(visitantes);
							visita.setNumVisitantes(visita.getNumVisitantes()-1);
							if(daoVisita.actualizarVisita(visita) >= 0 && daoVisitante.actualizarVisitante(visitante) >= 0)
								return 0;
							else 
								return -1;
						}
						else
							return -4;
					}
					else
						return -5;
			}
			else
				return -3;
		}
		else
			return -2;
		
	}
}