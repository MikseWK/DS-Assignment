package dsa_asg_new;

import boundary.MainMenuUI;
import manager.*;

public class DSA_ASG_NEW {
    public static void main(String[] args) {
        // Managers (each contains its own ADT)
        PatientManager patientManager = new PatientManager();
        DoctorManager doctorManager = new DoctorManager();
        RoomManager roomManager = new RoomManager();
        ConsultationManager consultationManager = new ConsultationManager();

        // Seed sample data (optional, for testing)
        patientManager.addPatient(new entity_implementation.Patient("P001", "Ali", 20, "M", "leowkaizhen@gmail.com"));
        patientManager.addPatient(new entity_implementation.Patient("P002", "Abu", 21, "M", "abu@gmail.com"));
        patientManager.addPatient(new entity_implementation.Patient("P003", "Akau", 22, "M", "akau@yahoo.com"));

        doctorManager.addDoctor(new entity_implementation.Doctor("D001", "Alice", "Smith", "Cardiology", "alice@example.com", "012-3456789"));
        doctorManager.addDoctor(new entity_implementation.Doctor("D002", "Bob", "Johnson", "Orthopedics", "bob@example.com", "013-9876543"));
        doctorManager.addDoctor(new entity_implementation.Doctor("D003", "Charlie", "Lee", "Dermatology", "charlie@example.com", "014-5678901"));

        roomManager.addRoom(new entity_implementation.Room("R001", "Dr. Alice", true));
        roomManager.addRoom(new entity_implementation.Room("R002", "Dr. Bob", true));
        roomManager.addRoom(new entity_implementation.Room("R003", "Dr. Charlie", true));

        // Pass managers into MainMenuUI
        MainMenuUI mainMenuUI = new MainMenuUI(patientManager, doctorManager, roomManager, consultationManager);

        // Start application
        mainMenuUI.start();
    }
}
