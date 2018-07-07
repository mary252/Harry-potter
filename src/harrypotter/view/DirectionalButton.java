package harrypotter.view;

import harrypotter.model.world.Direction;

import javax.swing.JButton;

public class DirectionalButton extends JButton {
	Direction direction;
	public Direction getDirection() {
		return direction;
	}
	public DirectionalButton(Direction d){
		super();
		this.direction=d;
		this.setFocusable(false);
	}

}
