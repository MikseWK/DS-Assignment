package adt;

public interface ConsultationNodeADT<T> {
    T getData();
    void setData(T data);
    ConsultationNodeADT<T> getNext();
    void setNext(ConsultationNodeADT<T> next);
}
