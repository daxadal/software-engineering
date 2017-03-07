package negocio.celdas;

import java.util.Vector;

import integracion.DAOFactoria;
import integracion.celdas.DAOCeldas;
import integracion.sectores.DAOSector;
import negocio.sectores.TransferSector;

public class SACeldasImpl implements SACeldas{

	@Override
	public int crearCelda(TransferCeldas celda) {
		int datos_ok = comprobarDatos(celda);
		if(datos_ok == -1){return -3;}
		else{
			DAOCeldas daoCeldas = DAOFactoria.getInstance().generarDAOCeldas();
			TransferCeldas tCelda = daoCeldas.buscarPorNumCelda(celda.getNumCelda());
			DAOSector daoSector = DAOFactoria.getInstance().generarDAOSector();
			TransferSector sector = daoSector.leerSector(celda.getIdSector());
			Vector<Integer> celdasSector = sector.getCeldas();
			
			if(tCelda == null){
				celda.setActivo(true);
				celda.setPresos(new Vector<Integer>());
				int crear_ok = daoCeldas.crearCelda(celda);
				if(crear_ok >= 0){
					celdasSector.addElement(crear_ok);
					sector.setCeldas(celdasSector);
					sector.setNumCeldas(sector.getNumCeldas()+1);
					
					if(daoSector.actualizarSector(sector) >= 0){
						return crear_ok;
					}
				}
				return -1;
			}
			else if(!tCelda.getActivo()){
				tCelda.setActivo(true);
				tCelda.setIdSector(celda.getIdSector());
				tCelda.setCapacidad(celda.getCapacidad());
				celdasSector.addElement(tCelda.getIdCelda());
				sector.setCeldas(celdasSector);
				sector.setNumCeldas(sector.getNumCeldas()+1);
				
				if(daoSector.actualizarSector(sector) >= 0){
					return daoCeldas.actualizarCelda(tCelda);
				}
				else
					return -1;
			}
			else{
				return -4;
			}
		}
	}
	
	@Override
	public int eliminarCelda(int id) {
		DAOCeldas daoCelda = DAOFactoria.getInstance().generarDAOCeldas();
		TransferCeldas celda = daoCelda.leerCelda(id);
		if(celda != null && celda.getActivo()){
			if(celda.getPresos().size() != 0){
				return -3;
			}
			DAOSector daoSector = DAOFactoria.getInstance().generarDAOSector();
			TransferSector sector = daoSector.leerSector(celda.getIdSector());
			Vector<Integer>celdasSector = sector.getCeldas();
			celdasSector.remove((Integer)id);
			sector.setCeldas(celdasSector);
			sector.setNumCeldas(sector.getNumCeldas()-1);
			celda.setActivo(false);
			if(daoSector.actualizarSector(sector) >= 0 && daoCelda.actualizarCelda(celda) >= 0)
				return 0;
			else
				return -1;
		}
		return -4;
	}

	@Override
	public int modificarCelda(TransferCeldas celda) {
		int datos_ok = comprobarDatos(celda);
		DAOCeldas dao = DAOFactoria.getInstance().generarDAOCeldas();
		TransferCeldas c = dao.buscarPorNumCelda(celda.getNumCelda());
		if (datos_ok == -1 || (c != null && c.getIdCelda() != celda.getIdCelda()))
			return -3;
		else {
			return dao.actualizarCelda(celda);
		}
	}

	@Override
	public TransferCeldas mostrarUnoCelda(int id) {
		TransferCeldas celda = DAOFactoria.getInstance().generarDAOCeldas().leerCelda(id);
		if (celda != null && celda.getActivo())
			return celda;
		else
			return null;
	}

	@Override
	public Vector<TransferCeldas> mostrarTodosCelda() {
		Vector<TransferCeldas> celdas = DAOFactoria.getInstance().generarDAOCeldas().leerTodosCelda();
		
		if(celdas != null && celdas.size() > 0) return celdas;
		return null;
	}

	public int moverCeldaASector(int idCelda, int idSector) {
		DAOFactoria daoF = DAOFactoria.getInstance();
		DAOCeldas daoCelda = daoF.generarDAOCeldas();
		TransferCeldas celda = daoCelda.leerCelda(idCelda);
		if(celda != null && celda.getActivo()){
			DAOSector daoSector = daoF.generarDAOSector();
			TransferSector sector1 = daoSector.leerSector(idSector);
			if(sector1 != null && sector1.getActivo()){
				if(idSector != celda.getIdSector()){
					TransferSector sector2 = daoSector.leerSector(celda.getIdSector());
					Vector<Integer> celdas1 = sector1.getCeldas();
					celdas1.addElement(idCelda);
					sector1.setCeldas(celdas1);
					sector1.setNumCeldas(sector1.getNumCeldas()+1);
					Vector<Integer> celdas2 = sector2.getCeldas();
					celdas2.remove((Integer)idCelda);
					sector2.setCeldas(celdas2);
					sector2.setNumCeldas(sector2.getNumCeldas()-1);
					celda.setIdSector(idSector);
					if(daoCelda.actualizarCelda(celda)>= 0 && daoSector.actualizarSector(sector1) >= 0 && daoSector.actualizarSector(sector2) >= 0)
						return 0;
					else
						return -1;
				}
				return 0;
			}
			else
				return -2;
		}
		else
			return -4;
	}

	private int comprobarDatos(TransferCeldas celda) {
		DAOSector daoSector = DAOFactoria.getInstance().generarDAOSector();
		TransferSector sector = daoSector.leerSector(celda.getIdSector());
		if ((celda.getCapacidad() == 1 || celda.getCapacidad() == 2) && sector != null && sector.getActivo())
			return 0;
		else
			return -1;
	}
}
