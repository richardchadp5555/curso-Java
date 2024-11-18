package ejercicio;

/*Autor: Richard Chadwick Plaza
 * Fecha: 18 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: clase Persona
 */
public class Persona implements Runnable{
	private String nombre;
	
	public Persona(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void run() {
	    try {
	        System.out.println(this.nombre + " está esperando para ir al baño (Baños disponibles: " 
	                           + Principal.baños.availablePermits() + ")");
	        Principal.baños.acquire();
	        System.out.println(this.nombre + " ha entrado al baño");

	        // Simulamos  el tiempo en el baño con sleep()
	        Thread.sleep((int) (Math.random() * 6000));
	    } catch (InterruptedException e) {
	        System.out.println("Se ha interrumpido el hilo de " + this.nombre + ": " + e.getMessage());
	    } finally {
	        // Liberamos el baño
	        Principal.baños.release();
	        System.out.println(this.nombre + " ha salido del baño (Baños disponibles: " 
	                           + Principal.baños.availablePermits() + ")");
	    }
	}


}
 