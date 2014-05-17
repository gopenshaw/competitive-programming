import java.util.*;

class Main {

	public static int[] A = new int[1000000];

	public static void main(String[] args) {
		fillArray();
		
		Scanner conIn = new Scanner(System.in);
		while (conIn.hasNext()) {
			int i1 = conIn.nextInt();
			int i2 = conIn.nextInt();
			
			int low;
			int high;
			if (i1 < i1) {
				low = i1;
				high = i2;
			}
			else {
				low = i2;
				high = i1;
			}
			
			int max = 0;
			for (int i = low; i <= high; i++) {
				if (A[i] > max)
					max = A[i];
			}
			System.out.println(i1 + " " + i2 + " " + max);
		}
	}
	
	public static void fillArray() {
	
	}
}