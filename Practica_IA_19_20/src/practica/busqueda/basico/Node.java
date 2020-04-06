package practica.busqueda.basico;

import java.util.ArrayList;

import practica.busqueda.basico.Node;
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
	
	/**
	 * MODIFICAR
	 * Constructor para introducir un nuevo nodo en el algoritmo A estrella
	 */
	public Node(Node parentNode, ArrayList<Herramienta> herramientas, ArrayList<Trabajador> trabajadores, ArrayList<Tarea> tareas) {
		this.parent       = parentNode;  // padre en el árbol A*
		if(parent!=null) {
		this.cost        = parentNode.cost;
		this.heuristic   = parentNode.heuristic;
		this.evaluation   = parentNode.evaluation;
		// Añadir más variables si se desea
		ArrayList<Trabajador> trabajadoress = new ArrayList<Trabajador>();
		for (int i = 0; i < trabajadores.size(); i++) {
			Trabajador trabajador = new Trabajador(trabajadores.get(i).getNombre(), trabajadores.get(i).getHabPodar(), 
					trabajadores.get(i).getHabLimpiar(),trabajadores.get(i).getHabReparar(), trabajadores.get(i).getArea(), 
					trabajadores.get(i).getHerramienta(), trabajadores.get(i).getTiempoExacto(),trabajadores.get(i).getAreaAnterior());
			//añadir los demas atributos
			trabajadoress.add(trabajador);
		}
		this.trabajadores = trabajadoress;
		ArrayList<Herramienta> herramientass = new ArrayList<Herramienta>();
		for (int i = 0; i < herramientas.size(); i++) {
			Herramienta herramienta = new Herramienta(herramientas.get(i).getNombre(), herramientas.get(i).getTrabajo(),herramientas.get(i).getPeso(), 
					herramientas.get(i).getMejora(),herramientas.get(i).getCantidad());
			herramientass.add(herramienta);
		}
		this.herramientas = herramientass;
		ArrayList<Tarea> tareass = new ArrayList<Tarea>();
		for (int i = 0; i < tareas.size(); i++) {
			Tarea tarea = new Tarea(tareas.get(i).getTipo(), tareas.get(i).getArea(), tareas.get(i).getUnidades());
			tareass.add(tarea);
		}
		this.tareas = tareass;
		}
		else {
			this.trabajadores = trabajadores;
			this.herramientas = herramientas;
			this.tareas = tareas;
		}
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
					original.trabajadores.get(i).getHerramienta(), original.trabajadores.get(i).getTiempoExacto(), original.trabajadores.get(i).getAreaAnterior());
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
		//diferencias con el estado final
		/*double aux=0;
		for (int i = 0; i < this.tareas.size(); i++) {
			if(this.tareas.get(i).getUnidades()>0)aux++;
		}
		if(aux==0) {
		for (int i = 0; i < this.trabajadores.size(); i++) {
			if(!this.trabajadores.get(i).getArea().equals("A"))aux=aux+0.5;
		}}
		this.heuristic = aux;*/
		//tareas cercanas con mucho peso, lejanas con poco
		double aux=0;
		for (int i = 0; i < this.tareas.size(); i++) {
			Tarea ta=this.tareas.get(i);
			if(ta.getUnidades()>0&&!ta.getTipo().equals("limpiar"))aux++;
		}
		for (int i = 0; i < this.trabajadores.size(); i++) {
			Trabajador t = this.trabajadores.get(i);
			if(!t.getArea().equals("A"))aux=aux+0.5;
			if(t.getAreaAnterior()!=null&&t.getCoste(t.getAreaAnterior(), t.getArea())>3&&t.getHerramienta().getPeso()>0)aux=aux+100;
			
		}
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
					this.trabajadores.get(i).getTiempoHorasDecimales()!=other.trabajadores.get(i).getTiempoHorasDecimales()//si el tiempo de trabajo de sus trabajadores es diferente
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

		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("mi coste es "+this.cost+" mi heurística es "+this.heuristic+" y mi estado es: ");
		System.out.println("----TAREAS----");
		for (int i = 0; i < this.tareas.size(); i++) {
					System.out.println(this.tareas.get(i).getUnidades()+ " "+ this.tareas.get(i).getTipo()+" "+this.tareas.get(i).getArea() );
		}
		System.out.println("----TRABAJADORES----");
		for (int i = 0; i < this.trabajadores.size(); i++) {
			if(this.trabajadores.get(i).getNombre().equals("Antonio")) {
			if(this.trabajadores.get(i).getHerramienta()!=null)System.out.println(this.trabajadores.get(i).getNombre()+" "+
		this.trabajadores.get(i).getArea()+ " "+this.trabajadores.get(i).getHerramienta().getNombre()+ " llevo currando "+this.trabajadores.get(i).getTiempoHorasDecimales());
			else System.out.println(this.trabajadores.get(i).getNombre()+" "+this.trabajadores.get(i).getArea()+ " ");
			}
		}
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
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
	
	public int getUnidades(String area,String trabajo) {
		for (int i = 0; i < this.tareas.size(); i++) {
			if(this.tareas.get(i).getArea().equals(area)&&this.tareas.get(i).getTipo().equals(trabajo)) {
				return this.tareas.get(i).getUnidades();
			}
		}
		return -1;
	}
	public void setUnidades(String area,String trabajo, int unidades) {
		for (int i = 0; i < this.tareas.size(); i++) {
			if(this.tareas.get(i).getArea().equals(area)&&this.tareas.get(i).getTipo().equals(trabajo)) {
				int resta = this.tareas.get(i).getUnidades() - unidades;
				if(resta>0)this.tareas.get(i).setUnidades(resta);
				else this.tareas.get(i).setUnidades(0);
				break;
			}
		}
	}
	public void setUnidadesNuevas(String area,String trabajo, int unidades) {
		for (int i = 0; i < this.tareas.size(); i++) {
			if(this.tareas.get(i).getArea().equals(area)&&this.tareas.get(i).getTipo().equals(trabajo)) {
				this.tareas.get(i).setUnidades(this.tareas.get(i).getUnidades() + unidades);
				break;
			}
		}
	}

	public void setHerramienta(int trabajador) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Trabajador t =this.trabajadores.get(trabajador);
		Herramienta ht = t.getHerramienta();						//herramienta del trabajador
		for (int i = 0; i < this.herramientas.size(); i++) {				//recorremos las herramientas
			Herramienta h = this.herramientas.get(i);
			if(ht==null&&h.getCantidad()>0&&this.hayTareas(h.getTrabajo())) {
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
	
	public void moverTrabajador(int trabajador, String tarea) {
		String area=null;
		if(tarea.equals("almacen"))area="A";
		else {
			area=getAreaTarea(tarea);
			if(area==null)area="A";
			else {
				hacerTarea(trabajador,area);
				this.setCoste(this.getCost()-0.7);
			}
		}
		Trabajador t =this.trabajadores.get(trabajador);
		t.setTiempoExacto((int) (t.getTiempoExacto()+t.getCoste(t.getArea(), area)*(5+t.getHerramienta().getPeso())));
		t.setAreaAnterior(t.getArea());
		t.setArea(area);
	}
	
	public void hacerTarea(int trabajador, String area) {
		Trabajador t =this.trabajadores.get(trabajador);
		
			if(t.getHerramienta().getTrabajo().equals("limpiar"))this.setUnidades(area, t.getHerramienta().getTrabajo(),t.getHabLimpiar() + t.getHerramienta().getMejora() );
			else if(t.getHerramienta().getTrabajo().equals("podar")) {
				int uAntes = this.getUnidades(area, "podar");
				this.setUnidades(area, t.getHerramienta().getTrabajo(),t.getHabPodar() + t.getHerramienta().getMejora());
				int uDespues= this.getUnidades(area, "podar");
				this.setUnidadesNuevas(area, "limpiar", uAntes-uDespues);
			}
			else this.setUnidades(area, t.getHerramienta().getTrabajo(),t.getHabReparar() + t.getHerramienta().getMejora());
			t.setTiempoExacto(t.getTiempoExacto()+60);
		
	}
	public String getAreaTarea(String tipo) {
		for (int i = 0; i < this.tareas.size();i++) {
			Tarea ta = this.tareas.get(i);
			if(ta.getTipo().equals(tipo)&&ta.getUnidades()>0) return ta.getArea();
		}
		return null;
	}
	
	
}









