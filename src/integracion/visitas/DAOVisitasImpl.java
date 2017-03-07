package integracion.visitas;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Vector;

import negocio.visitas.TransferVisitas;


public class DAOVisitasImpl implements DAOVisitas {
	
	private static String generarNombreArchivo(int id) {
		return "visita" + id + ".txt";
	}

	public TransferVisitas leerVisita(int id) {
		TransferVisitas visita = new TransferVisitas();
		String arch = generarNombreArchivo(id);
		try {
			Scanner entrada = new Scanner(new FileReader(arch));
			int activo = entrada.nextInt();
			if (activo == TRUE)
				visita.setActivo(true);
			else
				visita.setActivo(false);
			entrada.nextLine();
			visita.setId(id);
			Calendar fecha = new GregorianCalendar(entrada.nextInt(), entrada.nextInt(), entrada.nextInt(), entrada.nextInt(), entrada.nextInt());
			fecha.set(Calendar.SECOND, 0);
			fecha.set(Calendar.MILLISECOND, 0);
			visita.setFecha(fecha);
			visita.setPreso(entrada.nextInt());
			
			int numVisitantes = entrada.nextInt();
			visita.setNumVisitantes(numVisitantes);
			
			Vector<Integer> visitantes = new Vector<Integer>();
			for(int i = 0; i < numVisitantes; i++){
				visitantes.addElement(entrada.nextInt());
			}
			visita.setVisitantes(visitantes);
			
			entrada.close();
			
		} catch (FileNotFoundException e) {
			visita = null;
		}
		return visita;
		
	}
	
	public Vector<TransferVisitas> leerTodosVisita() {
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			Vector<TransferVisitas> visitas = new Vector<TransferVisitas>();
			
			for(int i = 1; i <= ultimoid; i++){
				TransferVisitas visita = leerVisita(i);
				if(visita.getActivo()){
					visitas.addElement(visita);
				}
			}
			entrada.close();
			
			return visitas;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	public int crearVisita (TransferVisitas visita){
		try{
			Scanner entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			visita.setId(ultimoid+1);
			entrada.close();
			FileWriter salida = new FileWriter(fileID);
			salida.write("" + (ultimoid+1));
			salida.close();
			
			return this.actualizarVisita(visita);
		}catch(IOException e){
			return -1;
		}		
	}
	
	public int actualizarVisita(TransferVisitas visita) {
		String arch = generarNombreArchivo(visita.getId());
		try {
			FileWriter salida = new FileWriter(arch);
			boolean activo = visita.getActivo();
			if (activo)
				salida.write(TRUE + "\n");
			else
				salida.write(FALSE + "\n");
			
			Calendar fecha = visita.getFecha();
			salida.write(fecha.get(Calendar.YEAR)+ " " + fecha.get(Calendar.MONTH) + " " + fecha.get(Calendar.DAY_OF_MONTH) + " " + fecha.get(Calendar.HOUR_OF_DAY) + " " + fecha.get(Calendar.MINUTE) + "\n");
			salida.write(visita.getPreso() + "\n");
			
			int numVisitantes = visita.getNumVisitantes();
			salida.write(numVisitantes + "\n");
			
			Vector<Integer> visitantes = visita.getVisitantes();
			for (int i=0; i< numVisitantes; i++)
				salida.write("" + visitantes.elementAt(i) +" ");
			
			salida.close();
			return visita.getId();
		} catch (IOException e) {
			return -1;
		}
	}

	public TransferVisitas buscarPorFechaYPreso(Calendar fecha, int idPreso){
		
		int i = 1; 
		boolean esta = false;
		TransferVisitas visita = null;
		Scanner entrada;
		try{
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			
			while(!esta && i <= ultimoid){
				visita = leerVisita(i);
				i++;
				if(visita.getPreso() == idPreso && visita.getFecha().compareTo(fecha) == 0)
					esta = true;
			}
			entrada.close();
			
			if (esta) return visita;
			return null;

		}catch(FileNotFoundException e){
			return null;
		}
	}
	
	private final int TRUE = 1;
	private final int FALSE = 0;
	private final String fileID = "ultimoIdVisita.txt";
}