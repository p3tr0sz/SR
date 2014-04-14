package pl.bambi.findreplace;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class Input extends JTextField implements MouseListener, ActionListener{

	/*
	 * Klasa w³asnego pola tekstowego implementuj¹ca zdadzenia klikniêcia
	 * przyciskiem myszy.
	 */
	
	private static final long serialVersionUID = 1L;
	private String defText;
	
	public Input(String text, String defaultText){
		
		super(text);
		super.addMouseListener(this);
		this.defText = defaultText;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		/*
		 * Klikniêcie lewym przyciskiem myszy usuwa domyœlny tekst pola
		 */
		
		if(getText().equals(defText) && e.getButton() == MouseEvent.BUTTON1)
			setText("");
		if(e.getButton() == MouseEvent.BUTTON3){

			this.requestFocus();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		/*
		 * Przyciœniêcie lewym przyciskiem myszy usuwa domyœlny tekst pola
		 * Przyciœniêcie prawym przyciskiem myszy automatycznie
		 * umieszcza kursor myszy wewn¹trz pola tekstowego
		 */
				
		if(getText().equals(defText) && e.getButton() == MouseEvent.BUTTON1)
			setText("");
		if(e.getButton() == MouseEvent.BUTTON3){
			this.requestFocus();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
}
