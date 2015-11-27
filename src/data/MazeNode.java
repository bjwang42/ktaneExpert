//MazeNode 
package data;

public class MazeNode {
	private int id; 
	private MazeNode up;
	private MazeNode down;
	private MazeNode right;
	private MazeNode left;
	private MazeNode prev;
	private boolean visited;
	
	public MazeNode(int id, MazeNode u, MazeNode d, MazeNode l, MazeNode r)
	{
		this.id = id;
		up = u;
		down = d;
		right = r; 
		left = l;
		prev = null;
		visited = false;	
	}
	
	public MazeNode(int id)
	{
		this.id=id;
	}
	
	public void reset()
	{
		prev = null;
		visited = false;
	}
	
	public void setVisited(boolean v)
	{
		visited = v;
	}
	
	public boolean getVisited()
	{
		return visited; 
	}
	
	
	public MazeNode getUp()
	{
		return up;
	}
	public MazeNode getDown()
	{
		return down;
	}		
	public MazeNode getRight()
	{
		return right;
	}
	public MazeNode getLeft()
	{
		return left;
	}
	public MazeNode getPrev()
	{
		return prev;
	}
	
	public MazeNode setUp(MazeNode n)
	{
		return up = n;
	}
	public MazeNode setDown(MazeNode n)
	{
		return down = n;
	}
	public MazeNode setLeft(MazeNode n)
	{
		return left = n;
	}
	public MazeNode setRight(MazeNode n)
	{
		return right = n;
	}
	public MazeNode setPrev(MazeNode n)
	{
		return prev = n;
	}
	public int getId()
	{
		return id;
	}
}
