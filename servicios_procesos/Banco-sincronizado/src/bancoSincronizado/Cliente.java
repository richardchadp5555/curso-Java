package bancoSincronizado;

import java.util.concurrent.Semaphore;

/*Autor: Richard Chadwick Plaza
 * Fecha: 1 dic 2024 
 * Curso: 1ºDAM
 * Descripcion: clase Cliente, hilo que hace transacciones accediendo al cajero
 * Lenguaje: Java
 * Asignatura: Programación de servicios y procesos
 */

public class Cliente implements Runnable{
	// Atributos
	private String nombre;
	private Semaphore cajeros;
	
	// Constructor 
	public Cliente (String nombre, Semaphore cajeros) {
		this.nombre = nombre;
		this.cajeros = cajeros;
	}

	@Override
	public void run() {
		try {	
			// coge el cajero
			System.out.println(this.nombre + " está esperando por un cajero");
			this.cajeros.acquire(1);
			hacerTransaccion();
			System.out.println(this.nombre + " está usando el cajero");
			System.out.println("El cliente " + this.nombre + " ha terminado la transacción y deja el cajero");
			this.cajeros.release();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("El cliente " + this.nombre + " ha sido interrumpido");
		}
		
	}
	
	// Método que simula el proceso de hacer una transacción
	public  void hacerTransaccion() {
		System.out.println("El cliente " + this.nombre + " acaba de llegar al cajero. ");
		try {
			Thread.sleep((int) ((Math.random() * 3000) + 1000));
		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("El cliente " + this.nombre + " se ha interrumpido. ");
		}
	}
}
 