package DSA.linked_list_adt;

/**
 * Singly Linked List ADT
 */
public class SinglyLinkedList<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    // --- Insertion ---

    public void insertFront(T data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void insertEnd(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    public void insertAtPosition(T data, int pos) {
        if (pos < 0 || pos > size)
            return; // or throw exception

        if (pos == 0) {
            insertFront(data);
            return;
        }

        Node newNode = new Node(data);
        Node temp = head;
        for (int i = 0; i < pos - 1; i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    // --- Deletion ---

    public void deleteFront() {
        if (head == null)
            return;
        head = head.next;
        size--;
    }

    public void deleteEnd() {
        if (head == null)
            return;
        if (head.next == null) {
            head = null;
            size--;
            return;
        }

        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        size--;
    }

    // --- Traversal & Utility ---

    public void traverse() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public boolean search(T key) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.equals(key))
                return true;
            temp = temp.next;
        }
        return false;
    }

    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public int length() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
