package boundary;

import entity_interface.RoomInterface;
import entity_implementation.Room;
import manager.RoomManager;

import java.util.Scanner;

public class RoomUI {

    private final Scanner scanner;
    private final RoomManager roomManager;

    public RoomUI(RoomManager roomManager, Scanner scanner) {
        this.roomManager = roomManager;
        this.scanner = scanner;
    }

    public void start() {
        int choice;
        do {
            choice = getMenuChoice();
            switch (choice) {
                case 1 -> addRoom();
                case 2 -> removeRoom();
                case 3 -> listAllRooms();
                case 4 -> viewAvailableRooms();
                case 0 -> System.out.println("Returning to Main Menu...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private int getMenuChoice() {
        System.out.println("\n=== ROOM MENU ===");
        System.out.println("1. Add new room");
        System.out.println("2. Remove room");
        System.out.println("3. List all rooms");
        System.out.println("4. View available rooms");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
        return choice;
    }

    private void addRoom() {
        System.out.print("Enter Room ID: ");
        String roomID = scanner.nextLine();

        System.out.print("Enter Doctor Name: ");
        String doctorName = scanner.nextLine();

        boolean available = true; // By default, new room is available

        Room room = new Room(roomID, doctorName, available);

        if (roomManager.addRoom(room)) {
            System.out.println("Room added successfully!");
        } else {
            System.out.println("Error: Room ID already exists or max capacity reached.");
        }
    }

    private void removeRoom() {
        System.out.print("Enter Room ID to remove: ");
        String roomID = scanner.nextLine();
        if (roomManager.removeRoom(roomID)) {
            System.out.println("Room removed successfully!");
        } else {
            System.out.println("Room not found.");
        }
    }

    private void listAllRooms() {
        System.out.println("\n=== ALL ROOMS ===");
        roomManager.displayRooms();
    }

    private void viewAvailableRooms() {
        System.out.println("\n=== AVAILABLE ROOMS ===");
        RoomInterface room = roomManager.getAvailableRoom();
        if (room != null) {
            System.out.printf("Room ID: %s | Doctor: %s%n", room.getRoomID(), room.getDoctorName());
        } else {
            System.out.println("No rooms are currently available.");
        }
    }
}
