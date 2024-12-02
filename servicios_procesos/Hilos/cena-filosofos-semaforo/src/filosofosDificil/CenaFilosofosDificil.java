package filosofosDificil;

import java.util.concurrent.Semaphore;

/*Autor: Richard Chadwick Plaza
 * Fecha: 30 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: clase CenaFilosofosDificil, en esta clase resolvemos el ejercicio de la cena de los filósofos en su modo difícil. En este caso, cada filósofo solo puede tomar los palillos directamente a su izquierda y derecha. 
 * Usaremos un array de semáforos para representar los palillos, asegurando que cada filósofo acceda a los recursos compartidos de manera sincronizada y evitando interbloqueos.
 * En este enfoque del problema de los filósofos cada palillo es en si un recurso compartido, en el modo fácil el conjunto de los cinco palillos conforman el recurso compartido, aquí cada uno lo es, por lo que debemos controlar
 * que cada palillo solo sea usado por un filósofo a la vez
 * Asignatura: Programación de procesos y servicios
 * Lenguaje: Java
 */


public class CenaFilosofosDificil {
	public static void main(String[] args) {
		// Declaramos un array de semáforos donde cada semáforo representa un palillo
		Semaphore[] palillos = new Semaphore[5];
		
		// Inicializamos cada posición del array con un nuevo semáforo (palillo)
		for (int i = 0; i < palillos.length; i++) {
			palillos[i] = new Semaphore(1);
		}
		
		// Array de filósofos
		Filosofo[] filosofos = new Filosofo[5];
		
		// Nombres
		String[] nombres = {"Sócrates", "Platón", "Aristóteles", "Descartes", "Nietzsche"};
		
		// Inicializamos los filósofos
		filosofos[0] = new Filosofo(nombres[0], palillos[0], palillos[1]);
		filosofos[1] = new Filosofo(nombres[1], palillos[1], palillos[2]);
		filosofos[2] = new Filosofo(nombres[2], palillos[2], palillos[3]);
		filosofos[3] = new Filosofo(nombres[3], palillos[3], palillos[4]);
		filosofos[4] = new Filosofo(nombres[4], palillos[4], palillos[0]);			// Están en una distribución circular por eso el palillo derecho del úLtimo filósofo es el 0
		
		// Iniciamos los hilos
		for (Filosofo filosofo : filosofos) {
			filosofo.start();
		}
	}
}
 