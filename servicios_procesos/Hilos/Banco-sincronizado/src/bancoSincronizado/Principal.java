package bancoSincronizado;

import java.util.concurrent.Semaphore;

/*Autor: Richard Chadwick Plaza
 * Fecha: 1 dic 2024 
 * Curso: 2ºDAM
 * Descripcion: clase Principal
 * Lenguaje: Java
 * Asignatura: Programación de servicios y procesos
 */
public class Principal {
	public static void main(String[] args) {
		// El cajero es el recurso compartido por los clientes, usamos un semáforo para controlarlo
		Semaphore cajeros = new Semaphore(2);			// Dos cajeros
		
		// Array de clientes
		Cliente clientes[] = new Cliente[10];
		
		// Array de nombres de clientes
		String nombres[] = {"Alberto", "Laura", "Silvia", "Oscar", "Antonio", "jose", "Pepe", "Maria", "Luis", "Carlos"};
		
		// Creamos e iniciamos los hilos
		for(int i = 0; i < clientes.length; i++) {
			clientes[i] = new Cliente(nombres[i], cajeros);
			Thread hilo = new Thread(clientes[i]);
			hilo.start();
		}
	}

}
 