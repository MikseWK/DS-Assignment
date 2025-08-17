package adt_implementation;

import adt.CustomizeADT;

public class QueueTicket implements QueueTicketADT {
    private int ticketNumber;
    private String patientID;
    private String roomID;
    
    public QueueTicket() {
        this.ticketNumber = 0;
        this.patientID = "";
        this.roomID = "";
    }

    public QueueTicket(int ticketNumber, String patientID, String roomID) {
        this.ticketNumber = ticketNumber;
        this.patientID = patientID;
        this.roomID = roomID;
    }

    @Override
    public int getTicketNumber() {
        return ticketNumber;
    }

    @Override
    public String getPatientID() {
        return patientID;
    }

    @Override
    public String getRoomID() {
        return roomID;
    }
}