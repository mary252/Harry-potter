package harrypotter.view;

import java.awt.Color;
import java.awt.Dimension;

import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Champinfo extends JPanel {
	private Champion champ;
	public Champion getChamp() {
		return champ;
	}
	public Champinfo(Champion c){
		super();
		this.champ=c;
		//setText(champ.toString());
	    //JLabel n=new JLabel("<html><span style='font-size:20px'>"+"What is your name"+"</span></html>");
		
		//setBorder(new TitledBorder(new LineBorder(Color.black, 5), ((Wizard)c).getName()));
		//setEditable(false);
		this.setPreferredSize(new Dimension(281,231));
	}

}
