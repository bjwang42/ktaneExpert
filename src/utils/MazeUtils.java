package utils;

public class MazeUtils {
	//cordinates start at 1 not 0
	private final static int MAX_X_CORD = 6;
	private final static int MAX_Y_CORD = 6;
	
	public static Integer cordToIndex(int x, int y)
	{
		if (x > MAX_X_CORD || x < 0 || y > MAX_Y_CORD || y < 0)
			return null;
		
		return ((x - 1) + (y-1)*6);
	}
}
