package pl.bambi.findreplace.display;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
 * Klasa okna pojawiaj¹ca siê po wyœwietleniu "O programie"
 * 
 * */


public class AboutWindow extends JDialog{


	private static final long serialVersionUID = 1L;


	public AboutWindow() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		
		//Informacje o programie
		String info = " O programie:\n"
				+"\n \"Podmieniacz\"  ver. 1.0\n"
				+ " Licencja: Shareware\n";
		
		try {
			setIconImage(ImageIO.read(getClass().getResourceAsStream(("/resources/mono_lupa.png"))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Icon pic = null;
		try {
			pic = new ImageIcon( ImageIO.read(getClass().getResourceAsStream(("/resources/mono_lupa.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

       
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5,220, 220, 5};
		gridBagLayout.rowHeights = new int[]{280,20 , 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel icon = new JLabel();
		icon.setIcon(pic);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(icon, gbc_lblNewLabel);
		
		JTextPane textArea = new JTextPane();
		textArea.setEditable(false);
		textArea.setBackground(new Color(238,238,238));
		textArea.setText(info);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 0;
		getContentPane().add(textArea, gbc_textArea);
		
		JLabel lblNewLabel_2 = new JLabel("@Piotr Bartkiewicz");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 1;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		setModalityType(ModalityType.APPLICATION_MODAL);

		setTitle("O programie");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
	}


}
