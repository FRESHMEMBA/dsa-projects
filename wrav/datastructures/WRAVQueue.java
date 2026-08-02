package wrav.datastructures;

public class WRAVQueue {
    private Node queueFront, queueRear;
    private int queueSize;

    public WRAVQueue() {
        queueFront = null;
        queueRear = null;
        queueSize = 0;
    }

    public boolean isEmptyQueue() {
        return queueFront == null;
    }

    public String peek() {
        return (isEmptyQueue()) ? null : queueFront.cargo;
    }

    public void enqueue(String newElement) {
        Node newNode = new Node(newElement);

        if (isEmptyQueue()) {
            queueFront = newNode;
            queueRear = newNode;
        } else {
            newNode.next = queueRear;
            queueRear = newNode;
        }

        queueSize++;
    }

    public String dequeue() {
        if (isEmptyQueue()) return null;

        String topElement = peek();
        queueFront = queueFront.next;
        queueSize--;

        return topElement;
    }

    public int size() {
        return queueSize;
    }
}
