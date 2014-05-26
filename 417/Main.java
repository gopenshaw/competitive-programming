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
		while (conIn.hasNext())
			System.out.println(map.get(conIn.next()));
	}
	
	public static void buildMap() {
		return;
	}
}