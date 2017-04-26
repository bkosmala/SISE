package puzzleSolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IOOperations {
	
	public static int[][] wczytajZPliku(String url)
	{
		File plik = new File(url);

		System.out.println("Odczyt z pliku:");
		String linia="";
		ArrayList<String> input = new ArrayList<String>();
		try {
			FileReader strumienOdczytu = new FileReader(plik);	// Konstrukcja i otwarcie strumienia odczytujacego
			BufferedReader bufor = new BufferedReader(strumienOdczytu);
		     while((linia = bufor.readLine()) != null){
		         System.out.println(linia);
		         input.add(linia);
		     }
			strumienOdczytu.close();
		}		
		catch (FileNotFoundException io)												
			{System.out.println(io.getMessage());}
		catch (IOException io)												
		{System.out.println(io.getMessage());} 

		int counter = 0;
		String tab[] = {};
		int [][] result={};
		/*if(input.size()>0)
		{
			System.out.println("Odkodowane:");
			String[] firstRow = input.get(0).split(" ");
			System.out.println("");
			
		result = new int[Integer.parseInt(firstRow[0])][Integer.parseInt(firstRow[1])];
		for(String s : input)
		{
			tab = s.split(" ");
			for(int k=0;k<tab.length;k++)
			{
				result[counter][k] = Double.parseDouble(tab[k]);
				System.out.print(result[counter][k] + "\n");
			}
			counter++;
			//System.out.println("");	
		}
		}*/
		
		return result;
	}

}
