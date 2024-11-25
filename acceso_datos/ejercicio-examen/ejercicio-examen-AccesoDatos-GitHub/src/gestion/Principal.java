package gestion;

import java.sql.Connection;

/*Autor: Richard Chadwick Plaza
 * Fecha: 24 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: clase principal del proyecto, encargada de inciar la ejecución del programa.
 * Su propósito inicial es probar la conexión a la base de datos utilizando la clase ConexionBD.
 * Permite verificar que las credencialesy la configuración son correctas y que la base de datos está accesible
 */
public class Principal {
	public static void main(String[] args) {
		// Probar la conexión
		Connection connection = ConexionBD.getConnection();
		  if (connection != null) {
	            System.out.println("Conexión establecida correctamente.");
	        } else {
	            System.err.println("No se pudo establecer la conexión.");
	            return; // Detengo el programa si la conexión no se ha establecido
	        }
		  
		  // Crear instancia de UusariosDAO
		  UsuarioDAO usuarioDAO = new UsuarioDAO();
		  
		  // Inserción de usuarios manualmente
		  	System.out.println("=== Insertando usuarios manualmente ===");
		  	usuarioDAO.insertarUsuario("richard", "richard", "Richard Chadwick Plaza");
		  	usuarioDAO.insertarUsuario("pedro ", "pedro", "Pedro Martín");
		  	usuarioDAO.insertarUsuario("antonio", "antonio", "Antonio López");
		  	System.out.println("Usuarios insertados correctamente. ");

		  	// Registro manual de un pedido
	        System.out.println("=== Registrando un pedido manualmente ===");
	        PedidoDAO pedido1 = new PedidoDAO(1, 1, 5);
	        pedido1.registrarPedido();
	        
	        //Importar datos desde archivos CSV
	        System.out.println("=== Importando datos desde archivos CSV ===");
	        String rutaUsuarios = "usuarios.csv";
	        String rutaProductos = "C:\\Users\\richard\\Desktop\\javadoc\\ejercicio-examen-AccesoDatos-GitHub\\productos.csv";
	        String rutaPedidos = "pedidos.csv";
	        
	        ImportarCSV importar = new ImportarCSV(rutaUsuarios, rutaProductos, rutaPedidos);

	        System.out.println("=== Importando Usuarios ===");
	        importar.importarUsuarios();

	        System.out.println("=== Importando Productos ===");
	        importar.importarProductos();

	        System.out.println("=== Importando Pedidos ===");
	        importar.importarPedidos();

	        System.out.println("=== Proceso de importación finalizado ===");
	        
	        System.out.println("Exportando los datos de la tabla 'productos' a un archivo CSV: ");
	        ExportarProductos exportar = new ExportarProductos();
	        exportar.exportacionCSV("productos_exportados.csv"); 
	}
}
 