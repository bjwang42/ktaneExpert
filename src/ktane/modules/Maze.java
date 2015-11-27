//Maze module
package ktane.modules;

import java.util.Queue;
import java.util.Stack;

import data.MazeGen;
import data.MazeNode;
import utils.MazeUtils;



public class Maze implements Module{
	
	private static final int EXPECTED_ARGS = 6; 

	//order expected: indicatorX, indicatorY, lightX, lightY, 
	//triangle (goal) X, triangle Y
	public String solve(String [] args)
	{
		if (args == null || args.length != EXPECTED_ARGS)
			return "incorrect args count ";
		int indicatorX, indicatorY, lightX, lightY, goalX, goalY;
		try
		{
			indicatorX = Integer.parseInt(args[0]);
			indicatorY = Integer.parseInt(args[1]);
			lightX = Integer.parseInt(args[2]);
			lightY = Integer.parseInt(args[3]);
			goalX = Integer.parseInt(args[4]);
			goalY = Integer.parseInt(args[5]);
		}
		catch (NumberFormatException e)
		{
			return "incorrect arg format";
		}
		
		Integer goalIndex = MazeUtils.cordToIndex(goalX, goalY);
		if (goalIndex == null)
			return "invalid goal node";
		System.out.println("Going to: " + goalIndex.toString());
		
		return getPath(indicatorX, indicatorY, lightX, lightY, goalIndex);
	}
	

	private String getPath(int indicatorX, int indicatorY, int startX, int startY, int goal)
	{
		Stack<Character> s = null;
		MazeNode head = MazeGen.getMaze(indicatorX, indicatorY, startX, startY);

		s = findPath(head, goal);

		String directions = "";
		if (s != null)
		{
			Character c;
			while (!s.isEmpty())
			{
				c = s.pop();
				directions += c;
				directions += " ";
			}
		}
		return directions;
		
	}
	
	
	//Use BFS to find path through maze
	private Stack<Character> findPath(MazeNode start, int goal)
	{
		Queue<MazeNode> q = new java.util.LinkedList<MazeNode>();
		if (start == null)
			return null;
		
		if (goal >= 36)
			return null;
		
		q.add(start);
		start.setVisited(true);
		addNeighbors(q,start);
		
		MazeNode n;
		while (!q.isEmpty())
		{
			n = q.poll();
			if (n.getId() == goal)
			{
				return makeReturnPath(n);
			}
			else 
				addNeighbors(q,n);
		}
		
		//if here no path - empty vector
		return new Stack<Character>(); 
		
	}
	
	
	private Stack<Character> makeReturnPath(MazeNode goal)
	{
		if (goal == null)
			return null;
		
		Stack<Character> s = new Stack<Character>(); 
		
		MazeNode cur = goal;
		Character d;
		do
		{
			d = getDirectionOfPrevious(cur);
			if (d != null)
				s.push(d);
			cur = cur.getPrev();
		} while (cur != null);
		
		return s;		
	}
	
	private Character getDirectionOfPrevious(MazeNode n)
	{
		if (n == null) 
			return null;
		MazeNode prev = n.getPrev();
		
		if (prev == null)
			return null;
		
		if (prev == n.getLeft())
			return 'R'; 
		if (prev == n.getRight())
			return 'L';
		if (prev == n.getUp())
			return 'D';
		if (prev == n.getDown())
			return 'U';
		
		//something bad happened
		return 'E';
	}
	
	//add all neighbors to queue for BFS
	private void addNeighbors(Queue<MazeNode> q, MazeNode n)
	{
		if (n == null || q == null)
			return;
		
		addNeighbor(q, n,  n.getLeft());
		addNeighbor(q, n, n.getUp());
		addNeighbor(q, n, n.getDown());
		addNeighbor(q, n, n.getRight());
	}
	
	//Add single neighbor to queue if not yet visited. 
	//Also track visited and previous
	private void addNeighbor(Queue<MazeNode> q, MazeNode prev, MazeNode toAdd)
	{
		if (toAdd != null && !toAdd.getVisited() && prev != null)
		{
			toAdd.setVisited(true);
			toAdd.setPrev(prev);
			q.add(toAdd);
		}
	}
	

	public static void main (String[] args)
	{
		Maze m = new Maze();
		System.out.println(m.solve(new String[] {"2", "4", "2", "2", "3", "3"} ));
		
	}
	
}
