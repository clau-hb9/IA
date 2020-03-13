package practica.objetos;

import java.util.ArrayList;
public class Area {

	// Variables del objeto Herramienta
	String nombre;
	ArrayList<String> adyacentes;
	Boolean visitadaTareaL;
	Boolean visitadaTareaR;
	Boolean visitadaTareaP;
	Boolean visitadaAlmacenA;
	Boolean visitadaAlmacenB;
	Boolean visitadaAlmacenC;
	Boolean visitadaAlmacenD;
	// AÑADIR LAS VARIABLES NECESARIAS

	public Area(String nombre, ArrayList<String> adyacentes) {
		this.nombre = nombre;
		this.adyacentes = adyacentes;
		this.visitadaTareaL=false;
		this.visitadaTareaR=false;
		this.visitadaTareaP=false;
		this.visitadaAlmacenA=false;
		this.visitadaAlmacenB=false;
		this.visitadaAlmacenC=false;
		this.visitadaAlmacenD=false;
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
	
	public boolean esAdyacente(String area) {
		return this.adyacentes.contains(area);
	}
	public boolean getVisitadaTarea(String tarea) {
		if(tarea.equals("podar"))return visitadaTareaP;
		else if(tarea.equals("reparar"))return visitadaTareaR;
		else return visitadaTareaL;
	}
	public void setVisitadaTarea(boolean visitada, String tarea) {
		if(tarea.equals("podar"))this.visitadaTareaP = visitada;
		else if(tarea.equals("reparar"))this.visitadaTareaR = visitada;
		else this.visitadaTareaL = visitada;
	}

	public boolean getVisitadaAlmacen(String nombre) {
		if(nombre.equals("Antonio"))return visitadaAlmacenA;
		else if(nombre.equals("Bernardo"))return visitadaAlmacenB;
		else if(nombre.equals("Cristian"))return visitadaAlmacenC;
		else return visitadaAlmacenD;
	}
	public void setVisitadaAlmacen(boolean visitada, String nombre) {
		if(nombre.equals("Antonio")) this.visitadaAlmacenA=visitada;
		else if(nombre.equals("Bernardo"))this.visitadaAlmacenB=visitada;
		else if(nombre.equals("Cristian"))this.visitadaAlmacenC=visitada;
		else this.visitadaAlmacenD=visitada;
	}
}
