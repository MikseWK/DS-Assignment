package entity_implementation;

import entity_interface.ConsultationInterface;
import java.io.Serializable;
import java.time.LocalDate;

public class Consultation implements ConsultationInterface, Serializable {
    private String consultationID;
    private String patientID;
    private String doctorID;      
    private String diagnosis;
    private LocalDate consultationDate;
    private LocalDate followUpDate;
    private String roomID;  
    
    public Consultation() {
        this.consultationID = "";
        this.patientID = "";
        this.doctorID = "";
        this.diagnosis = "";
        this.consultationDate = LocalDate.now();  
        this.followUpDate = null;                
        this.roomID = "";   
    }
    
    public Consultation(String consultationID, String patientID, String doctorID,
                        String diagnosis, LocalDate consultationDate, String roomID) {
        this.consultationID = consultationID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.diagnosis = diagnosis;
        this.consultationDate = consultationDate;
        this.followUpDate = null;  
        this.roomID = roomID;   
    }

    @Override
    public String getConsultationID() {
        return this.consultationID;
    }

    @Override
    public String getPatientID() {
        return this.patientID;
    }

    @Override
    public String getDoctorID() {   
        return this.doctorID;
    }

    @Override
    public String getDiagnosis() {
        return this.diagnosis;
    }

    @Override
    public LocalDate getConsultationDate() {
        return this.consultationDate;
    }
    
    @Override
    public LocalDate getFollowUpDate() {
        return this.followUpDate;
    }

    @Override
    public String getRoom() {
        return this.roomID;
    }
    
    @Override
    public void setConsultationID(String consultationID) {
        this.consultationID = consultationID;
    }
    
    @Override
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
    
    @Override
    public void setDoctorID(String doctorID) {  
        this.doctorID = doctorID;
    }
    
    @Override
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }   

    @Override
    public void setConsultationDate(LocalDate date) {
        this.consultationDate = date;
    }

    @Override
    public void setFollowUpDate(LocalDate date) {
        this.followUpDate = date;
    }

    @Override
    public void setRoom(String roomID) {
        this.roomID = roomID;
    }

    @Override
    public String toString() {
        return "Consultation ID: " + consultationID +
               ", Patient ID: " + patientID +
               ", Doctor ID: " + doctorID +
               ", Diagnosis: " + diagnosis +
               ", Date: " + consultationDate +
               ", Follow-up: " + (followUpDate != null ? followUpDate : "N/A") +
               ", Room: " + (roomID.isEmpty() ? "N/A" : roomID);
    }
}
