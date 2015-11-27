//Simple Wires module
package ktane.modules;

import ktane.BombState;

public class Wires implements Module {


	private static final int MIN_LEN = 3;
	private static final int MAX_LEN = 6;

	public Wires() {
	};

	
	//returns the wire to cut counting the first wire as 1
	@Override
	public String solve(String[] colors) {
		if (colors == null)
			return null;

		int len = colors.length;
		if (len < MIN_LEN || len > MAX_LEN)
			return null;

		cleanColorArray(colors);
		Integer cut = null;
		switch (len) {
		case 3:
			cut = solveWires3(colors);
			break;
		case 4:
			cut = solveWires4(colors);
			break;
		case 5:
			cut = solveWires5(colors);
			break;
		case 6:
			cut = solveWires6(colors);
			break;
		}
		
		if (cut != null)
		{
			return ("Cut wire: " + cut.toString()); 
			
		}
		return null;
	}
	
	


	//START: INDIVIDUAL WIRE SOLUTIONS
	private Integer solveWires3(String [] colors)
	{
		final int expt_length = 3; 
		if (colors == null) return null;
		if (colors.length != expt_length) return null;
		
		ColorsArray counts = new ColorsArray();
		counts.fill(colors);
		
		//If there are no red wires, cut the second wire.
		if (counts.getValAt(eColor.RED) == 0)
			return 2;
		
		//Otherwise, if the last wire is white, cut the last wire.
		String last = colors[colors.length - 1]; 
		if ( last != null && last.equals("white"))
			return expt_length;
		
		//Otherwise, if there is more than one blue wire, cut the last blue wire.
		if (counts.getValAt(eColor.BLUE) > 1)
		{
			return getLastOfColor(colors, eColor.BLUE);
		}
		//Otherwise, cut the last wire.
		return expt_length;
	}
	
	private Integer solveWires4(String [] colors)
	{
		final int expt_length = 4; 
		if (colors == null) return null;
		if (colors.length != expt_length) return null;
		
		ColorsArray counts = new ColorsArray();
		counts.fill(colors);
		
		//If there is more than one red wire and the last digit of the serial number is odd, cut the last red wire.
		if ((counts.getValAt(eColor.RED) > 1) && BombState.getSerialOdd())
		{
			return getLastOfColor(colors, eColor.BLUE);
		}
		//Otherwise, if the last wire is yellow and there are no red wires, cut the first wire.
		if ((counts.getValAt(eColor.RED) == 0) && eColor.YELLOW.name().equals(colors[expt_length - 1]))
			return 1;
		//Otherwise, if there is exactly one blue wire, cut the first wire.
		if (counts.getValAt(eColor.BLUE) == 1)
			return 1;
		//Otherwise, if there is more than one yellow wire, cut the last wire.
		if (counts.getValAt(eColor.YELLOW) > 1)
			return expt_length;
		//Otherwise, cut the second wire.
		return 2;
	}
	
	private Integer solveWires5(String [] colors)
	{
		final int expt_length = 5; 
		if (colors == null) return null;
		if (colors.length != expt_length) return null;
		
		ColorsArray counts = new ColorsArray();
		counts.fill(colors);
	
		//If the last wire is black and the last digit of the serial number is odd, cut the fourth wire.
		if (eColor.BLACK.name().equals(colors[expt_length - 1]) && BombState.getSerialOdd())
		{
			return 4;
		}
		//Otherwise, if there is exactly one red wire and there is more than one yellow wire, cut the first wire.
		if ((counts.getValAt(eColor.RED) == 1) && (counts.getValAt(eColor.YELLOW) > 1))
		{
			return 1; 
		}
		//Otherwise, if there are no black wires, cut the second wire.
		if (counts.getValAt(eColor.BLACK) == 0)
			return 2;
		//Otherwise, cut the first wire.
		return 1;
	}
	
	private Integer solveWires6(String [] colors)
	{
		final int expt_length = 6; 
		if (colors == null) return null;
		if (colors.length != expt_length) return null;
		
		ColorsArray counts = new ColorsArray();
		counts.fill(colors);
		
		//If there are no yellow wires and the last digit of the serial number is odd, cut the third wire.
		if ((counts.getValAt(eColor.YELLOW) > 1) && BombState.getSerialOdd())
		{
			return 3;
		}
		//Otherwise, if there is exactly one yellow wire and there is more than one white wire, cut the fourth wire.
		if ((counts.getValAt(eColor.YELLOW) == 1) && (counts.getValAt(eColor.WHITE) > 1))
		{
			return 4; 
		}
		//Otherwise, if there are no red wires, cut the last wire.
		if (counts.getValAt(eColor.RED) == 0)
			return expt_length;
		//Otherwise, cut the fourth wire.
		return 4;
	}
	//END: INDIVIDUAL WIRE SOLUTIONS
		
	private Integer getLastOfColor(String[] colors, eColor e) {
		if (colors == null)
			return null;
		
		for (int i = colors.length - 1; i >= 0; --i)
		{
			if (e.name().equals(colors[i]))
				return i + 1; //from 0 index to base 1 for people 
		}
		return null;
	}

	
	//make sure all the input is upper case so it matches enum.name()
	private void cleanColorArray(String[] colors) {
		if (colors == null)
			return;

		String s;
		for (int i = 0; i < colors.length; ++i) {
			s = colors[i].toUpperCase();
			colors[i] = s;

		}
	}

	protected enum eColor {
		RED(0), WHITE(1) , YELLOW(2), BLUE(3), BLACK(4), MAX_VAL(5);
		

		private int i;

		eColor(int val) {
			this.i = val;
		}

		public int i() {
			return i;
		}

	} // enum

	//Array of colors matching enum indexes
	private class ColorsArray {
		private int[] arr;

		public ColorsArray() {
			arr = new int[eColor.MAX_VAL.i];
		}

		public void fill(String[] colors) {
			if (colors == null)
				return;

			for (String s : colors) {
				if (s == null)
					continue;

				for (eColor e : eColor.values())
				{
					if (e.name().equals(s))
					{
						arr[e.i()]++;
						break;
					}
				}
			} // count
		}

		public int getValAt(eColor e) {
			return arr[e.i];
		}
	}; // ColorArray class
} // Wires class
