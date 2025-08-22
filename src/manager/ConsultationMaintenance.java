package manager;

import customizeADT.CustomizeADT;
import entity_interface.ConsultationInterface;
import java.time.LocalDate;
import manager_interface.ConsultationManagerInterface;

public class ConsultationMaintenance implements ConsultationManagerInterface {

    private CustomizeADT<String, ConsultationInterface, ?> consultationADT;

    public ConsultationMaintenance(CustomizeADT<String, ConsultationInterface, ?> consultationADT) {
        this.consultationADT = consultationADT;
    }

    public ConsultationMaintenance() {
        this(new CustomizeADT<>());
    }

    @Override
    public boolean addConsultation(String patientID, ConsultationInterface consultation) {
        if (!consultation.getPatientID().equals(patientID)) return false;
        if (consultationADT.containsKey(consultation.getConsultationID())) return false;
        consultationADT.put(consultation.getConsultationID(), consultation);
        return true;
    }

    @Override
    public ConsultationInterface getConsultation(String consultationID) {
        return consultationADT.get(consultationID);
    }

    @Override
    public boolean removeConsultation(String consultationID) {
        if (!consultationADT.containsKey(consultationID)) return false;
        consultationADT.remove(consultationID);
        return true;
    }

    @Override
    public boolean setFollowUp(String consultationID, LocalDate followUpDate) {
        ConsultationInterface consultation = getConsultation(consultationID);
        if (consultation == null) return false;
        consultation.setFollowUpDate(followUpDate);
        return true;
    }

    @Override
    public LocalDate getFollowUp(String consultationID) {
        ConsultationInterface consultation = getConsultation(consultationID);
        return (consultation != null) ? consultation.getFollowUpDate() : null;
    }

    @Override
    public void displayConsultationList() {
        if (consultationADT.isEmpty()) {
            System.out.println("No consultations available.");
            return;
        }

        Object[] rawValues = consultationADT.values();
        ConsultationInterface[] allConsultations = new ConsultationInterface[rawValues.length];
        for (int i = 0; i < rawValues.length; i++) {
            allConsultations[i] = (ConsultationInterface) rawValues[i];
        }

        System.out.println("=====================================================================");
        System.out.printf("| %-5s | %-10s | %-10s | %-10s | %-15s |\n", "No", "ConsultID", "PatientID", "DoctorID", "Room");
        System.out.println("=====================================================================");
        for (int i = 0; i < allConsultations.length; i++) {
            ConsultationInterface c = allConsultations[i];
            if (c != null) {
                System.out.printf("| %-5d | %-10s | %-10s | %-10s | %-15s |\n", (i + 1), c.getConsultationID(), c.getPatientID(), c.getDoctorID(), c.getRoom());
            }
        }
        System.out.println("=====================================================================");
    }

    private ConsultationInterface[] getAllConsultationsAsArray() {
        Object[] rawValues = consultationADT.values();
        ConsultationInterface[] all = new ConsultationInterface[rawValues.length];
        for (int i = 0; i < rawValues.length; i++) {
            all[i] = (ConsultationInterface) rawValues[i];
        }
        return all;
    }

    @Override
    public ConsultationInterface[] getConsultationsByPatientID(String patientID) {
        ConsultationInterface[] all = getAllConsultationsAsArray();
        return java.util.Arrays.stream(all)
                .filter(c -> c != null && c.getPatientID().equals(patientID))
                .toArray(ConsultationInterface[]::new);
    }

    @Override
    public ConsultationInterface[] getConsultationsByDoctorID(String doctorID) {
        ConsultationInterface[] all = getAllConsultationsAsArray();
        return java.util.Arrays.stream(all)
                .filter(c -> c != null && c.getDoctorID().equals(doctorID))
                .toArray(ConsultationInterface[]::new);
    }

    @Override
    public String[] getRoomsByDoctorID(String doctorID) {
        ConsultationInterface[] all = getAllConsultationsAsArray();
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


    public boolean containsConsultation(String consultationID) {
        return consultationADT.containsKey(consultationID);
    }

    public int getConsultationCount() {
        return consultationADT.size();
    }

    @Override
    public CustomizeADT<String, ConsultationInterface, ?> getAllConsultations() {
        return this.consultationADT;
    }
}
