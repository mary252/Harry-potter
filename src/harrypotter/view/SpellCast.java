package harrypotter.view;

import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/*ImageIcon icon = new ImageIcon("yourFile.gif");
Image normalImage = icon.getImage();
Image grayImage = GrayFilter.createDisabledImage(normalImage);
 */
public class SpellCast extends JButton implements MouseListener{
	private Spell spell;
	public SpellCast(Spell s){
		super();
		this.setFocusable(false);
		this.setToolTipText(s.toString());
		if(s instanceof DamagingSpell){
			ImageIcon img= new ImageIcon("assets/dam.png");
			this.setIcon(img);
			this.setText("");
			spell= new DamagingSpell(s.getName(),s.getCost(),s.getDefaultCooldown(),((DamagingSpell)s).getDamageAmount());
			spell.setCoolDown(s.getCoolDown());
		}
		if(s instanceof HealingSpell){
			ImageIcon img= new ImageIcon("assets/heal.png");
			this.setIcon(img);
			spell= new HealingSpell(s.getName(),s.getCost(),s.getDefaultCooldown(),((HealingSpell)s).getHealingAmount());
			spell.setCoolDown(s.getCoolDown());
		}
		if(s instanceof RelocatingSpell){
			ImageIcon img= new ImageIcon("assets/rel.png");
			this.setIcon(img);
			spell= new RelocatingSpell(s.getName(),s.getCost(),s.getDefaultCooldown(),((RelocatingSpell)s).getRange());
			spell.setCoolDown(s.getCoolDown());
		}
		update();
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.addMouseListener(this);
	}
	
	public Spell getSpell() {
		return spell;
	}

	public void update(){
		if(spell.getCoolDown()!=0){
		this.setEnabled(false);
		}
		else{
			this.setEnabled(true);
		}
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
		if(spell instanceof DamagingSpell){
			ImageIcon img= new ImageIcon("assets/dampressed.png");
			this.setIcon(img);
			this.setText("");
			}
		if(spell instanceof HealingSpell){
			ImageIcon img= new ImageIcon("assets/healpressed.png");
			this.setIcon(img);
			}
		if(spell instanceof RelocatingSpell){
			ImageIcon img= new ImageIcon("assets/relpressed.png");
			this.setIcon(img);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(spell instanceof DamagingSpell){
			ImageIcon img= new ImageIcon("assets/dam.png");
			this.setIcon(img);
			this.setText("");
			}
		if(spell instanceof HealingSpell){
			ImageIcon img= new ImageIcon("assets/heal.png");
			this.setIcon(img);
			}
		if(spell instanceof RelocatingSpell){
			ImageIcon img= new ImageIcon("assets/rel.png");
			this.setIcon(img);
		}
		
	}

}
