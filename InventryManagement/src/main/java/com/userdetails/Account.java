package com.userdetails;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
	 private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	 private static final String USERNAME = "system";
	 private static final String PASSWORD = "Expleosolutions";


	 public static void createAccFun() {
	        try (Scanner scanner = new Scanner(System.in)) {
	            System.out.println("Enter ID:");
	            int Id = scanner.nextInt();
	            scanner.nextLine();
	            System.out.println("Enter username:");
	            String username = scanner.nextLine();
	            System.out.println("Enter password:");
	            String password = scanner.nextLine();
	            System.out.println("Enter role:");
	            String role = scanner.nextLine();
	            System.out.println("Enter your name:");
	            String name = scanner.nextLine();
	            System.out.println("Enter your address:");
	            String address = scanner.nextLine();
	            System.out.println("Enter your phone number:");
	            String phone_number = scanner.nextLine();

	            if (isValidUsername(username) && isValidPassword(password) && isValidRole(role) && isValidName(name) && isValidPhoneNumber(phone_number)) {
	                try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	                    String sql = "INSERT INTO database.ACCOUNT(User_Id,username,password,role,name,address,phone_number) VALUES (?,?,?,?,?,?,?)";
	                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                        statement.setInt(1, Id);
	                        statement.setString(2, username);
	                        statement.setString(3, password);
	                        statement.setString(4, role);
	                        statement.setString(5, name);
	                        statement.setString(6, address);
	                        statement.setString(7, phone_number);
	                        int rowsInserted = statement.executeUpdate();
	                        if (rowsInserted > 0) {
	                            System.out.println("User registered successfully!");
	                        } else {
	                            System.out.println("Failed to register user.");
	                        }
	                    }
	                } catch (SQLIntegrityConstraintViolationException e) {
	                    System.out.println("Username '" + username + "' already exists. Please choose a different username.");
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            } else {
	                System.out.println("Invalid username, password, role, name, or phone number format.");
	            }

	        }
	    }
	    public static boolean isValidRole(String role) {
	        // Validate role format using regular expression
	        String regex = "^[a-zA-Z]+$"; // Only alphabets allowed
	        return role.matches(regex);
	    }
	    public static boolean isValidName(String name) {
	        // Validate name format using regular expression
	        String regex = "[a-zA-Z ]+"; // Only letters and spaces allowed
	        return name.matches(regex);
	    }

	    private static boolean isValidUsername(String username) {
	        // Username regex pattern: any string is valid for now, change the regex pattern according to your requirements
	        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(username);

	        return matcher.matches();
	    }


	    private static boolean isValidPassword(String password) {
	        // Password regex pattern: at least 8 characters, containing at least one uppercase letter, one lowercase letter, one digit, and one special character
	        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
	        Matcher matcher = pattern.matcher(password);
	        return matcher.matches();
	    }
	    
	  //LOG IN 

	    public static void loginFun() throws IOException, SQLException {
	        try (Scanner scanner = new Scanner(System.in)) {
	            System.out.println("Enter username:");
	            String username = scanner.nextLine();
	            System.out.println("Enter password:");
	            String password = scanner.nextLine();
	            
	            if (isValidCredentials(username, password)) {
	                System.out.println("Login successful! Welcome!");
	                Admin.showMenu();
	            } else {
	                System.out.println("Invalid username or password. Please try again.");
	            }
	        }
	    }

	    public static boolean isValidPhoneNumber(String phone_number) {
	        // Validate phone number format using regular expression
	        String regex = "\\d{10}"; // Assuming phone number is a 10-digit number
	        return phone_number.matches(regex);
	    }
	    
	    private static boolean isValidCredentials(String username, String password) {
	        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	            String sql = "SELECT * FROM database.ACCOUNT WHERE username = ? AND password = ? ";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, username);
	                statement.setString(2, password);
	               

	                try (ResultSet resultSet = statement.executeQuery()) {
	                    return resultSet.next();
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	}
	    
	    
	  

