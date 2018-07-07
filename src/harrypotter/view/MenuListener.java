package harrypotter.view;

import java.io.IOException;

import harrypotter.model.character.Champion;

public interface MenuListener {
	public void onChampionadd(Champion c) throws IOException;
}
