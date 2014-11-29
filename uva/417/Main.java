// 
// UVA 417 - Word Index
// Garrett Openshaw
// 

import java.util.*;

class Main {
	
	public static TreeMap<String, Integer> map = new TreeMap<String, Integer>();
	
	public static void main(String[] args) {
		buildMap();
	
		Scanner conIn = new Scanner(System.in);
		while (conIn.hasNext()) {
			Integer result = map.get(conIn.next());
			if (result == null)
				System.out.println(0);
			else
				System.out.println(result);
		}
	}
	
	public static void buildMap() {
		int count = 1;
	
		char[] one = new char[1];
		for (int i = 0; i < 26; i++) {
			one[0] = (char)('a' + i);
			map.put(new String(one), count++);
		}
		
		char[] two = new char[2];
		for (int i = 0; i < 26; i++) {
			two[0] = (char)('a' + i);
			for (int j = i + 1; j < 26; j++) {
				two[1] = (char)('a' + j);
				map.put(new String(two), count++);
			}
		}
		
		char[] three = new char[3];
		for (int i = 0; i < 26; i++) {
			three[0] = (char)('a' + i);
			for (int j = i + 1; j < 26; j++) {
				three[1] = (char)('a' + j);
				for (int k = j + 1; k < 26; k++) {
					three[2] = (char)('a' + k);
					map.put(new String(three), count++);
				}
			}
		}
		
		char[] four = new char[4];
		for (int i = 0; i < 26; i++) {
			four[0] = (char)('a' + i);
			for (int j = i + 1; j < 26; j++) {
				four[1] = (char)('a' + j);
				for (int k = j + 1; k < 26; k++) {
					four[2] = (char)('a' + k);
					for (int l = k + 1; l < 26; l++) {
						four[3] = (char)('a' + l);
						map.put(new String(four), count++);
					}
				}
			}
		}
		
		char[] five = new char[5];
		for (int i = 0; i < 26; i++) {
			five[0] = (char)('a' + i);
			for (int j = i + 1; j < 26; j++) {
				five[1] = (char)('a' + j);
				for (int k = j + 1; k < 26; k++) {
					five[2] = (char)('a' + k);
					for (int l = k + 1; l < 26; l++) {
						five[3] = (char)('a' + l);
						for (int m = l + 1; m < 26; m++) {
							five[4] = (char)('a' + m);
							map.put(new String(five), count++);
						}
					}
				}
			}
		}
	
		return;
	}
}