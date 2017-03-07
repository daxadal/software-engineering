package integracion.sectores;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import negocio.sectores.TransferSector;

public class DAOSectorImpl implements DAOSector {
	
	
	private static String generarNombreArchivo(int id) {
		return "sector" + id + ".txt";
	}

	public TransferSector leerSector(int id) {
		TransferSector sector = new TransferSector();
		String arch = generarNombreArchivo(id);
		try {
			Scanner entrada = new Scanner(new FileReader(arch));
			int activo = entrada.nextInt();
			if (activo == TRUE)
				sector.setActivo(true);
			else
				sector.setActivo(false);
			entrada.nextLine();
			sector.setId(id);
			sector.setNombre(entrada.nextLine());
			
			int numCeldas = entrada.nextInt();
			sector.setNumCeldas(numCeldas);
			
			Vector<Integer> celdas = new Vector<Integer>();
			for (int i=0; i< numCeldas; i++ )
				celdas.addElement(entrada.nextInt());
			sector.setCeldas(celdas);
			
			entrada.close();
			return sector;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public Vector<TransferSector> leerTodosSector() {
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			Vector<TransferSector> sectores = new Vector<TransferSector>();
			
			for(int i = 1; i <= ultimoid; i++){
				TransferSector sector = leerSector(i);
				if(sector.getActivo()){
					sectores.addElement(sector);
				}
			}
			entrada.close();
			return sectores;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	@Override
	public int crearSector(TransferSector sector) {
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			sector.setId(ultimoid+1);
			entrada.close();
			FileWriter salida = new FileWriter(fileID);
			salida.write("" + (ultimoid+1));
			salida.close();
			return this.actualizarSector(sector);
		} catch (IOException e) {
			return -1;
		}
	}
	
	public int actualizarSector(TransferSector sector) {
		try {
			String arch = generarNombreArchivo(sector.getId());
			FileWriter salida = new FileWriter(arch);
			boolean activo = sector.getActivo();
			if (activo)
				salida.write(TRUE + "\n");
			else
				salida.write(FALSE + "\n");
			salida.write(sector.getNombre() + "\n");
			int numCeldas = sector.getNumCeldas();
			salida.write(numCeldas + "\n");
			
			Vector<Integer> celdas = sector.getCeldas();
			for (int i=0; i< numCeldas; i++ )
				salida.write("" + celdas.elementAt(i) +" ");
			salida.close();
			return sector.getId();
		} catch (IOException e) {
			return -1;
		}
	}
	
	@Override
	public TransferSector buscarPorNombre(String n) {
		int i = 1; 
		boolean esta = false;
		TransferSector sector = null;
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			
			while(!esta && i <= ultimoid){
				sector = leerSector(i);
				i++;
				if(sector.getNombre().equals(n))
					esta = true;
			}
			entrada.close();
			if(esta) return sector;
			return null;
		} catch (FileNotFoundException e) {
			return null;
		}	
	}
	
	
	private final int TRUE = 1;
	private final int FALSE = 0;
	private final String fileID = "ultimoIdSector.txt";
}