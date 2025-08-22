package manager;

import customizeADT.CustomizeADT;
import entity_interface.DoctorInterface; 
import manager_interface.DoctorManagerInterface;

public class DoctorMaintenance implements DoctorManagerInterface {
    
    private CustomizeADT<String, DoctorInterface, ?> doctorADT;


    public DoctorMaintenance(CustomizeADT<String, DoctorInterface, ?> doctorADT) {
        this.doctorADT = doctorADT;
    }

    // Default constructor
    public DoctorMaintenance() {
        this(new CustomizeADT<>());
    }

    // Update method signature to match the interface
    @Override
    public boolean addDoctor(DoctorInterface doctor) {
        if (doctorADT.containsKey(doctor.getDoctorID())) return false; // avoid duplicates
        doctorADT.put(doctor.getDoctorID(), doctor);
        return true;
    }

    // Update return type to match the interface
    @Override
    public DoctorInterface getDoctor(String doctorID) {
        return doctorADT.get(doctorID);
    }

    // Update method signature to match the interface
    @Override
    public boolean updateDoctor(String doctorID, DoctorInterface updatedDoctor) {
        if (!doctorADT.containsKey(doctorID)) return false;
        doctorADT.put(doctorID, updatedDoctor);
        return true;
    }

    // Remove a doctor by ID
    @Override
    public boolean removeDoctor(String doctorID) {
        if (!doctorADT.containsKey(doctorID)) return false;
        doctorADT.remove(doctorID);
        return true;
    }

    // Display all doctors
    public void displayDoctors() {
        if (doctorADT.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }

        DoctorInterface[] allDoctors = doctorADT.values();
        System.out.println("===================================================================");
        System.out.printf("| %-5s | %-10s | %-15s | %-15s | %-15s |\n",
                "No", "Doctor ID", "First Name", "Last Name", "Specialty");
        System.out.println("===================================================================");
        for (int i = 0; i < allDoctors.length; i++) {
            DoctorInterface doc = allDoctors[i];
            if (doc != null) {
                System.out.printf("| %-5d | %-10s | %-15s | %-15s | %-15s |\n",
                        (i + 1),
                        doc.getDoctorID(),
                        doc.getFirstName(),
                        doc.getLastName(),
                        doc.getSpecialization());
            }
        }
        System.out.println("===================================================================");
    }

    // Check if doctor exists
    @Override
    public boolean containsDoctor(String doctorID) {
        return doctorADT.containsKey(doctorID);
    }

    // Get total number of doctors
    @Override
    public int getDoctorCount() {
        return doctorADT.size();
    }

    // Update return type to match the new field type
    @Override
    public CustomizeADT<String, DoctorInterface, ?> getAllDoctors() {
        return this.doctorADT;
    }
}