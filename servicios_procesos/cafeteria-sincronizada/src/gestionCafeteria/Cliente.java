package gestionCafeteria;
import java.util.Random;


/*Autor: Richard Chadwick Plaza
 * Fecha: 2 dic 2024 
 * Curso: 2ºDAM
 * Descripcion: los clientes representan los hilos productores que generan pedidos cada 1 a 3 segundos
 * Lenguaje: Java
 * Asignatura: Programación de procesos y servicios
 */

public class Cliente implements Runnable {
	// Atributos
	private String nombre;
	private Mostrador mostrador;
	 private String[] bebidasDisponibles = {"Latte", "Espresso", "Capuchino"}; // Tipos de bebidas disponibles
	 private Random random = new Random();
	 
	// Constructor
	public Cliente(String nombre, Mostrador mostrador) {
		this.nombre = nombre;
		this.mostrador = mostrador;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				// Genera una bebida aleatoria
				String bebida = bebidasDisponibles[random.nextInt(bebidasDisponibles.length)];
				Pedido pedido = new Pedido(bebida, this.nombre);
				
				// Coloca el pedido en el mostrador
				mostrador.agregarPedido(pedido.toString(), this.nombre);
				
				// Simula el tiempo que tarda entre los pedidos
				Thread.sleep((random.nextInt(5) + 1) * 1000);
			}	
		} catch (InterruptedException e) {
            System.out.println("El cliente " + this.nombre + " fue interrumpido. Error: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
		
	}


	

}
 