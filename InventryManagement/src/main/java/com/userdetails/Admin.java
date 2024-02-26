//package com.userdetails;
//
//import java.io.IOException;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.InputMismatchException;
//import java.util.List;
//import java.util.Scanner;
//
//public class Admin extends BuisnessUsers {
//
//    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
//    private static final String USERNAME = "system";
//    private static final String PASSWORD = "Expleosolutions";
//
//    private static Connection con;
//    static List<Product> productList = new ArrayList<Product>();
//
//    public Admin() throws ClassNotFoundException, IOException, SQLException {
//        super("defaultPersonId", "defaultFirstName", "defaultLastName", 'M', "defaultEmail", 0L, "defaultAddress",
//                null, null, null);
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//        intro();
//    }
//
//
//    public static void intro() throws IOException, SQLException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println(" ____________________________________________");
//        System.out.println("|   Welcome to Inventory Management Systems  |");
//        System.out.println("|────────────────────────────────────────────|");
//        System.out.println("| Type 1: Create Account                     |");
//        System.out.println("| Type 2: Login                              |");
//        System.out.println("| Type 3: Exit                               |");
//        System.out.println("|____________________________________________|");
//
//        int choiceAcc = scanner.nextInt();
//
//        switch (choiceAcc) {
//            case 1:
//                Account.createAccFun();
//                break;
//            case 2:
//                Account.loginFun();
//                break;
//            case 3:
//                System.out.println("Exiting the program...");
//                System.exit(0); // Exit the program
//                break;
//            default:
//                System.out.println("Incorrect! Choose a valid option again.");
//                intro();
//        }
//    }
//
//
////  
////    private static int getIntegerInput(Scanner scanner) {
////        while (true) {
////            try {
////                return scanner.nextInt();
////            } catch (InputMismatchException e) {
////                System.out.println("Invalid input. Please enter a number.");
////                scanner.nextLine(); // Consume the invalid input
////            }
////        }
////    }
//
//
//
//    public static void showMenu() throws SQLException, IOException {
//        Scanner sc = new Scanner(System.in);
//        boolean exit = false;
//
//        while (!exit) {
//            System.out.println("1.Inventory Tracking \n2.Supplier Management \n3.Profile Management\n4.Order Management\n5.Exit");
//            System.out.println("Enter Your Choice:");
//
//            int mainChoice = sc.nextInt();
//
//            switch (mainChoice) {
//                case 1:
//                    handleInventoryManagement();
//                    break;
//                case 2:
//                    handleSupplierManagement();
//                    break;
//                case 3:
//                    handleProfileManagement();
//                    break;
//                case 4:
//                    handleOrderManagement();
//                    break;
//                case 5:
//                    System.out.println("Exiting the program...");
//                    exit = true;
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please enter a valid option.");
//            }
//        }
//        sc.close();
//    }
//
//    public static void handleInventoryManagement() throws SQLException {
//        boolean exitInventory = false;
//        Scanner sc = new Scanner(System.in);
//
//        while (!exitInventory) {
//            System.out.println("1. Add Product");
//            System.out.println("2. Update Product");
//            System.out.println("3. Delete Product");
//            System.out.println("4. View Products");
//            System.out.println("5. Go Back to Main Menu");
//            System.out.println("Enter Your Choice:");
//
//            if (sc.hasNextInt()) {
//                int inventoryChoice = sc.nextInt();
//                switch (inventoryChoice) {
//                    case 1:
//                        addProduct();
//                        break;
//                    case 2:
//                        updateProduct();
//                        break;
//                    case 3:
//                        deleteProduct();
//                        break;
//                    case 4:
//                        viewProducts();
//                        break;
//                    case 5:
//                        System.out.println("Returning to main menu...");
//                        exitInventory = true;
//                        break;
//                    default:
//                        System.out.println("Invalid choice. Please enter a valid option.");
//                        break;
//                }
//            } else {
//                System.out.println("Invalid input. Please enter a valid option.");
//                sc.next(); // Consume invalid input to avoid infinite loop
//            }
//        }
//        sc.close();
//    }
//
//    public static void handleSupplierManagement() throws SQLException {
//        boolean exitSupplier = false;
//
//        while (!exitSupplier) {
//            System.out.println("1. Add a Supplier\n2. Update a Supplier\n3.Display the suppliers\n4. Back to Main Menu");
//            System.out.println("Enter Your Choice");
//
//            Scanner sc = new Scanner(System.in);
//            int supplierChoice = sc.nextInt();
//
//            switch (supplierChoice) {
//                case 1:
//                	addSupplier();
//                    break;
//
//                case 2:
//                    updateSupplier();
//                    break;
//
//                case 3:
//                    showSuppliers();
//                    break;
//
//                case 4:
//                	 System.out.println("Exiting the program...");
//                    exitSupplier = true;
//                    break;
//
//                default:
//                    System.out.println("Invalid choice. Please enter a valid option.");
//            }
//        }
//    }
//
//    public static void handleProfileManagement() throws SQLException {
//        boolean exitProfile = false;
//        Scanner sc = new Scanner(System.in);
//
//        while (!exitProfile) {
//            System.out.println("1. Update Profile Management\n2. Display Details\n3. Back to Main Menu");
//            System.out.println("Enter Your Choice");
//
//            int profileChoice = sc.nextInt();
//
//            switch (profileChoice) {
//                case 1:
//                    updateProfile();
//                    break;
//                case 2:
//                    DisplayProfile();
//                    break;
//                case 3:
//                    System.out.println("Exiting profile management...");
//                    exitProfile = true;
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please enter a valid option.");
//            }
//        }
//        sc.close();
//    }
//
//    private static Connection con1;
//   // private static List<Product> productList1 = new ArrayList<>();
//	
//	
//	//Inventry management starts here
//	 public static void addProduct() {
//	        Scanner sc = new Scanner(System.in);
//	        System.out.println("Enter the Product Details");
//	        System.out.println("***************************");
//
//	        System.out.println("Enter the Product ID:");
//	        int productId = sc.nextInt();
//	        sc.nextLine();
//
//	        System.out.println("Enter the Product Name:");
//	        String productName = sc.nextLine();
//
//	        System.out.println("Enter the Product Description:");
//	        String productDescription = sc.nextLine();
//
//	        System.out.println("Enter the Product Price:");
//	        double price = sc.nextDouble();
//
//	        System.out.println("Enter the Product Quantity:");
//	        int quantity = sc.nextInt();
//
//	       
//
//	        Product product = new Product(productId, productName, productDescription, price, quantity);
//	        productList.add(product);
//	        addToDatabase(product);
//	    }
//	 
//	 public static void updateProduct() throws SQLException {
//	        Scanner sc = new Scanner(System.in);
//	        System.out.println("Enter the Product Details to Update");
//	        System.out.println("Enter the Product ID");
//	        int productID = sc.nextInt();
//	        sc.nextLine();
//	        boolean isAvailable = findProduct(productID);
//	        if (isAvailable) {
//	            System.out.println("Enter the Product Name");
//	            String productName = sc.nextLine();
//	            System.out.println("Enter the Product description");
//	            String description = sc.nextLine();
//	            System.out.println("Enter the Product Price");
//	            double price = sc.nextDouble();
//	            System.out.println("Enter the Product Quantity");
//	            int quantity = sc.nextInt();
//
//	            updateDatabase(productID, productName, description, price, quantity);
//	        } else {
//	            System.out.println("Product Not Found");
//	        }
//	  }
//	 public static void deleteProduct() {
//	        Scanner sc = new Scanner(System.in);
//	        System.out.println("Enter the Product ID to delete:");
//	        int productId = sc.nextInt();
//
//	        boolean isAvailable = findProduct(productId);
//	        if (isAvailable) {
//	            String deleteQuery = "DELETE FROM Database.Products WHERE PRODUCT_ID = ?";
//	            try (PreparedStatement ps = con.prepareStatement(deleteQuery)) {
//	                ps.setInt(1, productId);
//
//	                int affectedRows = ps.executeUpdate();
//
//	                if (affectedRows > 0) {
//	                    System.out.println("Product deleted from the Database");
//	                } else {
//	                    System.out.println("Failed to delete product from the Database.");
//	                }
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        } else {
//	            System.out.println("Product Not Found");
//	        }
//	    }
//	  
//	 public static void viewProducts() {
//		    String query = "SELECT PRODUCT_ID, PRODUCT_NAME, DESCRIPTION, PRICE, STOCK_QUANTITY FROM Database.Products";
//
//		    try (Statement statement = con.createStatement();
//		         ResultSet resultSet = statement.executeQuery(query)) {
//
//		        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
//		        System.out.printf("| %-10s | %-20s | %-30s | %-10s | %-15s |\n",
//		                "Product ID", "Product Name", "Description", "Price", "Quantity");
//		        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
//
//		        while (resultSet.next()) {
//		            int productId = resultSet.getInt("PRODUCT_ID");
//		            String productName = resultSet.getString("PRODUCT_NAME");
//		            String description = resultSet.getString("DESCRIPTION");
//		            double price = resultSet.getDouble("PRICE");
//		            int quantity = resultSet.getInt("STOCK_QUANTITY");
//
//		            System.out.printf("| %-10s | %-20s | %-30s | $%-9.2f | %-15s |\n",
//		                    productId, productName, description, price, quantity);
//		        }
//
//		        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
//
//		    } catch (SQLException e) {
//		        e.printStackTrace();
//		    }
//		}
//
//	  //Inventry management ends here
//	  
//	  
//	  //Profile management starts here
//	  private static String currentUserId = null;
//
//	    private static String getCurrentUserId() {
//	        return currentUserId;
//	    }
//
//	    // Set the current user ID when the user logs in
//	    public static void setCurrentUserId(String userId) {
//	        currentUserId = userId;
//	    }
//	    
//	    public static boolean userExists(int userId) throws SQLException {
//	        String query = "SELECT USER_ID FROM database.ACCOUNT WHERE USER_ID = ?";
//
//	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
//	            preparedStatement.setInt(1, userId);
//
//	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//	                return resultSet.next();
//	            }
//	        }
//	    }
//	    
//	    
//	    //updateProfile
//	    @SuppressWarnings("unused")
//		//static
//	    public static void updateProfile() throws SQLException {
//	        Scanner scanner = new Scanner(System.in);
//	        System.out.println("Enter the ID to update profile:");
//
//	        int userId = scanner.nextInt();
//	        scanner.nextLine(); // Consume the newline character
//
//	        // Check if the user with the provided ID exists in the database
//	        if (userExists(userId)) {
//	            System.out.println("User found! Enter new details for the profile:");
//
//	            System.out.print("Enter new password: ");
//	            String newPassword = scanner.nextLine();
//
//	            System.out.print("Enter new address: ");
//	            String newAddress = scanner.nextLine();
//
//	            System.out.print("Enter new phone number: ");
//	            String newPhoneNumber = scanner.nextLine();
//
//	            // Validate phone number (exactly 10 digits)
//	            if (!newPhoneNumber.matches("\\d{10}")) {
//	                System.out.println("Phone number must be exactly 10 digits.");
//	                return;
//	            }
//
//	           
//
//	            try {
//	                // Assuming you have a method to get the current user's ID
//	                String currentUserId = getCurrentUserId();
//
//	                String updateQuery = "UPDATE database.ACCOUNT SET PASSWORD=?, ADDRESS=?, PHONE_NUMBER=? WHERE USER_ID=?";
//	                try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
//	                    preparedStatement.setString(1, newPassword);
//	                    preparedStatement.setString(2, newAddress);
//	                    preparedStatement.setString(3, newPhoneNumber);
//	                    preparedStatement.setInt(4, userId);
//
//	                    int rowsUpdated = preparedStatement.executeUpdate();
//
//	                    if (rowsUpdated > 0) {
//	                        System.out.println("Profile updated successfully.");
//	                    } else {
//	                        System.out.println("Failed to update profile.");
//	                    }
//	                }
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        } else {
//	            System.out.println("User with ID " + userId + " not found in the database.");
//	        }
//	    }
//
//	    //DisplayProfile
//	    public static void DisplayProfile() {
//	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
//	        System.out.printf("| %-10s | %-15s | %-10s | %-30s | %-20s | %-15s | %-30s |\n", "User ID", "Username", "Role", "Name", "Phone Number", "Address", "Password");
//	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
//
//	        String query = "SELECT USER_ID, USERNAME, ROLE, NAME, PHONE_NUMBER, ADDRESS, PASSWORD FROM database.ACCOUNT";
//	        try (PreparedStatement preparedStatement = con.prepareStatement(query);
//	             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//	            while (resultSet.next()) {
//	                String userId = resultSet.getString("USER_ID");
//	                String username = resultSet.getString("USERNAME");
//	                String role = resultSet.getString("ROLE");
//	                String name = resultSet.getString("NAME");
//	                String phoneNumber = resultSet.getString("PHONE_NUMBER");
//	                String address = resultSet.getString("ADDRESS");
//	                String password = resultSet.getString("PASSWORD");
//
//	                System.out.printf("| %-10s | %-15s | %-10s | %-30s | %-20s | %-15s | %-30s |\n",
//	                        userId, username, role, name, phoneNumber, address, password);
//	            }
//	        } catch (SQLException e) {
//	            System.out.println("An error occurred while fetching the profile information.");
//	            e.printStackTrace();
//	        }
//
//	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
//	    }
//
//	    
//	    //DisplayProfile ends here
//	    public static void updateDatabase(int productID, String productName, String description, double price, int quantity) {
//	        String update = "UPDATE Database.Products SET PRODUCT_NAME = ?, DESCRIPTION = ?, PRICE = ?, STOCK_QUANTITY = ? WHERE PRODUCT_ID = ?";
//
//	        try (PreparedStatement ps = con.prepareStatement(update)) {
//	            ps.setString(1, productName);
//	            ps.setString(2, description);
//	            ps.setDouble(3, price);
//	            ps.setInt(4, quantity);
//	            ps.setInt(5, productID);
//
//	            int affectedRows = ps.executeUpdate();
//
//	            if (affectedRows > 0) {
//	                System.out.println("Product updated in the Database");
//	            } else {
//	                System.out.println("Failed to update product in the Database.");
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	   
//
//	    public static boolean findProduct(int productId) {
//	        String query = "SELECT PRODUCT_ID FROM Database.Products WHERE PRODUCT_ID = ?";
//
//	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
//
//	            preparedStatement.setInt(1, productId);
//
//	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//	                return resultSet.next();
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	            return false;
//	        }
//	    }
//
//	    public static void addToDatabase(Product product) {
//	        String insert = "INSERT INTO Database.Products (PRODUCT_ID, PRODUCT_NAME, DESCRIPTION, PRICE, STOCK_QUANTITY) VALUES ( ?, ?, ?, ?, ?)";
//
//	        try (PreparedStatement ps = con.prepareStatement(insert)) {
//	            ps.setInt(1, product.getProductId());
//	            ps.setString(2, product.getProductName());
//	            ps.setString(3, product.getProductDescription());
//	            ps.setDouble(4, product.getPrice());
//	            ps.setInt(5, product.getProductQuantity());
//	           
//
//	            int affectedRows = ps.executeUpdate();
//
//	            if (affectedRows > 0) {
//	                System.out.println("Product added into the Database");
//	            } else {
//	                System.out.println("Failed to add product into the Database.");
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	     void fetchProductdetails() {
//	        String dataFetch = "SELECT PRODUCT_ID, PRODUCT_NAME, DESCRIPTION, PRICE, STOCK_QUANTITY FROM Database.Products";
//	        try (Statement state = con.createStatement();
//	             ResultSet set = state.executeQuery(dataFetch)) {
//	            while (set.next()) {
//	                int DBproductId = set.getInt("PRODUCT_ID");
//	                String DBproductName = set.getString("PRODUCT_NAME");
//	                String DBdescription = set.getString("DESCRIPTION");
//	                double DBprice = set.getDouble("PRICE");
//	                int DBquantity = set.getInt("STOCK_QUANTITY");
//	               
//
//	                Product ob = new Product(DBproductId, DBproductName, DBdescription, DBprice, DBquantity);
//	                productList.add(ob);
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	     
//	    
//
//	     private static List<Supplier> supplierList = new ArrayList<>();
//		private static int orderId;
//
//	     
//	     public static void addSupplier() throws SQLException {
//	         Scanner sc = new Scanner(System.in);
//	         System.out.println("Enter the Supplier Details");
//	         System.out.println("Enter the Supplier ID");
//	         int supplierID = sc.nextInt();
//	         sc.nextLine();
//	         System.out.println("Enter the Supplier Password");
//	         String password = sc.nextLine();
//	         System.out.println("Enter the Supplier Name");
//	         String supplierName = sc.nextLine();
//	         System.out.println("Enter the Contact Person");
//	         String conPerson = sc.nextLine();
//	         System.out.println("Enter the Contact Number");
//	         Long conNumber = sc.nextLong();
//	         sc.nextLine();
//	         System.out.println("Enter the Supplier Address");
//	         String address = sc.nextLine();
//
//	         Supplier ob = new Supplier(supplierID, password, supplierName, conPerson, conNumber, address);
//	         supplierList.add(ob);
//	         supplierAddToDatabase(ob);
//	     }
//
//	     public static void supplierAddToDatabase(Supplier ob) {
//	         try (Connection con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
//	             String insertQuery = "INSERT INTO DATABASE.Supplier (SUPPLIER_ID, SUPPLIER_NAME, CONTACT_PERSON, CONTACT_NUMBER, ADDRESS, SUPPLIER_PASSWORD) VALUES (?, ?, ?, ?, ?, ?)";
//	             try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
//
//	                 preparedStatement.setInt(1, ob.getSupplierID());
//	                 preparedStatement.setString(2, ob.getSupplierName());
//	                 preparedStatement.setString(3, ob.getConPerson());
//	                 preparedStatement.setLong(4, ob.getConNumber());
//	                 preparedStatement.setString(5, ob.getAddress());
//	                 preparedStatement.setString(6, ob.getPassword());
//
//	                 int rowsAffected = preparedStatement.executeUpdate();
//
//	                 if (rowsAffected > 0) {
//	                     System.out.println("Supplier added to the database successfully.");
//	                     supplierList.add(ob);  // Add the Supplier object to the list
//	                 } else {
//	                     System.out.println("Failed to add supplier to the database.");
//	                 }
//	             }
//	         } catch (SQLException e) {
//	             e.printStackTrace();  // Handle the exception more appropriately (e.g., log it)
//	         }
//	     }
//	 	public static List<Supplier> getSupplierList() {
//	        return supplierList;
//	    }
//
//	    public static void updateSupplier() throws SQLException {
//	        try (Scanner sc = new Scanner(System.in)) {
//	            System.out.println("Enter the Supplier ID to update:");
//	            int supplierID = sc.nextInt();
//	            
//	            boolean isAvailable = findSupplier(supplierID);
//	            
//	            if (isAvailable) {
//	                sc.nextLine(); // Consume the newline character
//	                System.out.println("Enter the Supplier Password:");
//	                String password = sc.nextLine();
//	                System.out.println("Enter the Supplier Name:");
//	                String supplierName = sc.nextLine();
//	                System.out.println("Enter the Contact Person:");
//	                String contactPerson = sc.nextLine();
//	                System.out.println("Enter the Contact Number:");
//	                Long contactNumber = sc.nextLong();
//	                sc.nextLine(); 
//	                System.out.println("Enter the Supplier Address:");
//	                String address = sc.nextLine();
//	                
//	                Supplier update = new Supplier(supplierID, password, supplierName, contactPerson, contactNumber, address);
//	                supplierUpdateToDB(update);
//	            } else {
//	                System.out.println("Supplier Not Found");
//	            }
//	        }
//	    }
//
//
//	    public static void supplierUpdateToDB(Supplier update) {
//	        try {
//	            String updateQuery = "UPDATE DATABASE.Supplier SET SUPPLIER_NAME=?, CONTACT_PERSON=?, CONTACT_NUMBER=?, ADDRESS=?, SUPPLIER_PASSWORD=? WHERE SUPPLIER_ID=?";
//	            try (Connection con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
//	                 PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
//	                preparedStatement.setString(1, update.getSupplierName());
//	                preparedStatement.setString(2, update.getConPerson());
//	                preparedStatement.setLong(3, update.getConNumber());
//	                preparedStatement.setString(4, update.getAddress());
//	                preparedStatement.setString(5, update.getPassword());
//	                preparedStatement.setInt(6, update.getSupplierID());
//
//	                int rowsUpdated = preparedStatement.executeUpdate();
//
//	                if (rowsUpdated > 0) {
//	                    System.out.println("Supplier updated in the database successfully.");
//	                } else {
//	                    System.out.println("Failed to update supplier in the database.");
//	                }
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//	    public static boolean findSupplier(int supplierID) {
//	        try {
//	        	
//	            String query = "SELECT * FROM DATABASE.supplier WHERE SUPPLIER_ID = ?";
//	            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
//		            preparedStatement.setInt(1, supplierID);
//		            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//		                return resultSet.next();
//		            }
//	                
//	            }
//	        } catch (SQLException e) {
//	            
//	            return false;
//	        }
//	    }
//
//	    public static void deleteSupplier() {
//	        try (Scanner sc = new Scanner(System.in)) {
//	            System.out.println("Enter the Supplier ID to delete:");
//	            int supplierID = sc.nextInt();
//	           
//	            boolean isAvailable = findSupplier(supplierID);
//	            System.out.println("             =====isAvailable"+isAvailable);
//
//	            if (isAvailable) {
//	                supplierDeleteFromDB(supplierID);
//	            
//	            } else {
//	                System.out.println("Supplier Not Found");
//	            }
//	        }
//	    }
//
//	    public static  void supplierDeleteFromDB(int supplierID) {
//	        try {
//	            String deleteQuery = "DELETE FROM DATABASE.Supplier WHERE SUPPLIER_ID=?";
//	            try (Connection con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
//	                 PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
//
//	                preparedStatement.setInt(1, supplierID);
//	                int rowsDeleted = preparedStatement.executeUpdate();
//
//	                if (rowsDeleted > 0) {
//	                	System.out.println("Supplier deleted from the database successfully.");
//	                } else {
//	                    System.out.println("Failed to delete supplier from the database.");
//	                }
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	
//	    static {
//	        try {
//	            con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	    public static void showSuppliers() {
//	        try {
//	            String query = "SELECT SUPPLIER_ID, SUPPLIER_NAME, CONTACT_PERSON, CONTACT_NUMBER, ADDRESS, SUPPLIER_PASSWORD FROM DATABASE.Supplier";
//
//	            try (Statement statement = con.createStatement();
//	                 ResultSet resultSet = statement.executeQuery(query)) {
//
//	                if (resultSet.next()) {
//	                    System.out.println("---------------------------------------------------------------------------------------------------------------------");
//	                    System.out.printf("| %-10s | %-20s | %-20s | %-15s | %-15s | %-40s |\n", "Supplier ID", "Supplier Name", "Contact Person", "Contact Number", "Address", "Password");
//	                    System.out.println("---------------------------------------------------------------------------------------------------------------------");
//
//	                    do {
//	                        System.out.printf("| %-10s | %-20s | %-20s | %-15s | %-15s | %-40s |\n",
//	                                resultSet.getInt("SUPPLIER_ID"), resultSet.getString("SUPPLIER_NAME"),
//	                                resultSet.getString("CONTACT_PERSON"), resultSet.getLong("CONTACT_NUMBER"),
//	                                resultSet.getString("ADDRESS"), resultSet.getString("SUPPLIER_PASSWORD"));
//	                    } while (resultSet.next());
//
//	                    System.out.println("---------------------------------------------------------------------------------------------------------------------");
//	                } else {
//	                    System.out.println("No suppliers found.");
//	                }
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//
//
//	     
//	    private static void handleOrderManagement() throws SQLException {
//	        boolean exitOrder = false;
//
//	        Scanner sc = new Scanner(System.in);
//
//	        while (!exitOrder) {
//	            System.out.println("1. Add Order");
//	            System.out.println("2. Cancel Order");
//	            System.out.println("3. View Orders");
//	            System.out.println("4. Check Order Status");
//	            System.out.println("5. Back to Main Menu");
//	            System.out.print("Enter Your Choice: ");
//
//	            try {
//	                int orderChoice = sc.nextInt();
//	                sc.nextLine(); // Consume the newline character
//
//	                switch (orderChoice) {
//	                    case 1:
//	                        placeOrder();
//	                        break;
//
//	                    case 2:
//	                        cancelOrder();
//	                        break;
//
//	                    case 3:
//	                        viewOrders();
//	                        break;
//	                    
//
//	                    case 4:
//	                    	 System.out.println("Exiting the program...");
//	                        exitOrder = true;
//	                        break;
//
//	                    default:
//	                        System.out.println("Invalid choice. Please enter a valid option.");
//	                }
//	            } catch (InputMismatchException e) {
//	                System.out.println("Invalid input. Please enter a valid integer.");
//	                sc.nextLine(); // Consume the invalid input
//	            }
//	        }
//
//	        sc.close();
//	    }
//
//
//	     public static void placeOrder() throws SQLException {
//	    	    Scanner scanner = new Scanner(System.in);
//
//	    	    try {
//	    	        System.out.print("Enter user ID: ");
//	    	        int userId = scanner.nextInt();
//
//	    	        System.out.print("Enter order ID: ");
//	    	        int orderId = scanner.nextInt();
//
//	    	        System.out.print("Enter product name: ");
//	    	        String productName = scanner.next();
//
//	    	        // Check if the product exists
//	    	        if (productExists(productName)) {
//	    	            System.out.print("Enter supplier ID: ");
//	    	            int supplierId = scanner.nextInt();
//
//	    	            // Check if the supplier exists
//	    	            if (supplierExists(supplierId)) {
//	    	                System.out.print("Enter quantity: ");
//	    	                int quantity = scanner.nextInt();
//	    	                scanner.nextLine();
//	    	                System.out.print("Enter order status: ");
//	    	                String orderStatus = scanner.nextLine();
//	    	              
//
//	    	                // Call your insertOrder method with the obtained inputs
//	    	                insertOrder(userId, orderId, productName, supplierId, quantity, orderStatus);
//	    	                System.out.println("Order placed successfully!");
//
//	    	            } else {
//	    	                System.out.println("Supplier does not exist. Order placement failed.");
//	    	            }
//	    	        } else {
//	    	            System.out.println("Product does not exist. Order placement failed.");
//	    	        }
//
//	    	    } catch (InputMismatchException e) {
//	    	        System.out.println("Invalid input. Please enter a valid integer or string as appropriate.");
//	    	        scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
//	    	        placeOrder(); // Recursive call to retry the input
//	    	    } finally {
//	    	        scanner.close(); // Close the scanner to prevent resource leak
//	    	    }
//	    	}
//
//
//	     public static boolean productExists(String productName) throws SQLException {
//	    	    
//	    	    String query = "SELECT COUNT(*) FROM database.Products WHERE PRODUCT_NAME = ?";
//	    	    
//	    	    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
//	    	        preparedStatement.setString(1, productName);
//
//	    	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
//	    	            // If there is at least one row in the result set, the product exists
//	    	            return resultSet.next() && resultSet.getInt(1) > 0;
//	    	        }
//	    	    }
//	    	}
//
//
//	     public static boolean supplierExists(int supplierId) throws SQLException {
//	    	    // Implement the database query to check if the supplier with the given ID exists
//	    	    String query = "SELECT COUNT(*) FROM database.Supplier WHERE SUPPLIER_ID = ?";
//	    	    
//	    	    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
//	    	        preparedStatement.setInt(1, supplierId);
//
//	    	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
//	    	            // If there is at least one row in the result set, the supplier exists
//	    	            return resultSet.next() && resultSet.getInt(1) > 0;
//	    	        }
//	    	    }
//	    	}
//
//        
//	     public static void insertOrder(int userId,int orderId, String productName, int supplierId, int quantity,String orderStatus) throws SQLException {
//	    	    // Implement the database query to insert the order details into the Orders table
//	    	 String insertQuery = "INSERT INTO database.Orders (USER_ID,ORDER_ID, PRODUCT_NAME, SUPPLIER_ID, QUANTITY, STATUS) VALUES (?, ?, ?, ?, ?, ?)";
//
//
//	    	    try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
//	    	        preparedStatement.setInt(1, userId);
//	    	        preparedStatement.setInt(2, orderId);
//	    	        preparedStatement.setString(3, productName);
//	    	        preparedStatement.setInt(4, supplierId);
//	    	        preparedStatement.setInt(5, quantity);
//	    	        preparedStatement.setString(6, orderStatus);
//
//	    	        int rowsInserted = preparedStatement.executeUpdate();
//
//	    	        if (rowsInserted > 0) {
//	    	            System.out.println("Order placed successfully.");
//	    	        } else {
//	    	            System.out.println("Failed to place the order.");
//	    	        }
//	    	    }
//	    	}
//
//
//
//	     public static void viewOrders() {
//	    	    try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
//	    	        String sql = "SELECT * FROM DATABASE.orders";
//	    	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
//	    	             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//	    	            while (resultSet.next()) {
//	    	                int orderId = resultSet.getInt("ORDER_ID");
//	    	                String productName = resultSet.getString("PRODUCT_NAME");
//	    	                String status = resultSet.getString("STATUS");
//
//	    	                System.out.println("Order ID: " + orderId);
//	    	                System.out.println("Product Name: " + productName);
//	    	                System.out.println("Status: " + status);
//	    	                System.out.println("----------------------");
//	    	            }
//	    	        }
//	    	    } catch (SQLException e) {
//	    	        e.printStackTrace();
//	    	    }
//	    	}
//
//
//	     public static void cancelOrder() {
//	    	    try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
//	    	        String sql = "UPDATE database.Orders SET STATUS = 'CANCELLED' WHERE ORDER_ID = ?";
//	    	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//	    	            preparedStatement.setInt(1, orderId);
//	    	            int affectedRows = preparedStatement.executeUpdate();
//
//	    	            if (affectedRows > 0) {
//	    	                System.out.println("Order canceled successfully");
//	    	            } else {
//	    	                System.out.println("Order not found or already canceled");
//	    	            }
//	    	        }
//	    	    } catch (SQLException e) {
//	    	        e.printStackTrace();
//	    	    }
//	    	}
//
//	     public void decreaseStock(String productName, int quantity) {
//	         // Implement logic to update the stock in the database or data structure
//	         // This example assumes you have a method to access the database connection
//
//	         try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
//	             String updateQuery = "UPDATE database.products SET stock = stock - ? WHERE product_id = ?";
//	             try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
//	                 preparedStatement.setInt(1, quantity);
//	                 preparedStatement.setString(2, productName);
//	                 int updatedRows = preparedStatement.executeUpdate();
//
//	                 if (updatedRows > 0) {
//	                     System.out.println("Stock decreased successfully");
//	                 } else {
//	                     System.out.println("Product not found or insufficient stock");
//	                 }
//	             }
//	         } catch (SQLException e) {
//	             e.printStackTrace();
//	             // Handle the exception as needed
//	         }
//	     }
//	     public static void sendNotificationToAdmin(String message) {
//	    	    // This is a placeholder method; you can implement your own notification mechanism here.
//	    	    // For example, you could send an email, push notification, or log the message for the admin.
//	    	    System.out.println("Stock level below minimum for product so please update it as soon : " + message);
//	    	    // You can replace the print statement with your actual notification mechanism.
//	    	}
//
//	    
//	 }
//
//    
//
// 
//

package com.userdetails;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Admin extends BuisnessUsers {

    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "Expleosolutions";

    private static Connection con;
    static List<Product> productList = new ArrayList<Product>();

    public Admin() throws ClassNotFoundException, IOException, SQLException {
        super("defaultPersonId", "defaultFirstName", "defaultLastName", 'M', "defaultEmail", 0L, "defaultAddress",
                null, null, null);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw e;
        }
        intro();
    }

    
   




    public static void intro() throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ____________________________________________");
        System.out.println("|   Welcome to Inventory Management Systems  |");
        System.out.println("|────────────────────────────────────────────|");
        System.out.println("| Type 1: Login                              |");
        System.out.println("| Type 2: Create Account                     |");
        System.out.println("|____________________________________________|");

        int choiceAcc = scanner.nextInt();
        scanner.nextLine();

        switch (choiceAcc) {
          case 1:
        	Account.loginFun();
            break;
            
            case 2:
            	Account.createAccFun();
                break;

            default:
                System.out.println("Incorrect! Choose a valid option again.");
                intro();
        }
    }

  
   public static void showMenu() throws SQLException {
        Scanner sc = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("1.Inventory Tracking \n2.Supplier Management \n3.Profile Management\n4.Order Management\n5.Exit");
            System.out.println("Enter Your Choice:");

            int mainChoice = sc.nextInt();

            switch (mainChoice) {
                case 1:
                    handleInventoryManagement();
                    break;

                case 2:
                    handleSupplierManagement();
                    break;

                case 3:
                    handleProfileManagement();
                    break;

                case 4:
                	handleOrderManagement();
                    break;

                case 5:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        sc.close();
    }

    public static void handleInventoryManagement() throws SQLException {
        boolean exitInventory = false;

        while (!exitInventory) {
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Products");
            System.out.println("5. Back to Main Menu");
            System.out.println("Enter Your Choice");

            Scanner sc = new Scanner(System.in);
            int inventoryChoice = sc.nextInt();

            switch (inventoryChoice) {
                case 1:
                    addProduct();
                    break;

                case 2:
                    updateProduct();
                    break;

                case 3:
                    deleteProduct();
                    break;

                case 4:
                    viewProducts();
                    break;

                case 5:
                	 System.out.println("Exting the program");
                    exitInventory = true;
                   
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static void handleSupplierManagement() throws SQLException {
        boolean exitSupplier = false;

        while (!exitSupplier) {
            System.out.println("1. Add a Supplier\n2. Update a Supplier\n3.Display the suppliers\n4. Back to Main Menu");
            System.out.println("Enter Your Choice");

            Scanner sc = new Scanner(System.in);
            int supplierChoice = sc.nextInt();

            switch (supplierChoice) {
                case 1:
                	addSupplier();
                    break;

                case 2:
                    updateSupplier();
                    break;

                case 3:
                    showSuppliers();
                    break;

                case 4:
                    exitSupplier = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    public static void handleProfileManagement() throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean exitProfile = false;

        while (!exitProfile) {
            System.out.println("1. Update Profile Management\n2. Display Details\n3. Back to Main Menu");
            System.out.println("Enter Your Choice:");

            String input = sc.nextLine();

            if (!input.matches("\\d")) {
                System.out.println("Invalid input. Please enter a valid option.");
                continue; // Continue to the next iteration of the loop
            }

            int profileChoice = Integer.parseInt(input);

            switch (profileChoice) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    DisplayProfile();
                    break;
                case 3:
                    exitProfile = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        // Close the Scanner after the loop
        sc.close();
    }



    private static Connection con1;
    private static List<Product> productList1 = new ArrayList<>();
	
	
	//Inventry management starts here
	 public static void addProduct() {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter the Product Details");
	        System.out.println("***************************");

	        System.out.println("Enter the Product ID:");
	        int productId = sc.nextInt();
	        sc.nextLine();

	        System.out.println("Enter the Product Name:");
	        String productName = sc.nextLine();

	        System.out.println("Enter the Product Description:");
	        String productDescription = sc.nextLine();

	        System.out.println("Enter the Product Price:");
	        double price = sc.nextDouble();

	        System.out.println("Enter the Product Quantity:");
	        int quantity = sc.nextInt();

	        System.out.println("Enter the Supplier ID:");
	        int supplierId = sc.nextInt();

	        Product product = new Product(productId, productName, productDescription, price, quantity, supplierId);
	        productList.add(product);
	        addToDatabase(product);
	    }
	 
	 public static void updateProduct() throws SQLException {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter the Product Details to Update");
	        System.out.println("Enter the Product ID");
	        int productID = sc.nextInt();
	        sc.nextLine();
	        boolean isAvailable = findProduct(productID);
	        if (isAvailable) {
	            System.out.println("Enter the Product Name");
	            String productName = sc.nextLine();
	            System.out.println("Enter the Product description");
	            String description = sc.nextLine();
	            System.out.println("Enter the Product Price");
	            double price = sc.nextDouble();
	            System.out.println("Enter the Product Quantity");
	            int quantity = sc.nextInt();

	            updateDatabase(productID, productName, description, price, quantity);
	        } else {
	            System.out.println("Product Not Found");
	        }
	  }
	 public static void deleteProduct() {
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Enter the Product ID to delete:");
	        int productId = sc.nextInt();

	        boolean isAvailable = findProduct(productId);
	        if (isAvailable) {
	            String deleteQuery = "DELETE FROM Database.Products WHERE PRODUCT_ID = ?";
	            try (PreparedStatement ps = con.prepareStatement(deleteQuery)) {
	                ps.setInt(1, productId);

	                int affectedRows = ps.executeUpdate();

	                if (affectedRows > 0) {
	                    System.out.println("Product deleted from the Database");
	                } else {
	                    System.out.println("Failed to delete product from the Database.");
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("Product Not Found");
	        }
	    }
	  
	 public static void viewProducts() {
		    String query = "SELECT PRODUCT_ID, PRODUCT_NAME, DESCRIPTION, PRICE, STOCK_QUANTITY FROM Database.Products";

		    try (Statement statement = con.createStatement();
		         ResultSet resultSet = statement.executeQuery(query)) {

		        System.out.println("-----------------------------------------------------------------------------------------------------------------------");
		        System.out.printf("| %-10s | %-20s | %-30s | %-10s | %-15s |\n",
		                "Product ID", "Product Name", "Description", "Price", "Quantity");
		        System.out.println("-----------------------------------------------------------------------------------------------------------------------");

		        while (resultSet.next()) {
		            int productId = resultSet.getInt("PRODUCT_ID");
		            String productName = resultSet.getString("PRODUCT_NAME");
		            String description = resultSet.getString("DESCRIPTION");
		            double price = resultSet.getDouble("PRICE");
		            int quantity = resultSet.getInt("STOCK_QUANTITY");

		            System.out.printf("| %-10s | %-20s | %-30s | $%-9.2f | %-15s |\n",
		                    productId, productName, description, price, quantity);
		        }

		        System.out.println("-----------------------------------------------------------------------------------------------------------------------");

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	  //Inventry management ends here
	  
	  
	  //Profile management starts here
	  private static String currentUserId = null;

	    private static String getCurrentUserId() {
	        return currentUserId;
	    }

	    // Set the current user ID when the user logs in
	    public static void setCurrentUserId(String userId) {
	        currentUserId = userId;
	    }
	    
	    public static boolean userExists(int userId) throws SQLException {
	        String query = "SELECT USER_ID FROM database.ACCOUNT WHERE USER_ID = ?";

	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	            preparedStatement.setInt(1, userId);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                return resultSet.next();
	            }
	        }
	    }
	    
	    
	    //updateProfile
	    @SuppressWarnings("unused")
		//static
	    public static void updateProfile() throws SQLException {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Enter the ID to update profile:");

	        int userId = scanner.nextInt();
	        scanner.nextLine(); // Consume the newline character

	        // Check if the user with the provided ID exists in the database
	        if (userExists(userId)) {
	            System.out.println("User found! Enter new details for the profile:");

	            System.out.print("Enter new password: ");
	            String newPassword = scanner.nextLine();

	            System.out.print("Enter new address: ");
	            String newAddress = scanner.nextLine();

	            System.out.print("Enter new phone number: ");
	            String newPhoneNumber = scanner.nextLine();

	            // Validate phone number (exactly 10 digits)
	            if (!newPhoneNumber.matches("\\d{10}")) {
	                System.out.println("Phone number must be exactly 10 digits.");
	                return;
	            }

	           

	            try {
	                // Assuming you have a method to get the current user's ID
	                String currentUserId = getCurrentUserId();

	                String updateQuery = "UPDATE database.ACCOUNT SET PASSWORD=?, ADDRESS=?, PHONE_NUMBER=? WHERE USER_ID=?";
	                try (PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
	                    preparedStatement.setString(1, newPassword);
	                    preparedStatement.setString(2, newAddress);
	                    preparedStatement.setString(3, newPhoneNumber);
	                    preparedStatement.setInt(4, userId);

	                    int rowsUpdated = preparedStatement.executeUpdate();

	                    if (rowsUpdated > 0) {
	                        System.out.println("Profile updated successfully.");
	                    } else {
	                        System.out.println("Failed to update profile.");
	                    }
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } else {
	            System.out.println("User with ID " + userId + " not found in the database.");
	        }
	    }

	    //DisplayProfile
	    public static void DisplayProfile() {
	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
	        System.out.printf("| %-10s | %-15s | %-10s | %-30s | %-20s | %-15s | %-30s |\n", "User ID", "Username", "Role", "Name", "Phone Number", "Address", "Password");
	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

	        String query = "SELECT USER_ID, USERNAME, ROLE, NAME, PHONE_NUMBER, ADDRESS, PASSWORD FROM database.ACCOUNT";
	        try (PreparedStatement preparedStatement = con.prepareStatement(query);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	                String userId = resultSet.getString("USER_ID");
	                String username = resultSet.getString("USERNAME");
	                String role = resultSet.getString("ROLE");
	                String name = resultSet.getString("NAME");
	                String phoneNumber = resultSet.getString("PHONE_NUMBER");
	                String address = resultSet.getString("ADDRESS");
	                String password = resultSet.getString("PASSWORD");

	                System.out.printf("| %-10s | %-15s | %-10s | %-30s | %-20s | %-15s | %-30s |\n",
	                        userId, username, role, name, phoneNumber, address, password);
	            }
	        } catch (SQLException e) {
	            System.out.println("An error occurred while fetching the profile information.");
	            e.printStackTrace();
	        }

	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
	    }
	    
	    //DisplayProfile
//	  public static void DisplayProfile() throws SQLException {
//	        System.out.println("-------------------------------------------------------------------------");
//	        System.out.printf("| %-15s | %-20s | %-15s | %-30s | %-15s |\n", "User ID", "Username", "Role", "Name", "Phone Number");
//	        System.out.println("-------------------------------------------------------------------------");
//
//	        String query = "SELECT USER_ID, USERNAME, ROLE, NAME, PHONE_NUMBER FROM database.ACCOUNT";
//	        try (PreparedStatement preparedStatement = con.prepareStatement(query);
//	             ResultSet resultSet = preparedStatement.executeQuery()) {
//
//	            while (resultSet.next()) {
//	                String userId = resultSet.getString("USER_ID");
//	                String username = resultSet.getString("USERNAME");
//	                String role = resultSet.getString("ROLE");
//	                String name = resultSet.getString("NAME");
//	                String phoneNumber = resultSet.getString("PHONE_NUMBER");
//
//	                System.out.printf("| %-15s | %-20s | %-15s | %-30s | %-15s |\n",
//	                        userId, username, role, name, phoneNumber);
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//
//	        System.out.println("-------------------------------------------------------------------------");
//	    }
	    
	    //DisplayProfile ends here
	    public static void updateDatabase(int productID, String productName, String description, double price, int quantity) {
	        String update = "UPDATE Database.Products SET PRODUCT_NAME = ?, DESCRIPTION = ?, PRICE = ?, STOCK_QUANTITY = ? WHERE PRODUCT_ID = ?";

	        try (PreparedStatement ps = con.prepareStatement(update)) {
	            ps.setString(1, productName);
	            ps.setString(2, description);
	            ps.setDouble(3, price);
	            ps.setInt(4, quantity);
	            ps.setInt(5, productID);

	            int affectedRows = ps.executeUpdate();

	            if (affectedRows > 0) {
	                System.out.println("Product updated in the Database");
	            } else {
	                System.out.println("Failed to update product in the Database.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	   

	    public static boolean findProduct(int productId) {
	        String query = "SELECT PRODUCT_ID FROM Database.Products WHERE PRODUCT_ID = ?";

	        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {

	            preparedStatement.setInt(1, productId);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                return resultSet.next();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public static void addToDatabase(Product product) {
	        String insert = "INSERT INTO Database.Products (PRODUCT_ID, PRODUCT_NAME, DESCRIPTION, PRICE, STOCK_QUANTITY, SUPPLIER_ID) VALUES (?, ?, ?, ?, ?, ?)";

	        try (PreparedStatement ps = con.prepareStatement(insert)) {
	            ps.setInt(1, product.getProductId());
	            ps.setString(2, product.getProductName());
	            ps.setString(3, product.getProductDescription());
	            ps.setDouble(4, product.getPrice());
	            ps.setInt(5, product.getProductQuantity());
	            ps.setInt(6, product.getSupplierId());

	            int affectedRows = ps.executeUpdate();

	            if (affectedRows > 0) {
	                System.out.println("Product added into the Database");
	            } else {
	                System.out.println("Failed to add product into the Database.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	     void fetchProductdetails() {
	        String dataFetch = "SELECT PRODUCT_ID, PRODUCT_NAME, DESCRIPTION, PRICE, STOCK_QUANTITY, SUPPLIER_ID FROM Database.Products";
	        try (Statement state = con.createStatement();
	             ResultSet set = state.executeQuery(dataFetch)) {
	            while (set.next()) {
	                int DBproductId = set.getInt("PRODUCT_ID");
	                String DBproductName = set.getString("PRODUCT_NAME");
	                String DBdescription = set.getString("DESCRIPTION");
	                double DBprice = set.getDouble("PRICE");
	                int DBquantity = set.getInt("STOCK_QUANTITY");
	                int DBsupplierId = set.getInt("SUPPLIER_ID");

	                Product ob = new Product(DBproductId, DBproductName, DBdescription, DBprice, DBquantity, DBsupplierId);
	                productList.add(ob);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	     
	    

	     private static List<Supplier> supplierList = new ArrayList<>();
		private static int orderId;

	     
	     public static void addSupplier() throws SQLException {
	         Scanner sc = new Scanner(System.in);
	         System.out.println("Enter the Supplier Details");
	         System.out.println("Enter the Supplier ID");
	         int supplierID = sc.nextInt();
	         sc.nextLine();
	         System.out.println("Enter the Supplier Password");
	         String password = sc.nextLine();
	         System.out.println("Enter the Supplier Name");
	         String supplierName = sc.nextLine();
	         System.out.println("Enter the Contact Person");
	         String conPerson = sc.nextLine();
	         System.out.println("Enter the Contact Number");
	         Long conNumber = sc.nextLong();
	         sc.nextLine();
	         System.out.println("Enter the Supplier Address");
	         String address = sc.nextLine();

	         Supplier ob = new Supplier(supplierID, password, supplierName, conPerson, conNumber, address);
	         supplierList.add(ob);
	         supplierAddToDatabase(ob);
	     }

	     public static void supplierAddToDatabase(Supplier ob) {
	         try (Connection con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	             String insertQuery = "INSERT INTO DATABASE.Supplier (SUPPLIER_ID, SUPPLIER_NAME, CONTACT_PERSON, CONTACT_NUMBER, ADDRESS, SUPPLIER_PASSWORD) VALUES (?, ?, ?, ?, ?, ?)";
	             try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {

	                 preparedStatement.setInt(1, ob.getSupplierID());
	                 preparedStatement.setString(2, ob.getSupplierName());
	                 preparedStatement.setString(3, ob.getConPerson());
	                 preparedStatement.setLong(4, ob.getConNumber());
	                 preparedStatement.setString(5, ob.getAddress());
	                 preparedStatement.setString(6, ob.getPassword());

	                 int rowsAffected = preparedStatement.executeUpdate();

	                 if (rowsAffected > 0) {
	                     System.out.println("Supplier added to the database successfully.");
	                     supplierList.add(ob);  // Add the Supplier object to the list
	                 } else {
	                     System.out.println("Failed to add supplier to the database.");
	                 }
	             }
	         } catch (SQLException e) {
	             e.printStackTrace();  // Handle the exception more appropriately (e.g., log it)
	         }
	     }
	 	public static List<Supplier> getSupplierList() {
	        return supplierList;
	    }

	    public static void updateSupplier() throws SQLException {
	        try (Scanner sc = new Scanner(System.in)) {
	            System.out.println("Enter the Supplier ID to update:");
	            int supplierID = sc.nextInt();
	            
	            boolean isAvailable = findSupplier(supplierID);
	            
	            if (isAvailable) {
	                sc.nextLine(); // Consume the newline character
	                System.out.println("Enter the Supplier Password:");
	                String password = sc.nextLine();
	                System.out.println("Enter the Supplier Name:");
	                String supplierName = sc.nextLine();
	                System.out.println("Enter the Contact Person:");
	                String contactPerson = sc.nextLine();
	                System.out.println("Enter the Contact Number:");
	                Long contactNumber = sc.nextLong();
	                sc.nextLine(); 
	                System.out.println("Enter the Supplier Address:");
	                String address = sc.nextLine();
	                
	                Supplier update = new Supplier(supplierID, password, supplierName, contactPerson, contactNumber, address);
	                supplierUpdateToDB(update);
	            } else {
	                System.out.println("Supplier Not Found");
	            }
	        }
	    }


	    public static void supplierUpdateToDB(Supplier update) {
	        try {
	            String updateQuery = "UPDATE DATABASE.Supplier SET SUPPLIER_NAME=?, CONTACT_PERSON=?, CONTACT_NUMBER=?, ADDRESS=?, SUPPLIER_PASSWORD=? WHERE SUPPLIER_ID=?";
	            try (Connection con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	                 PreparedStatement preparedStatement = con.prepareStatement(updateQuery)) {
	                preparedStatement.setString(1, update.getSupplierName());
	                preparedStatement.setString(2, update.getConPerson());
	                preparedStatement.setLong(3, update.getConNumber());
	                preparedStatement.setString(4, update.getAddress());
	                preparedStatement.setString(5, update.getPassword());
	                preparedStatement.setInt(6, update.getSupplierID());

	                int rowsUpdated = preparedStatement.executeUpdate();

	                if (rowsUpdated > 0) {
	                    System.out.println("Supplier updated in the database successfully.");
	                } else {
	                    System.out.println("Failed to update supplier in the database.");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public static boolean findSupplier(int supplierID) {
	        try {
	        	
	            String query = "SELECT * FROM DATABASE.supplier WHERE SUPPLIER_ID = ?";
	            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
		            preparedStatement.setInt(1, supplierID);
		            try (ResultSet resultSet = preparedStatement.executeQuery()) {
		                return resultSet.next();
		            }
	                
	            }
	        } catch (SQLException e) {
	            
	            return false;
	        }
	    }

	    public static void deleteSupplier() {
	        try (Scanner sc = new Scanner(System.in)) {
	            System.out.println("Enter the Supplier ID to delete:");
	            int supplierID = sc.nextInt();
	           
	            boolean isAvailable = findSupplier(supplierID);
	            System.out.println("             =====isAvailable"+isAvailable);

	            if (isAvailable) {
	                supplierDeleteFromDB(supplierID);
	            
	            } else {
	                System.out.println("Supplier Not Found");
	            }
	        }
	    }

	    public static  void supplierDeleteFromDB(int supplierID) {
	        try {
	            String deleteQuery = "DELETE FROM DATABASE.Supplier WHERE SUPPLIER_ID=?";
	            try (Connection con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	                 PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {

	                preparedStatement.setInt(1, supplierID);
	                int rowsDeleted = preparedStatement.executeUpdate();

	                if (rowsDeleted > 0) {
	                	System.out.println("Supplier deleted from the database successfully.");
	                } else {
	                    System.out.println("Failed to delete supplier from the database.");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	    static {
	        try {
	            con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public static void showSuppliers() {
	        try {
	            String query = "SELECT SUPPLIER_ID, SUPPLIER_NAME, CONTACT_PERSON, CONTACT_NUMBER, ADDRESS, SUPPLIER_PASSWORD FROM DATABASE.Supplier";

	            try (Statement statement = con.createStatement();
	                 ResultSet resultSet = statement.executeQuery(query)) {

	                if (resultSet.next()) {
	                    System.out.println("---------------------------------------------------------------------------------------------------------------------");
	                    System.out.printf("| %-10s | %-20s | %-20s | %-15s | %-15s | %-40s |\n", "Supplier ID", "Supplier Name", "Contact Person", "Contact Number", "Address", "Password");
	                    System.out.println("---------------------------------------------------------------------------------------------------------------------");

	                    do {
	                        System.out.printf("| %-10s | %-20s | %-20s | %-15s | %-15s | %-40s |\n",
	                                resultSet.getInt("SUPPLIER_ID"), resultSet.getString("SUPPLIER_NAME"),
	                                resultSet.getString("CONTACT_PERSON"), resultSet.getLong("CONTACT_NUMBER"),
	                                resultSet.getString("ADDRESS"), resultSet.getString("SUPPLIER_PASSWORD"));
	                    } while (resultSet.next());

	                    System.out.println("---------------------------------------------------------------------------------------------------------------------");
	                } else {
	                    System.out.println("No suppliers found.");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	     
	    private static void handleOrderManagement() throws SQLException {
	        boolean exitOrder = false;

	        Scanner sc = new Scanner(System.in);

	        while (!exitOrder) {
	            System.out.println("1. Add Order");
	            System.out.println("2. Cancel Order");
	            System.out.println("3. View Orders");
	            System.out.println("4. Check Order Status");
	            System.out.println("5. Back to Main Menu");
	            System.out.print("Enter Your Choice: ");

	            try {
	                int orderChoice = sc.nextInt();
	                sc.nextLine(); // Consume the newline character

	                switch (orderChoice) {
	                    case 1:
	                        placeOrder();
	                        break;

	                    case 2:
	                        cancelOrder();
	                        break;

	                    case 3:
	                        viewOrders();
	                        break;

	                    case 4:
	                        exitOrder = true;
	                        break;

	                    default:
	                        System.out.println("Invalid choice. Please enter a valid option.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Invalid input. Please enter a valid integer.");
	                sc.nextLine(); // Consume the invalid input
	            }
	        }

	        sc.close();
	    }


	     public static void placeOrder() throws SQLException {
	    	    Scanner scanner = new Scanner(System.in);

	    	    try {
	    	        System.out.print("Enter user ID: ");
	    	        int userId = scanner.nextInt();

	    	        System.out.print("Enter order ID: ");
	    	        int orderId = scanner.nextInt();

	    	        System.out.print("Enter product name: ");
	    	        String productName = scanner.next();

	    	        // Check if the product exists
	    	        if (productExists(productName)) {
	    	            System.out.print("Enter supplier ID: ");
	    	            int supplierId = scanner.nextInt();

	    	            // Check if the supplier exists
	    	            if (supplierExists(supplierId)) {
	    	                System.out.print("Enter quantity: ");
	    	                int quantity = scanner.nextInt();
	    	                scanner.nextLine();
	    	                System.out.print("Enter order status: ");
	    	                String orderStatus = scanner.nextLine();
	    	              

	    	                // Call your insertOrder method with the obtained inputs
	    	                insertOrder(userId, orderId, productName, supplierId, quantity, orderStatus);
	    	                System.out.println("Order placed successfully!");

	    	            } else {
	    	                System.out.println("Supplier does not exist. Order placement failed.");
	    	            }
	    	        } else {
	    	            System.out.println("Product does not exist. Order placement failed.");
	    	        }

	    	    } catch (InputMismatchException e) {
	    	        System.out.println("Invalid input. Please enter a valid integer or string as appropriate.");
	    	        scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
	    	        placeOrder(); // Recursive call to retry the input
	    	    } finally {
	    	        scanner.close(); // Close the scanner to prevent resource leak
	    	    }
	    	}


	     public static boolean productExists(String productName) throws SQLException {
	    	    
	    	    String query = "SELECT COUNT(*) FROM database.Products WHERE PRODUCT_NAME = ?";
	    	    
	    	    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	    	        preparedStatement.setString(1, productName);

	    	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	    	            // If there is at least one row in the result set, the product exists
	    	            return resultSet.next() && resultSet.getInt(1) > 0;
	    	        }
	    	    }
	    	}


	     public static boolean supplierExists(int supplierId) throws SQLException {
	    	    // Implement the database query to check if the supplier with the given ID exists
	    	    String query = "SELECT COUNT(*) FROM database.Supplier WHERE SUPPLIER_ID = ?";
	    	    
	    	    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
	    	        preparedStatement.setInt(1, supplierId);

	    	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	    	            // If there is at least one row in the result set, the supplier exists
	    	            return resultSet.next() && resultSet.getInt(1) > 0;
	    	        }
	    	    }
	    	}

        
	     public static void insertOrder(int userId,int orderId, String productName, int supplierId, int quantity,String orderStatus) throws SQLException {
	    	    // Implement the database query to insert the order details into the Orders table
	    	 String insertQuery = "INSERT INTO database.Orders (USER_ID,ORDER_ID, PRODUCT_NAME, SUPPLIER_ID, QUANTITY, STATUS) VALUES (?, ?, ?, ?, ?, ?)";


	    	    try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
	    	        preparedStatement.setInt(1, userId);
	    	        preparedStatement.setInt(2, orderId);
	    	        preparedStatement.setString(3, productName);
	    	        preparedStatement.setInt(4, supplierId);
	    	        preparedStatement.setInt(5, quantity);
	    	        preparedStatement.setString(6, orderStatus);

	    	        int rowsInserted = preparedStatement.executeUpdate();

	    	        if (rowsInserted > 0) {
	    	            System.out.println("Order placed successfully.");
	    	        } else {
	    	            System.out.println("Failed to place the order.");
	    	        }
	    	    }
	    	}



	     public static void viewOrders() {
	    	    try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	    	        String sql = "SELECT * FROM DATABASE.orders";
	    	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
	    	             ResultSet resultSet = preparedStatement.executeQuery()) {

	    	            while (resultSet.next()) {
	    	                int orderId = resultSet.getInt("ORDER_ID");
	    	                String productName = resultSet.getString("PRODUCT_NAME");
	    	                String status = resultSet.getString("STATUS");

	    	                System.out.println("Order ID: " + orderId);
	    	                System.out.println("Product Name: " + productName);
	    	                System.out.println("Status: " + status);
	    	                System.out.println("----------------------");
	    	            }
	    	        }
	    	    } catch (SQLException e) {
	    	        e.printStackTrace();
	    	    }
	    	}


	     public static void cancelOrder() {
	    	    try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	    	        String sql = "UPDATE database.Orders SET STATUS = 'CANCELLED' WHERE ORDER_ID = ?";
	    	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	    	            preparedStatement.setInt(1, orderId);
	    	            int affectedRows = preparedStatement.executeUpdate();

	    	            if (affectedRows > 0) {
	    	                System.out.println("Order canceled successfully");
	    	            } else {
	    	                System.out.println("Order not found or already canceled");
	    	            }
	    	        }
	    	    } catch (SQLException e) {
	    	        e.printStackTrace();
	    	    }
	    	}

	     public void decreaseStock(String productName, int quantity) {
	         // Implement logic to update the stock in the database or data structure
	         // This example assumes you have a method to access the database connection

	         try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	             String updateQuery = "UPDATE database.products SET stock = stock - ? WHERE product_id = ?";
	             try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
	                 preparedStatement.setInt(1, quantity);
	                 preparedStatement.setString(2, productName);
	                 int updatedRows = preparedStatement.executeUpdate();

	                 if (updatedRows > 0) {
	                     System.out.println("Stock decreased successfully");
	                 } else {
	                     System.out.println("Product not found or insufficient stock");
	                 }
	             }
	         } catch (SQLException e) {
	             e.printStackTrace();
	             // Handle the exception as needed
	         }
	     }
	     
	     public static void sendNotificationToAdmin(String message) {
	    	    
	    	    System.out.println("Stock level below minimum for product so please update it as soon : " + message);
	    	   
	    	}

	    
	 }

    

 


