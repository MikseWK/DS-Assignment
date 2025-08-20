package entity_interface;

public interface RoomInterface {
    String getRoomID();
    String getDoctorName();
    boolean isAvailable();
    String getPatientID();

    void setCounterID(String counterID); 
    void setDoctorName(String doctorName);
    void setAvailable(boolean available);
    void setPatientID(String patientID);
}
