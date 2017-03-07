package presentacion.controlador;

import java.util.Vector;

import negocio.SAFactoria;
import negocio.actividades.TransferActividades;
import negocio.celdas.TransferCeldas;
import negocio.presos.TransferPresos;
import negocio.sectores.TransferSector;
import negocio.visitantes.TransferVisitantes;
import negocio.visitas.TransferVisitas;

public class ControladorImp extends Controlador {

	public void actualiza(int opcion, Object objeto) {
		switch (opcion) {
			//SECTORES
			case Controlador.CREAR_SECTOR:
				int crearS_ok = SAFactoria.getInstance().generarSASector().crearSector((TransferSector) objeto);
				if(crearS_ok >= 0)
					presentacion.sectores.JFCrear.getInstance().informar(OPERACION_CORRECTA, crearS_ok);
				else if(crearS_ok == -1)
					presentacion.sectores.JFCrear.getInstance().informar(ESCRITURA_INCORRECTA, crearS_ok);
				else if(crearS_ok == -2)
					presentacion.sectores.JFCrear.getInstance().informar(SECTOR_EXISTENTE, crearS_ok);
				else
					presentacion.sectores.JFCrear.getInstance().informar(DATOS_INCORRECTOS, crearS_ok);
				break;
			case Controlador.ELIMINAR_SECTOR:
				int eliminarS_ok = SAFactoria.getInstance().generarSASector().eliminarSector((int)objeto);
				if(eliminarS_ok >= 0)
					presentacion.sectores.JFEliminar.getInstance().informar(OPERACION_CORRECTA);
				else if(eliminarS_ok == -1)
					presentacion.sectores.JFEliminar.getInstance().informar(ESCRITURA_INCORRECTA);
				else if(eliminarS_ok == -2)
					presentacion.sectores.JFEliminar.getInstance().informar(SECTOR_NO_EXISTENTE);
				else
					presentacion.sectores.JFEliminar.getInstance().informar(SECTOR_CON_CELDAS);
				break;
			case Controlador.MOSTRAR_PARA_MODIFICAR_SECTOR:
				TransferSector sectorModif = SAFactoria.getInstance().generarSASector().mostrarUnoSector((int)objeto);
				if(sectorModif != null)
					presentacion.sectores.JFModificar.getInstance().informar(OPERACION_CORRECTA, sectorModif);
				else
					presentacion.sectores.JFModificar.getInstance().informar(SECTOR_NO_EXISTENTE, null);
				break;
			case Controlador.MODIFICAR_SECTOR:
				int modifS_ok = SAFactoria.getInstance().generarSASector().modificarSector((TransferSector)objeto);
				if(modifS_ok >= 0)
					presentacion.sectores.JFModificar2.getInstance().informar(OPERACION_CORRECTA);
				else if(modifS_ok == -1)
					presentacion.sectores.JFModificar2.getInstance().informar(ESCRITURA_INCORRECTA);
				else
					presentacion.sectores.JFModificar2.getInstance().informar(DATOS_INCORRECTOS);
				break;
			case Controlador.MOSTRAR_UN_SECTOR:
				TransferSector sector = SAFactoria.getInstance().generarSASector().mostrarUnoSector((int)objeto);
				if(sector != null)
					presentacion.sectores.JFMostrarUno.getInstance().informar(OPERACION_CORRECTA, sector);
				else
					presentacion.sectores.JFMostrarUno.getInstance().informar(SECTOR_NO_EXISTENTE, null);
				break;
			case Controlador.MOSTRAR_TODOS_SECTOR:
				Vector<TransferSector> sectores = SAFactoria.getInstance().generarSASector().mostrarTodosSector();
				if(sectores != null)
					presentacion.sectores.JFMostrarTodos.getInstance().informar(OPERACION_CORRECTA, sectores);
				else
					presentacion.sectores.JFMostrarTodos.getInstance().informar(SECTOR_NO_EXISTENTE, null);
				break;
				
			//CELDAS
			case Controlador.CREAR_CELDA:
				int crearC_ok = SAFactoria.getInstance().generarSACeldas().crearCelda((TransferCeldas) objeto);
				if(crearC_ok >= 0)
					presentacion.celdas.JFCrear.getInstance().informar(OPERACION_CORRECTA, crearC_ok);
				else if(crearC_ok == -1)
					presentacion.celdas.JFCrear.getInstance().informar(ESCRITURA_INCORRECTA, crearC_ok);
				else if(crearC_ok == -4)
					presentacion.celdas.JFCrear.getInstance().informar(CELDA_EXISTENTE, crearC_ok);
				else
					presentacion.celdas.JFCrear.getInstance().informar(DATOS_INCORRECTOS, crearC_ok);
				break;
			case Controlador.ELIMINAR_CELDA:
				int eliminarC_ok = SAFactoria.getInstance().generarSACeldas().eliminarCelda((int)objeto);
				if(eliminarC_ok >= 0)
					presentacion.celdas.JFEliminar.getInstance().informar(OPERACION_CORRECTA);
				else if(eliminarC_ok == -1)
					presentacion.celdas.JFEliminar.getInstance().informar(ESCRITURA_INCORRECTA);
				else if(eliminarC_ok == -4)
					presentacion.celdas.JFEliminar.getInstance().informar(CELDA_NO_EXISTENTE);
				else
					presentacion.celdas.JFEliminar.getInstance().informar(CELDA_CON_PRESOS);
				break;
			case Controlador.MOSTRAR_PARA_MODIFICAR_CELDA:
				TransferCeldas celdaModif = SAFactoria.getInstance().generarSACeldas().mostrarUnoCelda((int)objeto);
				if(celdaModif != null)
					presentacion.celdas.JFModificar.getInstance().informar(OPERACION_CORRECTA, celdaModif);
				else
					presentacion.celdas.JFModificar.getInstance().informar(CELDA_NO_EXISTENTE, null);
				break;
			case Controlador.MODIFICAR_CELDA:
				int modifC_ok = SAFactoria.getInstance().generarSACeldas().modificarCelda((TransferCeldas)objeto);
				if(modifC_ok >= 0)
					presentacion.celdas.JFModificar2.getInstance().informar(OPERACION_CORRECTA);
				else if (modifC_ok == -1)
					presentacion.celdas.JFModificar2.getInstance().informar(ESCRITURA_INCORRECTA);
				else
					presentacion.celdas.JFModificar2.getInstance().informar(DATOS_INCORRECTOS);
				break;
			case Controlador.MOSTRAR_UNA_CELDA:
				TransferCeldas celda = SAFactoria.getInstance().generarSACeldas().mostrarUnoCelda((int)objeto);
				if(celda != null)
					presentacion.celdas.JFMostrarUna.getInstance().informar(OPERACION_CORRECTA, celda);
				else
					presentacion.celdas.JFMostrarUna.getInstance().informar(CELDA_NO_EXISTENTE, null);
				break;
			case Controlador.MOSTRAR_TODAS_CELDAS:
				Vector<TransferCeldas> celdas = SAFactoria.getInstance().generarSACeldas().mostrarTodosCelda();
				if(celdas != null)
					presentacion.celdas.JFMostrarTodas.getInstance().informar(OPERACION_CORRECTA, celdas);
				else
					presentacion.celdas.JFMostrarTodas.getInstance().informar(CELDA_NO_EXISTENTE, null);
				break;
			case Controlador.MOVER_CELDA_SECTOR:
				int[] info = (int[])objeto;
				int moverC_ok = SAFactoria.getInstance().generarSACeldas().moverCeldaASector(info[0], info[1]);
				if(moverC_ok >= 0)
					presentacion.celdas.JFMoverASector.getInstance().informar(OPERACION_CORRECTA);
				else if(moverC_ok == -1)
					presentacion.celdas.JFMoverASector.getInstance().informar(ESCRITURA_INCORRECTA);
				else if(moverC_ok == -2)
					presentacion.celdas.JFMoverASector.getInstance().informar(SECTOR_NO_EXISTENTE);
				else
					presentacion.celdas.JFMoverASector.getInstance().informar(CELDA_NO_EXISTENTE);
				break;
				
			//PRESOS
			case Controlador.ALTA_PRESO: 
				int crearPN_ok = SAFactoria.getInstance().generarSAPresos().altaPresoNuevo((TransferPresos)objeto);
				if(crearPN_ok >= 0)
					presentacion.presos.JFAltaNuevo.getInstance().informar(OPERACION_CORRECTA, crearPN_ok);
				else if(crearPN_ok == -1)
					presentacion.presos.JFAltaNuevo.getInstance().informar(ESCRITURA_INCORRECTA, crearPN_ok);
				else if(crearPN_ok == -3)
					presentacion.presos.JFAltaNuevo.getInstance().informar(DATOS_INCORRECTOS, crearPN_ok);
				else if(crearPN_ok == -4)
					presentacion.presos.JFAltaNuevo.getInstance().informar(PRESO_REINCIDENTE, crearPN_ok);
				else if(crearPN_ok == -5)
					presentacion.presos.JFAltaNuevo.getInstance().informar(PRESO_EXISTENTE, crearPN_ok);
				else
					presentacion.presos.JFAltaNuevo.getInstance().informar(CELDA_LLENA, crearPN_ok);
				break;
			case Controlador.ALTA_REINCIDENTE:
				int crearPR_ok = SAFactoria.getInstance().generarSAPresos().altaPresoReincidente((TransferPresos)objeto);
				if(crearPR_ok >= 0)
					presentacion.presos.JFAltaReincidente2.getInstance().informar(OPERACION_CORRECTA);
				else if(crearPR_ok == -1)
					presentacion.presos.JFAltaReincidente2.getInstance().informar(ESCRITURA_INCORRECTA);
				else if(crearPR_ok == -2)
					presentacion.presos.JFAltaNuevo.getInstance().informar(CELDA_LLENA, crearPR_ok);
				else
					presentacion.presos.JFAltaReincidente2.getInstance().informar(DATOS_INCORRECTOS);
				break;
			case Controlador.BAJA_PRESO:
				int eliminarP_ok = SAFactoria.getInstance().generarSAPresos().bajaPreso((int)objeto);
				if(eliminarP_ok >= 0)
					presentacion.presos.JFBaja.getInstance().informar(OPERACION_CORRECTA);
				else if(eliminarP_ok == -1)
					presentacion.presos.JFBaja.getInstance().informar(ESCRITURA_INCORRECTA);
				else
					presentacion.presos.JFBaja.getInstance().informar(PRESO_NO_EXISTENTE);
				break;
			case Controlador.MOSTRAR_PARA_ALTA_REINCIDENTE:
				TransferPresos presoCrear = SAFactoria.getInstance().generarSAPresos().mostrarDatosPreso((int)objeto, false);
				if(presoCrear != null)
					presentacion.presos.JFAltaReincidente.getInstance().informar(OPERACION_CORRECTA, presoCrear);
				else
					presentacion.presos.JFAltaReincidente.getInstance().informar(PRESO_NO_EXISTENTE, null);
				break;
			case Controlador.MOSTRAR_PARA_MODIFICAR_PRESO:
				TransferPresos presoModif = SAFactoria.getInstance().generarSAPresos().mostrarDatosPreso((int)objeto, true);
				if(presoModif != null)
					presentacion.presos.JFModificar.getInstance().informar(OPERACION_CORRECTA, presoModif);
				else
					presentacion.presos.JFModificar.getInstance().informar(PRESO_NO_EXISTENTE, null);
				break;
			case Controlador.MODIFICAR_PRESO:
				int modifP_ok = SAFactoria.getInstance().generarSAPresos().modificarPreso((TransferPresos)objeto);
				if(modifP_ok >= 0)
					presentacion.presos.JFModificar2.getInstance().informar(OPERACION_CORRECTA);
				else if(modifP_ok == -1)
					presentacion.presos.JFModificar2.getInstance().informar(ESCRITURA_INCORRECTA);
				else
					presentacion.presos.JFModificar2.getInstance().informar(DATOS_INCORRECTOS);
				break;
			case Controlador.MOSTRAR_UNO_PRESO:
				TransferPresos presoMostrar = SAFactoria.getInstance().generarSAPresos().mostrarDatosPreso((int)objeto, true);
				if(presoMostrar != null)
					presentacion.presos.JFMostrarUno.getInstance().informar(OPERACION_CORRECTA, presoMostrar);
				else
					presentacion.presos.JFMostrarUno.getInstance().informar(PRESO_NO_EXISTENTE, null);
				break;
			case Controlador.MOSTRAR_TODOS_PRESO:
				Vector<TransferPresos> presos = SAFactoria.getInstance().generarSAPresos().mostrarDatosPresos();
				if(presos != null)
					presentacion.presos.JFMostrarTodos.getInstance().informar(OPERACION_CORRECTA, presos);
				else
					presentacion.presos.JFMostrarTodos.getInstance().informar(PRESO_NO_EXISTENTE, null);
				break;
			case Controlador.MOVER_PRESO_CELDA:
				int[] infoPC = (int[])objeto;
				int moverP_ok = SAFactoria.getInstance().generarSAPresos().moverPresoAcelda(infoPC[0], infoPC[1]);
				if(moverP_ok >= 0)
					presentacion.presos.JFMoverACelda.getInstance().informar(OPERACION_CORRECTA);
				else if(moverP_ok == -1)
					presentacion.presos.JFMoverACelda.getInstance().informar(ESCRITURA_INCORRECTA);
				else if(moverP_ok == -2)
					presentacion.presos.JFMoverACelda.getInstance().informar(CELDA_LLENA);
				else if(moverP_ok == -4)
					presentacion.presos.JFMoverACelda.getInstance().informar(CELDA_NO_EXISTENTE);
				else
					presentacion.presos.JFMoverACelda.getInstance().informar(PRESO_NO_EXISTENTE);
					
				break;
				
			//ACTIVIDADES
			case Controlador.CREAR_ACTIVIDAD:
				int crearA_ok = SAFactoria.getInstance().generarSAActividades().crearActividad((TransferActividades) objeto);
				if(crearA_ok >= 0)
					presentacion.actividades.JFCrear.getInstance().informar(OPERACION_CORRECTA, crearA_ok);
				else if(crearA_ok == -1)
					presentacion.actividades.JFCrear.getInstance().informar(ESCRITURA_INCORRECTA, crearA_ok);
				else if(crearA_ok == -2)
					presentacion.actividades.JFCrear.getInstance().informar(ACTIVIDAD_EXISTENTE, crearA_ok);
				else
					presentacion.actividades.JFCrear.getInstance().informar(DATOS_INCORRECTOS, crearA_ok);
				
				break;
			case Controlador.ELIMINAR_ACTIVIDAD:
				int eliminarA_ok = SAFactoria.getInstance().generarSAActividades().eliminarActividad((int)objeto);
				if(eliminarA_ok >= 0)
					presentacion.actividades.JFEliminar.getInstance().informar(OPERACION_CORRECTA);
				else if(eliminarA_ok == -1)
					presentacion.actividades.JFEliminar.getInstance().informar(ESCRITURA_INCORRECTA);
				else if(eliminarA_ok == -2)
					presentacion.actividades.JFEliminar.getInstance().informar(ACTIVIDAD_NO_EXISTENTE);
				else if(eliminarA_ok == -3)
					presentacion.actividades.JFEliminar.getInstance().informar(ACTIVIDAD_PASADA);
				else
					presentacion.actividades.JFEliminar.getInstance().informar(ACTIVIDAD_CON_PRESOS);
				break;
			case Controlador.MOSTRAR_TODAS_ACTIVIDADES:
				Vector<TransferActividades> acts = SAFactoria.getInstance().generarSAActividades().mostrarTodasActividades();
				if(acts != null)
					presentacion.actividades.JFMostrarTodos.getInstance().informar(OPERACION_CORRECTA, acts);
				else
					presentacion.actividades.JFMostrarTodos.getInstance().informar(ACTIVIDAD_NO_EXISTENTE, null);
				break;
			case Controlador.MOSTRAR_UNA_ACTIVIDAD:
				TransferActividades actividad = SAFactoria.getInstance().generarSAActividades().mostrarActividad((int)objeto);
				if(actividad != null)
					presentacion.actividades.JFMostrarUno.getInstance().informar(OPERACION_CORRECTA, actividad);
				else
					presentacion.actividades.JFMostrarUno.getInstance().informar(ACTIVIDAD_NO_EXISTENTE, null);
				break;
			case Controlador.ANYADIR_PRESO_A_ACTIVIDAD:
				int[] infoAct = (int[]) objeto;
				int anyadirA_ok = SAFactoria.getInstance().generarSAActividades().anyadirPresoActividad(infoAct[0], infoAct[1]);
				if(anyadirA_ok >= 0)
					presentacion.actividades.JFAnyadirPreso.getInstance().informar(OPERACION_CORRECTA);
				else if(anyadirA_ok == -1)
					presentacion.actividades.JFAnyadirPreso.getInstance().informar(ESCRITURA_INCORRECTA);
				else if(anyadirA_ok == -2)
					presentacion.actividades.JFAnyadirPreso.getInstance().informar(ACTIVIDAD_NO_EXISTENTE);
				else if(anyadirA_ok == -3)
					presentacion.actividades.JFAnyadirPreso.getInstance().informar(ACTIVIDAD_PASADA);
				else if(anyadirA_ok == -4)
					presentacion.actividades.JFAnyadirPreso.getInstance().informar(ACTIVIDAD_COMPLETA);
				else if(anyadirA_ok == -5)
					presentacion.actividades.JFAnyadirPreso.getInstance().informar(PRESO_NO_EXISTENTE);
				else if(anyadirA_ok == -6)
					presentacion.actividades.JFAnyadirPreso.getInstance().informar(COINCIDENCIA_HORARIO);
				else 
					presentacion.actividades.JFAnyadirPreso.getInstance().informar(PRESO_YA_ANYADIDO);
				break;
			case Controlador.ELIMINAR_PRESO_DE_ACTIVIDAD:
				int[] infoAct1 = (int[])objeto;
				int eliminarPA_ok = SAFactoria.getInstance().generarSAActividades().eliminarPresoActividad(infoAct1[0], infoAct1[1]);
				if(eliminarPA_ok == 0)
					presentacion.actividades.JFEliminarPreso.getInstance().informar(OPERACION_CORRECTA);
				else if(eliminarPA_ok == -1)
					presentacion.actividades.JFEliminarPreso.getInstance().informar(ESCRITURA_INCORRECTA);
				else if(eliminarPA_ok == -2)
					presentacion.actividades.JFEliminarPreso.getInstance().informar(ACTIVIDAD_NO_EXISTENTE);
				else if(eliminarPA_ok == -3)
					presentacion.actividades.JFEliminarPreso.getInstance().informar(ACTIVIDAD_PASADA);
				else if(eliminarPA_ok == -5)
					presentacion.actividades.JFEliminarPreso.getInstance().informar(PRESO_NO_EXISTENTE);
				else
					presentacion.actividades.JFEliminarPreso.getInstance().informar(PRESO_NO_PERTENECIENTE);
				break;
			case Controlador.MOSTRAR_ACTIVIDADES_DE_PRESO:
				Vector<TransferActividades> actividadesP = SAFactoria.getInstance().generarSAActividades().mostrarActividadesPreso((int)objeto);
				if(actividadesP != null && actividadesP.size() > 0)
					presentacion.actividades.JFMostrarDePreso.getInstance().informar(OPERACION_CORRECTA, actividadesP);
				else if(actividadesP != null && actividadesP.size() == 0)
					presentacion.actividades.JFMostrarDePreso.getInstance().informar(ACTIVIDAD_NO_EXISTENTE, null);
				else
					presentacion.actividades.JFMostrarDePreso.getInstance().informar(PRESO_NO_EXISTENTE, null);
				break;
				
			// VISITAS
			case Controlador.CREAR_VISITA:
				int crearV_ok = SAFactoria.getInstance().generarSAVisitas().crearVisita((TransferVisitas)objeto);
				if(crearV_ok >= 0)
					presentacion.visitas.JFCrear.getInstance().informar(OPERACION_CORRECTA, crearV_ok);
				else if(crearV_ok == -1)
					presentacion.visitas.JFCrear.getInstance().informar(ESCRITURA_INCORRECTA, crearV_ok);
				else if(crearV_ok == -2)
					presentacion.visitas.JFCrear.getInstance().informar(VISITA_EXISTENTE, crearV_ok);
				else
					presentacion.visitas.JFCrear.getInstance().informar(DATOS_INCORRECTOS, crearV_ok);
				break;
			case Controlador.ELIMINAR_VISITA:
				int eliminarV_ok = SAFactoria.getInstance().generarSAVisitas().eliminarVisita((int)objeto);
				if(eliminarV_ok >= 0)
					presentacion.visitas.JFEliminar.getInstance().informar(OPERACION_CORRECTA);
				else if(eliminarV_ok == -1)
					presentacion.visitas.JFEliminar.getInstance().informar(ESCRITURA_INCORRECTA);
				else if(eliminarV_ok == -2)
					presentacion.visitas.JFEliminar.getInstance().informar(VISITA_NO_EXISTENTE);
				else
					presentacion.visitas.JFEliminar.getInstance().informar(VISITA_PASADA);
				break;
			case Controlador.MOSTRAR_TODAS_VISITAS:
				Vector<TransferVisitas> visitas = SAFactoria.getInstance().generarSAVisitas().mostrarTodosVisita();
				if(visitas != null)
					presentacion.visitas.JFMostrarTodos.getInstance().informar(OPERACION_CORRECTA, visitas);
				else
					presentacion.visitas.JFMostrarTodos.getInstance().informar(VISITA_NO_EXISTENTE, null);
				break;
			case Controlador.MOSTRAR_UNA_VISITA:
				TransferVisitas visita = SAFactoria.getInstance().generarSAVisitas().mostrarUnoVisita((int)objeto);
				if(visita != null)
					presentacion.visitas.JFMostrarUno.getInstance().informar(OPERACION_CORRECTA, visita);
				else
					presentacion.visitas.JFMostrarUno.getInstance().informar(VISITA_NO_EXISTENTE, null);
				break;
			case Controlador.ANYADIR_VISITANTE_A_VISITA:
				int[] infoA = (int[])objeto;
				int anyadir_ok = SAFactoria.getInstance().generarSAVisitas().anyadirVisitanteVisita(infoA[0], infoA[1]);
				if(anyadir_ok == 0)
					presentacion.visitas.JFAnyadirVisitante.getInstance().informar(OPERACION_CORRECTA);
				else if(anyadir_ok == -1)
					presentacion.visitas.JFAnyadirVisitante.getInstance().informar(ESCRITURA_INCORRECTA);
				else if(anyadir_ok == -2)
					presentacion.visitas.JFAnyadirVisitante.getInstance().informar(VISITA_NO_EXISTENTE);
				else if(anyadir_ok == -3)
					presentacion.visitas.JFAnyadirVisitante.getInstance().informar(VISITA_PASADA);
				else if(anyadir_ok == -5)
					presentacion.visitas.JFAnyadirVisitante.getInstance().informar(VISITANTE_NO_EXISTENTE);
				else if(anyadir_ok == -7)
					presentacion.visitas.JFAnyadirVisitante.getInstance().informar(VISITANTE_YA_ANYADIDO);
				else
					presentacion.visitas.JFAnyadirVisitante.getInstance().informar(COINCIDENCIA_HORARIO);
				break;
			case Controlador.ELIMINAR_VISITANTE_DE_VISITA:
				int[] infoE = (int[])objeto;
				int eliminar_ok = SAFactoria.getInstance().generarSAVisitas().eliminarVisitanteVisita(infoE[0], infoE[1]);
				if(eliminar_ok == 0)
					presentacion.visitas.JFEliminarVisitante.getInstance().informar(OPERACION_CORRECTA);
				else if(eliminar_ok == -1)
					presentacion.visitas.JFEliminarVisitante.getInstance().informar(ESCRITURA_INCORRECTA);
				else if(eliminar_ok == -2)
					presentacion.visitas.JFEliminarVisitante.getInstance().informar(VISITA_NO_EXISTENTE);
				else if(eliminar_ok == -3)
					presentacion.visitas.JFEliminarVisitante.getInstance().informar(VISITA_PASADA);
				else if(eliminar_ok == -4)
					presentacion.visitas.JFEliminarVisitante.getInstance().informar(VISITANTE_NO_PERTENECIENTE_VISITA);
				else if(eliminar_ok == -5)
					presentacion.visitas.JFEliminarVisitante.getInstance().informar(VISITANTE_NO_EXISTENTE);
				else
					presentacion.visitas.JFEliminarVisitante.getInstance().informar(VISITA_UN_VISITANTE);
				break;
			case Controlador.MOSTRAR_VISITAS_DE_PRESO:
				Vector<TransferVisitas> visitasP = SAFactoria.getInstance().generarSAVisitas().mostrarVisitasPreso((int)objeto);
				if(visitasP != null && visitasP.size()>0)
					presentacion.visitas.JFMostrarDePreso.getInstance().informar(OPERACION_CORRECTA, visitasP);
				else if(visitasP != null && visitasP.size() == 0)
					presentacion.visitas.JFMostrarDePreso.getInstance().informar(VISITA_NO_EXISTENTE, null);
				else
					presentacion.visitas.JFMostrarDePreso.getInstance().informar(PRESO_NO_EXISTENTE, null);
				break;
			case Controlador.MOSTRAR_VISITAS_DE_VISITANTE:
				Vector<TransferVisitas> visitasV = SAFactoria.getInstance().generarSAVisitas().mostrarVisitasVisitante((int)objeto);
				if(visitasV != null && visitasV.size() > 0)
					presentacion.visitas.JFMostrarDeVisitante.getInstance().informar(OPERACION_CORRECTA, visitasV);
				else if(visitasV != null && visitasV.size() == 0)
					presentacion.visitas.JFMostrarDeVisitante.getInstance().informar(VISITA_NO_EXISTENTE, null);
				else
					presentacion.visitas.JFMostrarDeVisitante.getInstance().informar(VISITANTE_NO_EXISTENTE, null);
				break;
				
			//VISITANTES
			case Controlador.ALTA_VISITANTE:
				int crearVi_ok = SAFactoria.getInstance().generarSAVisitantes().crearVisitante((TransferVisitantes)objeto);
				if(crearVi_ok >= 0)
					presentacion.visitantes.JFAlta.getInstance().informar(OPERACION_CORRECTA, crearVi_ok);
				else if(crearVi_ok == -1)
					presentacion.visitantes.JFAlta.getInstance().informar(ESCRITURA_INCORRECTA, crearVi_ok);
				else if(crearVi_ok == -5)
					presentacion.visitantes.JFAlta.getInstance().informar(VISITANTE_EXISTENTE, crearVi_ok);
				else
					presentacion.visitantes.JFAlta.getInstance().informar(DATOS_INCORRECTOS, crearVi_ok);
				break;
			case Controlador.BAJA_VISITANTE:
				int elimV_ok = SAFactoria.getInstance().generarSAVisitantes().eliminarVisitante((int)objeto);
				if(elimV_ok >= 0)
					presentacion.visitantes.JFBaja.getInstance().informar(OPERACION_CORRECTA);
				else if(elimV_ok == -1)
					presentacion.visitantes.JFBaja.getInstance().informar(ESCRITURA_INCORRECTA);
				else if(elimV_ok == -5)
					presentacion.visitantes.JFBaja.getInstance().informar(VISITANTE_NO_EXISTENTE);
				else
					presentacion.visitantes.JFBaja.getInstance().informar(VISITAS_PENDIENTES);
				break;
			case Controlador.MOSTRAR_PARA_MODIFICAR_VISITANTE:
				TransferVisitantes visModif =  SAFactoria.getInstance().generarSAVisitantes().mostrarUnoVisitante((int)objeto);
				if(visModif != null)
					presentacion.visitantes.JFModificar.getInstance().informar(OPERACION_CORRECTA, visModif);
				else
					presentacion.visitantes.JFModificar.getInstance().informar(VISITANTE_NO_EXISTENTE, null);
				break;
			case Controlador.MODIFICAR_VISITANTE:
				int modifV_ok = SAFactoria.getInstance().generarSAVisitantes().modificarVisitante((TransferVisitantes)objeto);
				if(modifV_ok >= 0)
					presentacion.visitantes.JFModificar2.getInstance().informar(OPERACION_CORRECTA);
				else if(modifV_ok == -1)
					presentacion.visitantes.JFModificar2.getInstance().informar(ESCRITURA_INCORRECTA);
				else
					presentacion.visitantes.JFModificar2.getInstance().informar(DATOS_INCORRECTOS);
				break;
			case Controlador.MOSTRAR_UNO_VISITANTE:
				TransferVisitantes visitante = SAFactoria.getInstance().generarSAVisitantes().mostrarUnoVisitante((int)objeto);
				if(visitante != null)
					presentacion.visitantes.JFMostrarUno.getInstance().informar(OPERACION_CORRECTA, visitante);
				else
					presentacion.visitantes.JFMostrarUno.getInstance().informar(VISITANTE_NO_EXISTENTE, null);
				break;
			case Controlador.MOSTRAR_TODOS_VISITANTE:
				Vector<TransferVisitantes> visitantes = SAFactoria.getInstance().generarSAVisitantes().mostrarTodosVisitantes();
				if(visitantes != null)
					presentacion.visitantes.JFMostrarTodos.getInstance().informar(OPERACION_CORRECTA, visitantes);
				else
					presentacion.visitantes.JFMostrarTodos.getInstance().informar(VISITANTE_NO_EXISTENTE, null);
				break;
		}
	}
}