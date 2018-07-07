package harrypotter.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;

@SuppressWarnings("serial")
public class SpellButton extends JToggleButton {
   Spell s;
   BufferedImage bk;
   public SpellButton(Spell s){
	   super();
	   try {
		bk=ImageIO.read(new File("assets/button1-disabled.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	super.setContentAreaFilled(false);
	   this.setIcon(new ImageIcon(bk));
	   this.s=s;
	   this.setOpaque(false);
	   this.setText(s.getName());
	   this.setForeground(Color.WHITE);
	   this.setBorder(BorderFactory.createEmptyBorder());
	   this.setToolTipText(s.toString());
	   this.setVerticalTextPosition(JButton.CENTER);
	   this.setHorizontalTextPosition(JButton.CENTER);
   }
  
}
