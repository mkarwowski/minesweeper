package pomoc;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Help extends JFrame {
	public static final int X = 700;
    public static final int Y = 260;  
	
	public Help() {
		setTitle("Pomoc");
		setSize(X, Y);
		
		HelpH help = new HelpH();
		Container powZawartosci = getContentPane();
		powZawartosci.add(help);
		}
}

@SuppressWarnings("serial")
class HelpH extends JPanel {
	public void paintComponent(Graphics g)
    {  
       super.paintComponent(g);
 
       g.drawString("Witaj! Ponizej rozpisane s� zasady dzia�ania gry:", 
          W_X, W_Y);
       g.drawString("1. Na planszy rozmieszczone s� statki. Nale�y wykry� gdzie dok�adnie si� znajduj�.", 
    	      W_X, W_Y1);
       g.drawString("2. Klikaj�c na dane pole odsloni si� statek lub cyfra od 0 do 4.", 
 	          W_X, W_Y2);
       g.drawString("3. Cyfra oznacza ile w s�siaduj�cych polach (g�ra, d�, lewo, prawo) znajduje si� cz�ci statk�w.", 
 	          W_X, W_Y3);
       g.drawString("4. Je�eli trafisz na statek przegrasz. Je�eli wiesz gdzie jest statek, omi� go.", 
 	          W_X, W_Y4);
       g.drawString("5. Wygrana nast�pi, gdy odkryjesz wszystkie pola pr�cz statk�w.", 
 	          W_X, W_Y5);
       g.drawString("6. Klikaj�c cofnij, cofnie ci� o ruch. Mozna cofn�� si� tylko o jeden ruch.", 
  	          W_X, W_Y6);
       g.drawString("6. Klikaj�c cofnij cofni�cie cofnie twoje poprzednie cofni�cie.", 
   	          W_X, W_Y7);
       
    }
 
    public static final int W_X = 55;
    public static final int W_Y = 45;
    public static final int W_Y1 = 65;
    public static final int W_Y2 = 85;
    public static final int W_Y3 = 105;
    public static final int W_Y4 = 125;
    public static final int W_Y5 = 145;
    public static final int W_Y6 = 165;
    public static final int W_Y7 = 185;
}
