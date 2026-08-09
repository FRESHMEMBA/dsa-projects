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
            queueRear.next = newNode;
            queueRear = newNode;
        }

        queueSize++;
    }

    public String dequeue() {
        if (isEmptyQueue()) return null;

        String topElement = peek();
        queueFront = queueFront.next;

        if (queueFront == null) queueRear = null;

        queueSize--;

        return topElement;
    }

    public int size() {
        return queueSize;
    }

    @Override
    public String toString() {
        String s = "";
        Node temp = queueFront;

        while (temp != null) {
            s += temp.cargo + " ";
            temp = temp.next;
        }

        return s;
    }
}
