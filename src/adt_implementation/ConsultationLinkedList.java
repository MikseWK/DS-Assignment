package adt_implementation;

import adt.CustomizeADT;

public class ConsultationLinkedList implements ConsultationListADT {
    private ConsultationNodeADT<ConsultationADT> head;
    private int size;

    public ConsultationLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addConsultation(ConsultationADT consultation) {
        ConsultationNode<ConsultationADT> newNode = new ConsultationNode<>(consultation);
        if (head == null) {
            head = newNode;
        } else {
            ConsultationNodeADT<ConsultationADT> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    @Override
    public boolean removeConsultation(String consultationID) {
        if (head == null) return false;

        if (head.getData().getConsultationID().equals(consultationID)) {
            head = head.getNext();
            size--;
            return true;
        }

        ConsultationNodeADT<ConsultationADT> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().getConsultationID().equals(consultationID)) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public ConsultationADT getConsultation(String consultationID) {
        ConsultationNodeADT<ConsultationADT> current = head;
        while (current != null) {
            if (current.getData().getConsultationID().equals(consultationID)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public ConsultationADT[] getAllConsultations() {
        ConsultationADT[] consultations = new ConsultationADT[size];
        ConsultationNodeADT<ConsultationADT> current = head;
        int index = 0;
        while (current != null) {
            consultations[index++] = current.getData();
            current = current.getNext();
        }
        return consultations;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean setConsultation(String consultationID, ConsultationADT newConsultation) {
        ConsultationNodeADT<ConsultationADT> current = head;
        while (current != null) {
            if (current.getData().getConsultationID().equals(consultationID)) {
                current.setData(newConsultation);
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
    
    @Override
    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No consultations recorded.");
            return;
        }

        ConsultationNodeADT<ConsultationADT> current = head;
        int count = 1;

        System.out.println("===== Consultation History =====");
        while (current != null) {
            ConsultationADT consultation = current.getData();
            System.out.println(count++ + ". Consultation ID: " + consultation.getConsultationID());
            System.out.println("   Patient ID: " + consultation.getPatientID());
            System.out.println("   Doctor Name: " + consultation.getDoctorName());
            System.out.println("   Diagnosis: " + consultation.getDiagnosis());
            System.out.println("   Date: " + consultation.getFollowUpDate());
            System.out.println("--------------------------------");
            current = current.getNext();
        }
    }
    
    
}
