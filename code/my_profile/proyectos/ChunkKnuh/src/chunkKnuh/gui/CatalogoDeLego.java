package chunkknuh.gui;

import java.util.ArrayList;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import chunkknuh.dominio.Lego;
import chunkknuh.base_datos.*;
import chunkknuh.excepciones.*;

import com.toedter.calendar.JDateChooser;

//paradigmas de programaci�n II
//Alumno=Mane Isabela Velasco Naranjo
//Reto 1

public class CatalogoDeLego extends JDialog implements ItemListener {

	private static final long serialVersionUID = 1L;

	private JMenu operaciones;
	private JMenuItem nuevo;
	private JMenuItem modificar;
	private JMenuItem guardar;
	private JMenuItem eliminar;
	private JMenuItem cancelar;
	private JMenuBar barraOperaciones;

	private JButton nuevoBot�n;
	private JButton modificarBot�n;
	private JButton guardarBot�n;
	private JButton eliminarBot�n;
	private JButton cancelarBot�n;

	private JComboBox<Lego> lego;
	// Variables miembro.
	private JSpinner piezas;
	private JSpinner precio;
	private JTextField nombre;
	private JTextField c�digo;
	private JDateChooser fechaPublicaci�n;
	private JComboBox<String> edadM�nima;
	private JComboBox<String> tema;

	private JCheckBox impresionesPegatina;
	private JCheckBox impresionesPl�stico;
	private JCheckBox impresionesLaser;

	private JScrollPane desplazamiento;
	private JButton agregarColorBot�n;
	private JButton quitarColorBot�n;
	private JList<String> coloresEnLista;
	private JComboBox<String> colores;
	// imagen
	private JLabel imagen;
	private String rutaImagenSelecciona;
	private JButton imagenBot�n;
	public static final String IMAGEN_DEFECTO = "/chunkknuh/imagenes/imagen.png";

	private boolean esNuevo;

	// Base de datos
	private BaseDatosLegos bd;

	public CatalogoDeLego(JFrame principal) {
		super(principal, "Cat�logo de lego", true);
		this.setIconImage(principal.getIconImage());

		operaciones = new JMenu("Operaciones");
		operaciones.setIcon(new ImageIcon(getClass().getResource("/chunkknuh/imagenes/operaciones.png")));
		operaciones.setMnemonic(KeyEvent.VK_O);

		// Nuevo
		Action operacionNuevo = new AbstractAction("Nuevo",
				new ImageIcon(getClass().getResource("/chunkknuh/imagenes/nuevo.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				operaci�nNuevo();
			}
		};
		operacionNuevo.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		operacionNuevo.putValue(Action.SHORT_DESCRIPTION, "Crea un nuevo lego para el cat�logo de lego.");
		operacionNuevo.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		nuevo = new JMenuItem(operacionNuevo);

		// Modificar
		Action operacionModificar = new AbstractAction("Modificar",
				new ImageIcon(getClass().getResource("/chunkknuh/imagenes/modificar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				operaci�nModificar();
			}
		};
		operacionModificar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_M);
		operacionModificar.putValue(Action.SHORT_DESCRIPTION, "Modifique un lego del cat�logo de lego.");
		operacionModificar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		modificar = new JMenuItem(operacionModificar);

		// Guardar
		Action operacionGuardar = new AbstractAction("Guardar",
				new ImageIcon(getClass().getResource("/chunkknuh/imagenes/guardar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				try {
					operacionGuardar();
				} catch (BaseDatosErrorException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getTitulo(), JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		operacionGuardar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_G);
		operacionGuardar.putValue(Action.SHORT_DESCRIPTION, "Guarde un lego Para cat�logo de lego.");
		operacionGuardar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		guardar = new JMenuItem(operacionGuardar);

		// Eliminar
		Action operacionEliminar = new AbstractAction("Eliminar",
				new ImageIcon(getClass().getResource("/chunkknuh/imagenes/eliminar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				try {
					operacionEliminar();
				} catch (BaseDatosErrorException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getTitulo(), JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		operacionEliminar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
		operacionEliminar.putValue(Action.SHORT_DESCRIPTION, "Elimine un lego del cat�logo de lego.");
		eliminar = new JMenuItem(operacionEliminar);

		// Cancelar
		Action operacionCancelar = new AbstractAction("Cancelar",
				new ImageIcon(getClass().getResource("/chunkknuh/imagenes/cancelar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				operacionCancelar();
			}
		};
		operacionCancelar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
		operacionCancelar.putValue(Action.SHORT_DESCRIPTION, "Cancele un lego del cat�logo de lego.");
		cancelar = new JMenuItem(operacionCancelar);

		operaciones.add(nuevo);
		operaciones.add(modificar);
		operaciones.add(guardar);
		operaciones.add(eliminar);
		operaciones.add(cancelar);

		barraOperaciones = new JMenuBar();
		barraOperaciones.add(operaciones);
		barraOperaciones.setBackground(Color.ORANGE);
		this.setJMenuBar(barraOperaciones);

		/// Creaci�n de paneles
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel();

		// Este
		JPanel panelBorderEste = new JPanel();
		panelBorderEste.setLayout(new GridLayout(5, 1, 0, 50));
		// Botones
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		nuevoBot�n = new JButton(operacionNuevo);
		nuevoBot�n.getActionMap().put("accionNuevo", operacionNuevo);
		nuevoBot�n.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) operacionNuevo.getValue(Action.ACCELERATOR_KEY), "accionNuevo");

		panel.add(nuevoBot�n);
		panelBorderEste.add(panel);

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		modificarBot�n = new JButton(operacionModificar);
		modificarBot�n.getActionMap().put("accionModificar", operacionModificar);
		modificarBot�n.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) operacionModificar.getValue(Action.ACCELERATOR_KEY), "accionModificar");

		panel.add(modificarBot�n);
		panelBorderEste.add(panel);

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		guardarBot�n = new JButton(operacionGuardar);
		guardarBot�n.getActionMap().put("accionGuardar", operacionGuardar);
		guardarBot�n.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) operacionGuardar.getValue(Action.ACCELERATOR_KEY), "accionGuardar");

		panel.add(guardarBot�n);
		panelBorderEste.add(panel);

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		eliminarBot�n = new JButton(operacionEliminar);

		panel.add(eliminarBot�n);
		panelBorderEste.add(panel);

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		cancelarBot�n = new JButton(operacionCancelar);

		panel.add(cancelarBot�n);
		panelBorderEste.add(panel);

		this.add(panelBorderEste, BorderLayout.EAST);

		// Norte
		JPanel panelBorderNorte = new JPanel();
		panelBorderNorte.setPreferredSize(new Dimension(-1, 60));
		// Entidad de lego
		panel = new JPanel();
		JLabel legoEtiqueta = new JLabel("Legos");
		legoEtiqueta.setDisplayedMnemonic(KeyEvent.VK_L);
		panel.add(legoEtiqueta);

		lego = new JComboBox<Lego>();
		lego.setPreferredSize(new Dimension(320, 20));
		legoEtiqueta.setLabelFor(lego);
		lego.addItemListener(this);
		lego.setToolTipText("lista de Legos");
		panel.add(lego);
		panelBorderNorte.add(panel);
		this.add(panelBorderNorte, BorderLayout.NORTH);

		// Oeste
		JPanel panelBorderOeste = new JPanel();
		panelBorderOeste.setLayout(new GridLayout(8, 2, 10, 0));

		// N�mero Piezas etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel piezasEtiqueta = new JLabel("1� N�mero de piezas");
		piezasEtiqueta.setDisplayedMnemonic(KeyEvent.VK_1);
		panel.add(piezasEtiqueta);
		panelBorderOeste.add(panel);

		// Precio etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel precioEtiqueta = new JLabel("2� Precio");
		precioEtiqueta.setDisplayedMnemonic(KeyEvent.VK_2);
		panel.add(precioEtiqueta);
		panelBorderOeste.add(panel);

		// N�mero Piezas
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		piezas = new JSpinner();
		piezas.setPreferredSize(new Dimension(150, 20));
		piezas.setValue(1);
		piezasEtiqueta.setLabelFor(piezas);
		piezas.setToolTipText("N�mero de piezas en el Lego que debe ser .");
		panel.add(piezas);
		panelBorderOeste.add(panel);

		// Precio
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		precio = new JSpinner();
		precio.setPreferredSize(new Dimension(150, 20));
		precioEtiqueta.setLabelFor(precio);
		precio.setToolTipText("Precio del Lego va de 240.99 a 24213.99.");
		panel.add(precio);
		panelBorderOeste.add(panel);

		// Nombre etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel nombreEtiqueta = new JLabel("3� Nombre");
		nombreEtiqueta.setDisplayedMnemonic(KeyEvent.VK_3);
		panel.add(nombreEtiqueta);
		panelBorderOeste.add(panel);

		// C�digo etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel c�digoEtiqueta = new JLabel("4� C�digo");
		c�digoEtiqueta.setDisplayedMnemonic(KeyEvent.VK_4);
		panel.add(c�digoEtiqueta);
		panelBorderOeste.add(panel);

		// Nombre
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		nombre = new JTextField();
		nombre.setPreferredSize(new Dimension(150, 20));
		nombreEtiqueta.setLabelFor(nombre);
		nombre.setToolTipText("Nombre completo del Lego.");
		panel.add(nombre);
		panelBorderOeste.add(panel);

		// C�digo
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		c�digo = new JTextField();
		c�digo.setPreferredSize(new Dimension(150, 20));
		c�digoEtiqueta.setLabelFor(c�digo);
		c�digo.setToolTipText("Una combinaci�n de 5 d�gitos num�ricos(0-9).");
		panel.add(c�digo);
		panelBorderOeste.add(panel);

		// A�o de publicaci�n etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel a�oPublicaci�nEtiqueta = new JLabel("5� A�o de publicaci�n");
		a�oPublicaci�nEtiqueta.setDisplayedMnemonic(KeyEvent.VK_5);
		panel.add(a�oPublicaci�nEtiqueta);
		panelBorderOeste.add(panel);

		// Edad m�nima etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel edadM�nimaEtiqueta = new JLabel("6� Edad m�nima");
		edadM�nimaEtiqueta.setDisplayedMnemonic(KeyEvent.VK_6);
		panel.add(edadM�nimaEtiqueta);
		panelBorderOeste.add(panel);

		// Fecha de publicaci�n
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		fechaPublicaci�n = new JDateChooser();
		fechaPublicaci�n.setPreferredSize(new Dimension(100, 20));
		a�oPublicaci�nEtiqueta.setLabelFor(fechaPublicaci�n);
		fechaPublicaci�n.getCalendarButton().setToolTipText("Fecha que se publico el Lego.");
		fechaPublicaci�n.getDateEditor().getUiComponent().setToolTipText("dd/mm/aaaa");

		panel.add(fechaPublicaci�n);
		panelBorderOeste.add(panel);

		// Edad m�nima
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		edadM�nima = new JComboBox<>();
		edadM�nima.setEditable(false);

		edadM�nima.setPreferredSize(new Dimension(100, 20));
		edadM�nimaEtiqueta.setLabelFor(edadM�nima);
		edadM�nima.setToolTipText("Rango de edad m�nimo  para el Lego.");
		panel.add(edadM�nima);
		panelBorderOeste.add(panel);

		// Tema etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel temaEtiqueta = new JLabel("7� Tema");
		temaEtiqueta.setDisplayedMnemonic(KeyEvent.VK_7);
		panel.add(temaEtiqueta);
		panelBorderOeste.add(panel);

		// Impresiones etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel impresionesEtiqueta = new JLabel("8� Impresiones");
		impresionesEtiqueta.setDisplayedMnemonic(KeyEvent.VK_8);
		panel.add(impresionesEtiqueta);
		panelBorderOeste.add(panel);

		// Tema
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		tema = new JComboBox<>();
		tema.setEditable(true);
		tema.setPreferredSize(new Dimension(150, 20));
		temaEtiqueta.setLabelFor(tema);
		tema.setToolTipText("Escoger el tema que representa el lego, se puede ingresar nuevos temas.");
		panel.add(tema);
		panelBorderOeste.add(panel);

		// Impresiones
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		impresionesPegatina = new JCheckBox("Pegatinas");
		impresionesPl�stico = new JCheckBox("Pl�stico");
		impresionesLaser = new JCheckBox("Impresi�n l�ser");
		impresionesEtiqueta.setLabelFor(impresionesPegatina);
		impresionesEtiqueta.setLabelFor(impresionesPl�stico);
		impresionesEtiqueta.setLabelFor(impresionesLaser);
		impresionesPegatina.setToolTipText("Papel sticker se pega sobre el bloque, duraci�n m�nima.");
		impresionesLaser.setToolTipText("Imagen esta grabada sobre una cara ,duraci�n media.");
		impresionesPl�stico.setToolTipText("Imagen ya est� ingresada en el bloque, duraci�n m�xima.");
		panel.add(impresionesPegatina);
		panel.add(impresionesLaser);
		panel.add(impresionesPl�stico);
		panelBorderOeste.add(panel);

		this.add(panelBorderOeste, BorderLayout.WEST);

		// Centro
		JPanel panelBorderCentro = new JPanel();
		panelBorderCentro.setLayout(new GridLayout(2, 1, 10, 10));

		JPanel panelNorte = new JPanel();
		JPanel panelCentro = new JPanel();
		JPanel panelSur = new JPanel();

		// colores
		JPanel panelColores = new JPanel();
		panelColores.setLayout(new BorderLayout());
		// Colores etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel coloresEtiqueta = new JLabel("9� Colores");
		coloresEtiqueta.setDisplayedMnemonic(KeyEvent.VK_9);
		panel.add(coloresEtiqueta);
		panelNorte.add(panel);

		// lista de colores
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		coloresEnLista = new JList<>();//FIXME volver
		desplazamiento = new JScrollPane(coloresEnLista);
		coloresEnLista.setToolTipText("Lista de colores escogidos.");
		desplazamiento.setPreferredSize(new Dimension(130, 125));
		coloresEtiqueta.setLabelFor(coloresEnLista);
		panel.add(desplazamiento);
		panelCentro.add(panel);

		// quitar color
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		Action quitarColor = new AbstractAction("Quitar",
				new ImageIcon(getClass().getResource("/chunkknuh/imagenes/quitar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				quitarColor();
			}
		};
		quitarColor.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Q);
		quitarColor.putValue(Action.SHORT_DESCRIPTION, "Quitar color a lista de colores.");
		quitarColorBot�n = new JButton(quitarColor);
		quitarColorBot�n.getActionMap().put("accionQuitarColor", quitarColor);
		quitarColorBot�n.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) operacionNuevo.getValue(Action.ACCELERATOR_KEY), "accionAgregarColor");
		quitarColorBot�n.setPreferredSize(new Dimension(100, 35));
		panel.add(quitarColorBot�n);
		panelCentro.add(panel);

		// escoger color
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.TRAILING));
		colores = new JComboBox<>();
		colores.setPreferredSize(new Dimension(130, 20));
		colores.setEditable(true);
		colores.setToolTipText("Seleccione un color o escribe uno nuevo.");
		panel.add(colores);
		panelSur.add(panel);

		// agregar color
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		Action agregarColor = new AbstractAction("Agregar",
				new ImageIcon(getClass().getResource("/chunkknuh/imagenes/agregar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				agregarColor();
			}
		};
		agregarColor.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
		agregarColor.putValue(Action.SHORT_DESCRIPTION, "Agregar un color a la lista de colores.");

		agregarColorBot�n = new JButton(agregarColor);
		agregarColorBot�n.getActionMap().put("accionAgregarColor", agregarColor);
		agregarColorBot�n.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) operacionNuevo.getValue(Action.ACCELERATOR_KEY), "accionAgregarColor");
		agregarColorBot�n.setPreferredSize(new Dimension(100, 35));
		panel.add(agregarColorBot�n);
		panelSur.add(panel);

		panelColores.add(panelNorte, BorderLayout.NORTH);
		panelColores.add(panelCentro, BorderLayout.CENTER);
		panelColores.add(panelSur, BorderLayout.SOUTH);

		// imagen
		panelNorte = new JPanel();
		panelCentro = new JPanel();
		panelSur = new JPanel();

		JPanel panelImagen = new JPanel();
		panelImagen.setLayout(new BorderLayout());
		// etiqueta imagen
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel imagenEtiqueta = new JLabel("10� Imagen");
		panel.add(imagenEtiqueta);
		panelNorte.add(panel);

		// imagen visual
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		imagen = new JLabel();
		imagenEtiqueta.setLabelFor(imagen);
		imagen.setToolTipText("Imagen de los Legos f�sicos.");
		imagen.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		imagen.setPreferredSize(new Dimension(170, 170));
		panel.add(imagen);
		panelCentro.add(panel);

		// Agregar imagen
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		Action escogerImagen = new AbstractAction("Escoger imagen",
				new ImageIcon(getClass().getResource("/chunkknuh/imagenes/escoger.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				escogerImagen();
			}
		};
		escogerImagen.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_S);
		escogerImagen.putValue(Action.SHORT_DESCRIPTION, "Selecciona una imagen del ordenador.");
		imagenBot�n = new JButton(escogerImagen);
		imagenEtiqueta.setLabelFor(imagenBot�n);
		imagenBot�n.getActionMap().put("accionEscogerImagen", escogerImagen);
		imagenBot�n.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) operacionNuevo.getValue(Action.ACCELERATOR_KEY), "accionEscogerImagen");

		panel.add(imagenBot�n);
		panelSur.add(panel);

		panelImagen.add(panelNorte, BorderLayout.NORTH);
		panelImagen.add(panelCentro, BorderLayout.CENTER);
		panelImagen.add(panelSur, BorderLayout.SOUTH);

		panelBorderCentro.add(panelColores);
		panelBorderCentro.add(panelImagen);
		this.add(panelBorderCentro, BorderLayout.CENTER);

		// Ventana
		this.setSize(new Dimension(1000, 700));
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(principal);
		try {
			bd = new BaseDatosLegos();
			inicializar();
		} catch (BaseDatosErrorException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitulo(), JOptionPane.ERROR_MESSAGE);
		}

		establecerFoco();

		this.setVisible(true);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource().equals(lego)) {
			mostrarLego();
		}
	}

	// entidad de lego
	private void mostrarLego() {
		// Obtener el objeto de la entidad que
		// est� seleccionado y mostrar� en los componentes gr�ficos los valores
		// almacenados
		Lego guardadoLego = (Lego) lego.getSelectedItem();
		int guardadoPiezas = guardadoLego.getPiezas();
		float guardadoPrecio = guardadoLego.getPrecio();
		String guardadoNombre = guardadoLego.getNombre();
		String guardadocodigo = guardadoLego.getC�digo();
		Date guardarFechaPublicaci�n = guardadoLego.getFechaPublicaci�n();
		String guardadoEdadMinima = guardadoLego.getEdadM�nima();
		// 7
		String guardadoTema = guardadoLego.getTema();
		// 8
		ArrayList<String> guardarTodasImpresiones = guardadoLego.getImpresiones();
		// 9
		ArrayList<String> guardarTodosColores = guardadoLego.getColores();

		piezas.setValue(guardadoPiezas);
		precio.setValue(guardadoPrecio);
		nombre.setText(guardadoNombre);
		c�digo.setText(guardadocodigo);
		fechaPublicaci�n.setDate(guardarFechaPublicaci�n);
		edadM�nima.setSelectedItem(guardadoEdadMinima);
		// 7
		tema.setSelectedItem(guardadoTema);
		// 8
		// Las impresiones no son obligatorias
		impresionesPl�stico.setSelected(false);
		impresionesPegatina.setSelected(false);
		impresionesLaser.setSelected(false);
		if (!guardarTodasImpresiones.isEmpty()) {
			for (String guardarImpresion : guardarTodasImpresiones) {
				if (guardarImpresion.compareToIgnoreCase(impresionesPl�stico.getText()) == 0) {
					impresionesPl�stico.setSelected(true);
				} else if (guardarImpresion.compareToIgnoreCase(impresionesPegatina.getText()) == 0) {
					impresionesPegatina.setSelected(true);
				} else {
					impresionesLaser.setSelected(true);
				}
			}
		}
		// 9
		DefaultListModel<String> modeloColores = (DefaultListModel<String>) coloresEnLista.getModel();
		modeloColores.clear();
		for (String guardarColor : guardarTodosColores) {
			modeloColores.addElement(guardarColor);
		}
		// 10
		rutaImagenSelecciona = guardadoLego.getImagen();
		muestraImagen();
	}

	// cancelara el ingreso y modificacion del arreglo de lego
	private void operacionCancelar() {
		limpiarCampos();
		habilitarCampos(false);

		// botones y men�s
		// habilitar�
		nuevoBot�n.setEnabled(true);
		nuevo.setEnabled(true);
		// deshabilitar�
		guardarBot�n.setEnabled(false);
		guardar.setEnabled(false);

		cancelarBot�n.setEnabled(false);
		cancelar.setEnabled(false);

		verificarLista();
	}

	// eliminara el arreglo de lego.
	private void operacionEliminar() throws BaseDatosErrorException {
		int confirmaci�n = JOptionPane.showConfirmDialog(this, "�Quiere eliminar el lego seleccionado?",
				"Eliminar lego", JOptionPane.YES_NO_OPTION);

		if (confirmaci�n == JOptionPane.YES_OPTION) {
			// lego a eliminar
			Lego legoEliminado = (Lego) lego.getSelectedItem();

			// eliminar la imagen
			String rutaImagenEliminar = ((Lego) legoEliminado).getImagen();
			if (!(rutaImagenEliminar.equals(IMAGEN_DEFECTO))) {
				File imagenEliminarRuta = new File(rutaImagenEliminar);
				imagenEliminarRuta.delete();
			}
			lego.removeItemListener(this);
			bd.guardarLegoBD(legoEliminado, "eliminar");
			lego.removeItem(legoEliminado);

			JOptionPane.showMessageDialog(this, "Lego " + legoEliminado + " fue eliminado exitosamente",
					"Eliminaci�n exitosa", JOptionPane.INFORMATION_MESSAGE);

			lego.addItemListener(this);
			operacionCancelar();
		}
	}

	// guardara los legos en el arreglo de lego.
	private void operacionGuardar() throws BaseDatosErrorException {
		try {
			Lego nuevoLego;

			// nuevo o modificaci�n del arreglo de lego
			if (esNuevo) {
				nuevoLego = new Lego();// nuevo
			} else {
				nuevoLego = (Lego) lego.getSelectedItem();// modificaci�n
			}

			// 1
			String nuevasPiezas = String.valueOf(piezas.getValue());
			nuevoLego.setPiezas(nuevasPiezas);

			// 2
			String nuevoPrecio = String.valueOf(precio.getValue());
			nuevoLego.setPrecio(nuevoPrecio);

			// 3
			String nuevoNombre = nombre.getText();
			nuevoLego.setNombre(nuevoNombre);

			// 4
			String nuevocodigo = c�digo.getText();
			nuevoLego.setC�digo(nuevocodigo);

			// 5
			Date nuevoFechaPublicaci�n = fechaPublicaci�n.getDate();
			nuevoLego.setFechaPublicaci�n(nuevoFechaPublicaci�n);

			// 6
			String nuevaEdadMinima = ((String) edadM�nima.getSelectedItem()).trim();
			nuevoLego.setEdadM�nima(nuevaEdadMinima);

			// 7
			String nuevoTema = ((String) tema.getSelectedItem()).trim();
			boolean temaExiste = false;
			if (!(nuevoTema.isEmpty())) {
				if (-1 == tema.getSelectedIndex()) {
					for (int i = 0; i < tema.getItemCount(); i++) {
						if (nuevoTema.equalsIgnoreCase(tema.getItemAt(i))) {
							temaExiste = true;
							tema.setSelectedIndex(i);
							nuevoTema = tema.getItemAt(i);
							break;
						}
					}
					if (!temaExiste) {
						tema.addItem(nuevoTema);
						tema.setSelectedItem(nuevoTema);
					}

				}
			} else {
				return;
			}
			nuevoLego.setTema(nuevoTema);

			// 8
			ArrayList<String> nuevasImpresiones = new ArrayList<>();
			if (impresionesLaser.isSelected()) {
				nuevasImpresiones.add(impresionesLaser.getText());
			}
			if (impresionesPegatina.isSelected()) {
				nuevasImpresiones.add(impresionesPegatina.getText());
			}
			if (impresionesPl�stico.isSelected()) {
				nuevasImpresiones.add(impresionesPl�stico.getText());
			}
			nuevoLego.setImpresiones(nuevasImpresiones);

			// 9
			ArrayList<String> nuevosColores = new ArrayList<>();
			for (int i = 0; i < coloresEnLista.getModel().getSize(); i++) {
				nuevosColores.add(coloresEnLista.getModel().getElementAt(i));
			}
			nuevoLego.setColores(nuevosColores);

			// 10
			String nuevaRutaImagenSelecciona;

			// crear carpeta imagen si no exite
			File carpetaImagen = new File("imagen");
			if (!(carpetaImagen.exists())) {
				carpetaImagen.mkdir();
			}
			// nombre del archivo
			if (!(rutaImagenSelecciona.equals(IMAGEN_DEFECTO))) {
				nuevaRutaImagenSelecciona = rutaImagenSelecciona;
			} else {
				nuevaRutaImagenSelecciona = IMAGEN_DEFECTO;
			}
			nuevoLego.setImagen(nuevaRutaImagenSelecciona);

			// mensajes
			if (esNuevo) {
				// nuevo lego
				lego.addItem(nuevoLego);
				bd.guardarLegoBD(nuevoLego, "nuevo");
				JOptionPane.showMessageDialog(this, "Legos guardado exitosamente.", "Guardado",
						JOptionPane.INFORMATION_MESSAGE);
				llenarTemas();
				llenarLegos();
			} else {
				// modificado lego
				bd.guardarLegoBD(nuevoLego, "modificar");
				JOptionPane.showMessageDialog(this, "Legos modificados exitosamente.", "Modificado",
						JOptionPane.INFORMATION_MESSAGE);
			}

			operacionCancelar();
		} catch (LegoAdvertenciasException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getTitulo(), JOptionPane.WARNING_MESSAGE);
		}
	}

	// crea un nuevo archivo.
	private void operaci�nNuevo() {
		habilitarCampos(true);
		limpiarCampos();

		// botones y men�s
		// habilitar�
		guardarBot�n.setEnabled(true);
		guardar.setEnabled(true);

		cancelarBot�n.setEnabled(true);
		cancelar.setEnabled(true);
		// deshabilitar�
		modificarBot�n.setEnabled(false);
		modificar.setEnabled(false);

		eliminarBot�n.setEnabled(false);
		eliminar.setEnabled(false);

		nuevoBot�n.setEnabled(false);
		nuevo.setEnabled(false);
		// lista desplegable principal
		lego.setEnabled(false);

		esNuevo = true;
	}

	// modifica un archivo guardado.
	private void operaci�nModificar() {
		esNuevo = false;
		habilitarCampos(true);

		// botones y men�s
		// habilitar�
		guardarBot�n.setEnabled(true);
		guardar.setEnabled(true);

		cancelarBot�n.setEnabled(true);
		cancelar.setEnabled(true);
		// deshabilitar�
		modificarBot�n.setEnabled(false);
		modificar.setEnabled(false);

		eliminarBot�n.setEnabled(false);
		eliminar.setEnabled(false);

		nuevoBot�n.setEnabled(false);
		nuevo.setEnabled(false);
		// lista desplegable principal
		lego.setEnabled(false);
	}

	// Quitara un color
	private void quitarColor() {
		int colorSeleccionado = coloresEnLista.getSelectedIndex();
		if (colorSeleccionado != -1) {
			((DefaultListModel<String>) coloresEnLista.getModel()).remove(colorSeleccionado);
		} else {
			JOptionPane.showMessageDialog(this, "Seleccione el color que se quitara de la lista.",
					"Color no seleccionado", JOptionPane.WARNING_MESSAGE);
		}
	}

	// Agregara un color.
	private void agregarColor() {
		String colorAgregado = ((String) colores.getSelectedItem()).trim();
		boolean colorExiste = false;
		boolean colorExisteEnLista = false;
		if (!(colorAgregado.isEmpty())) {
			// JComboBox
			if (-1 == colores.getSelectedIndex()) {
				for (int i = 0; i < colores.getItemCount(); i++) {
					if (colorAgregado.equalsIgnoreCase(colores.getItemAt(i))) {
						colores.setSelectedIndex(i);
						colorExiste = true;
						colorAgregado = colores.getItemAt(i);
						break;
					}
				}

				if (!colorExiste) {
					try {
						bd.agregarColorBD(colorAgregado);
					} catch (BaseDatosErrorException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitulo(), JOptionPane.ERROR_MESSAGE);
					}
					colores.addItem(colorAgregado);
					colores.setSelectedItem(colorAgregado);
				}
			}
			// Jlist
			for (int i = 0; i < coloresEnLista.getModel().getSize(); i++) {
				if (colorAgregado.equalsIgnoreCase(coloresEnLista.getModel().getElementAt(i))) {
					colorExisteEnLista = true;
					break;
				}
			}
			if (!colorExisteEnLista) {
				((DefaultListModel<String>) coloresEnLista.getModel()).addElement(colorAgregado);
			} else {
				JOptionPane.showMessageDialog(this,
						"El color que desea agregar ya existe en la lista. No se permiten duplicados.",
						"Color duplicado", JOptionPane.WARNING_MESSAGE);
			}
		}

		llenarColores();
	}

	// Escoge una imagen.
	private void escogerImagen() {
		// JOptionPane.showMessageDialog(this, "Escoger imagen");
		// di�logo para abrir un archivo
		JFileChooser chooser = new JFileChooser();
		// Titulo
		chooser.setDialogTitle("Seleccione una imagen para lego");
		// seleccionar solo archivos
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// filtros
		FileNameExtensionFilter filtroJPG = new FileNameExtensionFilter("formato de archivo de imagen", "jpg");
		FileNameExtensionFilter filtroPNG = new FileNameExtensionFilter("Formato gr�fico de compresi�n de im�genes",
				"png");
		FileNameExtensionFilter filtroGIF = new FileNameExtensionFilter("Formato de intercambio de gr�ficos", "gif");

		chooser.setFileFilter(filtroJPG);
		chooser.addChoosableFileFilter(filtroPNG);
		chooser.addChoosableFileFilter(filtroGIF);
		// No permitir cualquier tipo de archivo.
		chooser.setAcceptAllFileFilterUsed(false);
		// directorio del usuario.
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		// No permitirr selecci�n m�ltiple
		chooser.setMultiSelectionEnabled(false);
		// el bot�n �Aceptar�, con texto, mnem�nico y ayuda propia.
		chooser.setApproveButtonText("Aceptar");
		chooser.setApproveButtonToolTipText("Escoge un archivo de imagen jpg,png y gif de lego");

		int opcion = chooser.showOpenDialog(this);
		if (opcion == JFileChooser.APPROVE_OPTION) {
			File archivo = chooser.getSelectedFile();
			if (archivo.exists()) {
				rutaImagenSelecciona = archivo.getAbsolutePath();
				muestraImagen();
			} else {
				JOptionPane.showMessageDialog(this, "Escribe un nombre de imagen que si exista.", "Imagen no existe",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	// muestra la imagen que se escogio
	private void muestraImagen() {
		// imagen de la ruta temporal de la imagen seleccionada.
		ImageIcon iconoLegoOriginal;
		if (rutaImagenSelecciona.equals(IMAGEN_DEFECTO)) {
			iconoLegoOriginal = new ImageIcon(getClass().getResource(rutaImagenSelecciona));
		} else {
			iconoLegoOriginal = new ImageIcon(rutaImagenSelecciona);
		}
		Image imagenOriginal = iconoLegoOriginal.getImage();
		// escala con la dimensi�nm�s grande de la imagen
		// completa y sin distorsi�n
		Image imagenEscalada;
		if (imagenOriginal.getWidth(null) > imagenOriginal.getHeight(null)) {
			imagenEscalada = imagenOriginal.getScaledInstance(170, -1, Image.SCALE_SMOOTH);
		} else {
			imagenEscalada = imagenOriginal.getScaledInstance(-1, 170, Image.SCALE_SMOOTH);
		}
		// aplica escala

		imagen.setHorizontalAlignment(JLabel.CENTER);
		imagen.setVerticalAlignment(JLabel.CENTER);
		imagen.setIcon(new ImageIcon(imagenEscalada));

	}

	// limpiar� el contenido de los campos de texto del di�logo
	private void limpiarCampos() {
		// 1. N�mero libre.
		piezas.setValue(1);
		// 2. N�mero con rango.
		precio.setValue(240.99);
		// 3. Texto en formato libre.
		nombre.setText("");
		// 4. Texto con formato predefinido.
		c�digo.setText("");
		// 5. Fecha
		fechaPublicaci�n.setDate(new Date());
		// Opciones fijas
		edadM�nima.setSelectedIndex(0);
		// 7. Dato obtenido de opciones mutuamente excluyentes din�micas.
		tema.setSelectedIndex(0);
		// 8. Dato multivalorado de opciones no excluyentes fijas.
		impresionesLaser.setSelected(false);
		impresionesPegatina.setSelected(false);
		impresionesPl�stico.setSelected(false);
		// 9. Dato multivalorado de opciones no excluyentes din�micas.
		colores.setSelectedIndex(0);
		// 10 imagen
		rutaImagenSelecciona = IMAGEN_DEFECTO;
		muestraImagen();
		// limpiar el modelo de la lista del campo
		// de opciones no excluyentes din�mico.
		((DefaultListModel<String>) coloresEnLista.getModel()).clear();
	}

	// habilitar� o deshabilitar� la escritura de los campos de texto y habilitar� o
	// deshabilitar� al resto de los
	// componentes gr�ficos del �rea de trabajo,
	private void habilitarCampos(boolean escritura) {
		// JSpinner
		piezas.setEnabled(escritura);
		precio.setEnabled(escritura);
		// JTextField
		nombre.setEditable(escritura);
		c�digo.setEditable(escritura);
		fechaPublicaci�n.setEnabled(escritura);
		// JComboBox<String>
		edadM�nima.setEnabled(escritura);
		tema.setEnabled(escritura);
		colores.setEnabled(escritura);
		// JCheckBox
		impresionesPegatina.setEnabled(escritura);
		impresionesPl�stico.setEnabled(escritura);
		impresionesLaser.setEnabled(escritura);
		// JButton
		agregarColorBot�n.setEnabled(escritura);
		quitarColorBot�n.setEnabled(escritura);
		imagenBot�n.setEnabled(escritura);
		// Jscroll
		desplazamiento.setEnabled(escritura);

	}

	// verificar� si la lista desplegable principal tiene elementos. Si los
	// tiene,
	// habilitar� a los botones y men�s �Modificar� y
	// �Eliminar�,
	private void verificarLista() {
		boolean verificador = this.lego.getItemCount() > 0;
		// lista principal
		lego.setEnabled(verificador);
		// botones
		modificarBot�n.setEnabled(verificador);
		modificar.setEnabled(verificador);
		eliminarBot�n.setEnabled(verificador);
		eliminar.setEnabled(verificador);

		if (verificador) {
			lego.setSelectedIndex(0);
			mostrarLego();
		}
	}

	private void llenarColores() {
		colores.removeItemListener(this);
		colores.removeAllItems();
		try {
			ArrayList<String> coloresConsulta = new ArrayList<String>(bd.consultarColores());
			for (String colorItem : coloresConsulta) {
				colores.addItem(colorItem);
			}
			colores.addItemListener(this);
		} catch (BaseDatosErrorException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitulo(), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void llenarTemas() {
		tema.removeItemListener(this);
		tema.removeAllItems();
		try {
			ArrayList<String> temasConsulta = new ArrayList<String>(bd.consultarTemas());
			for (String temaItem : temasConsulta) {
				tema.addItem(temaItem);
			}
			tema.addItemListener(this);
		} catch (BaseDatosErrorException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitulo(), JOptionPane.ERROR_MESSAGE);
		}

	}

	private void llenarLegos() {
		lego.removeItemListener(this);
		lego.removeAllItems();
		try {
			ArrayList<Lego> listaLegoBD = bd.consultarLegos();
			for (Lego legoItem : listaLegoBD) {
				lego.addItem(legoItem);
			}
			lego.addItemListener(this);
		} catch (BaseDatosErrorException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getTitulo(), JOptionPane.ERROR_MESSAGE);
		} catch (LegoAdvertenciasException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), e2.getTitulo(), JOptionPane.WARNING_MESSAGE);
		}
	}

	// Inicia el di�logo
	private void inicializar() {
		// elementos gr�ficos
		habilitarCampos(false);
		// piezas
		piezas.setModel(new SpinnerNumberModel(1, 1, null, 1));
		// precio
		precio.setModel(new SpinnerNumberModel(240.99, 240.99, 24213.99, 1));
		// 7 y 9 temporales, en pr�ximas pr�cticas se borrar�n.
		// 6. Dato obtenido de opciones mutuamente excluyentes fijas.
		edadM�nima.addItem("1.5+");
		edadM�nima.addItem("4+");
		edadM�nima.addItem("6+");
		edadM�nima.addItem("9+");
		edadM�nima.addItem("13+");
		edadM�nima.addItem("18+");
		// Las listas desplegables auxiliares.
		llenarTemas();
		coloresEnLista.setModel(new DefaultListModel<>());
		llenarColores();
		// lista desplegable principal
		llenarLegos();

		// botones y men�s
		nuevoBot�n.setEnabled(true);
		nuevo.setEnabled(true);

		guardarBot�n.setEnabled(false);
		guardar.setEnabled(false);

		cancelarBot�n.setEnabled(false);
		cancelar.setEnabled(false);
		// lista
		verificarLista();
	}

	// Establece el orden de enfoque entre los componentes de la interfaz.
	private void establecerFoco() {
		Vector<Component> componentes = new Vector<>();
		componentes.add(nuevoBot�n);
		componentes.add(modificarBot�n);
		componentes.add(guardarBot�n);
		componentes.add(eliminarBot�n);
		componentes.add(cancelarBot�n);
		componentes.add(lego);
		componentes.add(piezas);
		componentes.add(precio);
		componentes.add(nombre);
		componentes.add(c�digo);
		componentes.add(fechaPublicaci�n);
		componentes.add(edadM�nima);
		componentes.add(impresionesPegatina);
		componentes.add(impresionesPl�stico);
		componentes.add(impresionesLaser);
		componentes.add(agregarColorBot�n);
		componentes.add(quitarColorBot�n);
		componentes.add(imagenBot�n);
		chunkknuh.utilerias.MiFocusTraversalPolicy politicaFoco = new chunkknuh.utilerias.MiFocusTraversalPolicy(
				componentes);
		this.setFocusTraversalPolicy(politicaFoco);
	}
}