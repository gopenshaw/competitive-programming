// 
// UVA #10055 - Hashmat the Brave Warrior
// Garrett Openshaw
// 

import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		
		while (conIn.hasNext()) {
			int a = conIn.nextInt();
			int b = conIn.nextInt();
			
			System.out.println(Math.abs(a - b));
		}
	}
}