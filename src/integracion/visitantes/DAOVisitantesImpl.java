package integracion.visitantes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import negocio.visitantes.TransferVisitanteFamiliar;
import negocio.visitantes.TransferVisitanteProfesional;
import negocio.visitantes.TransferVisitantes;

public class DAOVisitantesImpl implements DAOVisitantes {
	
	@Override
	public TransferVisitantes leerVisitante(int id) {
		
		TransferVisitantes visitante;
		String arch = generarNombreArchivo(id);
		try {
			Scanner entrada = new Scanner(new FileReader(arch));
			int tipoVis = entrada.nextInt();
			if(tipoVis == 0)
				visitante = new TransferVisitanteFamiliar();
			else
				visitante = new TransferVisitanteProfesional();
			
			int activo = entrada.nextInt();
			if (activo == TRUE)
				visitante.setActivo(true);
			else
				visitante.setActivo(false);
			entrada.nextLine(); 
			visitante.setId(id);
			visitante.setNombre(entrada.nextLine());
			visitante.setApellido(entrada.nextLine());
			visitante.setDni(entrada.nextLine());
			if ( visitante instanceof TransferVisitanteProfesional ) {
				((TransferVisitanteProfesional) visitante).setEmail(entrada.nextLine());
			}
			else if ( visitante instanceof TransferVisitanteFamiliar ) {
				((TransferVisitanteFamiliar) visitante).setParentesco(entrada.nextLine());
			}
			
			Vector<Integer> visitas = new Vector<Integer>();
			while(entrada.hasNextInt())
				visitas.addElement(entrada.nextInt());
			visitante.setHistorialVisitas(visitas);
			
			entrada.close();
			
		} catch (FileNotFoundException e) {
			visitante = null;
		}
		return visitante;
	}

	@Override
	public Vector<TransferVisitantes> leerTodosVisitante() {
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			Vector<TransferVisitantes> visitantes = new Vector<TransferVisitantes>();
			
			for(int i = 1; i <= ultimoid; i++){
				TransferVisitantes visitante = leerVisitante(i);
				if(visitante.getActivo())
					visitantes.addElement(visitante);
			}
			entrada.close();
			return visitantes;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	@Override
	public int crearVisitante(TransferVisitantes visitante) {
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			visitante.setId(ultimoid+1);
			entrada.close();
			FileWriter salida = new FileWriter(fileID);
			salida.write("" + (ultimoid+1));
			salida.close();
			return this.actualizarVisitante(visitante);
		} catch (IOException e) {
			return -1;
		}
	}
	
	@Override
	public int actualizarVisitante(TransferVisitantes visitante) {
		String arch = generarNombreArchivo(visitante.getId());
		try {
			FileWriter salida = new FileWriter(arch);
			boolean activo = visitante.getActivo();
			
			if(visitante instanceof TransferVisitanteProfesional)
				salida.write(1 + "\n");
			else
				salida.write(0 + "\n");
			
			if (activo)
				salida.write(TRUE + "\n");
			else
				salida.write(FALSE + "\n");
			salida.write(visitante.getNombre() + "\n");
			salida.write(visitante.getApellido() +"\n");
			salida.write(visitante.getDni() +"\n");
			if( visitante instanceof TransferVisitanteProfesional ) 
				salida.write(((TransferVisitanteProfesional) visitante).getEmail() + "\n");
			else if ( visitante instanceof TransferVisitanteFamiliar )
				salida.write(((TransferVisitanteFamiliar) visitante).getParentesco() + "\n");
			
			Vector<Integer> visitas = visitante.getHistorialVisitas();
			for (int i=0; i< visitas.size(); i++ )
				salida.write("" + visitas.elementAt(i) +" ");
			
			salida.close();
			return visitante.getId();
		} catch (IOException e) {
			return -1;
		}
	}

	@Override
	public TransferVisitantes buscarPorDNI(String dni) {
		int i = 1; 
		boolean esta = false;
		TransferVisitantes visitante = null;
		Scanner entrada;
		try {
			entrada = new Scanner(new FileReader(fileID));
			int ultimoid = entrada.nextInt();
			
			while(!esta && i <= ultimoid){
				visitante = leerVisitante(i);
				i++;
				if(visitante.getDni().equals(dni))
					esta = true;
			}
			entrada.close();
			if(esta) return visitante;
			return null;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	private static String generarNombreArchivo(int id) {
		return "visitante" + id + ".txt";
	}
	
	private static final int TRUE = 1;
	private static final int FALSE = 0;
	private final String fileID = "ultimoIdVisitante.txt";
}
