import java.util.Arrays;

public class App {
  public static void main(String[] args) throws Exception {
    var arr = new int[] { 60, 20, 54, 23, 12, 43, 23, 55 };
    insertionSort(arr);
    System.out.println(Arrays.toString(arr));
  }

  private static void insertionSort(int[] arr) {
    for (int i = 1, j, key; i < arr.length; i++) {
      key = arr[i];
      j = i - 1;
      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j--];
      }
      arr[j + 1] = key;
    }
  }
}
