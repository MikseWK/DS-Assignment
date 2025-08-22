package manager_interface;

import customizeADT.CustomizeADT;
import entity_interface.DoctorInterface; 

public interface DoctorManagerInterface {

    // Use DoctorInterface for all method signatures
    boolean addDoctor(DoctorInterface doctor);

    DoctorInterface getDoctor(String doctorID);

    boolean updateDoctor(String doctorID, DoctorInterface updatedDoctor);

    boolean removeDoctor(String doctorID);

    boolean containsDoctor(String doctorID);

    int getDoctorCount();

    CustomizeADT<String, DoctorInterface, ?> getAllDoctors();
}
