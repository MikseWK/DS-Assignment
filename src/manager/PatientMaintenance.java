package manager;

import customizeADT.CustomizeADT;
import entity_interface.PatientInterface; // Import the interface
import manager_interface.PatientManagerInterface;

public class PatientMaintenance implements PatientManagerInterface {
    private CustomizeADT<String, PatientInterface, ?> patientADT;

    
    public PatientMaintenance(CustomizeADT<String, PatientInterface, ?> patientADT) {
        this.patientADT = patientADT;
    }

   
    public PatientMaintenance() {
        this(new CustomizeADT<>());
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

    // Display all patients
    public void displayPatients() {
        if (patientADT.isEmpty()) {
            System.out.println("No patients available.");
            return;
        }

        // Correctly handle the array conversion
        Object[] rawValues = patientADT.values();
        PatientInterface[] allPatients = new PatientInterface[rawValues.length];
        for(int i = 0; i < rawValues.length; i++) {
            allPatients[i] = (PatientInterface) rawValues[i];
        }

        System.out.println("=====================================================================================");
        System.out.printf("| %-5s | %-10s | %-20s | %-5s | %-8s | %-25s |\n",
                "No", "Patient ID", "Name", "Age", "Gender", "Email");
        System.out.println("=====================================================================================");
        for (int i = 0; i < allPatients.length; i++) {
            PatientInterface p = allPatients[i];
            if (p != null) {
                System.out.printf("| %-5d | %-10s | %-20s | %-5d | %-8s | %-25s |\n",
                        (i + 1), p.getPatientID(), p.getName(), p.getAge(), p.getGender(), p.getEmail());
            }
        }
        System.out.println("=====================================================================================");
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

    @Override
    public CustomizeADT<String, PatientInterface, ?> getAllPatients() {
        return this.patientADT;
    }
}
