package implementation;

public class Patient {
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

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "PatientID: " + patientID + ", Name: " + name + ", Age: " + age + ", Gender: " + gender;
    }
}
