// 
// UVA 429 - Word Transformation
// Garrett Openshaw
// 

import java.util.*;

class Main {

	public static String[] wordArray = new String[200];
	public static HashMap<String, Integer> map = new HashMap<String, Integer>();
	public static int[][] graph = new int[200][200];

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		
		for (int n = 0; n < numCases; n++) {
		
			int numWords = 0;
		
			String input = conIn.next();	
			while (!input.equals("*")) {
				wordArray[numWords++] = input;
				input = conIn.next();
			}
			
			conIn.nextLine();
			String[] words = new String[2];
			
			while (conIn.hasNext()) {
				input = conIn.nextLine();
				if (input.length() == 0)
					break;
				
				words = input.split(" ");
				System.out.println(words[0] + " " + words[1] + " "
									+ getDistance(words[0], words[1]));
				
			}
			
			if (n != numCases - 1)
				System.out.println();	
		}
	}
	
	public static int getDistance(String word1, String word2) {
		return -1;
	}
}