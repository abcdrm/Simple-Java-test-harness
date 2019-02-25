package com.TDD.SQA;

import java.util.ArrayList;

public class DARTTestCase extends TestCase {
	private ArrayList<ArrayList<Float>> executed;
	private ArrayList<ArrayList<Float>> candidate;
	int candidateSize;
	/* Init the cases, executed and candidate
	 * array list to empty, according to the
	 * size of input test cases.
	 * For example, if put program have 4 args,
	 * then the size of three array lists will
	 * be init to size of 4, and each elements of
	 * them is an empty array list of float.
	 */
	public DARTTestCase(ArrayList<ArrayList<Float>> a, int size, Command c) {
		executed = new ArrayList<ArrayList<Float>>();
		candidate = new ArrayList<ArrayList<Float>>();
		for (int i = 0; i < a.size(); i++) {
			ArrayList<Float> tmp = new ArrayList<Float>();
			ArrayList<Float> tmp1 = new ArrayList<Float>();
			executed.add(tmp);
			candidate.add(tmp1);
		}
		cases = a;
		candidateSize = size;
		current = new float[a.size()];
		command = c;
	}
	/* move the current test case to executed list. 
	 */
	public void moveToExecuted() {
		addCase(executed, current);
	}
	/* evenly spread the test case through maximising
	 * the minimum distance between the next test case 
	 * and the already executed test cases.
	 */
	public void selectNext() {
		float bestDistance = -1.0f;
		RandomGenerater r = new RandomGenerater();
		for (int i = 0; i < candidateSize; i++) {
			if (cases.get(0).size() != 0) {
				// random choose a test case from total cases
				r.setParaf(Math.abs((int) System.currentTimeMillis()), 0.0f, cases.get(0).size() - 1, 1);
				int tmp = (int) r.generateRandomf().get(0).floatValue();
				float[] c = popCase(cases, tmp);
				// put it into candidate
				addCase(candidate, c);
				// compute and choose the minimum distance to
				// each cases in executed list
				float minCandidateDistance = Float.MAX_VALUE;
				for (int j = 0; j < executed.get(0).size(); j++) {
					minCandidateDistance = min(minCandidateDistance, getDistance(getCase(executed, j), c));
				}
				// maximising the minimum distance
				if (bestDistance < minCandidateDistance) {
					current = c;
					bestDistance = minCandidateDistance;
				}
			}
		}
	}
	/* compare two float and return the 
	 * small one.
	 */
	private float min(float a, float b) {
		if (a > b)
			return b;
		else
			return a;
	}
	/* add a test case to the tail of 
	 * given list 
	 */
	private void addCase(ArrayList<ArrayList<Float>> m, float[] c) {
		for (int i = 0; i < m.size(); i++) {
			m.get(i).add(c[i]);
		}
	}
	/* compute the distance between two
	 * given cases.
	 */
	private float getDistance(float[] a, float[] b) {
		float distance = 0;
		for (int i = 0; i < a.length; i++) {
			distance += (a[i] - b[i]) * (a[i] - b[i]);
		}
		return (float) Math.sqrt(distance);
	}

	public ArrayList<ArrayList<Float>> getCandidate() {
		return candidate;
	}

	public ArrayList<ArrayList<Float>> getExecuted() {
		return executed;
	}
}
