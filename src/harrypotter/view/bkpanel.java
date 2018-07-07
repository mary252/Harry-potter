package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class bkpanel extends JPanel{
	BufferedImage bk;
	public bkpanel(){
		try {
			bk = ImageIO.read(new File("assets/bk1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLayout(new BorderLayout());
	}
public void paintComponent(Graphics g) {
		
		
		
	    super.paintComponent(g);
	        g.drawImage(bk,0, 0, null);
	}
}
