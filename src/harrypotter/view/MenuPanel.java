package harrypotter.view;

import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.Tournament;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements ActionListener {
	JTextField name;//name entry
	JPanel house;//house selection
	JPanel spells;//spell selection
	JButton confirm;//creates champion
	String hous;
	Tournament t;
	int count=0;//spell count
	ArrayList<SpellButton> spellarray=new ArrayList<SpellButton>();
	public MenuListener game;
	private BufferedImage bk ;
	private JButton Gryff;
	private JButton Sly;
	private JButton Puff;
	private JButton Raven;
	private JPanel last;
	Font Magic;
	Font elegant;
	JPanel spellP;
	JButton add;
	JLabel SpellTital;
	JLabel SpellInfo;
	Spell s;
	SpellButton bs;
	ArrayList<Spell>ps=new ArrayList<Spell>();
	public MenuPanel() throws IOException{
		//background
		bk = ImageIO.read(new File("assets/wal11.jpg"));
		//music
		
		//label and name
	    this.setLayout(new BorderLayout());
	    this.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
	    JPanel intro=new JPanel();
	    intro.setOpaque(false);
	    intro.setLayout(new FlowLayout());
	    JLabel n=new JLabel("<html><span style='font-size:20px'>"+"What is your name"+"</span></html>");
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
	    Magic = null;
	    elegant=null;
		try {
			Magic = Font.createFont(Font.TRUETYPE_FONT,font).deriveFont(48f);
			elegant = Font.createFont(Font.TRUETYPE_FONT,Font1).deriveFont(48f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    n.setFont(Magic);
	    //n.setSize(70, 50);
	    n.setForeground(Color.WHITE);
	    intro.add(n);
	    intro.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	    
	    
		name=new JTextField();
		name.setColumns(30);
		name.setPreferredSize(new Dimension(20,30));
		//name.setBackground();  to do
		intro.add(name,BorderLayout.CENTER);
		
		this.add(intro,BorderLayout.NORTH);
		//houses
		house=new JPanel(new GridLayout(1,4));
		house.setOpaque(false);
		house.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "Which house do you represent?"));
		BufferedImage gry = ImageIO.read(new File("assets/gyff.png"));
		Gryff= new JButton(new ImageIcon(gry,"Gryffindor"));
		Gryff.setBorder(BorderFactory.createEmptyBorder());
		Gryff.setContentAreaFilled(false);
		
		BufferedImage sl=ImageIO.read(new File("assets/slyth.png"));
		Sly= new JButton(new ImageIcon(sl));
		Sly.setBorder(BorderFactory.createEmptyBorder());
		Sly.setContentAreaFilled(false);
		
		BufferedImage Ra=ImageIO.read(new File("assets/ravenc.png"));
		Raven= new JButton(new ImageIcon(Ra));
		Raven.setBorder(BorderFactory.createEmptyBorder());
		Raven.setContentAreaFilled(false);
		
		BufferedImage Hu=ImageIO.read(new File("assets/Huffl.png"));
		Puff= new JButton(new ImageIcon(Hu));
		Puff.setBorder(BorderFactory.createEmptyBorder());
		Puff.setContentAreaFilled(false);
		
		Sly.setPreferredSize(new Dimension(100, 100));
		Gryff.setToolTipText("Call To Action: The heroics of house Gryffindor imbues the champion, giving them two moves a turn.");
		Sly.setToolTipText("Slither step: The long reach of house Slytherin shows itself in each and every member. The champion moves two tiles a turn.");
		Raven.setToolTipText("The All Knowing: A blessing of knoweldge is granted upon the champion. The champion can predict where the dragon will fire or where the treasure lies.");
		Puff.setToolTipText("Nature's blessing: The champion gains affinity to nature, silencing the dragon or pacifying the merpeople for a turn. In the third task, nature protects him by halving all his damage taken.");
		Raven.addActionListener(this);
		Puff.addActionListener(this);
		Sly.addActionListener(this);
		Gryff.addActionListener(this);
		house.add(Gryff);
		house.add(Sly);
		house.add(Raven);
		house.add(Puff);
		
		this.add(house,BorderLayout.WEST);
		spells= new JPanel(new GridLayout(0,10));
		spells.setOpaque(false);
		
		
		last=new JPanel();
		last.setOpaque(false);
		last.setLayout(new BorderLayout());
		//this.add(last,BorderLayout.SOUTH);
		count=0;
		last.add(spells,BorderLayout.NORTH);
		//last.add(confirm,BorderLayout.SOUTH);
		spellP =new JPanel();
		spellP.setLayout(new BorderLayout());
		spellP.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "Spell Information"));
		spellP.setPreferredSize(new Dimension(450, 180));
		spellP.setOpaque(false);
		add=new JButton();
		BufferedImage bkk=null;
		try {
			bkk=ImageIO.read(new File("assets/button1-disabled.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//add.setContentAreaFilled(false);
			if(bkk!=null){
		   add.setIcon(new ImageIcon(bkk));}
		   add.setOpaque(false);
		   add.setText("Add Spell");
		   add.setForeground(Color.WHITE);
		   add.setBorder(BorderFactory.createEmptyBorder());
		   add.addActionListener(this);
		   add.setVerticalTextPosition(JButton.CENTER);
		   add.setHorizontalTextPosition(JButton.CENTER);
		   add.setEnabled(false);
		   add.setContentAreaFilled(false);
		   add.setPreferredSize(new Dimension(125,50));
		   confirm=new JButton();
		confirm.setText("Create Champion");
		 confirm.setForeground(Color.WHITE);
		   confirm.setBorder(BorderFactory.createEmptyBorder());
		   confirm.addActionListener(this);
		   confirm.setVerticalTextPosition(JButton.CENTER);
		   confirm.setHorizontalTextPosition(JButton.CENTER);
		  
		   confirm.setContentAreaFilled(false);
		   confirm.setPreferredSize(new Dimension(125,50));
		if(bkk!=null){
		 confirm.setIcon(new ImageIcon(bkk));}
		confirm.setOpaque(false);
		SpellTital=new JLabel();
		SpellTital.setAlignmentX(Component.CENTER_ALIGNMENT);
		SpellInfo=new JLabel();
		SpellInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
		SpellInfo.setForeground(Color.WHITE);
		spellP.add(SpellTital, BorderLayout.NORTH);
		spellP.add(SpellInfo, BorderLayout.CENTER);
		spellP.add(add, BorderLayout.SOUTH);
		last.add(spellP,BorderLayout.WEST);
		last.add(confirm,BorderLayout.EAST);
		this.add(last,BorderLayout.SOUTH);
		s=null;
		//last.add(spellP,BorderLayout.WEST);
	}
	
	public MenuPanel(Tournament t) throws IOException{
		this();
		this.t=t;
		for(Spell sp:t.getSpells()){
			SpellButton b= new SpellButton(sp);
			b.setPreferredSize(new Dimension(125,50));
			spells.add(b);
			b.addActionListener(this);
			spellarray.add(b);
		}
		spells.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "Choose your three spells"));
		
	}
	
	
	public void paintComponent(Graphics g) {
		
		
		
	    super.paintComponent(g);
	        g.drawImage(bk,0, 0, null);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Object src= arg0.getSource();
		Clip cl=null;
		
        try {cl = AudioSystem.getClip();
			cl.open(AudioSystem.getAudioInputStream(new File("assets/click.wav")));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} cl.start();
      
		for(SpellButton b:spellarray){
			if(b.isSelected()){
				count++;//count is pretty much an unused variable here.
				if(b.equals(src)){
				s=b.s;//a second minor bug, selecting multiple spells would not display the spell panel correctly, it would only display what's last in the array. By doing this and changing the conditions to solely be for s and not b.s, this is fixed.
				
				bs=b;
				}
				String t="Cost: "+s.getCost()+"\n Default CoolDown: "+s.getDefaultCooldown();
				if(s instanceof DamagingSpell){
					String sp="<html><span style='font-size:40px'>"+"Damaging Spell"+"</span></html>";
					SpellTital.setText(sp);
					SpellTital.setFont(elegant);
					SpellTital.setForeground(Color.red);
					add.setEnabled(true);
					t=t+"\n Damaging Amount: "+((DamagingSpell) s).getDamageAmount();

				}
				if(s instanceof HealingSpell){
					String sp="<html><span style='font-size:40px'>"+"Healing Spell"+"</span></html>";
					SpellTital.setText(sp);
					SpellTital.setFont(elegant);
					SpellTital.setForeground(Color.GREEN);
					add.setEnabled(true);
					t=t+"\n Healing Amount: "+((HealingSpell) s).getHealingAmount();
				}
				if(s instanceof RelocatingSpell){
					String sp="<html><span style='font-size:40px'>"+"Relocating Spell"+"</span></html>";
					SpellTital.setText(sp);
					SpellTital.setFont(elegant);
					SpellTital.setForeground(Color.orange);
					add.setEnabled(true);
					t=t+"\n Range"+((RelocatingSpell) s).getRange();
				}
				SpellInfo.setText(t);
				//SpellInfo.setFont(Magic);
				//count++;
				//b.setForeground(Color.GRAY);
				//ps.add(b.s);
			}
			
			
		}
		if(src==add){
			bs.setForeground(Color.GRAY);
			ps.add(s);
			if(ps.size()>3){//playing on ps.size() and not count.
					JOptionPane.showMessageDialog(this, "You're only supposed to choose 3 spells.");
					ps.remove(ps.size()-1);
			}
					
		}

		
		if(src!=confirm&&src instanceof JButton){
		JButton b=(JButton)src;
		BufferedImage im=null;
		if( b.getIcon()==Gryff.getIcon()){ 
			hous="Gryffindor";
			Color red = new Color(223,23,39);
		name.setBorder(new LineBorder(red, 5));
		
		try {
			im = ImageIO.read(new File("assets/gryfquote1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel pic =new JLabel(new ImageIcon(im));
		BorderLayout layout = (BorderLayout) this.getLayout();
		if(layout.getLayoutComponent(BorderLayout.CENTER) != null){
		this.remove(layout.getLayoutComponent(BorderLayout.CENTER));}
		this.add(pic,BorderLayout.CENTER);
		}
		
		if(b.getIcon()==Sly.getIcon()){
		hous="Slytherin";
		name.setBorder(new LineBorder(new Color(34,156,69), 5));
		try {
			im = ImageIO.read(new File("assets/slyquote1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel pic =new JLabel(new ImageIcon(im));
		BorderLayout layout = (BorderLayout) this.getLayout();
		if(layout.getLayoutComponent(BorderLayout.CENTER) != null){
		this.remove(layout.getLayoutComponent(BorderLayout.CENTER));}
		this.add(pic,BorderLayout.CENTER);}
		
		if(b.getIcon()==Raven.getIcon()){
		hous="Ravenclaw";
		name.setBorder(new LineBorder(new Color(12,157,201), 5));
		try {
			im = ImageIO.read(new File("assets/ravquote1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel pic =new JLabel(new ImageIcon(im));
		
		BorderLayout layout = (BorderLayout) this.getLayout();
		if(layout.getLayoutComponent(BorderLayout.CENTER) != null){
		this.remove(layout.getLayoutComponent(BorderLayout.CENTER));}
		this.add(pic,BorderLayout.CENTER);
		}
		
		if(b.getIcon()==Puff.getIcon()){
		hous="Hufflepuff";
		name.setBorder(new LineBorder(new Color(235,193,29), 5));
		try {
			im = ImageIO.read(new File("assets/huffleqoute.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel pic =new JLabel(new ImageIcon(im));
		BorderLayout layout = (BorderLayout) this.getLayout();
		if(layout.getLayoutComponent(BorderLayout.CENTER) != null){
		this.remove(layout.getLayoutComponent(BorderLayout.CENTER));}
		this.add(pic,BorderLayout.CENTER);
		
		}
		}
			
			if(src==confirm){
				
					String cname=name.getText();
					if(cname.length()!=0){
					Champion c;
					if(hous==null){
						JOptionPane.showMessageDialog(this, "Please Choose a house" );
					}
					else{
					switch(hous){
					case "Gryffindor":c= new GryffindorWizard(cname);break;
					case "Slytherin":c= new SlytherinWizard(cname);break;
					case "Hufflepuff":c= new HufflepuffWizard(cname);break;
					case "Ravenclaw":c= new RavenclawWizard(cname);break;
					//case null:JOptionPane.showMessageDialog(this, "Please Choose a house" );break;
					default:c=new GryffindorWizard(cname);}
					for(SpellButton s:spellarray){
						s.setForeground(Color.WHITE);
						s.setSelected(false);
					}
					if(ps.size()!=3){
						JOptionPane.showMessageDialog(this, "Please Choose 3 spells" );
					}else{
					for(Spell s:ps){
						if(s instanceof DamagingSpell){
							s= new DamagingSpell(s.getName(),s.getCost(),s.getDefaultCooldown(),((DamagingSpell)s).getDamageAmount());
						}
						if(s instanceof HealingSpell){

							s= new HealingSpell(s.getName(),s.getCost(),s.getDefaultCooldown(),((HealingSpell)s).getHealingAmount());

						}
						if(s instanceof RelocatingSpell){
							s= new RelocatingSpell(s.getName(),s.getCost(),s.getDefaultCooldown(),((RelocatingSpell)s).getRange());

						}
						((Wizard)c).getSpells().add(s);
					}
						t.addChampion(c);
						try {
							game.onChampionadd(c);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						ps= new ArrayList<Spell>();
						name.setText("");
						name.setBorder(null);
						if(Gryff.isSelected()){
							Gryff.setSelected(false);}
						if(Sly.isSelected()){
							Sly.setSelected(false);}
						if(Puff.isSelected()){
							Puff.setSelected(false);}
						if(Raven.isSelected()){
							Raven.setSelected(false);}
						BorderLayout layout = (BorderLayout) this.getLayout();
						if(layout.getLayoutComponent(BorderLayout.CENTER) != null){
						this.remove(layout.getLayoutComponent(BorderLayout.CENTER));}
						}
					
				}}
					else{
						JOptionPane.showMessageDialog(this, "Please enter a name" );
					}
					
			}
		
		if(t.getChampions().size()==3){
			confirm.setText("Begin Tournament");
		}

		
		this.repaint();
		this.revalidate();}
		

			
		
		
	
	public JButton getConfirm() {
		return confirm;
	}

	

}
