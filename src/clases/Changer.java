package clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import constants.FILE;

public class Changer implements FILE{

	public Changer() {
		// TODO Auto-generated constructor stub
	}
	
	private String s=null;
	private int counter=0;
	private String list_theme[];
	private String name_theme=null;
	private String destino=null;
	private String desti=null;
	private String THEME="Exec=env GTK_THEME=";
	private static StringBuffer txtNombre;
	private static char[] arrayChar1=null;
	
	public void print_list_soft() throws IOException
	{
		counter=0;
		//Se imprimira la lista de programas
		FileReader fr = new FileReader(LIST_SOFT);
		BufferedReader bf = new BufferedReader(fr);
		
		System.out.println("\n\nLista de programas\n");
		while((s=bf.readLine())!=null)
		{	counter++;
			System.out.println(counter+") "+s);
		}
		
		bf.close();
		
	}
	
	@SuppressWarnings("null")
	public void print_select_soft() throws IOException {
		
		@SuppressWarnings("resource")
		Scanner reading = new Scanner(System.in);
		
		FileReader fr1 = new FileReader(LIST_SOFT);
		BufferedReader bf1 = new BufferedReader(fr1);
		
		System.out.print("Seleccione la APP: ");
		int NumApp=Integer.parseInt(reading.nextLine());
		counter=0;
		
		String nameSearch = null;
		while((s=bf1.readLine())!=null)
		{
			counter++;
			if(NumApp==counter) {
				nameSearch=s;
			}
		}
		
		bf1.close();
		System.out.println("\n\nSelecciono \""+nameSearch+"\"\n\n");
		
		
		/*
		 * Aqui es donde se copia el archivo desktop del direcotiro raiz a nuestro home
		 */
		
		FileReader fr2 = new FileReader(LIST_SOFT_DIR);
		BufferedReader bf2 = new BufferedReader(fr2);
		
		counter=0;
		

		while((s=bf2.readLine())!=null)
		{
			counter++;
			if(NumApp==counter) {
				nameSearch=s;
			}
		}
		
		bf2.close();
		
		
		/*
		 * 
		 * Aqui leera el archivo desktop
		 * 
		 * 
		 */
		list_desktop();
		String list_desktop=null;
		//Se imprimira la lista de programas
				FileReader fr = new FileReader(LIST_DESKTOP);
				BufferedReader bf = new BufferedReader(fr);
						
				
				int c=0;
				while((s=bf.readLine())!=null){
					
					if(c<NumApp){
						
						list_desktop=s;
						
						c++;
						}
					}
				bf.close();
				
				System.out.print("\nSe estara creando el archivo: "+list_desktop);
		
	
		 File origen = new File(nameSearch);
		 destino=HOME_APPLICATIONS+list_desktop;
	
		 
		 desti=destino;
         File destino = new File(desti);

         try {
                 InputStream in = new FileInputStream(origen);
                 OutputStream out = new FileOutputStream(destino);
                         
                 byte[] buf = new byte[1024];
                 int len;

                 while ((len = in.read(buf)) > 0) {
                         out.write(buf, 0, len);
                 }
         
                 in.close();
                 out.close();
         } catch (IOException ioe){
                 ioe.printStackTrace();
         }
        	
		
	}
	

	
	public void list_desktop() throws IOException {
		char[] arrayChar_desktop;
		String s;
		//Aqui se guarda el directorio de cada software
		Process p = Runtime.getRuntime().exec("grep -Hirnm 1 Name= /usr/share/applications");
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

		File f = new File(LIST_DESKTOP);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		PrintWriter pw = new PrintWriter(bw);
		int contador=0;
		while ((s = br.readLine()) != null) {  
			
			//Pasaremos los caracteres en un Array Char
			arrayChar_desktop=s.toCharArray();
			for(int i=0; i<arrayChar_desktop.length; i++){
				
				
				if( arrayChar_desktop[i] == '/')
				{ 
					
					/*
					 * Detectera los primeros ":" que se impriman en la lina
					 * Dara un salto de linea y break para continuar leyendo
					 * la siguiente linea
					 */
					
					contador++;
					
	
				}
				
				else if(contador==4) {
					
					
					if(arrayChar_desktop[i] == ':') {
						pw.append("\n");
						break;
						}
						else {
							pw.append(arrayChar_desktop[i]);
						}
					
					
					
				}
			}
			
			contador=0;	
			
		}
    
		pw.close();
	}
	

	
	//Aqui es donde empezara a cambiar el tema GTK de la app seleccionada

	
	
	public void list_themes() throws IOException {
		
		// Comando a ejecutar fing /usr/share/themes/ -name *.theme 
		counter=0;
		String commands[] = {"find","/usr/share/themes/","-name", "*.theme"}; 
		Process process = new ProcessBuilder(commands).start(); // se crea el proceso

		// Se lee la salida
		//Aqui estan todos los direcotrio con temas GTK
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader brr = new BufferedReader(isr);
		File folder = new File(FOLDER_DIR);
		folder.mkdirs();
		File f = new File(LIST_THEMES_DIR);
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		PrintWriter pw = new PrintWriter(bw);
		
	
		while ((s = brr.readLine()) != null) {
			
			pw.append(s+"\n");
			counter++;
		}
		pw.close();
		
		//Guarda los directorios en una matriz
		list_theme =new String[counter];
		
		
		//Se imprimira la lista de programas
		FileReader fr = new FileReader(LIST_THEMES_DIR);
		BufferedReader bf = new BufferedReader(fr);
				
		
		int c=0;
		while((s=bf.readLine())!=null){
			//
			if(c<counter){
				
				list_theme[c]=s;
				
				c++;
				}
			}
		bf.close();
		
		save_list_theme();
		
	}
	
	public void save_list_theme() throws IOException {
		
		//Imprimite lista bonita de temas
		File folder = new File(FOLDER_LIST);
		folder.mkdirs();
		File f = new File(LIST_THEMES);
		//f.mkdir();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		PrintWriter pw = new PrintWriter(bw);
		for(int cont=0; cont<counter; cont++) {
			Process p = Runtime.getRuntime().exec("grep -Hirnm 2 Name= "+list_theme[cont]);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

			
			while ((s = br.readLine()) != null) {
			
				char[] ArrayChar=s.toCharArray();
				for(int i=0; i<ArrayChar.length; i++){
					//
					if( ArrayChar[i] == '='){
						i++; 
						for(int j=i; j<ArrayChar.length; j++){
							pw.append(ArrayChar[j]);
				
							
						}
						
						pw.append("\n");
						
					}
					
				}
				
			}
			
		}
		
		pw.close();
		
	}
	
	public void print_theme() throws IOException {
	
		counter=0;
		//Se imprimira la lista de programas
		FileReader fr = new FileReader(LIST_THEMES);
		BufferedReader bf = new BufferedReader(fr);
		
		System.out.println("\n\nLista de temas\n");
		while((s=bf.readLine())!=null)
		{	counter++;
			System.out.println(counter+") "+s);
		}
		System.out.println("\n\n\n");
		bf.close();
		
		
	}
	
	public void select_print_theme() throws IOException {
		@SuppressWarnings("resource")
		Scanner reading = new Scanner(System.in);
		
		FileReader fr = new FileReader(LIST_THEMES);
		BufferedReader bf = new BufferedReader(fr);
		
		System.out.print("Seleccione el tema que quiere poner: ");
		int NumTheme=Integer.parseInt(reading.nextLine());
		counter=0;
		
		String nameSearch = null;
		while((s=bf.readLine())!=null)
		{
			counter++;
			if(NumTheme==counter) {
				nameSearch=s;
			}
		}
		
		bf.close();
		name_theme=nameSearch;
		System.out.println("\n\nSelecciono \""+nameSearch+"\"\n\n");
	}
	

	public void Search_Exec_method() throws IOException {
		
		//Comando a ejecutar - Busca las lineas donde aparece Name=
		Process p = Runtime.getRuntime().exec("grep -Hirnm 1 Exec= "+destino);
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		//Crear folder
		File f = new File("notes.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		PrintWriter pw = new PrintWriter(bw);
		
		String sl = null;
		txtNombre = new StringBuffer();
		
		String s;
		while ((s = br.readLine()) != null) {  
			
			//Pasaremos los caracteres en un Array Char
			arrayChar1 = s.toCharArray();
			for(int i=0; i<arrayChar1.length; i++){
				
				//Empezara a guardar todos los caracteres despues del igual
				//Despues del igual es donde espiesa escribir en nombre de cada software instalado
				if( arrayChar1[i] == '=')
				{  i++; 
					for(int j=i; j<arrayChar1.length; j++)
					{
						pw.append(arrayChar1[j]);
						sl=String.valueOf(arrayChar1[j]);
						txtNombre.append(sl);
					}
					pw.append("\n");
				}
			}
			
		}
    
		pw.close();
		
	}
		
		
	public void replaceText(String replace_text_with) {
		
		 try {
		        BufferedReader file = new BufferedReader(new FileReader(destino));
		        String line;
		        StringBuffer inputBuffer = new StringBuffer();  

		        while ((line = file.readLine()) != null) {
		            inputBuffer.append(line); //Se ingresa el contenido del archivo
		            inputBuffer.append('\n');
		        }
		        String inputString = inputBuffer.toString();

		        file.close();

		       

		        inputString = inputString.replace(replace_text_with, THEME+name_theme+" "+txtNombre.toString());
		        
		     

		        // Escribe el nuevo String con la linea remplazada sobre el mismo archivo
		        FileOutputStream fileOut = new FileOutputStream(destino);
		        fileOut.write(inputString.getBytes());
		        fileOut.close();

		    } catch (Exception e) {
		        System.out.println("Problema al cargar el archivo.");
		    }
	   
	}
	
	
	
	public void end_theme() {
		
		try {
			Search_Exec_method();
		
			replaceText("Exec="+txtNombre.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	
	}
	
	
	
}
