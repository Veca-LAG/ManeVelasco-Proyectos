package chunkknuh.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.toedter.calendar.JDateChooser;

import chunkknuh.utilerias.*;
import chunkknuh.base_datos.BaseDatosLegos;
import chunkknuh.excepciones.*;

public class ConsultaLegos extends JDialog implements ActionListener {

	private JCheckBox casillaPrecio;
	private JSpinner precio;
	private JComboBox<String> rangoPrecio;

	private JCheckBox casillaNombre;
	private JTextField nombre;

	private JCheckBox casillaCódigo;
	private JTextField código;

	private JCheckBox casillaFechaPublicación;
	private JDateChooser fechaPublicación;
	private JComboBox<String> rangoFechaPublicación;

	private JCheckBox casillaEdadMínima;
	private JComboBox<String> edadMínima;

	private JCheckBox casillaTema;
	private JComboBox<String> tema;

	private JCheckBox casillaColor;
	private JComboBox<String> color;

	private JButton botonConsultar;

	private JTable tablaLegos;

	private BaseDatosLegos bd;

	private String CONSULTA_PREDETERMINADA = "SELECT codigo AS Código , nombre AS 'Nombre del lego', nombreTema AS 'Tema', fechaPublicacion AS 'Fecha de publicación', precio AS Precio, piezas AS 'Numero de piezas', edadMinima AS 'Edad Minima' "
			+ "FROM lego, tema " + "WHERE lego.idTema=tema.idTema";

	private ResultSetTableModel modelo;

	private static final long serialVersionUID = 1L;

	public ConsultaLegos(JFrame principal) throws BaseDatosErrorException {
		super(principal, "Consulta de legos");
		setSize(1000, 700);
		setResizable(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panelNorte = new JPanel();
		panelNorte.setLayout(new GridLayout(2, 8, 0, 0));
		JPanel panelAyuda;

		panelAyuda = new JPanel();
		casillaCódigo = new JCheckBox("Código");
		panelAyuda.add(casillaCódigo);
		panelNorte.add(panelAyuda);

		código = new JTextField();
		código.setPreferredSize(new Dimension(130, 25));
		panelAyuda.add(código);
		panelNorte.add(panelAyuda);

		panelAyuda = new JPanel();
		casillaNombre = new JCheckBox("Nombre");
		panelAyuda.add(casillaNombre);
		panelNorte.add(panelAyuda);

		nombre = new JTextField();
		nombre.setPreferredSize(new Dimension(130, 25));
		panelAyuda.add(nombre);
		panelNorte.add(panelAyuda);

		// precio
		panelAyuda = new JPanel();
		casillaPrecio = new JCheckBox("Precio");
		panelAyuda.add(casillaPrecio);
		panelNorte.add(panelAyuda);

		precio = new JSpinner();
		precio.setPreferredSize(new Dimension(100, 25));
		panelAyuda.add(precio);
		panelNorte.add(panelAyuda);

		rangoPrecio = new JComboBox<String>();
		rangoPrecio.setPreferredSize(new Dimension(45, 25));
		panelAyuda.add(rangoPrecio);
		panelNorte.add(panelAyuda);

		// edad mínima
		panelAyuda = new JPanel();
		casillaEdadMínima = new JCheckBox("Edad mínima");
		panelAyuda.add(casillaEdadMínima);
		panelNorte.add(panelAyuda);

		edadMínima = new JComboBox<String>();
		edadMínima.setPreferredSize(new Dimension(50, 25));
		panelAyuda.add(edadMínima);
		panelNorte.add(panelAyuda);

		// tema
		panelAyuda = new JPanel();
		casillaTema = new JCheckBox("Tema");
		panelAyuda.add(casillaTema);
		panelNorte.add(panelAyuda);

		tema = new JComboBox<String>();
		tema.setPreferredSize(new Dimension(130, 25));
		panelAyuda.add(tema);
		panelNorte.add(panelAyuda);

		// color
		panelAyuda = new JPanel();
		casillaColor = new JCheckBox("Color");
		panelAyuda.add(casillaColor);
		panelNorte.add(panelAyuda);

		color = new JComboBox<String>();
		color.setPreferredSize(new Dimension(130, 25));
		panelAyuda.add(color);
		panelNorte.add(panelAyuda);

		// fecha
		panelAyuda = new JPanel();
		casillaFechaPublicación = new JCheckBox("Fecha de publicación");
		panelAyuda.add(casillaFechaPublicación);
		panelNorte.add(panelAyuda);

		fechaPublicación = new JDateChooser();
		fechaPublicación.setPreferredSize(new Dimension(100, 20));
		panelAyuda.add(fechaPublicación);
		panelNorte.add(panelAyuda);

		rangoFechaPublicación = new JComboBox<String>();
		rangoFechaPublicación.setPreferredSize(new Dimension(45, 25));
		panelAyuda.add(rangoFechaPublicación);
		panelNorte.add(panelAyuda);

		panelAyuda = new JPanel();
		botonConsultar = new JButton("Consultar");
		botonConsultar.addActionListener(this);
		panelAyuda.add(botonConsultar);
		panelNorte.add(panelAyuda);

		panelNorte.setPreferredSize(new Dimension(990, 200));

		this.add(panelNorte, BorderLayout.NORTH);

		try {
			bd = new BaseDatosLegos();
			inicializar();
		} catch (BaseDatosErrorException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitulo(), JOptionPane.ERROR_MESSAGE);
		}

		try {
			modelo = new ResultSetTableModel(bd.getCONTROLADOR(), bd.getURL(), CONSULTA_PREDETERMINADA);
		} catch (ClassNotFoundException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_CONTROLADOR);
		} catch (SQLException e) {
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_CONEXIÓN);
		}

		tablaLegos = new JTable(modelo);
		JScrollPane panel = new JScrollPane(tablaLegos);
		panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.setPreferredSize(new Dimension(900, 400));
		JPanel panelCentro = new JPanel();
		panelCentro.add(panel);
		this.add(panelCentro, BorderLayout.CENTER);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				cerrar();
			}
		});

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource().equals(botonConsultar)) {
			consultar();
		}
	}

	public void inicializar() {
		try {
			color.removeAllItems();
			ArrayList<String> coloresConsulta = new ArrayList<String>(bd.consultarColores());
			for (String colorItem : coloresConsulta) {
				color.addItem(colorItem);
			}

			tema.removeAllItems();
			ArrayList<String> temasConsulta = new ArrayList<String>(bd.consultarTemas());
			for (String temaItem : temasConsulta) {
				tema.addItem(temaItem);
			}
			precio.setModel(new SpinnerNumberModel(240.99, 240.99, 24213.99, 1));
			edadMínima.addItem("1.5+");
			edadMínima.addItem("4+");
			edadMínima.addItem("6+");
			edadMínima.addItem("9+");
			edadMínima.addItem("13+");
			edadMínima.addItem("18+");

			rangoPrecio.addItem(">=");
			rangoPrecio.addItem("<=");

			rangoFechaPublicación.addItem(">=");
			rangoFechaPublicación.addItem("<=");
		} catch (BaseDatosErrorException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitulo(), JOptionPane.WARNING_MESSAGE);
		}
	}

	public void consultar() {

		String consulta;
		// color
		if (casillaColor.isSelected()) {
			String colorConsulta = ((String) color.getSelectedItem()).trim();
			consulta = "SELECT codigo AS Código , nombre AS 'Nombre del lego', nombreTema AS 'Tema', fechaPublicacion AS 'Fecha de publicación', precio AS Precio, piezas AS 'Numero de piezas', edadMinima AS 'Edad Minima' "
					+ "FROM lego, tema, lego_color, color "
					+ "WHERE lego.idTema=tema.idTema AND lego.idLego=lego_color.idLego "
					+ "AND color.idColor=lego_color.idColor AND nombreColor LIKE '%" + colorConsulta + "%'";
		} else {
			consulta = CONSULTA_PREDETERMINADA;
		}
		if (casillaPrecio.isSelected()) {
			String rango = ((String) rangoPrecio.getSelectedItem()).trim();
			String precioConsulta = String.valueOf(precio.getValue());
			consulta += " AND precio " + rango + precioConsulta;
		}
		if (casillaFechaPublicación.isSelected()) {
			String rango = ((String) rangoFechaPublicación.getSelectedItem()).trim();
			Date fechaPublicaciónConsulta = fechaPublicación.getDate();
			SimpleDateFormat formatoFechaCadena = new SimpleDateFormat("yyyy-MM-dd");
			String fechaPublicacionCadena = formatoFechaCadena.format(fechaPublicaciónConsulta);
			consulta += " AND fechaPublicacion " + rango + "'" + fechaPublicacionCadena + "'";
		}
		if (casillaCódigo.isSelected()) {
			String códigoConsulta = código.getText().trim();
			consulta += " AND codigo LIKE '%" + códigoConsulta + "%'";
		}

		if (casillaNombre.isSelected()) {
			String nombreConsulta = nombre.getText().trim();
			consulta += " AND nombre LIKE '%" + nombreConsulta + "%'";
		}

		if (casillaTema.isSelected()) {
			String temaConsulta = ((String) tema.getSelectedItem()).trim();
			consulta += " AND nombreTema LIKE '%" + temaConsulta + "%'";
		}

		if (casillaEdadMínima.isSelected()) {
			String edadMínimaConsulta = ((String) edadMínima.getSelectedItem()).trim();
			consulta += " AND edadMinima ='" + edadMínimaConsulta + "'";
		}

		try {
			modelo.establecerConsulta(consulta);
		} catch (IllegalStateException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"No se logró hacer la consulta en la base de datos por errores de sintaxis o tablas inexistentes.",
					"Error en la consulta", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void cerrar() {
		modelo.desconectar();
	}
}
