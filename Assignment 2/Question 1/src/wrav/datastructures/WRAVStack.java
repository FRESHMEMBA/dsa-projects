package wrav.datastructures;

public class WRAVStack {
    private Node stackTop;
    private int stackSize;

    public WRAVStack() {
        stackTop = null;
        stackSize = 0;
    }

    public boolean isEmptyStack() {
        return stackTop == null;
    }

    public void push(String newElement) {
        Node newNode = new Node(newElement);
        newNode.next = stackTop;
        stackTop = newNode;
        stackSize++;
    }

    public String peek() {
        return (isEmptyStack()) ? null : stackTop.cargo;
    }

    public String pop() {
        if (isEmptyStack()) return null;

        String topElement = peek();
        stackTop = stackTop.next;
        stackSize--;

        return topElement;
    }

    public int size() {
        return stackSize;
    }
}
