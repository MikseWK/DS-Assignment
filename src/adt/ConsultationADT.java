package adt;

import java.time.LocalDate;

public interface ConsultationADT {
    String getConsultationID();
    String getPatientID();
    String getDoctorName();
    String getDiagnosis();
    LocalDate getConsultationDate();
    LocalDate getFollowUpDate();
    
    void setConsultationID(String consultationID);
    void setPatientID(String patientID);
    void setDoctorName(String doctorName);
    void setDiagnosis(String diagnosis);
    void setConsultationDate(LocalDate date);
    
    void setFollowUpDate(LocalDate date);
}