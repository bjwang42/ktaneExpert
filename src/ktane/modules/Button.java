//Handles Button module

package ktane.modules;

import utils.Input;
import ktane.BombState;

public class Button implements Module {

	private static final int EXPECTED_LEN = 2; 
	
	private String color;
	private String word; 

	//expects color, word
	@Override
	public String solve(String[] in)
	{
		if (!validateInput(in))
			return "Invalid input";
		
		//1. If the button is blue and the button says "Abort", hold the button and refer to "Releasing a Held Button".
		if (color.equals("BLUE") && word.equals("ABORT"))
			return HeldButton();
		
		//2. If there is more than 1 battery on the bomb and the button says "Detonate", press and immediately release the button.
		if (word.equals("DETONATE") && BombState.getBatteries() > 1)
			return releaseImmedietly();
		
		//3. If the button is white and there is a lit indicator with label CAR, hold the button and refer to "Releasing a Held Button".
		if (color.equals("WHITE") && BombState.getCAR())
			return HeldButton();
		
		//4. If there are more than 2 batteries on the bomb and there is a lit indicator with label FRK, 
		//press and immediately release the button.
		if (BombState.getFRK() && BombState.getBatteries() > 2)
			return releaseImmedietly();

		//5. If the button is yellow, hold the button and refer to "Releasing a Held Button".
		if (color.equals("YELLOW"))
			return HeldButton();
		
		//6. If the button is red and the button says "Hold", press and immediately release the button.
		if (color.equals("RED") && word.equals("HOLD"))
			return releaseImmedietly();
		
		return HeldButton();
	}
	
	private String HeldButton()
	{
		String color = Input.prompt("Press and hold - color?");
		if (color == null)
		{
			return "Invalid input";
		}
		
		int releaseOn = 1;  //default
		switch(color)
		{
		case "BLUE":
			releaseOn = 4;
			break;
		case "YELLOW":
			releaseOn = 5;
			break;
		}
		
		return ("Release on " + releaseOn);
	}
	
	private String releaseImmedietly()
	{
		return "Press and release";
	}
	
	
	private boolean validateInput(String[] in)
	{
		if (in == null || in.length != EXPECTED_LEN)
			return false;
		
		String c = in[0];
		String w = in[1];
		if (c == null || w == null)
			return false;
		color = c.toUpperCase();
		word = w.toUpperCase();
		return true;
	}
	
}
