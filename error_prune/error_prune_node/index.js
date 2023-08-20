function calculate_fibonacci(n) {
  if (n <= 1) {
    return n;
  }
  return calculate_fibonacci(n - 1) + calculate_fibonacci(n - 2);
}

const result = calculate_fibonacci(10);
console.log(`O sexto termo da sequência de Fibonacci é: ${result}`);
