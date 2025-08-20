package manager_interface;

import entity_interface.RoomInterface;

public interface RoomManagerInterface {
    boolean addRoom(RoomInterface room);

    RoomInterface getRoom(String roomID);

    boolean removeRoom(String roomID);

    void displayRooms();

    boolean containsRoom(String roomID);

    int getRoomCount();
    
    RoomInterface getAvailableRoom();
}
