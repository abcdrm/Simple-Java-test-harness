package com.TDD.SQA;

import java.util.ArrayList;

public class RTTestCase extends TestCase {
	
	public RTTestCase(ArrayList<ArrayList<Float>> a, Command cmd) {
		cases = a;
		command = cmd;
	}
	
	public void selectNext() {
		current = popCase(cases, 0);
	}
	
	public ArrayList<ArrayList<Float>> getCases() {
		return cases;
	}
}
