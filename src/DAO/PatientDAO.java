package DAO;

import customizeADT.CustomizeADT;
import entity_interface.PatientInterface; // Import the interface
import java.io.*;

public class PatientDAO {
    private final String fileName = "patients.dat";

    // Change the parameter type here to accept the interface
    public void saveToFile(CustomizeADT<String, PatientInterface, ?> data) {
        try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            ooStream.writeObject(data);
        } catch (IOException ex) {
            System.err.println("ERROR saving patients: " + ex.getMessage());
        }
    }

    // This method should already be updated, but ensure it matches
    @SuppressWarnings("unchecked")
    public CustomizeADT<String, PatientInterface, ?> retrieveFromFile() {
        File file = new File(fileName);
        CustomizeADT<String, PatientInterface, ?> data = new CustomizeADT<>();
        if (file.exists()) {
            try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file))) {
                data = (CustomizeADT<String, PatientInterface, ?>) oiStream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println("ERROR retrieving patients: " + ex.getMessage());
            }
        }
        return data;
    }
}
