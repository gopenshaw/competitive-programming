class NSystem {
	static char[] token = {'m', 'c', 'x', 'i'};

	public static void main(String[] args) {
		System.out.println(convertToString(123));
	}

	static int convertToInt(String s) {
		int result = 0;
		int lastIndex = -1;
		for (int i = 0; i < token.length; i++) {
			int index = s.indexOf(token[i]);
			if (index == -1) continue;

			int multiplier = (int)Math.pow(10, token.length - i - 1);
			//System.out.printf("token %s index %d\n", token[i], index);
			int digit = index - 1 > lastIndex
				? new Integer(s.charAt(index - 1) - '0')
				: 1;
			//System.out.printf("mult %d dig %d\n", multiplier, digit);
			result += multiplier * digit;
			lastIndex = index;
		}

		return result;
	}

	static String convertToString(int i) {
		int power = token.length - 1;
		StringBuilder s = new StringBuilder();
		while (power >= 0) {
			int mult = (int)Math.pow(10, power);
			int res = i / mult;
			if (res == 0) {
				power--;
				continue;
			}

			if (res > 1) s.append(res);

			s.append(token[token.length - power - 1]);
			i %= mult;
			power--;
		}

		return s.toString();
	}
}
