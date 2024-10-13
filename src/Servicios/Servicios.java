package Servicios;

import java.util.ArrayList;
import java.util.List;

import Clases.Equipamiento;
import Clases.MainGUI;
import Clases.Personaje;
import Repositorios.Repositorio;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.sql.SQLException;

public class Servicios {

	private static JComboBox<String> personajeSeleccionadoCombo;
	private static JComboBox<String> enemigoSeleccionadoCombo;

	private static String personajeSeleccionado;
	private static String enemigoSeleccionado;

	private static JTextField fuerzaPersonajeField;
	private static JTextField defensaPersonajeField;

	private static JTextField nombrePersonajeField;
	private static JTextField razaPersonajeField;

	private static JButton crearPersonajeButton;

	private static int fuerzaPersonaje = 0;
	private static int defensaPersonaje = 0;
	private static int fuerzaEnemigo = 0;
	private static int defensaEnemigo = 0;

	private static int nuevaFuerzaPersonaje = 0;
	private static int nuevaDefensaPersonaje = 0;
	private static int nuevaFuerzaEnemigo = 0;
	private static int nuevaDefensaEnemigo = 0;
	private static int resultadoBatalla = 0;

	private static JPanel equipamientoPersonajePanel;
	private static JPanel equipamientoEnemigoPanel;

	private static DefaultTableModel tableModel = new DefaultTableModel();

	private static JComboBox<Equipamiento> equipamientoPersonajeComboBox;
	private static JComboBox<Equipamiento> equipamientoEnemigoComboBox;

	private static JTextArea batallasTextArea;

	// ---- ACTUALIZAR LAS LISTAS EN LOS JCOMBOX
	private static void actualizarListas() {
		ArrayList<String> personajes = new ArrayList<>();
		Repositorio.consultarPersonajesNombre(personajes);
		// Actualizar los JComboBox
		personajeSeleccionadoCombo.setModel(new DefaultComboBoxModel<>(personajes.toArray(new String[0])));
		enemigoSeleccionadoCombo.setModel(new DefaultComboBoxModel<>(personajes.toArray(new String[0])));
	}

	// ---- PANEL MODIFICAR PERSONAJES
	public static JPanel crearModificarPersonajesPanel() {
		JPanel panel = new JPanel(new GridBagLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Fondo_modificar_personaje.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.CENTER;

		// ---- Campo de búsqueda
		JLabel lblNombreBuscar = new JLabel("Personajes:");
		lblNombreBuscar.setForeground(Color.RED);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(lblNombreBuscar, gbc);

		JComboBox<String> comboNombreBuscar = new JComboBox<>();
		comboNombreBuscar.setEditable(true);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(comboNombreBuscar, gbc);

		JButton btnBuscar = new JButton("Listar");
		btnBuscar.setBackground(new Color(123, 0, 0));
		btnBuscar.setForeground(Color.WHITE);
		gbc.gridx = 2;
		gbc.gridy = 0;
		panel.add(btnBuscar, gbc);

		// ---- Campos para modificar personaje
		JLabel lblNombreModificar = new JLabel("Nombre a Modificar:");
		lblNombreModificar.setForeground(Color.RED);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		panel.add(lblNombreModificar, gbc);

		JTextField txtNombreModificar = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(txtNombreModificar, gbc);

		JLabel lblNuevoNombre = new JLabel("Nuevo Nombre:");
		lblNuevoNombre.setForeground(Color.RED);

		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(lblNuevoNombre, gbc);

		JTextField txtNuevoNombre = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(txtNuevoNombre, gbc);

		JLabel lblNuevaFuerza = new JLabel("Nueva Fuerza:");
		lblNuevaFuerza.setForeground(Color.RED);

		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(lblNuevaFuerza, gbc);

		JTextField txtNuevaFuerza = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(txtNuevaFuerza, gbc);

		JLabel lblNuevaDefensa = new JLabel("Nueva Defensa:");
		lblNuevaDefensa.setForeground(Color.RED);

		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(lblNuevaDefensa, gbc);

		JTextField txtNuevaDefensa = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(txtNuevaDefensa, gbc);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBackground(new Color(123, 0, 0));
		btnModificar.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 3;
		panel.add(btnModificar, gbc);

		// ---- Campo para eliminar personaje
		JLabel lblNombreEliminar = new JLabel("Personaje a Eliminar:");
		lblNombreEliminar.setForeground(Color.RED);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		panel.add(lblNombreEliminar, gbc);

		JTextField txtNombreEliminar = new JTextField(20);
		gbc.gridx = 1;
		gbc.gridy = 7;
		panel.add(txtNombreEliminar, gbc);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(123, 0, 0));
		btnEliminar.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 3;
		panel.add(btnEliminar, gbc);

		// ---- ActionListener para el botón de listar
		btnBuscar.addActionListener(e -> {
			List<Personaje> personajes = Repositorio.consultarPersonajes();

			comboNombreBuscar.removeAllItems();
			for (Personaje personaje : personajes) {
				comboNombreBuscar.addItem(personaje.getNombre() + " - " + personaje.getRaza() + " - "
						+ personaje.getFuerza() + " - " + personaje.getDefensa());
			}
			if (personajes.isEmpty()) {
				JOptionPane.showMessageDialog(panel, "No se encontraron personajes con el nombre especificado.");
			}
		});

		// ---- ActionListener para cargar datos seleccionados en los campos
		comboNombreBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedValue = (String) comboNombreBuscar.getSelectedItem();
				if (selectedValue != null && selectedValue != "") {
					String[] parts = selectedValue.split(" - ");
					String nombre = parts[0];
					String fuerza = parts[2];
					String defensa = parts[3];

					txtNombreModificar.setText(nombre);
					txtNombreEliminar.setText(nombre);
					txtNuevoNombre.setText(nombre);
					txtNuevaFuerza.setText(fuerza);
					txtNuevaDefensa.setText(defensa);
				}
			}
		});

		// ActionListener para el botón de eliminación
		btnEliminar.addActionListener(e -> {
			String nombre = txtNombreEliminar.getText();
			Repositorio.eliminarPersonajePorNombre(nombre);
			JOptionPane.showMessageDialog(panel, "Personaje eliminado exitosamente.");

			txtNombreEliminar.setText("");
			txtNombreModificar.setText("");
			txtNuevoNombre.setText("");
			txtNuevaFuerza.setText("");
			txtNuevaDefensa.setText("");
		});

		// ActionListener para el botón de modificación
		btnModificar.addActionListener(e -> {
			String nombre = txtNombreModificar.getText();
			String nuevoNombre = txtNuevoNombre.getText();
			String nuevaFuerza = txtNuevaFuerza.getText();
			String nuevaDefensa = txtNuevaDefensa.getText();
			Repositorio.modificarPersonajePorNombre(nombre, nuevoNombre, nuevaFuerza, nuevaDefensa);
			JOptionPane.showMessageDialog(panel, "Personaje modificado exitosamente.");
			comboNombreBuscar.setSelectedItem("");
			txtNombreEliminar.setText("");
			txtNombreModificar.setText("");
			txtNuevoNombre.setText("");
			txtNuevaFuerza.setText("");
			txtNuevaDefensa.setText("");
		});

		return panel;
	}

	// ---- PANEL CREAR SELECCIÓN DE LUCHADORES
	public static JPanel crearSeleccionLuchadoresPanel() {
		JPanel panel = new JPanel(new GridBagLayout()) {

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Fondo_seleccion_luchadores.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		Font labelFont = new Font("Arial", Font.PLAIN, 14);
		gbc.gridx = 0;
		gbc.gridy = 0;

		JLabel personajeLabel = new JLabel("Personaje 1:");
		personajeLabel.setForeground(Color.RED);
		personajeLabel.setFont(labelFont);
		panel.add(personajeLabel, gbc);

		List<Personaje> personajes = Repositorio.consultarPersonajes();

		personajeSeleccionadoCombo = new JComboBox<>();
		personajeSeleccionadoCombo.addActionListener(e -> {
			personajeSeleccionado = (String) personajeSeleccionadoCombo.getSelectedItem();
			if (personajeSeleccionado.equals(enemigoSeleccionado)) {
				personajeSeleccionado = null;
				JOptionPane.showMessageDialog(null, "Error. Has seleccionado el mismo personaje.");
			}

			for (Personaje personaje : personajes) {
				if (personaje.getNombre().equals(personajeSeleccionado)) {
					fuerzaPersonaje = personaje.getFuerza();
					defensaPersonaje = personaje.getDefensa();
				}
			}

			actualizarEquipamientoPaneles();
		});
		gbc.gridx = 1;
		panel.add(personajeSeleccionadoCombo, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		JLabel enemigoLabel = new JLabel("Personaje 2:");
		enemigoLabel.setForeground(Color.RED);
		enemigoLabel.setFont(labelFont);
		panel.add(enemigoLabel, gbc);

		enemigoSeleccionadoCombo = new JComboBox<>();
		enemigoSeleccionadoCombo.addActionListener(e -> {
			enemigoSeleccionado = (String) enemigoSeleccionadoCombo.getSelectedItem();
			if (enemigoSeleccionado.equals(personajeSeleccionado)) {
				enemigoSeleccionado = null;
				JOptionPane.showMessageDialog(null, "Error. Has seleccionado el mismo personaje. ");
			}

			for (Personaje personaje : personajes) {
				if (personaje.getNombre().equals(enemigoSeleccionado)) {
					fuerzaEnemigo = personaje.getFuerza();
					defensaEnemigo = personaje.getDefensa();
				}
			}

			actualizarEquipamientoPaneles();
		});
		gbc.gridx = 1;
		panel.add(enemigoSeleccionadoCombo, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		JButton actualizarButton = new JButton("Actualizar Listas");
		actualizarButton.setBackground(new Color(123, 0, 0));
		actualizarButton.setForeground(Color.WHITE);
		actualizarButton.addActionListener(e -> actualizarListas());
		panel.add(actualizarButton, gbc);

		actualizarListas();

		return panel;
	}

	// ---- Método para actualizar los paneles de equipamiento con las selecciones
	// actuales
	private static void actualizarEquipamientoPaneles() {
		TitledBorder borderFactory = BorderFactory.createTitledBorder("Equipamiento para " + personajeSeleccionado);
		borderFactory.setTitleColor(Color.RED);
		equipamientoPersonajePanel.setBorder(borderFactory);

		TitledBorder borderFactory2 = BorderFactory.createTitledBorder("Equipamiento para " + enemigoSeleccionado);
		borderFactory2.setTitleColor(Color.RED);
		equipamientoEnemigoPanel.setBorder(borderFactory2);
	}

	// ---- PANEL CREAR PERSONAJE
	public static JPanel crearPersonajePanel() {

		JPanel panel = new JPanel(new GridBagLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Fondo_crear_personaje.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;

		Font labelFont = new Font("Arial", Font.PLAIN, 14);
		Font fieldFont = new Font("Arial", Font.PLAIN, 14);
		Font buttonFont = new Font("Arial", Font.BOLD, 14);

		gbc.gridx = 0;
		gbc.gridy = 1;

		// ---Nombre
		JLabel nombreLabel = new JLabel("Nombre:");
		nombreLabel.setFont(labelFont);
		nombreLabel.setForeground(Color.RED);

		panel.add(nombreLabel, gbc);

		nombrePersonajeField = new JTextField(12);
		nombrePersonajeField.setFont(fieldFont);
		gbc.gridx = 1;
		panel.add(nombrePersonajeField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;

		// ----Raza
		JLabel razaLabel = new JLabel("Raza:");
		razaLabel.setFont(labelFont);
		razaLabel.setForeground(Color.RED);

		panel.add(razaLabel, gbc);

		razaPersonajeField = new JTextField(12);
		razaPersonajeField.setFont(fieldFont);
		gbc.gridx = 1;
		panel.add(razaPersonajeField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;

		// ----Fuerza
		JLabel fuerzaLabel = new JLabel("Fuerza:");
		fuerzaLabel.setFont(labelFont);
		fuerzaLabel.setForeground(Color.RED);

		panel.add(fuerzaLabel, gbc);

		fuerzaPersonajeField = new JTextField(6);
		fuerzaPersonajeField.setFont(fieldFont);
		gbc.gridx = 1;
		panel.add(fuerzaPersonajeField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;

		// ----Defensa
		JLabel defensaLabel = new JLabel("Defensa:");
		defensaLabel.setFont(labelFont);
		defensaLabel.setForeground(Color.RED);

		panel.add(defensaLabel, gbc);

		defensaPersonajeField = new JTextField(6);
		defensaPersonajeField.setFont(fieldFont);
		gbc.gridx = 1;
		panel.add(defensaPersonajeField, gbc);

		// ---- Botón
		crearPersonajeButton = new JButton("Crear Personaje");
		crearPersonajeButton.setFont(buttonFont);
		crearPersonajeButton.setBackground(new Color(123, 0, 0));
		crearPersonajeButton.setForeground(Color.WHITE);
		crearPersonajeButton.setOpaque(true);
		crearPersonajeButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		crearPersonajeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					crearPersonaje();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		panel.add(crearPersonajeButton, gbc);

		return panel;
	}

	public static void crearPersonaje() throws SQLException {
		personajeSeleccionado = nombrePersonajeField.getText();
		String raza = razaPersonajeField.getText();
		fuerzaPersonaje = Integer.parseInt(fuerzaPersonajeField.getText());
		defensaPersonaje = Integer.parseInt(defensaPersonajeField.getText());
		Repositorio.insertarPersonaje(personajeSeleccionado, raza, fuerzaPersonaje, defensaPersonaje);
		JOptionPane.showMessageDialog(null, "Personaje creado exitosamente");
		actualizarDatos();
		actualizarTitulos();
	}

	private static void actualizarDatos() {
		tableModel.setRowCount(0); // Limpiar tabla

		Object[] personajeData = { "Personaje", personajeSeleccionado, nuevaFuerzaPersonaje, nuevaDefensaPersonaje };
		tableModel.addRow(personajeData);

		Object[] enemigoData = { "Enemigo", enemigoSeleccionado, nuevaFuerzaEnemigo, nuevaDefensaEnemigo };
		tableModel.addRow(enemigoData);
	}

	private static void actualizarTitulos() {
		equipamientoPersonajePanel
				.setBorder(BorderFactory.createTitledBorder("Equipamiento para " + personajeSeleccionado));
		equipamientoEnemigoPanel
				.setBorder(BorderFactory.createTitledBorder("Equipamiento para " + enemigoSeleccionado));
	}

	// ---- PANEL GESTIONAR EQUIPAMIENTO
	public static JPanel crearGestionarEquipamientoPanel() {
		JPanel panel = new JPanel(new GridBagLayout()) {

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Fondo_equipamiento.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};

		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;

		Font fieldFont = new Font("Arial", Font.PLAIN, 14);
		Font buttonFont = new Font("Arial", Font.BOLD, 14);

		JPanel equipamientoPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		equipamientoPanel.setOpaque(false);

		equipamientoPersonajePanel = new JPanel(new GridLayout(2, 2, 10, 10));
		TitledBorder personajeBorder = BorderFactory.createTitledBorder("Equipamiento para " + personajeSeleccionado);
		personajeBorder.setTitleColor(Color.RED);
		equipamientoPersonajePanel.setBorder(personajeBorder);
		equipamientoPersonajePanel.setOpaque(false);

		JLabel lblEquipamientoPersonaje = new JLabel("Equipamiento del Personaje 1:");
		lblEquipamientoPersonaje.setForeground(Color.RED);
		equipamientoPersonajePanel.add(lblEquipamientoPersonaje);

		equipamientoPersonajeComboBox = new JComboBox<>(Equipamiento.values());
		equipamientoPersonajeComboBox.setFont(fieldFont);
		equipamientoPersonajePanel.add(equipamientoPersonajeComboBox);

		JButton aplicarPersonajeButton = new JButton("Aplicar Equipamiento");
		aplicarPersonajeButton.setFont(buttonFont);
		aplicarPersonajeButton.setBackground(new Color(123, 0, 0));
		aplicarPersonajeButton.setForeground(Color.WHITE);
		aplicarPersonajeButton.setOpaque(true);
		aplicarPersonajeButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		aplicarPersonajeButton.addActionListener(e -> aplicarEquipamientoPersonaje());
		equipamientoPersonajePanel.add(aplicarPersonajeButton);

		equipamientoPanel.add(equipamientoPersonajePanel);

		equipamientoEnemigoPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		TitledBorder enemigoBorder = BorderFactory.createTitledBorder("Equipamiento para " + enemigoSeleccionado);
		enemigoBorder.setTitleColor(Color.RED);
		equipamientoEnemigoPanel.setBorder(enemigoBorder);
		equipamientoEnemigoPanel.setOpaque(false);

		JLabel lblEquipamientoEnemigo = new JLabel("Equipamiento del Personaje 2:");
		lblEquipamientoEnemigo.setForeground(Color.RED);
		equipamientoEnemigoPanel.add(lblEquipamientoEnemigo);

		equipamientoEnemigoComboBox = new JComboBox<>(Equipamiento.values());
		equipamientoEnemigoComboBox.setFont(fieldFont);
		equipamientoEnemigoPanel.add(equipamientoEnemigoComboBox);

		JButton aplicarEnemigoButton = new JButton("Aplicar Equipamiento");
		aplicarEnemigoButton.setFont(buttonFont);
		aplicarEnemigoButton.setBackground(new Color(123, 0, 0));
		aplicarEnemigoButton.setForeground(Color.WHITE);
		aplicarEnemigoButton.setOpaque(true);
		aplicarEnemigoButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
		aplicarEnemigoButton.addActionListener(e -> aplicarEquipamientoEnemigo());
		equipamientoEnemigoPanel.add(aplicarEnemigoButton);

		equipamientoPanel.add(equipamientoEnemigoPanel);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		panel.add(equipamientoPanel, gbc);

		return panel;
	}

	private static void aplicarEquipamientoPersonaje() {
		Equipamiento equipamientoSeleccionado = (Equipamiento) equipamientoPersonajeComboBox.getSelectedItem();
		if (equipamientoSeleccionado != null) {
			nuevaFuerzaPersonaje = fuerzaPersonaje + equipamientoSeleccionado.getFuerza();
			nuevaDefensaPersonaje = defensaPersonaje + equipamientoSeleccionado.getDefensa();
			actualizarDatos();

			JOptionPane.showMessageDialog(null, "Equipamiento aplicado a " + personajeSeleccionado + "\n"
					+ "Fuerza actual: " + nuevaFuerzaPersonaje + "\n" + "Defensa actual: " + nuevaDefensaPersonaje);
		}
	}

	private static void aplicarEquipamientoEnemigo() {
		Equipamiento equipamientoSeleccionado = (Equipamiento) equipamientoEnemigoComboBox.getSelectedItem();
		if (equipamientoSeleccionado != null) {
			nuevaFuerzaEnemigo = fuerzaEnemigo + equipamientoSeleccionado.getFuerza();
			nuevaDefensaEnemigo = defensaEnemigo + equipamientoSeleccionado.getDefensa();
			actualizarDatos();

			JOptionPane.showMessageDialog(null, "Equipamiento aplicado a " + enemigoSeleccionado + "\n"
					+ "Fuerza actual: " + nuevaFuerzaEnemigo + "\n" + "Defensa actual: " + nuevaDefensaEnemigo);
		}
	}

	// ---- PANEL BATALLA
	public static JPanel crearBatallaPanel() {

		JPanel panel = new JPanel(new GridBagLayout()) {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Fondo_batalla.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;

		JLabel characterNameLabel = new JLabel("Personaje 1");
		characterNameLabel.setForeground(Color.RED);

		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(characterNameLabel, gbc);

		JLabel enemyNameLabel = new JLabel("Personaje 2");
		enemyNameLabel.setForeground(Color.RED);

		gbc.gridx = 1;
		panel.add(enemyNameLabel, gbc);

		// ---- DADO

		// ---- Lanzar dado para Pj1
		JButton tirarDadoPj1Boton = new JButton("Lanzar dado");
		tirarDadoPj1Boton.setBackground(new Color(123, 0, 0));
		tirarDadoPj1Boton.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(tirarDadoPj1Boton, gbc);

		// ---- Lanzar dado para Pj2
		JButton tirarDadoPj2Boton = new JButton("Lanzar dado");
		tirarDadoPj2Boton.setBackground(new Color(123, 0, 0));
		tirarDadoPj2Boton.setForeground(Color.WHITE);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(tirarDadoPj2Boton, gbc);

		// ---- Botón Comenzar Batalla
		JButton batallaBoton = new JButton("¡Comenzar Batalla!");
		batallaBoton.setBackground(new Color(123, 0, 0));
		batallaBoton.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(batallaBoton, gbc);

		// ---- Cuadros de resultado de los dados
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;

		JTextField resultadoDadoPj1 = new JTextField(4);
		resultadoDadoPj1.setEditable(false);
		resultadoDadoPj1.setFont(new Font("Arial", Font.BOLD, 20));
		resultadoDadoPj1.setHorizontalAlignment(JTextField.CENTER);
		gbc.gridx = 0;
		panel.add(resultadoDadoPj1, gbc);

		JTextField resultadoDadoPj2 = new JTextField(4);
		resultadoDadoPj2.setEditable(false);
		resultadoDadoPj2.setFont(new Font("Arial", Font.BOLD, 20));
		resultadoDadoPj2.setHorizontalAlignment(JTextField.CENTER);
		gbc.gridx = 1;
		panel.add(resultadoDadoPj2, gbc);

		// Área de resultados
		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;

		JTextArea resultArea = new JTextArea(5, 20);
		resultArea.setLineWrap(true);
		resultArea.setWrapStyleWord(true);
		panel.add(new JScrollPane(resultArea), gbc);

		gbc.gridy = 6;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;

		JTextArea batallaArea = new JTextArea(5, 20);
		batallaArea.setLineWrap(true);
		batallaArea.setWrapStyleWord(true);
		panel.add(new JScrollPane(batallaArea), gbc);

		// ActionListener para el botón de lanzar dado del personaje
		tirarDadoPj1Boton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int resultadoDado = (int) (Math.random() * 20) + 1;
				resultadoDadoPj1.setText(String.valueOf(resultadoDado));
				resultArea.append(personajeSeleccionado + " lanzó un dado y obtuvo: " + resultadoDado + "\n");
				nuevaDefensaPersonaje = nuevaDefensaPersonaje + resultadoDado;
				nuevaFuerzaPersonaje = nuevaFuerzaPersonaje + resultadoDado;
			}
		});

		// ActionListener para el botón de lanzar dado del enemigo
		tirarDadoPj2Boton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int resultadoDado = (int) (Math.random() * 20) + 1;
				resultadoDadoPj2.setText(String.valueOf(resultadoDado));
				resultArea.append(enemigoSeleccionado + " lanzó un dado y obtuvo: " + resultadoDado + "\n");
				nuevaDefensaEnemigo = nuevaDefensaEnemigo + resultadoDado;
				nuevaFuerzaEnemigo = nuevaFuerzaEnemigo + resultadoDado;
			}
		});

		// ActionListener para el botón de Batalla
		batallaBoton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int ida = nuevaFuerzaPersonaje - nuevaDefensaEnemigo;
				int vuelta = nuevaFuerzaEnemigo - nuevaDefensaPersonaje;
				resultadoBatalla = 0;
				String resultado;
				if (ida > 0) {
					resultadoBatalla = resultadoBatalla + 1;
				}
				if (vuelta > 0) {
					resultadoBatalla = resultadoBatalla - 1;
				}
				if (resultadoBatalla > 0) {
					resultado = personajeSeleccionado + " gana la batalla contra " + enemigoSeleccionado + "! " + "\n";
					batallaArea.append(resultado);
				} else if (resultadoBatalla < 0) {
					resultado = enemigoSeleccionado + " gana la batalla contra " + personajeSeleccionado + "! " + "\n";
					batallaArea.append(resultado);
				} else {
					resultado = "Empate entre " + personajeSeleccionado + " y " + enemigoSeleccionado
							+ "!. Se requieren nuevas tiradas " + "\n";
					batallaArea.append(resultado);
				}

				// ---- Guardar en fichero
				FileWriter fichero = null;
				try {

					fichero = new FileWriter("batallas.txt", true);

					fichero.write(resultado + "\n");

					fichero.close();

				} catch (Exception ex) {
					System.out.println("No se ha podido crear el fichero." + ex.getMessage());
				}
			}
		});

		return panel;
	}

	// ---- PANEL GUARDAR PROGRESOS
	public static JPanel crearGuardarPanel() {

		JPanel panel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon fondo = new ImageIcon(MainGUI.class.getResource("/Iconos/Fondo_guardar.png"));
				g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		JButton mostrarBatallasButton = new JButton("Mostrar últimas batallas");
		mostrarBatallasButton.setBackground(new Color(123, 0, 0));
		mostrarBatallasButton.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panel.add(mostrarBatallasButton, gbc);

		// ---- Área de texto para mostrar las últimas batallas
		batallasTextArea = new JTextArea(10, 30);
		batallasTextArea.setEditable(false);
		batallasTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		batallasTextArea.setLineWrap(true);
		batallasTextArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(batallasTextArea);
		panel.add(scrollPane, gbc);

		JButton descargarBatallasButton = new JButton("Descargar historial de batallas");
		descargarBatallasButton.setBackground(new Color(123, 0, 0));
		descargarBatallasButton.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 0;
		gbc.weighty = 0;
		panel.add(descargarBatallasButton, gbc);

		// Acción del botón para descargar el archivo
		descargarBatallasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descargarArchivo(panel);
			}
		});

		// Acción del botón para mostrar últimas batallas
		mostrarBatallasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leerArchivo();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error al leer el archivo");
				}
			}
		});

		return panel;
	}

	// ---- DESCARGAR ARCHIVO
	public static void descargarArchivo(Component parentComponent) {
		JFileChooser fileChooser = new JFileChooser(); // ---- se usa para permitir al usuario elegir un destino donde guardar un archivo.
		fileChooser.setDialogTitle("Guardar archivo como");

		int seleccion = fileChooser.showSaveDialog(parentComponent); // ---- Configurar cuadro de diálogo para guardar archivo

		if (seleccion == JFileChooser.APPROVE_OPTION) { // ---- Si se pulsa el botón "descargar"
			File archivoDestino = fileChooser.getSelectedFile();
			copiarArchivo(new File("batallas.txt"), archivoDestino, parentComponent);
		}
	}

	private static void copiarArchivo(File archivoOrigen, File archivoDestino, Component parentComponent) {
		try (FileInputStream fis = new FileInputStream(archivoOrigen); // ---- Leer
				FileOutputStream fos = new FileOutputStream(archivoDestino)) { // ---- Escribir

			byte[] buffer = new byte[1024];
			int longitud;
			while ((longitud = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, longitud);
			}

			JOptionPane.showMessageDialog(parentComponent, "Archivo descargado con éxito.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(parentComponent, "Error al descargar el archivo: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void leerArchivo() throws IOException {
		File archivo = new File("batallas.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			StringBuilder contenido = new StringBuilder();
			String linea;
			while ((linea = br.readLine()) != null) {
				contenido.append(linea).append("\n");
			}
			batallasTextArea.setText(contenido.toString()); 
		}
	}
}
