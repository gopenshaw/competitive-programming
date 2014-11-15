import java.util.*;

class H {
	public static void main (String[] args) {
		Scanner conIn = new Scanner(System.in);
		int numCases = conIn.nextInt();

		for (int i = 1; i <= numCases; i++) {

			int theta = conIn.nextInt();
			String s = conIn.next();
			int h = conIn.nextInt();

			
			int hour, minutes, seconds;
			if (s.charAt(0) == 'a') {
				int secondsAfter = getSecondsAfter(theta, h);
				hour = (h + secondsAfter / 3600) % 12;
				int rem = secondsAfter % 3600;
				minutes = rem / 60;
				seconds = rem % 60;
			}
			else {
				int secondsBefore = getSecondsBefore(theta, h);
				if (secondsBefore == 0) {
					hour = h;
					minutes = 0;
					seconds = 0;
				}
				else {
					hour = h - getHoursLost(secondsBefore);
					if (hour == 0) hour = 12;
					minutes = (60 - getMinutesLost(secondsBefore)) % 60;
					seconds = (60 - secondsBefore % 60) % 60;
				}
			}

			System.out.printf("Case %d: %d:%02d:%02d\n",
				i,hour,minutes,seconds);
		}
	}

	static int getSecondsAfter(int theta, int hour) {
		return (int)Math.round(120.0 / 11 * ( theta + 30 * hour));
	}

	static int getSecondsBefore(int theta, int hour) {
		return (int)Math.round(-120.0 / 11 * ( theta - 30 * (12 - hour)));
	}

	static int getHoursLost(int seconds) {
		int hours = 0;
		while (seconds > 0) {
			hours++;
			seconds -= 3600;
		}

		return hours;
	}

	static int getMinutesLost(int seconds) {
		seconds %= 3600;
		int minutes = 0;
		while (seconds > 0) {
			minutes++;
			seconds -= 60;
		}

		return minutes;
	}
}
