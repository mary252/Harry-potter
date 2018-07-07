package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.SwingConstants;

public class spellpanel extends JPanel{
	BufferedImage bk;
	JPanel spells;
	public spellpanel() throws IOException{
		try {
			bk = ImageIO.read(new File("assets/spellpan.png"));
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
		
	    JLabel n=new JLabel("<html><span style='font-size:20px'>"+"Spells"+"</span></html>");
	    n.setFont(Magic);
	    this.setLayout(new BorderLayout());
	    //n.setSize(70, 50);
	    this.setPreferredSize(new Dimension(365,195));
	    n.setHorizontalAlignment(SwingConstants.CENTER);
	    n.setForeground(Color.WHITE);
	    n.setBorder(BorderFactory.createEmptyBorder(10,5, 5, 5));
	    this.add(n,BorderLayout.NORTH);
	    spells=new JPanel();
	    spells.setLayout(new FlowLayout());
	    spells.setOpaque(false);
	    this.add(spells, BorderLayout.CENTER);
	}
	public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
		    g.drawImage(bk,0, 0, null);
		}
	
}
