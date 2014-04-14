package pl.bambi.findreplace.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import pl.bambi.findreplace.Findreplace;
import pl.bambi.findreplace.Input;
import pl.bambi.findreplace.PopUpMenu;

public class MainWindow extends JFrame{

	/*
	 * Klasa okna g³ównego aplikacji
	 */

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField directory;
	private JTextField fileFormat;
	private JTextField findText;
	private JTextField replaceText;
	private JLabel lblZnalezionePlikiO;
	private JButton btnWybierz;
	private JButton findReplace;
	private boolean[] run;
	private QuestionWindow qw;
	private String[] defaults = new String[2];
	private JTextArea results;
	private Findreplace fr;
	private PopUpMenu pop;
	private PopUpMenu popup2;
/*	private JMenuBar menuBar;
	private JMenu mnPomoc;
	private JMenuItem help;
	private JMenuItem about;*/
	private JLabel lblpiotrBartkiewicz;
	private JLabel yellowLoupe;

	// G³ówna metoda programu

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setIconImage(ImageIO.read(getClass()
							.getResourceAsStream(("/resources/mono_lupa.png"))));
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Tworzenie okna
	 */

	public MainWindow() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setTitle("Podmieniacz");

		setResizable(false);

		pop = new PopUpMenu();
		popup2 = new PopUpMenu();
		run = new boolean[4];

		defaults[0] = "Œcie¿ka do katalogu";
		defaults[1] = "Rozszerzenie pliku, bez \".\"";

		// Tablica wartoœci logicznych run[] s³u¿y do sprawdzenia
		// warunków potrzebnych do prawid³owego dzia³ania programu.
		// Pocz¹tkowo wszystkie równe s¹ false.
		for (int r = 0; r < run.length; r++) {
			run[r] = false;
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);

		JMenuBar menuBar = new JMenuBar();

		JMenu mnPomoc = new JMenu("Pomoc");
		
		JMenuItem help = new JMenuItem("Pomoc programu Podmieniacz");
		mnPomoc.add(help);
		// W przypadku wybrania opcji "pomoc programu" pojawia siê okno pomocy
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HelpWindow hw = new HelpWindow();
				hw.setVisible(true);
			}
		});
		//help.addActionListener(this);
		help.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/Help-icon.png")));


		JMenuItem about = new JMenuItem("O programie Podmieniacz");
		mnPomoc.add(about);
		// W przypadku wybrania opcji "o programie" pojawia siê okno informacji
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutWindow aw = new AboutWindow();
				aw.setVisible(true);			
			}
		});
		//about.addActionListener(this);

		menuBar.add(mnPomoc);
		setJMenuBar(menuBar);

		Icon pic = null;
		try {
			pic = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(
					("/resources/lupa_yellow.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 175, 371, 0 };
		gbl_contentPane.rowHeights = new int[] { 30, 30, 30, 30, 30, 0, 30, 30,
				0, 30 };
		gbl_contentPane.columnWeights = new double[] { 0.1, 0.8, 0.1 };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		directory = new Input(defaults[0], defaults[0]);
		directory.setToolTipText("Na przyk\u0142ad: C:\\Users");
		directory.setForeground(Color.DARK_GRAY);
		directory.add(pop.getPopupMenu());
		directory.setComponentPopupMenu(pop.getPopupMenu());

		GridBagConstraints gbc_directory = new GridBagConstraints();
		gbc_directory.fill = GridBagConstraints.HORIZONTAL;
		gbc_directory.insets = new Insets(0, 0, 5, 5);
		gbc_directory.gridx = 1;
		gbc_directory.gridy = 1;
		directory.getPreferredSize();

		JLabel lblKatalogDoPrzeszukania = new JLabel("Katalog do przeszukania:");
		GridBagConstraints gbc_lblKatalogDoPrzeszukania = new GridBagConstraints();
		gbc_lblKatalogDoPrzeszukania.insets = new Insets(0, 0, 5, 5);
		gbc_lblKatalogDoPrzeszukania.anchor = GridBagConstraints.EAST;
		gbc_lblKatalogDoPrzeszukania.gridx = 0;
		gbc_lblKatalogDoPrzeszukania.gridy = 1;
		contentPane.add(lblKatalogDoPrzeszukania, gbc_lblKatalogDoPrzeszukania);
		contentPane.add(directory, gbc_directory);
		directory.setColumns(10);

		btnWybierz = new JButton("Wybierz...");
		btnWybierz.setPreferredSize(new Dimension(136, 20));
		btnWybierz
				.setToolTipText("Wybierz katalog, w którym nale¿y wyszukaæ pliki");

		// Klikniêcie przycisku spowoduje wyœwietlenie okna wyboru katalogu
		btnWybierz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenDirectory od = new OpenDirectory();
				directory.setText(od.getFolder());
			}
		});
		GridBagConstraints gbc_btnWybierz = new GridBagConstraints();
		gbc_btnWybierz.anchor = GridBagConstraints.WEST;
		gbc_btnWybierz.insets = new Insets(0, 0, 5, 0);
		gbc_btnWybierz.gridx = 2;
		gbc_btnWybierz.gridy = 1;
		contentPane.add(btnWybierz, gbc_btnWybierz);

		JLabel lblRozszerzenie = new JLabel("Rozszerzenie pliku:");
		GridBagConstraints gbc_lblRozszerzenie = new GridBagConstraints();
		gbc_lblRozszerzenie.anchor = GridBagConstraints.EAST;
		gbc_lblRozszerzenie.insets = new Insets(0, 0, 5, 5);
		gbc_lblRozszerzenie.gridx = 0;
		gbc_lblRozszerzenie.gridy = 2;
		contentPane.add(lblRozszerzenie, gbc_lblRozszerzenie);

		fileFormat = new Input(defaults[1], defaults[1]);
		fileFormat.setToolTipText("Na przyk\u0142ad: txt");
		fileFormat.setForeground(Color.DARK_GRAY);
		fileFormat.add(pop.getPopupMenu());
		fileFormat.setComponentPopupMenu(pop.getPopupMenu());
		GridBagConstraints gbc_fileFormat = new GridBagConstraints();
		gbc_fileFormat.insets = new Insets(0, 0, 5, 5);
		gbc_fileFormat.fill = GridBagConstraints.HORIZONTAL;
		gbc_fileFormat.gridx = 1;
		gbc_fileFormat.gridy = 2;
		contentPane.add(fileFormat, gbc_fileFormat);
		fileFormat.setColumns(10);

		JLabel lblacuchZnakwDo = new JLabel(
				"\u0141a\u0144cuch znak\u00F3w do wyszukania:");
		GridBagConstraints gbc_lblacuchZnakwDo = new GridBagConstraints();
		gbc_lblacuchZnakwDo.anchor = GridBagConstraints.EAST;
		gbc_lblacuchZnakwDo.insets = new Insets(0, 0, 5, 5);
		gbc_lblacuchZnakwDo.gridx = 0;
		gbc_lblacuchZnakwDo.gridy = 3;
		contentPane.add(lblacuchZnakwDo, gbc_lblacuchZnakwDo);

		findText = new Input("", "");
		findText.setForeground(Color.DARK_GRAY);
		findText.add(pop.getPopupMenu());
		findText.setComponentPopupMenu(pop.getPopupMenu());
		GridBagConstraints gbc_findText = new GridBagConstraints();
		gbc_findText.insets = new Insets(0, 0, 5, 5);
		gbc_findText.fill = GridBagConstraints.HORIZONTAL;
		gbc_findText.gridx = 1;
		gbc_findText.gridy = 3;
		contentPane.add(findText, gbc_findText);
		findText.setColumns(10);

		JLabel lblacuchZnakwDo_1 = new JLabel(
				"\u0141a\u0144cuch znak\u00F3w do zamiany:");
		GridBagConstraints gbc_lblacuchZnakwDo_1 = new GridBagConstraints();
		gbc_lblacuchZnakwDo_1.anchor = GridBagConstraints.EAST;
		gbc_lblacuchZnakwDo_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblacuchZnakwDo_1.gridx = 0;
		gbc_lblacuchZnakwDo_1.gridy = 4;
		contentPane.add(lblacuchZnakwDo_1, gbc_lblacuchZnakwDo_1);

		replaceText = new Input("", "");
		replaceText.setForeground(Color.DARK_GRAY);
		replaceText.add(pop.getPopupMenu());
		replaceText.setComponentPopupMenu(pop.getPopupMenu());
		GridBagConstraints gbc_replaceText = new GridBagConstraints();
		gbc_replaceText.insets = new Insets(0, 0, 5, 5);
		gbc_replaceText.fill = GridBagConstraints.HORIZONTAL;
		gbc_replaceText.gridx = 1;
		gbc_replaceText.gridy = 4;
		contentPane.add(replaceText, gbc_replaceText);
		replaceText.setColumns(10);

		findReplace = new JButton("Znajd\u017A i zamie\u0144");
		findReplace.setPreferredSize(new Dimension(136, 20));
		findReplace
				.setToolTipText("Kliknij aby wyszukaæ i zamieniæ ³añcuch znaków w plikach");

		// Klikniêcie "znajdŸ i zamieñ"
		findReplace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Wyczyszczenie komponentu textArea
				results.setText("");

				// Przypisanie wartoœci podanych przez u¿ytkownika do zmiennych
				String catalogPath = directory.getText();
				String format = fileFormat.getText();
				String strToFind = findText.getText();
				String strReplace = replaceText.getText();
				File directory = new File(catalogPath);

				/*
				 * Sprawdzenie czy wprowadzona œcie¿ka jest b³êdna lub czy nie
				 * odnosi siê do katalogu (tylko do pliku) Jeœli tak wyœwietlony
				 * zostanie komunikat o b³êdzie, zmienna logiczna przyjmie
				 * wartoœæ false, czyli nie jest spe³niony jeden z warunków
				 * dzia³ania programu. W przeciwnym wypadku warunki s¹ spe³nione
				 * i zmienna logiczna przyjmuje wartoœæ true
				 */

				if (!(directory.exists() && directory.isDirectory())) {
					new ErrorWindow("Podany katalog nie istnieje");
					run[0] = false;
				} else
					run[0] = true;

				/*
				 * Sprawdzenie czy nie wprowadzono formatu pliku oraz czy
				 * spe³niony zosta³ wczeœniejszy warunek. Jeœli jest to
				 * spe³nione pojawia siê komunikat o b³êdzie zmienna logiczna =
				 * false W przeciwnym wypaku run = true
				 */
				if (format.equals("") && run[0]) {
					new ErrorWindow("Nie podano rozszerzenia pliku");
					run[1] = false;
				} else
					run[1] = true;

				/*
				 * Sprawdzenie czy nie wprowadzono ³añcucha znaków do wyszukania
				 * oraz czy spe³nione zosta³y wczeœniejsze warunki. Jeœli jest
				 * to spe³nione pojawia siê komunikat o b³êdzie zmienna logiczna
				 * = false W przeciwnym wypaku run = true
				 */
				if (strToFind.equals("") && run[0] && run[1]) {
					new ErrorWindow("Nie podano ³añcucha znaków do wyszukania");
					run[2] = false;
				} else
					run[2] = true;

				/*
				 * Sprawdzenie czy nie wprowadzono ³añcucha znaków do podmiany
				 * oraz czy spe³nione zosta³y wczeœniejsze warunki. Jeœli jest
				 * to spe³nione pojawia siê komunikat o b³êdzie zmienna logiczna
				 * = false W przeciwnym wypaku run = true Pojawia siê okno
				 * prosz¹ce u¿ytkownika o decyzjê czy zast¹piæ ³añcuch pustymi
				 * znakami
				 */
				if (strReplace.equals("") && run[0] && run[1] && run[2]) {
					qw = new QuestionWindow(
							"Nie podano ³añcucha zastêpuj¹cego\n³añcuch wyszukany w plikach zostanie\nzast¹piony pustymi znakami.\nKontynuowaæ?");
					if (qw.getChoice() == JOptionPane.YES_OPTION)
						run[3] = true;
					else
						run[3] = false;
				} else
					run[3] = true;

				/*
				 * Jeœli wszystkie warunki s¹ spe³nione rozpoczyna siê operacja
				 * na plikach
				 */

				if (run[0] && run[1] && run[2] && run[3]) {
					fr = new Findreplace(directory, format, strToFind,
							strReplace);

					// Drukowanie wyników
					printResult(fr.getResults(), results);
				}

			}
		});
		GridBagConstraints gbc_findReplace = new GridBagConstraints();
		gbc_findReplace.insets = new Insets(0, 0, 5, 5);
		gbc_findReplace.gridx = 1;
		gbc_findReplace.gridy = 5;
		contentPane.add(findReplace, gbc_findReplace);

		results = new JTextArea();
		results.setToolTipText("Lista plik\u00F3w, kt\u00F3re zosta\u0142y przetworzone");
		results.setFont(new Font("Tahoma", Font.PLAIN, 13));
		results.setEditable(false);
		results.setForeground(Color.DARK_GRAY);
		popup2.disableItem("Cut");
		popup2.disableItem("Paste");

		lblZnalezionePlikiO = new JLabel("Pliki poddane operacji:");
		GridBagConstraints gbc_lblZnalezionePlikiO = new GridBagConstraints();
		gbc_lblZnalezionePlikiO.anchor = GridBagConstraints.SOUTH;
		gbc_lblZnalezionePlikiO.insets = new Insets(0, 0, 5, 5);
		gbc_lblZnalezionePlikiO.gridx = 1;
		gbc_lblZnalezionePlikiO.gridy = 6;
		contentPane.add(lblZnalezionePlikiO, gbc_lblZnalezionePlikiO);

		yellowLoupe = new JLabel("");
		yellowLoupe.setIcon(pic);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 7;
		contentPane.add(yellowLoupe, gbc_lblNewLabel);
		results.add(popup2.getPopupMenu());
		results.setComponentPopupMenu(popup2.getPopupMenu());
		JScrollPane scroll = new JScrollPane(results);
		GridBagConstraints scrollConstrains = new GridBagConstraints();
		scrollConstrains.insets = new Insets(0, 0, 5, 5);
		scrollConstrains.fill = GridBagConstraints.BOTH;
		scrollConstrains.gridx = 1;
		scrollConstrains.gridy = 7;
		scrollConstrains.gridwidth = 1;
		contentPane.add(scroll, scrollConstrains);

		lblpiotrBartkiewicz = new JLabel("@Piotr Bartkiewicz");
		GridBagConstraints gbc_lblpiotrBartkiewicz = new GridBagConstraints();
		gbc_lblpiotrBartkiewicz.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblpiotrBartkiewicz.gridx = 2;
		gbc_lblpiotrBartkiewicz.gridy = 8;
		contentPane.add(lblpiotrBartkiewicz, gbc_lblpiotrBartkiewicz);
	}

	// Drukowanie wyników w polu TextArea, wyœwietlana jest œcie¿ka do plików,
	// na których przeprowadzono operacje
	public void printResult(Collection<File> files, JTextArea ta) {

		for (File f : files) {
			ta.append(f.getAbsolutePath() + "\n\r");
		}
	}

}
