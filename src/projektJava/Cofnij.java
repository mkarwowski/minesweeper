package projektJava;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
public class Cofnij {
	PrintWriter x;
	private Scanner y;
	public int i = -1,j = -1, sasiady = -1;//zwroci -1 kiedy nic nie zostanie zrobione albo bedzie error
	public void openWriter() {
		try {
			x = new PrintWriter("ruch.txt");
		}
		catch(Exception e) {
			System.out.println("ERROR");
		}
	}
	public void openScanner() {
		try {
			y = new Scanner(new File("ruch.txt"));
			
		}
		catch(Exception e) {
			System.out.println("ERROR");
		}
		}
	public void add(int a) {
		x.println(a);
	}
	public void read() {
		String linia1 = y.nextLine();
		String linia2 = y.nextLine();
		String linia3 = y.nextLine();
		i = Integer.valueOf(linia1);
		j = Integer.valueOf(linia2);
		sasiady = Integer.valueOf(linia3);
		
	}
	public void closeWriter() {
		x.close();
	}
	public void closeScanner() {
		y.close();
	}
}
