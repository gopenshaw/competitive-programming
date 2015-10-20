
#include <iostream>
#include <stack>

using namespace std;

bool is_operand(char c) {
  return c == '-'
  || c == '+'
  || c == '/'
  || c == '*'
  || c == '^';
}

int main()
{
  int N;
  cin >> N;
  for (int i = 0; i < N; i++) {
    string s;
    stack<char> c;
    cin >> s;
    for (int i = 0; i < s.length(); i++) {
      if (s[i] == '(') {

      }
      else if (s[i] == ')') {
        if (c.size() != 0) {
          cout << c.top();
          c.pop();
        }
      }
      else if (is_operand(s[i])) {
        c.push(s[i]);
      }
      else {
        cout << s[i];
      }
    }

    cout << "\n";
  }

  return 0;
}
