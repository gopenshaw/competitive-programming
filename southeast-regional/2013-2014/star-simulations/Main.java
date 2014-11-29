import java.util.*;

class Main {

	public static final int MAX_POINTS = 100000;

	public static int numStars;
	public static long desiredDistance;
	public static long distanceSquared;
	public static int result;

	public static Point rootPoint;

	public static Scanner conIn;

	public static Point points[] = new Point[MAX_POINTS];

	public static void main(String[] args) {
		conIn = new Scanner(System.in);
		numStars = conIn.nextInt();
		desiredDistance = conIn.nextLong();

		while (numStars != 0
				&& desiredDistance != 0) {
			result = 0;
			distanceSquared = desiredDistance * desiredDistance;
			solveProblem();
			numStars = conIn.nextInt();
			desiredDistance = conIn.nextLong();
		}
	}

	public static void solveProblem() {
		getInput();
		buildTree();

		for (int i = 0; i < numStars; i++) {
			int count = pointCheck(points[i], rootPoint);
			result += count - 1;
		}

		System.out.println(result / 2);
	}

	public static long distSqrd(Point p1, Point p2) {
		return (p1.x - p2.x)*(p1.x - p2.x) 
			 + (p1.y - p2.y)*(p1.y - p2.y)	
			 + (p1.z - p2.z)*(p1.z - p2.z);
	}

	public static int pointCheck(Point source, Point current) {
		int result = 0;

		if (current == null)
			return result;


		if (distSqrd(source, current) < distanceSquared) {
			result++;
		}
		
		long dimensionalDiff = current.diff(source);

		if (Math.abs(dimensionalDiff) < desiredDistance) {
			result += pointCheck(source, current.right);
			result += pointCheck(source, current.left);
		} else {
			if (dimensionalDiff < 0) {
				//--For x axis, source is the left of current
				result += pointCheck(source, current.left);
			} else if (dimensionalDiff > 0) {
				result += pointCheck(source, current.right);
			}
		}

		return result;
	}

	public static void swap (int index1, int index2) {
		Point temp = points[index1];
		points[index1] = points[index2];
		points[index2] = temp;
	}

	public static void getInput() {
		for (int i = 0; i < numStars; i++) {
			int x = conIn.nextInt();
			int y = conIn.nextInt();
			int z = conIn.nextInt();

			Point p = new Point(x,y,z);

			points[i] = p;

			int swapIndex = (int)(Math.random()*(i+1));
			swap(swapIndex, i);
		}
		rootPoint = points[0];
	}
	
	public static void buildTree() {
		rootPoint = points[0];
		for (int i = 1; i < numStars; i++) {
			rootPoint.addChild(points[i]);
		}
	}
}

class Point {
	long x, y, z;
	Point left, right;
	int split; /* 0 = x, 1 = y, 2 = z */

	public Point(int x1,int y1,int z1) {
		x = x1;
		y = y1;
		z = z1;
	}

	public void print() {
		System.out.printf("%d %d %d\n", x, y, z);
	}

	public long diff(Point p1) {
		switch (split)
		{
			case 0:
				return p1.x - x;
			case 1:
				return p1.y - y;
			case 2:
				return p1.z - z;
		}
		return 0;
	}
	
	public void addChild(Point p1) {
		long d = diff(p1);
		if (d <= 0) {
			if (left != null) {
				left.addChild(p1);
			} else {
				left = p1;
				p1.split = (split + 1) % 3;
			}
		} else {
			if (right != null) {
				right.addChild(p1);
			} else {
				right = p1;
				p1.split = (split + 1) % 3;
			}
		}
	}
}

