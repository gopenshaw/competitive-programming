// The values for each case are X1, Y1, X2, Y2, R
// The center of the circle is at (0, 0)
// 
// The problem is to determine the length of rope that will
// connect the two points and go around the circle.
// 
// If the points can be connected without intersecting the circle,
// the length of the rope is simply the distance between the points.
// 
// If the rope intersects the circle, then the rope length will 
// be the two segments plus the arc length.
// 
// The length of the segment can be found using pythagorean theorem,
// where the hypotenuse is the distance from point to origin, and the
// triangle sides are the segment length and the radius.
//
// The arc length depends on the angle the between the lines
// formed by connecting p1 to the origin and p2 to the origin
// and the radius of the circle. The shortest line that includes
// the arc length will have the lines intersecting the circle
// at a tangent. Therefore the arc angle is the angle between the
// two lines - 90 degrees.
//

import java.util.*;

class Main {

	static final Point ORIGIN = new Point(0, 0);

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		for (int i = 0; i < numCases; i++) {
			double x1 = conIn.nextFloat();
			double y1 = conIn.nextFloat();
			double x2 = conIn.nextFloat();
			double y2 = conIn.nextFloat();
			double r = conIn.nextFloat();
			Point p1 = new Point(x1, y1);
			Point p2 = new Point(x2, y2);
			Circle circle = new Circle(ORIGIN, r);
			if (!circleIntersectsLine(x1, y1, x2, y2, r)) {
				System.out.println("line solution");
				System.out.printf("%.3f\n",p1.distanceTo(p2));
				continue;
			}

			double segment1 =
				getSegmentLength(circle.radius, p1.distanceTo(ORIGIN));
			double segment2 =
				getSegmentLength(circle.radius, p2.distanceTo(ORIGIN));
			double arcAngle =
				p1.vectorTo(ORIGIN).angleInRadiansTo(p2.vectorTo(ORIGIN))
					- Math.PI / 2;
			double arcLength = circle.arcLength(arcAngle);
			System.out.printf("%.3f\n",segment1 + segment2 + arcLength);
		}
	}

	static boolean circleIntersectsLine(
		double x1, double y1, double x2, double y2, double radius) {
		double dx = x2 - x1;
		double dy = y2 - y1;
		double factor = (dx * -x1 + dy * -y1) / 
			(dx * dx + dy * dy);
		double x3 = dx * factor + x1;
		double y3 = dy * factor + x2;
		return (Math.sqrt(x3 * x3 + y3 * y3) <= radius);
	}

	static double getSegmentLength(double segment, double hypotenuse) {
		return Math.sqrt(Math.pow(hypotenuse, 2) - Math.pow(segment, 2));
	}

	static double getArcLength(double angleInRadians, double radius) {
		double circumference = 2 * Math.PI * radius;
		return circumference * angleInRadians / ( 2 * Math.PI);
	}
}

class Circle {
	Point origin;
	double radius;

	Circle(Point origin, double radius) {
		this.origin = origin;
		this.radius = radius;
	}

	boolean intersects(Line line) {
		Vector v = line.p1.vectorTo(line.p2);
		Vector toCircle = line.p1.vectorTo(origin);
		Vector proj = toCircle.projectOnto(v);
		Point p = line.p1.addVector(proj);
		double distance = origin.distanceTo(p);
		return (distance <= radius);
	}

	double circumference() {
		return 2 * Math.PI * radius;
	}

	double arcLength(double angleInRadians) {
		return circumference() * angleInRadians / (2 * Math.PI);
	}
}

class Line {
	Point p1;
	Point p2;

	Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
}

class Point {
	double x;
	double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	Vector vectorTo(Point p) {
		double dx = p.x - x;
		double dy = p.y - y;
		return new Vector(dx, dy);
	}

	Point addVector(Vector v) {
		return new Point(x + v.x, y + v.y);
	}

	double distanceTo(Point p) {
		return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
	}
}

class Vector {
	double x;
	double y;

	Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	double dotProduct(Vector v) {
		return v.x * x + v.y * y;
	}

	double magnitude() {
		return Math.sqrt(x * x + y * y);
	}

	Vector multiply(double factor) {
		return new Vector(x * factor, y * factor);
	}

	Vector projectOnto(Vector v) {
		return v.multiply(dotProduct(v) / Math.pow(v.magnitude(), 2));
	}
		
	double angleInRadiansTo(Vector v) {
		return Math.acos((dotProduct(v)) / (magnitude() * v.magnitude()));
	}
}
