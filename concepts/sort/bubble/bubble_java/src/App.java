import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
  public static void main(String[] args) throws Exception {
    var array = new int[] { 60, 20, 54, 23, 12, 43, 23, 55 };
    System.out.println(Arrays.toString(array));
    sortArray(array);
    System.out.println(Arrays.toString(array));

    List<Integer> list = new ArrayList<Integer>(List.of(60, 20, 54, 23, 12, 43, 23, 55));
    System.out.println(list);
    sortList(list);
    System.out.println(list);
  }

  private static void sortArray(int[] array) {
    int temp;
    for (int i = array.length - 1; i >= 0; --i) {
      for (int j = i - 1; j >= 0; --j) {
        if (array[i] < array[j]) {
          temp = array[j];
          array[j] = array[i];
          array[i] = temp;
        }
      }
    }
  }

  private static void sortList(List<Integer> list) {
    int temp;
    for (int i = list.size() - 1; i >= 0; --i) {
      for (int j = i - 1; j >= 0; --j) {
        if (list.get(i) < list.get(j)) {
          temp = list.get(j);
          list.set(j, list.get(i));
          list.set(i, temp);
        }
      }
    }
  }
}
