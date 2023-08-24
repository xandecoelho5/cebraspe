import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyQueue<T> {
  private List<T> list = new ArrayList<>();

  public void enqueue(T t) {
    list.add(t);
  }

  public T dequeue() {
    return list.remove(0);
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public String toString() {
    return Arrays.toString(list.toArray());
  }
}
