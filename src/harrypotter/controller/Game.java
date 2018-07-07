package harrypotter.controller;

import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.Task;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Direction;
import harrypotter.view.ControlListener;
import harrypotter.view.ControlPanel;
import harrypotter.view.DirectionalButton;
import harrypotter.view.InformationPanel;
import harrypotter.view.MenuListener;
import harrypotter.view.MenuPanel;
import harrypotter.view.SpellButton;
import harrypotter.view.SpellCast;
import harrypotter.view.State;
import harrypotter.view.TaskPanel;
import harrypotter.view.bkpanel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Game extends JFrame implements ActionListener, MenuListener,ControlListener,TournamentListener,KeyListener {
	State task;
	MenuPanel Menu;
	InformationPanel info;
	Tournament tournament;
	ArrayList<JButton> spells;
	TaskPanel vidya;
	ControlPanel controls;
	//BufferedImage bk;
	Spell buffered;
	Direction reltarget;
	Direction relspot;
	int relrange;//this variable and the two above it are for relocating spells.
	boolean sly;//slytherin trait
	boolean change;
	boolean action; //this boolean is for the sake of presentation
	ArrayList<Point> marked;
	Clip clip;
	
	public Game() throws IOException{
		//bk = ImageIO.read(new File("assets/bk1.jpg"));
		
		tournament= new Tournament();
		tournament.setListener(this);
		this.setSize(1440,900);
		this.setResizable(false);
		setTitle("Harry Potter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setBounds(50, 50, 800, 600);
		//setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		Menu=new MenuPanel(tournament);
		info=new InformationPanel(tournament);
		Menu.game=this;
		add(Menu,BorderLayout.CENTER);
		setFocusable(true);
		addKeyListener(this);
		setVisible(true);
		this.requestFocus(true);
		clip=null;
		try
	    {	
	        clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("assets/music/01 Prologue Book II & The Escape From the Dursleys.wav")));
	        clip.start();
	        clip.loop(clip.LOOP_CONTINUOUSLY);
	    	
	        
	        
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
		
	}

		
	public static void main(String[] args) throws IOException{
		new Game();
	}



	@Override
	public void onChampionadd(Champion c) throws IOException {
		info.addChamp(c);
		if(tournament.getChampions().size()==4&&task==null){
			remove(Menu);
			this.setContentPane(new bkpanel());
			//fist task sound
			
			clip.stop();
			try
		    {	
		        clip = AudioSystem.getClip();
		        clip.open(AudioSystem.getAudioInputStream(new File("assets/music/H-Pi - Styx Master of Shadows Soundtrack - 02 Styx Master of Shadows (Title Theme).wav")));
		        clip.start();
		        clip.loop(clip.LOOP_CONTINUOUSLY);
		    	
		        
		        
		    }
		    catch (Exception exc)
		    {
		        exc.printStackTrace(System.out);
		    }
			
			tournament.beginTournament();
			task= State.FIRST;
			vidya= new TaskPanel(tournament.getFirstTask());
			vidya.setPreferredSize(new Dimension(900,660));
			add(vidya,BorderLayout.CENTER);
			controls=new ControlPanel(tournament.getFirstTask().getCurrentChamp());
			controls.setListener(this);
			controls.setSize(200, getHeight());
			add(controls,BorderLayout.EAST);
			add(info,BorderLayout.SOUTH);
			this.repaint();
			this.revalidate();
		}
		
	}
		
	

	/*@Override
	public void castdam(Spell s, Direction d) {
		Task t=null;
		String flavor="";
		String deaths="";
		if(task==State.FIRST){
			t=tournament.getFirstTask();
			flavor=((Wizard)t.getCurrentChamp()).getName()+" uses "+s.getName()+" to hit for "+((DamagingSpell)s).getDamageAmount()+'\n'
			+"The dragon prepares to fire at"+'\n'+"two of the red marked cells."+'\n'+ "Beware!"+'\n'+
			"Allowed Moves: "+ t.getAllowedMoves()+'\n';
		}
		if(task==State.SECOND){
			t=tournament.getSecondTask();
			flavor=((Wizard)t.getCurrentChamp()).getName()+" uses "+s.getName()+" to hit for "+((DamagingSpell)s).getDamageAmount()+'\n'
			+
			"Allowed Moves: "+ t.getAllowedMoves()+'\n';
		}
		if(task==State.THIRD){
			t=tournament.getThirdTask();
			flavor=((Wizard)t.getCurrentChamp()).getName()+" uses "+s.getName()+" to hit for "+((DamagingSpell)s).getDamageAmount()+'\n'
			+
			"Allowed Moves: "+ t.getAllowedMoves()+'\n';
		}
		try{
			Point p=((Wizard)t.getCurrentChamp()).getLocation();
			t.castDamagingSpell((DamagingSpell)s,d);
			vidya.FlashButton(d,p);
			Thread.sleep(100);
	    	
		
			deaths=info.checkDeaths();
			vidya.update(t);
			info.update();
			info.event(flavor+deaths);
			
			controls.update(t.getCurrentChamp());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
	}*/

	@Override
	public void castHeal(HealingSpell s) {
		Task t=null;
		String flavor="";
		String deaths="";
		try
	    {	
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("assets/helspell.wav")));
	        clip.start();
	        //clip.loop(clip.LOOP_CONTINUOUSLY);
	    	
	        
	        
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }

		if(task==State.FIRST){
			t=tournament.getFirstTask();
			flavor=((Wizard)t.getCurrentChamp()).getName()+" heals his wounds for "+((HealingSpell)s).getHealingAmount()+'\n'
			+"The dragon prepares to fire at"+'\n'+"two of the red marked cells."+'\n'+" Beware!"+'\n'+
			"Allowed Moves: "+ t.getAllowedMoves()+'\n';
		}
		if(task==State.SECOND){
			t=tournament.getSecondTask();
			flavor=((Wizard)t.getCurrentChamp()).getName()+" heals his wounds for "+((HealingSpell)s).getHealingAmount()+'\n'
			+
			"Allowed Moves: "+ t.getAllowedMoves()+'\n';
		}
		if(task==State.THIRD){
			t=tournament.getThirdTask();
			flavor=((Wizard)t.getCurrentChamp()).getName()+" heals his wounds for "+((HealingSpell)s).getHealingAmount()+'\n'
			+
			"Allowed Moves: "+ t.getAllowedMoves()+'\n';
		}
		Point p=((Wizard)t.getCurrentChamp()).getLocation();
		try{
			Spell cs=null;
			for(Spell ts:((Wizard)t.getCurrentChamp()).getSpells()){
				if(ts.getName()==s.getName()){
					cs=ts;
					break;
				}
			}
			t.castHealingSpell((HealingSpell) cs);
			vidya.FlashButton(p,s);
			//System.out.println(s.getCoolDown());
			deaths=info.checkDeaths();
			vidya.update(t);
			info.update();
			info.event(flavor+deaths);
			controls.update(t.getCurrentChamp());
		}
		catch(Exception e){
			try
		    {	
		        Clip clip = AudioSystem.getClip();
		        clip.open(AudioSystem.getAudioInputStream(new File("assets/error.wav")));
		        clip.start();
		        //clip.loop(clip.LOOP_CONTINUOUSLY);
		    	
		        
		        
		    }
		    catch (Exception exc)
		    {
		        exc.printStackTrace(System.out);
		    }
			info.getInfo().append(e.toString());
		}

		
	}

	/*@Override
	public void castrel(Spell s, Direction d,Direction t, int r) {
		Task cur=null;
		String flavor="";
		String deaths="";
		if(task==State.FIRST){
			cur=tournament.getFirstTask();
			flavor=((Wizard)cur.getCurrentChamp()).getName()+" uses misdirection."+'\n'
			+"The dragon prepares to fire at"+'\n'+"two of the red marked cells."+'\n'+" Beware!"+'\n'+
			"Allowed Moves: "+ cur.getAllowedMoves()+'\n';
		}
		if(task==State.SECOND){
			cur=tournament.getSecondTask();
			flavor=((Wizard)cur.getCurrentChamp()).getName()+" uses misdirection."+'\n'+
			"Allowed Moves: "+ cur.getAllowedMoves()+'\n';
		}
		if(task==State.THIRD){
			cur=tournament.getThirdTask();
			flavor=((Wizard)cur.getCurrentChamp()).getName()+" uses misdirection."+'\n'+
			"Allowed Moves: "+ cur.getAllowedMoves()+'\n';
		}
		try{
			cur.castRelocatingSpell((RelocatingSpell)s,d,t,r);
			deaths=info.checkDeaths();
			vidya.update(cur);
			info.update();
			info.event(flavor+deaths);
			controls.update(cur.getCurrentChamp());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
	}*/

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		
	}

	@Override
	public void trait() {
		Task cur=null;
		String flavor="";
		String deaths="";
		switch(task){
		case FIRST:cur=tournament.getFirstTask();break;
		case SECOND:cur=tournament.getSecondTask();break;
		case THIRD:cur=tournament.getThirdTask();break;
		}
		if(cur.getCurrentChamp() instanceof GryffindorWizard){
			flavor=((Wizard)cur.getCurrentChamp()).getName()+" leaps into action!"+'\n'+" He can now perform"+'\n'+" two moves this turn."+'\n';
			if(cur instanceof FirstTask){
				flavor=flavor+"The dragon prepares to fire at"+'\n'+"two of the red marked cells."+'\n'+" Beware!"+'\n';
			}}
		if(cur.getCurrentChamp() instanceof HufflepuffWizard){
			if(cur instanceof FirstTask){
				flavor=((Wizard)cur.getCurrentChamp()).getName()+" has soothed the dragon... "+'\n'+"for a turn."+'\n';
			}
			if(cur instanceof SecondTask){
				flavor=((Wizard)cur.getCurrentChamp()).getName()+" has formed a one turn"+'\n'+" truce with the merpeople."+'\n';
			}
		}
		try{
			if(!(task==State.THIRD&&cur.getCurrentChamp() instanceof HufflepuffWizard)){
				if(task!=State.FIRST && cur.getCurrentChamp() instanceof RavenclawWizard){
					ArrayList<Direction> f=(ArrayList<Direction>) cur.onRavenclawTrait();
					flavor=((Wizard)cur.getCurrentChamp()).getName()+",your hearts content lies to your:"+'\n';
					for(Direction d:f){
						flavor=flavor+d+""+'\n';
					}
				}
				else{
				cur.getCurrentChamp().useTrait();
				if(cur.getCurrentChamp() instanceof RavenclawWizard){
					vidya.trueMark(tournament.getFirstTask().getMarkedCells());
				}
				}
			}
			deaths=info.checkDeaths();
			//vidya.update(cur);
		
		
			flavor=flavor+"Allowed Moves: "+ cur.getAllowedMoves()+'\n';
			info.update();
			info.event(flavor+deaths);
			controls.update(cur.getCurrentChamp());
		}
		catch(Exception e){
			try
		    {	
		        Clip clip = AudioSystem.getClip();
		        clip.open(AudioSystem.getAudioInputStream(new File("assets/error.wav")));
		        clip.start();
		        //clip.loop(clip.LOOP_CONTINUOUSLY);
		    	
		        
		        
		    }
		    catch (Exception exc)
		    {
		        exc.printStackTrace(System.out);
		    }
			info.getInfo().append(e.toString());
		}

	}

	/*@Override
	public void slyTrait(Direction d) {
		Task t=null;
		String deaths="";
		switch(task){
		case FIRST:t=tournament.getFirstTask();break;
		case SECOND:t=tournament.getSecondTask();break;
		case THIRD:t=tournament.getThirdTask();break;
		}
		
		try{
			((SlytherinWizard)t.getCurrentChamp()).setTraitDirection(d);
			t.getCurrentChamp().useTrait();
			deaths=info.checkDeaths();
			vidya.update(t);
			info.update();
			info.event(flavor+deaths);
			controls.update(t.getCurrentChamp());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}*/

	@Override
	public void beginSecond() throws IOException {
		
		
		task=State.SECOND;
		JOptionPane.showMessageDialog(this, "The second task begins");
		vidya.setVisible(false);
		Task t=tournament.getSecondTask();
		this.remove(vidya);
		clip.stop();
		try
	    {	
	        clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("assets/music/14 Underwater Secrets.wav")));
	        clip.start();
	        clip.loop(clip.LOOP_CONTINUOUSLY);
	    	
	        
	        
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	
		vidya= new TaskPanel(t);
		this.add(vidya,BorderLayout.CENTER);
		this.remove(info);
		info= new InformationPanel(tournament);
		this.add(info,BorderLayout.SOUTH);
		controls.update(t.getCurrentChamp());
		repaint();
		revalidate();
		change=true;
		
	}

	@Override
	public void beginThird() throws IOException {
		
		clip.stop();
		try
	    {	
	        clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("assets/music/H-Pi - Styx Master of Shadows Soundtrack - 13 The Mother Tree.wav")));
	        clip.start();
	        clip.loop(clip.LOOP_CONTINUOUSLY);
	    	
	        
	        
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
		task=State.THIRD;
		Task t=tournament.getThirdTask();
		this.remove(vidya);
		vidya= new TaskPanel(t);
		this.add(vidya, BorderLayout.CENTER);
		info.update();
		info.event("The final task begins!");
		controls.update(t.getCurrentChamp());
		repaint();
		revalidate();
		change=true;
	}

	@Override
	public void winner(Champion c) {
		try
	    {	
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("assets/jingle-win-00 (1).wav")));
	        clip.start();
	        //clip.loop(clip.LOOP_CONTINUOUSLY);
	    	
	        
	        
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
		JOptionPane.showMessageDialog(this,((Wizard)c).getName()+ " has triumphed over all to claim their rightful spot as the triwizard cup champion! Congratulations!");
		System.exit(0);
	}

	@Override
	public void death() {
		try
	    {	
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("assets/fail.wav")));
	        clip.start();
	        //clip.loop(clip.LOOP_CONTINUOUSLY);
	    	
	        
	        
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
		JOptionPane.showMessageDialog(this,"And so the tournament ends, without a winner. Hogwart's best have all perished for their weakness and incompetence.");
		System.exit(0);
		
		
	}
	public void pot(Potion p){
		Task t=null;
		String death="";
		switch(task){
		case FIRST:t=tournament.getFirstTask();break;
		case SECOND:t=tournament.getSecondTask();break;
		case THIRD:t=tournament.getThirdTask();break;
		}
		try
	    {	
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("assets/potiondrink.wav")));
	        clip.start();
	        //clip.loop(clip.LOOP_CONTINUOUSLY);
	    	
	        
	        
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
		String flavor=((Wizard)t.getCurrentChamp()).getName()+" gulps down a "+p.getName()+"."+'\n'+
				"His IP is restored by "+p.getAmount()+'\n';
		t.usePotion(p);
		death=info.checkDeaths();
		try {
			vidya.update(t);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		info.update();
		info.event(flavor+death);
		controls.update(t.getCurrentChamp());
		
		
		

	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		int keycode=arg0.getKeyCode();
		Direction d=null;
		int reply=-1;
		
		switch(keycode){
			case KeyEvent.VK_UP:d=Direction.FORWARD;break;
			case KeyEvent.VK_DOWN:d=Direction.BACKWARD;break;
			case KeyEvent.VK_LEFT:d=Direction.LEFT;break;
			case KeyEvent.VK_RIGHT:d=Direction.RIGHT;break;
			case KeyEvent.VK_ESCAPE:if(buffered!=null||sly!=false){
				//TODO: Mary, please add in a power down sound effect here. 
				//This is in case the player wants to cast another spell or move.
				buffered=null;
				sly=false;
			}
			else{
				reply=JOptionPane.showConfirmDialog(this, "Giving up so soon?","Exit game?",JOptionPane.YES_NO_OPTION);
			}
		}
		if(reply==JOptionPane.YES_OPTION){
			System.exit(0);
		}
		Task t=null;
		String flavor="";
		String death="";
		if(d!=null){
		if(task==State.FIRST){
			t=tournament.getFirstTask();
			flavor=((Wizard)t.getCurrentChamp()).getName()+" advances eagrly."+'\n'
			+"The dragon prepares to fire at"+'\n'+"two of the red marked cells. Beware!"+'\n'+
			"Allowed Moves: "+ t.getAllowedMoves()+'\n';
		}
		if(task==State.SECOND){
			t=tournament.getSecondTask();
			flavor=((Wizard)t.getCurrentChamp()).getName()+" makes haste."+'\n'
			+
			"Allowed Moves: "+ t.getAllowedMoves()+'\n';
		}
		if(task==State.THIRD){
			t=tournament.getThirdTask();
			flavor=((Wizard)t.getCurrentChamp()).getName()+" desperately moves on."+'\n'
			+
			"Allowed Moves: "+ t.getAllowedMoves()+'\n';
		}
		Wizard current= (Wizard)t.getCurrentChamp();
		int hp=current.getHp();
		Point p=((Wizard)t.getCurrentChamp()).getLocation();
		Boolean tr=t.isTraitActivated();
		ArrayList<Collectible> count1=((Wizard)t.getCurrentChamp()).getInventory();
		try{
			if(task==State.FIRST){
				marked=((FirstTask)t).getMarkedCells();}
			if(sly){
				t.onSlytherinTrait(d);
				sly=false;
			}
			
			else{
			if(buffered!=null){
				if(buffered instanceof DamagingSpell){
					try
				    {	
				        Clip clip = AudioSystem.getClip();
				        clip.open(AudioSystem.getAudioInputStream(new File("assets/damspell.wav")));
				        clip.start();
				        //clip.loop(clip.LOOP_CONTINUOUSLY);
				    	
				        
				        
				    }
					catch(Exception e){
						
					}

					Spell cs=null;
					for(Spell ts:((Wizard)t.getCurrentChamp()).getSpells()){
						if(ts.getName()==buffered.getName()){
							cs=ts;
							break;
						}
					}
					t.castDamagingSpell((DamagingSpell)cs, d);
					vidya.FlashButton(d,p,buffered);
					buffered=null;
					//Thread.sleep(50);
					
				}
				if(buffered instanceof RelocatingSpell){
					if(reltarget!=null){
						try{
						Spell cs=null;
						for(Spell ts:((Wizard)t.getCurrentChamp()).getSpells()){
							if(ts.getName()==buffered.getName()){
								cs=ts;
								break;
							}
						}
						try
					    {	
					        Clip clip = AudioSystem.getClip();
					        clip.open(AudioSystem.getAudioInputStream(new File("assets/relspell.wav")));
					        clip.start();
					        //clip.loop(clip.LOOP_CONTINUOUSLY);
					    	
					        
					        
					    }
					    catch (Exception exc)
					    {
					        exc.printStackTrace(System.out);
					    }
						relspot=d;
						vidya.FlashButton(reltarget,p,buffered);
							t.castRelocatingSpell((RelocatingSpell)cs, reltarget, relspot, relrange);
							buffered=null;
							relrange=-1;
							reltarget=null;
						}
						catch(Exception e){
							
						}
							try
						    {	
						        Clip clip = AudioSystem.getClip();
						        clip.open(AudioSystem.getAudioInputStream(new File("assets/error.wav")));
						        clip.start();
						        //clip.loop(clip.LOOP_CONTINUOUSLY);
						    	
						        
						        
						    }
							catch(Exception e){
								
							}
							buffered=null;
							relrange=-1;
							reltarget=null;
							relspot=null;
						}
					else{
						reltarget=d;
						String r=JOptionPane.showInputDialog(this, "Enter range of relocation. Max: "+((RelocatingSpell)buffered).getRange(), 
						        "Enter range", 
						        JOptionPane.WARNING_MESSAGE);
						try{
							relrange=Integer.parseInt(r);
						}
						catch(Exception el){
							try
						    {	
						        Clip clip = AudioSystem.getClip();
						        clip.open(AudioSystem.getAudioInputStream(new File("assets/error.wav")));
						        clip.start();
						        //clip.loop(clip.LOOP_CONTINUOUSLY);
						    	
						        
						        
						    }
						    catch (Exception exc)
						    {
						        exc.printStackTrace(System.out);
						    }
							
							buffered=null;
							relrange=-1;
							reltarget=null;
							sly=false;
						}
					}
				}}
			
			
			else{
			switch(d){
			case FORWARD:t.moveForward();break;
			case BACKWARD:t.moveBackward();break; 
			case LEFT:t.moveLeft();break; 
			case RIGHT:t.moveRight();break; 
			}
			}
			ArrayList<Collectible>count2=((Wizard)t.getCurrentChamp()).getInventory();
			for(Collectible pot:count2){
				if(!(count1.contains(pot))){
					try
				    {	
				        Clip clip = AudioSystem.getClip();
				        clip.open(AudioSystem.getAudioInputStream(new File("assets/click.wav")));
				        clip.start();
				        //clip.loop(clip.LOOP_CONTINUOUSLY);
				    	
				        
				        
				    }
				    catch (Exception exc)
				    {
				        exc.printStackTrace(System.out);
				    }
					
					flavor=flavor+((Wizard)t.getCurrentChamp()).getName()+" picks up a "+((Potion)pot).getName()+"!"+'\n';
				}
			}
			}
			//vidya.update(t);
			if(task==State.FIRST&&!(tr&&current instanceof HufflepuffWizard)){
				vidya.DragonFire(marked);
				}
			//vidya.update(t);
			if(task==State.SECOND&&!(tr&&current instanceof HufflepuffWizard)&& hp>current.getHp()){
				vidya.FlashButton(p);
				}
			if(change)
			change=false;
			else{
			
			death=info.checkDeaths();
			vidya.update(t);
			info.update();
			info.event(flavor+death);
			controls.update(t.getCurrentChamp());
			}
			
			}
		catch(Exception e){
			try
		    {	
		        Clip clip = AudioSystem.getClip();
		        clip.open(AudioSystem.getAudioInputStream(new File("assets/error.wav")));
		        clip.start();
		        //clip.loop(clip.LOOP_CONTINUOUSLY);
		    	
		        
		        
		    }
		    catch (Exception exc)
		    {
		        exc.printStackTrace(System.out);
		    }
			//JOptionPane.showMessageDialog(this, e.getMessage());
			buffered=null;
			sly=false;
			reltarget=null;
			relspot=null;
		}
		}
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
	}


	@Override
	public void keyTyped(KeyEvent arg0) {

	}


	@Override
	public void bufferSpell(Spell s) {
		buffered=s;
		info.event("Please select a target using the arrow keys");
		sly=false;
	}
	public void slyTrait(){
		sly=true;
		buffered=null;
		/*this.requestFocus(true);
		setFocusable(true);
		flavor=flavor+"Allowed Moves: "+ cur.getAllowedMoves()+'\n';
		info.update();
		info.event(flavor+deaths);
		controls.update(cur.getCurrentChamp());*/
	}

	}

	
