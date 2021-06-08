package pomoc;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HelpButton implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		Help help = new Help();
		help.setVisible(true);
	}
	
}
