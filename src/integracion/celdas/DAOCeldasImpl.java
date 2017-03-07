package integracion.celdas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import negocio.celdas.TransferCeldas;

public class DAOCeldasImpl implements DAOCeldas{

	@Override
	public TransferCeldas leerCelda(int id) {
		TransferCeldas celda = new TransferCeldas();
		String arch = generarNombreArchivo(id);
		try {
			Scanner entrada = new Scanner(new FileReader(arch));
			int activo = entrada.nextInt();
			if (activo == TRUE)
				celda.setActivo(true);
			else
				celda.setActivo(false);
			entrada.nextLine();
			celda.setIdCelda(id);
			celda.setNumCelda(entrada.nextInt());
			celda.setIdSector(entrada.nextInt());
			int capacidad = entrada.nextInt();
			celda.setCapacidad(capacidad);
			
			Vector<Integer> presos = new Vector<Integer>();
			int i = 0;
			while(i < capacidad && entrada.hasNextInt()){
				presos.addElement(entrada.nextInt());
				i++;
			}
			celda.setPresos(presos);
			
			entrada.close();
			
		} catch (FileNotFoundException e) {
			celda = null;
		}
		return celda;
	}

	@Override
	public Vector<TransferCeldas> leerTodosCelda() {
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			Vector<TransferCeldas> celdas = new Vector<TransferCeldas>();
			
			for(int i = 1; i <= ultimoid; i++){
				TransferCeldas celda = leerCelda(i);
				if(celda.getActivo())
					celdas.addElement(celda);
			}
			entrada.close();
			return celdas;
		} catch (FileNotFoundException e) {
			return null;
		}
		
	}

	@Override
	public int crearCelda(TransferCeldas celda) {
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			celda.setIdCelda(ultimoid+1);
			entrada.close();
			FileWriter salida = new FileWriter(fileID);
			salida.write("" + (ultimoid+1));
			salida.close();
			return this.actualizarCelda(celda);
		} catch (IOException e) {
			return -1;
		}
	}
	
	@Override
	public int actualizarCelda(TransferCeldas celda) {
		try {
			String arch = generarNombreArchivo(celda.getIdCelda());
			FileWriter salida = new FileWriter(arch);
			boolean activo = celda.getActivo();
			if (activo)
				salida.write(TRUE + "\n");
			else
				salida.write(FALSE + "\n");
			salida.write(celda.getNumCelda() + "\n");
			salida.write(celda.getIdSector() + "\n");
			salida.write(celda.getCapacidad() + "\n");
			
			Vector<Integer> presos = celda.getPresos();
			for (int i=0; i< presos.size(); i++ )
				salida.write("" + presos.elementAt(i) +" ");
			salida.close();
			return celda.getIdCelda();
		} catch (IOException e) {
			return -1;
		}
	}

	@Override
	public TransferCeldas buscarPorNumCelda(int n) { 
		int i = 1; 
		boolean esta = false;
		TransferCeldas celda = null;
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			entrada.close();
			
			while(!esta && i <= ultimoid){
				celda = leerCelda(i);
				i++;
				if(celda.getNumCelda() ==n){
					esta = true;
				}
			}
			if(esta) return celda;
			return null;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	private static String generarNombreArchivo(int id) {
		return "celda" + id + ".txt";
	}

	private final int TRUE = 1;
	private final int FALSE = 0;
	private final String fileID = "ultimoIdCelda.txt";
}
