package HospitalManagementSystem;

public class query {
    static String getp = "select * from PATIENTS";
    static String getd = "SELECT * FROM doctors WHERE id=?";
    static String addp = "INSERT INTO PATIENTS(name,age,gender)VALUES(?,?,?)";
    static String getpone = "SELECT * FROM PATIENTS WHERE id=?";
}
