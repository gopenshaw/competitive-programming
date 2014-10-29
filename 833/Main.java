//  Solution strategy
//  We will start at the source and make a temporary vertical line.
//  For all lines that this line crosses, we will get the intersection point.
//  We will choose the line associated with the point with the greatest y value.
//  For that point, the new source will be the point on the line with the smaller y value.
//  
//  This will be implemented recursively
//  
//	I may try to sort the lines from highest to lowest using the min y value
//	from of both points on the line. This would allow the program to check
//	only the lines that could be below the particular point.
//

import java.util.*;

class Main {

	final static int MAX_LINES = 1000000;
	final static int MAX_SOURCE = 1000000;

	static Line[] line = new Line[MAX_LINES];
	static Point[] source = new Point[MAX_SOURCE];

	static int numLines;
	static int numSources;

	public static void main(String[] args) {

		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		for (int i = 0; i < numCases; i++) {
			numLines = conIn.nextInt();
			for (int j = 0; j < numLines; j++) {
				int x1 = conIn.nextInt();
				int y1 = conIn.nextInt();
				int x2 = conIn.nextInt();
				int y2 = conIn.nextInt();
				line[j] = new Line(new Point(x1, y1), new Point(x2, y2));
			}

			Arrays.sort(line, 0, numLines);

			numSources = conIn.nextInt();
			for (int j = 0; j < numSources; j++) {
				int x = conIn.nextInt();
				int y = conIn.nextInt();
				int result = waterfall(new Point(x, y));
				System.out.println(result);
			}
		}
	}

	static int waterfall(Point source) {
		Line temp = new Line(source, new Point(source.x, 0));
		int highestLineUnderY = getHighestLineUnder((int)source.y);
		double max = 0;
		int nextLine = -1;
		for (int i = 0; i <= highestLineUnderY; i++) {
			Point cross = temp.getIntersection(line[i]);
			if (cross == null)
				continue;

			double pointHeight = cross.y;
			if (pointHeight > max) {
				max = pointHeight;
				nextLine = i;
			}
		}

		if (nextLine == -1)
			return (int)source.x;

		Point minPoint = line[nextLine].getLowerPoint();
		return waterfall(minPoint);
	}

	static int getHighestLineUnder(int y) {
		int min = 0;
		int max = numLines - 1;
		int mid = (max + min) / 2;
		while (min < max) {
//			System.out.printf("min:%d,max:%d,mid:%d\n",
//				min, max, mid);
			if (line[mid].minY() < y) {
				min = mid + 1;
				mid = (max + min) / 2;
			}
			else {
				max = mid - 1;
				mid = (max + min) / 2;
			}
		}

//		System.out.printf("min:%d,max:%d,mid:%d\n",
//			min, max, mid);
		return line[mid].minY() < y ? mid : mid - 1;
	}
}

class Point {
	double x;
	double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return String.format("x:%f,y:%f\n",x,y);
	}
}

class Line implements Comparable<Line> {
	Point p1;
	Point p2;

	double a;
	double b;
	double c;

	Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.a = p2.y - p1.y;
		this.b = p1.x - p2.x;
		this.c = a*p1.x + b*p1.y;
	}

	boolean isParallel(Line line) {
		return this.a * line.b - this.b * line.a == 0;
	}

	Point getIntersection(Line line) {
		if (this.isParallel(line))
			return null;

		double det = this.a * line.b - this.b * line.a;
		double x = (line.b * this.c - this.b * line.c) / det;
		double y = (this.a * line.c - line.a * this.c) / det;
		Point p = new Point(x, y);
		boolean onThisLine = Math.min(this.p1.x, this.p2.x) <= p.x
							&& p.x <= Math.max(this.p1.x, this.p2.x)
							&& Math.min(this.p1.y, this.p2.y) <= p.y
							&& p.y <= Math.max(this.p1.y, this.p2.y);
		boolean onThatLine = Math.min(line.p1.x, line.p2.x) <= p.x
							&& p.x <= Math.max(line.p1.x, line.p2.x)
							&& Math.min(line.p1.y, line.p2.y) <= p.y
							&& p.y <= Math.max(line.p1.y, line.p2.y);
		return onThisLine && onThatLine ? p : null;
	}

	Point getLowerPoint() {
		return p1.y < p2.y ? p1 : p2;
	}

	double minY() {
		return Math.min(this.p1.y, this.p2.y);
	}

	@Override
	public int compareTo(Line line) {
		if (this.minY() < line.minY())
			return -1;
		else if (this.minY() == line.minY())
			return 0;
		else
			return 1;
	}

	@Override
	public String toString() {
		return String.format("p1-%sp2-%s\n",p1,p2);
	}
}
