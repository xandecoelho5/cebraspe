public class MyLinkedList<T> {

  private int length;
  private Node<T> head;

  public MyLinkedList() {
    head = null;
  }

  public void insertAtBeginning(T data) {
    Node<T> newNode = new Node<>(data);
    newNode.next = head;
    head = newNode;
  }

  public void insertAtEnd(T data) {
    Node<T> newNode = new Node<>(data);
    if (head == null) {
      head = newNode;
      return;
    }
    Node<T> current = head;
    while (current.next != null) {
      current = current.next;
    }
    current.next = newNode;
  }

  public void removeFromBeginning() {
    if (head != null) {
      head = head.next;
    }
  }

  public void removeFromEnd() {
    if (head == null || head.next == null) {
      head = null;
      return;
    }

    Node<T> current = head;
    while (current.next.next != null) {
      current = current.next;
    }
    current.next = null;
  }

  public void remove(T data) {
    if (head == null) {
      return;
    }
    if (head.data.equals(data)) {
      head = head.next;
      return;
    }
    Node<T> current = head;
    while (current.next != null && !current.next.data.equals(data)) {
      current = current.next;
    }
    if (current.next != null) {
      current.next = current.next.next;
    }
  }

  public boolean isEmpty() {
    return false;
  }

  public int getSize() {
    return 0;
  }

  public void display() {
    Node<T> current = head;
    while (current != null) {
      System.out.print(current.data + "->");
      current = current.next;
    }
    System.out.println();
  }
}
