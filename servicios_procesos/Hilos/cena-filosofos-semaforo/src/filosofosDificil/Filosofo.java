package filosofosDificil;

import java.util.concurrent.Semaphore;

/*Autor: Richard Chadwick Plaza
 * Fecha: 30 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: clase Filosofo, cada filósofo representa un hilo en el problema de la cena de los fiLósofos por eso extiende de
 * Thread. En esta clase se manejará que cada filósofo solo pueda tomar los palillos de su izquierda y su derecha
 */

public class Filosofo extends Thread{
	// Atributos
	private String nombre;
	private Semaphore palilloIzquierdo;
	private Semaphore palilloDerecho;
	
	// Constructor
	public Filosofo(String nombre, Semaphore palilloIzquierdo, Semaphore palilloDerecho) {
		this.nombre = nombre;
		this.palilloIzquierdo = palilloIzquierdo;
		this.palilloDerecho = palilloDerecho;
	}
	
	// Método run
	@Override
	public void run() {
		try {
			while (true) {
				pensar();
				palilloIzquierdo.acquire(); 		// Toma el palillo izquierdo
				palilloDerecho.acquire();			// Toma el palillo derecho
				comer();
				palilloIzquierdo.release();			// Libera el palillo izquierdo
				palilloDerecho.release();			// Libera el palillo derecho
			} 
		}catch (InterruptedException e) {
				Thread.currentThread().interrupt();
		}
	}
	
	// Método pensar
	private void pensar() {
		System.out.println(this.nombre + " está pensando...");
		// Simulamos el tiempo en pensar con sleep
		try {
			Thread.sleep((int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	// Método comer
	private void comer() {
		System.out.println(this.nombre + " está comiendo...");
		// Simulamos el tiempo en comer
		try {
			Thread.sleep((int) (Math.random() * 1000));
		}catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	} 
}
 