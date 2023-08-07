import java.util.LinkedList;
import java.util.Queue;

public class FindPerimeter {

    private static Queue<Integer> myData = new LinkedList<>();

    public static void main(String[] args) {
        // Given queue myData: 12, 24, 36 (front is 12)
        Enqueue(myData, 48);
        Enqueue(myData, 60);
        Dequeue(myData);
        System.out.println(Peek(myData));
        System.out.println(IsEmpty(myData));
    }

    // Method to enqueue an element to the queue
    private static void Enqueue(Queue<Integer> queue, int element) {
        queue.add(element);
    }

    // Method to dequeue an element from the queue
    private static void Dequeue(Queue<Integer> queue) {
        queue.remove();
    }

    // Method to get the front element of the queue without removing it
    private static int Peek(Queue<Integer> queue) {
        return queue.peek();
    }

    // Method to check if the queue is empty
    private static boolean IsEmpty(Queue<Integer> queue) {
        return queue.isEmpty();
    }
}
