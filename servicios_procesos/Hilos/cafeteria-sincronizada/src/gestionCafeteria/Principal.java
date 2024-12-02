package gestionCafeteria;

/*Autor: Richard Chadwick Plaza
 * Fecha: 2 dic 2024 
 * Curso: 2ºDAM
 * Enunciado ejercicio: En una cafetería, los clientes hacen pedidios de café y los baristas preparan estos pedidos. El sitema debe simular
 * el patrón productor-consumidor con las siguientes características:
 *		- Cada pedido debe tener un id único yel tipo de café (por ejemplo, Latte, Espresso y Capucchino)
 *		- Los clientes generan un pedido cada 1 a 3 segundos
 *		- Los baristas procesan un pedido cada 2 a 4 segundos
 * Descripcion: esta clase inicializa y ejecuta el sistema de pedidos de la cafeteria
 * Lenguaje: Java
 * Asignatura: Programación de procesos y servicios
 */

public class Principal {
    public static void main(String[] args) {
        // Crear el mostrador (buffer compartido)
        Mostrador mostrador = new Mostrador(5); // Capacidad de 5 pedidos

        // Crear clientes (productores)
        Cliente cliente1 = new Cliente("Ana", mostrador);
        Cliente cliente2 = new Cliente("Carlos", mostrador);
        Cliente cliente3 = new Cliente("Sofía", mostrador);

        // Crear baristas (consumidores)
        Barista barista1 = new Barista("Laura", mostrador);
        Barista barista2 = new Barista("Pedro", mostrador);

        // Iniciar hilos de clientes
        new Thread(cliente1).start();
        new Thread(cliente2).start();
        new Thread(cliente3).start();

        // Iniciar hilos de baristas
        new Thread(barista1).start();
        new Thread(barista2).start();
    }
}
 