package harrypotter.controller;

import java.io.IOException;

import harrypotter.model.character.Champion;

public interface TournamentListener {
	public void beginSecond() throws IOException;
	public void beginThird() throws IOException;
	public void winner(Champion c);
	public void death();
}
