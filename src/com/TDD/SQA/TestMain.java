package com.TDD.SQA;
import java.util.ArrayList;

public class TestMain {
	public static void main(String[] args) throws NumberFormatException, Exception {
		Command cmd = new Command(args);
		if (cmd.checkCommand()) {
			// generate test cases
			CommandProcessor tc =  new CommandProcessor();
			ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
			// run test
			if (cmd.getMode().compareTo("DART") == 0) {
				DARTTestCase d = new DARTTestCase(a, Integer.parseInt(cmd.getCandidateSize()), cmd);
				while (!d.isEnd()) {
					d.selectNext();
					d.moveToExecuted();
					d.runTest();
				}
			}
			if (cmd.getMode().compareTo("RT") == 0) {
				RTTestCase d = new RTTestCase(a, cmd);
				while (!d.isEnd()) {
					d.selectNext();
					d.runTest();
				}
			}
		} else {
			System.out.println("Command error.");
		}
	}
}
