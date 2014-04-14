package pl.bambi.findreplace.display;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ErrorWindow extends JFrame {

	/*
	 * Klasa odpowiedzialna za okno komunikatu o b��dzie,
	 * jako argument konstruktor przyjmuje tre�� komunikatu,
	 * kt�ry ma zosta� wy�wietlony 
	 */
	
	
	private static final long serialVersionUID = 1L;

	public ErrorWindow(String error){
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		JOptionPane.showMessageDialog(this, error, "B��d", JOptionPane.ERROR_MESSAGE);
		
	}

}
