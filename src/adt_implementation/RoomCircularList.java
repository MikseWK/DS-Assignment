package adt_implementation;

import adt.RoomADT;
import adt.RoomCircularListADT;

public class RoomCircularList implements RoomCircularListADT {

    private class Node {
        RoomADT data;
        Node next;

        public Node(RoomADT data) {
            this.data = data;
        }
    }

    private Node last;
    private int size;

    public RoomCircularList() {
        last = null;
        size = 0;
    }

    // Adds a new room (available, no patient assigned)
    @Override
    public void add(RoomADT room) {
        Node newNode = new Node(room);
        if (last == null) {
            newNode.next = newNode;
            last = newNode;
        } else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    // Get the room at the front of the circular list
    @Override
    public RoomADT getFront() {
        if (last == null) return null;
        return last.next.data;
    }

    // Assigns a room to a patient if available and roomID matches
    @Override
    public RoomADT assignRoomByID(String roomID, String patientID) {
        if (isEmpty()) return null;

        Node current = last.next;
        do {
            if (current.data.getRoomID().equalsIgnoreCase(roomID)) {
                if (current.data.isAvailable()) {
                    current.data.setAvailable(false);
                    current.data.setPatientID(patientID);
                    return current.data;
                } else {
                    System.out.println("Room " + roomID + " is currently not available.");
                    return null;
                }
            }
            current = current.next;
        } while (current != last.next);

        System.out.println("Room " + roomID + " not found.");
        return null;
    }

    @Override
    public RoomADT getRoomByPatientID(String patientID) {
        if (isEmpty()) return null;

        Node current = last.next;
        do {
            if (patientID.equalsIgnoreCase(current.data.getPatientID())) {
                return current.data;
            }
            current = current.next;
        } while (current != last.next);

        return null;
    }

    // Get any available room (first found)
    @Override
    public RoomADT getAvailableRoom() {
        if (isEmpty()) return null;

        Node current = last.next;
        do {
            if (current.data.isAvailable()) {
                return current.data;
            }
            current = current.next;
        } while (current != last.next);

        return null;
    }

    // Print all rooms with doctor, availability, and assigned patient
    @Override
    public void traverseAndPrint() {
        if (isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        Node current = last.next;
        do {
            System.out.println(current.data.getRoomID() + " - Doctor: " +
                               current.data.getDoctorName() +
                               " | Available: " + current.data.isAvailable() +
                               " | Patient: " + current.data.getPatientID());
            current = current.next;
        } while (current != last.next);
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
}
