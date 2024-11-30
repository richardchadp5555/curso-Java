package filosofosFacil;

import java.util.concurrent.Semaphore;

/*Autor: Richard Chadwick Plaza
 * Fecha: 30 nov 2024 
 * Curso: 1ºDAM
 * Descripcion: clase Filosofo, esta clase representará a cada filósofo como un hilo haciendo que extienda de Thread
 * Lenguaje: Java
 */

public class Filosofo extends Thread{
	// Atributos
	private String nombre;								// nombre para identificar a los filósofos
	private Semaphore palillos;					// Referencia al semáforo compartido
	
	// Constructor para inicializar el filósofo
	public Filosofo(String nombre, Semaphore palillos) {
		this.nombre = nombre;
		this.palillos = palillos;
	}
	
	// Sobrescribir el método run() que hará que cada filósofo se ejecute como un hilo
	@Override
	public void run() {
			try {
				pensar();
				palillos.acquire(2);		// Toma 2 palillos
				comer();
				palillos.release(2);			// Suelta 2 palillos
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();		
			}
		}
	
	// Los métodos pensar() y comer() simulan el tiempo que pasan pensando y comiendo
	private void pensar() {
		System.out.println("Filósofo " + nombre + " está pensando....");
		try {
			// Simulamos el tiempo de pensar on sleep
			Thread.sleep ((int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	private void comer() {
		System.out.println("Filósofo " + nombre + " está comiendo. ");
		try {
			// Simulamos el tiempo que tarda en comer con sleep
			Thread.sleep((int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
 