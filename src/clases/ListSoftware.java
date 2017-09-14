package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import constants.FILE;


public class ListSoftware implements FILE {

	public ListSoftware() {
		// TODO Auto-generated constructor stub
	}
	
	private String s=null;
	private char[] arrayChar=null;
	
	public void list_software() throws IOException {
		
		//Comando a ejecutar - Busca las lineas donde aparece Name=
		Process p = Runtime.getRuntime().exec("grep -Hirnm 1 Name= /usr/share/applications");
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		//Crear folder
		File folder = new File(FOLDER_LIST);
		folder.mkdirs();
		File f = new File(LIST_SOFT);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		PrintWriter pw = new PrintWriter(bw);
		
		
		
		while ((s = br.readLine()) != null) {  
			
			//Pasaremos los caracteres en un Array Char
			arrayChar=s.toCharArray();
			for(int i=0; i<arrayChar.length; i++){
				
				//Empezara a guardar todos los caracteres despues del igual
				//Despues del igual es donde espiesa escribir en nombre de cada software instalado
				if( arrayChar[i] == '=')
				{  i++; 
					for(int j=i; j<arrayChar.length; j++)
					{
						pw.append(arrayChar[j]);
					}
					pw.append("\n");
				}
			}
			
		}
    
		pw.close();
	
	}
	
	public void save_dir_soft() throws IOException{
		
		//Aqui se guarda el directorio de cada software
		Process p = Runtime.getRuntime().exec("grep -Hirnm 1 Name= /usr/share/applications");
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		//Crear folder
		File folder = new File(FOLDER_DIR);
		folder.mkdirs();
		File f = new File(LIST_SOFT_DIR);
		//f.mkdirs();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		PrintWriter pw = new PrintWriter(bw);
		
		while ((s = br.readLine()) != null) {  
			
			//Pasaremos los caracteres en un Array Char
			arrayChar=s.toCharArray();
			for(int i=0; i<arrayChar.length; i++){
				
				
				if( arrayChar[i] == ':')
				{ 
					/*
					 * Detectera los primeros ":" que se impriman en la lina
					 * Dara un salto de linea y break para continuar leyendo
					 * la siguiente linea
					 */
					
					pw.append("\n");
					break;
					
				}else {
					pw.append(arrayChar[i]);
				}
			}
			
		}
    
		pw.close();
		
	}
	
	
	public void print_list_soft() throws IOException {
		//Se imprimira la lista de programas
		FileReader fr = new FileReader(LIST_SOFT);
		BufferedReader bf = new BufferedReader(fr);
		
		System.out.println("\n\nLista de programas\n");
		int counter=0;
		while((s=bf.readLine())!=null)
		{	counter++;
			System.out.println(counter+") "+s);
		}
		
		bf.close();
	}
	
}
