package manager_interface;

import customizeADT.CustomizeADT;
import entity_interface.PatientInterface; 

public interface PatientManagerInterface {
    boolean addPatient(PatientInterface patient);
    PatientInterface getPatient(String patientID);
    boolean updatePatient(String patientID, PatientInterface updatedPatient);
    boolean removePatient(String patientID);
    boolean containsPatient(String patientID);
    int getPatientCount();
    CustomizeADT<String, PatientInterface, ?> getAllPatients();
}
