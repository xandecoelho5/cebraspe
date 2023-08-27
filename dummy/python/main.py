def pack_bagpack(scores, weights, capacity):
  n = len(scores)
  table = [[0] * (capacity + 1) for _ in range(n + 1)]
  for i in range(1, n + 1):
    for w in range(capacity + 1):
      if (weights[i - 1] > w):
        table[i][w] = table[i - 1][w]
      else:
        table[i][w] = max(table[i - 1][w], table[i - 1]
                          [w - weights[i - 1]] + scores[i - 1])
  print(table)
  return table[n][capacity]


def pack_bagpack2(scores, weights, capacity):
  n = len(scores)
  table = [[0] * (capacity + 1) for _ in range(n)]
  for i in range(1, n):
    for w in range(capacity + 1):
      if (weights[i - 1] > w):
        table[i][w] = table[i - 1][w]
      else:
        table[i][w] = max(table[i - 1][w], table[i - 1]
                          [w - weights[i - 1]] + scores[i - 1])
  print(table)
  return table[n][capacity]


def knapsack(items, max_weight):
  n = len(items)
  dp = [[0] * (max_weight + 1) for _ in range(n + 1)]

  for i in range(1, n + 1):
    for w in range(max_weight + 1):
      if items[i - 1][0] > w:
        dp[i][w] = dp[i - 1][w]
      else:
        dp[i][w] = max(dp[i - 1][w], dp[i - 1]
                       [w - items[i - 1][0]] + items[i - 1][1])

  return dp[n][max_weight]


print(pack_bagpack2([15, 10, 9, 5], [1, 5, 3, 4], 8))

# print(knapsack([(1, 15), (5, 10), (3, 9), (4, 5)], 8))
# pack_bagpack([20, 5, 10, 40, 15, 25], [1, 2, 3, 8, 7, 4], 10)
