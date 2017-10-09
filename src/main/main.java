package main;

import clases.ListSoftware;
import clases.Changer;
import java.io.*;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner reading =new Scanner(System.in);
		int OpMenu=0;
		boolean ups=true;
		ListSoftware ls = new ListSoftware();
		Changer ch = new Changer();

		System.out.println("\nCon esta herramienta puedes cambiar el tema de cada programa.\n");


		do {

			do {

				ups=true;

				try {

					System.out.print("[ MENU ]\n"
					+ "1) Mostrar aplicaciones\n"
					+ "2) Cambiar tema\n"
					+ "3) Salir\n"
					+ "Selecionar opcion: ");

					OpMenu=Integer.parseInt(reading.nextLine());

					switch(OpMenu)
					{

					case 1:
						ls.list_software(); //Muestra la lista de los programas
						ls.save_dir_soft();
						ls.print_list_soft();
						pause();
						break;
					case 2:
						ch.print_list_soft();
						ch.print_select_soft();
						ch.list_themes();
						ch.print_theme();
						ch.select_print_theme();
						ch.end_theme();
						pause();
						break;
					case 3:
						break;
			}




		}catch(Exception e) {
			System.err.println("Error: "+e.getMessage());
            ups=false;

		}

			}while(OpMenu!=3);
		}while(ups!=true);

	}

	public static void pause() {
		System.out.println("Presione [ ENTER ] para continuar.");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
