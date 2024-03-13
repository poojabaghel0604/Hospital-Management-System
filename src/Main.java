import HospitalManagementSystem.HospitalManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to our Hospital Management System");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 for login as Doctor");
        System.out.println("Enter 2 for login as Patient");
        int choise = sc.nextInt();
        if(choise == 1){
            HospitalManagementSystem s = new HospitalManagementSystem();
            s.menu();
        }
        if(choise == 2){

        }
        else{
            System.out.println("Invalid choise");
        }
    }
}