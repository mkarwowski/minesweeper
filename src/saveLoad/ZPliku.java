package saveLoad;

import java.io.File;
import java.util.Scanner;

public class ZPliku  {
	private Scanner x;
	public int ilosc4;
	public int ilosc3;
	public int ilosc2;
	public int ilosc1;
	public int size;
	
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
		String linia2 = x.nextLine();
		String linia3 = x.nextLine();
		String linia4 = x.nextLine();
		String linia5 = x.nextLine();
		ilosc4 = Integer.valueOf(linia1);
		ilosc3 = Integer.valueOf(linia2);
		ilosc2 = Integer.valueOf(linia3);
		ilosc1 = Integer.valueOf(linia4);
		size = Integer.valueOf(linia5);
	}
	public void close() {
		x.close();
	}
}

