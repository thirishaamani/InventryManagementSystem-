package com.drivermanager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.userdetails.Admin;
import com.userdetails.Customer;
import com.userdetails.Supplier;


public class DriverClass {
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME TO INVENTORY MANAGEMENT");
        System.out.println("Welcome!!! Are you \n1.Admin \n2.Customer \n3.Supplier \n4.Exit");

        int choice = getIntegerInput(scanner);

        switch (choice) {
            case 1:
                Admin admin = new Admin();
                break;
            case 2:
                Customer customer = new Customer();
            case 3:
            	Supplier supplier= new Supplier();
//                Notification customerNotification = new Notification();
//                customerNotification.sendNotification("Dear " + customer.getUserName() + ", your product has been placed successfully.");
                break;
            case 4:
                System.out.println("Exiting the program...");
                System.exit(0); // Exit the program
                break; 
            default:
                System.out.println("Please enter a valid number");
        }

        scanner.close();
    }

    private static int getIntegerInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}



class Notification {
    public void sendNotification(String message) {
        // Logic to send notification
        System.out.println("Sending notification: " + message);
    }
}
