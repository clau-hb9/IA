package practica.inferencia.avanzado;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import jeops.engine.KnowledgeBase;
import practica.json.LectorJSON;
import practica.objetos.Area;
import practica.objetos.Herramienta;
import practica.objetos.Tarea;
import practica.objetos.Trabajador;

/**
 * Clase creada como base para la parte 1 de la práctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David Sánchez Pedroche
 */

public class MainClass {

	public static void main(java.lang.String[] args) throws IOException {
	
		/**
		 * No se permite modificar el código desde aquí. Salvo el valor de printDebug o problemPath
		 */
		
		System.out.println("--------------------------------------------------------");
		System.out.println("********** PRACTICA IA 19-20 UC3M COLMENAREJO **********");
		System.out.println("************ SOLUCION INFERENCIA - AVANZADO ************");
		System.out.println("--------------------------------------------------------");
        
		// Se define el nivel de debug a utilizar: Por argumentos el segundo parámetro
		int printDebug; // Nivel de debug. Permite elegir la cantidad de mensajes a imprimir
		if (args.length > 1) printDebug =  Integer.parseInt(args[1]);
		else printDebug = 1; // Definir aquí el valor
				
		//----------------------------- Se carga el problema -----------------------------//
		String problemPath = "problema.json"; // Problema en la misma ruta del paquete
		InputStream isJSON;
		// Si hay argumentos, se busca un fichero por parámetro. NO MODIFICAR
		if (args.length > 0 && !args[0].equals("")) isJSON = new FileInputStream(args[0]);
		else isJSON = LectorJSON.class.getResourceAsStream(problemPath); // Se busca en el path de LectorJSON dicho fichero
		LectorJSON lectorJSON = new LectorJSON();
		lectorJSON.readJSON(isJSON);
		ArrayList<Herramienta> readedHerramientas = lectorJSON.getHerramientas();
		ArrayList<Trabajador>  readedTrabajadores = lectorJSON.getTrabajadores();
		ArrayList<Tarea>       readedTareas       = lectorJSON.getTareas();

		/**
		 * No se permite modificar el código hasta aquí. Salvo el valor de printDebug o problemPath
		 */
		
		//----------------------------- Se preparan los objetos a utilizar en esta solución básica -----------------------------//
		// Se pueden añadir variables extra iterando sobre cada array y añadiendo un set en cada objeto		
		// Herramientas
		ArrayList<Herramienta> herramientas = readedHerramientas;
		// Trabajadores
		ArrayList<Trabajador>  trabajadores = readedTrabajadores;
		// Tareas
		ArrayList<Tarea> tareas = readedTareas;
		
		ArrayList<String> adyA= new ArrayList<String>();
		adyA.add("J2");
		adyA.add("J3");
		adyA.add("C2");
		Area A = new Area("A",adyA);
		
		ArrayList<String> adyB= new ArrayList<String>();
		adyB.add("U");
		adyB.add("J1");
		Area B = new Area("B",adyB);
		
		ArrayList<String> adyJ1= new ArrayList<String>();
		adyJ1.add("U");
		adyJ1.add("B");
		adyJ1.add("C1");
		adyJ1.add("J2");
		Area J1 = new Area("J1",adyJ1);
		
		ArrayList<String> adyJ2= new ArrayList<String>();
		adyJ2.add("U");
		adyJ2.add("A");
		adyJ2.add("C1");
		adyJ2.add("C2");
		adyJ2.add("J1");
		Area J2 = new Area("J2",adyJ2);
		
		ArrayList<String> adyJ3= new ArrayList<String>();
		adyJ3.add("R");
		adyJ3.add("A");
		adyJ3.add("C2");
		Area J3 = new Area("J3",adyJ3);
		
		ArrayList<String> adyU= new ArrayList<String>();
		adyU.add("B");
		adyU.add("J1");
		adyU.add("J2");
		Area U = new Area("U",adyU);
		
		ArrayList<String> adyC1= new ArrayList<String>();
		adyC1.add("C2");
		adyC1.add("J1");
		adyC1.add("J2");
		Area C1 = new Area("C1",adyC1);
		
		ArrayList<String> adyC2= new ArrayList<String>();
		adyC2.add("C1");
		adyC2.add("A");
		adyC2.add("J2");
		adyC2.add("J3");
		Area C2 = new Area("C2",adyC2);
		
		ArrayList<String> adyR= new ArrayList<String>();
		adyR.add("J3");
		Area R = new Area("R",adyR);

		ArrayList<Area> areas = new ArrayList<Area>();
		areas.add(A);
		areas.add(B);
		areas.add(C1);
		areas.add(C2);
		areas.add(J1);
		areas.add(J2);
		areas.add(J3);
		areas.add(U);
		areas.add(R);
		
		/**
		 * No se permite modificar el código desde aquí
		 */
		
		//----------------------------- Se crean los inicializan los objetos para ejecutar la solución -----------------------------//
		// Generación del motor de inferencia. Se le introduce la dirección a las reglas y se indica un orden para las reglas (ordenadas por prioridad en el fichero. No se puede modificar)
		InputStream isRules = MainClass.class.getResourceAsStream("reglas.rules"); // Se busca en el path de MainClass dicho fichero
		KnowledgeBase kb = new KnowledgeBase(isRules, new jeops.engine.PriorityRuleSorter(), printDebug);
		
		/**
		 * No se permite modificar el código hasta aquí
		 */
		
		//---------------------- Introducir los objetos en la base de hechos para el problema básico ---------------------- //
		for (int i = 0; i < herramientas.size(); i++) kb.join(herramientas.get(i));
		for (int i = 0; i < trabajadores.size(); i++) kb.join(trabajadores.get(i));
		for (int i = 0; i < tareas.size(); i++) kb.join(tareas.get(i));
		for (int i = 0; i < areas.size(); i++) kb.join(areas.get(i));

		// Impresión del estado final del problema		
		System.out.println("--------------------------------------------------------");
		System.out.println("****************** COMIENZO EJECUCION ******************");
		System.out.println("--------------------------------------------------------");
		printState(herramientas, trabajadores, tareas);

		// Ejecución del motor de inferencia con el problema
		double executionTime = kb.run();

		// Impresión del estado final del problema		
		System.out.println("--------------------------------------------------------");
		System.out.println("******************** FIN EJECUCION *********************");
		System.out.println("--------------------------------------------------------");
		printState(herramientas, trabajadores, tareas);
		
		// Impresión de las métricas definidas
		System.out.println("------------------------ METRICAS -----------------------");
		printMetrics(executionTime, herramientas, trabajadores, tareas);
	}
	
	/**
	 * Se imprime el estado del problema en el instante actual
	 * MODIFICAR
	 * @param herramientas
	 * @param trabajadores
	 * @param tareas
	 */
	public static void printState(ArrayList<Herramienta> herramientas, ArrayList<Trabajador> trabajadores, ArrayList<Tarea> tareas) {
		System.out.println("************** IMPRESION DEL ESTADO **************");
		for (int i = 0; i< tareas.size(); i++) {
			System.out.println(tareas.get(i).getTipo());
			System.out.println(tareas.get(i).getUnidades());
		}
		System.out.println("****HORAS TRABAJADAS");
		for (int i = 0; i< trabajadores.size(); i++) {
			System.out.println(trabajadores.get(i).getNombre());
			System.out.println((trabajadores.get(i).getTiempo()/60));
		}
		System.out.println("****LUGAR HERRAMIENTAS");
		for (int i = 0; i< trabajadores.size(); i++) {
			if(trabajadores.get(i).getHerramienta()!=null) {
			System.out.println(trabajadores.get(i).getHerramienta().getNombre());
			System.out.println(trabajadores.get(i).getArea());
			}
		}
		
	}

	/**
	 * Se generan las métricas implementadas y se imprimen sus resultados
	 * MODIFICAR
	 * @param herramientas
	 * @param trabajadores
	 * @param tareas
	 */
	public static void printMetrics(double executionTime, ArrayList<Herramienta> herramientas, ArrayList<Trabajador> trabajadores, ArrayList<Tarea> tareas) {
		System.out.println("************** IMPRESION DE METRICAS **************");
		System.out.println("La ejecución ha tardado: "+executionTime +" segundos");
	}

}
