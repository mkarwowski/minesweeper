package saveLoad;

import java.io.PrintWriter;
public class Zapis {
	PrintWriter x;
	public void open() {
		try {
			x = new PrintWriter("zapis.txt");
		}
		catch(Exception e) {
			System.out.println("ERROR");
		}
	}
	public void addInt(int a) {
		x.println(a);
	}
	public void addChar(char a) {
		x.println(a);
	}
	public void close() {
		x.close();
	}
}
