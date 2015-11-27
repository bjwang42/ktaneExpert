//Tracks global state of bomb (indicators, batteries, strikes)
//Including prompting for information when needed

package ktane;

import utils.Input;

public class BombState {
	private static Boolean indicatorCAR = null;
	private static Boolean indicatorFRK = null; 
	private static Integer batteries = null;
	private static Boolean serialOdd = null;
	
	private static int strikes = 0;
	
	
	public static void reset() {
		indicatorCAR = null;
		indicatorFRK = null;
		batteries = null;
		serialOdd = null;
		strikes = 0;
	}
	
	public static boolean getCAR() {
		if (indicatorCAR == null)
			indicatorCAR = Input.promptForBool("Is C A R indicator lit?");
		return indicatorCAR;
	}
	public static boolean getFRK() {
		if (indicatorFRK == null)
			indicatorFRK = Input.promptForBool("Is F R K indicator lit?");
		return indicatorFRK;
	}
	public static int getBatteries(){
		if (batteries == null)
			batteries = Input.promptForInt("How many batteries?");
		return batteries;
	}
	public static boolean getSerialOdd()
	{
		if (serialOdd == null)
			serialOdd = Input.promptForBool("Is serial number odd?");
		return serialOdd;
	}
	
	public static int getStrikes()
	{
		return strikes;
	}
	
	
	public static void setIndicatorCar(boolean s)
	{
		indicatorCAR = s;
	}
	public static void setIndicatorFRK(boolean s)
	{
		indicatorFRK = s;
	}	
	public static void setBatteries(Integer i)
	{
		batteries = i;
	}
	public static void setSerialOdd(boolean odd)
	{
		serialOdd = odd;
	}
	public static void addStrike()
	{
		strikes++;
	}
	
}
