package gestionCafeteria;

import java.util.LinkedList;

/*Autor: Richard Chadwick Plaza
 * Fecha: 2 dic 2024 
 * Curso: 2ºDAM
 * Descripcion: el mostrador representa el buffer compartido entre los clientes y los baristas
 * Lenguaje: Java
 * Asignatura: Programacion de procesos y servicios
 */

public class Mostrador {
	// Atributos
	private int capacidadPedidos;				// La cantidad máxima de pedidos que caben en el mostrador
	private LinkedList<String> pedidos;	// LinkedList donde se guardan los pedidos
	
	// Constructor
	public Mostrador(int capacidadPedidos) {
		this.capacidadPedidos = capacidadPedidos;
		this.pedidos = new LinkedList<>();
	}
	
	// Método para agregar un pedido
	public synchronized void agregarPedido(String pedido, String nombreCliente) {	
		try {
			// El cliente espera a que haya un hueco para dejar el pedido
			System.out.println("El cliente " + nombreCliente + " espera");
			while (this.pedidos.size() == this.capacidadPedidos) {
				wait();
			}
			// Si hay hueco, agrega el pedido
			this.pedidos.addLast(pedido);
			System.out.println("El cliente " + nombreCliente + " agregó el pedido: " + pedido + ". Pedidos en mostrador: " + pedidos.size());

			
			// Notifica a los baristas de que hay un pedido disponible
			notifyAll();
		}catch (InterruptedException e) {
	        System.out.println("Hilo interrumpido, cliente " + nombreCliente + ", error: " + e.getMessage());
	        Thread.currentThread().interrupt();
	    }
	}
	
	// Método para retirar un pedido
	public synchronized String retirarPedido(String nombreBarista) {
		String pedido = null;
		try {
			// El barista espera a que haya pedidos disponibles
			System.out.println("El barista " + nombreBarista + " espera");
			// Espera a que haya pedidos disponibles
			while (this.pedidos.size() == 0) {
				wait();
			}
			// Agrega el pedido 
			pedido = this.pedidos.removeFirst();
			System.out.println("El barista " + nombreBarista + " retiró el pedido: " + pedido + ". Pedidos restantes: " + pedidos.size());
			// Notifica a los clientes de que hay un hueco disponible
			notifyAll();
		} catch(InterruptedException e) {
			System.out.println("hilo interrumpido, barista " + nombreBarista + ", error: " + e.getMessage());
			Thread.currentThread().interrupt();
		}	
		return pedido;
	}
}
 