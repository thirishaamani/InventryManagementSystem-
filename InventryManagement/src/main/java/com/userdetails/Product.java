package com.userdetails;

public class Product {
    private int productId;
    private String productName;
    private double Price;
    private int productQuantity;
	private String productDescription;
	private int SupplierId;

	// Constructor
	public Product(int productId,String productName, String productDescription, double price, int productQuantity) {
	    this.productId = productId;
	    this.productName = productName;
	    this.productDescription = productDescription;
	    this.Price = price;
	    this.productQuantity = productQuantity;
	}


	public Product(int productId, String productName, String productDescription, double price, int stockQuantity, int SupplierId) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.Price = price;
        this.productQuantity = stockQuantity;
        this.SupplierId = SupplierId;
    }

    // Getters and setters for the new field
    public int getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(int supplierId) {
        this.SupplierId = supplierId;
    }
    

	public Product(String productName2, String category, double price2, int quantity) {
		// TODO Auto-generated constructor stub
	}





	public Product(String dBproductId, String dBproductName, String dBdescription, double dBprice, int dBquantity) {
		// TODO Auto-generated constructor stub
	}





	public Product(String dBproductId, String dBproductName, String dBdescription, double dBprice, int dBquantity,
			int dBsupplierId) {
		// TODO Auto-generated constructor stub
	}


	// Getters
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getPrice() {
        return Price;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", Price=" + Price +
                ", Product_Quantity=" + productQuantity +
                '}';
    }
}

