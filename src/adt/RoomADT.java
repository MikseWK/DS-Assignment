package adt;

public interface RoomADT {
    String getRoomID();
    String getDoctorName();
    boolean isAvailable();
    String getPatientID();

    void setCounterID(String counterID);  // This sets the Room ID
    void setDoctorName(String doctorName);
    void setAvailable(boolean available);
    void setPatientID(String patientID);
}
