public class App {
  public static void main(String[] args) throws Exception {
    MyLinkedList<Integer> myList = new MyLinkedList<>();

    myList.insertAtBeginning(1);
    myList.insertAtEnd(5);
    myList.display();
  }
}
