package pl.bambi.findreplace;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.DefaultEditorKit;

import pl.bambi.findreplace.display.MainWindow;

public class PopUpMenu extends JPopupMenu{

	/*
	 * Klasa menu wyœwietlanego po klikniêciu prawym przyciskiem
	 * myszy na pole tekstowe
	 */
	private static final long serialVersionUID = 1L;
	
	private JPopupMenu popup;
	private JMenuItem menuItemCut;
	private JMenuItem menuItemCopy;
	private JMenuItem menuItemPaste;

	public PopUpMenu(){
		
		/*
		 * Menu sk³ada siê z 3 opcji: Wytnik, Kopiuj, Wklej
		 */
		
		popup = new JPopupMenu();
		
		menuItemCut = new JMenuItem(new DefaultEditorKit.CutAction());
		menuItemCut.setText("Wytnij");
		menuItemCut.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/Cut.png")));
		popup.add(menuItemCut);
		
		menuItemCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
		menuItemCopy.setText("Kopiuj");
		menuItemCopy.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/Copy.png")));
		popup.add(menuItemCopy);
		
		menuItemPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
		menuItemPaste.setText("Wklej");
		menuItemPaste.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/Paste.png")));
		popup.add(menuItemPaste);
	}
	
	
	//Metoda zwracaj¹ca menu
	public JPopupMenu getPopupMenu(){
		return popup;
	}
	
	//Metoda pozwalaj¹ca na wy³¹cznie opcji menu podaj¹c s³owo klucz
	public void disableItem(String keyWord){
		
		if(keyWord.equals("Cut")){
			
			menuItemCut.setEnabled(false);
			
		}else if(keyWord.equals("Copy")){
			
			menuItemCopy.setEnabled(false);
			
		}else if(keyWord.equals("Paste")){
			
			menuItemPaste.setEnabled(false);
			
		}
		
	}
}
