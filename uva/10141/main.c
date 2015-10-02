
#include <stdio.h>

int main()
{
  int req_count;
  int candidate_count;
  char buffer[80];

  double cost;
  int proposal_count;
  int max;

  while (1) {
    scanf("%d %d", &req_count, &candidate_count);
    if (req_count == 0 && candidate_count == 0)
      break;

    while (req_count-- > 0)
      gets(buffer);

    gets(buffer);
    scanf("%lf %d", &cost, &proposal_count);
    if (proposal_count > max) max = proposal_count;

    while (proposal_count-- > 0)
      gets(buffer);

    printf("%d\n", max);
  }

  return 0;
}
