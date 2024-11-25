package gestion;

import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*Autor: Richard Chadwick Plaza
 * Fecha: 24 nov 2024 
 * Curso: 2ºDAM
 * Descripcion: esta clase aporta la funcionalidad para leer los datos de los archivos CSV (usuarios, productos, pedidos) 
 * e insertarlos en la base de datos.
 * Los atributos de esta clase son las rutas a los propios archivos.
 */

public class ImportarCSV {
	// Atributos
	private String rutaUsuarios;
	private String  rutaProdutos;
	private String rutaPedidos;
	private Connection connection;
	private Scanner scanner;
	private FileReader fileReader;
	
	//Constructor
	public ImportarCSV(String rutaUsuarios, String rutaProductos, String rutaPedidos) {
		this.rutaUsuarios = rutaUsuarios;
		this.rutaProdutos = rutaProductos;
		this.rutaPedidos = rutaPedidos;
		this.connection = ConexionBD.getConnection();
		this.scanner = null;
		this.fileReader = null;
	}
	
	// Método para importar los usuarios desde el archivo CSV
	public void importarUsuarios() {
		try { 
			this.fileReader = new FileReader(this.rutaUsuarios);
			this.scanner = new Scanner(fileReader);
			int contadorLinea = 1;
			while(this.scanner.hasNextLine()) {
				String linea = this.scanner.nextLine();
				String[] datos = linea.split(","); 			// Separados por comas
				
				// Comprobamos que hay exactamente 3 columnas en el archivo CSV
				if (datos.length != 3) {
					System.out.println("Error, formáto inválido en la línea número " + contadorLinea + ": " + linea);
					contadorLinea++;
					continue;
				}
				
				CallableStatement stmt  = null;
				try {
					// Llamamos al procedimiento almacenado insertUser
					String sql = "{CALL insertUser(?, ?, ?)}";
					stmt  = this.connection.prepareCall(sql);
					stmt .setString(1, datos[0]);		// nombrelogin
					stmt .setString(2, datos[1]);	  	// contrasena
					stmt .setString(3, datos[2]);	    // nombreCompleto
					stmt .execute();
					
				} catch (SQLException e) {
					System.out.println("Error al insertar el usuario: " + e.getMessage());
				} finally {
					if (stmt  != null) {
						try {
							stmt .close();
						} catch (SQLException e) {
							System.out.println("Error al cerrar CallableStatement " + e.getMessage());
						}
					}
				}
				contadorLinea ++;
			}
			System.out.println("Se han importado corretamente los usuarios desde el archivo CSV");
		} catch (IOException e) {
			System.out.println("Error al importar los usuarios del archivo CSV " + e.getMessage());
		} finally {
			cerrarRecursos();
		}
	}
	
	// Método para importar los productos a partir de un CSV
	public void importarProductos() {
		try {
			this.fileReader = new FileReader(this.rutaProdutos);
			this.scanner = new Scanner(fileReader);
			int contadorLinea = 1;
			while (this.scanner.hasNextLine()) {
				String linea = this.scanner.nextLine();
				String[] datos = linea.split(",");
				
				if (datos.length != 3) {
					System.out.println("Error, formáto inválido en la línea número " + contadorLinea + ": " + linea);
					contadorLinea++;
					continue;
				}
				
				PreparedStatement stmt  = null;
				try {
					// Consulta para insertar los productos
					String sql = "INSERT INTO productos (nombre, stock, precio) VALUES (?, ?, ?)";
					stmt = this.connection.prepareStatement(sql);
					stmt.setString(1,  datos[0]);	    // nombre
					stmt.setString(2, datos[1]);		// stock
					stmt.setString(3, datos[2]);		// precio
					stmt.executeUpdate();
				} catch(SQLException | NumberFormatException e) {
					System.out.println("Error al insertar el producto en la línea " + contadorLinea + ": " + e.getMessage());
				} finally {
					 cerrarPreparedStatement(stmt);
				}
				contadorLinea ++;
			}
			System.out.println("Se han importado correctamente los productos desde el archivo CSV");
		}catch (IOException e) {
			System.out.println("Error al importar el CSV de productos: " + e.getMessage());
		} finally {
			cerrarRecursos();
		}
	}
	
	// Método para importar los pedidos a partir del CSV
	public void importarPedidos() {
	    try {
	        this.fileReader = new FileReader(this.rutaPedidos);
	        this.scanner = new Scanner(this.fileReader);
	        int contadorLinea = 1;

	        while (this.scanner.hasNextLine()) {
	            String linea = this.scanner.nextLine();
	            String[] datos = linea.split(",");

	            if (datos.length != 3) {
	                System.out.println("Error, formato inválido en la línea número " + contadorLinea + ": " + linea);
	                contadorLinea++;
	                continue;
	            }

	            PreparedStatement insertPedidoStmt = null;
	            PreparedStatement updateStockStmt = null;

	            try {
	                this.connection.setAutoCommit(false); // Iniciar transacción

	                // Validar datos numéricos
	                int codUser = Integer.parseInt(datos[0]);
	                int idProducto = Integer.parseInt(datos[1]);
	                int cantidad = Integer.parseInt(datos[2]);

	                if (codUser <= 0 || idProducto <= 0 || cantidad <= 0) {
	                    System.out.println("Datos inválidos en la línea " + contadorLinea + ": Valores numéricos deben ser positivos.");
	                    continue;
	                }

	                // Insertar pedido
	                String insertarPedidosSQL = "INSERT INTO pedidos (coduser, idproducto, cantidad) VALUES (?, ?, ?)";
	                insertPedidoStmt = connection.prepareStatement(insertarPedidosSQL);
	                insertPedidoStmt.setInt(1, codUser);
	                insertPedidoStmt.setInt(2, idProducto);
	                insertPedidoStmt.setInt(3, cantidad);
	                insertPedidoStmt.executeUpdate();

	                // Actualizar stock
	                String updateStockSQL = "UPDATE productos SET stock = stock - ? WHERE idproducto = ? AND stock >= ?";
	                updateStockStmt = connection.prepareStatement(updateStockSQL);
	                updateStockStmt.setInt(1, cantidad);
	                updateStockStmt.setInt(2, idProducto);
	                updateStockStmt.setInt(3, cantidad);

	                int filasActualizadas = updateStockStmt.executeUpdate();

	                // Verificar si el stock fue suficiente
	                if (filasActualizadas == 0) {
	                    System.out.println("Stock insuficiente para el producto con ID: " + idProducto);
	                    this.connection.rollback();
	                } else {
	                    this.connection.commit();
	                    System.out.println("Pedido registrado correctamente en la línea: " + contadorLinea);
	                }
	            } catch (SQLException | NumberFormatException e) {
	                try {
	                    if (this.connection != null) {
	                        this.connection.rollback();
	                        System.out.println("Transacción revertida debido a un error.");
	                    }
	                } catch (SQLException rollbackEx) {
	                    System.out.println("Error al realizar rollback: " + rollbackEx.getMessage());
	                }
	                System.out.println("Error al insertar el pedido en la línea " + contadorLinea + ": " + e.getMessage());
	            } finally {
	            	cerrarPreparedStatement(insertPedidoStmt);
	                cerrarPreparedStatement(updateStockStmt);
	            }
	            contadorLinea++;
	        }
	        System.out.println("Se han importado correctamente los pedidos desde el archivo CSV.");
	    } catch (IOException e) {
	        System.out.println("Error al importar los pedidos del archivo CSV: " + e.getMessage());
	    } finally {
	        cerrarRecursos();
	    }
	}

	// Método para cerrar recursos (FileReader y Scanner)
	private void cerrarRecursos() { 
		if(this.scanner != null) {
			scanner.close();
		}
		if(fileReader != null) {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar el FileReader: " + e.getMessage());
			}
		}
	}
	
	// Método para cerrar el PreparedStatement, permite no duplicar código
	private void cerrarPreparedStatement(PreparedStatement stmt) {
	    if (stmt != null) {
	        try {
	            stmt.close();
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
	        }
	    }
	}

}
 