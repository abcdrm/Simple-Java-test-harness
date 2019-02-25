package com.TDD.SQA;

import java.util.ArrayList;
import java.util.Random;

public class RandomGenerater {
	private int seed;
	private float lowf, highf;
	private int count;
	
	public void setParaf(int s, float l, float h, int c) {
		seed = s;
		lowf = l;
		highf = h;
		count = c;
	}
	/* Generate random numbers and 
	 * add them into array list.
	 */
	public ArrayList<Float> generateRandomf() {
		ArrayList<Float> result = new ArrayList<Float>();
		Random rand = new Random(seed);
		for (int i = 0; i < count; i++) {
			result.add(rand.nextFloat() * (highf - lowf) + lowf);
		}
		return result;
	}
}
