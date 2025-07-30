package adt_implementation;

import adt.RoomADT;

public class Room implements RoomADT {
    private String roomID;
    private String doctorName;
    private boolean available;
    private String patientID;
    
    public Room() {
        this.roomID = "";
        this.doctorName = "";
        this.available = true; 
        this.patientID = null;
    }

    public Room(String roomID, String doctorName, boolean available) {
        this.roomID = roomID;
        this.doctorName = doctorName;
        this.available = available;
        this.patientID = null;
    }

    @Override
    public String getRoomID() {
        return this.roomID;
    }

    @Override
    public String getDoctorName() {
        return this.doctorName;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getPatientID() {
        return this.patientID;
    }

    @Override
    public void setCounterID(String counterID) {
        this.roomID = counterID;
    }

    @Override
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
}
