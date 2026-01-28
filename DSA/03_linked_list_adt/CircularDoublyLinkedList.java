package DSA.linked_list_adt;

/**
 * Circular Doubly Linked List ADT
 * 
 * Properties:
 * 1. Doubly Linked: Each node has prev and next pointers.
 * 2. Circular: The last node's next points to head, and head's prev points to
 * the last node.
 */
public class CircularDoublyLinkedList<T> {

    private class Node {
        T data;
        Node next;
        Node prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private int size;

    public CircularDoublyLinkedList() {
        head = null;
        size = 0;
    }

    // --- Insertion ---

    public void insertFront(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
        } else {
            Node tail = head.prev;
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
        }
        size++;
    }

    public void insertEnd(T data) {
        if (head == null) {
            insertFront(data);
            return;
        }
        Node newNode = new Node(data);
        Node tail = head.prev;

        newNode.next = head;
        newNode.prev = tail;
        tail.next = newNode;
        head.prev = newNode; // update head's prev to new tail
        size++;
    }

    public void insertAtPosition(T data, int pos) {
        if (pos < 0 || pos > size) {
            System.out.println("Invalid position");
            return;
        }

        if (pos == 0) {
            insertFront(data);
            return;
        }

        if (pos == size) {
            insertEnd(data);
            return;
        }

        Node newNode = new Node(data);
        Node current = head;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        // current is now the node at 'pos'. We want to insert BEFORE current.
        Node prevNode = current.prev;

        newNode.next = current;
        newNode.prev = prevNode;
        prevNode.next = newNode;
        current.prev = newNode;

        size++;
    }

    // --- Deletion ---

    public void deleteFront() {
        if (head == null) {
            return;
        }

        if (size == 1) {
            head = null;
        } else {
            Node tail = head.prev;
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
        size--;
    }

    public void deleteEnd() {
        if (head == null) {
            return;
        }

        if (size == 1) {
            head = null;
        } else {
            Node tail = head.prev;
            Node newTail = tail.prev;
            newTail.next = head;
            head.prev = newTail;
        }
        size--;
    }

    public void deleteAtPosition(int pos) {
        if (pos < 0 || pos >= size) {
            System.out.println("Invalid position");
            return;
        }

        if (pos == 0) {
            deleteFront();
            return;
        }

        if (pos == size - 1) {
            deleteEnd();
            return;
        }

        Node current = head;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }

        Node prevNode = current.prev;
        Node nextNode = current.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;
    }

    // --- Traversal & Utility ---

    public void traverse() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node temp = head;
        do {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(head)");
    }

    public void traverseReverse() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node tail = head.prev;
        Node temp = tail;
        do {
            System.out.print(temp.data + " <-> ");
            temp = temp.prev;
        } while (temp != tail);
        System.out.println("(tail)");
    }

    public boolean search(T key) {
        if (head == null)
            return false;
        Node temp = head;
        do {
            if (temp.data.equals(key))
                return true;
            temp = temp.next;
        } while (temp != head);
        return false;
    }

    public int length() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
