package implementation;

import adt.CustomizeADT;

public interface PatientManagerInterface {
    // Use your ADT for storage and retrieval
    void addPatient(Patient patient);                      
    Patient getPatientByID(String patientID);               
    boolean removePatient(String patientID);               
    int getPatientCount();                                
    boolean containsPatient(String patientID);             
    void printAllPatients();                               
    CustomizeADT<String, Patient, Patient> getPatientADT(); 
}