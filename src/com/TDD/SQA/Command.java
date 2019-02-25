package com.TDD.SQA;
import java.util.regex.Pattern;

public class Command {
	private String[] command;
	private String[] bounds;
	private String mode;

	public Command(String[] cmd) {
		this.command = cmd;
		this.mode = cmd[1];
		this.bounds = this.getBounds();
	}
	
	public String[] getCommand() {
		return command;
	}
	
	public String getMode() {
		return command[1];
	}
	
	public String getProgram() {
		if (mode.compareTo("RT") == 0)
			return command[3];
		else
			return command[5];
	}

	public String getOracle() {
		if (mode.compareTo("RT") == 0)
			return command[5];
		else
			return command[7];
	}

	public String getSeed() {
		if (mode.compareTo("RT") == 0)
			return command[7];
		else
			return command[9];
	}

	public String getNumOfCases() {
		if (mode.compareTo("RT") == 0)
			return command[9];
		else
			return command[11];
	}

	public String getNumOfArg() {
		if (mode.compareTo("RT") == 0)
			return command[11];
		else
			return command[13];
	}
	/* This method reads the last 2*argNum of elements
	 * to get each arg's bound
	 */
	public String[] getBounds() {
		int len, temp;
		if (mode.compareTo("RT") == 0) {
			len = command.length - 13;
			temp = 13;
		} else {
			len = command.length - 15;
			temp = 15;
		}
		bounds = new String[len];
		for (int i = 0; i < bounds.length; i++) {
			bounds[i] = command[temp];
			temp++;
		}
		return bounds;
	}
	
	public String getCandidateSize() throws Exception {
		if (mode.compareTo("DART") == 0) 
			return command[3];
		else 
			throw new Exception();
	}
	/* This method checks the command by RegExp
	 * and also check whether the bounds number
	 * is correct.
	 */
	public boolean checkCommand() throws Exception {
		if (bounds.length % 2 != 0)
			return false;
		String cmd = "";
		for (int i = 0; i < command.length; i++) {
			if (mode.compareTo("RT") == 0 && (i == 3 || i ==5)) {
				cmd = cmd + "\"" + command[i] + "\"" + " ";
			} else if (mode.compareTo("DART") == 0 && (i == 5 || i == 7)) {
				cmd = cmd + "\"" + command[i] + "\"" + " ";
			} else {
				if (i != command.length - 1)
					cmd = cmd + command[i] + " ";
				else
					cmd = cmd + command[i];
			}
		}
		String pattern1 = "(-m\\s+RT\\s+-p\\s+\".+?\"\\s+-o\\s+\".+?\"\\s+-s\\s+\\d+\\s+-n\\s+\\d+\\s+-a\\s+\\d+\\s+-r(\\s+-*\\d+\\.\\d+)+|-m\\s+RT\\s+-p\\s+\".+?\"\\s+-o\\s+\".+?\"\\s+-s\\s+\\d+\\s+-n\\s+\\d+\\s+-a\\s+\\d+\\s+-r(\\s+-*\\d+)+)";
		String pattern2 = "(-m\\s+DART\\s+-k\\s+\\d+\\s+-p\\s+\".+?\"\\s+-o\\s+\".+?\"\\s+-s\\s+\\d+\\s+-n\\s+\\d+\\s+-a\\s+\\d+\\s+-r(\\s+-*\\d+\\.\\d+)+|-m\\s+DART\\s+-k\\s+\\d+\\s+-p\\s+\".+?\"\\s+-o\\s+\".+?\"\\s+-s\\s+\\d+\\s+-n\\s+\\d+\\s+-a\\s+\\d+\\s+-r(\\s+-*\\d+)+)";
		boolean temp = (Pattern.matches(pattern1, cmd) || Pattern.matches(pattern2, cmd));
		if (Integer.parseInt(getNumOfArg())*2 != getBounds().length) 
			temp = false;
		else {
			for (int i = 0; i < Integer.parseInt(getNumOfArg()); i++) {
				if (Float.parseFloat(bounds[2 * i]) > Float.parseFloat(bounds[2 * i + 1])) {
					temp = false;
					System.out.println("Upper bound should be larger or equal than lower bound.");
					break;
				}
			}
		}
		return temp;		
	}
}
