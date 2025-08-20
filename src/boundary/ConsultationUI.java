package boundary;

import entity_implementation.Consultation;
import entity_interface.ConsultationInterface;
import entity_interface.PatientInterface;
import entity_interface.DoctorInterface;
import entity_interface.RoomInterface;
import manager.ConsultationManager;
import manager.PatientManager;
import manager.DoctorManager;
import manager.RoomManager;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsultationUI {

    private final Scanner scanner;
    private final ConsultationManager consultationManager;
    private final PatientManager patientManager;
    private final DoctorManager doctorManager;
    private final RoomManager roomManager;

    public ConsultationUI(ConsultationManager consultationManager,
                          PatientManager patientManager,
                          DoctorManager doctorManager,
                          RoomManager roomManager,
                          Scanner scanner) {
        this.consultationManager = consultationManager;
        this.patientManager = patientManager;
        this.doctorManager = doctorManager;
        this.roomManager = roomManager;
        this.scanner = scanner;
    }

    public void start() {
        int choice;
        do {
            choice = getMenuChoice();
            switch (choice) {
                case 1 -> addConsultation();
                case 2 -> setFollowUp();
                case 3 -> listAllConsultations();
                case 4 -> searchByPatientID();
                case 5 -> searchByDoctorID();
                case 0 -> System.out.println("Exiting Consultation Menu...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private int getMenuChoice() {
        System.out.println("\n=== CONSULTATION MENU ===");
        System.out.println("1. Add new consultation");
        System.out.println("2. Set follow-up appointment");
        System.out.println("3. List all consultations");
        System.out.println("4. Search consultations by patient ID");
        System.out.println("5. Search consultations by doctor ID");
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

    private void addConsultation() {
        System.out.println("\n=== ADD CONSULTATION ===");

        // Patient validation
        System.out.print("Enter patient ID: ");
        String patientID = scanner.nextLine();
        PatientInterface patient = patientManager.getPatient(patientID);
        if (patient == null) {
            System.out.println("Patient not found.");
            return;
        }

        // Doctor validation
        System.out.print("Enter doctor ID: ");
        String doctorID = scanner.nextLine();
        DoctorInterface doctor = doctorManager.getDoctor(doctorID);
        if (doctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        // Diagnosis
        System.out.print("Enter diagnosis: ");
        String diagnosis = scanner.nextLine();

        // Room validation
        System.out.print("Enter room ID: ");
        String roomID = scanner.nextLine();
        RoomInterface room = roomManager.getRoom(roomID);
        if (room == null || !room.isAvailable()) {
            System.out.println("Room not available.");
            return;
        }
        room.setAvailable(false);
        room.setPatientID(patientID);

        // Generate consultation ID
        ConsultationInterface lastConsult = consultationManager.getLastConsultation();
        String newCID = "C001";
        if (lastConsult != null) {
            int num = Integer.parseInt(lastConsult.getConsultationID().substring(1)) + 1;
            newCID = String.format("C%03d", num);
        }

        Consultation consultation = new Consultation(newCID, patientID, doctorID, diagnosis, LocalDate.now(), roomID);
        if (consultationManager.addConsultation(patientID, consultation)) {
            System.out.println("Consultation added successfully! ID: " + newCID);
        } else {
            System.out.println("Failed to add consultation.");
        }
    }

    private void setFollowUp() {
        System.out.print("Enter consultation ID to set follow-up: ");
        String cid = scanner.nextLine();
        ConsultationInterface consultation = consultationManager.getConsultation(cid);
        if (consultation == null) {
            System.out.println("Consultation not found.");
            return;
        }

        System.out.print("Enter follow-up date (yyyy-mm-dd): ");
        String dateStr = scanner.nextLine();
        try {
            LocalDate date = LocalDate.parse(dateStr);
            if (consultationManager.setFollowUp(cid, date)) {
                System.out.println("Follow-up date set successfully.");
            } else {
                System.out.println("Failed to set follow-up.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format.");
        }
    }

    private void listAllConsultations() {
        System.out.println("\n=== ALL CONSULTATIONS ===");
        consultationManager.displayConsultationList();
    }

    private void searchByPatientID() {
        System.out.print("Enter patient ID: ");
        String patientID = scanner.nextLine();
        ConsultationInterface[] results = consultationManager.getConsultationsByPatientID(patientID);
        if (results.length == 0) {
            System.out.println("No consultations found for this patient.");
        } else {
            for (ConsultationInterface c : results) {
                System.out.println(c);
            }
        }
    }

    private void searchByDoctorID() {
        System.out.print("Enter doctor ID: ");
        String doctorID = scanner.nextLine();
        ConsultationInterface[] results = consultationManager.getConsultationsByDoctorID(doctorID);
        if (results.length == 0) {
            System.out.println("No consultations found for this doctor.");
        } else {
            for (ConsultationInterface c : results) {
                System.out.println(c);
            }
        }
    }
}
