import java.util.Stack;

public class myClass {
    public static void main(String[] args) {
            Stack<Integer> myData = new Stack<>();
            myData.push(34);
            myData.push(56);
            myData.push(78);
            myData.push(12);
            myData.push(66);

            // Push 43 to the stack
            myData.push(43);

            // Pop two elements from the stack
            myData.pop();
            myData.pop();

            // Print the top element (Peek)
            System.out.println("Top element after pops: " + myData.peek());

            // Pop one more element from the stack
            myData.pop();

            // Print the top element again
            System.out.println("Top element after another pop: " + myData.peek());
        }
    }