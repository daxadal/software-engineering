package negocio.sectores;

import java.util.Vector;

import integracion.DAOFactoria;
import integracion.sectores.DAOSector;

public class SASectorImpl implements SASector {

	public int crearSector(TransferSector sector) {
		int datos_ok = comprobarDatos(sector);
		if (datos_ok == -1)
			return -3;
		else {
			DAOSector dao = DAOFactoria.getInstance().generarDAOSector();
			TransferSector tSector = dao.buscarPorNombre(sector.getNombre());
			if(tSector == null){
				sector.setActivo(true);
				sector.setNumCeldas(0);
				sector.setCeldas(new Vector<Integer>());
				if(dao.crearSector(sector) >= 0){
					return sector.getId();
				}
				else
					return -1;
			}
			else if(!tSector.getActivo()){
				tSector.setActivo(true);
				return dao.actualizarSector(tSector);
			}
			else{
				return -2;
			}
			
		}
	}

	public int eliminarSector(int id) {
		DAOSector dao = DAOFactoria.getInstance().generarDAOSector();
		TransferSector sector = dao.leerSector(id);
		if (sector != null && sector.getActivo()) {
			if(sector.getNumCeldas() == 0){
				sector.setActivo(false);
				return dao.actualizarSector(sector);
			}
			else
				return -3;
		}
		else return -2;
	}
	
	public int modificarSector(TransferSector sector) {
		int datos_ok = comprobarDatos(sector);
		DAOSector dao = DAOFactoria.getInstance().generarDAOSector();
		TransferSector s = dao.buscarPorNombre(sector.getNombre());
		if (datos_ok == -1 || (s!=null && s.getId() != sector.getId()))
			return -3;
		else {
			return dao.actualizarSector(sector);
		}
	}
	
	public TransferSector mostrarUnoSector(int id) {
		TransferSector sector = DAOFactoria.getInstance().generarDAOSector().leerSector(id);
		if (sector != null && sector.getActivo())
			return sector;
		else
			return null;
	}

	public Vector<TransferSector> mostrarTodosSector() {
		Vector<TransferSector> sectores = DAOFactoria.getInstance().generarDAOSector().leerTodosSector();
		
		if(sectores != null && sectores.size() > 0) return sectores;
		return null;
	}
	
	private int comprobarDatos(TransferSector sector) {
		if (sector.getNombre() != null && !sector.getNombre().equals("")
				&& sector.getNumCeldas() >= 0)
			return 0;
		else
			return -1;
	}

}