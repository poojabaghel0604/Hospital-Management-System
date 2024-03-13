package HospitalManagementSystem;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Scanner;

import static HospitalManagementSystem.query.*;

public class Patient {
    private Connection connection;
    private Scanner scanner;
    public Patient(Connection connection,Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }
    public void addPatient(){
        System.out.print("ENTER PATIENT NAME ");
        String name = scanner.next();
        System.out.print("ENTER PATIENT AGE ");
        int age = scanner.nextInt();
        System.out.print("ENTER PATIENT GENDER ");
        String gender = scanner.next();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(addp);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println(" patients added succesfuly..");
            }
            else {
                System.out.println("failed to add patients ..");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void viewPatients(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getp);
            ResultSet resultset = preparedStatement.executeQuery();
            System.out.println("Patients");
            System.out.println("");
            while(resultset.next()){
                int id = resultset.getInt("id");
                int age = resultset.getInt("age");
                String name = resultset.getString("name");
                String gender = resultset.getString("gender");
                System.out.printf("|%-12s|%-20s|%-10s|%-12s|\n",id,name,age,gender);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public boolean getPatient(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement((getpone));
            preparedStatement.setInt(1,id);
            ResultSet resultset = preparedStatement.executeQuery();
            if(resultset.next()){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
