package pl.bambi.findreplace.display;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ResultWindow extends JFrame{

	/*
	 * Klasa okna 
	 */
	private static final long serialVersionUID = 1L;

	public ResultWindow(String result){
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		JOptionPane.showMessageDialog(this, result, "Rezultat", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
}
