package manager_interface;

import entity_interface.ConsultationInterface;
import java.time.LocalDate;

public interface ConsultationManagerInterface {

    boolean addConsultation(String patientID, ConsultationInterface consultation);

    ConsultationInterface getConsultation(String consultationID);

    boolean setFollowUp(String consultationID, LocalDate followUpDate);

    LocalDate getFollowUp(String consultationID);

    void displayConsultationList();

    ConsultationInterface[] getConsultationsByPatientID(String patientID);

    ConsultationInterface[] getConsultationsByDoctorID(String doctorID);

    String[] getRoomsByDoctorID(String doctorID);
    
    ConsultationInterface getLastConsultation();
    
    boolean removeConsultation(String consultationID);
}
