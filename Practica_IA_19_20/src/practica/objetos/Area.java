package practica.objetos;

import java.util.ArrayList;

/**
 * Clase creada como objeto base para la práctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David Sánchez Pedroche
 */

public class Area {

	// Variables del objeto Herramienta
	String nombre;
	ArrayList<String> adyacentes;
	// AÑADIR LAS VARIABLES NECESARIAS

	/**
	 * Constructor para el objeto
	 * NO MODIFICAR LA LLAMADA DEL CONSTRUCTOR
	 */
	public Area(String nombre, ArrayList<String> adyacentes) {
		this.nombre = nombre;
		this.adyacentes = adyacentes;
		
		// Añadir el estado inicial (estático) de las variables que se añadan
		// Si se necesita añadir valores variables, como un ID, utilizar setters
	}

	/**
	 * Métodos getters y setters
	 */
	// Añadir (si procede) métodos auxiliares, como getters o setters
	
	public ArrayList<String> getAdyacentes(){
		return adyacentes;
		
	};

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
