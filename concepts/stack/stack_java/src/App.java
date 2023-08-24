import java.util.Stack;

public class App {
  public static void main(String[] args) throws Exception {
    var myStack = new MyStack<Integer>();

    myStack.push(1);
    myStack.push(6);
    myStack.push(2);
    myStack.push(3);
    System.out.println(myStack);
    System.out.println(myStack.pop());
    System.out.println(myStack.pop());
    System.out.println(myStack.pop());
    System.out.println(myStack.pop());

    var stack = new Stack<Integer>();
    stack.push(1);
    stack.push(6);
    stack.push(2);
    stack.push(3);
    System.out.println(stack);
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
    System.out.println(stack.pop());
  }
}
