package HospitalManagementSystem;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String username = "root";
    private static final String password = "root";
    public static void menu(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            Patient patient = new Patient(connection,scanner);
            Doctors doctors = new Doctors(connection);
            while (true){
                System.out.println("HOSPITAL MANAGEMETN SYSTEM ");
                System.out.println("1 Add Patients");
                System.out.println("2 View Patients ");
                System.out.println("3 View Doctors ");
                System.out.println("4 Book Appointment ");
                System.out.println("5 Exits ");
                System.out.println("Enter Your Choise ");
                int choise = scanner.nextInt();
                switch (choise){
                    case 1 :
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2 :
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3 :
                        doctors.viewDoctors();
                        System.out.println();
                        break;
                    case 4 :
                        BookAppointment(patient,doctors,connection,scanner);
                        System.out.println();
                        break;
                    case 5 :
                        return;
                    default:
                        System.out.println("INVALID CHOISE ");
                        break;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void BookAppointment(Patient patient,Doctors doctors,Connection connection,Scanner scanner){
        System.out.println("Enter Patients ID ");
        int patientID = scanner.nextInt();
        System.out.println("Enter Doctor Id ");
        int doctorID = scanner.nextInt();
        System.out.println("Enter appointment sate (YYYY.MM.DD) : ");
        String date = scanner.next();
        if(patient.getPatient(patientID) && doctors.getDoctor(doctorID)){
            if(checkDoctorAvailability(doctorID,date,connection)){
                String appointmentQuery = "INSERT INTO APPOINTMENTS(patient_id,doctors_id,appointment_date)VALUES(?,?,?)";
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1,patientID);
                    preparedStatement.setInt(2,doctorID);
                    preparedStatement.setString(3,date);

                    int affectRow = preparedStatement.executeUpdate();
                    if(affectRow>0){
                        System.out.println("Appointment Booked ");
                    }
                    else{
                        System.out.println("Failed to Book appointment ");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                System.out.println("Doctor is not available for this date");
            }
        }
        else{
            System.out.println("Either doctor or patient does not exist!!");
        }
    }
    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection){
        String query = "SELECT COUNT(*) FROM appointments WHERE doctors_id = ? AND appointment_date = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if(count==0){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
