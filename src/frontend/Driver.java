package frontend;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Driver {
	/**
	 * Returns a hashtable that has a value for the input and flop flop column.
	 * 
	 * @param noOfInput
	 * @param noOfFF
	 * @return the table filled with binary
	 */
	private ArrayList<Hashtable<String, String>> fillBinary(int noOfInput, int noOfFF) {
		System.out.println("no Of Input: " + noOfInput);
		System.out.println("no Of Flip-Flop: " + noOfFF);
		ArrayList<Hashtable<String, String>> table = new ArrayList<Hashtable<String, String>>();
		int size = noOfInput + noOfFF;
		int rows = (int) Math.pow(2, size);
		String[] binary = new String[rows];
		/**/
		for (int i = 0; i < rows; i++) {
			binary[i] = Integer.toBinaryString(i);
		}
		/**/

		System.out.println("rows :" + rows);
		System.out.println("size: " + size);
		for (int i = 0; i < rows; i++) {
			Hashtable<String, String> temp = new Hashtable<String, String>();
			if (size == 2) {
				temp.put("x", "" + binary[i].charAt(binary[i].length() - 1));
				if (binary[i].length() < 2) {
					temp.put("A", "" + 0);
				} else {
					temp.put("A", "" + binary[i].charAt(binary[i].length() - 2));
				}
			}
			if (noOfInput == 1 && noOfFF == 2) {
				temp.put("x", "" + binary[i].charAt(binary[i].length() - 1));
				if (binary[i].length() < 2) {
					temp.put("B", "0");
				} else {
					temp.put("B", "" + binary[i].charAt(binary[i].length() - 2));
				}
				if (binary[i].length() < 3) {
					temp.put("A", "0");
				} else {
					temp.put("A", "" + binary[i].charAt(binary[i].length() - 3));
				}
			}

			if (noOfInput == 1 && noOfFF == 3) {
				temp.put("x", "" + binary[i].charAt(binary[i].length() - 1));
				if (binary[i].length() < 2) {
					temp.put("C", "0");
				} else {
					temp.put("C", "" + binary[i].charAt(binary[i].length() - 2));
				}
				if (binary[i].length() < 3) {
					temp.put("B", "0");
				} else {
					temp.put("B", "" + binary[i].charAt(binary[i].length() - 3));
				}
				if (binary[i].length() < 4) {
					temp.put("A", "0");
				} else {
					temp.put("A", "" + binary[i].charAt(binary[i].length() - 4));
				}
			}

			if (noOfInput == 2 && noOfFF == 1) {
				temp.put("y", "" + binary[i].charAt(binary[i].length() - 1));
				if (binary[i].length() < 2) {
					temp.put("x", "0");
				} else {
					temp.put("x", "" + binary[i].charAt(binary[i].length() - 2));
				}
				if (binary[i].length() < 3) {
					temp.put("A", "0");
				} else {
					temp.put("A", "" + binary[i].charAt(binary[i].length() - 3));
				}
			}
			if (noOfInput == 2 && noOfFF == 2) {

				temp.put("y", "" + binary[i].charAt(binary[i].length() - 1));
				if (binary[i].length() < 2) {
					temp.put("x", "0");
				} else {
					temp.put("x", "" + binary[i].charAt(binary[i].length() - 2));
				}
				if (binary[i].length() < 3) {
					temp.put("B", "0");
				} else {
					temp.put("B", "" + binary[i].charAt(binary[i].length() - 3));
				}
				if (binary[i].length() < 4) {
					temp.put("A", "0");
				} else {
					temp.put("A", "" + binary[i].charAt(binary[i].length() - 4));
				}
			}
			if (noOfInput == 2 && noOfFF == 3) {
				temp.put("y", "" + binary[i].charAt(binary[i].length() - 1));
				if (binary[i].length() < 2) {
					temp.put("x", "0");
				} else {
					temp.put("x", "" + binary[i].charAt(binary[i].length() - 2));
				}
				if (binary[i].length() < 3) {
					temp.put("C", "0");
				} else {
					temp.put("C", "" + binary[i].charAt(binary[i].length() - 3));
				}
				if (binary[i].length() < 4) {
					temp.put("B", "0");
				} else {
					temp.put("B", "" + binary[i].charAt(binary[i].length() - 4));
				}
				if (binary[i].length() < 5) {
					temp.put("A", "0");
				} else {
					temp.put("A", "" + binary[i].charAt(binary[i].length() - 5));
				}
			}
			table.add(temp);
		}
		return table;
	}

	/**
	 * Evaluate the boolean function.
	 * 
	 * @param exp
	 * @return
	 * @throws NullPointerException
	 * @throws ScriptException
	 */
	private int getValue(String exp) throws NullPointerException, ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		Object result = "";

		result = engine.eval(exp);

		if (result instanceof Boolean) {
			if ((Boolean) result) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if ((int) result == 1 || (int) result == 0)
				return (int) result;
		}

		return -1;
	}

	/**
	 * Make DS State Table
	 * 
	 * @param noOfInput
	 * @param noOfOutput
	 * @param noOfFF
	 * @param output
	 * @param DA
	 * @param DB
	 * @param DC
	 * @throws NullPointerException
	 * @throws ScriptException
	 */
	public void makeDStateTable(int noOfInput, int noOfOutput, int noOfFF, String output, String DA, String DB,
			String DC) throws NullPointerException, ScriptException {

		ArrayList<Hashtable<String, String>> table = fillBinary(noOfInput, noOfFF);

		if (noOfInput == 1 && noOfFF == 1) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = DA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));

				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				// System.out.println(result);
				table.get(i).put("DA", result);
				table.get(i).put("nextA", result);
			}
		}

		if (noOfInput == 1 && noOfFF == 2) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = DA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				// System.out.println(result);
				table.get(i).put("DA", result);
				table.get(i).put("nextA", result);

				currentExp = DB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result = getValue(currentExp) + ""; // + "" to convert to string
				// System.out.println(result);
				table.get(i).put("DB", result);
				table.get(i).put("nextB", result);
			}
		}

		if (noOfInput == 1 && noOfFF == 3) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = DA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("DA", result);
				table.get(i).put("nextA", result);

				currentExp = DB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result = getValue(currentExp) + ""; // + "" to convert to string
				table.get(i).put("DB", result);
				table.get(i).put("nextB", result);

				currentExp = DC.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result = getValue(currentExp) + ""; // + "" to convert to string
				table.get(i).put("DC", result);
				table.get(i).put("nextC", result);
			}
		}

		if (noOfInput == 2 && noOfFF == 1) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = DA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("DA", result);
				table.get(i).put("nextA", result);
			}
		}

		if (noOfInput == 2 && noOfFF == 2) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = DA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("DA", result);
				table.get(i).put("nextA", result);

				currentExp = DB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result = getValue(currentExp) + ""; // + "" to convert to string
				table.get(i).put("DB", result);
				table.get(i).put("nextB", result);
			}
		}

		if (noOfInput == 2 && noOfFF == 3) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = DA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("DA", result);
				table.get(i).put("nextA", result);

				currentExp = DB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result = getValue(currentExp) + ""; // + "" to convert to string
				table.get(i).put("DB", result);
				table.get(i).put("nextB", result);

				currentExp = DC.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result = getValue(currentExp) + ""; // + "" to convert to string
				table.get(i).put("DC", result);
				table.get(i).put("nextC", result);
			}
		}

		if (noOfOutput == 1) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = output.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																							// para
																							// maconvert
																							// from
																							// string
																							// to
																							// char

				if (noOfFF == 2 || noOfFF == 3) {
					currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				}

				if (noOfFF == 3) {
					currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				}

				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));

				if (noOfInput == 2) {
					currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				}
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("Out", result);
			}
		}

		printTable(table);

	}

	/**
	 * Make TS State Table
	 * 
	 * @param noOfInput
	 * @param noOfOutput
	 * @param noOfFF
	 * @param output
	 * @param TA
	 * @param TB
	 * @param TC
	 * @throws NullPointerException
	 * @throws ScriptException
	 */
	public void makeTStateTable(int noOfInput, int noOfOutput, int noOfFF, String output, String TA, String TB,
			String TC) throws NullPointerException, ScriptException {

		ArrayList<Hashtable<String, String>> table = fillBinary(noOfInput, noOfFF);

		if (noOfInput == 1 && noOfFF == 1) {
			for (int i = 0; i < table.size(); i++) {

				String currentExp = TA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("TA", result);

				if (result.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else {
					table.get(i).put("nextA", complement(table.get(i).get("A")));
				}
			}
		}

		if (noOfInput == 1 && noOfFF == 2) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = TA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				// System.out.println(result);
				table.get(i).put("TA", result);

				if (result.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else {
					table.get(i).put("nextA", complement(table.get(i).get("A")));
				}

				currentExp = TB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result = getValue(currentExp) + ""; // + "" to convert to string
				// System.out.println(result);
				table.get(i).put("TB", result);

				if (result.equals("0")) {
					table.get(i).put("nextB", table.get(i).get("B"));
				} else {
					table.get(i).put("nextB", complement(table.get(i).get("B")));
				}
			}
		}

		if (noOfInput == 1 && noOfFF == 3) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = TA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("TA", result);

				if (result.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else {
					table.get(i).put("nextA", complement(table.get(i).get("A")));
				}

				currentExp = TB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result = getValue(currentExp) + ""; // + "" to convert to string
				table.get(i).put("TB", result);

				if (result.equals("0")) {
					table.get(i).put("nextB", table.get(i).get("B"));
				} else {
					table.get(i).put("nextB", complement(table.get(i).get("B")));
				}

				currentExp = TC.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result = getValue(currentExp) + ""; // + "" to convert to string
				table.get(i).put("TC", result);

				if (result.equals("0")) {
					table.get(i).put("nextC", table.get(i).get("C"));
				} else {
					table.get(i).put("nextC", complement(table.get(i).get("C")));
				}

			}
		}

		if (noOfInput == 2 && noOfFF == 1) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = TA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("TA", result);

				if (result.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else {
					table.get(i).put("nextA", complement(table.get(i).get("A")));
				}
			}
		}

		if (noOfInput == 2 && noOfFF == 2) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = TA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("TA", result);

				if (result.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else {
					table.get(i).put("nextA", complement(table.get(i).get("A")));
				}

				currentExp = TB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result = getValue(currentExp) + ""; // + "" to convert to string
				table.get(i).put("TB", result);

				if (result.equals("0")) {
					table.get(i).put("nextB", table.get(i).get("B"));
				} else {
					table.get(i).put("nextB", complement(table.get(i).get("B")));
				}
			}
		}

		if (noOfInput == 2 && noOfFF == 3) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = TA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("TA", result);

				if (result.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else {
					table.get(i).put("nextA", complement(table.get(i).get("A")));
				}

				currentExp = TB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result = getValue(currentExp) + ""; // + "" to convert to string
				table.get(i).put("TB", result);
				table.get(i).put("nextB", result);

				if (result.equals("0")) {
					table.get(i).put("nextB", table.get(i).get("B"));
				} else {
					table.get(i).put("nextB", complement(table.get(i).get("B")));
				}

				currentExp = TC.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result = getValue(currentExp) + ""; // + "" to convert to string
				table.get(i).put("TC", result);
				table.get(i).put("nextC", result);

				if (result.equals("0")) {
					table.get(i).put("nextC", table.get(i).get("C"));
				} else {
					table.get(i).put("nextC", complement(table.get(i).get("C")));
				}
			}
		}

		if (noOfOutput == 1) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = output.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																							// para
																							// maconvert
																							// from
																							// string
																							// to
																							// char

				if (noOfFF == 2 || noOfFF == 3) {
					currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				}

				if (noOfFF == 3) {
					currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				}

				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));

				if (noOfInput == 2) {
					currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				}
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("Out", result);
			}
		}

		printTable(table);
	}

	/**
	 * Make JK State Table
	 * 
	 * @param noOfInput
	 * @param noOfOutput
	 * @param noOfFF
	 * @param output
	 * @param JA
	 * @param JB
	 * @param JC
	 * @param KA
	 * @param KB
	 * @param KC
	 * @throws NullPointerException
	 * @throws ScriptException
	 */
	public void makeJKStateTable(int noOfInput, int noOfOutput, int noOfFF, String output, String JA, String JB,
			String JC, String KA, String KB, String KC) throws NullPointerException, ScriptException {

		ArrayList<Hashtable<String, String>> table = fillBinary(noOfInput, noOfFF);

		if (noOfInput == 1 && noOfFF == 1) {
			for (int i = 0; i < table.size(); i++) {

				String currentExp = JA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result1 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("JA", result1);

				currentExp = KA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
				// char
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result2 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("KA", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextA", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextA", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextA", complement(table.get(i).get("A")));
				}
			}
		}

		if (noOfInput == 1 && noOfFF == 2) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = JA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result1 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("JA", result1);

				currentExp = KA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result2 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("KA", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextA", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextA", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextA", complement(table.get(i).get("A")));
				}

				currentExp = JB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result1 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("JB", result1);

				currentExp = KB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result2 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("KB", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextB", table.get(i).get("B"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextB", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextB", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextB", complement(table.get(i).get("B")));
				}
			}
		}

		if (noOfInput == 1 && noOfFF == 3) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = JA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result1 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("JA", result1);

				currentExp = KA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result2 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("KA", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextA", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextA", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextA", complement(table.get(i).get("A")));
				}

				currentExp = JB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result1 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("JB", result1);

				currentExp = KB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result2 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("KB", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextB", table.get(i).get("B"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextB", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextB", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextB", complement(table.get(i).get("B")));
				}

				currentExp = JC.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result1 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("JC", result1);

				currentExp = KC.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result2 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("KC", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextC", table.get(i).get("C"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextC", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextC", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextC", complement(table.get(i).get("C")));
				}

			}
		}

		if (noOfInput == 2 && noOfFF == 1) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = JA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result1 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("JA", result1);

				currentExp = KA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result2 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("KA", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextA", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextA", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextA", complement(table.get(i).get("A")));
				}
			}
		}

		if (noOfInput == 2 && noOfFF == 2) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = JA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result1 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("JA", result1);

				currentExp = KA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result2 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("KA", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextA", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextA", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextA", complement(table.get(i).get("A")));
				}

				currentExp = JB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result1 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("JB", result1);

				currentExp = KB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result2 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("KB", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextB", table.get(i).get("B"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextB", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextB", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextB", complement(table.get(i).get("B")));
				}
			}
		}

		if (noOfInput == 2 && noOfFF == 3) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = JA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result1 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("JA", result1);

				currentExp = KA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result2 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("KA", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextA", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextA", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextA", complement(table.get(i).get("A")));
				}

				currentExp = JB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result1 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("JB", result1);

				currentExp = KB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result2 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("KB", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextB", table.get(i).get("B"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextB", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextB", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextB", complement(table.get(i).get("B")));
				}

				currentExp = JC.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result1 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("JC", result1);

				currentExp = KC.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result2 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("KC", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextC", table.get(i).get("C"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextC", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextC", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextC", complement(table.get(i).get("C")));
				}
			}
		}

		if (noOfOutput == 1) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = output.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																							// para
																							// maconvert
																							// from
																							// string
																							// to
																							// char

				if (noOfFF == 2 || noOfFF == 3) {
					currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				}

				if (noOfFF == 3) {
					currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				}

				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));

				if (noOfInput == 2) {
					currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				}
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("Out", result);
			}
		}

		printTable(table);
	}

	/**
	 * Make SR State Table
	 * 
	 * @param noOfInput
	 * @param noOfOutput
	 * @param noOfFF
	 * @param output
	 * @param RA
	 * @param RB
	 * @param RC
	 * @param SA
	 * @param SB
	 * @param SC
	 * @throws NullPointerException
	 * @throws ScriptException
	 */
	public void makeSRStateTable(int noOfInput, int noOfOutput, int noOfFF, String output, String RA, String RB,
			String RC, String SA, String SB, String SC) throws NullPointerException, ScriptException {
		ArrayList<Hashtable<String, String>> table = fillBinary(noOfInput, noOfFF);

		if (noOfInput == 1 && noOfFF == 1) {
			for (int i = 0; i < table.size(); i++) {

				String currentExp = RA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result1 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("RA", result1);

				currentExp = SA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result2 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("SA", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextA", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextA", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextA", "$");
				}
			}
		}

		if (noOfInput == 1 && noOfFF == 2) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = RA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result1 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("RA", result1);

				currentExp = SA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result2 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("SA", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextA", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextA", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextA", "$");
				}

				currentExp = RB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result1 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("RB", result1);

				currentExp = SB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result2 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("SB", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextB", table.get(i).get("B"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextB", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextB", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextB", "$");
				}
			}
		}

		if (noOfInput == 1 && noOfFF == 3) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = RA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result1 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("RA", result1);

				currentExp = SA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				String result2 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("SA", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextA", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextA", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextA", "$");
				}

				currentExp = RB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result1 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("RB", result1);

				currentExp = SB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result2 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("SB", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextB", table.get(i).get("B"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextB", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextB", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextB", "$");
				}

				currentExp = RC.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result1 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("RC", result1);

				currentExp = SC.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				result2 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("SC", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextC", table.get(i).get("C"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextC", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextC", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextC", "$");
				}

			}
		}

		if (noOfInput == 2 && noOfFF == 1) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = RA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result1 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("RA", result1);

				currentExp = SA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result2 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("SA", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextA", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextA", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextA", "$");
				}
			}
		}

		if (noOfInput == 2 && noOfFF == 2) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = RA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result1 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("RA", result1);

				currentExp = SA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result2 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("SA", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextA", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextA", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextA", "$");
				}

				currentExp = RB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result1 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("RB", result1);

				currentExp = SB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result2 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("SB", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextB", table.get(i).get("B"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextB", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextB", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextB", "$");
				}
			}
		}

		if (noOfInput == 2 && noOfFF == 3) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = RA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																						// para
																						// maconvert
																						// from
																						// string
																						// to
																						// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result1 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("RA", result1);

				currentExp = SA.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				String result2 = getValue(currentExp) + ""; // + "" to convert
															// to string
				table.get(i).put("SA", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextA", table.get(i).get("A"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextA", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextA", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextA", "$");
				}

				currentExp = RB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result1 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("RB", result1);

				currentExp = SB.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result2 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("SB", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextB", table.get(i).get("B"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextB", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextB", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextB", "$");
				}

				currentExp = RC.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result1 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("RC", result1);

				currentExp = SC.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																				// para
																				// maconvert
																				// from
																				// string
																				// to
																				// char
				currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));
				currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				result2 = getValue(currentExp) + ""; // + "" to convert to
														// string
				table.get(i).put("SC", result2);

				if (result1.equals("0") && result2.equals("0")) {
					table.get(i).put("nextC", table.get(i).get("C"));
				} else if (result1.equals("0") && result2.equals("1")) {
					table.get(i).put("nextC", "0");
				} else if (result1.equals("1") && result2.equals("0")) {
					table.get(i).put("nextC", "1");
				} else if (result1.equals("1") && result2.equals("1")) {
					table.get(i).put("nextC", "$");
				}
			}
		}

		if (noOfOutput == 1) {
			for (int i = 0; i < table.size(); i++) {
				String currentExp = output.replace('A', table.get(i).get("A").charAt(0)); // charat(0)
																							// para
																							// maconvert
																							// from
																							// string
																							// to
																							// char

				if (noOfFF == 2 || noOfFF == 3) {
					currentExp = currentExp.replace('B', table.get(i).get("B").charAt(0));
				}

				if (noOfFF == 3) {
					currentExp = currentExp.replace('C', table.get(i).get("C").charAt(0));
				}

				currentExp = currentExp.replace('x', table.get(i).get("x").charAt(0));

				if (noOfInput == 2) {
					currentExp = currentExp.replace('y', table.get(i).get("y").charAt(0));
				}
				String result = getValue(currentExp) + ""; // + "" to convert to
															// string
				table.get(i).put("Out", result);
			}
		}

		printTable(table);
	}

	/**
	 * Returns a complement binary value.
	 * 
	 * @param value
	 * @return
	 */
	private String complement(String value) {
		if (value.equals("0")) {
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * Prints the result in the console and saves the table to an HTML file on
	 * the user's desktop. (State Table.html
	 * 
	 * @param table
	 */
	private void printTable(ArrayList<Hashtable<String, String>> table) {
		Set<String> header = table.get(0).keySet();
		String location = System.getProperty("user.home") + "/Desktop/State Table.html";
		String content = "<html> \n  <head><title> State Table </title></head> \n <link rel=\"stylesheet\" href=\"https://bootswatch.com/4/flatly/bootstrap.css\">  \n <body> \n <br> \n <h1 style=\" margin-left: 50px\"> State Table </h1> \n <br><br> \n <table class=\"table table-striped table-hover\" style=\"width:55% ; margin-left:50px \"> \n <thead> \n <tr class=\"info\"> \n";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(location))) {
			// I did that because the arrangement of the Hash table is not in
			// order

			// Header
			System.out.print("A \t");
			content = content + "<th>A</th> \n";
			if (header.contains("B")) {
				content = content + "<th>B</th> \n";
				System.out.print("B \t");
			}
			if (header.contains("C")) {
				content = content + "<th>C</th> \n";
				System.out.print("C \t");
			}
			System.out.print("x \t");
			content = content + "<th>x</th> \n";
			if (header.contains("y")) {
				content = content + "<th>y</th> \n";
				System.out.print("y \t");
			}
			if (header.contains("DA")) {
				content = content + "<th>DA</th> \n";
				System.out.print("DA \t");
			}
			if (header.contains("DB")) {
				content = content + "<th>DB</th> \n";
				System.out.print("DB \t");
			}
			if (header.contains("DC")) {
				content = content + "<th>DC</th> \n";
				System.out.print("DC \t");
			}
			if (header.contains("TA")) {
				content = content + "<th>TA</th> \n";
				System.out.print("TA \t");
			}
			if (header.contains("TB")) {
				content = content + "<th>TB</th> \n";
				System.out.print("TB \t");
			}
			if (header.contains("TC")) {
				content = content + "<th>TC</th> \n";
				System.out.print("TC \t");
			}
			if (header.contains("JA")) {
				content = content + "<th>JA</th> \n";
				System.out.print("JA \t");
			}
			if (header.contains("JB")) {
				content = content + "<th>JB</th> \n";
				System.out.print("JB \t");
			}
			if (header.contains("JC")) {
				content = content + "<th>JC</th> \n";
				System.out.print("JC \t");
			}
			if (header.contains("KA")) {
				content = content + "<th>KA</th> \n";
				System.out.print("KA \t");
			}
			if (header.contains("KB")) {
				content = content + "<th>KB</th> \n";
				System.out.print("KB \t");
			}
			if (header.contains("KC")) {
				content = content + "<th>KC</th> \n";
				System.out.print("KC \t");
			}
			if (header.contains("RA")) {
				content = content + "<th>RA</th> \n";
				System.out.print("RA \t");
			}

			if (header.contains("RB")) {
				content = content + "<th>RB</th> \n";
				System.out.print("RB \t");
			}

			if (header.contains("RC")) {
				content = content + "<th>RC</th> \n";
				System.out.print("RC \t");
			}

			if (header.contains("SA")) {
				content = content + "<th>SA</th> \n";
				System.out.print("SA \t");
			}

			if (header.contains("SB")) {
				content = content + "<th>SB</th> \n";
				System.out.print("SB \t");
			}

			if (header.contains("SC")) {
				content = content + "<th>SC</th> \n";
				System.out.print("SC \t");
			}

			if (header.contains("nextA")) {
				content = content + "<th>Next A</th> \n";
				System.out.print("nextA \t");
			}

			if (header.contains("nextB")) {
				content = content + "<th>Next B</th> \n";
				System.out.print("nextB \t");
			}

			if (header.contains("nextC")) {
				content = content + "<th>Next C</th> \n";
				System.out.print("nextC \t");
			}

			if (header.contains("Out")) {
				content = content + "<th>Output</th> \n";
				System.out.print("Out \t");
			}
			content = content + "</tr> \n <thead> \n <tbody> \n";

			System.out.println(
					"\n---------------------------------------------------------------------------------------------------");

			// Body
			for (int i = 0; i < table.size(); i++) {

				content = content + "<tr> \n ";

				System.out.print(table.get(i).get("A") + " \t");
				content = content + "<td> " + table.get(i).get("A") + " </td> \n";

				if (header.contains("B")) {
					System.out.print(table.get(i).get("B") + " \t");
					content = content + "<td> " + table.get(i).get("B") + " </td> \n";
				}

				if (header.contains("C")) {
					System.out.print(table.get(i).get("C") + " \t");
					content = content + "<td> " + table.get(i).get("C") + " </td> \n";
				}

				System.out.print(table.get(i).get("x") + " \t");
				content = content + "<td> " + table.get(i).get("x") + " </td> \n";

				if (header.contains("y")) {
					System.out.print(table.get(i).get("y") + " \t");
					content = content + "<td> " + table.get(i).get("y") + " </td> \n";
				}

				if (header.contains("DA")) {
					System.out.print(table.get(i).get("DA") + " \t");
					content = content + "<td> " + table.get(i).get("DA") + " </td> \n";
				}

				if (header.contains("DB")) {
					System.out.print(table.get(i).get("DB") + " \t");
					content = content + "<td> " + table.get(i).get("DB") + " </td> \n";
				}

				if (header.contains("DC")) {
					System.out.print(table.get(i).get("DC") + " \t");
					content = content + "<td> " + table.get(i).get("DC") + " </td> \n";
				}

				if (header.contains("TA")) {
					System.out.print(table.get(i).get("TA") + " \t");
					content = content + "<td> " + table.get(i).get("TA") + " </td> \n";
				}

				if (header.contains("TB")) {
					System.out.print(table.get(i).get("TB") + " \t");
					content = content + "<td> " + table.get(i).get("TB") + " </td> \n";
				}

				if (header.contains("TC")) {
					System.out.print(table.get(i).get("TC") + " \t");
					content = content + "<td> " + table.get(i).get("TC") + " </td> \n";
				}

				if (header.contains("JA")) {
					System.out.print(table.get(i).get("JA") + " \t");
					content = content + "<td> " + table.get(i).get("JA") + " </td> \n";
				}

				if (header.contains("JB")) {
					System.out.print(table.get(i).get("JB") + " \t");
					content = content + "<td> " + table.get(i).get("JB") + " </td> \n";
				}

				if (header.contains("JC")) {
					System.out.print(table.get(i).get("JC") + " \t");
					content = content + "<td> " + table.get(i).get("JC") + " </td> \n";
				}

				if (header.contains("KA")) {
					System.out.print(table.get(i).get("KA") + " \t");
					content = content + "<td> " + table.get(i).get("KA") + " </td> \n";
				}

				if (header.contains("KB")) {
					System.out.print(table.get(i).get("KB") + " \t");
					content = content + "<td> " + table.get(i).get("KB") + " </td> \n";
				}

				if (header.contains("KC")) {
					System.out.print(table.get(i).get("KC") + " \t");
					content = content + "<td> " + table.get(i).get("KC") + " </td> \n";
				}

				if (header.contains("RA")) {
					System.out.print(table.get(i).get("RA") + " \t");
					content = content + "<td> " + table.get(i).get("RA") + " </td> \n";
				}

				if (header.contains("RB")) {
					System.out.print(table.get(i).get("RB") + " \t");
					content = content + "<td> " + table.get(i).get("RB") + " </td> \n";
				}

				if (header.contains("RC")) {
					System.out.print(table.get(i).get("RC") + " \t");
					content = content + "<td> " + table.get(i).get("RC") + " </td> \n";
				}

				if (header.contains("SA")) {
					System.out.print(table.get(i).get("SA") + " \t");
					content = content + "<td> " + table.get(i).get("SA") + " </td> \n";
				}

				if (header.contains("SB")) {
					System.out.print(table.get(i).get("SB") + " \t");
					content = content + "<td> " + table.get(i).get("SB") + " </td> \n";
				}

				if (header.contains("SC")) {
					System.out.print(table.get(i).get("SC") + " \t");
					content = content + "<td> " + table.get(i).get("SC") + " </td> \n";
				}

				if (header.contains("nextA")) {
					System.out.print(table.get(i).get("nextA") + " \t");
					content = content + "<td> " + table.get(i).get("nextA") + " </td> \n";
				}

				if (header.contains("nextB")) {
					System.out.print(table.get(i).get("nextB") + " \t");
					content = content + "<td> " + table.get(i).get("nextB") + " </td> \n";
				}

				if (header.contains("nextC")) {
					System.out.print(table.get(i).get("nextC") + " \t");
					content = content + "<td> " + table.get(i).get("nextC") + " </td> \n";
				}

				if (header.contains("Out")) {
					System.out.print(table.get(i).get("Out") + " \t");
					content = content + "<td> " + table.get(i).get("Out") + " </td> \n";
				}

				content = content + "</tr> \n";
				System.out.println("\n");
			}
			content = content + " <tbody> \n </table> \n </body> \n </html>";
			writer.write(content);
			File htmlFile = new File(location);
			Desktop.getDesktop().browse(htmlFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}