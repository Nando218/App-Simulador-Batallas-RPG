package Clases;

import javax.swing.*;
import Repositorios.Repositorio;
import Servicios.Servicios;
import java.awt.*;
import java.sql.SQLException;

public class MainGUI extends JFrame {

	JPanel inicioPanel;
	JPanel personajePanel;
	JPanel modificarPersonajesPanel;
	JPanel seleccionLuchadores;
	JPanel equipamientoPanel;
	JPanel batallaPanel;
	JPanel guardarPanel;

	public MainGUI() {
		setTitle("Simulador de batallas V.0.0");
		setSize(900, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane(); // Pestañas

		inicioPanel = crearInicioPanel();
		tabbedPane.addTab("Inicio", inicioPanel);

		personajePanel = Servicios.crearPersonajePanel();
		tabbedPane.addTab("Crear Personaje", personajePanel);

		modificarPersonajesPanel = Servicios.crearModificarPersonajesPanel();
		tabbedPane.addTab("Modificar Personajes", modificarPersonajesPanel);

		seleccionLuchadores = Servicios.crearSeleccionLuchadoresPanel();
		tabbedPane.addTab("Selección de Luchadores", seleccionLuchadores);

		equipamientoPanel = Servicios.crearGestionarEquipamientoPanel();
		tabbedPane.addTab("Gestionar Equipamiento", equipamientoPanel);

		batallaPanel = Servicios.crearBatallaPanel();
		tabbedPane.addTab("Batalla", batallaPanel);

		guardarPanel = Servicios.crearGuardarPanel();
		tabbedPane.addTab("Guardar progreso", guardarPanel);

		add(tabbedPane);
	}

	// ---- PANEL CREAR INICIO
	public static JPanel crearInicioPanel() {
		JPanel panel = new JPanel() {

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Fondo_inicio.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new BorderLayout());
		return panel;
	}

	public static void main(String[] args) throws SQLException {
		Repositorio.crearBaseDeDatos();
		SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
	}
}