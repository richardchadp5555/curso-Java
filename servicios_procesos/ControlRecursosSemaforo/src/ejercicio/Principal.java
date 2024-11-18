package ejercicio;
import java.util.concurrent.Semaphore;

/*Autor: Richard Chadwick Plaza
 * Fecha: 18 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: clase Principal
 */

public class Principal {
	// Creamos tres baños 
	  public static final Semaphore baños = new Semaphore(3);
    public static void main(String[] args) {
    	
    	  // Creamos diez personas por ejemplo, es decir, diez hilos
    	  int numeroHilos = 10;
    	  
    	  for(int i = 0; i < numeroHilos; i++) {
    		  Thread persona = new Thread(new Persona("Persona-" + i));
              persona.start();
              
              // Añadimos una pequeña pausa entre la llegada de cada persona al baño
              try {
            	  Thread.sleep(1000);
              }catch(InterruptedException e){
            	  e.printStackTrace();
              }
    	  }
    }
}
 