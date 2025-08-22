package DAO;

import customizeADT.CustomizeADT;
import entity_interface.RoomInterface; // Import the interface
import java.io.*;

public class RoomDAO {
    private final String fileName = "rooms.dat";

    // Change the parameter type here to accept the interface
    public void saveToFile(CustomizeADT<String, RoomInterface, ?> data) {
        try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            ooStream.writeObject(data);
        } catch (IOException ex) {
            System.err.println("ERROR saving rooms: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public CustomizeADT<String, RoomInterface, ?> retrieveFromFile() {
        File file = new File(fileName);
        CustomizeADT<String, RoomInterface, ?> data = new CustomizeADT<>();
        if (file.exists()) {
            try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file))) {
                data = (CustomizeADT<String, RoomInterface, ?>) oiStream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println("ERROR retrieving rooms: " + ex.getMessage());
            }
        }
        return data;
    }
}