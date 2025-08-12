package adt_implementation;

import adt.CustomizeADT;
import java.time.LocalDate;

public class Consultation implements ConsultationADT {
    private String consultationID;
    private String patientID;
    private String doctorName;
    private String diagnosis;
    private LocalDate consultationDate;
    private LocalDate followUpDate;

    public Consultation() {
        this.consultationID = "";
        this.patientID = "";
        this.doctorName = "";
        this.diagnosis = "";
        this.consultationDate = LocalDate.now();  
        this.followUpDate = null;                
    }
    
    public Consultation(String consultationID, String patientID, String doctorName,
                        String diagnosis, LocalDate consultationDate) {
        this.consultationID = consultationID;
        this.patientID = patientID;
        this.doctorName = doctorName;
        this.diagnosis = diagnosis;
        this.consultationDate = consultationDate;
        this.followUpDate = null;  // default: no follow-up yet
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
    public String getDoctorName() {
        return this.doctorName;
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
    public void setConsultationID(String consultationID) {
        this.consultationID = consultationID;
    }
    
    @Override
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
    
    @Override
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
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

    // Optional: toString() for display
    @Override
    public String toString() {
        return "Consultation ID: " + consultationID +
               ", Patient ID: " + patientID +
               ", Doctor: " + doctorName +
               ", Diagnosis: " + diagnosis +
               ", Date: " + consultationDate +
               ", Follow-up: " + (followUpDate != null ? followUpDate : "N/A");
    }
}
