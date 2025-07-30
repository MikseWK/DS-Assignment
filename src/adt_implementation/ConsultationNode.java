package adt_implementation;

import adt.ConsultationNodeADT;

public class ConsultationNode<T> implements ConsultationNodeADT<T> {
    private T data;
    private ConsultationNodeADT<T> next;

    public ConsultationNode(T data) {
        this.data = data;
        this.next = null;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public ConsultationNodeADT<T> getNext() {
        return next;
    }

    @Override
    public void setNext(ConsultationNodeADT<T> next) {
        this.next = next;
    }
}
