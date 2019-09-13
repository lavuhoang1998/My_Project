package TowerOfHanoi;

import TowerOfHanoi.DrawTowers;
import TowerOfHanoi.GameState;

public class Main {
	
	//DrawTowers GUI;
	//static GameState gs;
	
	public static void main(String[] args)
	{	
		//create the GUI object
		DrawTowers GUI = new DrawTowers();
		
		//create the object that will control the state of the game
		GameState gs = new GameState(GUI);
		
		GUI.setGameState(gs);
		
		//show the GUI to the user
		GUI.setVisible(true);
	}
}
