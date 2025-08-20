package manager;

import customizeADT.CustomizeADT;
import entity_interface.ConsultationInterface;
import manager_interface.ConsultationManagerInterface;

import java.time.LocalDate;

public class ConsultationManager implements ConsultationManagerInterface {

    private CustomizeADT<String, ConsultationInterface, ConsultationInterface> consultationADT;

    public ConsultationManager() {
        consultationADT = new CustomizeADT<>();
    }

    // Add a consultation
    @Override
    public boolean addConsultation(String patientID, ConsultationInterface consultation) {
        if (!consultation.getPatientID().equals(patientID)) return false; // patientID mismatch
        if (consultationADT.containsKey(consultation.getConsultationID())) return false; // duplicate ID
        consultationADT.put(consultation.getConsultationID(), consultation);
        return true;
    }

    // Get consultation by ID
    @Override
    public ConsultationInterface getConsultation(String consultationID) {
        return consultationADT.get(consultationID);
    }

    // Remove a consultation
    @Override
    public boolean removeConsultation(String consultationID) {
        if (!consultationADT.containsKey(consultationID)) return false;
        consultationADT.remove(consultationID);
        return true;
    }

    // Set follow-up date
    @Override
    public boolean setFollowUp(String consultationID, LocalDate followUpDate) {
        ConsultationInterface consultation = getConsultation(consultationID);
        if (consultation == null) return false;
        consultation.setFollowUpDate(followUpDate);
        return true;
    }

    // Get follow-up date
    @Override
    public LocalDate getFollowUp(String consultationID) {
        ConsultationInterface consultation = getConsultation(consultationID);
        if (consultation != null) {
            return consultation.getFollowUpDate();
        }
        return null;
    }

    // Display all consultations
    @Override
    public void displayConsultationList() {
        if (consultationADT.isEmpty()) {
            System.out.println("No consultations available.");
            return;
        }
        ConsultationInterface[] allConsultations = consultationADT.values();
        System.out.println("==============================================");
        System.out.printf("| %-5s | %-10s | %-10s | %-10s | %-15s |\n",
                "No", "ConsultID", "PatientID", "DoctorID", "Room");
        System.out.println("==============================================");
        for (int i = 0; i < allConsultations.length; i++) {
            ConsultationInterface c = allConsultations[i];
            if (c != null) {
                System.out.printf("| %-5d | %-10s | %-10s | %-10s | %-15s |\n",
                        (i + 1),
                        c.getConsultationID(),
                        c.getPatientID(),
                        c.getDoctorID(),
                        c.getRoom());
            }
        }
        System.out.println("==============================================");
    }

    // Get consultations by patient ID
    @Override
    public ConsultationInterface[] getConsultationsByPatientID(String patientID) {
        ConsultationInterface[] all = consultationADT.values();
        return java.util.Arrays.stream(all)
                .filter(c -> c != null && c.getPatientID().equals(patientID))
                .toArray(ConsultationInterface[]::new);
    }

    // Get consultations by doctor ID
    @Override
    public ConsultationInterface[] getConsultationsByDoctorID(String doctorID) {
        ConsultationInterface[] all = consultationADT.values();
        return java.util.Arrays.stream(all)
                .filter(c -> c != null && c.getDoctorID().equals(doctorID))
                .toArray(ConsultationInterface[]::new);
    }

    // Get room list by doctor ID
    @Override
    public String[] getRoomsByDoctorID(String doctorID) {
        ConsultationInterface[] all = consultationADT.values();
        return java.util.Arrays.stream(all)
                .filter(c -> c != null && c.getDoctorID().equals(doctorID))
                .map(ConsultationInterface::getRoom)
                .toArray(String[]::new);
    }

    // Get the last added consultation
    public ConsultationInterface getLastConsultation() {
        ConsultationInterface[] all = consultationADT.values();
        if (all.length == 0) return null;
        return all[all.length - 1];
    }

    // Check if a consultation exists
    public boolean containsConsultation(String consultationID) {
        return consultationADT.containsKey(consultationID);
    }

    // Get total consultations
    public int getConsultationCount() {
        return consultationADT.size();
    }
}
