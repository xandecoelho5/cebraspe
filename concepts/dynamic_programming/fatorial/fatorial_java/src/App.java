public class App {
  public static void main(String[] args) throws Exception {
    int n = 5;
    int sum = 1;
    for (int i = 2; i <= n; i++) {
      sum *= i;
    }
    System.out.println(sum);
  }
}
