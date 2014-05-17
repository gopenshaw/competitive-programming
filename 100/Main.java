import java.util.*;

class Main {

	public static int[] A = new int[1000000];
	public static double temp;
	public static int count;

	public static void main(String[] args) {
		fillArray();
		
		Scanner conIn = new Scanner(System.in);
		while (conIn.hasNext()) {
			int i1 = conIn.nextInt();
			int i2 = conIn.nextInt();
			
			int low;
			int high;
			if (i1 < i2) {
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
		for (int i = 1; i < 1000000; i++) {
			count = 1;
			temp = i;
			while (temp != 1) {
				if (temp % 2 == 0) {
					temp /= 2;
					count++;
				}
				else {
					temp = (3 * temp + 1) / 2;
					count += 2;
				}
			}
			A[i] = count;
		}
	}
}