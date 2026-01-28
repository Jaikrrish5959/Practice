package DSA.linked_list_adt;

public class CircularDLLTest {
    public static void main(String[] args) {
        System.out.println("Testing Circular Doubly Linked List...");
        CircularDoublyLinkedList<Integer> cdll = new CircularDoublyLinkedList<>();

        // Test Insertion
        System.out.println("\n--- Insertion Tests ---");
        cdll.insertFront(10);
        cdll.insertFront(5);
        cdll.insertEnd(20);
        cdll.insertEnd(30);
        cdll.insertAtPosition(15, 2); // Should be 5 <-> 10 <-> 15 <-> 20 <-> 30

        System.out.println("Forward Traversal (Expected: 5 <-> 10 <-> 15 <-> 20 <-> 30 <-> (head)):");
        cdll.traverse();

        System.out.println("Reverse Traversal (Expected: 30 <-> 20 <-> 15 <-> 10 <-> 5 <-> (tail)):");
        cdll.traverseReverse();

        System.out.println("Length: " + cdll.length()); // Expected: 5

        // Test Search
        System.out.println("\n--- Search Tests ---");
        System.out.println("Search 15: " + cdll.search(15)); // true
        System.out.println("Search 100: " + cdll.search(100)); // false

        // Test Deletion
        System.out.println("\n--- Deletion Tests ---");
        cdll.deleteFront(); // Removes 5
        System.out.println("After deleteFront (Expected: 10 <-> 15 <-> 20 <-> 30):");
        cdll.traverse();

        cdll.deleteEnd(); // Removes 30
        System.out.println("After deleteEnd (Expected: 10 <-> 15 <-> 20):");
        cdll.traverse();

        cdll.deleteAtPosition(1); // Removes 15 (index 1)
        System.out.println("After deleteAtPosition(1) (Expected: 10 <-> 20):");
        cdll.traverse();

        System.out.println("Reverse Traversal After Deletions:");
        cdll.traverseReverse();

        // Edge Cases
        System.out.println("\n--- Edge Case Tests ---");
        cdll.deleteFront();
        cdll.deleteFront();
        System.out.println("After deleting all (should be empty):");
        cdll.traverse();
        System.out.println("Is Empty? " + cdll.isEmpty());
    }
}
