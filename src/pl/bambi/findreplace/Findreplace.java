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
		
			//Listy plik�w o tym samym formacie i plik�w, kt�re zosta�y podmienione
			lstFiles = new ArrayList<File>();
			repFiles = new ArrayList<File>();
			
			/*
        	 * Wykorzystanie klasy FileUtils Apache Commons IO
        	 * oraz metody listFiles wyszukuj�cej pliki w podanym katalogu i podkatalogach( warto�� logiczna true) 
        	 * o podanym formacie
        	 */ 
			
			lstFiles = FileUtils.listFiles(directory, fr, true);
			

			/*
			 * Je�li lista jest pusta, to znaczy, �e nie znaleziono plik�w o podanym rozszerzeniu.
			 * Wy�wietlony zostaje komunikat
			 */
			if(!lstFiles.isEmpty()){
				run = true;
			}else{
				new ErrorWindow("Nie znaleziono plik�w o podanym rozszerzeniu");
				run = false;
			}
			
			/*
			 * Je�li znaleziono pliki o podanym rozszerzeniu nastepuje ich przetworzenie
			 */
			if(run){
			
				for (File f : lstFiles){
					try {
						
						/*
						 * Je�li plik zawiera �a�cuch znak�w, nale�y go podmieni�,
						 * a nast�pnie dodaje si� ten plik do listy rezultat�w
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
			
			//Je�li nie znaleziono �a�cucha w �adnym pliku wy�wietlany jest komunikat o b��dzie
			if(!res && run)
				new ErrorWindow("W plikach nie znaleziono podanego �a�cucha znak�w");
			
			//Je�li znaleziono pliki i podmieniono �a�cuchy zwracany jest komunikat o sukcesie
			//W przeciwnym wypadku wy�wietlana jest informacja o niepowodzeniu
			if(res && run)
				new ResultWindow("Operacja zako�czona powodzeniem");
			else
				new ResultWindow("Operacja zako�czona niepowodzeniem");
	}

	/*
	 * Metoda przetwarzaj�ca plik podmieniaj�c znaki.
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
        	 * oraz metody replace wyszukuj�cej i zast�puj�cej �a�cuch znak�w.
        	 * Pozwala na podmianie znak�w specjalnych takich jak \n, \t etc.
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
            throw new OwnException("Nie mo�na usun��" + file.getName());
        if (!file.createNewFile())
            throw new OwnException("Nie mo�na stworzy�" + file.getName());
 
         copyFile(tmpFile,file);
 
         if (!tmpFile.delete())
            throw new OwnException("Nie mo�na usun�� " + tmpFile.getName());
	}
	
	/*
	 * Metoda kopiuj�ca plik
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
	 * Metoda sprawdzaj�ca czy plik zawiera podany �a�cuch znak�w
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
        	 * oraz metody countMatches sprawdzaj�cej ile razy 
        	 * �a�cuch znak�w zawiera inny
        	 */
        	
        	n += StringUtils.countMatches(ln, strToFind);
        }
        
        br.close();
        in.close();
        is.close();
        
        //Je�li licznik jest wi�kszy od zera oznacza �e przynajmniej
        //jedna linia pliku zawiera poszukiwany �a�cuch znak�w.
        if(n > 0)
        	ifContains = true;

        
        return ifContains;
	}
}

class OwnException extends IOException{
	
	/*
	 * W�asna klasa wyj�tku
	 */
	
	private static final long serialVersionUID = 1L;

	public OwnException(String err){
		new ErrorWindow(err);
	}
	
}