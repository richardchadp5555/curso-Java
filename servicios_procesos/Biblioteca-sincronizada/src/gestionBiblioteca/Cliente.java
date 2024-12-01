package gestionBiblioteca;

/*Autor: Richard Chadwick Plaza
 * Fecha: 1 dic 2024 
 * Curso: 2ºDAM
 * Descripcion: los clientes son los hilos consumidores que quitan libros del estante
 * Lenguaje: Java
 * Asignatura: Programación de procesos y servicios
 */

public class Cliente implements Runnable {
	// Atributos
	private String nombre;
	private Estante estante;
	
	//  Constructor
	public Cliente(String  nombre, Estante estante) {
		this.nombre = nombre;
		this.estante = estante;
	}
	@Override
	public void run() {
		try {
			while(true) {
				String libro = this.estante.retirarLibro(this.nombre);
				// Simula el tiempo que tarda en coger el  libro, entre 1 y 5 segundos
				Thread.sleep((int) ((Math.random() * 5000) + 1000));
			}
		} catch (InterruptedException e) {
			System.out.println("Se ha interrumpido el cliente " + this.nombre + ", error: " + e.getMessage());
		}
	}
	
}
 