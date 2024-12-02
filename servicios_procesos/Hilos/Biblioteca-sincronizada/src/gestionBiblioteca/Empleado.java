package gestionBiblioteca;

/*Autor: Richard Chadwick Plaza
 * Fecha: 1 dic 2024 
 * Curso: 2ºDAM
 * Descripcion: el empleado es el hilo productor que produce libros y los agrega al estante
 * Lenguaje: Java
 * Asignatura: Programacion de procesos y servicios
 */

public class Empleado implements Runnable{
	// Atributos
	private String nombre;
	private Estante estante;
	private static int contadorLibros = 1; // Contador global para los libros
	
	// Constructor 
	public Empleado (String nombre, Estante estante) {
		this.nombre = nombre;
		this.estante = estante;
	}

	@Override
	public void run() {
		try {
			while(true) {
				// Genera un libro
				String libro;
				synchronized (Empleado.class) { // Bloque sincronizado para el contador global
                    libro = "libro nº" + contadorLibros;
                    contadorLibros++;
                }
				
				// Agrega el libro al estante
				this.estante.agregarLibro(libro, this.nombre);
				
				// Simula el tiempo que tarda en producir y agregar otro libro, entre 1 y 5 segundos
				Thread.sleep((int) ((Math.random() * 5000) + 1000));
			}
		} catch(InterruptedException e) {
			System.out.println("El empleado " + this.nombre + " ha sido interrumpidol, error: " + e.getMessage());
		}
	}
}
 