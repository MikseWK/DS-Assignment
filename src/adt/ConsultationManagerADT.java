package adt;

import java.time.LocalDate;

public interface ConsultationManagerADT {

    boolean addPatient(PatientADT patient);            
    PatientADT getPatient(String patientID);       

    boolean addConsultation(String patientID, ConsultationADT consultation);  
    ConsultationADT getConsultation(String consultationID);               

    boolean setFollowUp(String consultationID, LocalDate followUpDate);      
    LocalDate getFollowUp(String consultationID);    
    
    void displayConsultationList();
}
