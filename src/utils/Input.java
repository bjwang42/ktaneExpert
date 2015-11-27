//Helper class to prompt user for information
package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {

	public static String prompt(String prompt)
	{
		System.out.println(prompt);
		String s = Input.getInput();
		return s.toUpperCase();
	}
	
	
	//Requests input from user, doesn't stop until it gets it
	public static Boolean promptForBool(String prompt)
	{
		System.out.println(prompt);
		Boolean b = null;
		do {
			String s = Input.getInput();
			b = Input.translateToBool(s);
		} while (b == null);
		return b;
	}
	
	//Requests input from user, doesn't stop until it gets it
	public static Integer promptForInt(String prompt)
	{
		System.out.println(prompt);
		Integer i = null;
		do {
			try{
				i = Integer.parseInt(Input.getInput());
			} catch (NumberFormatException e)
			{
				i = null; //keep trying
			}
		} while (i == null);
		return i;
	}
	
	private static String getInput()
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try
        {	
        	s =br.readLine();
        }
        catch (IOException e)
        {
        	System.out.println(e.getMessage());
        	s = null;
        }
        
        return s; 
	}
	
	//"TRUE" and "FALSE" strings
	static final String[] TRUE = {"true", "yes"};
	static final String[] FALSE = {"false", "no"};
	
	
	private static Boolean translateToBool(String in)
	{
		if (in == null)
			return null;
		
		in = in.toLowerCase();

		for (String s : TRUE)
		{
			if (s.equals(in))
				return true;
		}
		
		for (String s : FALSE)
		{
			if (s.equals(in))
				return false;
		}

		return null;
	}
	
}
