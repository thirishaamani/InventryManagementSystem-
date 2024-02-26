package com.userdetails;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Supplier {

	private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "Expleosolutions";
	private static int productId;
    
    private int supplierID;
    private String password;
    private String supplierName;
    private String conPerson;
    private String address;
    private Long conNumber;


	public Supplier() throws ClassNotFoundException, IOException, SQLException {
		// TODO Auto-generated constructor stub
		intro();
	}

	public  Supplier(int supplierID, String password, String supplierName, String conPerson, Long conNumber,
            String address) {
        this.supplierID = supplierID;
        this.password = password;
        this.supplierName = supplierName;
        this.conPerson = conPerson;
        this.conNumber = conNumber;
        this.address = address;
     }
	public int getSupplierID() {
		// TODO Auto-generated method stub
		return supplierID;
	}

	public String getConPerson() {
		// TODO Auto-generated method stub
		return conPerson;
	}

	public String getSupplierName() {
		// TODO Auto-generated method stub
		return supplierName;
	}

	public long getConNumber() {
		// TODO Auto-generated method stub
		return conNumber;
	}

	public String getAddress() {
		// TODO Auto-generated method stub
		return address;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}


	 private static void intro() throws IOException, SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Create Products");
            System.out.println("2. Display Products");
            System.out.println("3. Update Products");
            System.out.println("4. Delete Products");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Create Product
                    System.out.println("Enter supplier ID: ");
                    int supplierId = scanner.nextInt();
                    System.out.println("Enter product name: ");
                    String productName = scanner.next();
                    System.out.println("Enter description: ");
                    String description = scanner.next();
                    System.out.println("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.println("Enter quantity in stock: ");
                    int quantity = scanner.nextInt();
                    createProduct(supplierId, productName, description, price, quantity);
                    break;

                case 2:
                    // Read Product
                    System.out.println("Enter product ID: ");
                    int productId = scanner.nextInt();
                    displayProducts();
                    break;

                case 3:
                    // Update Product
                    System.out.println("Enter product ID to update: ");
                    int updateProductId = scanner.nextInt();
                    System.out.println("Enter new product name: ");
                    String newProductName = scanner.next();
                    System.out.println("Enter new description: ");
                    String newDescription = scanner.next();
                    System.out.println("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    System.out.println("Enter new quantity in stock: ");
                    int newQuantity = scanner.nextInt();
                    updateProduct(updateProductId, newProductName, newDescription, newPrice, newQuantity);
                    break;

                case 4:
                    // Delete Product
                    System.out.println("Enter product ID to delete: ");
                    int deleteProductId = scanner.nextInt();
                    deleteProduct(deleteProductId);
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid operation.");
                    break;
            }
        }
    }

    // CRUD Operations

	public static void createProduct(int supplierId, String productName, String description, double price, int quantity) {
	    try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
	        String sql = "INSERT INTO database.SupplierProduct (supplier_id, product_name, description, price, quantity_in_stock, product_id) VALUES (?,?, ?, ?, ?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, supplierId);
	            statement.setString(2, productName);
	            statement.setString(3, description);
	            statement.setDouble(4, price);
	            statement.setInt(5, quantity);
	            statement.setInt(6, productId);

	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Product created successfully!");
	            } else {
	                System.out.println("Failed to create product.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	public static void displayProducts() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "SELECT * FROM database.SupplierProduct";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                System.out.println("Product List:");

                while (resultSet.next()) {
                    System.out.println("Product ID: " + resultSet.getInt("product_id"));
                    System.out.println("Supplier ID: " + resultSet.getInt("supplier_id"));
                    System.out.println("Product Name: " + resultSet.getString("product_name"));
                    System.out.println("Description: " + resultSet.getString("description"));
                    System.out.println("Price: " + resultSet.getDouble("price"));
                    System.out.println("Quantity in Stock: " + resultSet.getInt("quantity_in_stock"));
                    System.out.println("--------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
    public static void deleteProduct(int productId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "DELETE FROM database.SupplierProduct WHERE product_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, productId);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Product deleted successfully!");
                } else {
                    System.out.println("Failed to delete product.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateProduct(int productId, String newProductName, String newDescription, double newPrice, int newQuantity) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            String sql = "UPDATE database.SupplierProduct SET product_name = ?, description = ?, price = ?, quantity_in_stock = ? WHERE product_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, newProductName);
                statement.setString(2, newDescription);
                statement.setDouble(3, newPrice);
                statement.setInt(4, newQuantity);
           //    statement.setInt(5, );

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Product updated successfully!");
                } else {
                    System.out.println("Failed to update product.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
  
}
