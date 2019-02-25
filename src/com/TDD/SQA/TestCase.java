package com.TDD.SQA;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public abstract class TestCase implements TestInterface {
	protected ArrayList<ArrayList<Float>> cases;
	protected float[] current;
	protected Command command;
	/* check whether test cases is 
	 * running out.
	 */
	public boolean isEnd() {
		if (cases.get(0).size() == 0)
			return true;
		return false;
	}
	/* pop a test case of given position
	 * of a given list
	 */
	protected float[] popCase(ArrayList<ArrayList<Float>> m, int pos) {
		float[] tmp = getCase(m, pos);
		for (int i = 0; i < m.size(); i++) {
			m.get(i).remove(pos);
		}
		return tmp;
	}
	/* get a test case of given position
	 * of a given list
	 */
	protected float[] getCase(ArrayList<ArrayList<Float>> m, int pos) {
		float[] tmp = new float[m.size()];
		for (int i = 0; i < m.size(); i++) {
			tmp[i] = m.get(i).get(pos);
		}
		return tmp;
	}
	
	/* pass the current test case to put and oracle, 
	 * then run two program, and print the both 
	 * programs' output, intput, as well as whether 
	 * the test is passed.
	 */
	public void runTest() throws IOException, InterruptedException {
		TestRunner tr = new TestRunner();
		tr.setProgramCommand(command.getProgram());
		tr.setOracleCommand(command.getOracle());
		// use integer as input
		tr.useInt(false);
		tr.generateCommand(current);
		if (!tr.run()) {
			for (int i = 0; i < current.length; i++) {
				System.out.println("Random input: " + current[i]);
			}
			System.out.println("Failed!");
			System.out.println("---------------------------------");
			// append failing cases to TestResults.dat
			try(FileWriter fw = new FileWriter("TestResults.dat", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
			{
				for (int i = 0; i < current.length; i++) {
					out.println("Random input: " + current[i]);
				}
				out.println("Failed!");
				out.println("---------------------------------");
			} catch (IOException e) {
				System.out.println("Fail on writing file.");
			}
		} else {
			for (int i = 0; i < current.length; i++) {
				System.out.println("Random input: " + current[i]);
			}
			System.out.println("Succeed!");
			System.out.println("---------------------------------");
		}
	}

	public ArrayList<ArrayList<Float>> getCases() {
		return cases;
	}
}
