package practica.objetos;

import java.util.ArrayList;
public class Area {

	// Variables del objeto Herramienta
	String nombre;
	ArrayList<String> adyacentes;
	boolean[] casillaComprobada;
	static Area[] areas;

	
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
		this.casillaComprobada = new boolean[4];
		Area.areas = new Area[9];
		this.inicializar();
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
	
	//Inicializamos el mapa que nos ayudará a saber por que casillas ha pasado ya el trabajador en busca de tareas, evitando asi repetir.
	public void inicializar(){
		for (int i = 0; i < casillaComprobada.length; i++) {
				this.casillaComprobada[i]= false;
		}
	}
	
		
	
	public static Area[] getAreas() {
		ArrayList<String> adyA= new ArrayList<String>();
		adyA.add("J3");
		adyA.add("J2");
		adyA.add("C2");
		Area A = new Area("A",adyA);
		
		ArrayList<String> adyB= new ArrayList<String>();
		adyB.add("U");
		adyB.add("J1");
		Area B = new Area("B",adyB);
		
		ArrayList<String> adyJ1= new ArrayList<String>();
		adyJ1.add("U");
		adyJ1.add("C1");
		adyJ1.add("J2");
		adyJ1.add("B");

		Area J1 = new Area("J1",adyJ1);
		
		ArrayList<String> adyJ2= new ArrayList<String>();
		adyJ2.add("U");
		adyJ2.add("C1");
		adyJ2.add("C2");
		adyJ2.add("J1");
		adyJ2.add("A");

		Area J2 = new Area("J2",adyJ2);
		
		ArrayList<String> adyJ3= new ArrayList<String>();
		adyJ3.add("C2");
		adyJ3.add("R");
		adyJ3.add("A");
		Area J3 = new Area("J3",adyJ3);
		
		ArrayList<String> adyU= new ArrayList<String>();
		adyU.add("J2");
		adyU.add("J1");
		adyU.add("B");

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

		areas[0] = A;
		areas[1] = B;
		areas[2] = C1;
		areas[3] = C2;
		areas[4] = J1;
		areas[5] = J2;
		areas[6] = J3;
		areas[7] = U;
		areas[8] = R;
		
		return areas;
	}
	
	
}
