package integracion.presos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import negocio.presos.TransferPresos;

public class DAOPresosImpl implements DAOPresos {
	
	public TransferPresos leerPreso(int id) {
		TransferPresos preso = new TransferPresos();
		String arch = generarNombreArchivo(id);
		try {
			Scanner entrada = new Scanner(new FileReader(arch));
			int activo = entrada.nextInt();
			if (activo == TRUE)
				preso.setActivo(true);
			else
				preso.setActivo(false);
			entrada.nextLine(); 
			preso.setId(id);
			preso.setNombre(entrada.nextLine());
			preso.setApellido(entrada.nextLine());
			preso.setDni(entrada.nextLine());
			preso.setCelda(entrada.nextInt());
			entrada.nextLine();
			preso.setCondena(entrada.nextLine());
			
			Vector<Integer> visitas = new Vector<Integer>();
			int numVisitas = entrada.nextInt();
			for(int i = 0; i <numVisitas; i++)
				visitas.addElement(entrada.nextInt());
			preso.setVisitas(visitas);
			
			Vector<Integer> actividades = new Vector<Integer>();
			int numActs = entrada.nextInt();
			for(int i = 0; i< numActs; i++)
				actividades.addElement(entrada.nextInt());
			preso.setActividades(actividades);
			
			entrada.close();
			return preso;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public Vector<TransferPresos> leerTodosPreso() {
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			Vector<TransferPresos> presos = new Vector<TransferPresos>();
			
			for(int i = 1; i <= ultimoid; i++){
				TransferPresos preso = leerPreso(i);
				if(preso.getActivo()){
					presos.addElement(preso);
				}
			}
			entrada.close();
			return presos;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	@Override
	public int crearPreso(TransferPresos preso) {
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			preso.setId(ultimoid+1);
			entrada.close();
			FileWriter salida = new FileWriter(fileID);
			salida.write("" + (ultimoid+1));
			salida.close();
			return this.actualizarPreso(preso);
		} catch (IOException e) {
			return -1;
		}
	}
	
	public int actualizarPreso(TransferPresos preso) {
		try {
			String arch = generarNombreArchivo(preso.getId());
			FileWriter salida = new FileWriter(arch);
			boolean activo = preso.getActivo();
			if (activo)
				salida.write(TRUE + "\n");
			else
				salida.write(FALSE + "\n");
			salida.write(preso.getNombre() + "\n");
			salida.write(preso.getApellido() +"\n");
			salida.write(preso.getDni() +"\n");
			salida.write(preso.getCelda() + "\n");
			salida.write(preso.getCondena() + "\n");
		
			Vector<Integer> visitas = preso.getVisitas();
			salida.write(visitas.size() + "\n");
			for (int i=0; i < visitas.size(); i++)
				salida.write("" + visitas.elementAt(i) +" ");
			
			salida.write("\n");
			Vector<Integer> actividades = preso.getActividades();
			salida.write(actividades.size() + "\n");
			for(int i = 0; i < actividades.size(); i++){
				salida.write("" + actividades.elementAt(i) + " ");
			}
			
			salida.close();
			return preso.getId();
		} catch (IOException e) {
			return -1;
		}
	}

	public TransferPresos buscarPorDNI(String dni) {
		int i = 1; 
		boolean esta = false;
		TransferPresos preso = null;
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			
			while(!esta && i <= ultimoid){
				preso = leerPreso(i);
				i++;
				if(preso.getDni().equals(dni))
					esta = true;
			}
			entrada.close();
			if(esta) return preso;
			return null;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	private static String generarNombreArchivo(int id) {
		return "preso" + id + ".txt";
	}
	
	private static final int TRUE = 1;
	private static final int FALSE = 0;
	private final String fileID = "ultimoIdPreso.txt";
}