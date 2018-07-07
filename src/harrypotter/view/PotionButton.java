package harrypotter.view;

import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PotionButton extends JButton implements MouseListener {
	Potion p;
	public PotionButton(Potion p){
		super();
		//this.setText(p.getName());
		this.p=p;
		this.setFocusable(false);
		if(p.getAmount()<500){
			ImageIcon img= new ImageIcon("assets/pot1.png");
			this.setIcon(img);
			//this.setText("");
			}else{
				
			
		if(p.getAmount()<1000){
			ImageIcon img= new ImageIcon("assets/pot2.png");
			this.setIcon(img);
			}
		else{
			ImageIcon img= new ImageIcon("assets/pot3.png");
			this.setIcon(img);
			}
			}
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.addMouseListener(this);
	}
	public Potion getPotion() {
		return p;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(p.getAmount()<500){
			ImageIcon img= new ImageIcon("assets/pot1pressed.png");
			this.setIcon(img);
			//this.setText("");
			}else{
				
			
		if(p.getAmount()<1000){
			ImageIcon img= new ImageIcon("assets/pot2pressed.png");
			this.setIcon(img);
			}
		else{
			ImageIcon img= new ImageIcon("assets/pot3pressed.png");
			this.setIcon(img);
			}
			}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(p.getAmount()<500){
			ImageIcon img= new ImageIcon("assets/pot1.png");
			this.setIcon(img);
			//this.setText("");
			}else{
				
			
		if(p.getAmount()<1000){
			ImageIcon img= new ImageIcon("assets/pot2.png");
			this.setIcon(img);
			}
		else{
			ImageIcon img= new ImageIcon("assets/pot3.png");
			this.setIcon(img);
			}
			}
		
	}

}
