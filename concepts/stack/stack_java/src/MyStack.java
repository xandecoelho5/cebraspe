import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyStack<T> {
  private List<T> list = new ArrayList<>();

  public void push(T t) {
    list.add(t);
  }

  public T pop() {
    return list.remove(list.size() - 1);
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }

  @Override
  public String toString() {
    return Arrays.toString(list.toArray());
  }
}
