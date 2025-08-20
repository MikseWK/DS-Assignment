package manager_interface;

import entity_interface.DoctorInterface;

public interface DoctorManagerInterface {

    boolean addDoctor(DoctorInterface doctor);

    DoctorInterface getDoctor(String doctorID);

    boolean removeDoctor(String doctorID);

    void displayDoctors();

    boolean containsDoctor(String doctorID);

    int getDoctorCount();
    
    boolean updateDoctor(String doctorID, DoctorInterface updatedDoctor);
}
