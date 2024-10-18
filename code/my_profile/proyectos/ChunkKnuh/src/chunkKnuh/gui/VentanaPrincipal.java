package chunkKnuh.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

//paradigmas de programación I
//Reto 3- Catálogo de lego
//Alumno=Mane Isabela Velasco Naranjo
//grupo 412
//20/06/2024

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	// Menú Archivo
	private JMenu archivo;
	private JMenuItem abrir;
	private JMenuItem guardar;
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
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ChunkKnuh/imagenes/logo.png")));

		EventosDeLego eventosDeLego = new EventosDeLego();
		// Menú Archivo
		archivo = new JMenu("Archivo");
		archivo.setIcon(new ImageIcon(getClass().getResource("/ChunkKnuh/imagenes/archivo.png")));
		archivo.setMnemonic(KeyEvent.VK_A);
		archivo.setToolTipText("Menú de archivo de ChunkKnuh.");

		abrir = new JMenuItem("Abrir");
		abrir.setIcon(new ImageIcon(getClass().getResource("/ChunkKnuh/imagenes/abrir.png")));
		abrir.setMnemonic(KeyEvent.VK_R);
		// Acelerador
		abrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK));
		abrir.setToolTipText("Abrir archivos de ChunkKnuh.");
		abrir.addActionListener(eventosDeLego);

		guardar = new JMenuItem("Guardar");
		guardar.setIcon(new ImageIcon(getClass().getResource("/ChunkKnuh/imagenes/guardar.png")));
		guardar.setMnemonic(KeyEvent.VK_G);
		guardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_DOWN_MASK));
		guardar.setToolTipText("Guardar archivos de ChunkKnuh.");
		guardar.addActionListener(eventosDeLego);

		salir = new JMenuItem("Salir");
		salir.setIcon(new ImageIcon(getClass().getResource("/ChunkKnuh/imagenes/salir.png")));
		salir.setMnemonic(KeyEvent.VK_S);
		salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		salir.setToolTipText("Cerrar ChunkKnuh.");
		salir.addActionListener(eventosDeLego);

		// Menú de archivo
		archivo.add(abrir);
		archivo.add(guardar);
		archivo.addSeparator();
		archivo.add(salir);

		// Operaciones
		operaciones = new JMenu("Operaciones");
		operaciones.setIcon(new ImageIcon(getClass().getResource("/ChunkKnuh/imagenes/operaciones.png")));
		operaciones.setMnemonic(KeyEvent.VK_O);
		operaciones.setToolTipText("Menú de operaciones de ChunkKnuh.");

		// Consultar
		consultar = new JMenuItem("Consultar");
		consultar.setIcon(new ImageIcon(getClass().getResource("/ChunkKnuh/imagenes/consultar.png")));
		consultar.setMnemonic(KeyEvent.VK_U);
		consultar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
		consultar.setToolTipText("Consultar los archivo de ChunkKnuh.");
		consultar.addActionListener(eventosDeLego);

		// Catálogo
		catálogo = new JMenuItem("Catálogo");
		catálogo.setIcon(new ImageIcon(getClass().getResource("/ChunkKnuh/imagenes/catalogo.png")));
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
		ayuda.setIcon(new ImageIcon(getClass().getResource("/ChunkKnuh/imagenes/ayuda.png")));
		ayuda.setMnemonic(KeyEvent.VK_Y);
		ayuda.setToolTipText("Ayuda con ChunkKnuh.");

		acercaDe = new JMenuItem("Acerca de...");
		acercaDe.setIcon(new ImageIcon(getClass().getResource("/ChunkKnuh/imagenes/acercaDe.png")));
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

		ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/ChunkKnuh/imagenes/fondo.jpg"));
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
			} else if (e.getSource().equals(guardar)) {
				menúGuardar();
			} else if (e.getSource().equals(abrir)) {
				menúAbrir();
			}
		}
	}

	// Muestra un diálogo con el catálogo de Lego.
	private void mostrarDialogo() {
		new CatalogoDeLego(this);
	}

	// Muestra un cuadro de diálogo "Acerca de", con información del programa.
	private void menúAcercaDe() {
		JOptionPane.showMessageDialog(this,
				"ChunkKnuh" + "\n\n" + "Realizado por:" + "\nMane Isabela Velasco Naranjo" + "\n\n"
						+ "Derechos reservados UMAR " + '\u00A9' + " 2024",
				"Acerca de ChunkKnuh", JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(getClass().getResource("/legos/imagenes/logo.png")));
	}

	// Cierra la aplicación
	private void menúSalir() {

		System.exit(0);

	}

	// Muestra un cuadro de diálogo indicando que no hay elementos para consultar.
	private void menúConsultar() {
		JOptionPane.showMessageDialog(this, "No hay elementos para consultar", "Consultar",
				JOptionPane.WARNING_MESSAGE);
	}

	// Muestra un cuadro de diálogo indicando que no hay elementos para guardar.
	private void menúGuardar() {
		JOptionPane.showMessageDialog(this, "No hay elementos para guardar", "Guardar", JOptionPane.WARNING_MESSAGE);
	}

	// Muestra un cuadro de diálogo indicando que no hay elementos para abrir.
	private void menúAbrir() {
		JOptionPane.showMessageDialog(this, "No hay elementos para abrir", "Abrir", JOptionPane.WARNING_MESSAGE);
	}

}