package manager;

import customizeADT.CustomizeADT;
import entity_interface.RoomInterface;
import manager_interface.RoomManagerInterface;

public class RoomManager implements RoomManagerInterface {

    private CustomizeADT<String, RoomInterface, RoomInterface> roomADT;

    public RoomManager() {
        roomADT = new CustomizeADT<>();
    }

    @Override
    public boolean addRoom(RoomInterface room) {
        if (roomADT.containsKey(room.getRoomID())) return false; // avoid duplicates
        roomADT.put(room.getRoomID(), room);
        return true;
    }

    @Override
    public RoomInterface getRoom(String roomID) {
        return roomADT.get(roomID);
    }

    @Override
    public boolean removeRoom(String roomID) {
        if (!roomADT.containsKey(roomID)) return false;
        roomADT.remove(roomID);
        return true;
    }

    @Override
    public void displayRooms() {
        if (roomADT.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        RoomInterface[] allRooms = roomADT.values();
        System.out.printf("%-5s %-15s %-20s %-10s %-15s%n", "No.", "Room ID", "Doctor", "Available", "Patient ID");
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < allRooms.length; i++) {
            RoomInterface room = allRooms[i];
            if (room != null) {
                System.out.printf("%-5d %-15s %-20s %-10s %-15s%n",
                        i + 1,
                        room.getRoomID(),
                        room.getDoctorName(),
                        room.isAvailable() ? "Yes" : "No",
                        room.getPatientID() != null ? room.getPatientID() : "None");
            }
        }
    }

    @Override
    public boolean containsRoom(String roomID) {
        return roomADT.containsKey(roomID);
    }

    @Override
    public int getRoomCount() {
        return roomADT.size();
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
