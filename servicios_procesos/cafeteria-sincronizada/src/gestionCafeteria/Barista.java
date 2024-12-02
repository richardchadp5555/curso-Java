package gestionCafeteria;

/*Autor: Richard Chadwick Plaza
 * Fecha: 2 dic 2024 
 * Curso: 2ºDAM
 * Descripción: Representa un barista (hilo consumidor) que procesa los pedidos del mostrador.
 * Lenguaje: Java
 * Asignatura: Programación de Procesos y Servicios
 */
public class Barista implements Runnable {
    // Atributos
    private String nombre;          // Nombre del barista
    private Mostrador mostrador;    // Referencia al mostrador compartido

    // Constructor
    public Barista(String nombre, Mostrador mostrador) {
        this.nombre = nombre;
        this.mostrador = mostrador;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Retirar un pedido del mostrador
                String pedido = mostrador.retirarPedido(nombre);

                // Simula el tiempo de preparación del pedido (2-4 segundos)
                System.out.println(nombre + " está preparando el pedido: " + pedido);
                Thread.sleep((int) ((Math.random() * 3) + 2) * 1000);

                // Indicar que el pedido está listo
                System.out.println(nombre + " ha completado el pedido: " + pedido);
            }
        } catch (InterruptedException e) {
            System.out.println("El barista " + nombre + " fue interrumpido.");
            Thread.currentThread().interrupt();
        }
    }
}
