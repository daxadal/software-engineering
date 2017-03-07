package negocio.actividades;

import java.util.Calendar;
import java.util.Vector;

import negocio.presos.TransferPresos;
import negocio.visitas.TransferVisitas;
import integracion.DAOFactoria;
import integracion.actividades.DAOActividades;
import integracion.presos.DAOPresos;
import integracion.visitas.DAOVisitas;

public class SAActividadesImpl implements SAActividades {
	
	private int comprobarDatos(TransferActividades actividad) {
		Calendar fecha = actividad.getFecha();
		if(actividad.getNombre() != null && actividad.getNombre() != "" && actividad.getAforo() > 0 && fecha.after(Calendar.getInstance())){
			return 0;
		}
		else
			return -1;
	}

	public int crearActividad(TransferActividades actividad) {
		int datos_ok = comprobarDatos(actividad);
		if(datos_ok == -1){
			return -3;
		}
		else{
			DAOActividades daoActividad = DAOFactoria.getInstance().generarDAOActividades();
			TransferActividades tActividades = daoActividad.buscarPorNombreYFecha(actividad.getNombre(), actividad.getFecha());
			if(tActividades == null || !tActividades.getActivo()){
				actividad.setActivo(true);
				actividad.setNumPresos(0);
				actividad.setPresos(new Vector<Integer>());
				return daoActividad.crearActividad(actividad);
			}
			else if(!tActividades.getActivo()){
				tActividades.setActivo(true);
				tActividades.setAforo(actividad.getAforo());
				tActividades.setDescripcion(actividad.getDescripcion());
				return daoActividad.actualizarActividad(tActividades);
			}
			else{
				return -2;
			}
		}
	}

	public int eliminarActividad(int id) {
		DAOActividades dao = DAOFactoria.getInstance().generarDAOActividades();
		TransferActividades actividad = dao.leerActividad(id);
		if(actividad != null && actividad.getActivo()){
			if(actividad.getFecha().after(Calendar.getInstance())){
				if(actividad.getNumPresos() == 0){
					actividad.setActivo(false);
					return dao.actualizarActividad(actividad);
				}
				else
					return -4;
			}
			else
				return -3;
		}
		else
			return -2;
	}

	public int anyadirPresoActividad(int idActividad, int idPreso) {
		DAOActividades daoActividad = DAOFactoria.getInstance().generarDAOActividades();
		TransferActividades actividad = daoActividad.leerActividad(idActividad);
		if(actividad != null && actividad.getActivo()){
			if(actividad.getFecha().after(Calendar.getInstance())){
				if(actividad.getNumPresos() < actividad.getAforo()){
					DAOPresos daoPreso = DAOFactoria.getInstance().generarDAOPresos();
					TransferPresos preso = daoPreso.leerPreso(idPreso);
					if(preso != null && preso.getActivo()){
						Vector<Integer> actividades = preso.getActividades();
						int i = 0;
						boolean ok = true;
						while(ok && i < actividades.size()){
							TransferActividades act = daoActividad.leerActividad(actividades.elementAt(i));
							if(actividad.getFecha().compareTo(act.getFecha()) == 0)
								ok = false;
							i++;
						}
						
						DAOVisitas daoV = DAOFactoria.getInstance().generarDAOVisitas();
						Vector<Integer> visitas = preso.getVisitas();
						i = 0;
						while(ok && i < visitas.size()){
							TransferVisitas visita = daoV.leerVisita(visitas.elementAt(i));
							if(actividad.getFecha().compareTo(visita.getFecha()) == 0)
								ok = false;
							i++;
						}
						
						if(ok){
							actividades.addElement(idActividad);
							preso.setActividades(actividades);
							Vector<Integer> presos = actividad.getPresos();
							if(!presos.contains(idPreso))
							{
								presos.addElement(idPreso);
								actividad.setPresos(presos);
								actividad.setNumPresos(actividad.getNumPresos()+1);
								if(daoActividad.actualizarActividad(actividad) >= 0 && daoPreso.actualizarPreso(preso) >= 0)
									return 0;
								else
									return -1;
							}
							else return -7;
						}
						else return -6;
					}
					else
						return -5;
				}
				else 
					return -4;
			}
			else
				return -3;
		}
		else
			return -2;
	}

	public int eliminarPresoActividad(int idAct, int idPreso) {
		DAOActividades daoAct = DAOFactoria.getInstance().generarDAOActividades();
		TransferActividades actividad = daoAct.leerActividad(idAct);
		if(actividad != null && actividad.getActivo()){
			if(actividad.getFecha().after(Calendar.getInstance())){
				DAOPresos daoPreso = DAOFactoria.getInstance().generarDAOPresos();
				TransferPresos preso = daoPreso.leerPreso(idPreso);
				if(preso != null && preso.getActivo()){
					Vector<Integer> presos = actividad.getPresos();
					if(presos.contains(idPreso)){
						presos.remove((Integer)idPreso);
						actividad.setPresos(presos);
						actividad.setNumPresos(actividad.getNumPresos()-1);
						Vector<Integer> actividades = preso.getActividades();
						actividades.remove((Integer)idAct);
						if(daoAct.actualizarActividad(actividad) >= 0 && daoPreso.actualizarPreso(preso) >= 0){
							return 0;
						}
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

	public TransferActividades mostrarActividad(int id) {
		TransferActividades actividad = DAOFactoria.getInstance().generarDAOActividades().leerActividad(id);
		if(actividad != null && actividad.getActivo())
			return actividad;
		else
			return null;
	}

	public Vector<TransferActividades> mostrarTodasActividades(){
		Vector<TransferActividades> actividades = DAOFactoria.getInstance().generarDAOActividades().leerTodosActividad();
		
		if(actividades != null && actividades.size() > 0) return actividades;
		return null;
	}
	
	public Vector<TransferActividades> mostrarActividadesPreso(int id) {
		TransferPresos preso = DAOFactoria.getInstance().generarDAOPresos().leerPreso(id);
		if(preso != null && preso.getActivo()){
			Vector <Integer> actividades = preso.getActividades();
			int numActs = actividades.size();
			Vector<TransferActividades> act = new Vector<TransferActividades>();
			DAOActividades daoActividad = DAOFactoria.getInstance().generarDAOActividades();
			
			for(int i = 0; i < numActs; i++){
				act.add(daoActividad.leerActividad(actividades.elementAt(i)));
			}
			
			return act;
		}
		else
			return null;
	}
}