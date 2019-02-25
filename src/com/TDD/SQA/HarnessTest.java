package com.TDD.SQA;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class HarnessTest {

	@Test
	void test1() {
		String[] cmdLine = {"-m", "RT", "-p", "PUT", "-o", "Oracle", "-s", "7", "-n", "10000", "-a", "2", "-r", "0.0", "1.0", "-1.0", "0.0"};
		Command cmd = new Command(cmdLine);
	}
	
	@Test
	void test2() {
		String[] cmdLine = {"-m", "DART", "-k", "10", "-p", "PUT", "-o", "Oracle", "-s", "7", "-n", "10000", "-a", "2", "-r", "0.0", "1.0", "-1.0", "0.0"};
		Command cmd = new Command(cmdLine);
	}
	
	@Test
	void test3() {
		String[] cmdLine = {"-m", "RT", "-p", "PUT", "-o", "Oracle", "-s", "7", "-n", "10000", "-a", "2", "-r", "0.0", "1.0", "-1.0", "0.0"};
		Command cmd = new Command(cmdLine);
		assertEquals("-p", cmd.getCommand()[2]);
		assertEquals("PUT", cmd.getCommand()[3]);
	}
	
	@Test
	void test4() {
		String[] cmdLine = {"-m", "RT", "-p", "PUT", "-o", "Oracle", "-s", "7", "-n", "10000", "-a", "2", "-r", "0.0", "1.0", "-1.0", "0.0"};
		Command cmd = new Command(cmdLine);
		assertEquals("PUT", cmd.getProgram());
		assertEquals("Oracle", cmd.getOracle());
		assertEquals("7", cmd.getSeed());
		assertEquals("10000", cmd.getNumOfCases());
		assertEquals("2", cmd.getNumOfArg());
		assertEquals(17, cmd.getCommand().length);
	}
	
	@Test
	void test5() {
		String[] cmdLine = {"-m", "RT", "-p", "PUT", "-o", "Oracle", "-s", "7", "-n", "10000", "-a", "2", "-r", "0.0", "1.0", "-1.0", "0.0"};
		Command cmd = new Command(cmdLine);
		assertEquals("0.0", cmd.getBounds()[0]);
		assertEquals("1.0", cmd.getBounds()[1]);
		assertEquals("-1.0", cmd.getBounds()[2]);
		assertEquals("0.0", cmd.getBounds()[3]);
		assertEquals(4, cmd.getBounds().length);
	}
	
	@Test
	void test6() throws Exception {
		String[] cmdLine = {"-m", "RT", "-p", "PUT", "-o", "Oracle", "-s", "7", "-n", "10000", "-a", "2", "-r", "0.0", "1.0", "-1.0"};
		String[] cmdLine2 = {"-m", "DART", "-k", "10", "-p", "./a.out", "-o", "./b.out", "-s", "7", "-n", "10000", "-a", "2", "-r", "0.0", "1.0", "-1.0", "0.0"};
		Command cmd = new Command(cmdLine);
		Command cmd2 = new Command(cmdLine2);
		boolean a = cmd.checkCommand();
		boolean b = cmd2.checkCommand();
		assertEquals("10", cmd2.getCandidateSize());
		assertEquals(false, a);
		assertEquals(true, b);
	}
	
	@Test
	void test7() {
		RandomGenerater randomGenerater = new RandomGenerater();
	}
	
//	@Test
//	void test8() {
//		RandomGenerater randomGenerater = new RandomGenerater();
//		randomGenerater.setPara(1, 1, 100000, 9999999);
//	}
//	
//	@Test
//	void test9() {
//		RandomGenerater randomGenerater = new RandomGenerater();
//		randomGenerater.setPara(1, 1, 100000, 9999999);
//		int[] a = randomGenerater.generateRandom();
//	}
//	
//	@Test
//	void test10() {
//		RandomGenerater randomGenerater = new RandomGenerater();
//		randomGenerater.setPara(1, 1, 100000, 9999999);
//		int[] a = randomGenerater.generateRandom();
//		assertEquals(9999999, a.length);
//	}
	
	@Test
	void test11() {
		RandomGenerater randomGenerater = new RandomGenerater();
		randomGenerater.setParaf(1, 1.5f, 100000.5f, 9999999);
	}
	
	@Test
	void test12() {
		RandomGenerater randomGenerater = new RandomGenerater();
		randomGenerater.setParaf(1, 1.5f, 100000.5f, 9999999);
		ArrayList<Float> a = randomGenerater.generateRandomf();
	}
	
	@Test
	void test13() {
		RandomGenerater randomGenerater = new RandomGenerater();
		randomGenerater.setParaf(1, 1.5f, 100000.5f, 9999999);
		ArrayList<Float> a = randomGenerater.generateRandomf();
		assertEquals(9999999, a.size());
	}
	
	@Test
	void test14() {
		RandomGenerater randomGenerater = new RandomGenerater();
		randomGenerater.setParaf(1, 1.5f, 100000.5f, 9999999);
		ArrayList<Float> a = randomGenerater.generateRandomf();
		float min = a.get(0);
		float max = a.get(0);
		for (int i = 0; i < a.size(); i++) {
			if (min > a.get(i)) 
				min = a.get(i);
			if (max < a.get(i))
				max = a.get(i);
		}
		if (min < 1.5f)
			fail("low bound fail");
		if (max > 100000.5f)
			fail("high bound fail");
	}
	
//	@Test
//	void test15() {
//		RandomGenerater randomGenerater = new RandomGenerater();
//		randomGenerater.setPara(1, 1, 100000, 9999999);
//		int[] a = randomGenerater.generateRandom();
//		int min = a[0];
//		int max = a[0];
//		for (int i = 0; i < a.length; i++) {
//			if (min > a[i]) 
//				min = a[i];
//			if (max < a[i])
//				max = a[i];
//		}
//		if (min < 1)
//			fail("low bound fail");
//		if (max > 100000)
//			fail("high bound fail");
//	}
	
	@Test
	void test16() {
		CommandProcessor tc =  new CommandProcessor();
	}
	
	@Test
	void test17() throws NumberFormatException, Exception {
		String[] cmdLine = {"-m", "RT", "-p", "PUT", "-o", "Oracle", "-s", "7", "-n", "10000", "-a", "2", "-r", "0.0", "1.0", "-1.0", "0.0"};
		Command cmd = new Command(cmdLine);
		CommandProcessor tc =  new CommandProcessor();
		tc.processCommand(cmd);
	}
	
	@Test
	void test18() throws NumberFormatException, Exception {
		String[] cmdLine = {"-m", "RT", "-p", "PUT", "-o", "Oracle", "-s", "7", "-n", "10000", "-a", "2", "-r", "0.0", "1.0", "-1.0", "0.0"};
		Command cmd = new Command(cmdLine);
		CommandProcessor tc =  new CommandProcessor();
		assertEquals(2, tc.processCommand(cmd).size());
	}
	
	@Test
	void test19() throws NumberFormatException, Exception {
		String[] cmdLine = {"-m", "RT", "-p", "PUT", "-o", "Oracle", "-s", "7", "-n", "10000", "-a", "2", "-r", "0.0", "1.0", "-1.0", "0.0"};
		Command cmd = new Command(cmdLine);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		assertEquals(10000, a.get(0).size());
	}
	
	@Test
	void test20() throws NumberFormatException, Exception {
		String[] cmdLine = {"-m", "RT", "-p", "PUT", "-o", "Oracle", "-s", "7", "-n", "10000", "-a", "2", "-r", "0.0", "1.0", "-1.0", "0.0"};
		Command cmd = new Command(cmdLine);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		DARTTestCase d = new DARTTestCase(a, 10, cmd);
		d.moveToExecuted();
	}
	
	@Test
	void test21() throws NumberFormatException, Exception {
		String[] cmdLine = {"-m", "DART", "-k", "10", "-p", "cmd /c java -jar fib.jar", "-o", "./fib", "-s", "7", "-n", "10", "-a", "1", "-r", "2.0", "10000.0"};
		Command cmd = new Command(cmdLine);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		DARTTestCase d = new DARTTestCase(a, 10, cmd);
		d.selectNext();
		d.runTest();
		d.moveToExecuted();
		assertEquals(1, d.getExecuted().get(0).size());
	}
	
	@Test
	void test22() throws NumberFormatException, Exception {
		String[] cmdLine = {"-m", "DART", "-k", "10", "-p", "cmd /c java -jar fib.jar", "-o", "./fib", "-s", "7", "-n", "10", "-a", "1", "-r", "2.0", "10000.0"};
		Command cmd = new Command(cmdLine);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		DARTTestCase d = new DARTTestCase(a, 10, cmd);
		while (!d.isEnd()) {
			d.selectNext();
			d.runTest();
			d.moveToExecuted();
		}
	}
	
	@Test
	void test23() throws NumberFormatException, Exception {
		String[] cmdLine = {"-m", "DART", "-k", "10", "-p", "cmd /c java -jar fib.jar", "-o", "./fib", "-s", "7", "-n", "10", "-a", "1", "-r", "2.0", "10000.0"};
		Command cmd = new Command(cmdLine);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		DARTTestCase d = new DARTTestCase(a, 10, cmd);
		while (!d.isEnd()) {
			d.selectNext();
			d.runTest();
			d.moveToExecuted();
		}
		assertEquals(10, d.getExecuted().get(0).size());
		assertEquals(0, d.getCases().get(0).size());
	}
	
	@Test
	void test24() throws NumberFormatException, Exception {
		String[] cmdLine = {"-m", "DART", "-k", "10", "-p", "cmd /c java -jar fib.jar", "-o", "./fib", "-s", "7", "-n", "10", "-a", "1", "-r", "2.0", "10000.0"};
		Command cmd = new Command(cmdLine);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		RTTestCase rt = new RTTestCase(a, cmd);
	}
	
	@Test
	void test25() throws NumberFormatException, Exception {
		String[] cmdLine = {"-m", "DART", "-k", "10", "-p", "java -jar fib.jar", "-o", "./fib", "-s", "7", "-n", "10", "-a", "1", "-r", "2.0", "10000.0"};
		Command cmd = new Command(cmdLine);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		RTTestCase rt = new RTTestCase(a, cmd);
		rt.selectNext();
		rt.runTest();
	}
	
	@Test
	void test26() throws NumberFormatException, Exception {
		String[] cmdLine = {"-m", "RT", "-p", "cmd /c java -jar fib.jar", "-o", "./fib", "-s", "7", "-n", "10", "-a", "1", "-r", "2.0", "10000.0"};
		Command cmd = new Command(cmdLine);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		RTTestCase rt = new RTTestCase(a, cmd);
		rt.selectNext();
		rt.runTest();
		assertEquals(9, rt.getCases().get(0).size());
	}
	
	@Test
	void test27() throws NumberFormatException, Exception {
		String[] cmdLine = {"-m", "RT", "-p", "cmd /c java -jar fib.jar", "-o", "./fib", "-s", "7", "-n", "10", "-a", "1", "-r", "2.0", "10000.0"};
		Command cmd = new Command(cmdLine);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		RTTestCase rt = new RTTestCase(a, cmd);
		while (!rt.isEnd()) {
			rt.selectNext();
			rt.runTest();
		}
		assertEquals(0, rt.getCases().get(0).size());
	}
	
	@Test
	void test28() {
		TestRunner tr = new TestRunner();
	}
	
	@Test
	void test29() {
		TestRunner tr = new TestRunner();
		tr.setOracleCommand("./fib");
		tr.setProgramCommand("cmd /c java -jar fib.jar");
		float[] arg = {1000.0f};
		tr.generateCommand(arg);
		assertEquals("cmd /c java -jar fib.jar 1000", tr.getProgramCommand());
		assertEquals("./fib 1000", tr.getOracleCommand());
	}
	
	@Test
	void test30() throws IOException, InterruptedException {
		TestRunner tr = new TestRunner();
		tr.setOracleCommand("./fib");
		tr.setProgramCommand("cmd /c java -jar fib.jar");
		float[] arg = {1000.0f};
		tr.generateCommand(arg);
		tr.run();
	}
	
	@Test
	void test31() throws IOException, InterruptedException {
		TestRunner tr = new TestRunner();
		tr.setOracleCommand("./fib");
		tr.setProgramCommand("cmd /c java -jar fib.jar");
		float[] arg = {1000.0f};
		tr.generateCommand(arg);
		assertEquals(true, tr.run());
	}
	
	@Test
	void test32() throws IOException, InterruptedException {
		TestRunner tr = new TestRunner();
		tr.setOracleCommand("./fib");
		tr.setProgramCommand("cmd /c java -jar fib.jar");
		float[] arg = {100.0f};
		tr.generateCommand(arg);
		final Process process1 = Runtime.getRuntime().exec(tr.getProgramCommand());
		String programOutput = tr.printMessage(process1.getInputStream());
        process1.waitFor();
        assertEquals("354224848179261915075", programOutput);
	}
	
	@Test
	void test33() throws IOException, InterruptedException {
		TestRunner tr = new TestRunner();
		tr.setProgramCommand("./fib");
		tr.setOracleCommand("cmd /c java -jar fib.jar");
		float[] arg = {100.0f};
		tr.generateCommand(arg);
		final Process process1 = Runtime.getRuntime().exec(tr.getProgramCommand());
		String programOutput = tr.printMessage(process1.getInputStream());
        process1.waitFor();
        assertEquals("354224848179261915075", programOutput);
	}
	
	@Test
	void test34() throws NumberFormatException, Exception {
		String[] args = {"-m", "DART", "-k", "10", "-p", "java -jar fib.jar", "-o", "./fib", "-s", "7", "-n", "10", "-a", "1", "-r", "2.0", "10000.0"};
		Command cmd = new Command(args);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
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
	}
	
	@Test
	void test35() throws NumberFormatException, Exception {
		String[] args = {"-m", "RT", "-p", "java -jar fib.jar", "-o", "./fib", "-s", "7", "-n", "10", "-a", "1", "-r", "2.0", "10000.0"};
		Command cmd = new Command(args);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
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
	}
	
	@Test
	void test36() throws NumberFormatException, Exception {
		String[] args = {"-m", "RT", "-p", "./fib", "-o", "java -jar fib.jar", "-s", "7", "-n", "10", "-a", "1", "-r", "2.0", "10000.0"};
		Command cmd = new Command(args);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
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
	}
	
	@Test
	void test37() throws NumberFormatException, Exception {
		String[] args = {"-m", "DART", "-k", "20", "-p", "java -jar fib.jar", "-o", "./fib", "-s", "423", "-n", "7", "-a", "1", "-r", "2.0", "200.0"};
		Command cmd = new Command(args);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		DARTTestCase d = new DARTTestCase(a, Integer.parseInt(cmd.getCandidateSize()), cmd);
		while (!d.isEnd()) {
			d.selectNext();
			d.moveToExecuted();
			d.runTest();
		}
	}
	
	@Test
	void test38() throws NumberFormatException, Exception {
		String[] args = {"-m", "RT", "-p", "java -jar fib.jar", "-o", "./fib", "-s", "423", "-n", "7", "-a", "1", "-r", "2.0", "200.0"};
		Command cmd = new Command(args);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		RTTestCase d = new RTTestCase(a, cmd);
		while (!d.isEnd()) {
			d.selectNext();
			d.runTest();
		}
	}
	
	@Test
	void test39() throws NumberFormatException, Exception {
		String[] args = {"-m", "RT", "-p", "java -jar fib.jar", "-o", "./fib", "-s", "423", "-n", "70", "-a", "1", "-r", "2.0", "200.0"};
		Command cmd = new Command(args);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		RTTestCase d = new RTTestCase(a, cmd);
		while (!d.isEnd()) {
			d.selectNext();
			d.runTest();
		}
	}
	
	@Test
	void test40() throws NumberFormatException, Exception {
		String[] args = {"-m", "DART", "-k", "50", "-p", "java -jar fib.jar", "-o", "./fib", "-s", "423", "-n", "70", "-a", "1", "-r", "2.0", "200.0"};
		Command cmd = new Command(args);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		DARTTestCase d = new DARTTestCase(a, Integer.parseInt(cmd.getCandidateSize()), cmd);
		while (!d.isEnd()) {
			d.selectNext();
			d.moveToExecuted();
			d.runTest();
		}
	}
	
	@Test
	void test41() throws NumberFormatException, Exception {
		String[] args = {"-m", "DART", "-k", "10", "-p", "java -jar fib.jar", "-o", "./fib", "-s", "423", "-n", "70", "-a", "1", "-r", "2.0", "200.0"};
		Command cmd = new Command(args);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		DARTTestCase d = new DARTTestCase(a, Integer.parseInt(cmd.getCandidateSize()), cmd);
		while (!d.isEnd()) {
			d.selectNext();
			d.moveToExecuted();
			d.runTest();
		}
	}
	
	@Test
	void test42() throws NumberFormatException, Exception {
		String[] args = {"-m", "DART", "-k", "10", "-p", "java -jar fib.jar", "-o", "./fib", "-s", "424", "-n", "70", "-a", "1", "-r", "2.0", "200.0"};
		Command cmd = new Command(args);
		CommandProcessor tc =  new CommandProcessor();
		ArrayList<ArrayList<Float>> a = tc.processCommand(cmd);
		DARTTestCase d = new DARTTestCase(a, Integer.parseInt(cmd.getCandidateSize()), cmd);
		while (!d.isEnd()) {
			d.selectNext();
			d.moveToExecuted();
			d.runTest();
		}
	}
}
