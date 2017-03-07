package negocio.visitantes;

import integracion.DAOFactoria;
import integracion.visitantes.DAOVisitantes;
import integracion.visitas.DAOVisitas;

import java.util.Calendar;
import java.util.Vector;

import negocio.visitas.TransferVisitas;

public class SAVisitantesImpl implements SAVisitantes {

	@Override
	public int crearVisitante(TransferVisitantes visitante) {
			
		int datos_ok = comprobarDatos(visitante);
		if ( datos_ok == -1 ) 
			return -3;
		else {
			DAOVisitantes dao = DAOFactoria.getInstance().generarDAOVisitantes();
			TransferVisitantes tVisitante = dao.buscarPorDNI(visitante.getDni());
			if(tVisitante == null){
				return dao.crearVisitante(visitante);
			}
			else if(!tVisitante.getActivo()){
				if(visitante instanceof TransferVisitanteProfesional)
					tVisitante = new TransferVisitanteProfesional(true, tVisitante.getId(), visitante.getNombre(), visitante.getApellido(), 
							visitante.getDni() , new Vector<Integer>(), ((TransferVisitanteProfesional) visitante).getEmail());	
				else
					tVisitante = new TransferVisitanteFamiliar(true, tVisitante.getId(), visitante.getNombre(), visitante.getApellido(), 
							visitante.getDni() , new Vector<Integer>(),((TransferVisitanteFamiliar) visitante).getParentesco());
				return dao.actualizarVisitante(tVisitante);
			}
			else{
				return -5;
			}
		}
	}

	@Override
	public int eliminarVisitante(int id) {
		DAOVisitantes dao = DAOFactoria.getInstance().generarDAOVisitantes();
		TransferVisitantes visitante = dao.leerVisitante(id);
		if (visitante != null && visitante.getActivo()) {
			if( !visitasPendientes(visitante.getHistorialVisitas()) ){
				visitante.setActivo(false);
				return dao.actualizarVisitante(visitante);
			}
			else
				return -3;
		}
		else return -5;
	}

	private boolean visitasPendientes(Vector<Integer> historialVisitas) {
		DAOVisitas daoAux = DAOFactoria.getInstance().generarDAOVisitas();
		TransferVisitas tAux;
		boolean b = false;
		int i = 0;
		while ( !b && i < historialVisitas.size() ) {
			tAux = daoAux.leerVisita(historialVisitas.get(i));
			if (tAux.getFecha().after(Calendar.getInstance()))
				b = true;
		}
		return b;
	}

	@Override
	public int modificarVisitante(TransferVisitantes visitante) {
		int datos_ok = comprobarDatos(visitante);
		DAOVisitantes dao = DAOFactoria.getInstance().generarDAOVisitantes();
		TransferVisitantes v = dao.buscarPorDNI(visitante.getDni());
		if(datos_ok == -1 || (v!=null && v.getId() != visitante.getId())){
			return -3;
		}
		else
			return dao.actualizarVisitante(visitante);
	}

	@Override
	public TransferVisitantes mostrarUnoVisitante(int id) {
		TransferVisitantes tVisitantes = DAOFactoria.getInstance().generarDAOVisitantes().leerVisitante(id);
		if ( tVisitantes != null && tVisitantes.getActivo() )
			return tVisitantes;
		else return null;
	}
	
	@Override
	public Vector<TransferVisitantes> mostrarTodosVisitantes() {
		Vector<TransferVisitantes> tVisitantes = DAOFactoria.getInstance().generarDAOVisitantes().leerTodosVisitante();
	
		if(tVisitantes != null && tVisitantes.size() > 0) return tVisitantes;
		return null;
	}

	private int comprobarDatos(TransferVisitantes visitante) {
		if ( visitante.getNombre() != null && !visitante.getNombre().equals("") && visitante.getApellido() != null 
				&& !visitante.getApellido().equals("") && visitante.getDni() != null 
				&& !visitante.getDni().equals("") && visitante.getDni().length() == 9&& visitante.getDatoCampo() != null && !visitante.getDatoCampo().equals(""))
			return 0;
		else return -1;
		
		
	}
	
}
