package adt;

public interface CustomizeADTInterface<K, V, T> {

    // ======================
    // Circular Queue operations
    // ======================
    void enqueue(T item);       // Add item to the rear (circular queue)
    T dequeue();                // Remove item from the front (circular queue)
    T peekQueue();              // View item at the front (circular queue)

    // ======================
    // Stack operations
    // ======================
    void push(T item);          // Add to top
    T pop();                    // Remove from top
    T peekStack();              // View top item

    // ======================
    // List operations
    // ======================
    void add(T item);              // Add to end
    void add(int index, T item);   // Add at index
    T get(int index);              // Get item at index
    T remove(int index);           // Remove item at index
    int indexOf(T item);           // Get index of item
    boolean contains(T item);      // OPTIONAL: Check if item exists in list
    void set(int index, T item);   // OPTIONAL: Replace item at index
    int lastIndexOf(T item);       // OPTIONAL: Get last index of item

    // ======================
    // Map operations
    // ======================
    void put(K key, V value);       // Add key-value pair
    V get(K key);                   // Get value by key
    boolean containsKey(K key);     // Check if key exists
    boolean containsValue(V value); // OPTIONAL: Check if value exists in map
    V remove(K key);                // Remove by key
    K[] keys();                     // OPTIONAL: Return array of all keys
    V[] values();                   // OPTIONAL: Return array of all values

    // ======================
    // Common utility
    // ======================
    boolean isEmpty();      // Check if empty
    int size();             // Get total size
    void clear();           // Clear all contents

    // ======================
    // Optional general methods
    // ======================
    T peek();                        // OPTIONAL: Generic peek depending on context
    T[] toArray();                   // OPTIONAL: Return all elements as an array
    void reverse();                  // OPTIONAL: Reverse element order (list or queue)

    // ======================
    // Debugging and representation
    // ======================
    void printAll();                 // OPTIONAL: Print all items (for debugging)
    @Override
    String toString();               // OPTIONAL: String representation of contents

    // ======================
    // Object methods
    // ======================
    CustomizeADT<K, V, T> clone();   // OPTIONAL: Deep copy of the ADT
    @Override
    boolean equals(Object obj);      // OPTIONAL: Check logical equality with another ADT
}
