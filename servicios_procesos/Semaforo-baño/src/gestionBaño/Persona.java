package gestionBaño;

import java.util.concurrent.Semaphore;

/*Autor: Richard Chadwick Plaza
 * Fecha: 1 dic 2024 
 * Curso: 1ºDAM
 * Descripcion: esta clase funciona como un hilo que entra al baño
 * Lenguaje: Java
 */
public class Persona implements Runnable{
	// Atributos
	private String nombre;
	private Semaphore servicios;
	
	// Constructor
	public Persona(String nombre, Semaphore servicios){
		this.nombre = nombre;
		this.servicios = servicios;
	}

	@Override
	public void run() {
		try {
			esperaServicio();
			this.servicios.acquire(1);			// Entra al baño
			irServicio();
			System.out.println(this.nombre + " ha salido del baño");
			this.servicios.release(1);			// Sale del baño
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
	}
	
	// Metodo irServicio que simula el tiempo que tarda un hilo en ir al servicio
	public void irServicio() {
		System.out.println(this.nombre + " va al baño");
		// Simulamos el tiempo que tarda el hilo en el baño
		try {
			Thread.sleep((int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	// Metodo esperaServicio que simula un tiempo que espera el hilo 
	public void esperaServicio() {
		System.out.println(this.nombre + " está esperando para entrar al baño");
		try {
			Thread.sleep((int) (Math.random() * 5000));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
 