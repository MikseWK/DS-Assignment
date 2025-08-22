package dsa_asg_new;

import DAO.*;
import boundary.MainMenuUI;
import customizeADT.CustomizeADT;
import entity_implementation.*;
import entity_interface.*; // Import the interfaces package
import manager.*;

public class DSA_ASG_NEW {
    public static void main(String[] args) {
        
        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        RoomDAO roomDAO = new RoomDAO();
        ConsultationDAO consultationDAO = new ConsultationDAO();

        // Retrieve data from files using the INTERFACE type
        // This now matches the updated return type of your DAOs
        CustomizeADT<String, PatientInterface, ?> patients = patientDAO.retrieveFromFile();
        CustomizeADT<String, DoctorInterface, ?> doctors = doctorDAO.retrieveFromFile();
        CustomizeADT<String, RoomInterface, ?> rooms = roomDAO.retrieveFromFile();
        CustomizeADT<String, ConsultationInterface, ?> consultations = consultationDAO.retrieveFromFile();

        
        if (patients.isEmpty()) {
            System.out.println("No patient data found. Seeding initial data...");
            patients.put("P001", new Patient("P001", "Ali", 20, "M", "ali@web.com"));
            patients.put("P002", new Patient("P002", "Siti", 25, "F", "siti@web.com"));
        }
        if (doctors.isEmpty()) {
            System.out.println("No doctor data found. Seeding initial data...");
            doctors.put("D001", new Doctor("D001", "Dr. Alice", "Smith", "Cardiology", "smith@gmail.com", "0123456789"));
            doctors.put("D002", new Doctor("D002", "Dr. Bob", "Jones", "General", "bob@gmail.com", "9876543210"));
        }
        if (rooms.isEmpty()) {
            System.out.println("No room data found. Seeding initial data...");
            rooms.put("R001", new Room("R001", "Dr. Alice Smith", true));
            rooms.put("R002", new Room("R002", "Dr. Bob Jones", true));
        }

        
        PatientMaintenance patientManager = new PatientMaintenance(patients);
        DoctorMaintenance doctorManager = new DoctorMaintenance(doctors);
        RoomMaintenance roomManager = new RoomMaintenance(rooms);
        ConsultationMaintenance consultationManager = new ConsultationMaintenance(consultations);

        // Pass managers to UI
        MainMenuUI mainMenuUI = new MainMenuUI(patientManager, doctorManager, roomManager, consultationManager);

       
        mainMenuUI.start();
    }
}
