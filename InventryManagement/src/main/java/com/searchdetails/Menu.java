package com.searchdetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu implements Search {
    
    private static Connection connection;
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "Expleosolutions";
    private String productId;
    private String productName;

    static {
        try {
            // Initialize the connection in a static block
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   

    public static void searchProduct(String productName) {
        try {
            // Use try-with-resources to automatically close resources (PreparedStatement and ResultSet)
            String sql = "SELECT * FROM DATABASE.products WHERE LOWER(PRODUCT_NAME) LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + productName.toLowerCase() + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Products found, print details in table format
                        System.out.println("-----------------------------------------------------------------------------------------------------------");
                        System.out.printf("| %-15s | %-25s | %-40s | %-10s | %-15s |\n",
                                "Product ID", "Product Name", "Description", "Price", "Stock Quantity");
                        System.out.println("-----------------------------------------------------------------------------------------------------------");

                        do {
                            String productId = resultSet.getString("PRODUCT_ID");
                            String productName1 = resultSet.getString("PRODUCT_NAME");
                            String description = resultSet.getString("DESCRIPTION");
                            double price = resultSet.getDouble("PRICE");
                            double quantity = resultSet.getDouble("STOCK_QUANTITY");

                            // Print table row, adjust spacing dynamically for long descriptions
                            System.out.printf("| %-15s | %-25s | %-40s | %-10.2f | %-15.2f |\n",
                                    productId, productName1, description, price, quantity);

                        } while (resultSet.next());

                        // Print table footer
                        System.out.println("-----------------------------------------------------------------------------------------------------------");
                    } else {
                        throw new Exception("Product not found for keyword: " + productName);
                    }
                } catch (Exception e) {
                    System.out.println("Product not found for keyword: " + productName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public void searchItem(String searchItem) {
        // Method implementation
    }
}
