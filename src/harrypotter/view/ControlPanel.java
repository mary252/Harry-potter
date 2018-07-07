package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;




import harrypotter.model.world.Direction;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ControlPanel extends JPanel implements ActionListener,MouseListener {
	JPanel arrows;
	JPanel actions;
	Champinfo current;
	ArrayList<SpellCast> spells=new ArrayList<SpellCast>();
	ArrayList<PotionButton> potions=new ArrayList<PotionButton>();
	private ControlListener listener;
	Spell bufferedSpell;
	JButton trait;
	public void setListener(ControlListener list) {
		listener=list;
	}
	public ControlPanel(Champion c){
		super();
		this.setFocusable(false);
		this.setLayout(new BorderLayout());
		update(c);
		this.setOpaque(false);
		repaint();
		revalidate();
	}
	public void update(Champion c){
		this.removeAll();
		//current=new Champinfo(c);
		//current.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "Current Champion: "+((Wizard)c).getName()));
		//current.setPreferredSize(new Dimension(200, 100));
		//Champinfo a=null;
		if(c instanceof GryffindorWizard){
			try {
				current=new gryffinfo(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//a.setBackground(Color.red);
		}
		if(c instanceof SlytherinWizard){
			//a.setBackground(Color.green);
			 try {
				current=new slyinfo(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(c instanceof HufflepuffWizard){
			//a.setBackground(Color.yellow);
			 try {
				current=new huffleinfo(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(c instanceof RavenclawWizard){
			//a.setBackground(Color.cyan);
			 try {
				current=new raveninfo(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//buttons for movement, spell casting and trait
		actions=new JPanel(new BorderLayout());
		actions.setSize(new Dimension(100,500));
		//JButton move= new JButton("move");
		//move.addActionListener(this);
		//actions.add(move);
		actions.setOpaque(false);
		spellpanel ss=null;
		
		try {
			ss=new spellpanel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Spell s: ((Wizard)current.getChamp()).getSpells()){
			SpellCast spell= new SpellCast(s);
			spells.add(spell);
			if(ss!=null){
				ss.spells.add(spell);
			}
			
			spell.addActionListener(this);
			
		}
		actions.add(ss,BorderLayout.CENTER);
		inventory inv=null;
		try {
			inv=new inventory();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(Collectible p:((Wizard)current.getChamp()).getInventory() ){
			PotionButton pot= new PotionButton((Potion)p);
			potions.add(pot);
			inv.spells.add(pot);
			pot.addActionListener(this);
		}
		actions.add(inv, BorderLayout.SOUTH);
		
		trait= new JButton();
		trait.setFocusable(false);
		BufferedImage sbk=null;
		 try {
				sbk=ImageIO.read(new File("assets/button1-bg.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		 trait.setContentAreaFilled(false);
		 trait.setIcon(new ImageIcon(sbk));
		 
		 trait.setOpaque(false);
		 trait.setText("Trait");
		 trait.setForeground(Color.WHITE);
		 trait.setBorder(BorderFactory.createEmptyBorder());
		 trait.setVerticalTextPosition(JButton.CENTER);
		 trait.setHorizontalTextPosition(JButton.CENTER);
		trait.addMouseListener(this);
		trait.addActionListener(this);
		
		actions.add(trait,BorderLayout.NORTH);
		this.add(current,BorderLayout.NORTH);
		this.add(actions,BorderLayout.CENTER);
		
		
		arrows= new JPanel();
		DirectionalButton Forward=new DirectionalButton(Direction.FORWARD);
		DirectionalButton Backward=new DirectionalButton(Direction.BACKWARD);
		DirectionalButton Left=new DirectionalButton(Direction.LEFT);
		DirectionalButton Right=new DirectionalButton(Direction.RIGHT);
		JButton cancel= new JButton("cancel");
		Forward.setText("Forward");
		Backward.setText("Backward");
		Left.setText("Left");
		Right.setText("Right");
		Forward.addActionListener(this);
		Backward.addActionListener(this);
		Left.addActionListener(this);
		Right.addActionListener(this);
		cancel.addActionListener(this);
		arrows.add(cancel, BorderLayout.CENTER);
		arrows.add(Forward,BorderLayout.WEST);
		arrows.add(Backward,BorderLayout.EAST);
		arrows.add(Left,BorderLayout.NORTH);
		arrows.add(Right,BorderLayout.SOUTH);
		
		repaint();
		revalidate();
	}
	public void displayArrows(){
		this.remove(actions);
		this.add(arrows,BorderLayout.SOUTH);
		repaint();
		revalidate();
	}
	public void revert(){
		this.remove(arrows);
		this.add(actions,BorderLayout.SOUTH);
		repaint();
		revalidate();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Object src= arg0.getSource();
		Clip cl=null;
		
        try {cl = AudioSystem.getClip();
			cl.open(AudioSystem.getAudioInputStream(new File("assets/click.wav")));
		} catch (Exception e1) {
			e1.printStackTrace();
		} cl.start();
      
	
		if(src instanceof PotionButton){
			listener.pot(((PotionButton)src).getPotion());
		}
		if(src instanceof SpellCast){
			bufferedSpell= ((SpellCast)src).getSpell();
			if(bufferedSpell instanceof HealingSpell){
				listener.castHeal((HealingSpell)bufferedSpell);
				bufferedSpell=null;
			}
			else{
			listener.bufferSpell(bufferedSpell);
			
			}
		}
		if(src==trait){
			if(current.getChamp() instanceof SlytherinWizard){
				listener.slyTrait();
			}
			else{
				listener.trait();
			}
		}
		}
		
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}
	@Override
	public void mousePressed(MouseEvent e) {

	}
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		BufferedImage sbk=null;
		 try {
				sbk=ImageIO.read(new File("assets/button1-focused.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		 trait.setContentAreaFilled(false);
		 trait.setIcon(new ImageIcon(sbk));
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		BufferedImage sbk=null;
		 try {
				sbk=ImageIO.read(new File("assets/button1-bg.png"));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}	
		 trait.setContentAreaFilled(false);
		 trait.setIcon(new ImageIcon(sbk));
		
	}}

