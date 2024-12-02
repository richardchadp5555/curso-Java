package gestionCafeteria;

/*Autor: Richard Chadwick Plaza
 * Fecha: 2 dic 2024 
 * Curso: 2ºDAM
 * Descripción: Representa un pedido en la cafetería.
 * Lenguaje: Java
 * Asignatura: Programación de Procesos y Servicio
 */

public class Pedido {
	// Atributos
	private static int contadorGlobal = 1;			// Contador global para generar IDS unicos
	private int id;														// ID único del pedido
	private String tipo;												// Tipo de pedido (Latte, Espresso, Capuchino)
	private String cliente;											// Cliente que realizó el pedido
	
	// Constructor
	public Pedido(String tipo, String cliente) {
		this.id = contadorGlobal ++;					// Asigna un id único autoincrementado al pedido
		this.tipo = tipo;
		this.cliente = cliente;
	}
	//getters
	public int getId() {
		return this.id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public String getCliente() {
		return this.cliente;
	}
	
	@Override
	public String toString() {
		return "Pedido #: " + getId() + " (" + getTipo() + ") de " + getCliente();
	}
	
}
 