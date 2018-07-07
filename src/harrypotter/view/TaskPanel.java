package harrypotter.view;

import harrypotter.controller.Game;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.Task;
import harrypotter.model.tournament.ThirdTask;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.ObstacleCell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TaskPanel extends JPanel {
	ArrayList<MapCell> map=new ArrayList<MapCell>();
	Task current;
	MapCell currentchamp;
	int x;
	int y;
	int cflash=-1;
	int merflash=-1;
	Spell cast;
	ArrayList<Point> markedcells=null;
	boolean moving=false;
	
	public MapCell getCurrentchamp() {
		return currentchamp;
	}


	public void setCurrentchamp(MapCell currentchamp) {
		this.currentchamp = currentchamp;
	}


	public void setMoving(boolean moving) {
		this.moving = moving;
	}


	public TaskPanel(Task t) throws IOException{
		super(new GridLayout(10,10));
		this.setFocusable(false);
		current=t;
		update(current);
	}
		
	
	public void update(Task t) throws IOException{
		if(t instanceof FirstTask){
			this.setBackground(new Color(166,225,107));
		}
		if(t instanceof SecondTask){
			this.setBackground(new Color(55,108,139));
		}
		if(t instanceof ThirdTask){
			this.setBackground(new Color(178,142,84));
		}
		map=new ArrayList<MapCell>();
		this.removeAll();
		int x=-1;
		int y=-1;
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				MapCell btn=new MapCell(current.getMap()[i][j],t);
				map.add(btn);
				if(btn.getCell() instanceof ObstacleCell){
				btn.setToolTipText(btn.getCell()+"");
				if(t instanceof SecondTask){
					Merperson enemy= (Merperson) ((ObstacleCell)btn.getCell()).getObstacle();
					btn.setToolTipText(btn.getToolTipText()+" "+enemy.getDamage());
				}
				}
				this.add(btn);
				
				if(btn.getCell() instanceof ChampionCell&&((ChampionCell)btn.getCell()).getChamp()==t.getCurrentChamp()){
					btn.setBorder(new LineBorder(Color.BLACK));//indicating current champ
					x=j;
					y=i;
					currentchamp=btn;	

					
				}
			if(btn.getCell()instanceof CollectibleCell){
				btn.setBackground(Color.GREEN);
			}
			}
		}
		if(t instanceof FirstTask){
			ImageIcon img = new ImageIcon("assets/eggcell.jpg");
			map.get(44).setIcon(img);
			map.get(44).setDisabledIcon(img);
			if(x!=0){
				map.get((x-1)+(y*10)).setBackground(Color.red);
			}
			if(x!=9){
				map.get((x+1)+(y*10)).setBackground(Color.red);
			}
			if(y!=0){
				map.get((x)+((y-1)*10)).setBackground(Color.red);
			}
			if(y!=9){
				map.get((x)+((y+1)*10)).setBackground(Color.red);
			}
			

	}
}
	public void trueMark(ArrayList<Point> markedcells){
	 for(Point p:markedcells){
		 //ImageIcon img = new ImageIcon("assets/DANGER.gif");
		 
		 map.get((p.y)+(p.x*10)).setBorder(new LineBorder(Color.RED));
		 map.get((p.y)+(p.x*10)).setBorder(new LineBorder(Color.RED, 3));
		 
	 }
 }
	public void paint(Graphics g) {
		if(cflash!=-1){
			MapCell btn= map.get(cflash);
	    	ImageIcon img= (ImageIcon) btn.getIcon();
	    	btn.setIcon(null);
	    	btn.setDisabledIcon(null);
	    	//btn.setOpaque(true);
	    	 ImageIcon img1=null;
	    	if(current instanceof FirstTask){
	    		if(cast instanceof DamagingSpell){
		    		if(((MapCell)btn).getCell() instanceof ChampionCell){
		    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof GryffindorWizard){
		    				img1=new ImageIcon("assets/damage-spell-gryff-task21.jpg");
		    				
		    			}
		    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof SlytherinWizard){
		    				img1=(new ImageIcon("assets/damage-spell-sly-task1.jpg"));
		    					}
		    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof HufflepuffWizard){
		    				img1=(new ImageIcon("assets/damage-spell-huffle-task1.jpg"));
		    				}
		    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof RavenclawWizard){
		    				img1=(new ImageIcon("assets/damage-spell-raven-task1.jpg"));
		    						
		    			}
		    		}
		    		if(((MapCell)btn).getCell() instanceof ObstacleCell){
		    			img1=(new ImageIcon("assets/damage-spell-obstace-task-1.jpg"));
		    			}
	    	}
	    	if(cast instanceof RelocatingSpell){
		    	if(((MapCell)btn).getCell() instanceof ChampionCell){
		    		if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof GryffindorWizard){
		    			img1=(new ImageIcon("assets/relocating-spell-gryf-task1.jpg"));
		    			}
		    		if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof SlytherinWizard){
		    			img1=(new ImageIcon("assets/relocating-spell-sly-task1.jpg"));
		    				}
		    		if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof HufflepuffWizard){
		    			img1=(new ImageIcon("assets/relocating-spell-huffle-task1.jpg"));	
		    			}
		    		if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof RavenclawWizard){
		    			img1=(new ImageIcon("assets/relocating-spell-raven-task1.jpg"));
		    			}
		    		}
		    		if(((MapCell)btn).getCell() instanceof ObstacleCell){
		    			img1=(new ImageIcon("assets/relocating-spell-obstace-task-1.jpg"));
		    			}
	    	}
	    	if(cast instanceof HealingSpell){
	    		if(((MapCell)btn).getCell() instanceof ChampionCell){
	    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof GryffindorWizard){
	    				img1=(new ImageIcon("assets/healling-spell-gryff-task1.jpg"));
	    				}
	    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof SlytherinWizard){
	    				img1=(new ImageIcon("assets/healling-spell-rsly-task1.jpg"));
	    					}
	    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof HufflepuffWizard){
	    				img1=(new ImageIcon("assets/healling-spell-huffle-task1.jpg"));
	    				}
	    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof RavenclawWizard){
	    				img1=(new ImageIcon("healling-spell-raven-task1.jpg"));
	    						
	    			}
	    		}
	    		
    	}	
	    }
	    	if(current instanceof SecondTask){
	    		if(cast instanceof DamagingSpell||cast==null){//cast==null for merperson.
		    		if(((MapCell)btn).getCell() instanceof ChampionCell){
		    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof GryffindorWizard){
		    				img1=(new ImageIcon("assets/damage-spell-gryff-task2.jpg"));
		    				}
		    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof SlytherinWizard){
		    				img1=(new ImageIcon("assets/damage-spell-sly-task2.jpg"));
		    					}
		    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof HufflepuffWizard){
		    				img1=(new ImageIcon("assets/damage-spell-huffle-task2.jpg"));	
		    				}
		    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof RavenclawWizard){
		    				img1=(new ImageIcon("assets/damage-spell-raven-task2.jpg"));
		    						
		    			}
		    		}
		    		if(((MapCell)btn).getCell() instanceof ObstacleCell){
		    			img1=(new ImageIcon("assets/damage-spell-obstace-task-2.jpg"));
		    			}
	    	}
	    	if(cast instanceof RelocatingSpell){
		    	if(((MapCell)btn).getCell() instanceof ChampionCell){
		    		if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof GryffindorWizard){
		    			img1=(new ImageIcon("assets/relocating-spell-gryf-task2.jpg"));
		    			}
		    		if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof SlytherinWizard){
		    			img1=(new ImageIcon("assets/relocating-spell-sly-task2.jpg"));
		    				}
		    		if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof HufflepuffWizard){
		    			img1=(new ImageIcon("assets/relocating-spell-huffle-task2.jpg"));
		    			}
		    		if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof RavenclawWizard){
		    			img1=(new ImageIcon("assets/relocating-spell-raven-task2.jpg"));
		    					
		    		}
		    		}
		    		if(((MapCell)btn).getCell() instanceof ObstacleCell){
		    			img1=(new ImageIcon("assets/relocating-spell-obstace-task-2.jpg"));
		    			}
	    	}
	    	if(cast instanceof HealingSpell){
	    		if(((MapCell)btn).getCell() instanceof ChampionCell){
	    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof GryffindorWizard){
	    				img1=(new ImageIcon("assets/healling-spell-gryff-task2.jpg"));
	    				}
	    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof SlytherinWizard){
	    				img1=(new ImageIcon("assets/healling-spell-rsly-task2.jpg"));
	    					}
	    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof HufflepuffWizard){
	    				img1=(new ImageIcon("assets/healling-spell-huffle-task2.jpg"));	
	    				}
	    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof RavenclawWizard){
	    				img1=(new ImageIcon("assets/healling-spell-raven-task2.jpg"));
	    				}
	    		}
	    		
    	}	
	    }
	    	if(current instanceof ThirdTask){
	    		if(cast instanceof DamagingSpell){
		    		if(((MapCell)btn).getCell() instanceof ChampionCell){
		    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof GryffindorWizard){
		    				img1=(new ImageIcon("assets/damage-spell-gryff-task3.jpg"));
		    				}
		    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof SlytherinWizard){
		    				img1=(new ImageIcon("assets/damage-spell-sly-task3.jpg"));
		    					}
		    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof HufflepuffWizard){
		    				img1=(new ImageIcon("assets/damage-spell-huffle-task3.jpg"));	
		    				}
		    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof RavenclawWizard){
		    				img1=(new ImageIcon("assets/damage-spell-raven-task3.jpg"));
		    						
		    			}
		    		}
		    		if(((MapCell)btn).getCell() instanceof ObstacleCell){
		    			img1=(new ImageIcon("assets/damage-spell-obstace-task-3.jpg"));
		    			}
	    	}
	    	if(cast instanceof RelocatingSpell){
		    	if(((MapCell)btn).getCell() instanceof ChampionCell){
		    		if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof GryffindorWizard){
		    			img1=(new ImageIcon("assets/relocating-spell-gryf-task3.jpg"));
		    			}
		    		if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof SlytherinWizard){
		    			img1=(new ImageIcon("assets/relocating-spell-sly-task3.jpg"));
		    				}
		    		if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof HufflepuffWizard){
		    			img1=(new ImageIcon("assets/relocating-spell-huffle-task3.jpg"));	
		    			}
		    		if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof RavenclawWizard){
		    			img1=(new ImageIcon("assets/relocating-spell-raven-task3.jpg"));
		    					
		    		}
		    		}
		    		if(((MapCell)btn).getCell() instanceof ObstacleCell){
		    			img1=(new ImageIcon("assets/relocating-spell-obstace-task-3.jpg"));
		    			}
	    	}
	    	if(cast instanceof HealingSpell){
	    		if(((MapCell)btn).getCell() instanceof ChampionCell){
	    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof GryffindorWizard){
	    				img1=(new ImageIcon("assets/healling-spell-gryff-task3.jpg"));
	    				
	    			}
	    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof SlytherinWizard){
	    				img1=(new ImageIcon("assets/healling-spell-rsly-task3.jpg"));
	    					}
	    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof HufflepuffWizard){
	    				img1=(new ImageIcon("assets/healling-spell-huffle-task3.jpg"));	
	    				}
	    			if(((ChampionCell) ((MapCell) btn).getCell()).getChamp() instanceof RavenclawWizard){
	    				img1=(new ImageIcon("assets/healling-spell-raven-task3.jpg"));
	    						
	    			}
	    		}
	    		
    	}	
	    }
	    	btn.setIcon(img1);
	    	btn.setDisabledIcon(img1);
	    	//repaint();
	    	
	    	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    	
		    	cflash=-1;
		    	

		    	
	    		 
		        
	}
		
		


		
		if(markedcells!=null){
		for(Point p:markedcells){
			int i=(int) ((p.getY())+(p.getX()*10));
			MapCell cell=map.get(i);
			cell.setOpaque(true);
			cell.setIcon(null);
			cell.setDisabledIcon(null);
			if(cell.getCell() instanceof ChampionCell){
				if(((ChampionCell) cell.getCell()).getChamp() instanceof GryffindorWizard){
					cell.setIcon(new ImageIcon("assets/fire-gryff.jpg"));
					cell.setDisabledIcon(new ImageIcon("assets/fire-gryff.jpg"));
				}
				if(((ChampionCell) cell.getCell()).getChamp() instanceof SlytherinWizard){
					cell.setIcon(new ImageIcon("assets/fire-sly.jpg"));
					cell.setDisabledIcon(new ImageIcon("assets/fire-sly.jpg"));
				}
				if(((ChampionCell) cell.getCell()).getChamp() instanceof HufflepuffWizard){
					cell.setIcon(new ImageIcon("assets/fire-huffle.jpg"));
					cell.setDisabledIcon(new ImageIcon("assets/fire-huffle.jpg"));
				}
				if(((ChampionCell) cell.getCell()).getChamp() instanceof RavenclawWizard){
					cell.setIcon(new ImageIcon("assets/fire-raven.jpg"));
					cell.setDisabledIcon(new ImageIcon("assets/fire-raven.jpg"));
				}
			}
			else{
				if(cell.getCell() instanceof ObstacleCell){
					cell.setIcon(new ImageIcon("assets/fire-obstacle.jpg"));
					cell.setDisabledIcon(new ImageIcon("assets/fire-obstacle.jpg"));
				}
				else{
					cell.setIcon(new ImageIcon("assets/fire.jpg"));
					cell.setDisabledIcon(new ImageIcon("assets/fire.jpg"));
				}
			}
			
			}
			//cell.setBackground(new Color(139,37,0));
		markedcells=null;
		//repaint();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		}

		super.paint(g);

		
	 
    }
	
	public void FlashButton(Direction d,Point loc,Spell s){
		cast=s;
		int i=(int) ((loc.getY())+(loc.getX()*10));

		switch(d){
		case FORWARD:i=i-10;break;
		case BACKWARD:i=i+10;break;
		case LEFT:i--;break;
		case RIGHT:i++;break;
		default:break;
		}
		cflash= i;
		paint(this.getGraphics());

    	
    	
	}
	public void FlashButton(Point loc,Spell s){
		cast=s;
		int i=(int) ((loc.getY())+(loc.getX()*10));

		
		cflash= i;
		paint(this.getGraphics());

    	
    	
	}
	//For merpersons
	public void FlashButton(Point loc){
		int i=(int) ((loc.getY())+(loc.getX()*10));
		cast=null;
		
		cflash= i;
		paint(this.getGraphics());

    	
    	
	}
	public void DragonFire(ArrayList<Point> markedcells){
		this.markedcells=markedcells;
		paint(this.getGraphics());
	}
	

	
}

