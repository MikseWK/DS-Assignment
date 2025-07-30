package adt;

public interface RoomCircularListADT {
    void add(RoomADT room);
    RoomADT getFront();
    RoomADT assignRoomByID(String roomID, String patientID);
    RoomADT getRoomByPatientID(String patientID);
    RoomADT getAvailableRoom();
    void traverseAndPrint();
    boolean isEmpty();
    int size();
    void clear();
}
