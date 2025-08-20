package manager;

import customizeADT.CustomizeADT;
import entity_interface.PatientInterface;
import manager_interface.PatientManagerInterface;

public class PatientManager implements PatientManagerInterface {

    private CustomizeADT<String, PatientInterface, PatientInterface> patientADT;

    public PatientManager() {
        patientADT = new CustomizeADT<>();
    }

    // Add a new patient
    @Override
    public boolean addPatient(PatientInterface patient) {
        if (patientADT.containsKey(patient.getPatientID())) return false; // avoid duplicates
        patientADT.put(patient.getPatientID(), patient);
        return true;
    }

    // Get patient by ID
    @Override
    public PatientInterface getPatient(String patientID) {
        return patientADT.get(patientID);
    }

    // Update patient info
    @Override
    public boolean updatePatient(String patientID, PatientInterface updatedPatient) {
        if (!patientADT.containsKey(patientID)) return false;
        patientADT.put(patientID, updatedPatient);
        return true;
    }

    // Remove a patient by ID
    @Override
    public boolean removePatient(String patientID) {
        if (!patientADT.containsKey(patientID)) return false;
        patientADT.remove(patientID);
        return true;
    }

    @Override
    public void displayPatients() {
        if (patientADT.isEmpty()) {
            System.out.println("No patients available.");
            return;
        }

        System.out.println("====================================================================================================");
        System.out.printf("| %-5s | %-10s | %-20s | %-5s | %-6s | %-35s |\n", 
                          "No", "Patient ID", "Patient Name", "Age", "Gender", "Email");
        System.out.println("====================================================================================================");

        Object[] keys = patientADT.keys(); // get all patient IDs
        for (int i = 0; i < keys.length; i++) {
            PatientInterface p = patientADT.get((String) keys[i]); // get patient by ID
            System.out.printf("| %-5d | %-10s | %-20s | %-5d | %-6s | %-35s |\n",
                              i + 1,
                              p.getPatientID(),
                              p.getName(),
                              p.getAge(),
                              p.getGender(),
                              p.getEmail());
        }

        System.out.println("====================================================================================================");
    }


    // Check if patient exists
    @Override
    public boolean containsPatient(String patientID) {
        return patientADT.containsKey(patientID);
    }

    // Get total number of patients
    @Override
    public int getPatientCount() {
        return patientADT.size();
    }
}
