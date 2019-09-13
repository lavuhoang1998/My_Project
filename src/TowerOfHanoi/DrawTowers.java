package TowerOfHanoi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class DrawTowers extends JFrame implements ActionListener {

	//-----------------------PARAM--------------------------------
	GameState gameState;

	int diskNumber, movesCount, solutionIndex, fewestMoves;
	ArrayList<String> solutions;

	//---------------------------------------------------------------------------------------------	
	JPanel pnlMain;
	JLabel lbDisks, lbTitle, lbResult, lbMoves, lbMovesCount;
	JButton btReset, btPlus, btMinus, btStart, btNewDisks, btSolve;
	MyPanel myPanel;
	
	//---------------------------------------------------------------------------------------------	
	public DrawTowers() {
		gameState = null;
		initUI();
	}

	private void initUI() {
		myPanel = new MyPanel();
		getContentPane().add(myPanel);

		lbDisks = new JLabel("Disks: 1");
		lbDisks.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbDisks.setBounds(28, 364, 60, 14);
		myPanel.add(lbDisks);

		btPlus = new JButton("+");
		btPlus.addActionListener(this);
		btPlus.setBounds(98, 350, 41, 20);
		myPanel.add(btPlus);

		btMinus = new JButton("-");
		btMinus.addActionListener(this);
		btMinus.setBounds(98, 375, 41, 20);
		myPanel.add(btMinus);
		
		lbMoves = new JLabel("Moves: ");
		lbMoves.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbMoves.setBounds(175, 364, 65, 14);
		myPanel.add(lbMoves);

		movesCount = 0;
		fewestMoves = 0;
		lbMovesCount = new JLabel("" + movesCount);
		lbMovesCount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbMovesCount.setBounds(232, 364, 29, 14);
		myPanel.add(lbMovesCount);

		lbTitle = new JLabel("Towers of Hanoi");
		lbTitle.setForeground(new Color(255, 255, 153));
		lbTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		lbTitle.setBounds(10, 11, 240, 31);
		myPanel.add(lbTitle);
		
		btNewDisks = new JButton("New Disks");
		btNewDisks.addActionListener(this);
		btNewDisks.setEnabled(false);
		btNewDisks.setBounds(260, 11, 108, 23);
		myPanel.add(btNewDisks);

		btStart = new JButton("Start");
		btStart.addActionListener(this);
		btStart.setBounds(381, 11, 89, 23);
		myPanel.add(btStart);

		btReset = new JButton("Reset");
		btReset.addActionListener(this);
		btReset.setEnabled(false);
		btReset.setBounds(480, 11, 89, 23);
		myPanel.add(btReset);
		
		btSolve = new JButton("Show me how to solve");
		btSolve.addActionListener(this);
		btSolve.setBounds(342, 329, 175, 37);
		btSolve.setEnabled(false);
		myPanel.add(btSolve);
		
		lbResult = new JLabel("The quickest solution is n moves.");
		lbResult.setForeground(new Color(153, 0, 102));
		lbResult.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lbResult.setVisible(false);
		lbResult.setBounds(87, 45, 497, 23);
		myPanel.add(lbResult);

		setTitle("Towers of Hanoi");
		setSize(600, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	//---------------------------------------------------------------------------------------------	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton temp = (JButton) e.getSource();
		String buttonText = temp.getText();
		gameState.buttonClick(buttonText);
		diskNumber = getNumberOfDisks();
		
		if (buttonText == "+") {
			if (diskNumber < 6)
				diskNumber++;
			lbDisks.setText("Disks: " + diskNumber);
		} 
		
		else if (buttonText == "-") {
			if (diskNumber > 1)
				diskNumber--;
			lbDisks.setText("Disks: " + diskNumber);
		} 
		
		else if (buttonText == "Start") {
			movesCount = 0;
			lbMovesCount.setText("0");
			btSolve.setEnabled(true);
			btSolve.setText("Show me how to solve");
			btNewDisks.setEnabled(true);
			btStart.setEnabled(false);
			btPlus.setEnabled(false);
			btMinus.setEnabled(false);
			btReset.setEnabled(true);

			// draw poles
			myPanel.setPole(1, gameState.pole1);
			myPanel.setPole(2, gameState.pole2);
			myPanel.setPole(3, gameState.pole3);
			myPanel.repaint();
		} 
		
		else if (buttonText.contains("Move")) {
			movesCount++;
			updateMovesCount();
			// set the solutions button to next move
			if (buttonText.contains("pole")) {
				solutionIndex++;
				if (solutionIndex < solutions.size())
					btSolve.setText(solutions.get(solutionIndex));
			}

			// draw poles
			myPanel.setPole(1, gameState.pole1);
			myPanel.setPole(2, gameState.pole2);
			myPanel.setPole(3, gameState.pole3);
			myPanel.repaint();
			
			// if the game is over
			if (gameState.solvedCheck()) {
				setResult();
				lbResult.setVisible(true);
				btSolve.setEnabled(false);
				btSolve.setText("Show me how to solve");
				movesCount = 0;
				lbMovesCount.setText("0");
			}
		} 
		
		else if (buttonText == "Reset") {
			movesCount = 0;
			lbMovesCount.setText("0");
			btSolve.setText("Show me how to solve");
			btSolve.setEnabled(true);
			lbResult.setVisible(false);

			// draw poles
			myPanel.setPole(1, gameState.pole1);
			myPanel.setPole(2, gameState.pole2);
			myPanel.setPole(3, gameState.pole3);
			myPanel.repaint();
		}
		
		else if (buttonText == "New Disks") {
			lbResult.setVisible(false);
			btSolve.setText("Show me how to solve");
			btSolve.setEnabled(false);
			btStart.setEnabled(true);
			btReset.setEnabled(false);
			btPlus.setEnabled(true);
			btMinus.setEnabled(true);

			// draw poles
			myPanel.setPole(1, gameState.pole1);
			myPanel.setPole(2, gameState.pole2);
			myPanel.setPole(3, gameState.pole3);
			myPanel.repaint();
		} 
		
		else if (buttonText == "Show me how to solve") {
			// reset
			movesCount = 0;
			lbMovesCount.setText("0");

			myPanel.setPole(1, gameState.pole1);
			myPanel.setPole(2, gameState.pole2);
			myPanel.setPole(3, gameState.pole3);
			myPanel.repaint();

			solutionIndex = 0;
			// get all necessary moves in an array
			solutions = gameState.getSolutions();
			btSolve.setText(solutions.get(solutionIndex));
	}
}
	
	//---------------------------------------------------------------------------------------------
	public void updateMovesCount() {
		lbMovesCount.setText("" + movesCount);
	}

	public void setResult() {
		fewestMoves = (int) (Math.pow(2, diskNumber) - 1);
		if (fewestMoves == 1) lbResult.setText("The quickest solution is 1 move.");
		else {
			lbResult.setText("The quickest solution is " + fewestMoves + " moves.");
		}	
	}
	
	//---------------------------------------------------------------------------------------------
	// returns number of disks in the current game
	public int getNumberOfDisks() {
		char diskNumChar = lbDisks.getText().charAt(
				lbDisks.getText().length() - 1);
		return Character.getNumericValue(diskNumChar);
	}

	// initialize gamestate
	public void setGameState(GameState gs) {
		gameState = gs;
	}
}
