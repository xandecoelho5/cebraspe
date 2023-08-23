def fib(n):
  if (n in {0, 1}):
    return n
  return fib(n - 1) + fib(n - 2)


cache = {0: 0, 1: 1}


def fib2(n):
  if (n in cache):
    return cache[n]
  cache[n] = fib2(n - 1) + fib2(n - 2)
  return cache[n]


print(fib2(40))
print(fib(40))
