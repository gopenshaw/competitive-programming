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
// The length of the segments can be found using pythagorean theorem,
// where the hypotenuse is the distance from point to origin, and the
// triangle sides are the segment length and the radius.
//
// The arc length can be found by taking the angle between the lines
// and subtracting the angle corresponding the the triangle formed by
// connecting each point to the origin and the point at which it connects
// with the circle. This is hard to write in words.

import java.util.*;

class Main {

	static final Point ORIGIN = new Point(0, 0);

	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();
		for (int i = 0; i < numCases; i++) {
			Point p1 = new Point(conIn.nextFloat(), conIn.nextFloat());
			Point p2 = new Point(conIn.nextFloat(), conIn.nextFloat());
			Circle circle = new Circle(ORIGIN, conIn.nextFloat());
			if (!circle.intersects(new Line(p1, p2))) {
				System.out.printf("%.3f\n",p1.distanceTo(p2));
				continue;
			}

			double segment1 =
				getSegmentLength(circle.radius, p1.distanceTo(ORIGIN));
			double segment2 =
				getSegmentLength(circle.radius, p2.distanceTo(ORIGIN));
			double angleBetweenVectors = 
				p1.vectorTo(ORIGIN).angleInRadiansTo(p2.vectorTo(ORIGIN));
			double arcAngle = 
				angleBetweenVectors 
					- Math.atan(segment1 / circle.radius)
					- Math.atan(segment2 / circle.radius);
			double arcLength = circle.arcLength(arcAngle);
//			System.out.printf("line1,%f\nline2,%f\narcLength,%f\n",
//				segment1, segment2, arcAngle);
			System.out.printf("%.3f\n",segment1 + segment2 + arcLength);
		}
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
	Point center;
	double radius;

	Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	boolean isContained(Point p) {
		return center.distanceTo(p) < radius;
	}

	boolean hasOnBoundary(Point p) {
		return p.distanceTo(center) == radius;
	}

	boolean intersects(Line line) {
		Point closePoint, farPoint;
		if (line.p1.distanceTo(center) < line.p2.distanceTo(center)) {
			closePoint = line.p1;
			farPoint = line.p2;
		}
		else {
			closePoint = line.p2;
			farPoint = line.p1;
		}
		
		Vector v = closePoint.vectorTo(farPoint);
		Vector toCenter = closePoint.vectorTo(center);
		Vector proj = toCenter.projectOnto(v);
		if (!isContained(closePoint)
			&& v.angleInRadiansTo(toCenter) >= Math.PI / 2) {
			return false;
		}

		Point p = closePoint.addVector(proj);
		double distance = center.distanceTo(p);
		return (distance < radius);
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
