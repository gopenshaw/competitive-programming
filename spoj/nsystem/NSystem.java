class NSystem {
	public static void main(String[] args) {
		String s1 = "c2x2i";
		String s2 = "4c8x8i";
		System.out.println(convertToInt(s1));
		System.out.println(convertToInt(s2));
	}

	static int convertToInt(String s) {
		int result = 0;
		char[] token = {'m', 'c', 'x', 'i'};
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
}
