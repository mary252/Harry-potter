package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import harrypotter.model.character.Champion;
import harrypotter.model.character.Wizard;

@SuppressWarnings("serial")
public class gryffinfo extends Champinfo{
	BufferedImage bk;
	public gryffinfo(Champion champ) throws IOException{
		super(champ);
		try {
			bk = ImageIO.read(new File("assets/gryffinfo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GraphicsEnvironment ge =GraphicsEnvironment.getLocalGraphicsEnvironment();
	    File font=new File("assets/HARRP___.TTF");
	    File Font1=new File("assets/QaskinBlack_PersonalUse.ttf");
	     try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,font));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,Font1));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Font Magic = null;
	    Font elegant=null;
		try {
			Magic = Font.createFont(Font.TRUETYPE_FONT,font).deriveFont(48f);
			elegant = Font.createFont(Font.TRUETYPE_FONT,Font1).deriveFont(48f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setOpaque(false);
		//setText(champ.toString());
		String t=((Wizard)champ).getName();
	    JLabel n=new JLabel("<html><span style='font-size:20px'>"+t+"</span></html>");
	    n.setFont(Magic);
	    this.setLayout(new BorderLayout());
	    //n.setSize(70, 50);
	    //n.setHorizontalAlignment(.CENTER);
	    n.setForeground(Color.WHITE);
	    n.setBorder(BorderFactory.createEmptyBorder(10,20, 5, 5));
	   // n.setBackground(BorderFactory.createEmptyBorder(top, left, bottom, right));
	    this.add(n,BorderLayout.NORTH);
	    JTextArea in=new JTextArea();
	    in.setText(champ.toString());
	    in.setOpaque(false);
	    in.setForeground(Color.WHITE);
	    in.setEditable(false);
	    in.setBorder(BorderFactory.createEmptyBorder(25, 15, 5, 5));
	    this.add(in,BorderLayout.CENTER);
	    //intro.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	    
		//this.setVerticalTextPosition(JButton.CENTER);
		//this.setHorizontalTextPosition(JButton.CENTER);
	}
	public void paintComponent(Graphics g) {
		
		
		
	    super.paintComponent(g);
	        g.drawImage(bk,0, 0, null);
	}
}
