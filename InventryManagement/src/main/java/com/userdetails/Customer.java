package com.userdetails;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.notification.Notification;
import com.searchdetails.Menu;


public class Customer extends Person {
	static Scanner sc = new Scanner(System.in);
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "Expleosolutions";

    private static int orderId; // Changed variable name
    private static String productName;
    private static int quantity;
	private static long paymentMethod;
	private static double price;

//    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
//        intro();
//    }

    public Customer() throws ClassNotFoundException, IOException, SQLException {
        super("defaultPersonId", "defaultFirstName", "defaultLastName", 'M', "defaultEmail", 0L, "defaultAddress",
                null, null, null);
        intro();
    }

    private static void intro() throws IOException, SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ____________________________________________");
        System.out.println("|   Welcome to Inventory Management Systems  |");
        System.out.println("|────────────────────────────────────────────|");
        System.out.println("| Type 1: Login                              |");
        System.out.println("| Type 2: Create Account                     |");
        System.out.println("| Type 3: Exit                               |");
        System.out.println("|____________________________________________|");

        int choiceAcc = getIntegerInput(scanner);

        switch (choiceAcc) {
            case 1:
                loginAccount();
                break;
            case 2:
                createAccount();
                break;
            case 3:
                System.out.println("Exiting the program...");
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect! Choose a valid option again.");
                intro();
        }
    }

    private static int getIntegerInput(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public static void loginAccount() throws IOException, SQLException, ClassNotFoundException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter username:");
            String username = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            if (isValidCredentials(username, password)) {
                System.out.println("Login successful!");
                showMenu();
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }
    }

    private static boolean isValidCredentials(String username, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM database.customer WHERE username = ? AND password = ?";
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

    public static void createAccount() {
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
    public static boolean isValidPhoneNumber(String phone_number) {
        // Validate phone number format using regular expression
        String regex = "\\d{10}"; // Assuming phone number is a 10-digit number
        return phone_number.matches(regex);
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
    

    // Show menu
    static void showMenu() throws SQLException, ClassNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1.Show the products \n2.Place Order \n3.Cancel Order \n4.Display Order \n5.Order Invoice \n6.Search Product \n7.Exit");
          //  System.out.println("Enter Your Choice:");

            int mainChoice = getIntegerInput(sc);

            switch (mainChoice) {
                case 1:
                    showProducts();
                    break;
                case 2:
                    placeOrder();
                    break;
                case 3:
                    cancelOrder();
                    break;
                case 4:
                    displayOrders();
                    break;
                case 5:
                    OrderInvoice();
                    break;
                case 6:
                    productSearch();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static int getIntegerInput1(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    static public Scanner objectScan() {
		return (new Scanner(System.in));
	}
	private static void productSearch() {
		// TODO Auto-generated method stub
    	  System.out.println("Enter the product to be searched: ");
    	  String productName=sc.nextLine();
    	  Menu.searchProduct(productName);
		
	}


	private static void showProducts() {
	    try {
	        String selectQuery = "SELECT * FROM DATABASE.products ORDER BY PRODUCT_ID ASC";
	        Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

	        PreparedStatement ps = connection.prepareStatement(selectQuery);
	        ResultSet result = ps.executeQuery();

	        // Print table header
	        System.out.println("-----------------------------------------------------------------------------------------------------------");
	        System.out.printf("| %-15s | %-25s | %-30s | %-15s | %-10s |\n", "Product ID", "Product Name", "Description", "Quantity", "Price");
	        System.out.println("-----------------------------------------------------------------------------------------------------------");

	        while (result.next()) {
	            // Adjust column names based on your actual database schema
	            int productId = result.getInt("PRODUCT_ID");
	            String productName = result.getString("PRODUCT_NAME");
	            String description = result.getString("DESCRIPTION");
	            int quantity = result.getInt("STOCK_QUANTITY");
	            double price = result.getDouble("PRICE");

	            // Print table row
	            System.out.printf("| %-15d | %-25s | %-30s | %-15d | $%,-9.2f |\n", productId, productName, description, quantity, price);
	        }

	        // Print table footer
	        System.out.println("-----------------------------------------------------------------------------------------------------------");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}





    public static void displayOrders() {
        try {
            String selectQuery = "SELECT * FROM DATABASE.CustomerOrder ORDER BY CUSTOMER_ORDER_ID ASC";
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            PreparedStatement ps = connection.prepareStatement(selectQuery);
            ResultSet result = ps.executeQuery();

            // Print table header
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-15s | %-15s | %-25s | %-10s | %-15s | %-10s |\n", "Order ID", "User ID", "Product Name", "Quantity", "Order Date", "Status");
            System.out.println("-------------------------------------------------------------------------------------------------------------------");

            while (result.next()) {
                // Adjust column names based on your actual database schema
                int orderId = result.getInt("CUSTOMER_ORDER_ID");
                int userId = result.getInt("USER_ID");
                String productName = result.getString("PRODUCT_NAME");
                int quantity = result.getInt("QUANTITY");
                Date orderDate = result.getDate("ORDER_DATE");
                String status = result.getString("STATUS");

                // Print table row
                System.out.printf("| %-15d | %-15d | %-25s | %-10d | %-15tF | %-10s |\n", orderId, userId, productName, quantity, orderDate, status);
            }

            // Print table footer
            System.out.println("-------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void placeOrder() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter product name: ");
            String productName = scanner.nextLine();

            if (isProductAvailable(productName)) {
                // Product is available
                System.out.println("Enter user ID: ");
                int userId ;
                if (scanner.hasNextInt()) {
                	userId = scanner.nextInt();
            	} else {
            	    System.out.println("Invalid input for user ID.");
            	    return; // or handle the error appropriately
            	}

                System.out.println("Enter order ID: ");
                int orderId;
            	if (scanner.hasNextInt()) {
            	    orderId = scanner.nextInt();
            	} else {
            	    System.out.println("Invalid input for order ID.");
            	    return; // or handle the error appropriately
            	}
            	
                System.out.println("Enter quantity: ");
                int quantity = scanner.nextInt();

                Date orderDate = new Date(System.currentTimeMillis());
                String status = "Pending"; // Set the initial status

                // Insert the order into the customerorders table
                String insertOrderQuery = "INSERT INTO database.CustomerOrder (CUSTOMER_ORDER_ID, USER_ID, PRODUCT_NAME, QUANTITY, ORDER_DATE, STATUS) VALUES (?, ?, ?, ?, ?, ?)";

                try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                     PreparedStatement ps = connection.prepareStatement(insertOrderQuery)) {
                    ps.setInt(1, orderId);
                    ps.setInt(2, userId);
                    ps.setString(3, productName);
                    ps.setInt(4, quantity);
                    ps.setDate(5, orderDate);
                    ps.setString(6, status);

                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Order placed successfully!");
                        updateStockQuantity(productName, quantity);

                        // Check stock level only if the stock quantity is below the minimum
                        if (isStockBelowMinimum(productName)) {
                        	 Admin.sendNotificationToAdmin(productName);
                            //checkStockLevel();
                        }
                    } else {
                        System.out.println("Failed to place the order.");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("Product not available.");
            }
        }
    }
   

	private static void sendNotificationToAdmin(String productName) {
        String message = "Stock level below minimum for product: " + productName;
        Notification adminNotification = new Notification();
        adminNotification.sendNotification(message);
    }


    private static boolean isStockBelowMinimum(String productName) {
        try {
            String selectQuery = "SELECT STOCK_QUANTITY FROM DATABASE.products WHERE PRODUCT_NAME = ?";
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
                ps.setString(1, productName);
                try (ResultSet result = ps.executeQuery()) {
                    if (result.next()) {
                        int currentStock = result.getInt("STOCK_QUANTITY");
                        return currentStock < 10;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    private static void updateStockQuantity(String productName, int quantity) {
        String updateQuery = "UPDATE database.products SET STOCK_QUANTITY = STOCK_QUANTITY - ? WHERE PRODUCT_NAME = ?";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(updateQuery)) {

            ps.setInt(1, quantity);
            ps.setString(2, productName);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(":)");
            } else {
                System.out.println(" ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isProductAvailable(String productName) {
        String selectQuery = "SELECT * FROM database.products WHERE LOWER(PRODUCT_NAME) = LOWER(?)";

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(selectQuery)) {

            ps.setString(1, productName.toLowerCase());

            try (ResultSet resultSet = ps.executeQuery()) {
                // If there is at least one result, the product is available
                return resultSet.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private static void cancelOrder() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
           

            System.out.print("Enter the Order ID to cancel: ");
            int orderId = sc.nextInt();

            String sql = "UPDATE database.CustomerOrder SET STATUS = 'CANCELLED' WHERE CUSTOMER_ORDER_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, orderId);
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Order with ID " + orderId + " canceled successfully");
                } else {
                    System.out.println("Order with ID " + orderId + " not found or already canceled");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    private static void OrderInvoice() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the Order ID: ");
            int orderId = scanner.nextInt();

            String sql = "SELECT o.CUSTOMER_ORDER_ID, o.USER_ID, c.NAME AS CUSTOMER_NAME, o.PRODUCT_NAME, o.QUANTITY, p.PRICE " +
                    "FROM database.CustomerOrder o " +
                    "JOIN database.Customer c ON o.USER_ID = c.USER_ID " +
                    "JOIN database.Products p ON o.PRODUCT_NAME = p.PRODUCT_NAME " +
                    "WHERE o.CUSTOMER_ORDER_ID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, orderId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int customerId = resultSet.getInt("USER_ID");
                        String customerName = resultSet.getString("CUSTOMER_NAME");
                        String productName = resultSet.getString("PRODUCT_NAME");
                        int quantity = resultSet.getInt("QUANTITY");
                        double unitCost = resultSet.getDouble("PRICE");

                        double totalCost = quantity * unitCost;

                        // Print table header
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.format("| %-15s | %-15s | %-25s | %-20s | %-10s | %-10s | %-10s |\n", "Order ID", "Customer ID", "Customer Name", "Product Name", "Quantity", "Unit Cost", "Total Cost");
                        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

                        // Print table row
                        System.out.format("| %-15d | %-15d | %-25s | %-20s | %-10d | $%-9.2f | $%-9.2f |\n", orderId, customerId, customerName, productName, quantity, unitCost, totalCost);

                        // Print totals
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------- ");
                    } else {
                        System.out.println("Order not found with ID: " + orderId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }}

//    private static void OrderInvoice() throws SQLException {
//        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
//           
//
//        	System.out.println("Entered Order ID: " + orderId);
//        	orderId =sc.nextInt();
//        	String sql = "SELECT o.CUSTOMER_ORDER_ID, o.USER_ID, c.NAME AS CUSTOMER_NAME, o.PRODUCT_NAME, o.QUANTITY, p.PRICE " +
//        	        "FROM database.CustomerOrder o " +
//        	        "JOIN database.Customer c ON o.USER_ID = c.USER_ID " +
//        	        "JOIN database.Products p ON o.PRODUCT_NAME = p.PRODUCT_NAME " +
//        	        "WHERE o.CUSTOMER_ORDER_ID = ?";
//
//
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setInt(1, orderId);
//
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    if (resultSet.next()) {
//                    	int customerId = resultSet.getInt("USER_ID");
//                        String customerName = resultSet.getString("CUSTOMER_NAME");
//                        String productName = resultSet.getString("PRODUCT_NAME");
//                        int quantity = resultSet.getInt("QUANTITY");
//                        double unitCost = resultSet.getDouble("PRICE");
//                        double totalCost = quantity * unitCost;
//
//                        // Print table header
//                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
//                        System.out.format("| %-15s | %-15s | %-25s | %-20s | %-10s | %-10s | %-10s |\n", "Order ID", "Customer ID", "Customer Name", "Product Name", "Quantity", "Unit Cost", "Total Cost");
//                        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
//
//                        // Print table row
//                        System.out.format("| %-15d | %-15d | %-25s | %-20s | %-10d | $%-9.2f | $%-9.2f |\n", orderId, customerId, customerName, productName, quantity, unitCost, totalCost);
//
//                        // Print totals
//                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------ ");
//                    } else {
//                        System.out.println("No results found for Order ID: " + orderId);
//                    }
//                }
//            } catch (SQLException e) {
//                System.out.println("SQL Exception:");
//                e.printStackTrace();
//            }}}
    
   /* public static void checkStockLevel() {
        try {
            String selectQuery = "SELECT * FROM DATABASE.products WHERE STOCK_QUANTITY < 10";
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            PreparedStatement ps = connection.prepareStatement(selectQuery);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                String productName = result.getString("PRODUCT_NAME");
                int currentStock = result.getInt("STOCK_QUANTITY");

                Admin.sendNotificationToAdmin("Stock level below minimum for product: " + productName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


	public static int getOrderId() {
		return orderId;
	}

	public static void setOrderId(int orderId) {
		Customer.orderId = orderId;
	}

	public static int getQuantity() {
		return quantity;
	}

	public static void setQuantity(int quantity) {
		Customer.quantity = quantity;
	}


    }


