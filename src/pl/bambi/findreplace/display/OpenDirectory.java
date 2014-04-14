package pl.bambi.findreplace.display;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class OpenDirectory extends JFrame {

	/*
	 * Klasa odpowiedzialna za wy�wietlenie okna katalog�w do wyszukania
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 400;
	private JFileChooser chooser;
	private String chooseFolder = "";

	public OpenDirectory() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, DEFAULT_HEIGHT, DEFAULT_WIDTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		try {
			setIconImage(ImageIO.read(getClass().getResourceAsStream(
					("/resources/mono_lupa.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * Spolszczenie okna
		 */

		UIManager.put("FileChooser.openButtonText", "Wybierz");
		UIManager.put("FileChooser.cancelButtonText", "Anuluj");
		UIManager.put("FileChooser.saveButtonText", "Zapisz");
		UIManager.put("FileChooser.cancelButtonToolTipText",
				"Anulowanie operacji");
		UIManager.put("FileChooser.saveButtonToolTipText",
				"Zapis danych do pliku");
		UIManager.put("FileChooser.openButtonToolTipText",
				"Wybierz katalog do przeszukania");

		UIManager.put("FileChooser.lookInLabelText", "Szukaj w :");
		UIManager.put("FileChooser.fileNameLabelText", "�cie�ka katalogu:");
		UIManager.put("FileChooser.filesOfTypeLabelText", "Pliki typu:");
		UIManager.put("FileChooser.upFolderToolTipText", "Do g�ry");
		UIManager.put("FileChooser.homeFolderToolTipText", "Gl�wny pulpit");
		UIManager.put("FileChooser.newFolderToolTipText", "Nowy folder");
		UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");
		UIManager.put("FileChooser.detailsViewButtonToolTipText", "Szczeg�y");

		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setDialogTitle("Otw�rz");
		chooser.setAcceptAllFileFilterUsed(false);

		// Dzia�anie w przypaku wybrania przycisku zatwierdzaj�cego "Wybierz"
		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {

			// Je�li �cie�ka do pliku nie jest pustym znakiem zostaje przekazana
			// do zmiennej
			if (!chooser.getSelectedFile().getAbsolutePath().equals("")) {
				chooseFolder = chooser.getSelectedFile().getAbsolutePath();
			}
		}
	}

	// Metoda zwracaj�ca �cie�k� wybran� scie�k� do pliku
	public String getFolder() {
		return chooseFolder;
	}

}
