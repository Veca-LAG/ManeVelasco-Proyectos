package chunkknuh.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import chunkknuh.excepciones.BaseDatosErrorException;
import chunkknuh.utilerias.GenerarReporteLego;

//paradigmas de programación II
//Alumno=Mane Isabela Velasco Naranjo
//Reto 1

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	// Menú Archivo
	private JMenu archivo;
	private JMenuItem generarReporte;
	private JMenuItem salir;
	// Menú Operaciones
	private JMenu operaciones;
	private JMenuItem catálogo;
	private JMenuItem consultar;
	// Menú Ayuda
	private JMenu ayuda;
	private JMenuItem acercaDe;

	private JMenuBar barraMenu;

	public VentanaPrincipal() {
		// Configuración de pantalla
		super("ChunkKnuh");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/chunkknuh/imagenes/logo.png")));

		EventosDeLego eventosDeLego = new EventosDeLego();
		// Menú Archivo
		archivo = new JMenu("Archivo");
		archivo.setIcon(new ImageIcon(getClass().getResource("/chunkknuh/imagenes/archivo.png")));
		archivo.setMnemonic(KeyEvent.VK_A);
		archivo.setToolTipText("Menú de legos de ChunkKnuh.");

		// Generar reporte
		generarReporte = new JMenuItem("Generar reporte");
		generarReporte.setIcon(new ImageIcon(getClass().getResource("/chunkknuh/imagenes/generarReporte.png")));
		generarReporte.setMnemonic(KeyEvent.VK_G);
		generarReporte.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_DOWN_MASK));
		generarReporte.setToolTipText("Generar reporte de legos de ChunkKnuh.");
		generarReporte.addActionListener(eventosDeLego);

		salir = new JMenuItem("Salir");
		salir.setIcon(new ImageIcon(getClass().getResource("/chunkknuh/imagenes/salir.png")));
		salir.setMnemonic(KeyEvent.VK_S);
		salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		salir.setToolTipText("Cerrar ChunkKnuh.");
		salir.addActionListener(eventosDeLego);

		// Menú de archivo
		archivo.add(generarReporte);
		archivo.addSeparator();
		archivo.add(salir);

		// Operaciones
		operaciones = new JMenu("Operaciones");
		operaciones.setIcon(new ImageIcon(getClass().getResource("/chunkknuh/imagenes/operaciones.png")));
		operaciones.setMnemonic(KeyEvent.VK_O);
		operaciones.setToolTipText("Menú de operaciones de ChunkKnuh.");

		// Consultar
		consultar = new JMenuItem("Consultar");
		consultar.setIcon(new ImageIcon(getClass().getResource("/chunkknuh/imagenes/consultar.png")));
		consultar.setMnemonic(KeyEvent.VK_U);
		consultar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
		consultar.setToolTipText("Consultar legos de ChunkKnuh.");
		consultar.addActionListener(eventosDeLego);

		// Catálogo
		catálogo = new JMenuItem("Catálogo");
		catálogo.setIcon(new ImageIcon(getClass().getResource("/chunkknuh/imagenes/catalogo.png")));
		catálogo.setMnemonic(KeyEvent.VK_C);
		catálogo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
		catálogo.addActionListener(eventosDeLego);
		catálogo.setToolTipText("Ventana del catálogo de ChunkKnuh.");

		// Menú de operaciones
		operaciones.add(catálogo);
		operaciones.addSeparator();
		operaciones.add(consultar);

		// Ayuda
		ayuda = new JMenu("Ayuda");
		ayuda.setIcon(new ImageIcon(getClass().getResource("/chunkknuh/imagenes/ayuda.png")));
		ayuda.setMnemonic(KeyEvent.VK_Y);
		ayuda.setToolTipText("Ayuda con ChunkKnuh.");

		acercaDe = new JMenuItem("Acerca de...");
		acercaDe.setIcon(new ImageIcon(getClass().getResource("/chunkknuh/imagenes/acercaDe.png")));
		acercaDe.setMnemonic(KeyEvent.VK_E);
		acercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK));
		acercaDe.setToolTipText("Muestra los créditos de ChunkKnuh.");
		acercaDe.addActionListener(eventosDeLego);

		// Menú de ayuda
		ayuda.add(acercaDe);

		// Barra de menú
		barraMenu = new JMenuBar();
		barraMenu.add(archivo);
		barraMenu.add(operaciones);
		barraMenu.add(ayuda);
		barraMenu.setBackground(Color.ORANGE);
		this.setJMenuBar(barraMenu);
		this.getContentPane().setLayout(new FlowLayout());

		// Ventana
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setResizable(false);

		this.getContentPane().setLayout(new FlowLayout());
		JLabel fondo = new JLabel();

		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/chunkknuh/imagenes/fondo.jpg"));
		Image imagenEscalada = imagenFondo.getImage().getScaledInstance(
				Toolkit.getDefaultToolkit().getScreenSize().width - 100,
				Toolkit.getDefaultToolkit().getScreenSize().height - 100, Image.SCALE_SMOOTH);
		fondo.setIcon(new ImageIcon(imagenEscalada));
		this.getContentPane().add(fondo);
		getContentPane().setBackground(Color.WHITE);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// Implementar el evento WindowListener en la ventana principal utilizando una
		// clase anónima
		addWindowListener(new WindowAdapter() {
			@Override
			// Solo implementar el método windowClosing
			public void windowClosing(WindowEvent e) {
				menúSalir();
			}
		});

		setVisible(true);

	}

	// Implementar el evento ActionListener utilizando una clase interna.
	// clase privada
	private class EventosDeLego implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(salir)) {
				menúSalir();
			} else if (e.getSource().equals(acercaDe)) {
				menúAcercaDe();
			} else if (e.getSource().equals(catálogo)) {
				mostrarDialogo();
			} else if (e.getSource().equals(consultar)) {
				menúConsultar();
			} else if (e.getSource().equals(generarReporte)) {
				menúGenerarReporte();
			}
		}
	}

	// Muestra un diálogo con el catálogo de Lego.
	private void mostrarDialogo() {
		new CatalogoDeLego(this);
	}

	// Muestra un cuadro de diálogo "Acerca de", con información del programa.
	private void menúAcercaDe() {
		JOptionPane.showMessageDialog(this, "1. Generar reporte PDF\n	Archivo > Generar reporte o Alt+G\n"
				+ "	- Genera un reporte PDF con los datos:\n"
				+ "	   código, nombre del lego, tema, precio, piezas y edad mínima.\n"
				+ "      El reporte incluye todos los legos de la base de datos y se guarda automáticamente en la ruta seleccionada.\n\n"
				+ "2. Catálogo de Legos\n" + "	Operaciones > Catálogo o Alt+C\n"
				+ "	- Permite guardar, modificar y eliminar datos de legos en la base de datos.\n\n"
				+ "3. Consulta de Legos\n" + "	Operaciones > Consultar o Alt+U\n"
				+ "	- Busca un lego en la base de datos mediante cero o siete criterios." + "\n\n" + "Realizado por:"
				+ "\nMane Isabela Velasco Naranjo" + "\n\n" + "Derechos reservados UMAR " + '\u00A9' + " 2024",
				"Acerca de ChunkKnuh", JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(getClass().getResource("/chunkknuh/imagenes/logo.png")));
	}

	// Cierra la aplicación
	private void menúSalir() {
		System.exit(0);
	}

	// Consulta datos de la base de datos
	private void menúConsultar() {
		try {
			new ConsultaLegos(this);
		} catch (BaseDatosErrorException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitulo(), JOptionPane.ERROR_MESSAGE);
		}
	}

	// Genera un reporte de todos los legos de la base de datos.
	private void menúGenerarReporte() {
		new GenerarReporteLego();
	}

}