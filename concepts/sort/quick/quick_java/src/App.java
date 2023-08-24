import java.util.ArrayList;
import java.util.List;

public class App {
  public static void main(String[] args) throws Exception {
    List<Integer> array = new ArrayList<>(List.of(64, 34, 25, 12, 22, 11, 90));
    List<Integer> sortedArray = quickSort(array);
    System.out.println(sortedArray);
  }

  public static List<Integer> quickSort(List<Integer> arr) {
    if (arr.size() <= 1) {
      return arr;
    }

    int pivot = arr.get(arr.size() - 1);
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();

    for (int element : arr.subList(0, arr.size() - 1)) {
      if (element <= pivot) {
        left.add(element);
      } else {
        right.add(element);
      }
    }

    List<Integer> sortedLeft = quickSort(left);
    List<Integer> sortedRight = quickSort(right);

    List<Integer> sortedArray = new ArrayList<>();
    sortedArray.addAll(sortedLeft);
    sortedArray.add(pivot);
    sortedArray.addAll(sortedRight);

    return sortedArray;
  }

  public static void inPlaceQuickSort(int[] arr, int low, int high) {
    if (low < high) {
      int pivotIndex = partition(arr, low, high);

      inPlaceQuickSort(arr, low, pivotIndex - 1);
      inPlaceQuickSort(arr, pivotIndex + 1, high);
    }
  }

  public static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;

    for (int j = low; j < high; j++) {
      if (arr[j] <= pivot) {
        i++;
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }

    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;

    return i + 1;
  }
}
