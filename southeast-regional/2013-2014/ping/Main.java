import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);

		String input = conIn.next();

		while (!input.equals("0")) {

			ArrayList<Integer> results = new ArrayList<Integer>();

			for (int i = 1; i < input.length(); i++) {
				
				int current = new Integer(input.charAt(i) - '0');

				int count = 0;
				for(Integer n : results) {
					if (i % n == 0)
						count++;
				}

				if (count % 2 != current)
					results.add(new Integer(i));
			}

			for (int i = 0; i < results.size(); i++) {
				System.out.print(results.get(i));

				if (i != results.size() - 1)
					System.out.print(" ");
			}

			System.out.println();

			input = conIn.next();
		}
	}
}