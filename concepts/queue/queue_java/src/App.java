public class App {
  public static void main(String[] args) throws Exception {
    var myQueue = new MyQueue<Integer>();
    myQueue.enqueue(1);
    myQueue.enqueue(6);
    myQueue.enqueue(2);
    myQueue.enqueue(3);
    System.out.println(myQueue);
    System.out.println(myQueue.dequeue());
    System.out.println(myQueue.dequeue());
    System.out.println(myQueue.dequeue());
    System.out.println(myQueue.dequeue());
  }
}
