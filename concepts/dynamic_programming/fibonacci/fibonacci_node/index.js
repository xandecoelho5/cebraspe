const n = 5;
const map = new Map();
const memo = {};

const fibonacci = (n) => {
  if (map.get(n)) return map.get(n);

  if (n == 0) return 0;
  if (n == 1) return 1;
  map.set(n, fibonacci(n - 1) + fibonacci(n - 2));
  return map.get(n);
};

const fibonacci3 = (n) => {
  if (n in memo) return memo[n];

  if (n == 0) return 0;
  if (n == 1) return 1;
  memo[n] = fibonacci3(n - 1) + fibonacci3(n - 2);
  return memo[n];
};

const fibonacci2 = (n) => {
  if (n == 0) return 0;
  if (n == 1) return 1;
  return fibonacci2(n - 1) + fibonacci2(n - 2);
};

console.log(fibonacci(1000));
