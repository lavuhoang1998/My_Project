package TowerOfHanoi;

import java.awt.Color;
import java.util.ArrayList;

public class GameState {
	//-----------------------PARAM--------------------------------
	DrawTowers GUI;
	
	int numberOfBlocks, from, to;
	final int MAX_BLOCKS = 6;
	
	ArrayList<Block> pole1;
	ArrayList<Block> pole2;
	ArrayList<Block> pole3;
	
	ArrayList<String> solutions;
	
	Block [] block = new Block[MAX_BLOCKS];
	
	//---------------------------------------------------------------------------------------------	
	public GameState(DrawTowers gui)
	{
		GUI = gui;
		//simplest tower has one block
		numberOfBlocks = 1;
		
		pole1 = new ArrayList<Block>();
		pole2 = new ArrayList<Block>();
		pole3 = new ArrayList<Block>();
		solutions = new ArrayList<String>();
		
		//width and color each block
		block[5] = new Block(160, new Color(233,104,134));
		block[4] = new Block(140, new Color(90,169,222));
		block[3] = new Block(120, new Color(165,250,245));
		block[2] = new Block(100, new Color(217,139,255));
		block[1] = new Block(80, new Color(255,230,168));
		block[0] = new Block(60, new Color(246,215,62));
	}
	
	//Cac nut lien quan den tro choi
	public void buttonClick(String buttonText)
	{
		//sets up all blocks in order on pole 1
		if(buttonText == "Start")
		{
			int pole1Blocks = GUI.getNumberOfDisks();
			for(int i = pole1Blocks-1; i >= 0; i--)
				pole1.add(block[i]);
			
		}
		
		else if(buttonText.contains("Move"))
		{
			if(buttonText.contains("pole"))
			{
				from = Character.getNumericValue(buttonText.charAt(10));  	//Lay ki tu thu 10
				to = Character.getNumericValue(buttonText.charAt(20));		//Lay ki tu thu 20
			}
			moveDisk(from, to);
		}
	
		else if(buttonText == "Reset")
		{
			buttonClick("New Disks");
			buttonClick("Start");
		}
	
		else if(buttonText == "New Disks")
		{
			pole1.clear();
			pole2.clear();
			pole3.clear();
		}
	
		else if(buttonText == "Show me how to solve")
		{
			buttonClick("Reset");
		}
		
	}

	//---------------------------------------------------------------------------------------------	
	//Di Chuyen
	public void moveDisk(int p1, int p2){
		if(p1 == 1 && p2 == 2){
			pole2.add(pole1.get(pole1.size()-1));
			pole1.remove(pole1.size()-1);
		}
		else if(p1 == 1 && p2 == 3){
			pole3.add(pole1.get(pole1.size()-1));
			pole1.remove(pole1.size()-1);
		}
		else if(p1 == 2 && p2 == 1){
			pole1.add(pole2.get(pole2.size()-1));
			pole2.remove(pole2.size()-1);
		}
		else if(p1 == 2 && p2 == 3){
			pole3.add(pole2.get(pole2.size()-1));
			pole2.remove(pole2.size()-1);
		}
		if(p1 == 3 && p2 == 1){
			pole1.add(pole3.get(pole3.size()-1));
			pole3.remove(pole3.size()-1);
		}
		if(p1 == 3 && p2 == 2){
			pole2.add(pole3.get(pole3.size()-1));
			pole3.remove(pole3.size()-1);
		}
	}
	
	//---------------------------------------------------------------------------------------------	
	//Get Solution
	public ArrayList<String> getSolutions()
	{
		solutions.clear();
		move( GUI.getNumberOfDisks(), 1, 3, 2);
		return solutions;
	}
	
	//---------------------------------------------------------------------------------------------	
	//Thuat toan Towers of Hanoi
	public void move(int numberOfDisks, int startPole, int endPole, int usingPole) {
		if (numberOfDisks == 1) {
		  solutions.add("Move pole " + startPole + " to pole " + endPole);
	    } else {
		  move(numberOfDisks - 1, startPole, usingPole, endPole);
		  move(1, startPole, endPole, usingPole);
		  move(numberOfDisks - 1, usingPole, endPole, startPole);
		 }
	}
	
	//---------------------------------------------------------------------------------------------	
	//Check ket thuc( Khi tat ca block o cot 3)
	public boolean solvedCheck()
	{
		if(pole1.isEmpty() && pole2.isEmpty())
			return true;
		return false;
	}
}
