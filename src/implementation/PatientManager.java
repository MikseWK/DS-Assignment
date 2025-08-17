package implementation;

import adt.CustomizeADT;
import implementation.Patient;
import implementation.PatientManagerInterface;

public class PatientManager implements PatientManagerInterface {
    private CustomizeADT<String, Patient, Patient> patientADT;

    public PatientManager() {
        patientADT = new CustomizeADT<>();
    }

    @Override
    public void addPatient(Patient patient) {
        patientADT.put(patient.getPatientID(), patient);
    }

    @Override
    public Patient getPatientByID(String patientID) {
        return patientADT.get(patientID);
    }

    @Override
    public boolean removePatient(String patientID) {
        return patientADT.remove(patientID) != null;
    }

    @Override
    public int getPatientCount() {
        return patientADT.size();
    }

    @Override
    public boolean containsPatient(String patientID) {
        return patientADT.containsKey(patientID);
    }

    @Override
    public void printAllPatients() {
        patientADT.printAll();
    }

    @Override
    public CustomizeADT<String, Patient, Patient> getPatientADT() {
        return patientADT;
    }
}
