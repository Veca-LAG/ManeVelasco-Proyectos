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

//paradigmas de programación II
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

	private JButton nuevoBotón;
	private JButton modificarBotón;
	private JButton guardarBotón;
	private JButton eliminarBotón;
	private JButton cancelarBotón;

	private JComboBox<Lego> lego;
	// Variables miembro.
	private JSpinner piezas;
	private JSpinner precio;
	private JTextField nombre;
	private JTextField código;
	private JDateChooser fechaPublicación;
	private JComboBox<String> edadMínima;
	private JComboBox<String> tema;

	private JCheckBox impresionesPegatina;
	private JCheckBox impresionesPlástico;
	private JCheckBox impresionesLaser;

	private JScrollPane desplazamiento;
	private JButton agregarColorBotón;
	private JButton quitarColorBotón;
	private JList<String> coloresEnLista;
	private JComboBox<String> colores;
	// imagen
	private JLabel imagen;
	private String rutaImagenSelecciona;
	private JButton imagenBotón;
	public static final String IMAGEN_DEFECTO = "/chunkknuh/imagenes/imagen.png";

	private boolean esNuevo;

	// Base de datos
	private BaseDatosLegos bd;

	public CatalogoDeLego(JFrame principal) {
		super(principal, "Catálogo de lego", true);
		this.setIconImage(principal.getIconImage());

		operaciones = new JMenu("Operaciones");
		operaciones.setIcon(new ImageIcon(getClass().getResource("/chunkknuh/imagenes/operaciones.png")));
		operaciones.setMnemonic(KeyEvent.VK_O);

		// Nuevo
		Action operacionNuevo = new AbstractAction("Nuevo",
				new ImageIcon(getClass().getResource("/chunkknuh/imagenes/nuevo.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				operaciónNuevo();
			}
		};
		operacionNuevo.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
		operacionNuevo.putValue(Action.SHORT_DESCRIPTION, "Crea un nuevo lego para el catálogo de lego.");
		operacionNuevo.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		nuevo = new JMenuItem(operacionNuevo);

		// Modificar
		Action operacionModificar = new AbstractAction("Modificar",
				new ImageIcon(getClass().getResource("/chunkknuh/imagenes/modificar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				operaciónModificar();
			}
		};
		operacionModificar.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_M);
		operacionModificar.putValue(Action.SHORT_DESCRIPTION, "Modifique un lego del catálogo de lego.");
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
		operacionGuardar.putValue(Action.SHORT_DESCRIPTION, "Guarde un lego Para catálogo de lego.");
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
		operacionEliminar.putValue(Action.SHORT_DESCRIPTION, "Elimine un lego del catálogo de lego.");
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
		operacionCancelar.putValue(Action.SHORT_DESCRIPTION, "Cancele un lego del catálogo de lego.");
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

		/// Creación de paneles
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel();

		// Este
		JPanel panelBorderEste = new JPanel();
		panelBorderEste.setLayout(new GridLayout(5, 1, 0, 50));
		// Botones
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		nuevoBotón = new JButton(operacionNuevo);
		nuevoBotón.getActionMap().put("accionNuevo", operacionNuevo);
		nuevoBotón.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) operacionNuevo.getValue(Action.ACCELERATOR_KEY), "accionNuevo");

		panel.add(nuevoBotón);
		panelBorderEste.add(panel);

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		modificarBotón = new JButton(operacionModificar);
		modificarBotón.getActionMap().put("accionModificar", operacionModificar);
		modificarBotón.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) operacionModificar.getValue(Action.ACCELERATOR_KEY), "accionModificar");

		panel.add(modificarBotón);
		panelBorderEste.add(panel);

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		guardarBotón = new JButton(operacionGuardar);
		guardarBotón.getActionMap().put("accionGuardar", operacionGuardar);
		guardarBotón.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) operacionGuardar.getValue(Action.ACCELERATOR_KEY), "accionGuardar");

		panel.add(guardarBotón);
		panelBorderEste.add(panel);

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		eliminarBotón = new JButton(operacionEliminar);

		panel.add(eliminarBotón);
		panelBorderEste.add(panel);

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		cancelarBotón = new JButton(operacionCancelar);

		panel.add(cancelarBotón);
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

		// Número Piezas etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel piezasEtiqueta = new JLabel("1° Número de piezas");
		piezasEtiqueta.setDisplayedMnemonic(KeyEvent.VK_1);
		panel.add(piezasEtiqueta);
		panelBorderOeste.add(panel);

		// Precio etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel precioEtiqueta = new JLabel("2° Precio");
		precioEtiqueta.setDisplayedMnemonic(KeyEvent.VK_2);
		panel.add(precioEtiqueta);
		panelBorderOeste.add(panel);

		// Número Piezas
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		piezas = new JSpinner();
		piezas.setPreferredSize(new Dimension(150, 20));
		piezas.setValue(1);
		piezasEtiqueta.setLabelFor(piezas);
		piezas.setToolTipText("Número de piezas en el Lego que debe ser .");
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
		JLabel nombreEtiqueta = new JLabel("3° Nombre");
		nombreEtiqueta.setDisplayedMnemonic(KeyEvent.VK_3);
		panel.add(nombreEtiqueta);
		panelBorderOeste.add(panel);

		// Código etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel códigoEtiqueta = new JLabel("4° Código");
		códigoEtiqueta.setDisplayedMnemonic(KeyEvent.VK_4);
		panel.add(códigoEtiqueta);
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

		// Código
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		código = new JTextField();
		código.setPreferredSize(new Dimension(150, 20));
		códigoEtiqueta.setLabelFor(código);
		código.setToolTipText("Una combinación de 5 dígitos numéricos(0-9).");
		panel.add(código);
		panelBorderOeste.add(panel);

		// Año de publicación etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel añoPublicaciónEtiqueta = new JLabel("5° Año de publicación");
		añoPublicaciónEtiqueta.setDisplayedMnemonic(KeyEvent.VK_5);
		panel.add(añoPublicaciónEtiqueta);
		panelBorderOeste.add(panel);

		// Edad mínima etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel edadMínimaEtiqueta = new JLabel("6° Edad mínima");
		edadMínimaEtiqueta.setDisplayedMnemonic(KeyEvent.VK_6);
		panel.add(edadMínimaEtiqueta);
		panelBorderOeste.add(panel);

		// Fecha de publicación
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		fechaPublicación = new JDateChooser();
		fechaPublicación.setPreferredSize(new Dimension(100, 20));
		añoPublicaciónEtiqueta.setLabelFor(fechaPublicación);
		fechaPublicación.getCalendarButton().setToolTipText("Fecha que se publico el Lego.");
		fechaPublicación.getDateEditor().getUiComponent().setToolTipText("dd/mm/aaaa");

		panel.add(fechaPublicación);
		panelBorderOeste.add(panel);

		// Edad mínima
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		edadMínima = new JComboBox<>();
		edadMínima.setEditable(false);

		edadMínima.setPreferredSize(new Dimension(100, 20));
		edadMínimaEtiqueta.setLabelFor(edadMínima);
		edadMínima.setToolTipText("Rango de edad mínimo  para el Lego.");
		panel.add(edadMínima);
		panelBorderOeste.add(panel);

		// Tema etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel temaEtiqueta = new JLabel("7° Tema");
		temaEtiqueta.setDisplayedMnemonic(KeyEvent.VK_7);
		panel.add(temaEtiqueta);
		panelBorderOeste.add(panel);

		// Impresiones etiqueta
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel impresionesEtiqueta = new JLabel("8° Impresiones");
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
		impresionesPlástico = new JCheckBox("Plástico");
		impresionesLaser = new JCheckBox("Impresión láser");
		impresionesEtiqueta.setLabelFor(impresionesPegatina);
		impresionesEtiqueta.setLabelFor(impresionesPlástico);
		impresionesEtiqueta.setLabelFor(impresionesLaser);
		impresionesPegatina.setToolTipText("Papel sticker se pega sobre el bloque, duración mínima.");
		impresionesLaser.setToolTipText("Imagen esta grabada sobre una cara ,duración media.");
		impresionesPlástico.setToolTipText("Imagen ya está ingresada en el bloque, duración máxima.");
		panel.add(impresionesPegatina);
		panel.add(impresionesLaser);
		panel.add(impresionesPlástico);
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
		JLabel coloresEtiqueta = new JLabel("9° Colores");
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
		quitarColorBotón = new JButton(quitarColor);
		quitarColorBotón.getActionMap().put("accionQuitarColor", quitarColor);
		quitarColorBotón.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) operacionNuevo.getValue(Action.ACCELERATOR_KEY), "accionAgregarColor");
		quitarColorBotón.setPreferredSize(new Dimension(100, 35));
		panel.add(quitarColorBotón);
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

		agregarColorBotón = new JButton(agregarColor);
		agregarColorBotón.getActionMap().put("accionAgregarColor", agregarColor);
		agregarColorBotón.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) operacionNuevo.getValue(Action.ACCELERATOR_KEY), "accionAgregarColor");
		agregarColorBotón.setPreferredSize(new Dimension(100, 35));
		panel.add(agregarColorBotón);
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
		JLabel imagenEtiqueta = new JLabel("10° Imagen");
		panel.add(imagenEtiqueta);
		panelNorte.add(panel);

		// imagen visual
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		imagen = new JLabel();
		imagenEtiqueta.setLabelFor(imagen);
		imagen.setToolTipText("Imagen de los Legos físicos.");
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
		imagenBotón = new JButton(escogerImagen);
		imagenEtiqueta.setLabelFor(imagenBotón);
		imagenBotón.getActionMap().put("accionEscogerImagen", escogerImagen);
		imagenBotón.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) operacionNuevo.getValue(Action.ACCELERATOR_KEY), "accionEscogerImagen");

		panel.add(imagenBotón);
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
		// esté seleccionado y mostrará en los componentes gráficos los valores
		// almacenados
		Lego guardadoLego = (Lego) lego.getSelectedItem();
		int guardadoPiezas = guardadoLego.getPiezas();
		float guardadoPrecio = guardadoLego.getPrecio();
		String guardadoNombre = guardadoLego.getNombre();
		String guardadocodigo = guardadoLego.getCódigo();
		Date guardarFechaPublicación = guardadoLego.getFechaPublicación();
		String guardadoEdadMinima = guardadoLego.getEdadMínima();
		// 7
		String guardadoTema = guardadoLego.getTema();
		// 8
		ArrayList<String> guardarTodasImpresiones = guardadoLego.getImpresiones();
		// 9
		ArrayList<String> guardarTodosColores = guardadoLego.getColores();

		piezas.setValue(guardadoPiezas);
		precio.setValue(guardadoPrecio);
		nombre.setText(guardadoNombre);
		código.setText(guardadocodigo);
		fechaPublicación.setDate(guardarFechaPublicación);
		edadMínima.setSelectedItem(guardadoEdadMinima);
		// 7
		tema.setSelectedItem(guardadoTema);
		// 8
		// Las impresiones no son obligatorias
		impresionesPlástico.setSelected(false);
		impresionesPegatina.setSelected(false);
		impresionesLaser.setSelected(false);
		if (!guardarTodasImpresiones.isEmpty()) {
			for (String guardarImpresion : guardarTodasImpresiones) {
				if (guardarImpresion.compareToIgnoreCase(impresionesPlástico.getText()) == 0) {
					impresionesPlástico.setSelected(true);
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

		// botones y menús
		// habilitará
		nuevoBotón.setEnabled(true);
		nuevo.setEnabled(true);
		// deshabilitará
		guardarBotón.setEnabled(false);
		guardar.setEnabled(false);

		cancelarBotón.setEnabled(false);
		cancelar.setEnabled(false);

		verificarLista();
	}

	// eliminara el arreglo de lego.
	private void operacionEliminar() throws BaseDatosErrorException {
		int confirmación = JOptionPane.showConfirmDialog(this, "¿Quiere eliminar el lego seleccionado?",
				"Eliminar lego", JOptionPane.YES_NO_OPTION);

		if (confirmación == JOptionPane.YES_OPTION) {
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
					"Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);

			lego.addItemListener(this);
			operacionCancelar();
		}
	}

	// guardara los legos en el arreglo de lego.
	private void operacionGuardar() throws BaseDatosErrorException {
		try {
			Lego nuevoLego;

			// nuevo o modificación del arreglo de lego
			if (esNuevo) {
				nuevoLego = new Lego();// nuevo
			} else {
				nuevoLego = (Lego) lego.getSelectedItem();// modificación
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
			String nuevocodigo = código.getText();
			nuevoLego.setCódigo(nuevocodigo);

			// 5
			Date nuevoFechaPublicación = fechaPublicación.getDate();
			nuevoLego.setFechaPublicación(nuevoFechaPublicación);

			// 6
			String nuevaEdadMinima = ((String) edadMínima.getSelectedItem()).trim();
			nuevoLego.setEdadMínima(nuevaEdadMinima);

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
			if (impresionesPlástico.isSelected()) {
				nuevasImpresiones.add(impresionesPlástico.getText());
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
	private void operaciónNuevo() {
		habilitarCampos(true);
		limpiarCampos();

		// botones y menús
		// habilitará
		guardarBotón.setEnabled(true);
		guardar.setEnabled(true);

		cancelarBotón.setEnabled(true);
		cancelar.setEnabled(true);
		// deshabilitará
		modificarBotón.setEnabled(false);
		modificar.setEnabled(false);

		eliminarBotón.setEnabled(false);
		eliminar.setEnabled(false);

		nuevoBotón.setEnabled(false);
		nuevo.setEnabled(false);
		// lista desplegable principal
		lego.setEnabled(false);

		esNuevo = true;
	}

	// modifica un archivo guardado.
	private void operaciónModificar() {
		esNuevo = false;
		habilitarCampos(true);

		// botones y menús
		// habilitará
		guardarBotón.setEnabled(true);
		guardar.setEnabled(true);

		cancelarBotón.setEnabled(true);
		cancelar.setEnabled(true);
		// deshabilitará
		modificarBotón.setEnabled(false);
		modificar.setEnabled(false);

		eliminarBotón.setEnabled(false);
		eliminar.setEnabled(false);

		nuevoBotón.setEnabled(false);
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
		// diálogo para abrir un archivo
		JFileChooser chooser = new JFileChooser();
		// Titulo
		chooser.setDialogTitle("Seleccione una imagen para lego");
		// seleccionar solo archivos
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// filtros
		FileNameExtensionFilter filtroJPG = new FileNameExtensionFilter("formato de archivo de imagen", "jpg");
		FileNameExtensionFilter filtroPNG = new FileNameExtensionFilter("Formato gráfico de compresión de imágenes",
				"png");
		FileNameExtensionFilter filtroGIF = new FileNameExtensionFilter("Formato de intercambio de gráficos", "gif");

		chooser.setFileFilter(filtroJPG);
		chooser.addChoosableFileFilter(filtroPNG);
		chooser.addChoosableFileFilter(filtroGIF);
		// No permitir cualquier tipo de archivo.
		chooser.setAcceptAllFileFilterUsed(false);
		// directorio del usuario.
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		// No permitirr selección múltiple
		chooser.setMultiSelectionEnabled(false);
		// el botón “Aceptar”, con texto, mnemónico y ayuda propia.
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
		// escala con la dimensiónmás grande de la imagen
		// completa y sin distorsión
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

	// limpiará el contenido de los campos de texto del diálogo
	private void limpiarCampos() {
		// 1. Número libre.
		piezas.setValue(1);
		// 2. Número con rango.
		precio.setValue(240.99);
		// 3. Texto en formato libre.
		nombre.setText("");
		// 4. Texto con formato predefinido.
		código.setText("");
		// 5. Fecha
		fechaPublicación.setDate(new Date());
		// Opciones fijas
		edadMínima.setSelectedIndex(0);
		// 7. Dato obtenido de opciones mutuamente excluyentes dinámicas.
		tema.setSelectedIndex(0);
		// 8. Dato multivalorado de opciones no excluyentes fijas.
		impresionesLaser.setSelected(false);
		impresionesPegatina.setSelected(false);
		impresionesPlástico.setSelected(false);
		// 9. Dato multivalorado de opciones no excluyentes dinámicas.
		colores.setSelectedIndex(0);
		// 10 imagen
		rutaImagenSelecciona = IMAGEN_DEFECTO;
		muestraImagen();
		// limpiar el modelo de la lista del campo
		// de opciones no excluyentes dinámico.
		((DefaultListModel<String>) coloresEnLista.getModel()).clear();
	}

	// habilitará o deshabilitará la escritura de los campos de texto y habilitará o
	// deshabilitará al resto de los
	// componentes gráficos del área de trabajo,
	private void habilitarCampos(boolean escritura) {
		// JSpinner
		piezas.setEnabled(escritura);
		precio.setEnabled(escritura);
		// JTextField
		nombre.setEditable(escritura);
		código.setEditable(escritura);
		fechaPublicación.setEnabled(escritura);
		// JComboBox<String>
		edadMínima.setEnabled(escritura);
		tema.setEnabled(escritura);
		colores.setEnabled(escritura);
		// JCheckBox
		impresionesPegatina.setEnabled(escritura);
		impresionesPlástico.setEnabled(escritura);
		impresionesLaser.setEnabled(escritura);
		// JButton
		agregarColorBotón.setEnabled(escritura);
		quitarColorBotón.setEnabled(escritura);
		imagenBotón.setEnabled(escritura);
		// Jscroll
		desplazamiento.setEnabled(escritura);

	}

	// verificará si la lista desplegable principal tiene elementos. Si los
	// tiene,
	// habilitará a los botones y menús “Modificar” y
	// “Eliminar”,
	private void verificarLista() {
		boolean verificador = this.lego.getItemCount() > 0;
		// lista principal
		lego.setEnabled(verificador);
		// botones
		modificarBotón.setEnabled(verificador);
		modificar.setEnabled(verificador);
		eliminarBotón.setEnabled(verificador);
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

	// Inicia el diálogo
	private void inicializar() {
		// elementos gráficos
		habilitarCampos(false);
		// piezas
		piezas.setModel(new SpinnerNumberModel(1, 1, null, 1));
		// precio
		precio.setModel(new SpinnerNumberModel(240.99, 240.99, 24213.99, 1));
		// 7 y 9 temporales, en próximas prácticas se borrarán.
		// 6. Dato obtenido de opciones mutuamente excluyentes fijas.
		edadMínima.addItem("1.5+");
		edadMínima.addItem("4+");
		edadMínima.addItem("6+");
		edadMínima.addItem("9+");
		edadMínima.addItem("13+");
		edadMínima.addItem("18+");
		// Las listas desplegables auxiliares.
		llenarTemas();
		coloresEnLista.setModel(new DefaultListModel<>());
		llenarColores();
		// lista desplegable principal
		llenarLegos();

		// botones y menús
		nuevoBotón.setEnabled(true);
		nuevo.setEnabled(true);

		guardarBotón.setEnabled(false);
		guardar.setEnabled(false);

		cancelarBotón.setEnabled(false);
		cancelar.setEnabled(false);
		// lista
		verificarLista();
	}

	// Establece el orden de enfoque entre los componentes de la interfaz.
	private void establecerFoco() {
		Vector<Component> componentes = new Vector<>();
		componentes.add(nuevoBotón);
		componentes.add(modificarBotón);
		componentes.add(guardarBotón);
		componentes.add(eliminarBotón);
		componentes.add(cancelarBotón);
		componentes.add(lego);
		componentes.add(piezas);
		componentes.add(precio);
		componentes.add(nombre);
		componentes.add(código);
		componentes.add(fechaPublicación);
		componentes.add(edadMínima);
		componentes.add(impresionesPegatina);
		componentes.add(impresionesPlástico);
		componentes.add(impresionesLaser);
		componentes.add(agregarColorBotón);
		componentes.add(quitarColorBotón);
		componentes.add(imagenBotón);
		chunkknuh.utilerias.MiFocusTraversalPolicy politicaFoco = new chunkknuh.utilerias.MiFocusTraversalPolicy(
				componentes);
		this.setFocusTraversalPolicy(politicaFoco);
	}
}