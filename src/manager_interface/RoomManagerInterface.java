package manager_interface;

import customizeADT.CustomizeADT;
import entity_interface.RoomInterface; 

public interface RoomManagerInterface {
    
    boolean addRoom(RoomInterface room);
    RoomInterface getRoom(String roomID);
    boolean updateRoom(String roomID, RoomInterface updatedRoom);
    boolean removeRoom(String roomID);
    RoomInterface getAvailableRoom();
    boolean containsRoom(String roomID);
    int getRoomCount();
    CustomizeADT<String, RoomInterface, ?> getAllRooms();
}
