/**
 * 
 */
package edu.csupomona.cs.cs365.Proj1;

/**
 * @author Loomomm
 * 
 */
public class MultUnit {

	final static int BIAS = 127;

	public MultUnit() {
	}

	public String xor(String sign1, String sign2) {
		if (sign1.compareTo(sign2) == 0) {
			return "0";
		} else {
			return "1";
		}
	}

	public String aluPlus(String exponent1, String exponent2) {
		char carry = '0';

		char exp1[] = exponent1.toCharArray();
		char exp2[] = exponent2.toCharArray();
		char exp3[] = new char[exp1.length];

		for (int i = exp1.length - 1; i >= 0; i--) {
			if (exp1[i] == '0' && exp2[i] == '0' && carry == '0') {
				exp3[i] = '0';
				carry = '0';
			} else if (exp1[i] == '0' && exp2[i] == '0' && carry == '1') {
				exp3[i] = '1';
				carry = '0';
			} else if (exp1[i] == '0' && exp2[i] == '1' && carry == '0') {
				exp3[i] = '1';
				carry = '0';
			} else if (exp1[i] == '0' && exp2[i] == '1' && carry == '1') {
				exp3[i] = '0';
				carry = '1';
			} else if (exp1[i] == '1' && exp2[i] == '0' && carry == '0') {
				exp3[i] = '1';
				carry = '0';
			} else if (exp1[i] == '1' && exp2[i] == '0' && carry == '1') {
				exp3[i] = '0';
				carry = '1';
			} else if (exp1[i] == '1' && exp2[i] == '1' && carry == '0') {
				exp3[i] = '0';
				carry = '1';
			} else {
				exp3[i] = '1';
				carry = '1';
			}
		}
		String temp = new String(exp3);
		return temp;
	}

	public String aluMinus(String exponent1) {

		char exp1[] = exponent1.toCharArray();
		char exp2[] = new char[exp1.length];
		int exp = 0;
		int counter = 0;

		for (int i = exp1.length - 1; i >= 0; i--) {
			exp += Integer.parseInt(Character.toString(exp1[i]))
					* Math.pow(2, counter++);
		}
		exp -= BIAS;
		for (int i = exp1.length - 1; i >= 0; i--) {
			exp2[i] = Integer.toString((exp % 2)).charAt(0);
			exp /= 2;
		}
		String temp = new String(exp2);
		return temp;
	}

	public String hiddenOne(String fraction) {
		return "1." + fraction;
	}

	public String mult(String fraction1, String fraction2) {
		int counter1 = 0;
		int counter2 = 0;
		boolean b1 = true;
		boolean b2 = true;
		char[] f1 = fraction1.toCharArray();
		char[] f2 = fraction2.toCharArray();

		for (int i = f1.length - 1; i >= 0; i--) {
			if (f1[i] == '.' && b1) {
				b1 = false;
			} else if (b1) {
				counter1++;
			}

			if (f2[i] == '.' && b2) {
				b2 = false;
			} else if (b2) {
				counter2++;
			}
		}
		
		int total = counter1 + counter2;
		f1 = fraction1.replaceAll("\\.", "").toCharArray();
		f2 = fraction2.replaceAll("\\.", "").toCharArray();

		char[] sum1 = new char[(f1.length) * 2];
		char[] sum2 = new char[(f1.length) * 2];
		char[] f3 = new char[(f1.length) * 2];
		int counter = 1;
		int temp = 0;
		char carry, in, out;
		if (f2[f2.length - 1] == '1') {
			for (int i = 0; i < sum1.length; i++) {
				if (i < (sum1.length - f1.length)) {
					sum1[i] = '0';
				} else {
					sum1[i] = f1[temp++];
				}
			}
		} else {
			for (int i = 0; i < sum1.length; i++) {
				sum1[i] = '0';
			}
		}
		for (int i = f2.length - 1; i > 0; i--) {
			temp = sum2.length - 1;
			for (int j = counter; j > 0; j--) {
				sum2[temp--] = '0';
			}
			if (f2[f2.length - 1 - counter] == '1') {
				for (int j = f1.length - 1; j >= 0; j--) {
					sum2[temp--] = f1[j];
				}
				for (int j = temp; j >= 0; j--) {
					sum2[temp--] = '0';
				}
			} else {
				for (int j = 0; j < sum1.length; j++) {
					sum2[j] = '0';
				}
			}
			carry = '0';
			for (int j = sum1.length - 1; j >= 0; j--) {
				if (sum1[j] == '0' && sum2[j] == '0' && carry == '0') {
					f3[j] = '0';
					carry = '0';
				} else if (sum1[j] == '0' && sum2[j] == '0' && carry == '1') {
					f3[j] = '1';
					carry = '0';
				} else if (sum1[j] == '0' && sum2[j] == '1' && carry == '0') {
					f3[j] = '1';
					carry = '0';
				} else if (sum1[j] == '0' && sum2[j] == '1' && carry == '1') {
					f3[j] = '0';
					carry = '1';
				} else if (sum1[j] == '1' && sum2[j] == '0' && carry == '0') {
					f3[j] = '1';
					carry = '0';
				} else if (sum1[j] == '1' && sum2[j] == '0' && carry == '1') {
					f3[j] = '0';
					carry = '1';
				} else if (sum1[j] == '1' && sum2[j] == '1' && carry == '0') {
					f3[j] = '0';
					carry = '1';
				} else {
					f3[j] = '1';
					carry = '1';
				}
			}
			sum1 = f3;
			counter++;
		}
		in = '.';
		for (int i = f3.length - total; i < f3.length; i++) {
			out = f3[i];
			f3[i] = in;
			in = out;
		}
		String s = new String(f3);
		return s;
	}
}
