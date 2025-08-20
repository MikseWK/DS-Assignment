package entity_interface;

public interface PatientInterface {
    String getPatientID();
    void setPatientID(String patientID);

    String getName();
    void setName(String name);

    int getAge();
    void setAge(int age);

    String getGender();
    void setGender(String gender);

    // Add these for email
    String getEmail();
    void setEmail(String email);
}
