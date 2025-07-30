package adt;

public interface ConsultationQueueADT {
    void enqueue(PatientADT patient);
    PatientADT dequeue();
    PatientADT peek();
    boolean isEmpty();
    int size();
    void clear();
    void traverseAndPrint();
}

