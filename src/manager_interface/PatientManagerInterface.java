package manager_interface;

import entity_interface.PatientInterface;

public interface PatientManagerInterface {

    // Add a new patient
    boolean addPatient(PatientInterface patient);

    // Get a patient by ID
    PatientInterface getPatient(String patientID);

    // Remove a patient by ID
    boolean removePatient(String patientID);

    // Check if a patient exists
    boolean containsPatient(String patientID);

    // Display all patients
    void displayPatients();

    // Get total number of patients
    int getPatientCount();
    
    boolean updatePatient(String patientID, PatientInterface updatedPatient);
}
