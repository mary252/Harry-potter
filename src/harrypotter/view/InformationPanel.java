package harrypotter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.tournament.Tournament;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class InformationPanel extends JPanel {
	Tournament t;
	JPanel champs;
	private infopan info;
	public infopan getInfo() {
		return info;
	}

	public void setInfo(infopan info) {
		this.info = info;
	}
	ArrayList<Champinfo> champslist=new ArrayList<Champinfo>();
	public InformationPanel(Tournament t){
		this.t=t;
		this.setFocusable(false);
		champs=new JPanel(new GridLayout(1,4));
		champs.setOpaque(false);
		try {
			info=new infopan();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//info.setBorder(new TitledBorder(new LineBorder(Color.black,5),"Events"));
		//info.setSize(100, 500);
		this.setOpaque(false);
		for(Champion c:t.getChampions()){
			addChamp(c);
		}

		this.setPreferredSize(new Dimension(1420,240));
		this.add(champs,BorderLayout.WEST);
		this.add(info,BorderLayout.EAST);
		setBorder(new TitledBorder(new LineBorder(Color.black, 5), "Information:"));
	}
	
	public void addChamp(Champion c){
		Champinfo a=null;
		if(c instanceof GryffindorWizard){
			try {
				a=new gryffinfo(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//a.setBackground(Color.red);
		}
		if(c instanceof SlytherinWizard){
			//a.setBackground(Color.green);
			 try {
				a=new slyinfo(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(c instanceof HufflepuffWizard){
			//a.setBackground(Color.yellow);
			 try {
				a=new huffleinfo(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(c instanceof RavenclawWizard){
			//a.setBackground(Color.cyan);
			 try {
				a=new raveninfo(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(a!=null){
		champs.add(a);
		champslist.add(a);
		info.append(((Wizard)c).getName()+" has joined!"+'\n');
		if(t.getChampions().size()==4){
			info.append("Let the tournament begin!");}}
		
		this.repaint();
		this.revalidate();
	}
	public void event(String s){
		info.setText(s);
	}
	public void update(){
		this.removeAll();
		champslist=new ArrayList<Champinfo>();
		champs.removeAll();
		try {
			info=new infopan();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//info.setBorder(new TitledBorder(new LineBorder(Color.black,5),"Events"));
		//info.setSize(100, 500);
		for(Champion c:t.getChampions()){
			addChamp(c);
		}
		this.add(champs,BorderLayout.CENTER);
		this.add(info,BorderLayout.EAST);
		setBorder(new TitledBorder(new LineBorder(Color.black, 5), "Information:"));
		repaint();
		revalidate();

	}
	public String checkDeaths(){
		String r="";
		for(Champinfo i:champslist){
			Champion c= i.getChamp();
			if(((Wizard)c).getHp()==0){
				r=r+((Wizard)c).getName()+" has met his end."+'\n';
			}
		}
		return r;
	}
}
