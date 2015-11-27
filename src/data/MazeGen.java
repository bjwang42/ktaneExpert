//Maze helper class
//Handles initialization of hardcoded mazes and selection of the correct 
//maze given indicator information.
package data;

import utils.MazeUtils;

public class MazeGen {

	//mazes are numbered 0-8 in the same order as in the manual, with Top left being 0,
	//top right is 2 and bottom right being 8. 
	private final static int NUM_MAZES = 9;
	private static MazeNode[][] mazes = new MazeNode[NUM_MAZES][];
	

	
	//get Maze based on indicator - currently any one indicator is enough to 
	//uniquely ID a maze. If that changes this should be reworked
	public static MazeNode getMaze(int indicatorX, int indicatorY,
								   int startX, int startY)
	{
		Integer mazeNum = getMazeNum(indicatorX, indicatorY);
		Integer startIndex = MazeUtils.cordToIndex(startX, startY);
		if (mazeNum == null) 
		{
			System.out.println("Unknown maze at " + indicatorX + ", " +indicatorY);
			return null;
		}
		
		if (startIndex == null)
		{
			System.out.println("Bad start point at " + startX + ", " +startY);
			return null;			
		}
		
		System.out.println("Maze selected: " + mazeNum + " starting at " + startIndex);
		
		return getMaze(mazeNum, startIndex);
	}
	
	private static MazeNode getMaze(int num, int startIndex)
	{
		if (num < 0 || num >= NUM_MAZES)
			return null;
		
		if (mazes[num] == null)
		{
			InitMaze(num);
		}
		else
		{
			//if we're not initializing the maze it already exists and 
			//should be cleaned before being reused
			cleanMaze(mazes[num]);
		}
		return mazes[num][startIndex];
	}
	
	private static Integer getMazeNum(int x, int y)
	{
		Integer index = MazeUtils.cordToIndex(x,y);
		Integer i = null;
		
		switch(index){
			case 6:
			case 17:
				i = 0;
				break;
			case 10:
			case 19:
				i = 1;
				break;
			case 21:
			case 23:
				i = 2;
				break;
			case 0:
			case 18:
				i = 3;
				break;
			case 16:
			case 33:
				i = 4;
				break;
			case 4:
			case 26:
				i = 5;
				break;
			case 1:
			case 31:
				i = 6;
				break;
			case 3:
			case 20:
				i = 7; 
				break;
			case 8:
			case 24:
				i = 8;
				break;
		} //switch
		
		return i;
	}
	
	
	//START: Maze creation helpers
	private static void setRight(MazeNode[] l, int i)
	{
		if (l == null) return;
		if (i%6 == 5)
		{
			System.out.println("Right error");
			return;
		}
		setRight(l[i], l[i+1]);
	}
	private static void setDown(MazeNode[] l, int i)
	{
		if (l == null) return;
		if (i/6 == 5)
		{
			System.out.println("Down error");
			return;
		}
		setDown(l[i], l[i+6]);
	}	
	private static void setRight(MazeNode n1, MazeNode n2)
	{
		if (n1 == null || n2 == null)
			return;
		n1.setRight(n2);
		n2.setLeft(n1);
	}
	private static void setDown(MazeNode n1, MazeNode n2)
	{
		if (n1 == null || n2 == null)
			return;
		n1.setDown(n2);
		n2.setUp(n1);
	}
	//END: Maze creation helpers

	//START: Init Code for individual mazes
	private static MazeNode[] InitMazeList()
	{
		MazeNode[] l  = new MazeNode[36]; 
		for (int i = 0; i < l.length; ++i)
		{
			l[i] = new MazeNode(i);
		}
		return l;
	}
	
	//initiate maze based on maze index
	private static void InitMaze(int num)
	{
		switch(num)
		{
			case 0:
				InitMaze0();
				return;
			case 1: 
				InitMaze1();
				return;
			case 2: 
				InitMaze2();
				return;
			case 3: 
				InitMaze3();
				return;				
			case 4: 
				InitMaze4();
				return;
			case 5: 
				InitMaze5();
				return;
			case 6: 
				InitMaze6();
				return;
			case 7:
				InitMaze7();
				return;
			case 8:
				InitMaze8();
				return;
			};//end switch - maze initialization selection
	}
	
	private static void InitMaze0()
	{
		MazeNode[] l = InitMazeList();
		mazes[0] = l;
	
		setRight(l, 0);
		setRight(l, 1);
		setRight(l, 3);
		setRight(l, 4);
		setRight(l, 7);
		setRight(l, 9);
		setRight(l, 10);
		setRight(l, 13);
		setRight(l, 15);
		setRight(l, 16);
		setRight(l, 19);
		setRight(l, 20);
		setRight(l, 22);
		setRight(l, 24);
		setRight(l, 25);
		setRight(l, 27);
		setRight(l, 30);
		setRight(l, 32);
		setRight(l, 34);
		setDown(l, 0);
		setDown(l, 6);
		setDown(l, 12);
		setDown(l, 18);
		setDown(l, 24);
		setDown(l, 7);
		setDown(l, 2);
		setDown(l, 14);
		setDown(l, 26);
		setDown(l, 3);
		setDown(l, 15);
		setDown(l, 27);
		setDown(l, 11);
		setDown(l, 17);
		setDown(l, 23);
		setDown(l, 29);
	}
	
	private static void InitMaze1()
	{
		MazeNode[] l = InitMazeList();
		mazes[1] = l;
	
		setRight(l, 0);
		setRight(l, 1);
		setRight(l, 3);
		setRight(l, 4);
		setRight(l, 6);
		setRight(l, 8);
		setRight(l, 10);
		setRight(l, 13);
		setRight(l, 15);
		setRight(l, 16);
		setRight(l, 18);
		setRight(l, 20);
		setRight(l, 27);
		setRight(l, 31);
		setRight(l, 33);
		setRight(l, 34);
		
		setDown(l, 6);
		setDown(l, 12);
		setDown(l, 18);
		setDown(l, 24);
		setDown(l, 1);
		setDown(l, 13);
		setDown(l, 25);
		setDown(l, 8);
		setDown(l, 20);
		setDown(l, 26);
		setDown(l, 3);
		setDown(l, 15);
		setDown(l, 27);
		setDown(l, 4);
		setDown(l, 22);
		setDown(l, 11);
		setDown(l, 17);
		setDown(l, 23);
		setDown(l, 29);
	}
	
	private static void InitMaze2()
	{
		MazeNode[] l = InitMazeList();
		mazes[2] = l;
	
		setRight(l, 0);
		setRight(l, 1);
		setRight(l, 4);
		setRight(l, 9);
		setRight(l, 12);
		setRight(l, 15);
		setRight(l, 25);
		setRight(l, 30);
		setRight(l, 31);
		setRight(l, 32);
		setRight(l, 34);
		
		setDown(l, 0);
		setDown(l, 12);
		setDown(l, 18);
		setDown(l, 24);
		setDown(l, 7);
		setDown(l, 13);
		setDown(l, 19);
		setDown(l, 2);
		setDown(l, 8);
		setDown(l, 14);
		setDown(l, 20);
		setDown(l, 3);
		setDown(l, 15);
		setDown(l, 21);
		setDown(l, 27);
		setDown(l, 4);
		setDown(l, 16);
		setDown(l, 22);
		setDown(l, 28);
		setDown(l, 5);
		setDown(l, 11);
		setDown(l, 17);
		setDown(l, 23);
		setDown(l, 29);
	}
	
	private static void InitMaze3()
	{
		MazeNode[] l = InitMazeList();
		mazes[3] = l;
	
		setRight(l, 0);
		setRight(l, 2);
		setRight(l, 3);
		setRight(l, 4);
		setRight(l, 8);
		setRight(l, 9);
		setRight(l, 10);
		setRight(l, 13);
		setRight(l, 15);
		setRight(l, 19);
		setRight(l, 20);
		setRight(l, 21);
		setRight(l, 22);
		setRight(l, 24);
		setRight(l, 25);
		setRight(l, 26);
		setRight(l, 27);
		setRight(l, 30);
		setRight(l, 31);
		setRight(l, 33);
		
		setDown(l, 0);
		setDown(l, 6);
		setDown(l, 12);
		setDown(l, 18);
		setDown(l, 24);
		setDown(l, 1);
		setDown(l, 7);
		setDown(l, 8);
		setDown(l, 15);
		setDown(l, 28);
		setDown(l, 5);
		setDown(l, 11);
		setDown(l, 17);
		setDown(l, 23);
		setDown(l, 29);
	}
	
	private static void InitMaze4()
	{
		MazeNode[] l = InitMazeList();
		mazes[4] = l;
	
		setRight(l, 0);
		setRight(l, 1);
		setRight(l, 2);
		setRight(l, 3);
		setRight(l, 4);
		setRight(l, 6);
		setRight(l, 7);
		setRight(l, 8);
		setRight(l, 9);
		setRight(l, 12);
		setRight(l, 14);
		setRight(l, 16);
		setRight(l, 19);
		setRight(l, 20);
		setRight(l, 25);
		setRight(l, 26);
		setRight(l, 27);
		setRight(l, 31);
		setRight(l, 32);
		setRight(l, 33);
		setRight(l, 34);
		
		setDown(l, 6);
		setDown(l, 12);
		setDown(l, 18);
		setDown(l, 24);
		setDown(l, 13);
		setDown(l, 15);
		setDown(l, 9);
		setDown(l, 21);
		setDown(l, 4);
		setDown(l, 16);
		setDown(l, 5);
		setDown(l, 17);
		setDown(l, 23);
		setDown(l, 29);
	}
	
	private static void InitMaze5()
	{
		MazeNode[] l = InitMazeList();
		mazes[5] = l;
	
		setRight(l, 1);
		setRight(l, 3);
		setRight(l, 4);
		setRight(l, 9);
		setRight(l, 12);
		setRight(l, 16);
		setRight(l, 18);
		setRight(l, 20);
		setRight(l, 24);
		setRight(l, 28);
		setRight(l, 30);
		setRight(l, 31);
		setRight(l, 32);
		setRight(l, 34);
		
		setDown(l, 0);
		setDown(l, 6);
		setDown(l, 12);
		setDown(l, 24);
		setDown(l, 1);
		setDown(l, 7);
		setDown(l, 19);
		setDown(l, 2);
		setDown(l, 8);
		setDown(l, 20);
		setDown(l, 9);
		setDown(l, 15);
		setDown(l, 21);
		setDown(l, 27);
		setDown(l, 4);
		setDown(l, 16);
		setDown(l, 22);
		setDown(l, 5);
		setDown(l, 11);
		setDown(l, 23);
		setDown(l, 29);
	}
	
	private static void InitMaze6()
	{
		MazeNode[] l = InitMazeList();
		mazes[6] = l;
	
		setRight(l, 0);
		setRight(l, 1);
		setRight(l, 2);
		setRight(l, 4);
		setRight(l, 7);
		setRight(l, 9);
		setRight(l, 12);
		setRight(l, 14);
		setRight(l, 16);
		setRight(l, 18);
		setRight(l, 20);
		setRight(l, 21);
		setRight(l, 26);
		setRight(l, 27);
		setRight(l, 30);
		setRight(l, 31);
		setRight(l, 32);
		setRight(l, 33);
		setRight(l, 34);
		
		setDown(l, 0);
		setDown(l, 6);
		setDown(l, 18);
		setDown(l, 24);
		setDown(l, 7);
		setDown(l, 19);
		setDown(l, 14);
		setDown(l, 20);
		setDown(l, 3);
		setDown(l, 4);
		setDown(l, 16);
		setDown(l, 28);
		setDown(l, 5);
		setDown(l, 11);
		setDown(l, 23);
		setDown(l, 29);
	}
	
	private static void InitMaze7()
	{
		MazeNode[] l = InitMazeList();
		mazes[7] = l;
	
		setRight(l, 1);
		setRight(l, 2);
		setRight(l, 4);
		setRight(l, 6);
		setRight(l, 7);
		setRight(l, 9);
		setRight(l, 13);
		setRight(l, 14);
		setRight(l, 15);
		setRight(l, 19);
		setRight(l, 21);
		setRight(l, 22);
		setRight(l, 26);
		setRight(l, 27);
		setRight(l, 28);
		setRight(l, 30);
		setRight(l, 31);
		setRight(l, 32);
		setRight(l, 33);
		setRight(l, 34);
		
		setDown(l, 0);
		setDown(l, 6);
		setDown(l, 12);
		setDown(l, 18);
		setDown(l, 24);
		setDown(l, 1);
		setDown(l, 13);
		setDown(l, 25);
		setDown(l, 20);
		setDown(l, 3);
		setDown(l, 4);
		setDown(l, 16);
		setDown(l, 5);
		setDown(l, 11);
		setDown(l, 17);
	}
	
	private static void InitMaze8()
	{
		MazeNode[] l = InitMazeList();
		mazes[8] = l;
	
		setRight(l, 2);
		setRight(l, 3);
		setRight(l, 4);
		setRight(l, 5);
		setRight(l, 8);
		setRight(l, 12);
		setRight(l, 13);
		setRight(l, 15);
		setRight(l, 20);
		setRight(l, 22);
		setRight(l, 27);
		setRight(l, 30);
		setRight(l, 32);
		setRight(l, 34);
		
		setDown(l, 0);
		setDown(l, 6);
		setDown(l, 12);
		setDown(l, 18);
		setDown(l, 24);
		setDown(l, 1);
		setDown(l, 7);
		setDown(l, 19);
		setDown(l, 25);
		setDown(l, 8);
		setDown(l, 20);
		setDown(l, 26);
		setDown(l, 15);
		setDown(l, 27);
		setDown(l, 4);
		setDown(l, 10);
		setDown(l, 28);
		setDown(l, 5);
		setDown(l, 11);
		setDown(l, 17);
		setDown(l, 23);
	}
	//END: Init Code for individual mazes
	private static void cleanMaze(MazeNode[] l)
	{
		if (l == null)
			return;
		
		for (int i = 0; i < l.length; ++i)
		{
			l[i].reset();
		}
	}
}
