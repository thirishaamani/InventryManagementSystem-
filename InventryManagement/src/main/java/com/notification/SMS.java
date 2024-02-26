package com.notification;

public class SMS extends Notification {
    // Method to send SMS notification
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS: " + message);
    }
}




