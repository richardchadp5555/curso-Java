package gestion;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*Autor: Richard Chadwick Plaza
 * Fecha: 25 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: esta clase agrega la funcionalidad necesaria para exportar los productos de la base de datos a un archivo CSV
 */

public class ExportarProductos {
	// Atributos
	private Connection connection;
	
	// Constructor
	public ExportarProductos() {
		this.connection = ConexionBD.getConnection();
	}
	
	// Método para exportar los productos a CSV
	public void exportacionCSV(String rutaArchivo) {
		String sql = "SELECT * FROM productos";
		FileWriter fileWriter = null;
		
		try { 
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			// Creación  del archivo CSV
			fileWriter = new FileWriter(rutaArchivo);
			// Encabezado en el archivo
			fileWriter.append("idproduto, nombre, stock, precio\n");
			// Escribimos los datos de cada fila
			while(rs.next()) {
				fileWriter.append(String.valueOf(rs.getInt("idproducto")));
				fileWriter.append(",");
				fileWriter.append(rs.getString("nombre"));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(rs.getInt("stock")));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(rs.getDouble("precio")));
				fileWriter.append("\n");
			}
			System.out.println("Los datos de la tabla productos e han exportado correctamente. ");
		} catch (SQLException e) {
            System.out.println("Error al consultar los datos de la tabla productos: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo CSV: " + e.getMessage());
        } finally {
            // Cerramos el FileWriter
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el FileWriter: " + e.getMessage());
                }
            }
        }
	}
}
 