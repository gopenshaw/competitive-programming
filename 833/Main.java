//  Solution strategy
//  We will start at the source and make a temporary vertical line.
//  For all lines that this line crosses, we will get the intersection point.
//  We will choose the line associated with the point with the greatest y value.
//  For that point, the new source will be the point with the smallest x value.
//  
//  This will be implemented recursively
//  
//	I may try to sort the lines from highest to lowest using the min y value
//	from of both points on the line. This would allow the program to check
//	only the lines that could be below the particular point.
//

import java.util.*;

class Main {
	public static void main(String[] args) {
		Line line1 = new Line(new Point(1, 1), new Point(11, 1));
		Line line2 = new Line(new Point(10, 10), new Point(10, 0));
		Point intersection = line1.getIntersection(line2);
		System.out.println(intersection);
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

class Line {
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
							&& p.x <= Math.max(this.p1.x, this.p2.x);
		boolean onThatLine = Math.min(line.p1.x, line.p2.x) <= p.x
							&& p.x <= Math.max(line.p1.x, line.p2.x);
		return onThisLine && onThatLine ? p : null;
	}

	@Override
	public String toString() {
		return String.format("%s-%s\n",p1,p2);
	}
}
