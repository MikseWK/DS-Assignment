package DAO;

import customizeADT.CustomizeADT;
import entity_interface.DoctorInterface; // Import the interface
import java.io.*;

public class DoctorDAO {
    private final String fileName = "doctors.dat";

    // Change the parameter type here to accept the interface
    public void saveToFile(CustomizeADT<String, DoctorInterface, ?> data) {
        try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            ooStream.writeObject(data);
        } catch (IOException ex) {
            System.err.println("ERROR saving doctors: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public CustomizeADT<String, DoctorInterface, ?> retrieveFromFile() {
        File file = new File(fileName);
        CustomizeADT<String, DoctorInterface, ?> data = new CustomizeADT<>();
        if (file.exists()) {
            try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file))) {
                data = (CustomizeADT<String, DoctorInterface, ?>) oiStream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println("ERROR retrieving doctors: " + ex.getMessage());
            }
        }
        return data;
    }
}
