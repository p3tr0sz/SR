package pl.bambi.findreplace.display;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class QuestionWindow extends JFrame{

	/*
	 * Klasa okna prosz¹cego u¿ytkowanika o decyzjê.
	 * Dostêpne opcje: Tak, Nie
	 */
	
	private static final long serialVersionUID = 1L;
	private int choice;
	private Object[] options = {"Tak", "Nie"};
	
	public QuestionWindow(String question){
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		setAlwaysOnTop(true);

		choice = JOptionPane.showOptionDialog(this, question, "Ostrzerzenie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
	}
	
	//Metoda zwracaj¹ca wybór u¿ytkownika
	public int getChoice(){
	
		return choice;
	
	}
}
