package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

public class Welcome {
    public void WelcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to Cryptic Shell!");
        System.out.println("Please select an option:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("0. Exit");

        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (choice) {
            case 1:
                System.out.println("Login selected.");
                login();
                break;
            case 2 :
                System.out.println("Register selected.");
                signup();
                break;
            case 3:
                System.out.println("Exiting the application.");
                System.exit(0);
                break;
        }
    }

    
    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email: ");
        String email = sc.nextLine();
        try {
            if (UserDAO.isUserExists(email)){
                String generatedOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email, generatedOTP);
                System.out.println("An OTP has been sent to your email. Please enter it to verify your account.");
                String enteredOTP = sc.nextLine();
                if(enteredOTP.equals(generatedOTP)){
                    System.out.println("Login successful!\n");
                    new UserView(email).home();
                }else{
                    System.out.println("Invalid OTP.");
                }
            }
            else{
                System.out.println("User does not exist. Please register first.");
            }
            // sc.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void signup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Enter your email: ");
        String email = sc.nextLine();


        String generatedOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email, generatedOTP);
        System.out.println("An OTP has been sent to your email. Please enter it to verify your account.");
        String enteredOTP = sc.nextLine();
        if(enteredOTP.equals(generatedOTP)){
            User user = new User(name, email);
            int response = UserService.saveUser(user);
            if(response == 0){
                System.out.println("User already exists. Please login.");
            }
            else {
                System.out.println("User registered successfully. Please login to continue.");

            }
            
        }else{
            System.out.println("Invalid OTP.");
        }

    }
}
