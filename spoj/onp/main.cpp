
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

int get_priority(char c) {
  switch (c) {
  case '^': return 5; break;
  case '/': return 4; break;
  case '*': return 3; break;
  case '-': return 2; break;
  default: return 1;
  }
}

struct Operator {
  char c;
  int level;

  Operator(char c, int level) : c(c), level(level)
  {
  }
};

int main()
{
  int N;
  cin >> N;
  for (int i = 0; i < N; i++) {
    string s;
    stack<Operator> ops;
    cin >> s;
    int level = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s[i] == '(') {
        level++;
      }
      else if (s[i] == ')') {
        level--;
        while (ops.size() > 0
              && ops.top().level > level) {
          cout << ops.top().c;
          ops.pop();
        }
      }
      else if (is_operand(s[i])) {
        if (ops.size() > 0
            && ops.top().level == level
            && get_priority(ops.top().c) > get_priority(s[i])) {
          cout << ops.top().c;
          ops.pop();
        }

        ops.push(Operator(s[i], level));
      }
      else {
        cout << s[i];
      }
    }

    while (ops.size() != 0) {
      cout << ops.top().c;
      ops.pop();
    }

    cout << "\n";
  }

  return 0;
}
