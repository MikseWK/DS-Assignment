package run;

import adt_implementation.*;
import adt.*;
import adt_implementation.Consultation;
import adt_implementation.ConsultationManager;
import adt_implementation.ConsultationQueue;
import adt_implementation.Patient;

import java.time.LocalDate;
import java.util.Scanner;

public class DSA_ASG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultationManagerADT consultationManager = new ConsultationManager();
        ConsultationQueueADT consultationQueue = new ConsultationQueue();
        RoomCircularList roomList = new RoomCircularList();
        
        PatientADT patient1 = new Patient("P001","AAA");
        PatientADT patient2 = new Patient("P002","BBB");
        PatientADT patient3 = new Patient("P003","CCC");
        consultationManager.addPatient(patient1);
        consultationManager.addPatient(patient2);
        consultationManager.addPatient(patient3);
        roomList.add(new Room("Room 1", "Dr. Alice", true));
        roomList.add(new Room("Room 2", "Dr. Bob", true));
        roomList.add(new Room("Room 3", "Dr. Charlie", true));
        roomList.add(new Room("Room 4", "Dr. Diana", true));
        roomList.add(new Room("Room 5", "Dr. Ethan", true));
        roomList.add(new Room("Room 6", "Dr. Fiona", true));
        roomList.add(new Room("Room 7", "Dr. George", true));
        roomList.add(new Room("Room 8", "Dr. Helen", true));

        while (true) {
            System.out.println("\n=== Consultation Management Menu ===");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Consultation");
            System.out.println("3. Set Follow-Up Appointment");
            System.out.println("4. Enqueue Patient (Walk-In Queue)");
            System.out.println("5. Dequeue and Assign Consultation");
            System.out.println("6. View All Consultations");
            System.out.println("7. View All Room");
            System.out.println("8. View All Consultation Queue");
            System.out.println("9. Exit");
            System.out.print("Select an option: ");
            
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter patient ID: ");
                    String pid = scanner.nextLine();
                    PatientADT patient = new Patient(pid);
                    if (consultationManager.addPatient(patient)) {
                        System.out.println("Patient added successfully.");
                    } else {
                        System.out.println("Error: Patient already exists or max capacity reached.");
                    }
                }
                case 2 -> {
                    System.out.print("Enter consultation ID: ");
                    String cid = scanner.nextLine();

                    if (consultationManager.getConsultation(cid) != null) {
                        System.out.println("Error: Consultation ID already exists. Use a unique ID.");
                        break;
                    }

                    System.out.print("Enter patient ID: ");
                    String pid = scanner.nextLine();
                    System.out.print("Enter doctor name: ");
                    String doctor = scanner.nextLine();
                    System.out.print("Enter diagnosis: ");
                    String diagnosis = scanner.nextLine();
                    LocalDate date = LocalDate.now();

                    ConsultationADT cons = new Consultation(cid, pid, doctor, diagnosis, date);
                    if (consultationManager.addConsultation(pid, cons)) {
                        System.out.println("Consultation added successfully.");
                    } else {
                        System.out.println("Failed to add consultation. Patient not found or full.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter consultation ID to set follow-up: ");
                    String cid = scanner.nextLine();

                    if (consultationManager.getConsultation(cid) == null) {
                        System.out.println("Error: Consultation not found.");
                        break;
                    }

                    LocalDate date = null;
                    boolean validDate = false;

                    while (!validDate) {
                        System.out.print("Enter follow-up date (yyyy-mm-dd): ");
                        String inputDate = scanner.nextLine();
                        try {
                            date = LocalDate.parse(inputDate);
                            validDate = true;
                        } catch (Exception e) {
                            System.out.println("Invalid date format. Please enter a valid date in yyyy-mm-dd format.");
                        }
                    }

                    if (consultationManager.setFollowUp(cid, date)) {
                        System.out.println("Follow-up date set successfully.");
                    } else {
                        System.out.println("Unexpected error: Consultation found earlier, but failed to update.");
                    }
                }
                case 4 -> {
                    System.out.print("Enter patient ID to enqueue: ");
                    String pid = scanner.nextLine();
                    PatientADT patient = consultationManager.getPatient(pid);

                    if (patient == null) {
                        System.out.println("Error: Patient not found.");
                        break;
                    }

                    RoomADT room = roomList.getAvailableRoom();
                    if (room == null) {
                        System.out.println("No available rooms at the moment. Please wait.");
                    } else {
                        consultationQueue.enqueue(patient);   
                        room.setAvailable(false);        
                        room.setPatientID(pid);                     
                        System.out.println("Patient queued.");
                        System.out.println("Assigned Room: " + room.getRoomID() +
                                           " (Doctor: " + room.getDoctorName() + ")");
                    }
                }
                case 5 -> {
                    if (!consultationQueue.isEmpty()) {
                        PatientADT patient = consultationQueue.dequeue();
                        String pid = patient.getPatientID();

                        RoomADT room = roomList.getRoomByPatientID(pid); // Find the room by patient ID
                        System.out.println("Dequeued Patient: " + pid);

                        String[] commonDiagnoses = {
                            "Fever", "Cough and Cold", "Stomach Ache", "Headache",
                            "Skin Rash", "High Blood Pressure", "Diabetes Check", "Back Pain"
                        };

                        System.out.println("Select diagnosis:");
                        for (int i = 0; i < commonDiagnoses.length; i++) {
                            System.out.println((i + 1) + ". " + commonDiagnoses[i]);
                        }

                        int diagnosisChoice = -1;
                        while (diagnosisChoice < 1 || diagnosisChoice > commonDiagnoses.length) {
                            System.out.print("Enter choice (1-" + commonDiagnoses.length + "): ");
                            diagnosisChoice = scanner.nextInt();
                            scanner.nextLine();
                        }

                        String diagnosis = commonDiagnoses[diagnosisChoice - 1];
                        String cid = "C" + String.format("%03d", (int)(Math.random() * 900) + 100);

                        String doctorName = room != null ? room.getDoctorName() : "Dr. Default"; // Get from room if available
                        LocalDate today = LocalDate.now();

                        ConsultationADT cons = new Consultation(cid, pid, doctorName, diagnosis, today);

                        if (consultationManager.addConsultation(pid, cons)) {
                            System.out.println("Consultation added for " + pid);
                            System.out.println("Diagnosis: " + diagnosis);
                            System.out.println("Suggested Medicine: " + ""); // You can add logic here for suggestions
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            if (room != null) {
                                room.setAvailable(true);
                                room.setPatientID(null); // Clear assignment
                            }
                        } else {
                            System.out.println("Error: Failed to add consultation.");
                        }

                    } else {
                        System.out.println("Queue is empty.");
                    }
                }

                case 6 -> {
                    consultationManager.displayConsultationList();
                }
                
                case 7 -> {
                    System.out.println("===== All Room Information =====");
                    roomList.traverseAndPrint();
                }

                case 8 -> {
                    System.out.println("===== Consultation Queue =====");
                    consultationQueue.traverseAndPrint();
                }
                
                case 9 -> {
                    System.out.println("Exiting system. Goodbye.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}