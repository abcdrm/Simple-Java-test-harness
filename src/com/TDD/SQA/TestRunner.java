package com.TDD.SQA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

public class TestRunner {
	private String programCommand;
	private String oracleCommand;
	private String programOutput;
	private String oracleOutput;
	private boolean useInt = true;
	/* add parameters to both put and oracle
	 * command line.
	 */
	public void generateCommand(float[] args) {
		if (useInt) {
			for (int i = 0; i < args.length; i++) {
				Integer tmp = (int) args[i];
				programCommand = programCommand + " " + tmp.toString();
			}
		} else {
			for (int i = 0; i < args.length; i++) {
				Float tmp = args[i];
				programCommand = programCommand + " " + tmp.toString();
			}
		}
		
		if (useInt) {
			for (int i = 0; i < args.length; i++) {
				Integer tmp = (int) args[i];
				oracleCommand = oracleCommand + " " + tmp.toString();
			}
		} else {
			for (int i = 0; i < args.length; i++) {
				Float tmp = args[i];
				oracleCommand = oracleCommand + " " + tmp.toString();
			}
		}
	}
	/* run put and oracle use same input, and compare the
	 * output, true for same, false for different.
	 */
	public boolean run() throws IOException, InterruptedException {
		final Process process1 = Runtime.getRuntime().exec(programCommand);
        programOutput = printMessage(process1.getInputStream());
        //printMessage(process.getErrorStream());
        process1.waitFor();
        
        final Process process2 = Runtime.getRuntime().exec(oracleCommand);
        oracleOutput = printMessage(process2.getInputStream());
        //printMessage(process.getErrorStream());
        process2.waitFor();
        
        System.out.println("Program output: " + programOutput);
        System.out.println("Oracle output: " + oracleOutput);
        
        if (programOutput.compareTo(oracleOutput) != 0) {
        	try(FileWriter fw = new FileWriter("TestResults.dat", true);
    			BufferedWriter bw = new BufferedWriter(fw);
    			PrintWriter out = new PrintWriter(bw))
    		{
        		out.println("Program output: " + programOutput);
        		out.println("Oracle output: " + oracleOutput);
    		} catch (IOException e) {
    			System.out.println("Fail on writing file.");
    		}
        	return false;
        }
        return true;
	}
	/* store the program output in a string.
	 */
    protected String printMessage(final InputStream input) {
    	String output = "";
    	Reader reader = new InputStreamReader(input);
    	BufferedReader bf = new BufferedReader(reader);
    	String line = null;
    	try {
    		while((line=bf.readLine())!=null) {
    			output += line;
    			//System.out.println(line);
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return output;
    }
	
	public void setProgramCommand(String programCommand) {
		this.programCommand = programCommand;
	}
	public String getProgramCommand() {
		return programCommand;
	}

	public String getOracleCommand() {
		return oracleCommand;
	}

	public void setOracleCommand(String oracleCommand) {
		this.oracleCommand = oracleCommand;
	}
	public void useInt(boolean useInt) {
		this.useInt = useInt;
	}
}
