//Handles Keypad module
package ktane.modules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Names of symbols in order by col:
 * 1: "head", "alpha", "lamda", "lightning", "cat", "hotel", "charlie"
 * 2: "echo", "head", "charlie", "at", "star", "hotel", "question"
 * 3: "copy", "butt", "at", "x-ray", "romeo", "lamda", "star"
 * 4: "six", "paragraph", "bravo", "cat", "x-ray", "question", "smile"
 * 5: "psi", "smile", "bravo", "charlie", "paragraph", "three", "star"
 * 6: "six", "echo", "track", "ash", "psi", "november", "omega"
 */


public class Keypads implements Module{

	//array of maps consisting of string (symbol), integer (index) pairs
	private static List<Map<String,Integer>> cols;
	private static boolean init = false;
	private static final int EXPECTED_COL_SIZE = 7;
	private static final int EXPECTED_COL = 6;
	private static final int INPUT_LEN = 4;
	
	
	public Keypads()
	{
		if (!init)
		{
			initCols();
			init = true;
		}
	}
	
	//expects 4 symbols
	@Override
	public String solve(String [] in)
	{
		if (in == null || in.length != INPUT_LEN)
			return null;
		
		//find position of each element in the columns
		//correct column is found when all elements found in the same column
		int[] pos = new int[INPUT_LEN];
		boolean done = false;
		for (Map<String, Integer> col : cols)
		{
			for (int i = 0; i < INPUT_LEN; ++i)
			{
				if (col.containsKey(in[i]))
				{
					pos[i] = col.get(in[i]);
					if (i == INPUT_LEN - 1)
						done = true;
				}
				else
					break;
			}
			if (done)
				break;
		}
		
		if (!done)
		{
			return "error, invalid input?";
		}
		
		//get the right order
		//kind of insertion sort, efficient enough for 4 elements
		String output = "order: ";
		int found = 0;
		for (int i = 0; i < EXPECTED_COL_SIZE; ++i)
		{
			for (int j = 0; j < INPUT_LEN; ++j)
			{
				if (i == pos[j])
				{
					output += in[j];
					output += " ";
					found++;
					continue;
				}
			}
			if (found == 4)
				break;
		}
		return output;
	}
	
	//START: INITIALIZATION
	private void initCols()
	{
		cols = new ArrayList<Map<String,Integer>>(EXPECTED_COL);
		initCol(new String[] {"head", "alpha", "lamda", "lightning", "cat", "hotel", "charlie"});
		initCol(new String[] {"echo", "head", "charlie", "at", "star", "hotel", "question"});
		initCol(new String[] {"copy", "butt", "at", "x-ray", "romeo", "lamda", "star"});
		initCol(new String[] {"six", "paragraph", "bravo", "cat", "x-ray", "question", "smile"});
		initCol(new String[] {"psi", "smile", "bravo", "charlie", "paragraph", "three", "star"});
		initCol(new String[] {"six", "echo", "track", "ash", "psi", "november", "omega"});
	}
	
	private void initCol(String[] arr)
	{
		if (arr == null || arr.length != EXPECTED_COL_SIZE)
			return;
		Map<String,Integer> m = new HashMap<String, Integer>();
		for (int i = 0; i < EXPECTED_COL_SIZE; ++i)
		{
			if (arr[i] == null) return;
			m.put(arr[i], i);
		}
			
		cols.add(m);
	}
	//END: INITIALIZATION
	
	public static void main (String args[])
	{
		Keypads k = new Keypads();
		System.out.println(k.solve(new String[]{"butt", "star", "lamda", "romeo"}));
		System.out.println(k.solve(new String[]{"cat", "bravo", "smile", "six"}));
		System.out.println(k.solve(new String[]{"question", "hotel", "star", "at"}));
	}
}
