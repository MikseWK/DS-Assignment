package boundary;

import java.util.InputMismatchException;
import java.util.Scanner;
import manager.ConsultationMaintenance;
import manager.DoctorMaintenance;
import manager.PatientMaintenance;
import manager.RoomMaintenance;

public class MainMenuUI {
    private final Scanner scanner;
    private final PatientMaintenance patientManager;
    private final DoctorMaintenance doctorManager;
    private final RoomMaintenance roomManager;
    private final ConsultationMaintenance consultationManager;

    public MainMenuUI(PatientMaintenance patientManager,
                      DoctorMaintenance doctorManager,
                      RoomMaintenance roomManager,
                      ConsultationMaintenance consultationManager) {
        this.patientManager = patientManager;
        this.doctorManager = doctorManager;
        this.roomManager = roomManager;
        this.consultationManager = consultationManager;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            choice = getMenuChoice();
            switch (choice) {
                case 1:
                    // go into Patient Management UI
                    PatientUI patientUI = new PatientUI(patientManager, scanner);
                    patientUI.start();
                    break;
                case 2:
                    // go into Doctor Management UI
                    DoctorUI doctorUI = new DoctorUI(doctorManager, scanner);
                    doctorUI.start();
                    break;
                case 3:
                    // go into Room Management UI
                    RoomUI roomUI = new RoomUI(roomManager, scanner);
                    roomUI.start();
                    break;
                case 4:
                    // go into Consultation Management UI
                    ConsultationUI consultationUI = new ConsultationUI(consultationManager, patientManager, doctorManager, roomManager, scanner);
                    consultationUI.start();
                    break;
                case 0:
                    System.out.println("Exiting system... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);
    }

    private int getMenuChoice() {
        int choice = -1;
        boolean valid = false;
        while (!valid) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Room Management");
            System.out.println("4. Consultation Management");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // clear invalid input
            }
        }
        return choice;
    }
}