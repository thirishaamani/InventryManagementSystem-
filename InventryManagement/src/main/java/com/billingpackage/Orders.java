package com.billingpackage;

public class Orders {
	
	public enum OrderStatus {
	    PENDING("Pending"),
	    SHIPPED("Shipped"),
	    DELIVERED("Delivered");

	    private final String status;

	    OrderStatus(String status) {
	        this.status = status;
	    }

	    public String getStatus() {
	        return status;
	    }
	}

	  private OrderStatus status;

	    // Other methods...

	    public OrderStatus getStatus() {
	        return status;
	    }

	    public void setStatus(OrderStatus status) {
	        this.status = status;
	    }
	    
	    
}