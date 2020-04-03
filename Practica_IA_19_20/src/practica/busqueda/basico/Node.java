package practica.busqueda.basico;

import java.util.ArrayList;

import practica.objetos.Area;
import practica.objetos.Herramienta;
import practica.objetos.Tarea;
import practica.objetos.Trabajador;

/**
 * Clase creada como base para la parte 2 de la práctica 2019-2020 de Inteligencia Artificial, UC3M, Colmenarejo
 *
 * @author Daniel Amigo Herrero
 * @author David Sánchez Pedroche
 * 
 */

public class Node {
	private double cost;							// Valor del coste de llegada al nodo
	private double heuristic;						// Valor de la heurística del nodo
	private double evaluation;						// Valor de la evaluación
	private Node parent;							// Nodo padre del arbol A*
	private Node nextNodeList = null;				// Para la gestión de la lista
	ArrayList<Herramienta> herramientas;
	ArrayList<Trabajador>  trabajadores;
	ArrayList<Tarea>       tareas;
	// Añadir más variables si se desea
	//boolean tareasHechas [][];//matriz de booleanos que representa las tareas hechas como en el enunciado de la practica areas/tipoTareas
	ArrayList<Area> areas;
	//Area areaActual;
	
	/**
	 * MODIFICAR
	 * Constructor para introducir un nuevo nodo en el algoritmo A estrella
	 */
	public Node(Node parentNode, ArrayList<Herramienta> herramientas, ArrayList<Trabajador> trabajadores, ArrayList<Tarea> tareas, ArrayList<Area> areas) {
		this.parent       = parentNode;  // padre en el árbol A*
		this.herramientas = herramientas;
		this.trabajadores = trabajadores;
		this.tareas       = tareas;
		// Añadir más variables si se desea
		this.areas=areas;
/*		this.tareasHechas= new boolean [this.areas.size()][3];
		for (int i = 0; i < areas.size(); i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < tareas.size(); k++) {
					if (j==0&&tareas.get(k).getTipo().equals("podar")&&tareas.get(k).getArea().equals(areas.get(i).getNombre())) {
						if(tareas.get(k).getUnidades()>0)tareasHechas[i][j]=false;
						else tareasHechas[i][j]=true;
					}
					if (j==1&&tareas.get(k).getTipo().equals("limpiar")&&tareas.get(k).getArea().equals(areas.get(i).getNombre())) {
						if(tareas.get(k).getUnidades()>0)tareasHechas[i][j]=false;
						else tareasHechas[i][j]=true;
					}
					if (j==2&&tareas.get(k).getTipo().equals("reparar")&&tareas.get(k).getArea().equals(areas.get(i).getNombre())) {
						if(tareas.get(k).getUnidades()>0)tareasHechas[i][j]=false;
						else tareasHechas[i][j]=true;
					}
				}
			}
		}*/
	}

	/**
	 * MODIFICAR
	 * Constructor auxiliar para la implementación del algoritmo. Genera una copia de un nodo para introducirla en la OpenList
	 */ 
	public Node(Node original) {
		// Incluir todas las variables del nodo
		this.cost        = original.cost;
		this.heuristic   = original.heuristic;
		this.evaluation   = original.evaluation;
		this.parent       = original.parent;
		this.nextNodeList = original.nextNodeList;
		// Añadir más variables si se desea

		// Se copian los objetos de los ArrayList a uno nuevo de este Nodo
		// Si se necesita añadir valores variables, como un ID, utilizar setters
		ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
		for (int i = 0; i < original.trabajadores.size(); i++) {
			Trabajador trabajador = new Trabajador(original.trabajadores.get(i).getNombre(), original.trabajadores.get(i).getHabPodar(), 
					original.trabajadores.get(i).getHabLimpiar(), original.trabajadores.get(i).getHabReparar(), original.trabajadores.get(i).getArea(), 
					original.trabajadores.get(i).getHerramienta(), original.trabajadores.get(i).getTiempo());
			//añadir los demas atributos
			trabajadores.add(trabajador);
		}
		this.trabajadores = trabajadores;
		ArrayList<Herramienta> herramientas = new ArrayList<Herramienta>();
		for (int i = 0; i < original.herramientas.size(); i++) {
			Herramienta herramienta = new Herramienta(original.herramientas.get(i).getNombre(), original.herramientas.get(i).getTrabajo(), original.herramientas.get(i).getPeso(), original.herramientas.get(i).getMejora(), original.herramientas.get(i).getCantidad());
			herramientas.add(herramienta);
		}
		this.herramientas = herramientas;
		ArrayList<Tarea> tareas = new ArrayList<Tarea>();
		for (int i = 0; i < original.tareas.size(); i++) {
			Tarea tarea = new Tarea(original.tareas.get(i).getTipo(), original.tareas.get(i).getArea(), original.tareas.get(i).getUnidades());
			tareas.add(tarea);
		}
		this.tareas = tareas;
		ArrayList<Area> areas = new ArrayList<Area>();
		for (int i = 0; i < original.areas.size(); i++) {
			Area area = new Area(original.areas.get(i).getNombre(),original.areas.get(i).getAdyacentes());
			areas.add(area);
			
		}
		this.areas = areas;
		/*for (int i = 0; i < areas.size(); i++) {
			for (int j = 0; j < 3; j++) {
				this.tareasHechas[i][j]=true;
			}
		}*/
		
	}
	public Node(Node original, Node parent) {
		// Incluir todas las variables del nodo
		this.cost        = original.cost;
		this.heuristic   = original.heuristic;
		this.evaluation   = original.evaluation;
		this.parent       = parent;
		// Añadir más variables si se desea

		// Se copian los objetos de los ArrayList a uno nuevo de este Nodo
		// Si se necesita añadir valores variables, como un ID, utilizar setters
		ArrayList<Trabajador> trabajadores = new ArrayList<Trabajador>();
		for (int i = 0; i < original.trabajadores.size(); i++) {
			Trabajador trabajador = new Trabajador(original.trabajadores.get(i).getNombre(), original.trabajadores.get(i).getHabPodar(), 
					original.trabajadores.get(i).getHabLimpiar(), original.trabajadores.get(i).getHabReparar(), original.trabajadores.get(i).getArea(), 
					original.trabajadores.get(i).getHerramienta(), original.trabajadores.get(i).getTiempo());
			//añadir los demas atributos
			trabajadores.add(trabajador);
		}
		this.trabajadores = trabajadores;
		ArrayList<Herramienta> herramientas = new ArrayList<Herramienta>();
		for (int i = 0; i < original.herramientas.size(); i++) {
			Herramienta herramienta = new Herramienta(original.herramientas.get(i).getNombre(), original.herramientas.get(i).getTrabajo(), 
					original.herramientas.get(i).getPeso(), original.herramientas.get(i).getMejora(), original.herramientas.get(i).getCantidad());
			herramientas.add(herramienta);
		}
		this.herramientas = herramientas;
		ArrayList<Tarea> tareas = new ArrayList<Tarea>();
		for (int i = 0; i < original.tareas.size(); i++) {
			Tarea tarea = new Tarea(original.tareas.get(i).getTipo(), original.tareas.get(i).getArea(), original.tareas.get(i).getUnidades());
			tareas.add(tarea);
		}
		this.tareas = tareas;
		ArrayList<Area> areas = new ArrayList<Area>();
		for (int i = 0; i < original.areas.size(); i++) {
			Area area = new Area(original.areas.get(i).getNombre(),original.areas.get(i).getAdyacentes());
			areas.add(area);
			
		}
		this.areas = areas;
		/*for (int i = 0; i < areas.size(); i++) {
			for (int j = 0; j < 3; j++) {
				this.tareasHechas[i][j]=true;
			}
		}*/
		
	}

	/**
	 * Constructor auxiliar para generar el primer nodo de la lista abierta
	 */ 
	public Node() {	}

	/**
	 *  Calcula el valor de la heuristica del problema para el nodo 
	 *  MODIFICAR
	 * @param finalNode - El nodo sobre el que calcular la heurística
	 * this.heuristica  - Resultado
	 */
	public void computeHeuristic(Node finalNode) {
		// MODIFICAR para ajustarse a las necesidades del problema
		//diferencias con el nodo final
		int aux=0;
		for (int i = 0; i < this.tareas.size(); i++) {
			if(this.tareas.get(i).getUnidades()>0)aux++;
		}
		/*for (int i = 0; i < this.herramientas.size(); i++) {
			if(!this.herramientas.get(i).getArea().equals("A"))aux++;
		}*/
		this.heuristic = aux;
	}

	/**
	 * Comprobación de que la información de un nodo es equivalente a la de otro nodo
	 * Solo comparar la información necesaria para ver si es el mismo estado del problema
	 * 
	 * @param other - el nodo con el que comparar this
	 * @return true: son iguales. false: no lo son
	 */
	public boolean equals(Node other) {
		boolean check=true;  
		
		for (int i = 0; i < this.tareas.size(); i++) {
			if(this.tareas.get(i).getUnidades()!=other.tareas.get(i).getUnidades())check=false; //si no les quedan las mismas unidades de cada tarea
		}
		for (int i = 0; i < this.trabajadores.size(); i++) {
			
			if(other.trabajadores.get(i).getHerramienta()!=null&&this.trabajadores.get(i).getHerramienta()!=null) {//si sus trabajadores tienen una herramienta
					
					if(!this.trabajadores.get(i).getHerramienta().getNombre().equals(other.trabajadores.get(i).getHerramienta().getNombre())//si su herramienta es diferente
					||
					!this.trabajadores.get(i).getArea().equals(other.trabajadores.get(i).getArea())//si sus trabajadores estan en diferentes areas
					||
					this.trabajadores.get(i).getTiempoHoras()!=other.trabajadores.get(i).getTiempoHoras()//si el tiempo de trabajo de sus trabajadores es diferente
					//SI HAN VISITADO LAS MISMAS AREAS
					)check=false;
			}
		}
		
		return check;
	}
	public boolean equals(Node other,Node goal) {
		boolean check=true;  
		for (int i = 0; i < goal.tareas.size(); i++) {
			if(goal.tareas.get(i).getUnidades()!=other.tareas.get(i).getUnidades())check=false; 
		}
		for (int i = 0; i < this.trabajadores.size(); i++) {
			if(this.trabajadores.get(i).getArea()!=other.trabajadores.get(i).getArea())check=false; 
		}
		return check;
	}


	/**
	 * Impresión de la información del nodo
	 * @param printDebug. Permite seleccionar cuántos mensajes imprimir
	 */
	public void printNodeData(int printDebug) {
		imprime();
		System.out.println("mi coste es "+this.cost+" mi heurística es "+this.heuristic+" y mi estado es: ");
		for (int i = 0; i < this.tareas.size(); i++) {
					System.out.println(this.tareas.get(i).getUnidades());
		}
		System.out.println("*******");
		System.out.println();
	}

	/**
	 * Ejecuta la función de evaluacion del problema para el nodo. IMPORTANTE: ejecutar después el cálculo del coste y heurística
	 */
	public void computeEvaluation() {
		this.evaluation = this.cost + this.heuristic; 
	}

	/**** Getters y Setters ****/
	/**
	 * MODIFICAR si se considera necesario. No es imprescindible, solo si consideras que puede ayudar a tu implementación
	 */
	public double getEvaluation() {
		return evaluation;
	}
	public ArrayList<Herramienta> getHerramientas() {
		return herramientas;
	}
	public void setHerramientas(ArrayList<Herramienta> herramientas) {
		this.herramientas = herramientas;
	}
	public ArrayList<Trabajador> getTrabajadores() {
		return trabajadores;
	}
	public ArrayList<Area> getAreas() {
		return areas;
	}
	public void setTrabajadores(ArrayList<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}
	public ArrayList<Tarea> getTareas() {
		return tareas;
	}
	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = tareas;
	}
	public void setEvaluation(double evaluacion) {
		this.evaluation = evaluacion;
	}
	public double getCost() {
		return cost;
	}
	public void setCoste(double coste) {
		this.cost = coste;
	}
	public double getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(double heuristica) {
		this.heuristic = heuristica;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getNextNode() {
		return nextNodeList;
	}
	public void setNextNode(Node nextNode) {
		this.nextNodeList = nextNode;
	}
	
	//METODOS NUEVOS
	public ArrayList<String> getAdyacentes(String area) {
		for (int i = 0; i < this.areas.size(); i++) {
			if(areas.get(i).getNombre().equals(area)) {
				return areas.get(i).getAdyacentes();
			}
		}
		return null;
	}
	public int getUnidades(String area,String trabajo) {
		for (int i = 0; i < this.tareas.size(); i++) {
			if(tareas.get(i).getArea().equals(area)&&tareas.get(i).getTipo().equals(trabajo)) {
				return tareas.get(i).getUnidades();
			}
		}
		return 0;
	}
	public void setUnidades(String area,String trabajo, int unidades) {
		for (int i = 0; i < this.tareas.size(); i++) {
			if(this.tareas.get(i).getArea().equals(area)&&tareas.get(i).getTipo().equals(trabajo)) {
				int resta = this.tareas.get(i).getUnidades() - unidades;
				if(resta>0)this.tareas.get(i).setUnidades(resta);
				else this.tareas.get(i).setUnidades(0);
				break;
			}
		}
	}

	public void setHerramienta(int trabajador) {
		// TODO Auto-generated method stub
		Trabajador t =this.trabajadores.get(trabajador);
		Herramienta ht = t.getHerramienta();						//herramienta del trabajador
		for (int i = 0; i < this.herramientas.size(); i++) {				//recorremos las herramientas
			Herramienta h = this.herramientas.get(i);
			if(ht==null) {
				t.setHerramienta(h);
				h.setCantidad(h.getCantidad()-1);		
				//cambiamos la herramienta
				break;
			}
			else if(!h.getNombre().equals(ht.getNombre())&&h.getCantidad()>0&&this.hayTareas(h.getTrabajo())) {  //si es diferente de la que tenia, hay al menos 1 y quedan trabajos por hacer de ese tipo de herramienta
				ht.setCantidad(ht.getCantidad()+1);							//devolvemos la del trbajador
				t.setHerramienta(h);
				h.setCantidad(h.getCantidad()-1);		
				//cambiamos la herramienta
				break;
			}
		}
		
	}
	public boolean hayTareas(String trabajo) {
		for (int i = 0; i < this.tareas.size(); i++) {
			if(this.tareas.get(i).getTipo().equals(trabajo)&&this.tareas.get(i).getUnidades()>0) return true;
		}
		return false;
	}
	public void estadoFinal() {
		for (int i = 0; i < this.tareas.size(); i++) {
			this.tareas.get(i).setUnidades(0);
		}
		for (int i = 0; i < this.trabajadores.size(); i++) {
			this.trabajadores.get(i).setArea("A");
		}
	}

	public void imprime() {
		for (int i = 0; i < this.trabajadores.size(); i++) {
			Trabajador t = this.trabajadores.get(i);
			if(t.getNombre().equals("Antonio")&&t.getHerramienta()!=null) {
				System.out.println(" estoy en "+t.getArea()
				+" tengo "+t.getHerramienta().getNombre()
				+" me quedan por hacer "+this.getHeuristic()
				+ " llevo currando "+ t.getTiempoHoras()+ " horas");
			}
		}
	}
	
	public void moverTrabajador(int trabajador, String area) {
		Trabajador t =this.trabajadores.get(trabajador);
		t.setArea(area);
		t.setTiempo((int) (t.getTiempo()+5+
				t.getHerramienta().getPeso()));
	}
	
	public void hacerTarea(int trabajador, String area) {
		Trabajador t =this.trabajadores.get(trabajador);
		while(this.getUnidades(area, t.getHerramienta().getTrabajo())>0) {
			if(t.getHerramienta().getTrabajo().equals("limpiar"))this.setUnidades(area, t.getHerramienta().getTrabajo(),t.getHabLimpiar() + t.getHerramienta().getMejora() );
			else if(t.getHerramienta().getTrabajo().equals("podar"))this.setUnidades(area, t.getHerramienta().getTrabajo(),t.getHabPodar() + t.getHerramienta().getMejora());
			else this.setUnidades(area, t.getHerramienta().getTrabajo(),t.getHabReparar() + t.getHerramienta().getMejora());
			t.setTiempo(t.getTiempo()+60);
		}	
	}
	
	
}









