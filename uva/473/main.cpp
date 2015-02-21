#include <iostream>
#include <sstream>
#include <string>

using namespace std;

int numSongs;
int numDisks;
int minutesPerDisk;

int main () {
	int numCases;
	stringstream ss;
	cin >> numCases;
	for (int i = 0; i < numCases; i++) {
		cin >> numSongs;
		cin >> minutesPerDisk;
		cin >> numDisks;

		string songLengths;
		getline(cin, songLengths); // Get end of this line
		getline(cin, songLengths); // Get the line with data

		ss.clear();
		ss.str("");
		ss << songLengths;
		cout << songLengths << "\n";
		int length;
		while (!ss.eof()) {
			if (ss.peek() == ',') {
				ss.get();
			}

			ss >> length;
			cout << length << " ";
		}

		cout << "\n";
	}
}
