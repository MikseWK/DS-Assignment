package entity_implementation;

import entity_interface.DoctorInterface;
import java.io.Serializable;

public class Doctor implements DoctorInterface, Serializable {
    private String doctorID;
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String phoneNumber;

    public Doctor (String doctorID, String firstName, String lastName,
                  String specialization, String email, String phoneNumber) {
        this.doctorID = doctorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getDoctorID() { return doctorID; }
    @Override
    public void setDoctorID(String doctorID) { this.doctorID = doctorID; }

    @Override
    public String getFirstName() { return firstName; }
    @Override
    public void setFirstName(String firstName) { this.firstName = firstName; }

    @Override
    public String getLastName() { return lastName; }
    @Override
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public String getSpecialization() { return specialization; }
    @Override
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    @Override
    public String getEmail() { return email; }
    @Override
    public void setEmail(String email) { this.email = email; }

    @Override
    public String getPhoneNumber() { return phoneNumber; }
    @Override
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String getStatus() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setStatus(String status) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "ID='" + doctorID + '\'' +
                ", Name='" + getFullName() + '\'' +
                ", Specialization='" + specialization + '\'' +
                ", Email='" + email + '\'' +
                ", Phone='" + phoneNumber + '\'' +
                '}';
    }

    
}
