package pl.bambi.findreplace;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import pl.bambi.findreplace.display.ErrorWindow;
import pl.bambi.findreplace.display.ResultWindow;

public class Findreplace {

	private Collection<File> lstFiles;
	private Collection<File> repFiles;
	private boolean run;
	private boolean res;
	
	
	public static String newline = System.getProperty("line.separator");
	
	public Findreplace(File directory,String format, String strToFind, String strReplace){
		
		run = false;
		res = false;
		
		String[] fr = {format};
		
			//Listy plików o tym samym formacie i plików, które zosta³y podmienione
			lstFiles = new ArrayList<File>();
			repFiles = new ArrayList<File>();
			
			/*
        	 * Wykorzystanie klasy FileUtils Apache Commons IO
        	 * oraz metody listFiles wyszukuj¹cej pliki w podanym katalogu i podkatalogach( wartoœæ logiczna true) 
        	 * o podanym formacie
        	 */ 
			
			lstFiles = FileUtils.listFiles(directory, fr, true);
			

			/*
			 * Jeœli lista jest pusta, to znaczy, ¿e nie znaleziono plików o podanym rozszerzeniu.
			 * Wyœwietlony zostaje komunikat
			 */
			if(!lstFiles.isEmpty()){
				run = true;
			}else{
				new ErrorWindow("Nie znaleziono plików o podanym rozszerzeniu");
				run = false;
			}
			
			/*
			 * Jeœli znaleziono pliki o podanym rozszerzeniu nastepuje ich przetworzenie
			 */
			if(run){
			
				for (File f : lstFiles){
					try {
						
						/*
						 * Jeœli plik zawiera ³añcuch znaków, nale¿y go podmieniæ,
						 * a nastêpnie dodaje siê ten plik do listy rezultatów
						 */
						
						if(isFileContainsString(f, strToFind)){
							processFile(f, strToFind, strReplace);
							repFiles.add(f);
							res = true;
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			//Jeœli nie znaleziono ³añcucha w ¿adnym pliku wyœwietlany jest komunikat o b³êdzie
			if(!res && run)
				new ErrorWindow("W plikach nie znaleziono podanego ³añcucha znaków");
			
			//Jeœli znaleziono pliki i podmieniono ³añcuchy zwracany jest komunikat o sukcesie
			//W przeciwnym wypadku wyœwietlana jest informacja o niepowodzeniu
			if(res && run)
				new ResultWindow("Operacja zakoñczona powodzeniem");
			else
				new ResultWindow("Operacja zakoñczona niepowodzeniem");
	}

	/*
	 * Metoda przetwarzaj¹ca plik podmieniaj¹c znaki.
	 */
	
	public static void processFile (File file, String strToFind, String strReplace) throws IOException{
		
		FileInputStream is = new FileInputStream(file);
		DataInputStream in = new DataInputStream(is);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
        File tmpFile = new File(file.getAbsolutePath() + "_tmp");
        FileWriter fw = new FileWriter(tmpFile);
        BufferedWriter out = new BufferedWriter(fw);
        
        String ln;
        
        while ( (ln = br.readLine()) != null){
        	
         	/*
        	 * Wykorzystanie klasy StringUtils Apache Commons Lang
        	 * oraz metody replace wyszukuj¹cej i zastêpuj¹cej ³añcuch znaków.
        	 * Pozwala na podmianie znaków specjalnych takich jak \n, \t etc.
        	 * 
        	 */
        
        	ln = StringUtils.replace(ln, strToFind, strReplace);
        	out.write(ln+newline);
        }
		
        br.close();
        in.close();
        is.close();
        
        out.close();
        fw.close();
        
        if (!file.delete())
            throw new OwnException("Nie mo¿na usun¹æ" + file.getName());
        if (!file.createNewFile())
            throw new OwnException("Nie mo¿na stworzyæ" + file.getName());
 
         copyFile(tmpFile,file);
 
         if (!tmpFile.delete())
            throw new OwnException("Nie mo¿na usun¹æ " + tmpFile.getName());
	}
	
	/*
	 * Metoda kopiuj¹ca plik
	 */
	
	private static void copyFile(File source, File dest) throws IOException{
		
		InputStream in = new FileInputStream(source);
		OutputStream os = new FileOutputStream(dest);
		
		byte[] buffer = new byte[1024];
		int len;
		
		while((len = in.read(buffer)) > 0){
			os.write(buffer, 0, len);
		}
		
		in.close();
		os.close();
	}
	
	public Collection<File> getResults(){
		return repFiles;
	}
	
	
	/*
	 * Metoda sprawdzaj¹ca czy plik zawiera podany ³añcuch znaków
	 */
	public boolean isFileContainsString(File file, String strToFind) throws IOException{

		FileInputStream is = new FileInputStream(file);
		DataInputStream in = new DataInputStream(is);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		boolean ifContains = false;
		
		//licznik
		int n = 0;
		
        String ln;
        
        while ( (ln = br.readLine()) != null){
        	
        	/*
        	 * Wykorzystanie klasy StringUtils Apache Commons Lang
        	 * oraz metody countMatches sprawdzaj¹cej ile razy 
        	 * ³añcuch znaków zawiera inny
        	 */
        	
        	n += StringUtils.countMatches(ln, strToFind);
        }
        
        br.close();
        in.close();
        is.close();
        
        //Jeœli licznik jest wiêkszy od zera oznacza ¿e przynajmniej
        //jedna linia pliku zawiera poszukiwany ³añcuch znaków.
        if(n > 0)
        	ifContains = true;

        
        return ifContains;
	}
}

class OwnException extends IOException{
	
	/*
	 * W³asna klasa wyj¹tku
	 */
	
	private static final long serialVersionUID = 1L;

	public OwnException(String err){
		new ErrorWindow(err);
	}
	
}