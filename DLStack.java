public class DLStack<T> implements DLStackADT<T> {
    private DoubleLinkedNode<T> top;
    private int numItems;

    public DLStack() {
        top = null; // initilize top stack to null
        numItems = 0; /// initilize numItems to 0
    }

    public void push(T dataItem) { //used to push a new item to the top of the stack
        DoubleLinkedNode<T> newNode = new DoubleLinkedNode<>(dataItem); // creates new node for dataItem
        newNode.setNext(top);  //sets the next link of new node to current top node
        if (top != null) {
            top.setPrevious(newNode); //sets the previous link
        }
        top = newNode; // updates top of stack
        numItems++; // increments
    }

    public T pop() throws EmptyStackException { // pop and remove the data item from top of stack
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty."); //uses custom emptyStackException to throw if empty
        }
        T data = top.getElement(); // gets top element
        if (data == null) { // if data is null throw nullPointerException (had to put this for Test3 to pass but causes test4 to fail)
            throw new NullPointerException("Top element is null.");
        }
        top = top.getNext();
        if (top != null) {
            top.setPrevious(null);
        }
        numItems--;
        return data;
    }

    public T pop(int k) throws InvalidItemException {
        if (k <= 0 || k > numItems) {
            throw new InvalidItemException("Invalid item position. Received position: " + k + ", Stack size: " + numItems);
        }

        T data = null; // Default value for reference types (e.g., null)
        int counter = 0; // Counter to track the pop position

        // Start popping from the top of the stack
        DoubleLinkedNode<T> current = top;
        DoubleLinkedNode<T> previous = null;

        while (current != null && counter < k) { // loop until the kth node or end of stack
            previous = current; /// update previous
            current = current.getNext(); // move to next node
            counter++; /// increment counter
        }

        if (counter == k) { // Pop position found
            data = previous.getElement();
            if (previous != null) {
                previous.setNext(current);
            }
            if (current != null) {
                current.setPrevious(previous);
            }
            if (previous == top) { // if the popped node was at the top of the stack
                top = current; // set the next node as the top
                if (top != null) {
                    top.setPrevious(null); // ensure that the previous field of the new top node is null
                }
            }
            numItems--; // decrease the size of the stack
        }

        return data; // /return the data (null if pop was not successful)
    }
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty.");
        }
        T data = top.getElement();
        if (data == null) {
            throw new NullPointerException("Top element is null.");
        }
        return data;
    }

    public boolean isEmpty() {
        return numItems == 0;
    }

    public int size() {
        return numItems;
    }

    public DoubleLinkedNode<T> getTop() { // returns the top of the stack
        return top; // returns the top node
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        DoubleLinkedNode<T> node = top; // start from the top of the stack
        while (node != null) { // loop until the end of the stack
            sb.append(node.getElement().toString()); // append the string representation of the node's element
            node = node.getNext(); // move to the next node
            if (node != null) { // if there's another node, add a space
                sb.append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}