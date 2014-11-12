import java.util.*;

class A {
	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int caseNum = 0;
		int n1 = conIn.nextInt();
		int n2 = conIn.nextInt();
		while (n1 != 0 || n2 != 0) {
			caseNum++;
			ArrayList<Integer> conFrac1 = new ArrayList<Integer>();
			for (int i = 0; i < n1; i++) {
				conFrac1.add(conIn.nextInt());
			}
			ArrayList<Integer> conFrac2 = new ArrayList<Integer>();
			for (int i = 0; i < n2; i++) {
				conFrac2.add(conIn.nextInt());
			}
			Fraction r1 = convertToFraction(conFrac1, 0);
			Fraction r2 = convertToFraction(conFrac2, 0);

			System.out.printf("Case %d:\n",caseNum);
			printList(convertToContinuousFraction(r1.add(r2)));
			printList(convertToContinuousFraction(r1.subtract(r2)));
			printList(convertToContinuousFraction(r1.multiply(r2)));
			printList(convertToContinuousFraction(r1.divide(r2)));

			n1 = conIn.nextInt();
			n2 = conIn.nextInt();
		}
	}

	static Fraction convertToFraction(ArrayList<Integer> list, int index) {
		Fraction value = new Fraction(list.get(index), 1L);
		if (index == list.size() - 1)
			return value;

		return value.add(convertToFraction(list, index + 1).inverse());
	}

	static ArrayList<Long> convertToContinuousFraction(Fraction f) {
		ArrayList<Long> result = new ArrayList<Long>();
		long value = 0L;
		if (f.isNegative()
			&& !f.isWholeNumber()) {
			value = f.num / f.den - 1;
		}
		else {
			value = f.num / f.den;
		}

		Fraction rem = f.subtract(new Fraction(value, 1L));
		result.add(value);
		while (rem.num != 0) {
			value = rem.den / rem.num;
			result.add(value);
			rem = rem.inverse().subtract(new Fraction(value, 1L));
		}

		return result;
	}

	static void printList(ArrayList<Long> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
			if (i != list.size() - 1)
				System.out.print(" ");

		}

		System.out.println();
	}
}

class Fraction {
	long num;
	long den;

	Fraction(long num, long den) {
		this.num = num;
		this.den = den;
	}

	boolean isNegative() {
		return (num > 0 && den < 0)
			|| (num < 0 && den > 0);
	}

	boolean isWholeNumber() {
		return (num / den % 1) == 0;
	}

	Fraction add(Fraction f) {
		if (den == f.den)
			return new Fraction(num + f.num, den);

		return new Fraction(num * f.den + f.num * den, f.den * den);
	}

	Fraction subtract(Fraction f) {
		if (den == f.den)
			return new Fraction(num - f.num, den);

		return new Fraction(num * f.den - f.num * den, f.den * den);
	}

	Fraction multiply(Fraction f) {
		return new Fraction(num * f.num, den * f.den);
	}

	Fraction divide(Fraction f) {
		return multiply(f.inverse());
	}

	Fraction inverse() {
		return new Fraction(den, num);
	}
}
