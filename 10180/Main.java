// The values for each case are X1, Y1, X2, Y2, R
// The center of the circle is at (0, 0)
// 
// We will connect p1 to the origin and p2 to the origin.
// The rope will go around the circle where the angle is less than 180.
// 
// The rope length will be the two segments plus the arc length.
// The length of the segment can be found using pythagorean theorem,
// where the hypotenuse is the distance from point to origin, and the
// triangle sides are the segment length and the radius.
//
// The arc length will be the degree between the two lines - 90.
//

import java.util.*

class Main {
	double getSegmentLength(double segment, double hypotenuse) {
		return Math.sqrt(Math.pow(hypotenuse, 2), Math.pow(segment, 2));
	}
}
