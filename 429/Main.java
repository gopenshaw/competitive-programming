// 
// UVA 429 - Word Transformation
// Garrett Openshaw
// 

import java.util.*;

class Word {
	public int word;
	public int distance;
	
	public Word(int word, int distance) {
		this.word = word;
		this.distance = distance;
	}
}

class Main {

	public static String[] wordArray = new String[200];
	public static HashMap<String, Integer> map = new HashMap<String, Integer>();
	public static boolean[][] graph = new boolean[200][200];
	public static boolean[] wasVisited = new boolean[200];
	public static int numWords;

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		
		for (int n = 0; n < numCases; n++) {
		
			numWords = 0;
		
			String input = conIn.next();
			while (!input.equals("*")) {
				wordArray[numWords++] = input;
				input = conIn.next();
			}
			
			for (int i = 0; i < numWords; i++) {
				map.put(wordArray[i], i);
				for (int j = i ; j < numWords; j++) {
					if (wordArray[i].length() == wordArray[j].length()
						&& areOneLetterApart(i, j)) {
						graph[i][j] = true;
						graph[j][i] = true;
					}
				}
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
			
			//--Prepare for next case
			if (n != numCases - 1) {
				System.out.println();
				map.clear();
				for (int i = 0; i < numWords; i++) {
					wasVisited[i] = false;
					for (int j = 0; j < numWords; j++) {
						graph[i][j] = false;
					}
				}
			}
		}
	}
	
	public static boolean areOneLetterApart(int x, int y) {
		String word1 = wordArray[x];
		String word2 = wordArray[y];
		boolean oneDifferent = false;
		
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				if (oneDifferent)
					return false;
				else
					oneDifferent = true;
			}
		}
		return true;
	}
	
	public static int getDistance(String word1, String word2) {
		//--Will return distance from source to destination.
		//--Will return -1 if no path exists
		
		int source = map.get(word1);
		int dest = map.get(word2);
		LinkedList<Word> q = new LinkedList<Word>();
		
		q.add(new Word(source, 0));
		
		while (!q.isEmpty()) {
			Word current = q.poll();
			if (current.word == dest)
				return current.distance;
		
			for (int i = 0; i < numWords; i++) {
				if (graph[current.word][i] && !wasVisited[i]) {
					wasVisited[i] = true;
					q.add(new Word(i, current.distance + 1));
				}
			}
		}
		
		return -1;
	}
}