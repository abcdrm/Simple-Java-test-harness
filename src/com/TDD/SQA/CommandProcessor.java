package com.TDD.SQA;

import java.util.ArrayList;
public class CommandProcessor {
	/* processing the command, each arg has an
	 * array list to store the random input for
	 * it, and them add all of array list to 
	 * another array list and return.
	 */
	public ArrayList<ArrayList<Float>> processCommand(Command cmd) throws NumberFormatException, Exception {
		ArrayList<ArrayList<Float>> testCase = new ArrayList<ArrayList<Float>>();
		RandomGenerater r = new RandomGenerater();
		int a = Integer.parseInt(cmd.getNumOfArg());
		int s = Integer.parseInt(cmd.getSeed());
		int b;
		
		if (cmd.getMode().compareTo("DART") == 0)
			b = Integer.parseInt(cmd.getNumOfCases()) * Integer.parseInt(cmd.getCandidateSize());
		else
			b = Integer.parseInt(cmd.getNumOfCases());
		
		String[] c = cmd.getBounds();
		for (int i = 0; i < a; i++) {
			float low = Float.parseFloat(c[2 * i]);
			float high = Float.parseFloat(c[2 * i + 1]);
			r.setParaf(s, low, high, b);
			ArrayList<Float> tmp = r.generateRandomf();
			testCase.add(tmp);
		}
		return testCase;
	}
}
