import customizeADT.CustomizeADT;

public class CustomizeADTTest {
    public static void main(String[] args) {
        CustomizeADT<String, Integer, String> adt = new CustomizeADT<>();

        // ===== Queue Tests =====
        System.out.println("====== QUEUE TEST ======");
        adt.enqueue("Alice");
        adt.enqueue("Bob");
        adt.enqueue("Charlie");
        System.out.println("Peek Queue: " + adt.peekQueue());         // Expected: Alice
        System.out.println("Dequeue: " + adt.dequeue());              // Expected: Alice
        System.out.println("Peek after Dequeue: " + adt.peekQueue()); // Expected: Bob

        // ===== Stack Tests =====
        System.out.println("\n====== STACK TEST ======");
        adt.push("David");
        adt.push("Eve");
        adt.push("Frank");
        System.out.println("Peek Stack: " + adt.peekStack());         // Expected: Frank
        System.out.println("Pop: " + adt.pop());                      // Expected: Frank
        System.out.println("Peek after Pop: " + adt.peekStack());     // Expected: Eve

        // ===== List Tests =====
        System.out.println("\n====== LIST TEST ======");
        adt.add("Gina");
        adt.add("Hank");
        adt.add(2, "Ivy"); // Insert Ivy at index 2
        System.out.println("Item at index 2: " + adt.get(2));         // Expected: Ivy
        System.out.println("Contains 'Eve'? " + adt.contains("Eve")); // Expected: true
        adt.set(1, "Harry");
        System.out.println("Item at index 1 after set: " + adt.get(1)); // Expected: Harry
        System.out.println("Index of 'Gina': " + adt.indexOf("Gina")); // Expected: 0 or 1 depending on list state
        System.out.println("Last index of 'Eve': " + adt.lastIndexOf("Eve")); // Expected: last position of Eve
        System.out.println("Removed index 2: " + adt.remove(2));      // Expected: Ivy

        // ===== Map Tests =====
        System.out.println("\n====== MAP TEST ======");
        adt.put("Math", 95);
        adt.put("Science", 88);
        adt.put("English", 78);
        System.out.println("Get 'Science': " + adt.get("Science"));       // Expected: 88
        System.out.println("Contains key 'Math'? " + adt.containsKey("Math"));   // Expected: true
        System.out.println("Contains value 100? " + adt.containsValue(100));     // Expected: false
        adt.remove("English");

        // ===== Utility Methods =====
        System.out.println("\n====== UTILITY METHODS ======");
        System.out.println("Size: " + adt.size());                    // Expected: total elements
        System.out.println("Is empty? " + adt.isEmpty());            // Expected: false
        adt.reverse(); // Reverses the list/stack part only
        System.out.println("Reversed (list/stack only): " + adt.toString());

        // ===== Print All Structures =====
        System.out.println("\n====== PRINT ALL ======");
        adt.printAll();

        // ===== Clone & Equals =====
        System.out.println("\n====== CLONE & EQUALS ======");
        CustomizeADT<String, Integer, String> clone = adt.clone();
        System.out.println("Original: " + adt.toString());
        System.out.println("Clone:    " + clone.toString());
        System.out.println("Are equal? " + adt.equals(clone));       // Expected: true

        // ===== Clear ADT =====
        System.out.println("\n====== AFTER CLEAR ======");
        adt.clear();
        System.out.println("Is empty? " + adt.isEmpty());            // Expected: true
        adt.printAll();
    }
}
