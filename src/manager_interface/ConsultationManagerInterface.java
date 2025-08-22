package manager_interface;

import customizeADT.CustomizeADT; // Add this import
import entity_interface.ConsultationInterface;
import java.time.LocalDate;

public interface ConsultationManagerInterface {

    boolean addConsultation(String patientID, ConsultationInterface consultation);

    ConsultationInterface getConsultation(String consultationID);

    boolean removeConsultation(String consultationID);

    boolean setFollowUp(String consultationID, LocalDate followUpDate);

    LocalDate getFollowUp(String consultationID);

    void displayConsultationList();

    ConsultationInterface[] getConsultationsByPatientID(String patientID);

    ConsultationInterface[] getConsultationsByDoctorID(String doctorID);

    String[] getRoomsByDoctorID(String doctorID);

    CustomizeADT<String, ConsultationInterface, ?> getAllConsultations();
}
