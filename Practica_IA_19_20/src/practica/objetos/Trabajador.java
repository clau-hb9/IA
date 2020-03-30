package practica.objetos;

/**
 * Clase creada como objeto base para la pr�ctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David S�nchez Pedroche
 */

public class Trabajador {

	// Variables del objeto Trabajador
	String nombre;
	int habPodar;
	int habLimpiar;
	int habReparar;
	String area;
	String areaAnterior;
	Herramienta herramienta;
	int tiempo;
	// A�ADIR LAS VARIABLES NECESARIAS

	/**
	 * Constructor para el objeto
	 * NO MODIFICAR LA LLAMADA DEL CONSTRUCTOR
	 */
	public Trabajador(String nombre, int habPodar, int habLimpiar, int habReparar) {
		this.nombre      = nombre;
		this.habPodar    = habPodar;
		this.habLimpiar  = habLimpiar;
		this.habReparar  = habReparar;
		this.area="A";
		this.areaAnterior="";
		this.herramienta = null;
		// A�adir el estado inicial (est�tico) de las variables que se a�adan
		// Si se necesita a�adir valores variables, como un ID, utilizar setters
	}
	
	/**
	 * A�adir (si procede) m�todos auxiliares, como getters o setters
	 */
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getHabPodar() {
		return habPodar;
	}
	public void setHabPodar(int habPodar) {
		this.habPodar = habPodar;
	}
	public int getHabLimpiar() {
		return habLimpiar;
	}
	public void setHabLimpiar(int habLimpiar) {
		this.habLimpiar = habLimpiar;
	}
	public int getHabReparar() {
		return habReparar;
	}
	public void setHabReparar(int habReparar) {
		this.habReparar = habReparar;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getAreaAnterior() {
		return areaAnterior;
	}
	public void setAreaAnterior(String area) {
		this.areaAnterior = area;
	}
	
	public void imprime(String tarea) {
		System.out.println("Soy : "+this.getNombre()+" y Me muevo de: "+ this.getAreaAnterior()+" a: "+ this.getArea()
		+ " y voy a : "+ tarea);
	}
	public void imprimeH() {
		System.out.println("Soy : "+this.getNombre()+ " y He cogido : "+ this.getHerramienta().getNombre() + " y quedan "
				+this.getHerramienta().getCantidad());
	}
	
	public Herramienta getHerramienta() {
		return herramienta;
	}
	public void setHerramienta(Herramienta herramienta) {
		this.herramienta = herramienta;
	}
	public int getTiempo() {
		return tiempo;
	}
	public int getTiempoHoras() {
		return (tiempo/60);
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public void sumarTiempo() {
		if (this.getHerramienta()!=null) {
			this.setTiempo((int)(this.getTiempo()+5+this.getHerramienta().getPeso()));
		}
		else {
			this.setTiempo(this.getTiempo()+5);
		}
	}
}
