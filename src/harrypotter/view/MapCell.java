package harrypotter.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.Task;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.WallCell;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MapCell extends JButton {
private Cell cell;
private	Image walk;
private Task t;
public Image getWalk() {
	return walk;
}
public MapCell(Cell c,Task t) throws IOException{
	super();
	this.t=t;
	this.setEnabled(false);
	cell=c;
	this.setOpaque(false);
	this.setBorder(BorderFactory.createEmptyBorder());
	this.setContentAreaFilled(false);
	ImageIcon img = null;
	if(t instanceof FirstTask){
		if(c instanceof ObstacleCell){
			img=new ImageIcon("assets/obstacle task1.jpg");
			this.setToolTipText(((ObstacleCell) c).getObstacle().getHp()+"");
		}
		else{
			if(c instanceof ChampionCell){
				
				
				if(((ChampionCell)c).getChamp() instanceof GryffindorWizard){
					img= new ImageIcon("assets/gryftask1.gif");
				}
				if(((ChampionCell)c).getChamp() instanceof SlytherinWizard){
					img= new ImageIcon("assets/slytask1.gif");
				}
				if(((ChampionCell)c).getChamp() instanceof HufflepuffWizard){
					img= new ImageIcon("assets/huffletask1.gif");
				}
				if(((ChampionCell)c).getChamp() instanceof RavenclawWizard){
					img= new ImageIcon("assets/raventask1.gif");
				}
				
				
		     
			}else{
			img=new ImageIcon("assets/task1.jpg");
		}}
	}
	else{
		if( t instanceof SecondTask){
			if(c instanceof ObstacleCell){
				img = new ImageIcon("assets/task2obs.gif");
				this.setToolTipText(((ObstacleCell) c).getObstacle().getHp()+"");

			}
			else{
				if(c instanceof ChampionCell){
					
					
					if(((ChampionCell)c).getChamp() instanceof GryffindorWizard){
						img= new ImageIcon("assets/gryftask2.gif");
					}
					if(((ChampionCell)c).getChamp() instanceof SlytherinWizard){
						img= new ImageIcon("assets/slytask2.gif");
					}
					if(((ChampionCell)c).getChamp() instanceof HufflepuffWizard){
						img= new ImageIcon("assets/huffletask2.gif");
					}
					if(((ChampionCell)c).getChamp() instanceof RavenclawWizard){
						img= new ImageIcon("assets/raventask2.gif");
					}
					
					
			     
				}else{
				img=new ImageIcon("assets/task2.jpg");
			}}
		}
		else{
			if(c instanceof ObstacleCell){
				img=new ImageIcon("assets/obstacle task3.jpg");
				this.setToolTipText(((ObstacleCell) c).getObstacle().getHp()+"");
			}else{
				if(c instanceof WallCell){
					img=new ImageIcon("assets/wallcell.jpg");
				}
			
			else{
				if(c instanceof ChampionCell){
					
					
					if(((ChampionCell)c).getChamp() instanceof GryffindorWizard){
						img= new ImageIcon("assets/gryftask3.gif");
					}
					if(((ChampionCell)c).getChamp() instanceof SlytherinWizard){
						img= new ImageIcon("assets/slytask3.gif");
					}
					if(((ChampionCell)c).getChamp() instanceof HufflepuffWizard){
						img= new ImageIcon("assets/huffletask3.gif");
					}
					if(((ChampionCell)c).getChamp() instanceof RavenclawWizard){
						img= new ImageIcon("assets/raventask3.gif");
					}
					
					
			     
				}else{
				img=new ImageIcon("assets/task3.jpg");
			}}}
		}
	}
	
	this.setIcon(img);
	this.setDisabledIcon(img);
	/*if(c instanceof ObstacleCell){
		this.setForeground(Color.red);
		this.setText((((ObstacleCell)c).getObstacle()).getHp()+"");
	}*/
}
public Cell getCell() {
	return cell;
}
public void clear(){
	this.setIcon(null);
}

}

