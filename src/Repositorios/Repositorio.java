package Repositorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Clases.Personaje;

public class Repositorio {
	// ---- BBDD
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String DB_NAME = "rpg";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static final String rutaArchivoSQL = "src\\rpg.sql";

	// ---- CREACIÓN DE LA BASE DE DATOS
	public static void crearBaseDeDatos() {

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			ejecutarScriptSQL(connection, rutaArchivoSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// --- LEER Y EJECUTAR SCRIPT SQL
	private static void ejecutarScriptSQL(Connection connection, String archivoSQL) {
		try (BufferedReader br = new BufferedReader(new FileReader(archivoSQL))) {
			StringBuilder sql = new StringBuilder();
			String linea;
			while ((linea = br.readLine()) != null) {

				if (!linea.trim().isEmpty() && !linea.trim().startsWith("--")) {

					sql.append(linea);

					if (linea.trim().endsWith(";")) {
						try (Statement stmt = connection.createStatement()) {
							stmt.execute(sql.toString());
							sql.setLength(0); // Limpiar el comando para el siguiente
						}
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error al leer el archivo SQL: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Error al ejecutar el script SQL: " + e.getMessage());
		}
	}

	// ---- CONSULTAR PERSONAJE
	public static ArrayList<Personaje> consultarPersonajes() {

		ArrayList<Personaje> personajes = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
				Statement statement = connection.createStatement()) {

			ResultSet resultSetPersonajes = statement.executeQuery("SELECT * FROM personajes");
			while (resultSetPersonajes.next()) {
				Personaje p1 = new Personaje(resultSetPersonajes.getString("nombre"),
						resultSetPersonajes.getString("raza"), resultSetPersonajes.getInt("fuerza"),
						resultSetPersonajes.getInt("defensa"));

				personajes.add(p1);
			}
			resultSetPersonajes.close();

		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al obtener datos de la base de datos: " + e.getMessage());
		}
		return personajes;
	}

	// ---- CONSULTAR NOMBRES DE PERSONAJE
	public static ArrayList<String> consultarPersonajesNombre(ArrayList<String> personajes) {
		
		try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
				Statement statement = connection.createStatement()) {
			
			ResultSet resultSetPersonajes = statement.executeQuery("SELECT nombre FROM personajes");
			while (resultSetPersonajes.next()) {
				personajes.add(resultSetPersonajes.getString("nombre"));
			}
			resultSetPersonajes.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al obtener datos de la base de datos: " + e.getMessage());
		}
		return personajes;
	}

	// ---- MÉTODO ELIMINAR PERSONAJE POR NOMBRE
	public static void eliminarPersonajePorNombre(String nombrePersonaje) {
		try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
				Statement statement = connection.createStatement()) {
			String sqlDelete = "DELETE FROM personajes WHERE nombre = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
				preparedStatement.setString(1, nombrePersonaje);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Se ha eliminado el personaje correctamente.");
				} else {
					System.out.println("No se encontró ningún personaje con ese nombre.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al intentar eliminar el personaje.");
			e.printStackTrace();
		}
	}

	// ---- MÉTODO MODIFICAR PERSONAJE POR NOMBRE
	public static void modificarPersonajePorNombre(String nombre, String nuevoNombre, String nuevaFuerza,
			String nuevaDefensa) {
		try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
				Statement statement = connection.createStatement()) {
			String sqlUpdate = "UPDATE personajes SET nombre = ?, fuerza = ?, defensa = ? WHERE nombre = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
				preparedStatement.setString(1, nuevoNombre);
				preparedStatement.setInt(2, Integer.parseInt(nuevaFuerza));
				preparedStatement.setInt(3, Integer.parseInt(nuevaDefensa));
				preparedStatement.setString(4, nombre);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Se ha modificado el personaje correctamente.");
				} else {
					System.out.println("No se encontró ningún personaje con ese nombre.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Error al intentar modificar el personaje.");
			e.printStackTrace();
		}
	}

	// ---- MÉTODO INSERTAR PERSONAJE
	public static void insertarPersonaje(String personajeSeleccionado, String raza, int fuerzaPersonaje,
			int defensaPersonaje) {
		try (Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD)) {
			String insertSQL = "INSERT INTO personajes (nombre, raza, fuerza, defensa) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, personajeSeleccionado);
			preparedStatement.setString(2, raza);
			preparedStatement.setInt(3, fuerzaPersonaje);
			preparedStatement.setInt(4, defensaPersonaje);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Error al intentar insertar el personaje.");
			e.printStackTrace();
		}
	}
}
