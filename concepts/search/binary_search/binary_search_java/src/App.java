public class App {
  public static void main(String[] args) throws Exception {
    System.out.println(binarySearch(new int[] { -1, 0, 3, 5, 9, 12 }, 9));
    System.out.println(binarySearch(new int[] { -1, 0, 3, 5, 9, 12 }, 12));
    System.out.println(binarySearch(new int[] { -1, 0, 3, 5, 9, 12 }, 2));
  }

  public static int binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (nums[mid] == target)
        return mid;
      if (nums[mid] < target)
        left = mid + 1;
      else
        right = mid - 1;
    }
    return -1;
  }
}
