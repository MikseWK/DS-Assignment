package adt_implementation;

import adt.CustomizeADT;

public class ConsultationQueue implements ConsultationQueueADT {

    private class Node {
        PatientADT data;
        Node next;

        public Node(PatientADT data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node last;  // Points to the last node (tail)
    private int size;

    public ConsultationQueue() {
        last = null;
        size = 0;
    }

    @Override
    public void enqueue(PatientADT patient) {
        Node newNode = new Node(patient);
        if (isEmpty()) {
            newNode.next = newNode;  // Circular reference
            last = newNode;
        } else {
            newNode.next = last.next;  // newNode points to front
            last.next = newNode;       // last points to newNode
            last = newNode;            // update last
        }
        size++;
    }

    @Override
    public PatientADT dequeue() {
        if (isEmpty()) {
            return null;
        }

        Node front = last.next;
        
        if (last == front) {
            // Only one node
            last = null;
        } else {
            last.next = front.next;
        }

        size--;
        return front.data;
    }

    @Override
    public PatientADT peek() {
        if (isEmpty()) {
            return null;
        }
        return last.next.data;  // Front of queue
    }

    @Override
    public boolean isEmpty() {
        return last == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        last = null;
        size = 0;
    }
    
    @Override
    public void traverseAndPrint() {
        if (isEmpty()) {
            System.out.println("The consultation queue is empty.");
            return;
        }

        System.out.println("Current Consultation Queue:");
        Node current = last.next; // Start from front
        int count = 1;

        do {
            PatientADT patient = current.data;
            System.out.println(count++ + ". Patient ID: " + patient.getPatientID() +
                               ", Name: " + patient.getName());
            current = current.next;
        } while (current != last.next); 
    }
}
