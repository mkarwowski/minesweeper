package projektJava;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

import pomoc.HelpButton;
import saveLoad.Wczytanie;
import saveLoad.ZPliku;
import saveLoad.Zapis;
import winLose.Porazka;
import winLose.Wygrana;

@SuppressWarnings("serial")
public class Plansza extends JFrame {
		
	  Model model = new Model();
	  ZPliku plik = new ZPliku();
	  int size = model.maxSize;
	  JButton tab[][] = new JButton[size][size];
	  JPanel plansza = new JPanel();
	  JPanel sterowanie = new JPanel();
	  JButton help = new JButton("Pomoc / instrukcje");
	  JButton cofnij = new JButton("Cofnij");
	  JButton zapis = new JButton("Zapisz");
	  JButton wczyt = new JButton("Wczytaj ostatnio zapisane");
	  JButton exit = new JButton("Wyjscie");
	  JButton redo = new JButton("Cofnij cofniêcie");
	  JTextField t = new JTextField(10);
	  Cofnij undo = new Cofnij();
	  int ilosc1 = 4;
	  int ilosc2 = 3;
	  int ilosc3 = 2;
	  int ilosc4 = 1;
	  int iloscO = ilosc1 + ilosc2*2 + ilosc3*3 + ilosc4*4;
	  
	  public Plansza() {
	    plik.open();//przypisanie wartosci z pliku
	    plik.read();
	    ilosc1 = plik.ilosc1;
	    ilosc2 = plik.ilosc2;
	    ilosc3 = plik.ilosc3;
	    ilosc4 = plik.ilosc4;
	    size = plik.size;
	    plik.close();
		setTitle("Przed bitwa morska");  
		int i,j;
	    Container cp = getContentPane();
	    cp.setLayout(new GridLayout(1,2));
	    cp.add(plansza); 
	    cp.add(sterowanie);
	    setSize(700,450);
	    setLocation(600,300);
	    sterowanie.setLayout(new GridLayout(8,1));
	    sterowanie.add(t);
	    sterowanie.add(help);
	    sterowanie.add(cofnij);
	    sterowanie.add(redo);
	    sterowanie.add(zapis);
	    sterowanie.add(wczyt);
	    sterowanie.add(exit);
	    redo.addActionListener(new RedoButton());
	    redo.setFont(new Font("Calibri", Font.BOLD, 20));
	    cofnij.addActionListener(new UndoButton());
	    cofnij.setFont(new Font("Calibri", Font.BOLD, 20));
	    exit.addActionListener(new Exit());
	    exit.setFont(new Font("Calibri", Font.BOLD, 20));
	    help.addActionListener(new HelpButton());
	    help.setFont(new Font("Calibri", Font.BOLD, 20));
	    zapis.addActionListener(new ZapisButton());
	    zapis.setFont(new Font("Calibri", Font.BOLD, 20));
	    wczyt.addActionListener(new WczytButton());
	    wczyt.setFont(new Font("Calibri", Font.BOLD, 20));
	    t.setFont(t.getFont().deriveFont(30.0f));
	    plansza.setLayout(new GridLayout(size,size));
	    for (i=0;i<size;i++)
	       for (j=0;j<size;j++){
	    	   tab[i][j]=new JButton();
	    	   plansza.add(tab[i][j]);
	    	   (tab[i][j]).addActionListener(new KlikPlansza(i,j));
	       }
	    for (i = 0;i < size; i++) {
		       for (j = 0;j < size; j++) {
		    	   model.tab[i][j] = 'N';
		    	   tab[i][j].setBackground(Color.LIGHT_GRAY);
		       }
		}
	    Random r1 = new Random();
	    Random r2 = new Random();
	    Random r3 = new Random();
	    
	    int random1;
	    int random2;
	    int randomRotation;
	    undo.openWriter();
	    undo.add(-1);
	    undo.add(-1);
	    undo.add(-1);
	    undo.closeWriter();
	    
	    for (int temp = 0; temp < ilosc4; temp++) {//poczworne statki
	    	random1 = r1.nextInt(size);
	    	random2 = r2.nextInt(size);
	    	randomRotation = r3.nextInt(2);
	    	if (randomRotation == 0) {//poziomo
	    		if (random2 != size - 1 && random2 != size - 2 && random2 != size - 3) {
	    			if (model.tab[random1][random2] != 'O' && model.tab[random1][random2 + 1] != 'O' 
	    					&& model.tab[random1][random2 + 2] != 'O' && model.tab[random1][random2 + 3] != 'O') {
	    				if(random1==0 && random2==0 && model.tab[1][0]!='O' && model.tab[1][1]!='O' &&
	    						model.tab[1][2]!='O' && model.tab[1][3]!='O' && model.tab[0][4]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    					model.tab[random1][random2 + 3] = 'O';
	    				}
	    				else if(random1==0 && random2==size-4 && model.tab[0][random2-1]!='O' && model.tab[1][random2]!='O'
	    						&& model.tab[1][random2+1]!='O' && model.tab[1][random2+2]!='O' && model.tab[1][random2+2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    					model.tab[random1][random2 + 3] = 'O';
	    				}
	    				else if(random1==size-1 && random2==0 && model.tab[random1-1][0]!='O' && model.tab[random1-1][1]!='O'
	    						&& model.tab[random1-1][2]!='O' && model.tab[random1-1][3]!='O' && model.tab[random1][4]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    					model.tab[random1][random2 + 3] = 'O';
	    				}
	    				else if(random1==size-1 && random2==size-4 && model.tab[random1][random2-1]!='O' &&
	    						model.tab[random1-1][random2]!='O' && model.tab[random1-1][random2+1]!='O' &&
	    						model.tab[random1-1][random2+2]!='O' && model.tab[random1-1][random2+3]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    					model.tab[random1][random2 + 3] = 'O';
	    				}//dotad rogi
	    				else if(random1==0 && random2!=0 && random2!=size-4 && model.tab[0][random2-1]!='O' &&
	    						model.tab[1][random2]!='O' && model.tab[1][random2+1]!='O' && model.tab[1][random2+2]!='O' 
	    						&& model.tab[1][random2+3]!='O' && model.tab[0][random2+4]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    					model.tab[random1][random2 + 3] = 'O';
	    				}
	    				else if(random1==size-1 && random2!=0 && random2!=size-4 && model.tab[random1][random2-1]!='O' &&
	    						model.tab[random1-1][random2]!='O' && model.tab[random1-1][random2+1]!='O' && 
	    						model.tab[random1-1][random2+2]!='O' && model.tab[random1-1][random2+3]!='O' &&
	    						model.tab[random1][random2+4]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    					model.tab[random1][random2 + 3] = 'O';
	    				}
	    				else if(random2==0 && random1!=0 && random1!=size-1 && model.tab[random1-1][0]!='O' &&
	    						model.tab[random1+1][0]!='O' && model.tab[random1-1][1]!='O' &&
	    						model.tab[random1+1][1]!='O' && model.tab[random1-1][2]!='O' &&
	    						model.tab[random1+1][2]!='O' && model.tab[random1-1][3]!='O' &&
	    						model.tab[random1+1][3]!='O' && model.tab[random1][4]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    					model.tab[random1][random2 + 3] = 'O';
	    				}
	    				else if(random2==size-4 && random1!=0 && random1!=size-1 && model.tab[random1][random2-1]!='O'
	    						&& model.tab[random1-1][random2]!='O' && model.tab[random1+1][random2]!='O'
	    						&& model.tab[random1-1][random2+1]!='O' && model.tab[random1+1][random2+1]!='O'
	    						&& model.tab[random1-1][random2+2]!='O' && model.tab[random1+1][random2+2]!='O'
	    						&& model.tab[random1-1][random2+3]!='O' && model.tab[random1+1][random2+3]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    					model.tab[random1][random2 + 3] = 'O';
	    				}
	    				else if(random1!=0 && random1!=size-1 && random2!=0 && random2!=size-4 &&
	    						model.tab[random1][random2-1]!='O' && model.tab[random1-1][random2]!='O' &&
	    						model.tab[random1+1][random2]!='O' && model.tab[random1-1][random2+1]!='O' &&
	    						model.tab[random1+1][random2+1]!='O' && model.tab[random1-1][random2+2]!='O' &&
	    						model.tab[random1+1][random2+2]!='O' && model.tab[random1-1][random2+3]!='O' &&
	    						model.tab[random1+1][random2+3]!='O' && model.tab[random1][random2+4]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    					model.tab[random1][random2 + 3] = 'O';
	    				}
	    				else ilosc4++;
	    			}
	    			else ilosc4++;
	    		}
	    		else ilosc4++;
	    	}
	    	else {//pionowo
	    		if (random1 != size - 1 && random1 != size - 2 && random1 != size - 3) {
	    			if (model.tab[random1][random2] != 'O' && model.tab[random1 + 1][random2] != 'O' 
	    					&& model.tab[random1 + 2][random2] != 'O' && model.tab[random1 + 3][random2] != 'O') {
	    				if (random1==0 && random2==0 && model.tab[0][1]!='O' && model.tab[1][1]!='O' &&
	    						model.tab[2][1]!='O' && model.tab[3][1]!='O' && model.tab[4][0]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    					model.tab[random1 + 3][random2] = 'O';
	    				}
	    				else if(random1==0 && random2==size-1 && model.tab[0][random2-1]!='O' &&
	    						model.tab[1][random2-1]!='O' && model.tab[2][random2-1]!='O' &&
	    						model.tab[3][random2-1]!='O' && model.tab[4][random2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    					model.tab[random1 + 3][random2] = 'O';
	    				}
	    				else if(random1==size-4 && random2==0 && model.tab[random1-1][0]!='O' &&
	    						model.tab[random1][1]!='O' && model.tab[random1+1][1]!='O' &&
	    						model.tab[random1+2][1]!='O' && model.tab[random1+3][1]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    					model.tab[random1 + 3][random2] = 'O';
	    				}
	    				else if(random1==size-4 && random2==size-1 && model.tab[random1-1][random2]!='O' &&
	    						model.tab[random1][random2-1]!='O' && model.tab[random1+1][random2-1]!='O' &&
	    						model.tab[random1+2][random2-1]!='O' && model.tab[random1+3][random2-1]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    					model.tab[random1 + 3][random2] = 'O';
	    				}//dotad rogi
	    				else if(random2==0 && random1!=0 && random1!=size-4 && model.tab[random1-1][0]!='O' &&
	    						model.tab[random1][1]!='O' && model.tab[random1+1][1]!='O' && model.tab[random1+2][1]!='O' 
	    						&& model.tab[random1+3][1]!='O' && model.tab[random1+4][0]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    					model.tab[random1 + 3][random2] = 'O';
	    				}
	    				else if(random2==size-1 && random1!=0 && random1!=size-4 && model.tab[random1-1][random2]!='O'
	    						&& model.tab[random1][random2-1]!='O' && model.tab[random1+1][random2-1]!='O'
	    						&& model.tab[random1+2][random2-1]!='O' && model.tab[random1+3][random2-1]!='O'
	    						&& model.tab[random1+4][random2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    					model.tab[random1 + 3][random2] = 'O';
	    				}
	    				else if(random1==0 && random2!=0 && random2!=size-1 && model.tab[0][random2-1]!='O' &&
	    						model.tab[0][random2+1]!='O' && model.tab[1][random2-1]!='O' &&
	    						model.tab[1][random2+1]!='O' && model.tab[2][random2-1]!='O' &&
	    						model.tab[2][random2+1]!='O' && model.tab[3][random2-1]!='O' &&
	    						model.tab[3][random2+1]!='O' && model.tab[4][random2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    					model.tab[random1 + 3][random2] = 'O';
	    				}
	    				else if(random1==size-4 && random2!=0 && random2!=size-1 && model.tab[random1-1][random2]!='O'
	    						&& model.tab[random1][random2-1]!='O' && model.tab[random1][random2+1]!='O'
	    						&& model.tab[random1+1][random2-1]!='O' && model.tab[random1+1][random2+1]!='O'
	    						&& model.tab[random1+2][random2-1]!='O' && model.tab[random1+2][random2+1]!='O'
	    						&& model.tab[random1+3][random2-1]!='O' && model.tab[random1+3][random2+1]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    					model.tab[random1 + 3][random2] = 'O';
	    				}
	    				else if(random1!=0 && random1!=size-4 && random2!=0 && random2!=size-1 &&
	    						model.tab[random1-1][random2]!='O' && model.tab[random1][random2-1]!='O'
	    						&& model.tab[random1][random2+1]!='O' && model.tab[random1+1][random2-1]!='O'
	    						&& model.tab[random1+1][random2+1]!='O' && model.tab[random1+2][random2-1]!='O'
	    						&& model.tab[random1+2][random2+1]!='O' && model.tab[random1+3][random2-1]!='O'
	    						&& model.tab[random1+3][random2+1]!='O' && model.tab[random1+4][random2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    					model.tab[random1 + 3][random2] = 'O';
	    				}
	    				else ilosc4++;
	    			}
	    			else ilosc4++;
	    		}
	    		else ilosc4++;
	    	}
	    }
	    
	    for (int temp = 0; temp < ilosc3; temp++) {//potrojne statki
	    	random1 = r1.nextInt(size);
	    	random2 = r2.nextInt(size);
	    	randomRotation = r3.nextInt(2);
	    	if (randomRotation == 0) {//poziomo
	    		if (random2 != size - 1 && random2 != size - 2) {
	    			if (model.tab[random1][random2] != 'O' && model.tab[random1][random2 + 1] != 'O' && model.tab[random1][random2 + 2] != 'O') {
	    				if (random1==0 && random2==0 && model.tab[1][0]!='O' && model.tab[1][1]!='O' && model.tab[1][2]!='O'
	    						&& model.tab[0][3]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    				}
	    				else if(random1==0 && random2==size-3 && model.tab[0][random2-1]!='O' && model.tab[1][random2]!='O'
	    						&& model.tab[1][random2+1]!='O' && model.tab[1][random2+2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    				}
	    				else if(random1==size-1 && random2==0 && model.tab[random1-1][0]!='O' && model.tab[random1-1][1]!='O' 
	    						&& model.tab[random1-1][2]!='O' && model.tab[random1][3]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    				}
	    				else if(random1==size-1 && random2==size-3 && model.tab[random1][random2-1]!='O' &&
	    						model.tab[random1-1][random2]!='O' && model.tab[random1-1][random2 + 1]!='O' &&
	    						model.tab[random1-1][random2+2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    				}//dotad rogi
	    				else if(random1==0 && random2!=0 && random2!=size-3 && model.tab[0][random2-1]!='O' &&
	    						model.tab[1][random2]!='O' && model.tab[1][random2+1]!='O' && model.tab[1][random2+2]!='O' &&
	    						model.tab[0][random2+3]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    				}
	    				else if(random2==0 && random1!=0 && random1!=size-1 && model.tab[random1-1][0]!='O' &&
	    						model.tab[random1+1][0]!='O' && model.tab[random1-1][1]!='O' && model.tab[random1+1][1]!='O' &&
	    						model.tab[random1-1][2]!='O' && model.tab[random1+1][2]!='O' && model.tab[random1][3]!='O'){
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    				}
	    				else if (random1==size-1 && random2!=0 && random2!=size-3 && model.tab[random1][random2-1]!='O' &&
	    						model.tab[random1-1][random2]!='O' && model.tab[random1-1][random2+1]!='O' &&
	    						model.tab[random1-1][random2+2]!='O' && model.tab[random1][random2+3]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    				}
	    				else if(random2==size-3 && random1!=0 && random1!=size-1 && model.tab[random1][random2-1]!='O'
	    						&& model.tab[random1-1][random2]!='O' && model.tab[random1+1][random2]!='O' &&
	    						model.tab[random1-1][random2+1]!='O' && model.tab[random1+1][random2+1]!='O' &&
	    						model.tab[random1-1][random2+2]!='O' && model.tab[random1+1][random2+2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    				}
	    				else if(random1!=0 && random1!=size-1 && random2!=0 && random2!=size-3 &&
	    						model.tab[random1][random2-1]!='O' && model.tab[random1-1][random2]!='O' &&
	    						model.tab[random1+1][random2]!='O' && model.tab[random1-1][random2+1]!='O' &&
	    						model.tab[random1+1][random2+1]!='O' && model.tab[random1-1][random2+2]!='O' &&
	    						model.tab[random1+1][random2+2]!='O' && model.tab[random1][random2+3]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    					model.tab[random1][random2 + 2] = 'O';
	    				}
	    					
	    				else ilosc3++;
	    			}
	    			else ilosc3++;
	    		}
	    		else ilosc3++;
	    	}
	    	else {//pionowo
	    		if (random1 != size - 1 && random1 != size - 2) {
	    			if (model.tab[random1][random2] != 'O' && model.tab[random1 + 1][random2] != 'O' && model.tab[random1 + 2][random2] != 'O') {
	    				if(random1==0 && random2==0 && model.tab[0][1]!='O' && model.tab[1][1]!='O' &&
	    						model.tab[2][1]!='O' && model.tab[3][0]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    				}
	    				else if(random1==0 && random2==size-1 && model.tab[0][random2-1]!='O' &&
	    						model.tab[1][random2-1]!='O' && model.tab[2][random2-1]!='O' &&
	    						model.tab[3][random2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    				}
	    				else if(random2==0 && random1==size-3 && model.tab[random1-1][0]!='O' &&
	    						model.tab[random1][1]!='O' && model.tab[random1+1][1]!='O' &&
	    						model.tab[random1+2][1]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    				}
	    				else if(random1==size-3 && random2==size-1 && model.tab[random1-1][random2]!='O' &&
	    						model.tab[random1][random2-1]!='O' && model.tab[random1+1][random2-1]!='O' &&
	    						model.tab[random1+2][random2-1]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    				}//dotad rogi
	    				else if(random2==0 && random1!=0 && random1!=size-3 && model.tab[random1-1][0]!='O' &&
	    						model.tab[random1][1]!='O' && model.tab[random1+1][1]!='O' && 
	    						model.tab[random1+2][1]!='O' && model.tab[random1+3][0]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    				}
	    				else if(random1==0 && random2!=0 && random2!=size-1 && model.tab[0][random2-1]!='O' &&
	    						model.tab[0][random2+1]!='O' && model.tab[1][random2-1]!='O' &&
	    						model.tab[1][random2+1]!='O' && model.tab[2][random2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    				}
	    				else if(random2==size-1 && random1!=0 && random1!=size-3 && model.tab[random1-1][random2]!='O' &&
	    						model.tab[random1][random2-1]!='O' && model.tab[random1+1][random2-1]!='O' &&
	    						model.tab[random1+2][random2-1]!='O' && model.tab[random1+3][random2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    				}
	    				else if(random1==size-3 && random2!=0 && random2!=size-1 && model.tab[random1-1][random2]!='O' &&
	    						model.tab[random1][random2-1]!='O' && model.tab[random1][random2+1]!='O' &&
	    						model.tab[random1+1][random2-1]!='O' && model.tab[random1+1][random2+1]!='O' &&
	    						model.tab[random1+2][random2-1]!='O' && model.tab[random1+2][random2+1]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    				}
	    				else if(random1!=0 && random1!=size-3 && random2!=0 && random2!=size-1 &&
	    						model.tab[random1-1][random2]!='O' && model.tab[random1][random2-1]!='O' &&
	    						model.tab[random1][random2+1]!='O' && model.tab[random1+1][random2-1]!='O' &&
	    						model.tab[random1+1][random2+1]!='O' && model.tab[random1+2][random2-1]!='O' &&
	    						model.tab[random1+2][random2+1]!='O' && model.tab[random1+3][random2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    					model.tab[random1 + 2][random2] = 'O';
	    				}
	    				else ilosc3++;
	    			}
	    			else ilosc3++;
	    		}
	    		else ilosc3++;
	    	}
	    }
	    
	    for (int temp = 0; temp < ilosc2; temp++) {//podwojne statki
	    	random1 = r1.nextInt(size);
	    	random2 = r2.nextInt(size);
	    	randomRotation = r3.nextInt(2);//0 - j++ | 1 - i++
	    	if (randomRotation == 0) {//poziomo
	    		if (random2 != size - 1) {
	    			if (model.tab[random1][random2] != 'O' && model.tab[random1][random2 + 1] != 'O') {
	    				if(random2 == 0 && random1==0 && model.tab[1][0]!='O' && model.tab[1][1]!='O' && model.tab[0][2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    				}
	    				else if(random1==0 && random2==size-2 && model.tab[0][random2-1]!='O' && model.tab[1][random2]!='O' &&
	    						model.tab[1][random2 +1]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    				}
	    				else if(random1==size-1 && random2==0 && model.tab[random1][2]!='O' && model.tab[random1-1][0]!='O' &&
	    						model.tab[random1-1][1]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    				}
	    				else if(random1==size-1 && random2==size-2 && model.tab[random1][random2-1]!='O' &&
	    						model.tab[random1-1][random2]!='O' && model.tab[random1-1][random2+1]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    				}//dotad rogi
	    				else if(random1==0 && random2!=0 && random2!=size-1 && model.tab[0][random2-1]!='O' && 
	    						model.tab[0][random2+2]!='O' && model.tab[1][random2]!='O' && model.tab[1][random2+1]!='O') {
	    								model.tab[random1][random2] = 'O';
	    		    					model.tab[random1][random2 + 1] = 'O';
	    						}
	    				else if(random1!=0 && random2==0 && random1!=size-1 && model.tab[random1-1][0]!='O' &&
	    						 model.tab[random1+1][0]!='O' && model.tab[random1-1][1]!='O' && model.tab[random1+1][1]!='O' &&
	    						 model.tab[random1][2]!='O') { 
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    				}
	    				
	    				else if(random1==size-1 && random2!=0 && random2!=size-2 && model.tab[random1][random2-1]!='O' &&
	    				model.tab[random1][random2+2]!='O' && model.tab[random1-1][random2]!='O' &&
	    				model.tab[random1-1][random2+1]!= 'O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    				}
	    				else if(random2==size-2 && random1!=0 && random1!=size-1 && model.tab[random1][random2-1]!='O' &&
	    						model.tab[random1-1][random2]!='O' && model.tab[random1+1][random2]!='O' &&
	    						model.tab[random1-1][random2+1]!='O' && model.tab[random1+1][random2+1]!='O') {
	    					model.tab[random1][random2] = 'O';
    						model.tab[random1][random2 + 1] = 'O';
	    				}
	    				else if(random1!=0 && random1!=size-1 && random2!=0 && random2!=size-2 && 
	    						model.tab[random1][random2-1]!='O' && model.tab[random1-1][random2]!='O' &&
	    						model.tab[random1+1][random2]!='O' && model.tab[random1-1][random2+1]!='O' &&
	    						model.tab[random1+1][random2+1]!='O' && model.tab[random1][random2+2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1][random2 + 1] = 'O';
	    				}
	    				else ilosc2++;
	    					
	    			}
	    			else ilosc2++;
	    		}
	    		else ilosc2++;
	    	}
	    	else {//pionowo
	    		if (random1 != size - 1) {
	    			if (model.tab[random1][random2] != 'O' && model.tab[random1 + 1][random2] != 'O') {
	    				if (random1==0 && random2==0 && model.tab[0][1]!='O' && model.tab[1][1]!='O' &&
	    						model.tab[2][0]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    				}
	    				else if(random1==0 && random2==size-1 && model.tab[0][random2-1]!='O' && model.tab[1][random2-1]!='O' &&
	    						model.tab[2][random2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    				}
	    				else if(random1==size-2 && random2==0 && model.tab[random1-1][0]!='O' && model.tab[random1][1]!='O' &&
	    						model.tab[random1+1][1]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    				}
	    				else if(random1==size-2 && random2==size-1 && model.tab[random1-1][random2]!='O' &&
	    						model.tab[random1][random2-1]!='O' && model.tab[random1+1][random2-1]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    				}
	    				//dotad rogi
	    				else if(random1!=0 && random2==0 && random1!=size-1 && model.tab[random1-1][0]!='O' && 
	    						model.tab[random1+2][0]!='O' && model.tab[random1][1]!='O' && model.tab[random1+1][1]!='O') {
	    								model.tab[random1][random2] = 'O';
	    		    					model.tab[random1 + 1][random2] = 'O';
	    						}
	    				else if(random1!=0 && random2==size-1 && random1!=size-1 && model.tab[random1-1][random2]!='O' && 
	    						model.tab[random1+2][random2]!='O' && model.tab[random1][random2-1]!='O' && model.tab[random1+1][random2-1]!='O') {
	    								model.tab[random1][random2] = 'O';
	    		    					model.tab[random1 + 1][random2] = 'O';
	    						}
	    				else if(random1==0 && random2!=0 && random2!=size-1 && model.tab[0][random2-1]!='O' &&
	    						model.tab[0][random2+1]!='O' && model.tab[1][random2-1]!='O' && model.tab[1][random2+1]!='O'
	    						&& model.tab[2][random2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    				}
	    				else if(random1==size-2 && random2!=0 && random2!=size-1 && model.tab[random1-1][random2]!='O' &&
	    						model.tab[random1][random2-1]!='O' && model.tab[random1][random2+1]!='O' && 
	    						model.tab[random1+1][random2-1]!='O' && model.tab[random1+1][random2+1]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    				}
	    				else if(random1!=0 && random1!=size-2 && random2!=0 && random2!=size-1 &&
	    						model.tab[random1-1][random2]!='O' && model.tab[random1][random2-1]!='O' &&
	    						model.tab[random1][random2+1]!='O' && model.tab[random1+1][random2-1]!='O' &&
	    						model.tab[random1+1][random2+1]!='O' && model.tab[random1+2][random2]!='O') {
	    					model.tab[random1][random2] = 'O';
	    					model.tab[random1 + 1][random2] = 'O';
	    				}
	    				else ilosc2++;
	    			}
	    			else ilosc2++;
	    		}
	    		else ilosc2++;
	    	}
	    }
	    
	    for (int temp = 0; temp < ilosc1; temp++) {//pojedyncze statki
	    	random1 = r1.nextInt(size);
	    	random2 = r2.nextInt(size);
	    	if (model.tab[random1][random2] != 'O') {
	    		if (random1 == 0 && random2 == 0 && model.tab[1][0] != 'O' && model.tab[0][1] != 'O')
	    		model.tab[random1][random2] = 'O';
	    		else if (random1 == 0 && random2 != 0 && random2 != size - 1 && model.tab[0][random2 - 1] != 'O' && 
	    				model.tab[0][random2 + 1] != 'O' && model.tab[1][random2] != 'O')
	    			model.tab[random1][random2] = 'O';
	    		else if (random1 == size - 1 && random2 == size -1 && model.tab[random1 - 1][random2] != 'O' &&
	    				model.tab[random1][random2]!='O')
	    			model.tab[random1][random2] = 'O';
	    		else if (random1 == size -1 && random2 == 0 && model.tab[random1 - 1][0] !='O' && model.tab[random1][1]!='O')
	    			model.tab[random1][random2] = 'O';
	    		else if (random1 == 0 && random2 == size-1 && model.tab[0][random2-1] !='O' && model.tab[1][random2]!='O')
	    			model.tab[random1][random2] = 'O';//dotad sa rogi
	    		else if (random1 != 0 && random2 == 0 && random1 != size - 1 && model.tab[random1 - 1][0] != 'O' && 
	    				model.tab[random1 + 1][0]!='O' && model.tab[random1][1] != 'O') 
	    			model.tab[random1][random2] = 'O';
	    		else if (random1 == 0 && random2 != 0 && random2 != size - 1 && model.tab[0][random2-1] != 'O' && 
	    				model.tab[0][random2+1]!='O' && model.tab[1][random2] != 'O') 
	    			model.tab[random1][random2] = 'O';
	    		else if (random1 ==size -1 && random2 !=0 && random2!=size-1 && model.tab[random1][random2+1]!='O' &&
	    				model.tab[random1][random2-1]!='O' && model.tab[random1 -1][random2]!='O')
	    			model.tab[random1][random2] = 'O';
	    		else if (random1 !=size -1 && random1 !=0 && random2==size-1 && model.tab[random1+1][random2]!='O' &&
	    				model.tab[random1-1][random2]!='O' && model.tab[random1][random2 -1]!='O')
	    			model.tab[random1][random2] = 'O';
	    		else if (random1!=0 && random2!=0 && random1!=size-1 && random2!=size-1 && model.tab[random1-1][random2]!='O' &&
	    				model.tab[random1+1][random2]!='O' && model.tab[random1][random2+1]!='O' && model.tab[random1][random2 -1]!='O')
	    			model.tab[random1][random2] = 'O';
	    		else ilosc1++;
	    	}
	    	else ilosc1++;
	    }
	    
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	  }
	  
	  class ZapisButton implements ActionListener {//zapisywanie stanu tablicy
		  Zapis save = new Zapis();
		  public void actionPerformed(ActionEvent e) {
			save.open();
			save.addInt(size);
			for(int temp1 = 0; temp1 < size; temp1++) {
				for(int temp2 = 0; temp2 < size; temp2++) {
					save.addChar(model.tab[temp1][temp2]);
				}
			}
			save.close();
		  } 
	  }
	  class WczytButton implements ActionListener {
		Wczytanie load = new Wczytanie();
		public void actionPerformed(ActionEvent e) {
			load.open();
			load.read();
			//size = load.size;
			/*for(int temp1 = 0; temp1 < size; temp1++) {
				for(int temp2 = 0; temp2 < size; temp2++) {
					model.tab[temp1][temp2]=load.tab[temp1][temp2];
				}
			}*/
			load.close();
		}
	  }
	  class UndoButton implements ActionListener {
		  int i,j;
		  public void actionPerformed(ActionEvent e) {
			undo.openScanner();
			undo.read();
			i = undo.i;
			j = undo.j;
			undo.closeScanner();
			if (model.tab[i][j]=='S') {
					model.tab[i][j] = 'N';
					(tab[i][j]).setText("");
					(tab[i][j]).setBackground(Color.LIGHT_GRAY);
					liczbaNieO++;
					t.setText("Pozosta³o pustych pól: " + liczbaNieO);
			}
			/*else if(model.tab[i][j]=='O') {
				model.tab[i][j] = 'O';
				(tab[i][j]).setText("");
				(tab[i][j]).setBackground(Color.LIGHT_GRAY);
				liczbaNieO++;
			}*/
			
		} 
	  }
	  
	  class RedoButton implements ActionListener {
		  int i, j, s;
		  public void actionPerformed(ActionEvent e) {
			  undo.openScanner();
				undo.read();
				i = undo.i;
				j = undo.j;
				s = undo.sasiady;
				undo.closeScanner();
				if (s == -1 || i == -1 || j == - 1) {
					t.setText("Nie ma co cofaæ!");
				}
				else if (model.tab[i][j]=='N') {
					String a = Integer.toString(s);
					model.tab[i][j] = 'S';
					(tab[i][j]).setText(a);
					(tab[i][j]).setBackground(Color.CYAN);
					liczbaNieO--;
					t.setText("Pozosta³o pustych pól: " + liczbaNieO);
				}
		}
		   
	  }
	  
	  int liczbaO = iloscO;
	  int liczbaNieO = size*size - iloscO;
	  class KlikPlansza implements ActionListener {
	    int i,j;
	    
	    KlikPlansza(int i, int j) {
	    	
	    	this.i = i;
	    	this.j = j;
	    	plik.open();
		    plik.read();
		    
		    ilosc1 = plik.ilosc1;
		    ilosc2 = plik.ilosc2;
		    ilosc3 = plik.ilosc3;
		    ilosc4 = plik.ilosc4;
		    iloscO = ilosc1 + ilosc2*2 + ilosc3*3 + ilosc4*4;
		    liczbaNieO = size*size - iloscO;
		    plik.close();
		    
	    }  
	    
	      public void actionPerformed(ActionEvent e) {
	    	  
	    	  if (model.tab[i][j] == 'N') {
	    		  undo.openWriter();
	  				undo.add(i);
	  				undo.add(j);
	    		  liczbaNieO--;
	    		  model.tab[i][j] = 'S';
	    		  (tab[i][j]).setBackground(Color.CYAN);
	    		  int sasiady = 0;
	    		  if(i == 0 && j == 0) {
	    			  if (model.tab[0][1] == 'O') sasiady++;
	    			  if (model.tab[1][0] == 'O') sasiady++;
	    		  }
	    		  else if(i == 0 && j == size - 1) {
	    			  if (model.tab[1][size - 1] == 'O') sasiady++;
	    			  if (model.tab[0][size - 2] == 'O') sasiady++;
	    		  }
	    		  else if(i == size - 1 && j == 0) {
	    			  if (model.tab[size - 1][1] == 'O') sasiady++;
	    			  if (model.tab[size - 2][0] == 'O') sasiady++;
	    		  }
	    		  else if(i == size - 1 && j == size - 1) {
	    			  if (model.tab[size - 1][size - 2] == 'O') sasiady++;
	    			  if (model.tab[size - 2][size - 1] == 'O') sasiady++;
	    		  }
	    		  else if(i == 0 && j != 0 && j != size - 1) {
	    			  if (model.tab[0][j - 1] == 'O') sasiady++;
	    			  if (model.tab[0][j + 1] == 'O') sasiady++;
	    			  if (model.tab[1][j] == 'O') sasiady++;
	    		  }
	    		  else if(j == 0 && i != 0 && i != size - 1) {
	    			  if (model.tab[i - 1][0] == 'O') sasiady++;
	    			  if (model.tab[i + 1][0] == 'O') sasiady++;
	    			  if (model.tab[i][1] == 'O') sasiady++;
	    		  }
	    		  else if (i == size - 1 && j != 0 && j != size - 1) {
	    			  if (model.tab[i][j - 1] == 'O') sasiady++;
	    			  if (model.tab[i][j + 1] == 'O') sasiady++;
	    			  if (model.tab[i - 1][j] == 'O') sasiady++;
	    		  }
	    		  else if (j == size - 1 && i != 0 && i != size - 1) {
	    			  if (model.tab[i - 1][j] == 'O') sasiady++;
	    			  if (model.tab[i + 1][j] == 'O') sasiady++;
	    			  if (model.tab[i][j - 1] == 'O') sasiady++;
	    		  }
	    		  else {
	    			  if (model.tab[i][j - 1] == 'O') sasiady++;
	    			  if (model.tab[i][j + 1] == 'O') sasiady++;
	    			  if (model.tab[i - 1][j] == 'O') sasiady++;
	    			  if (model.tab[i + 1][j] == 'O') sasiady++;
	    			  /*if (i != 0 && i != size - 1 && j != 0 && j != size - 1) {
	    			  if (model.tab[i][j - 1] == 'O') sasiady++;
	    			  if (model.tab[i][j + 1] == 'O') sasiady++;
	    			  if (model.tab[i - 1][j] == 'O') sasiady++;
	    			  if (model.tab[i + 1][j] == 'O') sasiady++;}*/
	    		  }
	    		  String str = Integer.toString(sasiady);
	    		  undo.add(sasiady);
	    		  undo.closeWriter();
	    		  (tab[i][j]).setText(str);
	    		  
	    	  }
	    	  if (model.tab[i][j] == 'O') {//porazka
	    		  t.setText(" PRZEGRALES!");
	    
	    		  if ((tab[i][j]).getBackground()!= Color.RED) {
	    			  liczbaO--;
	    		  }
	    		  for(int temp1 = 0; temp1 < size; temp1++) {
    				  for(int temp2 = 0; temp2 <size; temp2++) {
    					  if (model.tab[temp1][temp2]=='O') (tab[temp1][temp2]).setBackground(Color.RED);
    					  
    					  
    				  }
    			  }
	    		  (tab[i][j]).setBackground(Color.RED);
	    		  Porazka por = new Porazka();
	    		  por.setLocation(400,300);
	    		  por.setVisible(true);
	    	  }
	    	  else { 
	    		  t.setText("Pozosta³o pustych pól: " + liczbaNieO);
	    		  if (liczbaNieO == 0) {//wygrana
	    			  t.setText("WYGRALES!");
	    			  for(int temp1 = 0; temp1 < size; temp1++) {
	    				  for(int temp2 = 0; temp2 <size; temp2++) {
	    					  if ((tab[temp1][temp2]).getBackground()==Color.LIGHT_GRAY) (tab[temp1][temp2]).setBackground(Color.RED);
	    					  else (tab[temp1][temp2]).setBackground(Color.BLUE);
	    					  
	    				  }
	    			  }
	    			  Wygrana wyg = new Wygrana();
					  wyg.setLocation(400,300);
					  wyg.setVisible(true);
	    		  }
	    	  	}
	    	  }
	    }
	   

	 public static void main(String[] args) {
	    JFrame f = new Plansza();
	    f.setSize(1200,600);
	    f.setLocation(150,150);
	    f.setVisible(true);
	    
	  }
	} 
//autor: Mariusz Karwowski