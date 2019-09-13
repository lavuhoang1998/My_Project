package ooppj.sort;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import ooppj.sort.Sort.GraphCanvas;
import ooppj.sort.Sort.SortingAlgorithms;

public class Sort {
	//-----------------------PARAM--------------------------------
	
	//GENERAL VARIABLES
	private int len = 50;
	private int curAlg = 0;
	private int delay = 500;
		
	//GRAPH VARIABLES
	private final int SIZE = 600;
	private int current = -1;
	private int check = -1;
	private int width = SIZE/len;
	
	//ARRAYS
	private int[] list;
	private String[] algorithms = {"Shell Sort","Insertion Sort"};
	
	//---------------------------------------------------------------------------------------------	
	//BOOLEANS
	private boolean sorting = false;
	private boolean shuffled = true;
		
	//UTIL OBJECTS
	SortingAlgorithms algorithm = new SortingAlgorithms();
	Random r = new Random();
		
	//---------------------------------------------------------------------------------------------
	//FRAME
	private JFrame jf;
		
	//PANELS
	JPanel pane = new JPanel();
	GraphCanvas canvas;
		
	//LABELS
	JLabel delayL = new JLabel("Delay :");
	JLabel msL = new JLabel(delay+" ms");
	JLabel sizeL = new JLabel("Size :");
	JLabel lenL = new JLabel(len+"");
	JLabel algorithmL = new JLabel("Algorithms");
	
		
	//DROP DOWN BOX
	JComboBox alg = new JComboBox(algorithms);
		
	//BUTTONS
	JButton btSort = new JButton("Sort");
	JButton btShuffle = new JButton("Shuffle");
		
	//SLIDERS
	JSlider size = new JSlider(JSlider.HORIZONTAL,1,6,1);
	JSlider speed = new JSlider(JSlider.HORIZONTAL,0,2000,delay);
		
	//BORDER STYLE
	Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	
	//---------------------------------------------------------------------------------------------
	//CONSTRUCTOR
	public Sort() {
		shuffleList();	
		initialize();	
	}
	
	public void createList() {
		list = new int[len];	
		for(int i = 0; i < len; i++) {	
			list[i] = i+1;
		} 
	}
	
	public void shuffleList() {
		createList();
		for(int a = 0; a < 500; a++) {		//SHUFFLE RUNS 500 TIMES
			for(int i = 0; i < len; i++) {	
				int rand = r.nextInt(len);	
				int temp = list[i];			
				list[i] = list[rand];		
				list[rand] = temp;			
			}
		}
		sorting = false;
		shuffled = true;
	}
	
	public void initialize() {
		//SET UP FRAME
		jf = new JFrame();
		jf.setSize(800,635);
		jf.setTitle("Sort Visualize");
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.getContentPane().setLayout(null);
		
		//---------------------------------------------------------------------------------------------
		//SET UP TOOLBAR
		pane.setLayout(null);
		pane.setBounds(5,10,180,590);
		pane.setBorder(BorderFactory.createTitledBorder(loweredetched,"Controls"));
		
		//---------------------------------------------------------------------------------------------
		//SET UP ALGORITHM LABEL
		algorithmL.setHorizontalAlignment(JLabel.CENTER);
		algorithmL.setBounds(40,40,100,25);
		pane.add(algorithmL);
		
		//SET UP DROP DOWN
		alg.setBounds(30,80,120,25);
		pane.add(alg);

		//---------------------------------------------------------------------------------------------
		//SET UP SIZE LABEL
		sizeL.setHorizontalAlignment(JLabel.CENTER);
		sizeL.setBounds(15,150,150,25);
		pane.add(sizeL);
				
		//SET UP LEN LABEL
		lenL.setHorizontalAlignment(JLabel.LEFT);
		lenL.setBounds(140,180,50,25);
		pane.add(lenL);
				
		//SET UP SIZE SLIDER
		size.setMajorTickSpacing(50);
		size.setBounds(30,180,110,25);
		size.setPaintTicks(false);
		pane.add(size);
		
		//---------------------------------------------------------------------------------------------
		//SET UP DELAY LABEL
		delayL.setHorizontalAlignment(JLabel.CENTER);
		delayL.setBounds(20,250,100,25);
		pane.add(delayL);
		
		//SET UP MS LABEL
		msL.setHorizontalAlignment(JLabel.LEFT);
		msL.setBounds(130,280,50,25);
		pane.add(msL);
		
		//SET UP SPEED SLIDER
		speed.setMajorTickSpacing(5);
		speed.setBounds(20,280,110,25);
		speed.setPaintTicks(false);
		pane.add(speed);
				
		
		//---------------------------------------------------------------------------------------------
		//SET UP SORT BUTTON
		btSort.setBounds(40,380,100,25);
		pane.add(btSort);
				
		//SET UP SHUFFLE BUTTON
		btShuffle.setBounds(40,450,100,25);
		pane.add(btShuffle);		
		
		//---------------------------------------------------------------------------------------------
		//SET UP CANVAS FOR GRAPH
		canvas = new GraphCanvas();
		canvas.setBounds(190,0,SIZE,SIZE);
		canvas.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//---------------------------------------------------------------------------------------------
		jf.getContentPane().add(pane);
		jf.getContentPane().add(canvas);
		
		//---------------------------------------------------------------------------------------------
		//ADD ACTION LISTENERS
		alg.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				curAlg = alg.getSelectedIndex();
					}
				});
				
		size.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				int val = size.getValue();
				if(size.getValue() == 5)
					val = 4;
					len = val * 50;
					lenL.setText(len+"");
					if(list.length != len)
						shuffleList();
						reset();
					}	
				});
				
		speed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				delay = (int)speed.getValue();
				msL.setText(delay+" ms");
					}
				});
				
		btSort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(shuffled) {
				sorting = true;
					}
					}
				});
				
		btShuffle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shuffleList();
				reset();
					}
				});
				
		sorting();
			}
	
	//---------------------------------------------------------------------------------------------
	//SORTING STATE
	public void sorting() {
		if(sorting) {
			try {
				switch(curAlg) {	
					case 0:
						algorithm.shellSort();
						break;	
					case 1:
						algorithm.insertionSort();
						break;
					default:
						algorithm.insertionSort();
						break;
				}
			} catch(IndexOutOfBoundsException e) {}	
		}
		reset();	
		pause();	
	}
	
	//PAUSE STATE
	public void pause() {
		int i = 0;
		while(!sorting) {	//LOOP RUNS UNTIL SORTING STARTS
			i++;
			if(i > 100)
				i = 0;
			try {
				Thread.sleep(1);
			} catch(Exception e) {}
		}
		sorting();	//EXIT THE LOOP AND START SORTING
	}
	
	//RESET SOME PARAM
	public void reset() {
		sorting = false;
		current = -1;
		check = -1;
		Update();
	}
	
	//DELAY 
	public void delay() {
		try {
			Thread.sleep(delay);
		} catch(Exception e) {}
	}
	
	//UPDATES THE GRAPH AND LABELS
	public void Update() {	
		width = SIZE/len;
		canvas.repaint();
	}
	
	//MAIN METHOD
	public static void main(String[] args) {
		new Sort();
	}

	//CLASS Graph Canvas
	class GraphCanvas extends JPanel {
		
		public GraphCanvas() {
			setBackground(Color.black);
		}
		
		//PAINTS THE GRAPH
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int i = 0; i < len; i++) {	
				int HEIGHT = list[i]*width;	
				g.setColor(Color.white);	
				if(current > -1 && i == current) {
					g.setColor(Color.green);	//COLOR OF CURRENT
					}
				if(check > -1 && i == check) {
					g.setColor(Color.red);	//COLOR OF CHECKING
					}
				//DRAWS THE BAR AND THE OUTLINE
				g.fillRect(i*width, SIZE-HEIGHT, width, HEIGHT);
				g.setColor(Color.black);
				g.drawRect(i*width, SIZE-HEIGHT, width, HEIGHT);
				
				
			}
		}
	}
	
	//CLASS ALGORITHMS
	class SortingAlgorithms {
		
		public void shellSort() {
			int n=len;
			for (int gap = n/2; gap > 0; gap /= 2) {
				for (int i = gap; i < n; i += 1) {
					 int temp = list[i]; 
					 current = i;
					 check=i-gap;
					 int j; 
					 for (j = i; j >= gap && list[j - gap] > temp; j -= gap) {
		                    swap(j,j-gap);
		                }
					 Update();
	                 delay();
					 list[j] = temp;	
		                
		                
				}
				Update();
                delay();
			}}
				
		public void insertionSort1(int start, int end) {
			for(int i = start+1; i <= end; i++) {
				//current = i;
				int j = i;
				while(list[j] < list[j-1] && sorting) {
					swap(j,j-1);
					current = i;
					check = j;
					Update();
					delay();
					if(j > start+1)
						j--;
				}
			}
		}
		
	
		 public void insertionSort() 
		    { 
		        int n = len; 
		        for (int i = 1; i < n; ++i) { 
		            int key = list[i];
		            current=i;
		            int j = i - 1; 
		  
		            /* Move elements of arr[0..i-1], that are 
		               greater than key, to one position ahead 
		               of their current position */
		            while (j >= 0 && list[j] > key) { 
		                swap(j+1,j); 
		                check=j;
		                Update();
		                delay();
		                j = j - 1; 
		            } 
		            list[j + 1] = key; 
		        } 
		    } 
		
		public void swap(int i1, int i2) {
			int temp = list[i1];	
			list[i1] = list[i2];	
			list[i2] = temp;	
		}
	}
					
}
