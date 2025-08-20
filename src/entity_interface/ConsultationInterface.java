package entity_interface;

import java.time.LocalDate;

public interface ConsultationInterface {
    String getConsultationID();
    String getPatientID();
    String getDoctorID();
    String getDiagnosis();
    LocalDate getConsultationDate();
    LocalDate getFollowUpDate();
    String getRoom();  

    void setConsultationID(String consultationID);
    void setPatientID(String patientID);
    void setDoctorID(String doctorID);
    void setDiagnosis(String diagnosis);
    void setConsultationDate(LocalDate date);
    void setFollowUpDate(LocalDate date);
    void setRoom(String room); 
}