package practica.busqueda.basico;

import java.util.ArrayList;
import java.util.List;

import practica.busqueda.basico.Node;
import practica.busqueda.basico.OpenList;
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

public class AStar {

	int printDebug; // 0: nada, 1: información básica, 2: información completa

	private OpenList openList = new OpenList();						// Lista de nodos por explorar
	private ArrayList<Node> closedList = new ArrayList<Node>();		// Lista de nodos explorados
	private Node initialNode;										// Nodo inicial del problema
	private Node goalNode;											// Nodo meta del problema
	private boolean findGoal;										// Se ha encontrado la meta

	/**
	 * Insertamos en la lista de nodos abiertos los nodos según las acciones que se pueden realizar en este instante
	 * MODIFICAR
	 * @param currentNode - el nodo actual
	 */
	private void addAdjacentNodes(Node currentNode) {
		// MODIFICAR para insertar las acciones específicas del problema
		ArrayList<Trabajador> trabajadores  = currentNode.getTrabajadores();
		for (int i = 0; i < trabajadores.size(); i++) {//recorremos todos los trabajadores
			if(trabajadores.get(i).getNombre().equals("Antonio")) {//en la parte basica solo nos interesa antonio
				Trabajador t =trabajadores.get(i);
				ArrayList<String> adyacentes = currentNode.getAdyacentes(t.getArea());//areas adyacentes a donde se encuentra antonio
					for (int j = 0; j < adyacentes.size(); j++) {//crea un nodo para cada una de las areas a las que se puede mover antonio
						Node newNode = new Node(currentNode,currentNode);
						//si no tiene herramienta y esta en el almacen que coja una y le movemos al adyacente
						if(newNode.trabajadores.get(i).getHerramienta()==null&&newNode.trabajadores.get(i).getArea().equals("A")){
							newNode.setHerramienta(i);
							
							//movemos al trabajador y a su herramienta al area adyacente
							newNode.moverTrabajador(i, adyacentes.get(j));
						}
						//si el adyacente es el almacen que se mueva al almacen y cambie de herramienta
						else if(adyacentes.get(j).equals("A")) {
							//movemos al trabajador y a su herramienta al area adyacente
							newNode.moverTrabajador(i, adyacentes.get(j));
						
							newNode.setHerramienta(i);
							
						}
						else {//si no es ningun caso de los anteriores, movemos al trabajador al area adyacente
							//movemos al trabajador y a su herramienta al area adyacente
							newNode.moverTrabajador(i, adyacentes.get(j));
						}
		
						//si en el area adyacente hay una tarea por hacer y ese trabajador tiene la herramienta necesaria 
						//que la haga hasta completarla
						if(newNode.getUnidades(adyacentes.get(j), newNode.trabajadores.get(i).getHerramienta().getTrabajo())>0) {
							newNode.hacerTarea(i, adyacentes.get(j));
						}
						
						newNode.setCoste(currentNode.getCost()+(newNode.trabajadores.get(i).getHerramienta().getPeso()/10));		// calcula el coste de la acción y se lo suma al coste del padre
						newNode.computeHeuristic(this.goalNode);								// genera su heurística
						newNode.computeEvaluation();
						this.openList.insertAtEvaluation(newNode);
					}
			}
		}
	}
	
	/**
	 * Implementación de A estrella
	 */
	public double Algorithm() {
		double initialTime = Double.parseDouble(""+System.currentTimeMillis()); // Para contar el tiempo de ejecución

		Node currentNode = null;

		while(!this.openList.isEmpty()) { 				// Recorremos la lista de nodos sin explorar
			currentNode = this.openList.pullFirst(); 	// Extraemos el primero (la lista esta ordenada segun la funcion de evaluación)
			if(checkNode(currentNode)) {				// Si el nodo ya se ha visitado con un coste menor (esta en la lista de explorados) lo ignoramos
				currentNode.printNodeData(printDebug);
				
				closedList.add(currentNode); 			// Añadimos dicho nodo a la lista de explorados

				if(this.getGoalNode().equals(currentNode,this.getGoalNode())) {	// Si es el nodo meta hemos acabado y no hace falta continuar
					this.setGoalNode(currentNode);
					this.setFindGoal(true);
					break;
				}
				this.addAdjacentNodes(currentNode); 	// Expandimos el nodo segun las acciones posibles    	
			}
		}
		
		// Para contar el tiempo de ejecución
		double fin    = Double.parseDouble(""+System.currentTimeMillis());
		double tiempo = (fin - initialTime) / 1000;
		return tiempo;
	}


	/**
	 * Constructor del algoritmo, obtiene el nodo de inicio y el nodo meta
	 * NO MODIFICAR
	 * @param initialNode
	 * @param goalNode
	 */
	public AStar(int printDebug, Node initialNode, Node goalNode) { 
		this.printDebug = printDebug;
		this.setInitialNode(initialNode);
		this.setGoalNode(goalNode);
		this.setFindGoal(false); 					// No se ha llegado al nodo meta

		// Introducir heurísticas y costes para el nodo inicial. El nodo meta solo tiene heurística
		initialNode.computeHeuristic(goalNode);	// Coste esperado por la heurística para llegar al nodo final desde el inicial
		initialNode.setCoste(0);					// el nodo inicial tiene coste cero
		initialNode.computeEvaluation();			// coste + heurística
		goalNode.computeHeuristic(goalNode);		// Debe ser 0, ya es el nodo final
		// Genera la lista de nodos explorados y sin explorar
		this.closedList = new ArrayList<Node>();
		this.openList   = new OpenList();
		this.openList.insertAtEvaluation(initialNode); // Añadimos a la lista de nodos sin explorar el nodo inicial
	}


	/**
	 * Comprobación de si el nodo ya se ha explorado
	 * NO MODIFICAR
	 * @param currentNode
	 * @return
	 */
	private boolean checkNode(Node currentNode) {
		boolean expandirNodo = true;
		for (Node node : this.closedList) { // Se observa si el nodo está en la lista de cerrados
			if(currentNode.equals(node)) {	// Comprueba si la información del nodo es igual
				expandirNodo = false;
				break;
			}
		}
		return expandirNodo;				// false en el caso de que el nodo se haya visitado, indicando que no hay que expandirlo
	}


	/**
	 * Método para calcular el camino desde el nodo Inicial hasta el nodo actual
	 * NO MODIFICAR
	 * @param currentNode
	 * @return lista de nodos ordenada, desde el primer nodo al último
	 */
	public List<Node> getPath(Node currentNode) {
		List<Node> path = new ArrayList<Node>();	
		path.add(currentNode);	
		Node parent;
		while ((parent = currentNode.getParent()) != null) {	// Desde el nodo actual, se busca el nodo padre y se insertan 
			path.add(0, parent);								//  dentro de la lista de manera inversa
			currentNode = parent;
		}
		return path;
	}


	/**** Getters y Setters ****/
	/**
	 * MODIFICAR y/o AÑADIR si se considera necesario. No es imprescindible, solo si se considera que puede ayudar a la implementación
	 */
	public Node getInitialNode() {
		return initialNode;
	}
	public void setInitialNode(Node initialNode) {
		this.initialNode = initialNode;
	}
	public boolean isFindGoal() {
		return findGoal;
	}
	public void setFindGoal(boolean findGoal) {
		this.findGoal = findGoal;
	}
	public Node getGoalNode() {
		return goalNode;
	}
	public void setGoalNode(Node goalNode) {
		this.goalNode = goalNode;
	}

}
