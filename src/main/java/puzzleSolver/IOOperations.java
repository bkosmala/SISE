package puzzleSolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IOOperations {
	
	public static int[][] readFromFile(String url)
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
		if(input.size()>0)
		{
			System.out.println("Odkodowane:");
			String[] firstRow = input.get(0).split(" ");
			
		result = new int[Integer.parseInt(firstRow[0])][Integer.parseInt(firstRow[1])];
		for(int i = 1; i<input.size();i++)
		{
			tab = input.get(i).split(" ");
			for(int k=0;k<tab.length;k++)
			{
				result[counter][k] = Integer.parseInt(tab[k]);
				System.out.print(result[counter][k] + " ");
			}
			counter++;
			System.out.println("");			
		}
		}
		
		return result;
	}
	
	public static void writeToFile(String url, String[] lines)
	{
		File plik = new File(url);
		
		try	{
			plik.createNewFile();					
			FileWriter strumienZapisu = new FileWriter(plik);
			for(int i=0;i<lines.length;i++)
			{
			strumienZapisu.write(lines[i]);	
			strumienZapisu.write(System.lineSeparator());
			}
			strumienZapisu.close(); 				
		}
		catch (IOException io)												
		{System.out.println(io.getMessage());}

		catch (Exception se)
		{System.err.println("blad sec");}
	}
}
