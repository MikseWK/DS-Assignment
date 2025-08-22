package manager;

import customizeADT.CustomizeADT;
import entity_interface.RoomInterface; // Import the interface
import manager_interface.RoomManagerInterface;

public class RoomMaintenance implements RoomManagerInterface {
    private CustomizeADT<String, RoomInterface, ?> roomADT;

    // Constructor to accept pre-loaded data
    public RoomMaintenance(CustomizeADT<String, RoomInterface, ?> roomADT) {
        this.roomADT = roomADT;
    }

    
    public RoomMaintenance() {
        this(new CustomizeADT<>());
    }

    // Add a new room
    @Override
    public boolean addRoom(RoomInterface room) {
        if (roomADT.containsKey(room.getRoomID())) return false; // avoid duplicates
        roomADT.put(room.getRoomID(), room);
        return true;
    }

    // Get room by ID
    @Override
    public RoomInterface getRoom(String roomID) {
        return roomADT.get(roomID);
    }

    // Update room info
    @Override
    public boolean updateRoom(String roomID, RoomInterface updatedRoom) {
        if (!roomADT.containsKey(roomID)) return false;
        roomADT.put(roomID, updatedRoom);
        return true;
    }

    // Remove a room by ID
    @Override
    public boolean removeRoom(String roomID) {
        if (!roomADT.containsKey(roomID)) return false;
        roomADT.remove(roomID);
        return true;
    }

    // Display all rooms
    public void displayRooms() {
        if (roomADT.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        // Correctly handle the array conversion
        Object[] rawValues = roomADT.values();
        RoomInterface[] allRooms = new RoomInterface[rawValues.length];
        for(int i = 0; i < rawValues.length; i++) {
            allRooms[i] = (RoomInterface) rawValues[i];
        }

        System.out.println("==========================================================");
        System.out.printf("| %-5s | %-10s | %-20s | %-12s |\n",
                "No", "Room ID", "Assigned Doctor", "Availability");
        System.out.println("==========================================================");
        for (int i = 0; i < allRooms.length; i++) {
            RoomInterface r = allRooms[i];
            if (r != null) {
                System.out.printf("| %-5d | %-10s | %-20s | %-12s |\n",
                        (i + 1), r.getRoomID(), r.getDoctorName(), r.isAvailable() ? "Available" : "Occupied");
            }
        }
        System.out.println("==========================================================");
    }

    // Check if room exists
    @Override
    public boolean containsRoom(String roomID) {
        return roomADT.containsKey(roomID);
    }

    // Get total number of rooms
    @Override
    public int getRoomCount() {
        return roomADT.size();
    }

    @Override
    public CustomizeADT<String, RoomInterface, ?> getAllRooms() {
        return this.roomADT;
    }

     @Override
    public RoomInterface getAvailableRoom() {
        RoomInterface[] allRooms = roomADT.values();
        for (RoomInterface room : allRooms) {
            if (room != null && room.isAvailable()) {
                System.out.printf("Available Room: %-15s Doctor: %-15s%n",
                        room.getRoomID(), room.getDoctorName());
                return room;
            }
        }
        System.out.println("No available rooms at the moment.");
        return null;
    }

}
