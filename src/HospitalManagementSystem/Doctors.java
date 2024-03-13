package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static HospitalManagementSystem.query.getd;

public class Doctors {
        private Connection connection;
        public Doctors(Connection connection){
            this.connection=connection;
        }
        public void viewDoctors(){
            String query = "SELECT * FROM doctors";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultset = preparedStatement.executeQuery();
                System.out.println("Doctors are " );
                System.out.println("");
                while(resultset.next()){
                    int id = resultset.getInt("id");
                    String name = resultset.getString("name");
                    String specialization = resultset.getString("specialization");
                    System.out.printf("|%-12s|%-20s|%-10s|\n",id,name,specialization);
                }

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        public boolean getDoctor(int id){
            try {
                PreparedStatement preparedStatement = connection.prepareStatement((getd));
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
