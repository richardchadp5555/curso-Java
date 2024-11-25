package gestion;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*Autor: Richard Chadwick Plaza
 * Fecha: 24 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: Esta clase se encarga de gerstionar la conexión a la base de datos MySQL.
 * Utiliza un archivo de configuración externo (config.properties) para obtener los parámetros de conexión, 
 * como la URL, el usuario y la contraseña.
 * La conexión se establece utilizando la clase DriverManager de JDBC.
 * Implementa un patrón Singleton para garantizar que solo exista una isntancia de la conexión activa durante la 
 * ejecución del programa.
 * 
 * Métodos principales:
 * 	-	getConnection(): Devuelve una conexión activa a la base de datos
 * 	
 * Uso:
 * 	- Esta clase puede ser utilizada por otras clases para interactuar con la base de datos.
 */

public class ConexionBD {
	private static Connection connection;
	public static Connection getConnection() {
		if (connection == null) {
			try {
				// Cargar el archivo config.properties
				Properties properties = new Properties();
				FileInputStream fis = new FileInputStream("config.properties");
				properties.load(fis);
				
				// Leer las propiedades del archivo
				String url = properties.getProperty("db.url");
				String user = properties.getProperty("db.user");
				String password = properties.getProperty("db.password");
				
				// Establecer la conexión
				connection = DriverManager.getConnection(url, user, password);
			}catch (IOException e) {
				System.out.println("Error al leer el archivo de configuración. " + e.getMessage());
			}catch (SQLException e) {
				System.out.println("Error al conectar con la base de datos. " + e.getMessage());
			}
		}
		return connection;
	}
}
 