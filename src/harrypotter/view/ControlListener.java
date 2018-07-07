package harrypotter.view;

import java.io.IOException;

import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.Spell;
import harrypotter.model.world.Direction;

public interface ControlListener {
	public void bufferSpell(Spell s);
	public void castHeal(HealingSpell h);
	public void trait();
	public void slyTrait();
	public void pot(Potion p);
}
