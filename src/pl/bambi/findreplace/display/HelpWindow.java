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
	
		//Tre�� wy�wietlanej wiadomo�ci
		
		String help = "  Podmieniacz\n"
				+ "  Aby wyszuka� i podmieni� �a�cuch znak�w w plikach danego katalogu nale�y:\n"
				+ "  1. Wprowadzi� �cie�k� do katalogu zawieraj�cego poszukiwane pliki\n"
				+ "  (wpisa� �cie�k� lub wykorzysta� przycisk Wybierz... aby go odszuka�).\n"
				+ "  2. Wpisa� rozszerzenie pliku nie podaj�c .\n"
				+ "  3. Wprowadzi� �a�cuch znak�w, kt�ry u�ytkownik chce zmieni�\n"
				+ "  4. Wprowadzi� �a�cuch znak�w, na jaki ma zosta� podmieniony �a�cuch z punktu 3.\n"
				+ "  5. Wykona� operacj� klikacj�c przycisk Znajd� i zamie�\n"
				+ "  Rezultat dzia�ania programu (pliki w kt�rych dokonano zamiany) b�dzie widoczny\n"
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
