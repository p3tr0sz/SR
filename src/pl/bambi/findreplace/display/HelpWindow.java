package pl.bambi.findreplace.display;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class HelpWindow extends JDialog {

	
	/*
	 * Klasa okna Pomoc programu
	 */
	
	
	private static final long serialVersionUID = 1L;

	public HelpWindow() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		setAlwaysOnTop(true);
		
		setModal(true);
		
		setTitle("Pomoc");
		try {
			setIconImage(ImageIO.read(getClass().getResourceAsStream(("/resources/mono_lupa.png"))));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//Treœæ wyœwietlanej wiadomoœci
		
		String help = "  Podmieniacz\n"
				+ "  Aby wyszukaæ i podmieniæ ³añcuch znaków w plikach danego katalogu nale¿y:\n"
				+ "  1. Wprowadziæ œcie¿kê do katalogu zawieraj¹cego poszukiwane pliki\n"
				+ "  (wpisaæ œcie¿kê lub wykorzystaæ przycisk Wybierz... aby go odszukaæ).\n"
				+ "  2. Wpisaæ rozszerzenie pliku nie podaj¹c .\n"
				+ "  3. Wprowadziæ ³añcuch znaków, który u¿ytkownik chce zmieniæ\n"
				+ "  4. Wprowadziæ ³añcuch znaków, na jaki ma zostaæ podmieniony ³añcuch z punktu 3.\n"
				+ "  5. Wykonaæ operacjê klikacj¹c przycisk ZnajdŸ i zamieñ\n"
				+ "  Rezultat dzia³ania programu (pliki w których dokonano zamiany) bêdzie widoczny\n"
				+ "  w polu: Pliki poddane operacji";
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 250);
		setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{534, 0};
		gridBagLayout.rowHeights = new int[]{301, 10, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JTextPane txtpnZnajdIZamie = new JTextPane();
		txtpnZnajdIZamie.setBackground(new Color(238,238,238));
		txtpnZnajdIZamie.setEditable(false);
		txtpnZnajdIZamie.setText(help);
		GridBagConstraints gbc_txtpnZnajdIZamie = new GridBagConstraints();
		gbc_txtpnZnajdIZamie.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnZnajdIZamie.fill = GridBagConstraints.BOTH;
		gbc_txtpnZnajdIZamie.gridx = 0;
		gbc_txtpnZnajdIZamie.gridy = 0;
		getContentPane().add(txtpnZnajdIZamie, gbc_txtpnZnajdIZamie);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);
			
			
			JLabel lblPiotrBartkiewicz = new JLabel("@Piotr Bartkiewicz");
			buttonPane.add(lblPiotrBartkiewicz);

		}
	}
}
