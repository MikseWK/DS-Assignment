package customizeADT;

import customizeADT.CustomizeADTInterface;

public class CustomizeADT<K, V, T> implements CustomizeADTInterface<K, V, T> {

    private class Node {
        T data;
        Node next;
        Node(T data) { this.data = data; }
    }

    // Inside CustomizeADT class
    private class MapEntry {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    
    @SuppressWarnings("unchecked")
    public CustomizeADT() {
        queueArray = (T[]) new Object[100];
        mapEntries = (MapEntry[]) new CustomizeADT.MapEntry[100];
        head = tail = null;
        size = 0;
        queueSize = 0;
        front = 0;
        rear = -1;
        mapSize = 0;
    }
    
    private T[] queueArray;
    private int front = 0;
    private int rear = -1;
    private int queueSize = 0;

    private Node head, tail;
    private int size;
    private MapEntry[] mapEntries;
    private int mapSize;

    // ======================
    // Queue operations
    // ======================
    @Override
    public void enqueue(T item) {
        if (queueSize == queueArray.length) {
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % queueArray.length;
        queueArray[rear] = item;
        queueSize++;
        size++;  // maintain overall ADT size
    }


    @Override
    public T dequeue() {
        if (queueSize == 0) return null;
        T item = queueArray[front];
        queueArray[front] = null;
        front = (front + 1) % queueArray.length;
        queueSize--;
        size--;  // maintain overall ADT size
        return item;
    }


    @Override
    public T peekQueue() {
        return queueSize == 0 ? null : queueArray[front];
    }


    // ======================
    // Stack operations
    // ======================
    @Override
    public void push(T item) {
        Node newNode = new Node(item);
        newNode.next = head;
        head = newNode;
        if (tail == null) tail = head;
        size++;
    }

    @Override
    public T pop() {
        if (head == null) return null;
        T data = head.data;
        head = head.next;
        if (head == null) tail = null;
        size--;
        return data;
    }

    @Override
    public T peekStack() {
        return head == null ? null : head.data;
    }

    // ======================
    // List operations
    // ======================
    @Override
    public void add(T item) {
        Node newNode = new Node(item);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) return;
        Node newNode = new Node(item);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (tail == null) tail = head;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) current = current.next;
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) tail = newNode;
        }
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        Node current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.data;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) return null;
        T removed;
        if (index == 0) {
            removed = head.data;
            head = head.next;
            if (head == null) tail = null;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) current = current.next;
            removed = current.next.data;
            current.next = current.next.next;
            if (current.next == null) tail = current;
        }
        size--;
        return removed;
    }

    @Override
    public int indexOf(T item) {
        Node current = head;
        for (int i = 0; current != null; i++, current = current.next)
            if (current.data.equals(item)) return i;
        return -1;
    }

    @Override
    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size) return;
        Node current = head;
        for (int i = 0; i < index; i++) current = current.next;
        current.data = item;
    }

    @Override
    public int lastIndexOf(T item) {
        int lastIndex = -1;
        Node current = head;
        for (int i = 0; current != null; i++, current = current.next)
            if (current.data.equals(item)) lastIndex = i;
        return lastIndex;
    }

    // ======================
    // Map operations
    // ======================
    @Override
    public void put(K key, V value) {
        for (int i = 0; i < mapSize; i++) {
            if (mapEntries[i].getKey().equals(key)) {
                mapEntries[i].setValue(value);
                return; // Existing key — no need to increase size
            }
        }
        mapEntries[mapSize++] = new MapEntry(key, value);
        size++; 
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < mapSize; i++)
            if (mapEntries[i].key.equals(key)) return mapEntries[i].value;
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < mapSize; i++)
            if (mapEntries[i].value.equals(value)) return true;
        return false;
    }

    @Override
    public V remove(K key) {
        for (int i = 0; i < mapSize; i++) {
            if (mapEntries[i].getKey().equals(key)) {
                V removedValue = mapEntries[i].getValue();
                // Shift remaining entries
                for (int j = i; j < mapSize - 1; j++) {
                    mapEntries[j] = mapEntries[j + 1];
                }
                mapEntries[--mapSize] = null;
                size--; // ✅ Key was removed — decrease size
                return removedValue;
            }
        }
        return null; // Key not found — nothing to remove
    }

    @Override
    public K[] keys() {
        K[] keys = (K[]) new Object[mapSize];
        for (int i = 0; i < mapSize; i++) keys[i] = mapEntries[i].key;
        return keys;
    }

    @Override
    public V[] values() {
        V[] vals = (V[]) new Object[mapSize];
        for (int i = 0; i < mapSize; i++) vals[i] = mapEntries[i].value;
        return vals;
    }

    // ======================
    // Common utility
    // ======================
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
        front = 0;
        rear = -1;
        queueSize = 0;
        mapSize = 0;
        for (int i = 0; i < queueArray.length; i++) queueArray[i] = null;
        for (int i = 0; i < mapEntries.length; i++) mapEntries[i] = null;
    }

    // ======================
    // Optional general methods
    // ======================
    @Override
    public T peek() {
        return peekQueue();
    }

    @Override
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.data;
            current = current.next;
        }
        return arr;
    }

    @Override
    public void reverse() {
        Node prev = null, current = head;
        tail = head;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    // ======================
    // Debugging and representation
    // ======================
    @Override
    public void printAll() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // ======================
    // Object methods
    // ======================
    @SuppressWarnings("unchecked")
    @Override
    public CustomizeADT<K, V, T> clone() {
        CustomizeADT<K, V, T> copy = new CustomizeADT<>();

        Node current = head;
        while (current != null) {
            copy.add(current.data); // reuses your add(T) method
            current = current.next;
        }

        for (int i = 0; i < mapSize; i++) {
            copy.put((K) mapEntries[i].getKey(), (V) mapEntries[i].getValue());
        }

        for (int i = 0; i < queueSize; i++) {
            int index = (front + i) % queueArray.length;
            copy.enqueue(queueArray[index]);
        }

        return copy;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (!(obj instanceof CustomizeADT)) return false;
        CustomizeADT<K, V, T> other = (CustomizeADT<K, V, T>) obj;
        if (this.size != other.size || this.mapSize != other.mapSize) return false;
        Node curr1 = this.head, curr2 = other.head;
        while (curr1 != null && curr2 != null) {
            if (!curr1.data.equals(curr2.data)) return false;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        for (int i = 0; i < mapSize; i++) {
            V val1 = mapEntries[i].value;
            V val2 = other.get(mapEntries[i].key);
            if (val1 == null && val2 != null) return false;
            if (val1 != null && !val1.equals(val2)) return false;
        }
        return true;
    }
}
