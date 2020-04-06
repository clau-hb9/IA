package practica.objetos;

/**
 * Clase creada como objeto base para la práctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David Sánchez Pedroche
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
	double tExacto;
	// AÑADIR LAS VARIABLES NECESARIAS
	int [][] coste = new int [9][9];

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
		this.areaAnterior=null;
		this.herramienta = null;
		// Añadir el estado inicial (estático) de las variables que se añadan
		// Si se necesita añadir valores variables, como un ID, utilizar setters
	}
	public Trabajador(String nombre, int habPodar, int habLimpiar, int habReparar,String area, Herramienta h, double tiempo, String aAnterior) {
		this.nombre      = nombre;
		this.habPodar    = habPodar;
		this.habLimpiar  = habLimpiar;
		this.habReparar  = habReparar;
		this.area=area;
		this.areaAnterior=aAnterior;
		this.herramienta = h;
		this.tExacto=tiempo;
		// Añadir el estado inicial (estático) de las variables que se añadan
		// Si se necesita añadir valores variables, como un ID, utilizar setters
		this.setCostes();
	}
	
	/**
	 * Añadir (si procede) métodos auxiliares, como getters o setters
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
	public double getTiempoExacto() {
		return tExacto;
	}
	public int getTiempoHoras() {
		return (tiempo/60);
	}
	public double getTiempoHorasDecimales() {
		return (double)this.getTiempoExacto()/60;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public void setTiempoExacto(double tiempo) {
		this.tExacto = tiempo;
	}
	public void sumarTiempo() {
		if (this.getHerramienta()!=null) {
			this.setTiempo((int)(this.getTiempo()+5+this.getHerramienta().getPeso()));
		}
		else {
			this.setTiempo(this.getTiempo()+5);
		}
	}
	public void setCostes() {
		this.coste[0][0]=0;this.coste[0][1]=4;this.coste[0][2]=5;this.coste[0][3]=3;this.coste[0][4]=2;
		this.coste[0][5]=4;this.coste[0][6]=3;this.coste[0][7]=1;this.coste[0][8]=2;
		
		this.coste[1][1]=0;this.coste[1][2]=1;this.coste[1][3]=2;this.coste[1][4]=2;
		this.coste[1][5]=1;this.coste[1][6]=1;this.coste[1][7]=3;this.coste[1][8]=2;
		
		this.coste[2][2]=0;this.coste[2][3]=2;this.coste[2][4]=3;
		this.coste[2][5]=1;this.coste[2][6]=2;this.coste[2][7]=4;this.coste[2][8]=3;
		
		this.coste[3][3]=0;this.coste[3][4]=1;
		this.coste[3][5]=1;this.coste[3][6]=1;this.coste[3][7]=2;this.coste[3][8]=2;
		
		this.coste[4][4]=0;
		this.coste[4][5]=2;this.coste[4][6]=1;this.coste[4][7]=1;this.coste[4][8]=1;
		
		this.coste[5][5]=0;this.coste[5][6]=1;this.coste[5][7]=3;this.coste[5][8]=2;
		
		this.coste[6][6]=0;this.coste[6][7]=2;this.coste[2][8]=1;
		
		this.coste[7][7]=0;this.coste[7][8]=1;
		
		this.coste[8][8]=0;
		
		for (int i = 0; i < coste.length; i++) {
			for (int j = 0; j < coste.length; j++) {
				this.coste[j][i]=this.coste[j][i];
			}
		}
		
	}
	
	public int getIndex(String area) {
		if(area.equals("R"))return 0;
		if(area.equals("U"))return 1;
		if(area.equals("B"))return 2;
		if(area.equals("C1"))return 3;
		if(area.equals("C2"))return 4;
		if(area.equals("J1"))return 5;
		if(area.equals("J2"))return 6;
		if(area.equals("J3"))return 7;
		if(area.equals("A"))return 8;
		return -1;
	}
	public String getNombreArea(int i) {
		if(i==0)return "R";
		if(i==1)return "U";
		if(i==2)return "B";
		if(i==3)return "C1";
		if(i==4)return "C2";
		if(i==5)return "J1";
		if(i==6)return "J2";
		if(i==7)return "J3";
		if(i==8)return "A";
		return null;
	}
	public int getCoste(String a1,String a2) {
		int i=getIndex(a1);
		int j=getIndex(a2);
		return this.coste[i][j];
	}
}
