package adt_implementation;

import adt.CustomizeADT;

public class Patient implements PatientADT {
    private String patientID;
    private String name;
    private int age;
    private String gender;

    public Patient(String patientID) {
        this.patientID = patientID;
        this.name = "";
        this.age = 0;
        this.gender = "";
    }
    
    public Patient(String patientID, String name) {
        this.patientID = patientID;
        this.name = name;
        this.age = 0;
        this.gender = "";
    }

    public Patient(String patientID, String name, int age, String gender) {
        this.patientID = patientID;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String getPatientID() {
        return patientID;
    }

    @Override
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "PatientID: " + patientID + ", Name: " + name + ", Age: " + age + ", Gender: " + gender;
    }
}
