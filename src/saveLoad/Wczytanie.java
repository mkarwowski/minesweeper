package saveLoad;

import java.io.File;
import java.util.Scanner;

public class Wczytanie {
	private Scanner x;
	public int size;
	public char tab[][] = new char[size][size];
	public void open() {
		try {
			x = new Scanner(new File("dane.txt"));
		}
		catch(Exception e) {
			System.out.println("ERROR");
		}	
	}
	public void read() {
		String linia1 = x.nextLine();
		size = Integer.valueOf(linia1);
		String linia[][] = new String[size][size];
		
		for(int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				linia[i][j] = x.nextLine();
				tab[i][j] = linia[i][j].charAt(j);
			}
		}
	}
	public void close() {
		x.close();
	}
}
