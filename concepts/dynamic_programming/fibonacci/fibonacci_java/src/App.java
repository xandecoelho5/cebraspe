import java.util.HashMap;
import java.util.Map;

public class App {
  public static void main(String[] args) throws Exception {
    System.out.println(fibonacci(40));
    System.out.println(fib(40));
  }

  private static int fib(int n) {
    if (n == 0 || n == 1)
      return n;
    return fib(n - 1) + fib(n - 2);
  }

  private static Map<Integer, Integer> map = new HashMap<>() {
    {
      put(0, 0);
      put(1, 1);
    }
  };

  private static int fibonacci(int n) {
    if (map.containsKey(n))
      return map.get(n);
    map.put(n, fibonacci(n - 1) + fibonacci(n - 2));
    return map.get(n);
  }
}
