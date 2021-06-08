package winLose;

import java.awt.*;
import javax.swing.*;

import projektJava.Exit;

@SuppressWarnings("serial")
public class Porazka extends JFrame {

	public static final int X = 400;
    public static final int Y = 200;

	
	public Porazka() {
		setTitle("Porazka :(");
		setSize(X, Y);
		JButton exit = new JButton("Porazka :( Nastepnym razem sie uda!");
		exit.setFont(new Font("Calibri", Font.BOLD, 20));
		Container powZawartosci = getContentPane();
		powZawartosci.add(exit);
		exit.addActionListener(new Exit());
		}
}

