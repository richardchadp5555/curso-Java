package gestionBiblioteca;

import java.util.LinkedList;

/*Autor: Richard Chadwick Plaza
 * Fecha: 1 dic 2024 
 * Curso: 2SºDAM
 * Descripcion: clase Estante (Buffer compartido). El estante será el lugar donde los empleados colocarán los libros y los clientes lo retirarán
 * Lenguaje: Java
 * Asignatura: Programación de procesos y servicios
 */

public class Estante {
	// Atributos
	private final int capacidad;
	private final LinkedList<String> libros;
	
	// Constructor
	public Estante(int capacidad) {
		this.capacidad = capacidad;
		this.libros = new LinkedList<>();		
	}
	
	// Método para agregar libros al estante
	public synchronized void agregarLibro(String libro, String empleado) {
		while(this.libros.size() == capacidad) {			// Si el estante esta lleno espera
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Error: " + e.getMessage());
			}
		}
		// Cuando haya hueco agrega el libro
		this.libros.addLast(libro);
		System.out.println("El empleado " + empleado + " ha agregado el libro " + libro + " al estante");
		// Notifica a los clientes de que hay un libro disponible
		notifyAll();
	}
	
	// Método para retirar libros del estante
	public synchronized String retirarLibro(String cliente) {
		while(this.libros.isEmpty()) {		// Si no hay libros espera
			try {
				wait();
			} catch(InterruptedException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		// Cuando haya libros disponibles retira uno
		String libro = this.libros.removeFirst();
		System.out.println("El cliente " + cliente + " ha retirado el libro " + libro + " del estante");
		notifyAll();			// Notifica a los empleados de que hay un hueco en el estante
		return libro;
	}
}
 