package adt;

public interface ConsultationListADT {
    void addConsultation(ConsultationADT consultation);
    boolean removeConsultation(String consultationID);
    ConsultationADT getConsultation(String consultationID);
    ConsultationADT[] getAllConsultations();
    int getSize();
    boolean isEmpty();
    boolean setConsultation(String consultationID, ConsultationADT newConsultation);
    void clear();
     void displayAll();
}
