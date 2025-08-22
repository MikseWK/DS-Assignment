package DAO;

import customizeADT.CustomizeADT;
import entity_interface.ConsultationInterface; // Import the interface
import java.io.*;

public class ConsultationDAO {
    private final String fileName = "consultations.dat";

    
    public void saveToFile(CustomizeADT<String, ConsultationInterface, ?> data) {
        try (ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            ooStream.writeObject(data);
        } catch (IOException ex) {
            System.err.println("ERROR saving consultations: " + ex.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
    public CustomizeADT<String, ConsultationInterface, ?> retrieveFromFile() {
        File file = new File(fileName);
        CustomizeADT<String, ConsultationInterface, ?> data = new CustomizeADT<>();
        if (file.exists()) {
            try (ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file))) {
                data = (CustomizeADT<String, ConsultationInterface, ?>) oiStream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println("ERROR retrieving consultations: " + ex.getMessage());
            }
        }
        return data;
    }
}
