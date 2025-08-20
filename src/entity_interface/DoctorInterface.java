package entity_interface;

public interface DoctorInterface {
    String getDoctorID();
    String getFirstName();
    String getLastName();
    String getSpecialization();
    String getEmail();
    String getPhoneNumber();
    String getStatus(); // Active, Inactive

    void setDoctorID(String doctorID);
    void setFirstName(String firstName);
    void setLastName(String lastName);
    void setSpecialization(String specialization);
    void setEmail(String email);
    void setPhoneNumber(String phoneNumber);
    void setStatus(String status);

    String getFullName();
}
