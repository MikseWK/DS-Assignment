package boundary;

import entity_implementation.Doctor;
import manager.DoctorManager;
import entity_interface.DoctorInterface;

import java.util.Scanner;

public class DoctorUI {

    private final Scanner scanner;
    private final DoctorManager doctorManager;

    public DoctorUI(DoctorManager doctorManager, Scanner scanner) {
        this.doctorManager = doctorManager;
        this.scanner = scanner;
    }

    // Show the doctor menu and get user choice
    public int getMenuChoice() {
        System.out.println("\n=== DOCTOR MENU ===");
        System.out.println("1. Add new doctor");
        System.out.println("2. Remove doctor");
        System.out.println("3. List all doctors");
        System.out.println("4. View doctor details");
        System.out.println("5. Search doctor by ID");
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

    // Input doctor details
    public Doctor inputDoctorDetails() {
        System.out.print("Enter Doctor ID: ");
        String doctorID = scanner.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        return new Doctor(doctorID, firstName, lastName, specialization, email, phone);
    }

    // Display all doctors in table format
    public void listAllDoctors() {
        if (doctorManager.getDoctorCount() == 0) {
            System.out.println("No doctors found.");
        } else {
            doctorManager.displayDoctors();
        }
    }

    // Display a single doctor's details
    public void printDoctorDetails(DoctorInterface doctor) {
        System.out.println("\n=== Doctor Details ===");
        System.out.println("Doctor ID: " + doctor.getDoctorID());
        System.out.println("Name: " + doctor.getFirstName() + " " + doctor.getLastName());
        System.out.println("Specialization: " + doctor.getSpecialization());
        System.out.println("Email: " + doctor.getEmail());
        System.out.println("Phone Number: " + doctor.getPhoneNumber());
    }

    // Input doctor ID for search or delete
    public String inputDoctorID() {
        System.out.print("Enter Doctor ID: ");
        return scanner.nextLine();
    }

    // Add a doctor
    public void addDoctor() {
        Doctor doctor = inputDoctorDetails();
        if (doctorManager.addDoctor(doctor)) {
            System.out.println("Doctor added successfully!");
        } else {
            System.out.println("Error: Doctor ID already exists.");
        }
    }

    // Remove a doctor
    public void removeDoctor() {
        String doctorID = inputDoctorID();
        if (doctorManager.removeDoctor(doctorID)) {
            System.out.println("Doctor removed successfully!");
        } else {
            System.out.println("Error: Doctor not found.");
        }
    }

    // Search and view a doctor by ID
    public void searchDoctorByID() {
        String doctorID = inputDoctorID();
        DoctorInterface doctor = doctorManager.getDoctor(doctorID);
        if (doctor != null) {
            printDoctorDetails(doctor);
        } else {
            System.out.println("Doctor not found.");
        }
    }

    // Start method to run the menu loop
    public void start() {
        int choice;
        do {
            choice = getMenuChoice();
            switch (choice) {
                case 1 -> addDoctor();
                case 2 -> removeDoctor();
                case 3 -> listAllDoctors();
                case 4 -> {
                    String doctorID = inputDoctorID();
                    DoctorInterface doctor = doctorManager.getDoctor(doctorID);
                    if (doctor != null) {
                        printDoctorDetails(doctor);
                    } else {
                        System.out.println("Doctor not found.");
                    }
                }
                case 5 -> searchDoctorByID();
                case 0 -> System.out.println("Exiting Doctor Menu...");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }
}