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

	private JCheckBox casillaC�digo;
	private JTextField c�digo;

	private JCheckBox casillaFechaPublicaci�n;
	private JDateChooser fechaPublicaci�n;
	private JComboBox<String> rangoFechaPublicaci�n;

	private JCheckBox casillaEdadM�nima;
	private JComboBox<String> edadM�nima;

	private JCheckBox casillaTema;
	private JComboBox<String> tema;

	private JCheckBox casillaColor;
	private JComboBox<String> color;

	private JButton botonConsultar;

	private JTable tablaLegos;

	private BaseDatosLegos bd;

	private String CONSULTA_PREDETERMINADA = "SELECT codigo AS C�digo , nombre AS 'Nombre del lego', nombreTema AS 'Tema', fechaPublicacion AS 'Fecha de publicaci�n', precio AS Precio, piezas AS 'Numero de piezas', edadMinima AS 'Edad Minima' "
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
		casillaC�digo = new JCheckBox("C�digo");
		panelAyuda.add(casillaC�digo);
		panelNorte.add(panelAyuda);

		c�digo = new JTextField();
		c�digo.setPreferredSize(new Dimension(130, 25));
		panelAyuda.add(c�digo);
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

		// edad m�nima
		panelAyuda = new JPanel();
		casillaEdadM�nima = new JCheckBox("Edad m�nima");
		panelAyuda.add(casillaEdadM�nima);
		panelNorte.add(panelAyuda);

		edadM�nima = new JComboBox<String>();
		edadM�nima.setPreferredSize(new Dimension(50, 25));
		panelAyuda.add(edadM�nima);
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
		casillaFechaPublicaci�n = new JCheckBox("Fecha de publicaci�n");
		panelAyuda.add(casillaFechaPublicaci�n);
		panelNorte.add(panelAyuda);

		fechaPublicaci�n = new JDateChooser();
		fechaPublicaci�n.setPreferredSize(new Dimension(100, 20));
		panelAyuda.add(fechaPublicaci�n);
		panelNorte.add(panelAyuda);

		rangoFechaPublicaci�n = new JComboBox<String>();
		rangoFechaPublicaci�n.setPreferredSize(new Dimension(45, 25));
		panelAyuda.add(rangoFechaPublicaci�n);
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
			throw new BaseDatosErrorException(BaseDatosErrorException.ERROR_CONEXI�N);
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
			edadM�nima.addItem("1.5+");
			edadM�nima.addItem("4+");
			edadM�nima.addItem("6+");
			edadM�nima.addItem("9+");
			edadM�nima.addItem("13+");
			edadM�nima.addItem("18+");

			rangoPrecio.addItem(">=");
			rangoPrecio.addItem("<=");

			rangoFechaPublicaci�n.addItem(">=");
			rangoFechaPublicaci�n.addItem("<=");
		} catch (BaseDatosErrorException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitulo(), JOptionPane.WARNING_MESSAGE);
		}
	}

	public void consultar() {

		String consulta;
		// color
		if (casillaColor.isSelected()) {
			String colorConsulta = ((String) color.getSelectedItem()).trim();
			consulta = "SELECT codigo AS C�digo , nombre AS 'Nombre del lego', nombreTema AS 'Tema', fechaPublicacion AS 'Fecha de publicaci�n', precio AS Precio, piezas AS 'Numero de piezas', edadMinima AS 'Edad Minima' "
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
		if (casillaFechaPublicaci�n.isSelected()) {
			String rango = ((String) rangoFechaPublicaci�n.getSelectedItem()).trim();
			Date fechaPublicaci�nConsulta = fechaPublicaci�n.getDate();
			SimpleDateFormat formatoFechaCadena = new SimpleDateFormat("yyyy-MM-dd");
			String fechaPublicacionCadena = formatoFechaCadena.format(fechaPublicaci�nConsulta);
			consulta += " AND fechaPublicacion " + rango + "'" + fechaPublicacionCadena + "'";
		}
		if (casillaC�digo.isSelected()) {
			String c�digoConsulta = c�digo.getText().trim();
			consulta += " AND codigo LIKE '%" + c�digoConsulta + "%'";
		}

		if (casillaNombre.isSelected()) {
			String nombreConsulta = nombre.getText().trim();
			consulta += " AND nombre LIKE '%" + nombreConsulta + "%'";
		}

		if (casillaTema.isSelected()) {
			String temaConsulta = ((String) tema.getSelectedItem()).trim();
			consulta += " AND nombreTema LIKE '%" + temaConsulta + "%'";
		}

		if (casillaEdadM�nima.isSelected()) {
			String edadM�nimaConsulta = ((String) edadM�nima.getSelectedItem()).trim();
			consulta += " AND edadMinima ='" + edadM�nimaConsulta + "'";
		}

		try {
			modelo.establecerConsulta(consulta);
		} catch (IllegalStateException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this,
					"No se logr� hacer la consulta en la base de datos por errores de sintaxis o tablas inexistentes.",
					"Error en la consulta", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void cerrar() {
		modelo.desconectar();
	}
}
