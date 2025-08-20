package boundary;

import entity_implementation.Patient;
import entity_interface.PatientInterface;
import java.util.Scanner;
import manager.PatientMaintenance;

public class PatientUI {

    private final Scanner scanner;
    private final PatientMaintenance patientManager;

    public PatientUI(PatientMaintenance patientManager, Scanner scanner) {
        this.patientManager = patientManager;
        this.scanner = scanner;
    }

    public void start() {
        int choice;
        do {
            choice = getMenuChoice();
            switch (choice) {
                case 1 -> addPatient();
                case 2 -> removePatient();
                case 3 -> listAllPatients();
                case 4 -> searchPatientByID();
                case 0 -> System.out.println("Exiting Patient Menu...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    private int getMenuChoice() {
        System.out.println("\n=== PATIENT MENU ===");
        System.out.println("1. Add new patient");
        System.out.println("2. Remove patient");
        System.out.println("3. List all patients");
        System.out.println("4. Search patient by ID");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");

        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {

        }
        return choice;
    }

    private void addPatient() {
        System.out.print("Enter patient ID (P###): ");
        String patientID = scanner.nextLine().trim();
        if (!patientID.matches("^[Pp]\\d{3}$")) {
            System.out.println("Invalid patient ID! Must start with 'P' followed by 3 digits.");
            return; // go back to menu
        } else if (patientManager.containsPatient(patientID)) {
            System.out.println("Patient ID already exists! Enter a different ID.");
            return; // go back to menu
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (!name.matches("^[A-Za-z ]+$")) {
            System.out.println("Invalid name! Only letters and spaces allowed.");
            return;
        }

        System.out.print("Enter age: ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine());
            if (age <= 0 || age >= 100) {
                System.out.println("Age must be greater than 0 and less than 100.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid age! Enter a number.");
            return;
        }

        System.out.print("Enter gender (M/F): ");
        String gender = scanner.nextLine().trim();
        if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
            System.out.println("Invalid gender! Must be 'M' or 'F'.");
            return;
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        if (!email.endsWith("@gmail.com") && !email.endsWith("@yahoo.com")) {
            System.out.println("Invalid email! Must end with @gmail.com or @yahoo.com.");
            return;
        }

        Patient patient = new Patient(patientID);
        patient.setName(name);
        patient.setAge(age);
        patient.setGender(gender.toUpperCase());
        patient.setEmail(email);

        if (patientManager.addPatient(patient)) {
            System.out.println("Patient added successfully!");
        } else {
            System.out.println("Error: Could not add patient.");
        }
    }


    private void removePatient() {
        System.out.print("Enter patient ID to remove: ");
        String patientID = scanner.nextLine();
        if (patientManager.removePatient(patientID)) {
            System.out.println("Patient removed successfully!");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private void listAllPatients() {
        if (patientManager.getPatientCount() == 0) {
            System.out.println("No patients found.");
        } else {
            patientManager.displayPatients();
        }
    }


    private void searchPatientByID() {
        System.out.print("Enter patient ID to search: ");
        String patientID = scanner.nextLine();
        PatientInterface patient = patientManager.getPatient(patientID);
        if (patient != null) {
            printPatientDetails(patient);
        } else {
            System.out.println("Patient not found.");
        }
    }

    private void printPatientDetails(PatientInterface patient) {
        System.out.println("\n=== Patient Details ===");
        System.out.println("Patient ID: " + patient.getPatientID());
        System.out.println("Name: " + patient.getName());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Gender: " + patient.getGender());
        System.out.println("Email: " + patient.getEmail());
    }
}
