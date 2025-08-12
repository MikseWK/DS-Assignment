package adt_implementation;

import adt.CustomizeADT;

import java.time.LocalDate;

public class ConsultationManager implements ConsultationManagerADT {
    private final int MAX = 200;
    private PatientADT[] patients;
    private ConsultationADT[] consultations;
    private int patientCount;
    private int consultationCount;

    public ConsultationManager() {
        patients = new PatientADT[MAX];
        consultations = new ConsultationADT[MAX];
        patientCount = 0;
        consultationCount = 0;
    }

    @Override
    public boolean addPatient(PatientADT patient) {
        if(getPatient(patient.getPatientID()) == null) {
            if (patientCount < MAX) {
                patients[patientCount++] = patient;
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
        
    }

    @Override
    public PatientADT getPatient(String patientID) {
        for (int i = 0; i < patientCount; i++) {
            if (patients[i].getPatientID().equals(patientID)) {
                return patients[i];
            }
        }
        return null;
    }

    @Override
    public boolean addConsultation(String patientID, ConsultationADT consultation) {
        if (getPatient(patientID) == null) {
            return false;
        } else if (consultationCount >= MAX) {
            return false;
        } else {
            consultations[consultationCount++] = consultation;
            return true;
        }
    }

    @Override
    public ConsultationADT getConsultation(String consultationID) {
        for (int i = 0; i < consultationCount; i++) {
            if (consultations[i].getConsultationID().equals(consultationID)) {
                return consultations[i];
            }
        }
        return null;
    }

    @Override
    public boolean setFollowUp(String consultationID, LocalDate followUpDate) {
        ConsultationADT consultation = getConsultation(consultationID);
        if (consultation == null) {
            return false;
        }
        else {
            consultation.setFollowUpDate(followUpDate);
            return true;
        }
    }

    @Override
    public LocalDate getFollowUp(String consultationID) {
        ConsultationADT consultation = getConsultation(consultationID);
        if (consultation != null) {
            // Requires a getFollowUpDate method in Consultation implementation
            try {
                return ((adt_implementation.Consultation) consultation).getFollowUpDate();
            } catch (ClassCastException e) {
                return null;
            }
        }
        return null;
    }
    @Override
    public void displayConsultationList() {
        if (consultationCount == 0) {
            System.out.println("No consultations available.");
            return;
        }

        System.out.println("Consultation List:");
        for (int i = 0; i < consultationCount; i++) {
            System.out.println((i + 1) + ". " + consultations[i]);
        }
    }
}
