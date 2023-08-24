import java.util.Arrays;

public class App {
  public static void main(String[] args) throws Exception {
    var array = new int[] { 60, 20, 54, 23, 12, 43, 23, 1 };
    selectionSort(array);
    System.out.println(Arrays.toString(array));
  }

  private static void selectionSort(int[] array) {
    for (int i = 0; i < array.length - 1; i++) {
      int minIndex = i;

      for (int j = i + 1; j < array.length; j++) {
        if (array[j] < array[minIndex]) {
          minIndex = j;
        }
      }

      int temp = array[i];
      array[i] = array[minIndex];
      array[minIndex] = temp;
    }
  }
}
