package chunkknuh.utilerias;

import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import chunkknuh.base_datos.BaseDatosLegos;

import chunkknuh.excepciones.BaseDatosErrorException;

public class GenerarReporteLego extends JDialog {

	private static final long serialVersionUID = 1L;

	private BaseDatosLegos bd;

	public GenerarReporteLego() {
		try {
			exportar();
		} catch (BaseDatosErrorException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getTitulo(), JOptionPane.WARNING_MESSAGE);
		}
	}

	private void exportar() throws BaseDatosErrorException {
		// di�logo para abrir un archivo
		JFileChooser chooser = new JFileChooser();
		// Titulo
		chooser.setDialogTitle("Generar reporte de los legos");
		// seleccionar solo archivos
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// filtros
		FileNameExtensionFilter filtroPDF = new FileNameExtensionFilter("Formato de documento port�til", "pdf");
		chooser.setFileFilter(filtroPDF);
		// No permitir cualquier tipo de archivo.
		chooser.setAcceptAllFileFilterUsed(false);
		// directorio del usuario.
		chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		// No permitirr selecci�n m�ltiple
		chooser.setMultiSelectionEnabled(false);
		// el bot�n �Aceptar�, con texto, mnem�nico y ayuda propia.
		chooser.setApproveButtonText("Guardar");
		chooser.setApproveButtonToolTipText("Guardar pdf de reporte de legos");
		int opcion = chooser.showSaveDialog(this);
		if (opcion == JFileChooser.APPROVE_OPTION) {
			File archivo = chooser.getSelectedFile();

			// existe extencion JSON
			String archivoRuta = archivo.getAbsolutePath();
			int extensi�n = archivoRuta.lastIndexOf(".");
			if (extensi�n == -1 || archivoRuta.substring(extensi�n).equalsIgnoreCase("pdf")) {
				archivo = new File(archivoRuta + ".pdf");
			}

			// existe sobrescribir
			if (archivo.exists()) {
				int sobrescribir = JOptionPane.showConfirmDialog(this,
						"Existe un archivo con el mismo nombre. �Desea sobrescribirlo?, se eliminaran datos pasados",
						"Confirmar sobrescritura", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (sobrescribir != JOptionPane.YES_OPTION) {// si no es aceptado cancelar
					return;
				}
			}
			// Exportar
			String nombreArchivo = archivo.getAbsolutePath();

			bd = new BaseDatosLegos();
			bd.crearReporte(nombreArchivo);
		}
	}
}